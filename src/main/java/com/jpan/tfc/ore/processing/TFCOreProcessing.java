package com.jpan.tfc.ore.processing;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import org.slf4j.Logger;

@Mod("tfcoreprocessing")
public class TFCOreProcessing {
   public static final String MODID = "tfcoreprocessing";
   private static final Logger LOGGER = LogUtils.getLogger();
   public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(Keys.FLUID_TYPES, "tfcoreprocessing");
   public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, "tfcoreprocessing");
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, "tfcoreprocessing");
   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, "tfcoreprocessing");
   public static final ItemsAndFluids items = new ItemsAndFluids();

   public TFCOreProcessing() {
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
      BLOCKS.register(modEventBus);
      ITEMS.register(modEventBus);
      FLUIDS.register(modEventBus);
      FLUID_TYPES.register(modEventBus);
      if (FMLEnvironment.dist == Dist.CLIENT)
      {
            ClientEventHandler.init();
      }
   }
}
