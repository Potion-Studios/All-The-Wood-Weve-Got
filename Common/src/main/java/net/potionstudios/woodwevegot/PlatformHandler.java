package net.potionstudios.woodwevegot;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;

import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.function.Supplier;

/**
 * This class handles the registration of all content
 * Also handles making custom objects that are needed for each platforms
 * @author Joseph T. McQuigg
 */
public interface PlatformHandler {

	PlatformHandler PLATFORM_HANDLER = load(PlatformHandler.class);

	/**
	 * Gets the name of the current platform
	 *
	 * @return The name of the current platform.
	 */
	Platform getPlatform();

	/**
	 * Gets the path to the config directory
	 * @return The path to the config directory
	 */
	Path configPath();

	private static <T> T load(Class<T> clazz) {
		final T loadedService = ServiceLoader.load(clazz)
				.findFirst()
				.orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
		WoodWeveGot.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
		return loadedService;
	}

	<T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value);

	<T> Supplier<Holder.Reference<T>> registerForHolder(Registry<T> registry, String name, Supplier<T> value);

	enum Platform {
		FORGE,
		FABRIC
	}
}
