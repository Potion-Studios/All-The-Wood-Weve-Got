package net.potionstudios.woodwevegot.forge;

import com.google.auto.service.AutoService;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.potionstudios.woodwevegot.PlatformHandler;
import net.potionstudios.woodwevegot.WoodWeveGot;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Supplier;

@AutoService(PlatformHandler.class)
public class ForgePlatformHandler implements PlatformHandler {
	@Override
	public Platform getPlatform() {
		return Platform.FORGE;
	}

	@Override
	public Path configPath() {
		return FMLPaths.CONFIGDIR.get().resolve(WoodWeveGot.MOD_ID);
	}

	public static final Map<ResourceKey<?>, DeferredRegister> CACHED = new Reference2ObjectOpenHashMap<>();

	@Override
	public <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value) {
		return CACHED.computeIfAbsent(registry.key(), key -> DeferredRegister.create(registry.key().location(), WoodWeveGot.MOD_ID)).register(name, value);
	}

	@Override
	public <T> Supplier<Holder.Reference<T>> registerForHolder(Registry<T> registry, String name, Supplier<T> value) {
		RegistryObject<T> registryObject = CACHED.computeIfAbsent(registry.key(), key -> DeferredRegister.create(registry.key().location(), WoodWeveGot.MOD_ID)).register(name, value);
		return () -> (Holder.Reference<T>) registryObject.getHolder().get();
	}

	public static void register(IEventBus bus) {
		CACHED.values().forEach(deferredRegister -> deferredRegister.register(bus));
	}
}
