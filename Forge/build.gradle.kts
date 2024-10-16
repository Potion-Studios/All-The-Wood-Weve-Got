import com.hypherionmc.modpublisher.properties.ModLoader

plugins {
    id("com.gradleup.shadow")
}

architectury {
    platformSetupLoomIde()
    forge()
}

val minecraftVersion = project.properties["minecraft_version"] as String

configurations {
    create("common")
    "common" {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    create("shadowBundle")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentForge").extendsFrom(configurations["common"])
    "shadowBundle" {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    configureEach {
        resolutionStrategy.force("net.sf.jopt-simple:jopt-simple:5.0.4")
    }
}

loom {
    accessWidenerPath.set(project(":Common").loom.accessWidenerPath)

    forge {
        convertAccessWideners.set(true)
        extraAccessWideners.add(loom.accessWidenerPath.get().asFile.name)
    }
}

dependencies {
    forge("net.minecraftforge:forge:$minecraftVersion-${project.properties["forge_version"]}")

    "common"(project(":Common", "namedElements")) { isTransitive = false }
    "shadowBundle"(project(":Common", "transformProductionForge"))

    modLocalRuntime("me.djtheredstoner:DevAuth-forge-latest:${project.properties["devauth_version"]}")

    modApi("net.potionstudios:Oh-The-Biomes-Weve-Gone-Forge:${project.properties["bwg_version"]}") { isTransitive = false }

    modRuntimeOnly("com.github.glitchfiend:TerraBlender-forge:$minecraftVersion-${project.properties["terrablender_version"]}")
    modRuntimeOnly("corgitaco.corgilib:Corgilib-Forge:$minecraftVersion-${project.properties["corgilib_version"]}")
    modRuntimeOnly("dev.corgitaco:Oh-The-Trees-Youll-Grow-forge:$minecraftVersion-${project.properties["ohthetreesyoullgrow_version"]}")
    modRuntimeOnly("software.bernie.geckolib:geckolib-forge-$minecraftVersion:${project.properties["geckolib_version"]}")

    modCompileOnly("mcp.mobius.waila:wthit-api:forge-${project.properties["WTHIT"]}")
    modLocalRuntime("mcp.mobius.waila:wthit:forge-${project.properties["WTHIT"]}")
    modLocalRuntime("lol.bai:badpackets:forge-${project.properties["badPackets"]}")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/mods.toml") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("architectury.common.json", ".cache/**", "data/woodwevegot/neoforge/**")
        configurations = listOf(project.configurations.getByName("shadowBundle"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
    }
}

publisher {
    setLoaders(ModLoader.FORGE)
}
