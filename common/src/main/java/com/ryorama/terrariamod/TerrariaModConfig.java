package com.ryorama.terrariamod;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "terrariamod")
public class TerrariaModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean customWorldGen = false;
    @ConfigEntry.Gui.Tooltip
    public boolean generateExtrasInVanilla = false;
    @ConfigEntry.Gui.Tooltip
    public boolean useVanillaHud = true;
    @ConfigEntry.Gui.Tooltip
    public boolean useVanillaFluidPhysics = true;
    @ConfigEntry.Gui.Tooltip
    public boolean modifyPlayerHealth = false;
    @ConfigEntry.Gui.Tooltip
    public boolean disableHunger = false;
}