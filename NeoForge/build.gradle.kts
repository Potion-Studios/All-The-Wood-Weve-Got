import com.hypherionmc.modpublisher.properties.ModLoader

plugins {
    id("com.gradleup.shadow")
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

val minecraftVersion = providers.gradleProperty("minecraft_version").get()

configurations {
    val common = register("common")
    register("shadowCommon")
    compileClasspath.get().extendsFrom(common.get())
    runtimeClasspath.get().extendsFrom(common.get())
    named("developmentNeoForge") { extendsFrom(common.get()) }
}

loom {
    accessWidenerPath.set(project(":Common").loom.accessWidenerPath)

    runs.create("datagen") {
        data()
        programArguments.addAll(
            "--all", "--mod", "woodwevegot",
            "--output", project(":Common").file("src/main/generated/resources").absolutePath,
            "--existing", project(":Common").file("src/main/resources").absolutePath,
            "--existing-mod", "biomeswevegone"
        )
    }
}

dependencies {
    neoForge("net.neoforged:neoforge:${providers.gradleProperty("neoforge_version").get()}")

    "common"(project(":Common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":Common", "transformProductionNeoForge"))

    modLocalRuntime("me.djtheredstoner:DevAuth-neoforge:${providers.gradleProperty("devauth_version").get()}")

    modApi("net.potionstudios:Oh-The-Biomes-Weve-Gone-NeoForge:${providers.gradleProperty("bwg_version").get()}") { isTransitive = false }

    modRuntimeOnly("com.github.glitchfiend:TerraBlender-neoforge:$minecraftVersion-${providers.gradleProperty("terrablender_version").get()}")
    modRuntimeOnly("dev.corgitaco:Corgilib-NeoForge:$minecraftVersion-${providers.gradleProperty("corgilib_version").get()}")
    modRuntimeOnly("dev.corgitaco:Oh-The-Trees-Youll-Grow-neoforge:$minecraftVersion-${providers.gradleProperty("ohthetreesyoullgrow_version").get()}")
    modRuntimeOnly("software.bernie.geckolib:geckolib-neoforge-$minecraftVersion:${providers.gradleProperty("geckolib_version").get()}")

    modCompileOnly("mcp.mobius.waila:wthit-api:neo-${providers.gradleProperty("WTHIT").get()}")
    modLocalRuntime("mcp.mobius.waila:wthit:neo-${providers.gradleProperty("WTHIT").get()}")
    modLocalRuntime("lol.bai:badpackets:neo-${providers.gradleProperty("badPackets").get()}")

    modLocalRuntime("com.github.Jab125.architectury-data-generation-fix:architectury-data-generation-fix-neoforge:21.0.3")
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mapOf("version" to project.version))
        }
    }

    shadowJar {
        exclude("net/potionstudios/woodwevegot/neoforge/datagen/**", "architectury.common.json", ".cache/**")
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
        atAccessWideners.add("woodwevegot.accesswidener")
    }
}

publisher {
    setLoaders(ModLoader.NEOFORGE)
}
