package daomephsta.silverfish.tostring;

import java.util.Optional;

import daomephsta.silverfish.Silverfish;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public abstract class Registries
{
    private Registries() {}

    @SuppressWarnings("unchecked")
    public static Optional<Identifier> getId(RegistryKey<? extends Registry<?>> key,
        Object value)
    {
        return Silverfish.proxy()
            .flatMap(proxy -> proxy.getRegistryManager())
            .flatMap(manager -> (Optional<Registry<Object>>) manager.getOptional(key))
            .or(() -> (Optional<Registry<Object>>) Registry.REGISTRIES.getOrEmpty(key.getValue()))
            .flatMap(registry -> getId(registry, value));
    }

    @SuppressWarnings("unchecked")
    private static Optional<Identifier> getId(Registry<?> registry, Object value)
    {
        if (registry instanceof DefaultedRegistryExtension<?>)
            return Optional.ofNullable(((DefaultedRegistryExtension<Object>) registry)
                .silverfish_getIdNullable(value));
        return Optional.ofNullable(((Registry<Object>) registry).getId(value));
    }
}
