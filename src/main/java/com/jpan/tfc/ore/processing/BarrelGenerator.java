package com.jpan.tfc.ore.processing;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class BarrelGenerator {

   public static String barrel_recipe_path = "resources/data/tfcoreprocessing/recipes/metal/barrel/==0==_barrel.json";
   public static String barrel_recipe = """
      {
         "type": "minecraft:crafting_shaped",
         "pattern": [
           "X X",
           "X X",
           "XXX"
         ],
         "key": {
           "X": {
             "tag": "forge:sheets/==0=="
           }
         },
         "result": {
           "item": "tfcoreprocessing:metal/barrel/==0=="
         }
       }
       """;

   public static String barrel_loot_table_path = "resources/data/tfcoreprocessing/loot_tables/blocks/metal/barrel/==0==.json";
   public static String barrel_loot_table = """
      {
         "type": "minecraft:block",
         "pools": [
           {
             "name": "loot_pool",
             "rolls": 1,
             "entries": [
               {
                 "type": "minecraft:alternatives",
                 "children": [
                   {
                     "type": "minecraft:item",
                     "name": "tfcoreprocessing:metal/barrel/==0==",
                     "conditions": [
                       {
                         "condition": "minecraft:block_state_property",
                         "block": "tfcoreprocessing:metal/barrel/==0==",
                         "properties": {
                           "sealed": "true"
                         }
                       }
                     ],
                     "functions": [
                       {
                         "function": "minecraft:copy_name",
                         "source": "block_entity"
                       },
                       {
                         "function": "minecraft:copy_nbt",
                         "source": "block_entity",
                         "ops": [
                           {
                             "source": "",
                             "target": "BlockEntityTag",
                             "op": "replace"
                           }
                         ]
                       }
                     ]
                   },
                   {
                     "type": "minecraft:item",
                     "name": "tfcoreprocessing:metal/barrel/==0=="
                   }
                 ]
               }
             ],
             "conditions": [
               {
                 "condition": "minecraft:survives_explosion"
               }
             ]
           }
         ]
       }
           """;

      public static String handstone_recipe_path = "resources/data/tfcoreprocessing/recipes/==0==_handstone.json";
      public static String handstone_recipe = """
         {
            "type": "minecraft:crafting_shaped",
            "pattern": [
              "Y  ",
              "XXX"
            ],
            "key": {
              "X": {
                "tag": "forge:double_ingots/==0=="
              },
              "Y": {
                "tag": "forge:rods/wooden"
              }
            },
            "result": {
              "item": "tfcoreprocessing:==0==_handstone"
            }
          }
         """;

   public static String barrel_blockstate_path = "resources/assets/tfcoreprocessing/blockstates/metal/barrel/==0==.json";
   public static String barrel_blockstate = """
      {
         "variants": {
           "facing=up,rack=true,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0=="
           },
           "facing=up,rack=true,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0=="
           },
           "facing=up,rack=false,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0=="
           },
           "facing=up,rack=false,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0=="
           },
           "facing=east,rack=true,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side_rack"
           },
           "facing=east,rack=true,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side_rack"
           },
           "facing=east,rack=false,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side"
           },
           "facing=east,rack=false,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side"
           },
           "facing=west,rack=true,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side_rack",
             "y": 180
           },
           "facing=west,rack=true,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side_rack",
             "y": 180
           },
           "facing=west,rack=false,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side",
             "y": 180
           },
           "facing=west,rack=false,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side",
             "y": 180
           },
           "facing=south,rack=true,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side_rack",
             "y": 90
           },
           "facing=south,rack=true,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side_rack",
             "y": 90
           },
           "facing=south,rack=false,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side",
             "y": 90
           },
           "facing=south,rack=false,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side",
             "y": 90
           },
           "facing=north,rack=true,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side_rack",
             "y": 270
           },
           "facing=north,rack=true,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side_rack",
             "y": 270
           },
           "facing=north,rack=false,sealed=true": {
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0==_side",
             "y": 270
           },
           "facing=north,rack=false,sealed=false": {
             "model": "tfcoreprocessing:block/metal/barrel/==0==_side",
             "y": 270
           }
         }
       }
       """;

   public static String[] barrel_models_block_path = new String[]{
         "resources/assets/tfcoreprocessing/models/block/metal/barrel/==0==.json",
         "resources/assets/tfcoreprocessing/models/block/metal/barrel/==0==_side.json",
         "resources/assets/tfcoreprocessing/models/block/metal/barrel/==0==_side_rack.json",
         "resources/assets/tfcoreprocessing/models/block/metal/barrel_sealed/==0==.json",
         "resources/assets/tfcoreprocessing/models/block/metal/barrel_sealed/==0==_side.json",
         "resources/assets/tfcoreprocessing/models/block/metal/barrel_sealed/==0==_side_rack.json"
         };
   public static String[] barrel_models_block = new String[]{"""
      {
         "parent": "tfc:block/barrel",
         "textures": {
           "particle": "tfc:block/metal/block/==0==",
           "planks": "tfc:block/metal/block/==0==",
           "sheet": "tfc:block/metal/block/==0=="
         }
       }
       """,
       """
      {
         "parent": "tfc:block/barrel_side",
         "textures": {
           "particle": "tfc:block/metal/block/==0==",
           "planks": "tfc:block/metal/block/==0==",
           "sheet": "tfc:block/metal/block/==0=="
         }
       }
       """,
       """
      {
         "parent": "tfc:block/barrel_side_rack",
         "textures": {
           "particle": "tfc:block/metal/block/==0==",
           "planks": "tfc:block/metal/block/==0==",
           "sheet": "tfc:block/metal/block/==0=="
         }
       }
       """,
       """
       {
         "parent": "tfc:block/barrel_sealed",
         "textures": {
           "particle": "tfc:block/metal/block/==0==",
           "planks": "tfc:block/metal/block/==0==",
           "sheet": "tfc:block/metal/block/==0=="
         }
       }
       """,
       """
      {
         "parent": "tfc:block/barrel_side_sealed",
         "textures": {
           "particle": "tfc:block/metal/block/==0==",
           "planks": "tfc:block/metal/block/==0==",
           "sheet": "tfc:block/metal/block/==0=="
         }
       }
       """,
       """
      {
         "parent": "tfc:block/barrel_side_sealed_rack",
         "textures": {
           "particle": "tfc:block/metal/block/==0==",
           "planks": "tfc:block/metal/block/==0==",
           "sheet": "tfc:block/metal/block/==0=="
         }
       }
       """};

   public static String barrel_item_model_path = "resources/assets/tfcoreprocessing/models/item/metal/barrel/==0==.json";
   public static String barrel_item_model = """
      {
         "parent": "tfcoreprocessing:block/metal/barrel/==0==",
         "overrides": [
           {
             "predicate": {
               "tfc:sealed": 1.0
             },
             "model": "tfcoreprocessing:block/metal/barrel_sealed/==0=="
           }
         ]
       }
       """;
       public static String[] handstone_item_model_path = new String[]{"resources/assets/tfcoreprocessing/models/item/==0==_handstone.json"
       ,"resources/assets/tfcoreprocessing/models/item/==0==_handstone_healthy.json","resources/assets/tfcoreprocessing/models/item/==0==_handstone_damaged.json"};
       public static String[] handstone_item_model = new String[]{"""
         {
           
            "parent": "tfcoreprocessing:item/cast_iron_handstone_healthy",
            "overrides": [
              {
                "predicate": {
                  "tfc:damaged": 1.0
                },
                "model": "tfcoreprocessing:item/cast_iron_handstone_damaged"
              }
            ]
          }
          """,
          """
          {
               "parent": "tfc:item/handstone_healthy",
               "textures": {
                 "3": "tfcoreprocessing:block/devices/quern/==0==/handstone_bottom",
                   "side": "tfcoreprocessing:block/devices/quern/==0==/handstone_side",
                   "particle": "tfcoreprocessing:block/devices/quern/==0==/handstone_top",
                   "handstone": "tfcoreprocessing:block/devices/quern/==0==/handstone_top"
               }
             }   
         """,
          """
            {
               "parent": "tfc:item/handstone_healthy",
               "textures": {
                 "handstone": "tfc:block/devices/quern/==0==/handstone_top_damaged",
                 "particle": "tfc:block/devices/quern/==0==/handstone_top_damaged",
                 "side": "tfc:block/devices/quern/==0==/handstone_side_damaged"
               }
             }
         """
         };


   public static void main(String[] args) {
      if (args.length != 1) {
         System.out.println("Invalid path for generation");
      } else {
         File parent = new File(args[0]);
         if (parent.exists() && parent.isDirectory()) {
            String[] barrel_metals = new String[]{
               "bismuth",
               "bismuth_bronze",
               "black_bronze",
               "black_steel",
               "blue_steel",
               "brass",
               "bronze",
               "cast_iron",
               "copper",
               "gold",
               "nickel",
               "pig_iron",
               "red_steel",
               "rose_gold",
               "steel",
               "silver",
               "sterling_silver",
               "tin",
               "wrought_iron",
               "zinc"
            };

            String[] handstone_metals = new String[]{
               "black_steel",
               "blue_steel",
               "cast_iron",
               "red_steel",
               "steel",
               "wrought_iron"
            };

            for(String b : barrel_metals){
               File recipe = new File(parent, barrel_recipe_path.replaceAll("==0==", b));
               imprintFile(recipe, b, barrel_recipe);
               File loot = new File(parent,barrel_loot_table_path.replaceAll("==0==", b));
               imprintFile(loot, b, barrel_loot_table);
               File blockstate = new File(parent,barrel_blockstate_path.replaceAll("==0==", b));
               imprintFile(blockstate, b, barrel_blockstate);
               File item_model = new File(parent,barrel_item_model_path.replaceAll("==0==", b));
               imprintFile(item_model, b, barrel_item_model);
               ArrayList<File> models =  new ArrayList<File>();
               for (String f : barrel_models_block_path) {
                  models.add(new File(parent,f.replaceAll("==0==", b)));  
               }
               for (int i = 0; i<models.size(); i++) {
                  imprintFile(models.get(i), b, barrel_models_block[i]);
               }
            }

            for(String b : handstone_metals){
               File recipe = new File(parent, handstone_recipe_path.replaceAll("==0==", b));
               imprintFile(recipe, b, handstone_recipe);
               ArrayList<File> models =  new ArrayList<File>();
               for (String f : handstone_item_model_path) {
                  models.add(new File(parent,f.replaceAll("==0==", b)));  
               }
               for (int i = 0; i<models.size(); i++) {
                  imprintFile(models.get(i), b, handstone_item_model[i]);
               }
            }
         }
      }
   }

   private static void imprintFile(File toSave, String replacer, String textToWrite){
      try{
      Files.createDirectories(Path.of(toSave.getParent()));
      Files.writeString(Path.of(toSave.getAbsolutePath()), textToWrite.replaceAll("==0==", replacer));
      }catch(Exception ex){
         ex.printStackTrace();
      }
   }
}
