package net.potionstudios.woodwevegot.fabric;

import com.google.auto.service.AutoService;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;

import java.nio.file.Path;
import java.util.function.Supplier;

@AutoService(PlatformHandler.class)
public class FabricPlatformHandler implements PlatformHandler {
	@Override
	public Platform getPlatform() {
		return Platform.FABRIC;
	}

	@Override
	public Path configPath() {
		return FabricLoader.getInstance().getConfigDir().resolve(WoodWeveGot.MOD_ID);
	}

	@Override
	public <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value) {
		T value1 = Registry.register(registry, WoodWeveGot.id(name), value.get());
		return () -> value1;
	}

	@Override
	public <T> Supplier<Holder.Reference<T>> registerForHolder(Registry<T> registry, String name, Supplier<T> value) {
		Holder.Reference<T> reference = Registry.registerForHolder(registry, WoodWeveGot.id(name), value.get());
		return () -> reference;
	}
}
