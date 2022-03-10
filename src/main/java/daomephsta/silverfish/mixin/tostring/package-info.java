@RegistryObjectToString(target = Activity.class, registry = "ACTIVITY_KEY", overwrite = true)
@RegistryObjectToString(target = Biome.class, registry = "BIOME_KEY")
@RegistryObjectToString(target = Block.class, registry = "BLOCK_KEY", overwrite = true)
@RegistryObjectToString(target = BlockEntityType.class, registry = "BLOCK_ENTITY_TYPE_KEY")
@RegistryObjectToString(target = BlockStateProviderType.class, registry = "BLOCK_STATE_PROVIDER_TYPE_KEY")
@RegistryObjectToString(target = Carver.class, registry = "CARVER_KEY")
@RegistryObjectToString(target = ChunkStatus.class, registry = "CHUNK_STATUS_KEY", overwrite = true)
@RegistryObjectToString(target = ConfiguredStructureFeature.class, registry = "CONFIGURED_STRUCTURE_FEATURE_KEY")
@RegistryObjectToString(target = DimensionOptions.class, registry = "DIMENSION_KEY")
@RegistryObjectToString(target = DimensionType.class, registry = "DIMENSION_TYPE_KEY")
@RegistryObjectToString(target = Enchantment.class, registry = "ENCHANTMENT_KEY")
@RegistryObjectToString(target = EntityAttribute.class, registry = "ATTRIBUTE_KEY")
@RegistryObjectToString(target = EntityType.class, registry = "ENTITY_TYPE_KEY", overwrite = true)
@RegistryObjectToString(target = Feature.class, registry = "FEATURE_KEY")
@RegistryObjectToString(target = FeatureSizeType.class, registry = "FEATURE_SIZE_TYPE_KEY")
@RegistryObjectToString(target = Fluid.class, registry = "FLUID_KEY")
@RegistryObjectToString(target = FoliagePlacerType.class, registry = "FOLIAGE_PLACER_TYPE_KEY")
@RegistryObjectToString(target = Item.class, registry = "ITEM_KEY", overwrite = true)
@RegistryObjectToString(target = LootConditionType.class, registry = "LOOT_CONDITION_TYPE_KEY")
@RegistryObjectToString(target = LootFunctionType.class, registry = "LOOT_FUNCTION_TYPE_KEY")
@RegistryObjectToString(target = LootNbtProviderType.class, registry = "LOOT_NBT_PROVIDER_TYPE_KEY")
@RegistryObjectToString(target = LootNumberProviderType.class, registry = "LOOT_NUMBER_PROVIDER_TYPE_KEY")
@RegistryObjectToString(target = LootPoolEntryType.class, registry = "LOOT_POOL_ENTRY_TYPE_KEY")
@RegistryObjectToString(target = LootScoreProviderType.class, registry = "LOOT_SCORE_PROVIDER_TYPE_KEY")
@RegistryObjectToString(target = MemoryModuleType.class, registry = "MEMORY_MODULE_TYPE_KEY", overwrite = true)
@RegistryObjectToString(target = NoiseParameters.class, registry = "NOISE_WORLDGEN", overwrite = true)
@RegistryObjectToString(target = PaintingMotive.class, registry = "MOTIVE_KEY")
@RegistryObjectToString(target = ParticleType.class, registry = "PARTICLE_TYPE_KEY")
@RegistryObjectToString(target = PointOfInterestType.class, registry = "POINT_OF_INTEREST_TYPE_KEY", overwrite = true)
@RegistryObjectToString(target = Potion.class, registry = "POTION_KEY")
@RegistryObjectToString(target = Schedule.class, registry = "SCHEDULE_KEY")
@RegistryObjectToString(target = ScreenHandlerType.class, registry = "MENU_KEY")
@RegistryObjectToString(target = SensorType.class, registry = "SENSOR_TYPE_KEY")
@RegistryObjectToString(target = SoundEvent.class, registry = "SOUND_EVENT_KEY")
@RegistryObjectToString(target = StatType.class, registry = "STAT_TYPE_KEY")
@RegistryObjectToString(target = StatusEffect.class, registry = "MOB_EFFECT_KEY")
@RegistryObjectToString(target = StructureFeature.class, registry = "STRUCTURE_FEATURE_KEY")
@RegistryObjectToString(target = StructurePool.class, registry = "STRUCTURE_POOL_KEY")
@RegistryObjectToString(target = StructureProcessorList.class, registry = "STRUCTURE_PROCESSOR_LIST_KEY", overwrite = true)
@RegistryObjectToString(target = TreeDecoratorType.class, registry = "TREE_DECORATOR_TYPE_KEY")
@RegistryObjectToString(target = TrunkPlacerType.class, registry = "TRUNK_PLACER_TYPE_KEY")
@RegistryObjectToString(target = VillagerProfession.class, registry = "VILLAGER_PROFESSION_KEY", overwrite = true)
@RegistryObjectToString(target = VillagerType.class, registry = "VILLAGER_TYPE_KEY", overwrite = true)
package daomephsta.silverfish.mixin.tostring;

import daomephsta.silverfish.codegen.RegistryObjectToString;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.entry.LootPoolEntryType;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.nbt.LootNbtProviderType;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.loot.provider.score.LootScoreProviderType;
import net.minecraft.particle.ParticleType;
import net.minecraft.potion.Potion;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatType;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler.NoiseParameters;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.size.FeatureSizeType;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.minecraft.world.poi.PointOfInterestType;
