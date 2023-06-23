package com.ryorama.terrariamod;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "terrariamod")
public class TerrariaModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean customWorldGen = true;
    @ConfigEntry.Gui.Tooltip
    public boolean generateExtrasInVanilla = true;
    @ConfigEntry.Gui.Tooltip
    public boolean useVanillaHud = false;
    @ConfigEntry.Gui.Tooltip
    public boolean useVanillaFluidPhysics = false;
    @ConfigEntry.Gui.Tooltip
    public boolean modifyPlayerHealth = true;
    @ConfigEntry.Gui.Tooltip
    public boolean disableHunger = true;
}