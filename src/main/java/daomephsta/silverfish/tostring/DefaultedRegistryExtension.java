package daomephsta.silverfish.tostring;

import net.minecraft.util.Identifier;

public interface DefaultedRegistryExtension<T>
{
    public Identifier silverfish_getIdNullable(T value);
}
