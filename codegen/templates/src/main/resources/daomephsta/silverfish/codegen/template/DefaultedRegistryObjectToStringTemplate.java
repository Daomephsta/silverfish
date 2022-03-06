package /*!!package*/daomephsta.silverfish.codegen.template/*!!*/;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import daomephsta.silverfish.tostring.DefaultedRegistryExtension;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Mixin(/*!!target*/Object/*!!*/.class)
public class /*!!name*/DefaultedRegistryObjectToStringTemplate/*!!*/
{
    @SuppressWarnings("unchecked")
    @Override
    @Overwrite(remap = false)
    public String toString()
    {
        Identifier id = ((DefaultedRegistryExtension<Object>)
            Registry./*!!registry*/BLOCK/*!!*/).silverfish_getIdNullable(this);
        if (id != null)
            return getClass().getTypeName() + "{" + id + "}";
        return getClass().getTypeName() + "{not yet registered}";
    }
}