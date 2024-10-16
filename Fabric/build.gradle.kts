import com.hypherionmc.modpublisher.properties.ModLoader

plugins {
    id("com.gradleup.shadow")
}

architectury {
    platformSetupLoomIde()
    fabric()
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
    getByName("developmentFabric").extendsFrom(configurations["common"])
    "shadowBundle" {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

loom.accessWidenerPath.set(project(":Common").loom.accessWidenerPath)

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")
    modApi("net.fabricmc.fabric-api:fabric-api:${project.properties["fabric_api_version"]}+$minecraftVersion")

    "common"(project(":Common", "namedElements")) { isTransitive = false }
    "shadowBundle"(project(":Common", "transformProductionFabric"))

    modLocalRuntime("me.djtheredstoner:DevAuth-fabric:${project.properties["devauth_version"]}")

    modApi("net.potionstudios:Oh-The-Biomes-Weve-Gone-Fabric:${project.properties["bwg_version"]}") { isTransitive = false }
    modRuntimeOnly("com.github.glitchfiend:TerraBlender-fabric:$minecraftVersion-${project.properties["terrablender_version"]}")
    modRuntimeOnly("corgitaco.corgilib:Corgilib-Fabric:$minecraftVersion-${project.properties["corgilib_version"]}")
    modRuntimeOnly("dev.corgitaco:Oh-The-Trees-Youll-Grow-fabric:$minecraftVersion-${project.properties["ohthetreesyoullgrow_version"]}")
    modRuntimeOnly("software.bernie.geckolib:geckolib-fabric-$minecraftVersion:${project.properties["geckolib_version"]}")

    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")
    modLocalRuntime("mcp.mobius.waila:wthit:fabric-${project.properties["WTHIT"]}")
    modLocalRuntime("lol.bai:badpackets:fabric-${project.properties["badPackets"]}")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("architectury.common.json", ".cache/**", "data/woodwevegot/neoforge/**")
        configurations = listOf(project.configurations.getByName("shadowBundle"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
    }
}

publisher {
    setLoaders(ModLoader.FABRIC, ModLoader.QUILT)
    curseDepends.required.set(mutableListOf("fabric-api"))
    modrinthDepends.required.set(mutableListOf("fabric-api"))
}
