package daomephsta.silverfish.mixin.tostring;

import org.spongepowered.asm.mixin.Mixin;

import daomephsta.silverfish.tostring.DefaultedRegistryExtension;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.SimpleRegistry;

@Mixin(DefaultedRegistry.class)
public class DefaultedRegistryMixin<T> extends SimpleRegistry<T> implements DefaultedRegistryExtension<T>
{
    // Required for compilation, ignored by Mixin
    private DefaultedRegistryMixin()
    {
        super(null, null, null);
    }

    @Override
    public Identifier silverfish_getIdNullable(T value)
    {
        return super.getId(value);
    }
}
