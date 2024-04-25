package com.jpan.tfc.ore.processing.fluid;

import java.util.Locale;

public enum Slurries {
   BISMUTHINITE(-12031118),
   NATIVE_COPPER(-4833241),
   TETRAHEDRITE(-4833241),
   MALACHITE(-4833241),
   NATIVE_GOLD(-2310373),
   NATIVE_SILVER(-7039851),
   GARNIERITE(-11645380),
   CASSITERITE(-7297861),
   SPHALERITE(-4474428),
   HEMATITE(-6776681),
   LIMONITE(-6776681),
   MAGNETITE(-6776681);

   private final String id = "slurry_" + this.name().toLowerCase(Locale.ROOT);
   private final int color;

   private Slurries(int color) {
      this.color = color;
   }

   public String getId() {
      return this.id;
   }

   public int getColor() {
      return this.color;
   }

   public boolean isTransparent() {
      return false;
   }
}
