import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ReleaseType
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.gradle.kotlin.dsl.get

plugins {
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false
    id("com.gradleup.shadow") version "8.3.3" apply false
    id("com.hypherionmc.modutils.modpublisher") version "2.+"
    java
    idea
    `maven-publish`
}

val minecraftVersion = project.properties["minecraft_version"] as String
architectury.minecraft = minecraftVersion

allprojects {
    version = project.properties["mod_version"] as String
    group = project.properties["maven_group"] as String
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")
    apply(plugin = "com.hypherionmc.modutils.modpublisher")

    base.archivesName.set(project.properties["archives_base_name"] as String + "-${project.name}")

    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://maven.parchmentmc.org")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.architectury.dev/")
        maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        maven("https://maven.jt-dev.tech/releases")
        maven("https://maven.jt-dev.tech/snapshots")
        maven("https://api.modrinth.com/maven").content { includeGroup("maven.modrinth") }
        maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
        maven("https://maven2.bai.lol").content {
            includeGroup("lol.bai")
            includeGroup("mcp.mobius.waila")
        }
        maven("https://jitpack.io")
    }

    @Suppress("UnstableApiUsage")
    dependencies {
        "minecraft"("com.mojang:minecraft:$minecraftVersion")
        "mappings"(loom.layered{
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-1.21:${project.properties["parchment"]}@zip")
        })

        compileOnly("org.jetbrains:annotations:26.0.1")
        compileOnly("com.google.auto.service:auto-service:1.1.1")
        annotationProcessor("com.google.auto.service:auto-service:1.1.1")
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(21)
    }

    publishing {
        publications.create<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }

        repositories {
            mavenLocal()
            maven {
                val releasesRepoUrl = "https://maven.jt-dev.tech/releases"
                val snapshotsRepoUrl = "https://maven.jt-dev.tech/snapshots"
                url = uri(if (project.version.toString().endsWith("SNAPSHOT") || project.version.toString().startsWith("0")) snapshotsRepoUrl else releasesRepoUrl)
                name = "JTDev-Maven-Repository"
                credentials {
                    username = project.properties["repoLogin"]?.toString()
                    password = project.properties["repoPassword"]?.toString()
                }
            }
        }
    }

    if (project.name != "Common")
        publisher {
            apiKeys {
                curseforge(getPublishingCredentials().first)
                modrinth(getPublishingCredentials().second)
                github(project.properties["github_token"].toString())
            }
            displayName.set(base.archivesName.get() + "-${project.version}")
            artifact.set(project.tasks.getByName("remapJar"))
            projectVersion.set(project.version.toString() + "-${project.name}")
            changelog.set(projectDir.toPath().parent.resolve("CHANGELOG.md").toFile().readText())
            curseID.set("")
            modrinthID.set("")
            githubRepo.set("https://github.com/Potion-Studios/All-The-Wood-Weve-Got")
            setReleaseType(ReleaseType.ALPHA)
            setGameVersions(minecraftVersion)
            setCurseEnvironment(CurseEnvironment.BOTH)
            setJavaVersions(JavaVersion.VERSION_21, JavaVersion.VERSION_22)
            val softDepends = mutableListOf("oh-the-biomes-weve-gone")
            curseDepends.required.set(softDepends)
            modrinthDepends.required.set(softDepends)
        }
}

private fun getPublishingCredentials(): Pair<String?, String?> {
    val curseForgeToken = (project.findProperty("curseforge_token") ?: System.getenv("CURSEFORGE_TOKEN") ?: "") as String?
    val modrinthToken = (project.findProperty("modrinth_token") ?: System.getenv("MODRINTH_TOKEN") ?: "") as String?
    return Pair(curseForgeToken, modrinthToken)
}
