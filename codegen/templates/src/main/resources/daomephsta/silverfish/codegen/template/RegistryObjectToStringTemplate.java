package /*!!package*/daomephsta.silverfish.codegen.template/*!!*/;

import org.spongepowered.asm.mixin.Mixin;

import daomephsta.silverfish.tostring.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
        String id = Registries.getId(Registry./*!!registry*/BLOCK_KEY/*!!*/, this)
            .map(Identifier::toString)
            .orElse("not yet registered");
        return getClass().getTypeName() + "{" + id + "}";
    }
}