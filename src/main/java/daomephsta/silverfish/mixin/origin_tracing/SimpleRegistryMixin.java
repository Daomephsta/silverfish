package daomephsta.silverfish.mixin.origin_tracing;

import java.lang.StackWalker.StackFrame;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import daomephsta.silverfish.Silverfish;
import daomephsta.silverfish.SilverfishConfig;
import daomephsta.silverfish.origin_tracing.OriginAware;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry.Reference;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> implements MutableRegistry<T>
{

    @Shadow @Nullable private Map<T, Reference<T>> intrusiveValueToEntry;

    @Inject(method = "freeze",
        at = @At(value = "NEW", target = "java.lang.IllegalStateException", ordinal = 1),
        locals = LocalCapture.CAPTURE_FAILHARD)
    private void silverfish_improveIntrusiveHoldersError(CallbackInfoReturnable<Registry<T>> info)
    {
        Collection<Reference<T>> notAdded = this.intrusiveValueToEntry.values();
        if (!SilverfishConfig.instance().originTracing.isEnabled())
        {
            Silverfish.LOGGER.info("No origin traces for {} (originTracing.classes is empty)",
                    notAdded.stream().map(Reference::value).toList());
            return;
        }
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
            else if (!SilverfishConfig.instance().originTracing.shouldTrace(entry.value()))
            {
                Silverfish.LOGGER.info("No origin trace for {} ({} is not in originTracing.classes)",
                    entry.value(), entry.value().getClass().getName());
            }
            else
                Silverfish.LOGGER.info("No origin trace for {} (unknown reason)", entry.value());
        }
    }
}
