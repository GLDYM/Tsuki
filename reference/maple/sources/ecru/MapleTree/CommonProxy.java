package ecru.MapleTree;

import cpw.mods.fml.common.registry.GameRegistry;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import ecru.MapleTree.tile.ecru_TileEntityEngine;
import ecru.MapleTree.tile.ecru_TileEntityFountain;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
import ecru.MapleTree.tile.ecru_TileEntityGrapeTub;
import ecru.MapleTree.tile.ecru_TileEntityHumanPowerDrive;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIllumination;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaft;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaftGear;
import ecru.MapleTree.tile.ecru_TileEntitySLight;
import ecru.MapleTree.tile.ecru_TileEntitySprinkler;
import ecru.MapleTree.tile.ecru_TileEntityStoneMortar;
import ecru.MapleTree.tile.ecru_TileEntitySunDrying;
import ecru.MapleTree.tile.ecru_TileEntitySunFlower;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import net.minecraft.world.World;

public class CommonProxy {
    public void init() {
    }

    public World getClientWorld() {
        return null;
    }

    public void registerTextures() {
    }

    public void registerComponents() {
        GameRegistry.registerTileEntity(ecru_TileEntityEngine.class, "ecru_TileEntityEngine");
        GameRegistry.registerTileEntity(ecru_TileEntityStoneMortar.class, "ecru_TileEntityStoneMortar");
        GameRegistry.registerTileEntity(ecru_TileEntitySunFlower.class, "ecru_TileEntitySunFlower");
        GameRegistry.registerTileEntity(ecru_TileEntityLighthouseIllumination.class, "ecru_TileEntityLighthouseIllumination");
        GameRegistry.registerTileEntity(ecru_TileEntitySprinkler.class, "ecru_TileEntitySprinkler");
        GameRegistry.registerTileEntity(ecru_TileEntityFountain.class, "ecru_TileEntityFountain");
        GameRegistry.registerTileEntity(ecru_TileEntityCookPot.class, "ecru_TileEntityCookPot");
        GameRegistry.registerTileEntity(ecru_TileEntitySLight.class, "ecru_TileEntitySLight");
        GameRegistry.registerTileEntity(ecru_TileEntityBiofuelPD.class, "ecru_TileEntityBiofuelPD");
        GameRegistry.registerTileEntity(ecru_TileEntityGrainHopper.class, "ecru_TileEntityGrainHopper");
        GameRegistry.registerTileEntity(ecru_TileEntityHumanPowerDrive.class, "ecru_TileEntityHumanPowerDrive");
        GameRegistry.registerTileEntity(ecru_TileEntityPowerShaftGear.class, "ecru_TileEntityPowerShaftGear");
        GameRegistry.registerTileEntity(ecru_TileEntityPowerShaft.class, "ecru_TileEntityPowerShaft");
        GameRegistry.registerTileEntity(ecru_TileEntityGrapeTub.class, "ecru_TileEntityGrapeTab");
        GameRegistry.registerTileEntity(ecru_TileEntityWineBarrel.class, "ecru_TileEntityWineBarrel");
        GameRegistry.registerTileEntity(ecru_TileEntitySunDrying.class, "ecru_TileEntitySunDrying");
    }

    public void initRenderingAndTextures() {
    }

    public int getNewRenderType() {
        return -1;
    }

    public void registerRenderers() {
    }

    public void registerRenderInformation() {
    }
}
