package daomephsta.silverfish.mixin.origin_tracing;

import java.lang.StackWalker.StackFrame;
import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import daomephsta.silverfish.Silverfish;
import daomephsta.silverfish.origin_tracing.OriginAware;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntry.Reference;
import net.minecraft.util.registry.SimpleRegistry;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> extends MutableRegistry<T>
{
    // Required to compile, ignored by Mixin
    private SimpleRegistryMixin()
    {
        super(null, null);
    }

    @Inject(method = "freeze",
        at = @At(value = "NEW", target = "java.lang.IllegalStateException", ordinal = 1),
        locals = LocalCapture.CAPTURE_FAILHARD)
    private void silverfish_improveIntrusiveHoldersError(CallbackInfoReturnable<Registry<T>> info,
        List<RegistryEntry.Reference<?>> notAdded)
    {
        for (Reference<?> entry : notAdded)
        {
            if (entry.value() instanceof OriginAware originAware && originAware.silverfish_getOrigin() != null)
            {
                var origin = new StringBuilder().append("Origin of ").append(originAware).append('\n');
                for (Iterator<StackFrame> iter = originAware.silverfish_getOrigin().iterator(); iter.hasNext();)
                {
                    StackFrame frame = iter.next();
                    origin.append("\tat ").append(frame);
                    if (iter.hasNext()) origin.append('\n');
                }
                Silverfish.LOGGER.info(origin.toString());
            }
            else
                Silverfish.LOGGER.info("No origin trace for " + entry.value());
        }
    }
}
