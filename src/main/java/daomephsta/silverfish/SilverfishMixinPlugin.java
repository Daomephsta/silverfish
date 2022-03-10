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

        if (SilverfishConfig.instance().improveToStrings.enabled())
        {
            for (String type : List.of("Activity", "Biome", "BlockEntityType",
                "BlockStateProviderType", "Block", "Carver", "ChunkStatus",
                "ConfiguredStructureFeature", "DimensionOptions", "DimensionType", "Enchantment",
                "EntityAttribute", "EntityType", "FeatureSizeType", "Feature", "Fluid",
                "FoliagePlacerType", "Item", "LootConditionType", "LootFunctionType",
                "LootNbtProviderType", "LootNumberProviderType", "LootPoolEntryType",
                "LootScoreProviderType", "MemoryModuleType", "NoiseParameters", "PaintingMotive",
                "ParticleType", "PointOfInterestType", "Potion", "Schedule", "ScreenHandlerType",
                "SensorType", "SoundEvent", "StatType", "StatusEffect", "StructureFeature",
                "StructurePool", "StructureProcessorList", "TreeDecoratorType", "TrunkPlacerType",
                "VillagerProfession", "VillagerType"))
            {
                mixins.add("tostring." + type + "ToString");
            }
        }

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
