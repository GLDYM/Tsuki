package ecru.MapleTree.client;

import cpw.mods.fml.common.network.IGuiHandler;
import ecru.MapleTree.container.ecru_ContainerBiofuelPD;
import ecru.MapleTree.container.ecru_ContainerCauldron;
import ecru.MapleTree.container.ecru_ContainerCookPot;
import ecru.MapleTree.container.ecru_ContainerEngine;
import ecru.MapleTree.container.ecru_ContainerGatherItems;
import ecru.MapleTree.container.ecru_ContainerGrainDryer;
import ecru.MapleTree.container.ecru_ContainerGrainHopper;
import ecru.MapleTree.container.ecru_ContainerLighthouseIllumination;
import ecru.MapleTree.container.ecru_ContainerMortar;
import ecru.MapleTree.container.ecru_ContainerPlanter;
import ecru.MapleTree.container.ecru_ContainerSLight;
import ecru.MapleTree.container.ecru_ContainerSapling;
import ecru.MapleTree.container.ecru_ContainerStoneMortar;
import ecru.MapleTree.container.ecru_ContainerWineBarrel;
import ecru.MapleTree.entity.container.ecru_ContainerMomiji;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.entity.gui.ecru_GuiMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import ecru.MapleTree.tile.ecru_TileEntityCauldron;
import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import ecru.MapleTree.tile.ecru_TileEntityEngine;
import ecru.MapleTree.tile.ecru_TileEntityGatherItems;
import ecru.MapleTree.tile.ecru_TileEntityGrainDryer;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIllumination;
import ecru.MapleTree.tile.ecru_TileEntityMortar;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
import ecru.MapleTree.tile.ecru_TileEntitySLight;
import ecru.MapleTree.tile.ecru_TileEntityStoneMortar;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ecru_GuiHandler implements IGuiHandler {
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == mod_ecru_MapleTree.guiIdButtonId) {
            return new ecru_GuiConfig(player, world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdCauldron) {
            return new ecru_GuiCauldron(player, (ecru_TileEntityCauldron) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdPlanter) {
            return new ecru_GuiPlanter(player, (ecru_TileEntityPlanter) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdEngineId) {
            return new ecru_GuiEngune(player, (ecru_TileEntityEngine) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdStoneMortarId) {
            return new ecru_GuiStoneMortar(player, (ecru_TileEntityStoneMortar) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdLighthouseIllumination) {
            return new ecru_GuiLighthouseIllumination(player, (ecru_TileEntityLighthouseIllumination) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdCookPot) {
            return new ecru_GuiCookPot(player, (ecru_TileEntityCookPot) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdSLight) {
            return new ecru_GuiSLight(player, (ecru_TileEntitySLight) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdGrainHopper) {
            return new ecru_GuiGrainHopper(player, (ecru_TileEntityGrainHopper) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdBiofuelPD) {
            return new ecru_GuiBiofuelPD(player, (ecru_TileEntityBiofuelPD) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdWineBarrel) {
            return new ecru_GuiWineBarrel(player, (ecru_TileEntityWineBarrel) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdGatherItems) {
            return new ecru_GuiGatherItems(player, (ecru_TileEntityGatherItems) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdMortar) {
            return new ecru_GuiMortar(player, (ecru_TileEntityMortar) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdGrainDryer) {
            return new ecru_GuiGrainDryer(player, (ecru_TileEntityGrainDryer) world.func_147438_o(x, y, z), world, x, y, z);
        }
        ecru_EntityMomiji entitymomiji = player.field_70170_p.func_73045_a(x);
        if (ID == mod_ecru_MapleTree.guiId_momiji && entitymomiji != null) {
            return new ecru_GuiMomiji(player, entitymomiji);
        }
        return null;
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == mod_ecru_MapleTree.guiIdButtonId) {
            return new ecru_ContainerSapling(player, world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdCauldron) {
            return new ecru_ContainerCauldron(player, (ecru_TileEntityCauldron) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdPlanter) {
            return new ecru_ContainerPlanter(player, (ecru_TileEntityPlanter) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdEngineId) {
            return new ecru_ContainerEngine(player, (ecru_TileEntityEngine) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdStoneMortarId) {
            return new ecru_ContainerStoneMortar(player, (ecru_TileEntityStoneMortar) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdLighthouseIllumination) {
            return new ecru_ContainerLighthouseIllumination(player, (ecru_TileEntityLighthouseIllumination) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdCookPot) {
            return new ecru_ContainerCookPot(player, (ecru_TileEntityCookPot) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdSLight) {
            return new ecru_ContainerSLight(player, (ecru_TileEntitySLight) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdGrainHopper) {
            return new ecru_ContainerGrainHopper(player, (ecru_TileEntityGrainHopper) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdBiofuelPD) {
            return new ecru_ContainerBiofuelPD(player, (ecru_TileEntityBiofuelPD) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdWineBarrel) {
            return new ecru_ContainerWineBarrel(player, (ecru_TileEntityWineBarrel) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdGatherItems) {
            return new ecru_ContainerGatherItems(player, (ecru_TileEntityGatherItems) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdMortar) {
            return new ecru_ContainerMortar(player, (ecru_TileEntityMortar) world.func_147438_o(x, y, z), world, x, y, z);
        }
        if (ID == mod_ecru_MapleTree.guiIdGrainDryer) {
            return new ecru_ContainerGrainDryer(player, (ecru_TileEntityGrainDryer) world.func_147438_o(x, y, z), world, x, y, z);
        }
        ecru_EntityMomiji entitymomiji = player.field_70170_p.func_73045_a(x);
        if (ID == mod_ecru_MapleTree.guiId_momiji && entitymomiji != null) {
            return new ecru_ContainerMomiji(player, entitymomiji);
        }
        return null;
    }
}
