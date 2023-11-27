package daomephsta.silverfish.mixin.tostring;

import org.spongepowered.asm.mixin.Mixin;

import daomephsta.silverfish.tostring.DefaultedRegistryExtension;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

@Mixin(SimpleDefaultedRegistry.class)
public class DefaultedRegistryMixin<T> extends SimpleRegistry<T> implements DefaultedRegistryExtension<T>
{
    // Required for compilation, ignored by Mixin
    private DefaultedRegistryMixin()
    {
        super(null, null, false);
    }

    @Override
    public Identifier silverfish_getIdNullable(T value)
    {
        return super.getId(value);
    }
}
