architectury {
    common("forge", "fabric", "neoforge")
    platformSetupLoomIde()
}

loom.accessWidenerPath.set(file("src/main/resources/woodwevegot.accesswidener"))

sourceSets.main.get().resources.srcDir("src/main/generated/resources")

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${providers.gradleProperty("fabric_loader_version").get()}")

    modImplementation("net.potionstudios:Oh-The-Biomes-Weve-Gone-Common:${providers.gradleProperty("bwg_version").get()}")
}
