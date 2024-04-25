package com.jpan.tfc.ore.processing;

import com.jpan.tfc.ore.processing.fluid.Slurries;
import com.jpan.tfc.ore.processing.mixin.BlockEntityTypeAccessor;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blockentities.BarrelBlockEntity;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.devices.BarrelBlock;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.fluids.ExtendedFluidType;
import net.dries007.tfc.common.fluids.FluidRegistryObject;
import net.dries007.tfc.common.fluids.FluidTypeClientProperties;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.fluids.MixingFluid.Flowing;
import net.dries007.tfc.common.fluids.MixingFluid.Source;
import net.dries007.tfc.common.items.BarrelBlockItem;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(
   modid = "tfcoreprocessing",
   bus = Bus.MOD
)
public class ItemsAndFluids {
   public static final Map<Slurries, RegistryObject<LiquidBlock>> SLURRY_FLUIDS_BLOCKS = Helpers.mapOfKeys(Slurries.class, fluid -> registerSlurryBlock(fluid));
   public static final Map<Slurries, RegistryObject<BucketItem>> SLURRY_FLUIDS_BUCKETS = Helpers.mapOfKeys(Slurries.class, fluid -> registerSlurryBucket(fluid));
   public static final Map<Slurries, FluidRegistryObject<ForgeFlowingFluid>> SLURRY_FLUIDS = Helpers.mapOfKeys(
      Slurries.class,
      fluid -> registerFluid(
            fluid.getId().toLowerCase(Locale.ROOT),
            properties -> properties.block((Supplier)SLURRY_FLUIDS_BLOCKS.get(fluid)).bucket((Supplier)SLURRY_FLUIDS_BUCKETS.get(fluid)),
            waterLike().descriptionId("fluid.tfc.slurry." + fluid.getId()).canConvertToSource(false),
            new FluidTypeClientProperties(fluid.getColor(), TFCFluids.WATER_STILL, TFCFluids.WATER_FLOW, TFCFluids.WATER_OVERLAY, TFCFluids.UNDERWATER_LOCATION),
            Source::new,
            Flowing::new
         )
   );
   public static final Map<Ore, RegistryObject<Item>> REFINED_ORE_POWDERS = Helpers.mapOfKeys(
      Ore.class, Ore::isGraded, ore -> registerSimpleItem("refined/" + ore.name())
   );
   public static final Map<Ore, RegistryObject<Item>> COMPRESSED_ORE_POWDERS = Helpers.mapOfKeys(
      Ore.class, Ore::isGraded, ore -> registerSimpleItem("compressed/" + ore.name())
   );
   public static final Map<Ore, RegistryObject<Item>> ROASTED_ORE_BRICKS = Helpers.mapOfKeys(
      Ore.class, Ore::isGraded, ore -> registerSimpleItem("roasted_brick/" + ore.name())
   );
   public static final Map<Ore, RegistryObject<Item>> ROASTED_ORE_POWDERS = Helpers.mapOfKeys(
      Ore.class, Ore::isGraded, ore -> registerSimpleItem("roasted/" + ore.name())
   );
   public static final RegistryObject<Item> SAWDUST = registerSimpleItem("kindle/sawdust");
   public static final RegistryObject<Item> BAMBOO_FIBERS = registerSimpleItem("kindle/bamboo_fiber");
   public static final RegistryObject<Item> PALM_FIBERS = registerSimpleItem("kindle/palm_fiber");
   public static final RegistryObject<Item> WOOD_PELLET = registerSimpleItem("kindle/wood_pellets");
   public static final RegistryObject<Item> PALM_FIBER_PELLET = registerSimpleItem("kindle/palm_fiber_pellets");
   public static final RegistryObject<Item> BAMBOO_PELLET = registerSimpleItem("kindle/bamboo_pellets");
   public static final RegistryObject<Item> FIRELOG = registerSimpleItem("kindle/firelog");
   public static final RegistryObject<Item> PALM_FIRELOG = registerSimpleItem("kindle/palm_fiber_firelog");
   public static final RegistryObject<Item> BAMBOO_FIRELOG = registerSimpleItem("kindle/bamboo_firelog");
  
   public static final RegistryObject<Item> CHARCOAL_PELLET = registerSimpleItem("kindle/charcoal_pellets");
   public static final RegistryObject<Item> GRAPHITE_PELLET = registerSimpleItem("kindle/graphite_pellets");
   public static final RegistryObject<Item> COAL_DUST = registerSimpleItem("kindle/coal_dust");
   public static final RegistryObject<Item> COAL_PELLET = registerSimpleItem("kindle/coal_pellets");
   public static final RegistryObject<Item> COKE_DUST = registerSimpleItem("kindle/coke_dust");
   public static final RegistryObject<Item> COKE_PELLET = registerSimpleItem("kindle/coke_pellets");
   public static final RegistryObject<Item> COKE_BRIQUETTE = registerSimpleItem("kindle/coke_briquette");
   public static final RegistryObject<Item> COKE_CHARCOAL_BRIQUETTE = registerSimpleItem("kindle/coke_charcoal_briquette");
   public static final RegistryObject<Item> COAL_BRIQUETTE = registerSimpleItem("kindle/coal_briquette");
   public static final RegistryObject<Item> CHARCOAL_BRIQUETTE = registerSimpleItem("kindle/charcoal_briquette");
   public static final RegistryObject<Item> CHARCOAL_GRAPHITE_BRIQUETTE = registerSimpleItem("kindle/charcoal_graphite_briquette");
   public static final RegistryObject<Item> CHARCOAL_WOOD_BRIQUETTE = registerSimpleItem("kindle/charcoal_wood_briquette");
   public static final RegistryObject<Item> LOW_CHARCOAL_BRIQUETTE = registerSimpleItem("kindle/low_charcoal_briquette");

  // public static final RegistryObject<Item> HANDSTONE_CAST_IRON = registerDurableItem("cast_iron_handstone",2500);
   /*public static final RegistryObject<Item> HANDSTONE_STEEL = registerDurableItem("steel_handstone",5000);
   public static final RegistryObject<Item> HANDSTONE_RED_STEEL = registerSimpleItem("red_steel_handstone");
   public static final RegistryObject<Item> HANDSTONE_BLUE_STEEL = registerSimpleItem("blue_steel_handstone");*/

   public static final Map<BarrelMetals, RegistryObject<BarrelBlock>> BARRELS = Helpers.mapOfKeys(BarrelMetals.class, barrel -> registerBarrel("metal/barrel/" + barrel.name())); 

   private static enum BarrelMetals{
      bismuth,
      bismuth_bronze,
      black_bronze,
      black_steel,
      blue_steel,
      brass,
      bronze,
      cast_iron,
      copper,
      gold,
      nickel,
      pig_iron,
      red_steel,
      rose_gold,
      steel,
      silver,
      sterling_silver,
      tin,
      wrought_iron,
      zinc
   };

   public static final Map<HandstoneMetals, RegistryObject<Item>> HANDSTONES = Helpers.mapOfKeys(HandstoneMetals.class, 
         handstone -> ((HandstoneMetals)handstone).getDurability() > 0 ? registerDurableItem(handstone.name() + "_handstone", handstone.getDurability()):registerSimpleItem(handstone.name() + "_handstone")); 

   private static enum HandstoneMetals{
      black_steel(4000),
      blue_steel(-1),
      cast_iron(2500),
      red_steel(-1),
      steel(3250),
      wrought_iron(2500);

      int durability;

      HandstoneMetals(int durability){
         this.durability = durability;
      }

      public int getDurability(){return durability;}
   };


   //public static final RegistryObject<BarrelBlock> BARREL_STEEL = registerBarrel("metal/barrel/steel");
   


   @SubscribeEvent
   public static void buildTab(BuildCreativeModeTabContentsEvent event) {
      if (event.getTab().equals(TFCCreativeTabs.ORES.tab().get())) {
         for (RegistryObject<Item> e : REFINED_ORE_POWDERS.values()) {
            event.accept(e);
         }

         for (RegistryObject<BucketItem> e : SLURRY_FLUIDS_BUCKETS.values()) {
            event.accept(e);
         }

         for (RegistryObject<Item> e : COMPRESSED_ORE_POWDERS.values()) {
            event.accept(e);
         }

         for (RegistryObject<Item> e : ROASTED_ORE_POWDERS.values()) {
            event.accept(e);
         }

         for (RegistryObject<Item> e : ROASTED_ORE_BRICKS.values()) {
            event.accept(e);
         }
      }

      if (event.getTab().equals(TFCCreativeTabs.WOOD.tab().get())) {
         event.accept(SAWDUST);
         event.accept(WOOD_PELLET);
         event.accept(FIRELOG);
         event.accept(BAMBOO_FIBERS);
         event.accept(BAMBOO_PELLET);
         event.accept(BAMBOO_FIRELOG);
         event.accept(PALM_FIBERS);
         event.accept(PALM_FIBER_PELLET);
         event.accept(PALM_FIRELOG);
         event.accept(CHARCOAL_PELLET);
         event.accept(CHARCOAL_BRIQUETTE);
         event.accept(CHARCOAL_GRAPHITE_BRIQUETTE);
         event.accept(CHARCOAL_WOOD_BRIQUETTE);
         event.accept(LOW_CHARCOAL_BRIQUETTE);
         event.accept(COAL_DUST);
         event.accept(COAL_PELLET);
         event.accept(COAL_BRIQUETTE);
         event.accept(COKE_DUST);
         event.accept(COKE_PELLET);
         event.accept(COKE_BRIQUETTE);
         event.accept(COKE_CHARCOAL_BRIQUETTE);

      }
      if(event.getTab().equals(TFCCreativeTabs.METAL.tab().get())){
         for(RegistryObject<Item> e : HANDSTONES.values()){
            event.accept(e);
         }
         for(RegistryObject<BarrelBlock> e : BARRELS.values()){
            event.accept(e);
         }
      }
   }

   private static <F extends FlowingFluid> FluidRegistryObject<F> registerFluid(
      String name,
      Consumer<Properties> builder,
      net.minecraftforge.fluids.FluidType.Properties typeProperties,
      FluidTypeClientProperties clientProperties,
      Function<Properties, F> sourceFactory,
      Function<Properties, F> flowingFactory
   ) {
      int index = name.lastIndexOf(47);
      String flowingName = index == -1 ? "flowing_" + name : name.substring(0, index) + "/flowing_" + name.substring(index + 1);
      return RegistrationHelpers.registerFluid(
         TFCOreProcessing.FLUID_TYPES,
         TFCOreProcessing.FLUIDS,
         name,
         name,
         flowingName,
         builder,
         () -> new ExtendedFluidType(typeProperties, clientProperties),
         sourceFactory,
         flowingFactory
      );
   }

   private static RegistryObject<LiquidBlock> registerSlurryBlock(Slurries fluid) {
      return RegistrationHelpers.registerBlock(
         TFCOreProcessing.BLOCKS,
         TFCOreProcessing.ITEMS,
         ("fluid/" + fluid.getId()).toLowerCase(Locale.ROOT),
         () -> new LiquidBlock(
               SLURRY_FLUIDS.get(fluid).source(), net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()
            ),
         (Function)null
      );
   }

   private static RegistryObject<BucketItem> registerSlurryBucket(Slurries fluid) {
      return TFCOreProcessing.ITEMS
         .register(
            ("bucket/" + fluid.getId()).toLowerCase(Locale.ROOT),
            () -> new BucketItem(SLURRY_FLUIDS.get(fluid).source(), new net.minecraft.world.item.Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
         );
   }

   private static RegistryObject<Item> registerSimpleItem(String name) {
      return TFCOreProcessing.ITEMS.register(name.toLowerCase(Locale.ROOT), () -> new Item(new net.minecraft.world.item.Item.Properties()));
   }

   private static RegistryObject<Item> registerDurableItem(String name, int durability) {
      return TFCOreProcessing.ITEMS.register(name.toLowerCase(Locale.ROOT), () -> new Item(new net.minecraft.world.item.Item.Properties().defaultDurability(durability)));
   }

   private static RegistryObject<BarrelBlock> registerBarrel(String registryName) {
      return RegistrationHelpers.<BarrelBlock>registerBlock(
         TFCOreProcessing.BLOCKS,
         TFCOreProcessing.ITEMS,
         (registryName).toLowerCase(Locale.ROOT),
         () -> new BarrelBlock(ExtendedProperties.of(Metal.Default.STEEL.mapColor()).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                                                           .strength(2.0F, 3.0F).sound(SoundType.METAL).noOcclusion().blockEntity(TFCBlockEntities.BARREL).serverTicks(BarrelBlockEntity::serverTick))
         , (block) -> new BarrelBlockItem(block,  new net.minecraft.world.item.Item.Properties())
      );
   }

   //

   private static net.minecraftforge.fluids.FluidType.Properties waterLike() {
      return net.minecraftforge.fluids.FluidType.Properties.create()
         .adjacentPathType(BlockPathTypes.WATER)
         .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
         .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
         .canConvertToSource(true)
         .canDrown(true)
         .canExtinguish(true)
         .canHydrate(true)
         .canPushEntity(true)
         .canSwim(true)
         .supportsBoating(true);
   }

   
}
