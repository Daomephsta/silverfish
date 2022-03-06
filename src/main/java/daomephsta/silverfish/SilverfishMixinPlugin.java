package daomephsta.silverfish;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class SilverfishMixinPlugin implements IMixinConfigPlugin
{
    @Override
    public void onLoad(String mixinPackage)
    {}

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
    {}

    @Override
    public List<String> getMixins()
    {
        var mixins = new ArrayList<String>();

        mixins.add("tostring.DefaultedRegistryMixin");
        mixins.add("tostring.BlockToString");
        mixins.add("tostring.ItemToString");

        if (SilverfishConfig.instance().originTracing.isEnabled())
        {
            mixins.add("origin_tracing.RegistryObjectOriginTracer");
            mixins.add("origin_tracing.SimpleRegistryMixin");
        }
        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {}
}
