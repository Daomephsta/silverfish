package /*!!package*/daomephsta.silverfish.codegen.template/*!!*/;

import org.spongepowered.asm.mixin.Mixin;

import daomephsta.silverfish.tostring.Registries;
import net.minecraft.util.Identifier;

@Mixin(/*!!target*/Object/*!!*/.class)
public class /*!!name*/RegistryObjectToStringTemplate/*!!*/
{
    /**
     * @author Daomephsta
     * @reason Improve informativity & consistency of registry object toString() implementations
     */
    /*!!annotations*/@Override/*!!*/
    public String toString()
    {
        String id = Registries.getId(net.minecraft.registry.RegistryKeys./*!!registry*/BLOCK/*!!*/, this)
            .map(Identifier::toString)
            .orElse("not yet registered, hashCode " + Integer.toHexString(hashCode()));
        return getClass().getTypeName() + "{" + id + "}";
    }
}