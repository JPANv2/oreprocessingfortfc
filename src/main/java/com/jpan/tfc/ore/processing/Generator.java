package com.jpan.tfc.ore.processing;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Generator {
   public static String blockstates_Fluid_path = "assets/tfcoreprocessing/blockstate/fluid/";
   public static String blockstates_fluids = "{\r\n  \"variants\": {\r\n    \"\": {\r\n      \"model\": \"tfcoreprocessing:block/fluid/=0=\"\r\n    }\r\n  }\r\n}";
   public static String block_Fluid_model_path = "assets/tfcoreprocessing/models/block/fluid/";
   public static String block_fluid_model = "{\r\n  \"textures\": {\r\n    \"particle\": \"minecraft:block/water_still\"\r\n  }\r\n}";
   public static String block_fluid_bucket_model_path = "assets/tfcoreprocessing/models/item/bucket/";
   public static String fluid_bucket_model = "{\r\n  \"loader\": \"forge:fluid_container\",\r\n  \"parent\": \"forge:item/bucket\",\r\n  \"fluid\": \"tfcoreprocessing:=0=\"\r\n}";
   public static String[] categories = new String[]{"refined", "compressed", "roasted", "roasted_brick"};
   public static String item_powder_model_path = "assets/tfcoreprocessing/models/item/=1=/";
   public static String item_powder_model = "{\r\n  \"parent\": \"item/generated\",\r\n  \"textures\": {\r\n    \"layer0\": \"tfcoreprocessing:item/=1=/=0=\"\r\n  }\r\n}";
   public static String tags = "data/tfcoreprocessing/tags/items/";
   public static String powders = "data/tfcoreprocessing/tags/items/=0=.json";
   public static String powders_JSON = "{\r\n  \"replace\": false,\r\n  \"values\": [\r\n  =1= \r\n  ]\r\n}";
   public static String item_sizes = "data/tfcoreprocessing/tfc/item_sizes/";
   public static String sizes_Brick = "{\r\n  \"ingredient\": {\r\n    \"tag\": \"tfcoreprocessing:=0=\"\r\n  },\r\n  \"size\": \"small\",\r\n  \"weight\": \"medium\"\r\n}";
   public static String sizes_powders = "{\r\n  \"ingredient\": {\r\n    \"tag\": \"tfcoreprocessing:=0=\"\r\n  },\r\n  \"size\": \"small\",\r\n  \"weight\": \"light\"\r\n}";
   public static String item_heating_recipe_path = "data/tfcoreprocessing/recipes/heating/=1=/";
   static String heating_other = "{\r\n  \"type\": \"tfc:heating\",\r\n  \"ingredient\": {\r\n    \"item\": \"tfcoreprocessing:=1=/=0=\"\r\n  },\r\n  \"result_fluid\": {\r\n    \"fluid\": \"tfc:metal/=2=\",\r\n    \"amount\": =3=\r\n  },\r\n  \"temperature\": =4=\r\n}";
   public static String item_heats_path = "data/tfcoreprocessing/tfc/item_heats/=1=/";
   static String temp = "{\r\n  \"ingredient\": {\r\n    \"item\": \"tfcoreprocessing:=1=/=0=\"\r\n  },\r\n  \"heat_capacity\": =2=,\r\n \"forging_temperature\": =3=,\r\n  \"welding_temperature\": =4= \r\n}";
   public static String barrel_path = "data/tfcoreprocessing/recipes/barrel/slurry/";
   public static String barrel_recipe = "{\r\n  \"type\": \"tfc:barrel_sealed\",\r\n  \"input_item\": {\r\n    \"ingredient\": {\r\n        \"item\": \"tfc:powder/=0=\"\r\n    }\r\n  },\r\n  \"input_fluid\": {\r\n    \"ingredient\": \"minecraft:water\",\r\n    \"amount\": 250\r\n  },\r\n  \"output_fluid\": {\r\n    \"fluid\": \"tfcoreprocessing:slurry_=0=\",\r\n    \"amount\": 250\r\n  },\r\n  \"duration\": 6000\r\n}";
   public static String pot_path = "data/tfcoreprocessing/recipes/pot/slurry/";
   public static String evaporate_recipe = "{\r\n  \"type\": \"tfc:pot\",\r\n\"ingredients\": [\r\n    {\r\n      \"item\": \"tfc:pan/empty\"\r\n    }\r\n  ],\r\n  \"fluid_ingredient\": {\r\n    \"ingredient\": \"tfcoreprocessing:slurry_=0=\",\r\n    \"amount\": 1000\r\n  },\r\n  \"duration\": 1300,\r\n  \"temperature\": 300,\r\n  \"item_output\": [\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n          \"item\": \"tfc:pan/empty\"\r\n    }\r\n  ]\r\n}";
   public static String roast_ingredient = "    {\r\n          \"item\": \"tfcoreprocessing:compressed/=0=\"\r\n    }\r\n";
   public static String roast_output = "    {\r\n      \"item\":\"tfcoreprocessing:roasted_brick/=0=\"\r\n    }\r\n";
   public static String roast_recipe = "{\r\n  \"type\": \"tfc:pot\",\r\n  \"ingredients\": [\r\n=1=  ],\r\n  \"fluid_ingredient\": {\r\n    \"ingredient\": \"minecraft:water\",\r\n    \"amount\": 100\r\n  },\r\n  \"duration\": 2000,\r\n  \"temperature\": 300,\r\n  \"item_output\": [\r\n=2=  ]\r\n}";
   public static String compress_path = "data/tfcoreprocessing/recipes/compress/";
   public static String compress_shapeless = "{\r\n  \"type\": \"minecraft:crafting_shapeless\",\r\n  \"ingredients\": [\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\"\r\n    }\r\n  ],\r\n  \"result\": {\r\n      \"item\":\"tfcoreprocessing:compressed/=0=\"\r\n  }\r\n}";
   public static String compress_roasted_shapeless = "{\r\n  \"type\": \"minecraft:crafting_shapeless\",\r\n  \"ingredients\": [\r\n    {\r\n      \"item\":\"tfcoreprocessing:roasted/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:roasted/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:roasted/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:roasted/=0=\"\r\n    },\r\n    {\r\n      \"item\":\"tfcoreprocessing:roasted/=0=\"\r\n    }\r\n  ],\r\n  \"result\": {\r\n      \"item\":\"tfcoreprocessing:roasted_brick/=0=\"\r\n  }\r\n}";
   public static String uncompress_path = "data/tfcoreprocessing/recipes/uncompress/";
   public static String uncompress_refined_shapeless = "{\r\n  \"type\": \"minecraft:crafting_shapeless\",\r\n  \"ingredients\": [\r\n    {\r\n      \"item\":\"tfcoreprocessing:compressed/=0=\"\r\n    }\r\n  ],\r\n  \"result\": {\r\n      \"item\":\"tfcoreprocessing:refined/=0=\",\r\n      \"count\":4\r\n  }\r\n}";
   public static String uncompress_roasted_shapeless = "{\r\n    \"type\": \"tfc:damage_inputs_shapeless_crafting\",\r\n    \"recipe\": {\r\n        \"type\": \"minecraft:crafting_shapeless\",\r\n        \"ingredients\": [{\r\n     \t \"item\":\"tfcoreprocessing:roasted_brick/=0=\"\r\n        }, {\r\n            \"tag\": \"tfc:hammers\"\r\n        }],\r\n        \"result\": {\r\n           \"item\":\"tfcoreprocessing:roasted/=0=\",\r\n           \"count\": 5 \r\n        }\r\n    }\r\n}";
   public static String fluid_tags_path = "data/tfc/tags/fluids/";
   public static String fluid_tags = "{\r\n  \"replace\": false,\r\n  \"values\": [\r\n    \"#tfc:slurries\"\r\n  ]\r\n}";
   public static String slurry_name = "\"tfcoreprocessing:slurry_=0=\",\r\n";
   public static String slurries_tags = "{\r\n  \"replace\": false,\r\n  \"values\": [\r\n   =1=  ]\r\n}";

   public static void main(String[] args) {
      if (args.length != 1) {
         System.out.println("Invalid path for generation");
      } else {
         File parent = new File(args[0]);
         if (parent.exists() && parent.isDirectory()) {
            Generator.OreDef[] defs = new Generator.OreDef[]{
               new Generator.OreDef("bismuthinite", "bismuth", "270", "2.857", "162", "216"),
               new Generator.OreDef("cassiterite", "tin", "230", "2.857", "138", "184"),
               new Generator.OreDef("garnierite", "nickel", "1453", "0.833", "872", "1162"),
               new Generator.OreDef("hematite", "cast_iron", "1535", "1.143", "921", "1228"),
               new Generator.OreDef("limonite", "cast_iron", "1535", "1.143", "921", "1228"),
               new Generator.OreDef("magnetite", "cast_iron", "1535", "1.143", "921", "1228"),
               new Generator.OreDef("malachite", "copper", "1080", "1.143", "648", "864"),
               new Generator.OreDef("native_copper", "copper", "1080", "1.143", "648", "864"),
               new Generator.OreDef("native_gold", "gold", "1060", "0.667", "636", "848"),
               new Generator.OreDef("native_silver", "silver", "961", "0.833", "577", "769"),
               new Generator.OreDef("sphalerite", "zinc", "420", "1.905", "252", "336"),
               new Generator.OreDef("tetrahedrite", "copper", "1080", "1.143", "648", "864")
            };
            File blockstates_fluid_parent = new File(parent, blockstates_Fluid_path);
            File block_fluid_parent = new File(parent, block_Fluid_model_path);
            File bucket_fluid_parent = new File(parent, block_fluid_bucket_model_path);
            File tags_parent = new File(parent, tags);
            File item_sizes_parent = new File(parent, item_sizes);
            File powder_heat_recipe_parent = new File(parent, item_heating_recipe_path.replace("=1=", "powder"));
            File powder_heating_parent = new File(parent, item_heats_path.replace("=1=", "powder"));
            File barrel_parent = new File(parent, barrel_path);
            File pot_parent = new File(parent, pot_path);
            File compress_parent = new File(parent, compress_path);
            File uncompress_parent = new File(parent, uncompress_path);
            File fluid_tag_parent = new File(parent, fluid_tags_path);

            try {
               Files.createDirectories(Path.of(blockstates_fluid_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(block_fluid_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(bucket_fluid_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(blockstates_fluid_parent.getAbsolutePath().replace("models/", "textures/")));
               Files.createDirectories(Path.of(block_fluid_parent.getAbsolutePath().replace("models/", "textures/")));
               Files.createDirectories(Path.of(bucket_fluid_parent.getAbsolutePath().replace("models/", "textures/")));
               Files.createDirectories(Path.of(tags_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(item_sizes_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(powder_heat_recipe_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(powder_heating_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(barrel_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(pot_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(compress_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(uncompress_parent.getAbsolutePath()));
               Files.createDirectories(Path.of(fluid_tag_parent.getAbsolutePath()));
               HashMap<String, File[]> solids = new HashMap<>();

               for (int i = 0; i < categories.length; i++) {
                  String s = categories[i];
                  File[] mapped = new File[]{
                     new File(tags_parent, s + ".json"),
                     new File(item_sizes_parent, s + ".json"),
                     new File(parent, item_powder_model_path.replace("=1=", s)),
                     new File(parent, item_powder_model_path.replace("=1=", s).replace("models/", "textures/")),
                     new File(parent, item_heating_recipe_path.replace("=1=", s)),
                     new File(parent, item_heats_path.replace("=1=", s))
                  };

                  for (int j = 2; j < mapped.length; j++) {
                     Files.createDirectories(Path.of(mapped[j].getAbsolutePath()));
                  }

                  String cumm_tags_cat = powders_JSON;

                  for (Generator.OreDef od : defs) {
                     cumm_tags_cat = cumm_tags_cat.replace("=1=", "\"tfcoreprocessing:" + s + "/" + od.oreName + "\",\r\n=1=");
                  }

                  cumm_tags_cat = cumm_tags_cat.replace(",\r\n=1=", "");
                  Files.writeString(Path.of(mapped[0].getAbsolutePath()), cumm_tags_cat);
                  if ((i & 1) == 0) {
                     Files.writeString(Path.of(mapped[1].getAbsolutePath()), sizes_powders.replace("=0=", s));
                  } else {
                     Files.writeString(Path.of(mapped[1].getAbsolutePath()), sizes_Brick.replace("=0=", s));
                  }

                  solids.put(s, mapped);
               }

               HashMap<String, String> categoryAmount = new HashMap<>();
               categoryAmount.put("refined", "8");
               categoryAmount.put("compressed", "40");
               categoryAmount.put("roasted", "10");
               categoryAmount.put("roasted_brick", "50");
               String slurries_tags_compiled = slurries_tags;

               for (Generator.OreDef s : defs) {
                  Files.writeString(
                     Path.of(blockstates_fluid_parent.getAbsolutePath() + "/slurry_" + s.oreName + ".json"),
                     blockstates_fluids.replaceAll("=0=", "slurry_" + s.oreName)
                  );
                  Files.writeString(
                     Path.of(bucket_fluid_parent.getAbsolutePath() + "/slurry_" + s.oreName + ".json"),
                     fluid_bucket_model.replaceAll("=0=", "slurry_" + s.oreName)
                  );
                  Files.writeString(Path.of(block_fluid_parent.getAbsolutePath() + "/slurry_" + s.oreName + ".json"), block_fluid_model);
                  Files.writeString(
                     Path.of(powder_heat_recipe_parent.getAbsolutePath() + "/" + s.oreName + ".json"),
                     heating_other.replace("tfcoreprocessing", "tfc")
                        .replaceAll("=0=", s.oreName)
                        .replaceAll("=1=", "powder")
                        .replaceAll("=2=", s.metalName)
                        .replaceAll("=3=", "6")
                        .replaceAll("=4=", s.temperature)
                  );
                  Files.writeString(
                     Path.of(powder_heating_parent.getAbsolutePath() + "/" + s.oreName + ".json"),
                     temp.replace("tfcoreprocessing", "tfc")
                        .replaceAll("=0=", s.oreName)
                        .replaceAll("=1=", "powder")
                        .replaceAll("=2=", s.heatCapacity)
                        .replaceAll("=3=", s.forgingTemperature)
                        .replaceAll("=4=", s.weldingTemperature)
                  );
                  slurries_tags_compiled = slurries_tags_compiled.replaceAll("=1=", slurry_name.replaceAll("=0=", s.oreName) + "=1=");

                  for (String c : categories) {
                     Files.writeString(
                        Path.of(solids.get(c)[2].getAbsolutePath() + "/" + s.oreName + ".json"),
                        item_powder_model.replaceAll("=0=", s.oreName).replaceAll("=1=", c)
                     );
                     Files.writeString(
                        Path.of(solids.get(c)[4].getAbsolutePath() + "/" + s.oreName + ".json"),
                        heating_other.replaceAll("=0=", s.oreName)
                           .replaceAll("=1=", c)
                           .replaceAll("=2=", s.metalName)
                           .replaceAll("=3=", categoryAmount.get(c))
                           .replaceAll("=4=", s.temperature)
                     );
                     Files.writeString(
                        Path.of(solids.get(c)[5].getAbsolutePath() + "/" + s.oreName + ".json"),
                        temp.replaceAll("=0=", s.oreName)
                           .replaceAll("=1=", c)
                           .replaceAll("=2=", s.heatCapacity)
                           .replaceAll("=3=", s.forgingTemperature)
                           .replaceAll("=4=", s.weldingTemperature)
                     );
                  }

                  Files.writeString(Path.of(barrel_parent.getAbsolutePath() + "/slurry_" + s.oreName + ".json"), barrel_recipe.replaceAll("=0=", s.oreName));
                  Files.writeString(Path.of(pot_parent.getAbsolutePath() + "/slurry_" + s.oreName + ".json"), evaporate_recipe.replaceAll("=0=", s.oreName));

                  for (int i = 0; i < 5; i++) {
                     String ingr = roast_ingredient.replaceAll("=0=", s.oreName);
                     String outp = roast_output.replaceAll("=0=", s.oreName);

                     for (int j = 0; j < i; j++) {
                        ingr = ingr + ",\r\n";
                        ingr = ingr + roast_ingredient.replaceAll("=0=", s.oreName);
                        outp = outp + ",\r\n";
                        outp = outp + roast_output.replaceAll("=0=", s.oreName);
                     }

                     Files.writeString(
                        Path.of(pot_parent.getAbsolutePath() + "/roast_" + (i + 1) + "_" + s.oreName + ".json"),
                        roast_recipe.replaceAll("=0=", s.oreName).replaceAll("=1=", ingr).replaceAll("=2=", outp)
                     );
                  }

                  Files.writeString(
                     Path.of(compress_parent.getAbsolutePath() + "/compress_refined_" + s.oreName + ".json"), compress_shapeless.replaceAll("=0=", s.oreName)
                  );
                  Files.writeString(
                     Path.of(compress_parent.getAbsolutePath() + "/compress_roasted_" + s.oreName + ".json"),
                     compress_roasted_shapeless.replaceAll("=0=", s.oreName)
                  );
                  Files.writeString(
                     Path.of(uncompress_parent.getAbsolutePath() + "/uncompress_refined_" + s.oreName + ".json"),
                     uncompress_refined_shapeless.replaceAll("=0=", s.oreName)
                  );
                  Files.writeString(
                     Path.of(uncompress_parent.getAbsolutePath() + "/uncompress_roasted_" + s.oreName + ".json"),
                     uncompress_roasted_shapeless.replaceAll("=0=", s.oreName)
                  );
               }

               Files.writeString(Path.of(fluid_tag_parent.getAbsolutePath() + "/ingredients.json"), fluid_tags);
               Files.writeString(Path.of(fluid_tag_parent.getAbsolutePath() + "/slurries.json"), slurries_tags_compiled.replaceAll(",\r\n=1=", ""));
            } catch (Exception var26) {
               var26.printStackTrace();
            }
         } else {
            System.out.println("Invalid directory for generation:" + (parent.exists() ? " not a diractory" : " does not exist"));
         }
      }
   }

   private static class OreDef {
      public String oreName;
      public String metalName;
      public String temperature;
      public String heatCapacity;
      public String forgingTemperature;
      public String weldingTemperature;

      public OreDef(String oreName, String metalName, String temperature, String heatCapacity, String forging, String welding) {
         this.oreName = oreName;
         this.metalName = metalName;
         this.temperature = temperature;
         this.heatCapacity = heatCapacity;
         this.forgingTemperature = forging;
         this.weldingTemperature = welding;
      }
   }
}
