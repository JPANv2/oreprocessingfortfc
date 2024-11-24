package com.jpan.tfc.ore.processing;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import org.slf4j.Logger;

@Mod("tfcoreprocessing")
public class TFCOreProcessing {
   public static final String MODID = "tfcoreprocessing";
   public static final Logger LOGGER = LogUtils.getLogger();
   public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(Keys.FLUID_TYPES, "tfcoreprocessing");
   public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, "tfcoreprocessing");
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, "tfcoreprocessing");
   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, "tfcoreprocessing");
   public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "tfcoreprocessing");
   public static final ItemsAndFluids items = new ItemsAndFluids();

   public TFCOreProcessing() {
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
      BLOCKS.register(modEventBus);
      ITEMS.register(modEventBus);
      FLUIDS.register(modEventBus);
      FLUID_TYPES.register(modEventBus);
      BLOCK_ENTITIES.register(modEventBus);
      if (FMLEnvironment.dist == Dist.CLIENT)
      {
            ClientEventHandler.init();
      }
   }
   
   static void setFinal(Field field, Object source, Object newValue) throws Exception {
	      field.setAccessible(true);

	      final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
	      unsafeField.setAccessible(true);
	      Unsafe u = (Unsafe) unsafeField.get(null);
	      
			long fieldOffset = u.objectFieldOffset(field);
			u.putObject(source, fieldOffset, newValue);	   
		}
	
}
