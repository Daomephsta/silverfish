package daomephsta.silverfish.mixin.tostring;

import net.minecraft.registry.SimpleDefaultedRegistry;
import org.spongepowered.asm.mixin.Mixin;

import daomephsta.silverfish.tostring.DefaultedRegistryExtension;
import net.minecraft.util.Identifier;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.SimpleRegistry;

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
