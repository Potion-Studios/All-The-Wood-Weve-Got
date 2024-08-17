package net.potionstudios.woodwevegot.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.potionstudios.woodwevegot.client.WoodWeveGotClient;

@Environment(EnvType.CLIENT)
public class WoodWeveGotClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WoodWeveGotClient.onInitialize(Minecraft.getInstance());
    }
}
