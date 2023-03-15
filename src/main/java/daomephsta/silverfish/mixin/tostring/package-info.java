@RegistryObjectToString(target = Activity.class, registry = "ACTIVITY", overwrite = true)
@RegistryObjectToString(target = Biome.class, registry = "BIOME")
@RegistryObjectToString(target = Block.class, registry = "BLOCK", overwrite = true)
@RegistryObjectToString(target = BlockEntityType.class, registry = "BLOCK_ENTITY_TYPE")
@RegistryObjectToString(target = BlockStateProviderType.class, registry = "BLOCK_STATE_PROVIDER_TYPE")
@RegistryObjectToString(target = Carver.class, registry = "CARVER")
@RegistryObjectToString(target = ChunkStatus.class, registry = "CHUNK_STATUS", overwrite = true)
@RegistryObjectToString(target = DimensionOptions.class, registry = "DIMENSION")
@RegistryObjectToString(target = DimensionType.class, registry = "DIMENSION_TYPE")
@RegistryObjectToString(target = Enchantment.class, registry = "ENCHANTMENT")
@RegistryObjectToString(target = EntityAttribute.class, registry = "ATTRIBUTE")
@RegistryObjectToString(target = EntityType.class, registry = "ENTITY_TYPE", overwrite = true)
@RegistryObjectToString(target = Feature.class, registry = "FEATURE")
@RegistryObjectToString(target = FeatureSizeType.class, registry = "FEATURE_SIZE_TYPE")
@RegistryObjectToString(target = Fluid.class, registry = "FLUID")
@RegistryObjectToString(target = FoliagePlacerType.class, registry = "FOLIAGE_PLACER_TYPE")
@RegistryObjectToString(target = Item.class, registry = "ITEM", overwrite = true)
@RegistryObjectToString(target = LootConditionType.class, registry = "LOOT_CONDITION_TYPE")
@RegistryObjectToString(target = LootFunctionType.class, registry = "LOOT_FUNCTION_TYPE")
@RegistryObjectToString(target = LootNbtProviderType.class, registry = "LOOT_NBT_PROVIDER_TYPE")
@RegistryObjectToString(target = LootNumberProviderType.class, registry = "LOOT_NUMBER_PROVIDER_TYPE")
@RegistryObjectToString(target = LootPoolEntryType.class, registry = "LOOT_POOL_ENTRY_TYPE")
@RegistryObjectToString(target = LootScoreProviderType.class, registry = "LOOT_SCORE_PROVIDER_TYPE")
@RegistryObjectToString(target = MemoryModuleType.class, registry = "MEMORY_MODULE_TYPE", overwrite = true)
@RegistryObjectToString(target = PaintingVariant.class, registry = "PAINTING_VARIANT")
@RegistryObjectToString(target = ParticleType.class, registry = "PARTICLE_TYPE")
@RegistryObjectToString(target = PointOfInterestType.class, registry = "POINT_OF_INTEREST_TYPE", overwrite = true)
@RegistryObjectToString(target = Potion.class, registry = "POTION")
@RegistryObjectToString(target = Schedule.class, registry = "SCHEDULE")
@RegistryObjectToString(target = ScreenHandlerType.class, registry = "SCREEN_HANDLER")
@RegistryObjectToString(target = SensorType.class, registry = "SENSOR_TYPE")
@RegistryObjectToString(target = SoundEvent.class, registry = "SOUND_EVENT")
@RegistryObjectToString(target = StatType.class, registry = "STAT_TYPE")
@RegistryObjectToString(target = StatusEffect.class, registry = "STATUS_EFFECT")
@RegistryObjectToString(target = Structure.class, registry = "STRUCTURE")
@RegistryObjectToString(target = StructurePool.class, registry = "TEMPLATE_POOL")
@RegistryObjectToString(target = StructureProcessorList.class, registry = "PROCESSOR_LIST", overwrite = true)
@RegistryObjectToString(target = TreeDecoratorType.class, registry = "TREE_DECORATOR_TYPE")
@RegistryObjectToString(target = TrunkPlacerType.class, registry = "TRUNK_PLACER_TYPE")
@RegistryObjectToString(target = VillagerProfession.class, registry = "VILLAGER_PROFESSION", overwrite = true)
@RegistryObjectToString(target = VillagerType.class, registry = "VILLAGER_TYPE", overwrite = true)
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
import net.minecraft.entity.decoration.painting.PaintingVariant;
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
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.size.FeatureSizeType;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.minecraft.world.poi.PointOfInterestType;
