package daomephsta.silverfish.mixin.origin_tracing;

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

    @Inject(method = "<init>", at = @At("TAIL"))
    private void silverfish_traceOrigin(CallbackInfo info)
    {
        if (!SilverfishConfig.instance().originTracing.shouldTrace(this)) return;
        silverfish_origin = StackWalker.getInstance().walk(this::silverfish_originWalker);
    }

    private List<StackFrame> silverfish_originWalker(Stream<StackFrame> trace)
    {
        Stream<StackFrame> origin = trace
            .dropWhile(frame -> frame.getMethodName().equals("<init>") ||
                frame.getMethodName().contains("silverfish_traceOrigin"))
            .takeWhile(new Before<>(frame -> frame.getClassName()
                .equals("net.fabricmc.loader.impl.entrypoint.EntrypointUtils")));
        SilverfishConfig.instance().originTracing.depth.ifPresent(origin::limit);
        return origin.toList();
    }

    @Override
    public List<StackFrame> silverfish_getOrigin()
    {
        return silverfish_origin;
    }
}
