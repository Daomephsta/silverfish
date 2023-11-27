package daomephsta.silverfish.mixin.origin_tracing;

import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.util.List;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import daomephsta.silverfish.SilverfishConfig;
import daomephsta.silverfish.origin_tracing.Before;
import daomephsta.silverfish.origin_tracing.OriginAware;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.world.event.GameEvent;

@Mixin({Block.class, EntityType.class, Fluid.class, GameEvent.class, Item.class})
public class RegistryObjectOriginTracer implements OriginAware
{
    @Unique
    private @Final List<StackFrame> silverfish_origin;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void silverfish$traceOrigin(CallbackInfo info)
    {
        if (!SilverfishConfig.instance().originTracing.shouldTrace(this)) return;
        silverfish_origin = StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE)
            .walk(this::silverfish_originWalker);
    }

    private List<StackFrame> silverfish_originWalker(Stream<StackFrame> trace)
    {
        Stream<StackFrame> origin = trace
            // Trim the head until it's the frame calling the constructor
            .dropWhile(this::silverfish_trimHead)
            // Trim the tail until it's the most recent mod entrypoint
            .takeWhile(new Before<>(frame -> frame.getClassName()
                .contains("net.fabricmc.loader.impl")));
        SilverfishConfig.instance().originTracing.depth.ifPresent(origin::limit);
        return origin.toList();
    }

    private boolean silverfish_trimHead(StackFrame frame)
    {
        return frame.getMethodName().contains("silverfish$traceOrigin") ||
            frame.getMethodName().equals("<init>") &&
            // Check the constructor is declared by the target or a supertype
            frame.getDeclaringClass().isAssignableFrom(getClass());
    }

    @Override
    public List<StackFrame> silverfish_getOrigin()
    {
        return silverfish_origin;
    }
}
