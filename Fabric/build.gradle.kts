import com.hypherionmc.modpublisher.properties.ModLoader

plugins {
    id("com.gradleup.shadow")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val minecraftVersion = providers.gradleProperty("minecraft_version").get()

configurations {
    val common = register("common")
    register("shadowCommon")
    compileClasspath.get().extendsFrom(common.get())
    runtimeClasspath.get().extendsFrom(common.get())
    named("developmentFabric") { extendsFrom(common.get()) }
}

loom.accessWidenerPath.set(project(":Common").loom.accessWidenerPath)

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${providers.gradleProperty("fabric_loader_version").get()}")
    modApi("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabric_api_version").get()}+$minecraftVersion")

    "common"(project(":Common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":Common", "transformProductionFabric"))

    modLocalRuntime("me.djtheredstoner:DevAuth-fabric:${providers.gradleProperty("devauth_version").get()}")

    modApi("net.potionstudios:Oh-The-Biomes-Weve-Gone-Fabric:${providers.gradleProperty("bwg_version").get()}") { isTransitive = false }
    modRuntimeOnly("com.github.glitchfiend:TerraBlender-fabric:$minecraftVersion-${providers.gradleProperty("terrablender_version").get()}")
    modRuntimeOnly("dev.corgitaco:Corgilib-Fabric:$minecraftVersion-${providers.gradleProperty("corgilib_version").get()}")
    modRuntimeOnly("dev.corgitaco:Oh-The-Trees-Youll-Grow-fabric:$minecraftVersion-${providers.gradleProperty("ohthetreesyoullgrow_version").get()}")
    modRuntimeOnly("software.bernie.geckolib:geckolib-fabric-$minecraftVersion:${providers.gradleProperty("geckolib_version").get()}")

    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${providers.gradleProperty("WTHIT").get()}")
    modLocalRuntime("mcp.mobius.waila:wthit:fabric-${providers.gradleProperty("WTHIT").get()}")
    modLocalRuntime("lol.bai:badpackets:fabric-${providers.gradleProperty("badPackets").get()}")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("architectury.common.json", ".cache/**", "data/neoforge/**")
        configurations = listOf(project.configurations.getByName("shadowCommon"))
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
    curseDepends.required.set(mutableListOf(curseDepends.required.get()[0], "fabric-api"))
    modrinthDepends.required.set(mutableListOf(modrinthDepends.required.get()[0], "fabric-api"))
}
