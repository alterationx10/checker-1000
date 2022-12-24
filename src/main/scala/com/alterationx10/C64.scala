package com.alterationx10

// Color values from Aseprite Commodore 64 Color Palette
enum C64(val hex: String) {
  case Black      extends C64("0x000000")
  case White      extends C64("0xffffff")
  case Red        extends C64("0x883932")
  case Cyan       extends C64("0x67b6bd")
  case Purple     extends C64("0x8b3f96")
  case Green      extends C64("0x55a049")
  case Blue       extends C64("0x40318d")
  case Yellow     extends C64("0xbfce72")
  case LightBrown extends C64("0x8b5429")
  case Brown      extends C64("0x574200")
  case Pink       extends C64("0xb86962")
  case GreyGreen  extends C64("0x505050")
  case Grey       extends C64("0x787878")
  case LightGreen extends C64("0x94e089")
  case Zyklam     extends C64("0x7869c4")
  case LightGray  extends C64("0x9f9f9f")
}
