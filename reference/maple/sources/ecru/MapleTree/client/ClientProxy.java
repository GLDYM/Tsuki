package ecru.MapleTree.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.CommonProxy;
import ecru.MapleTree.common.ecru_Particles;
import ecru.MapleTree.entity.ecru_EntityGacha;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.entity.render.ecru_RenderMomiji;
import ecru.MapleTree.help.ecru_EntityDummyPlayer;
import ecru.MapleTree.help.ecru_helpRender;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPDRender;
import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import ecru.MapleTree.tile.ecru_TileEntityCookPotRender;
import ecru.MapleTree.tile.ecru_TileEntityEngine;
import ecru.MapleTree.tile.ecru_TileEntityEngineRender;
import ecru.MapleTree.tile.ecru_TileEntityFountain;
import ecru.MapleTree.tile.ecru_TileEntityFountainRender;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopperRender;
import ecru.MapleTree.tile.ecru_TileEntityGrapeTub;
import ecru.MapleTree.tile.ecru_TileEntityGrapeTubRender;
import ecru.MapleTree.tile.ecru_TileEntityHumanPowerDrive;
import ecru.MapleTree.tile.ecru_TileEntityHumanPowerDriveRender;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIllumination;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIlluminationRender;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaft;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaftGear;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaftGearRender;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaftRender;
import ecru.MapleTree.tile.ecru_TileEntitySLight;
import ecru.MapleTree.tile.ecru_TileEntitySLightRender;
import ecru.MapleTree.tile.ecru_TileEntitySprinkler;
import ecru.MapleTree.tile.ecru_TileEntitySprinklerRender;
import ecru.MapleTree.tile.ecru_TileEntityStoneMortar;
import ecru.MapleTree.tile.ecru_TileEntityStoneMortarRender;
import ecru.MapleTree.tile.ecru_TileEntitySunDrying;
import ecru.MapleTree.tile.ecru_TileEntitySunDryingRender;
import ecru.MapleTree.tile.ecru_TileEntitySunFlower;
import ecru.MapleTree.tile.ecru_TileEntitySunFlowerRender;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrelRender;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public static final KeyBinding inputKey = new KeyBinding("MapleTree.text.key.name", 25, "MapleTree.text.mod.name");

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new ecru_Particles());
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().field_71441_e;
    }

    @Override
    public void registerComponents() {
        ClientRegistry.registerTileEntity(ecru_TileEntityStoneMortar.class, "ecru_TileEntityStoneMortar", new ecru_TileEntityStoneMortarRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityEngine.class, "ecru_TileEntityEngine", new ecru_TileEntityEngineRender());
        ClientRegistry.registerTileEntity(ecru_TileEntitySunFlower.class, "ecru_TileEntitySunFlower", new ecru_TileEntitySunFlowerRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityLighthouseIllumination.class, "ecru_TileEntityLighthouseIllumination", new ecru_TileEntityLighthouseIlluminationRender());
        ClientRegistry.registerTileEntity(ecru_TileEntitySprinkler.class, "ecru_TileEntitySprinkler", new ecru_TileEntitySprinklerRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityFountain.class, "ecru_TileEntityFountain", new ecru_TileEntityFountainRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityCookPot.class, "ecru_TileEntityCookPot", new ecru_TileEntityCookPotRender());
        ClientRegistry.registerTileEntity(ecru_TileEntitySLight.class, "ecru_TileEntitySLight", new ecru_TileEntitySLightRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityBiofuelPD.class, "ecru_TileEntityBiofuelPD", new ecru_TileEntityBiofuelPDRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityGrainHopper.class, "ecru_TileEntityGrainHopper", new ecru_TileEntityGrainHopperRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityHumanPowerDrive.class, "ecru_TileEntityHumanPowerDrive", new ecru_TileEntityHumanPowerDriveRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityPowerShaftGear.class, "ecru_TileEntityPowerShaftGear", new ecru_TileEntityPowerShaftGearRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityPowerShaft.class, "ecru_TileEntityPowerShaft", new ecru_TileEntityPowerShaftRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityGrapeTub.class, "ecru_TileEntityGrapeTub", new ecru_TileEntityGrapeTubRender());
        ClientRegistry.registerTileEntity(ecru_TileEntityWineBarrel.class, "ecru_TileEntityWineBarrel", new ecru_TileEntityWineBarrelRender());
        ClientRegistry.registerTileEntity(ecru_TileEntitySunDrying.class, "ecru_TileEntitySunDrying", new ecru_TileEntitySunDryingRender());
        ClientRegistry.registerKeyBinding(inputKey);
    }

    @Override
    public int getNewRenderType() {
        return RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new ecru_BlockRender());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderFire());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderDecoration());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderLeafFence());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderCauldron());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderPlanter());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderSpile());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderVanilla());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderGrape());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderOreFlower());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderEngine());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderStoneMortar());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderSunFlower());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderPetal());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderWhiteFence());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderLighthouseIllumination());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderTomato());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderEggplant());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderOnion());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderSprinkler());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderFountain());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderOreBlock());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderMiniStairs1());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderCookPot());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderCabbage());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderSLight());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderGrainHopper());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderBiofuelPD());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderMarbleJewel());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderJapaneseRadish());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderPepper());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderSpice());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderHumanPowerDrive());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderPowerShaftGear());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderPowerShaft());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderGrapeTub());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderWineBarrel());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderWineFaucet());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderPersimmon());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderGatherItems());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderDriedPersimmon());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderMortar());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderThinWood());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderGrainDryer());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderKelp());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderSunDrying());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderTeuchiUdon());
        RenderingRegistry.registerBlockHandler(new ecru_BlockRenderCompost());
        RenderingRegistry.registerEntityRenderingHandler(ecru_EntityMomiji.class, new ecru_RenderMomiji());
        RenderingRegistry.registerEntityRenderingHandler(ecru_EntityGacha.class, new RenderSnowball(mod_ecru_MapleTree.Item_gacha));
        RenderingRegistry.registerEntityRenderingHandler(ecru_EntityDummyPlayer.class, new ecru_helpRender());
    }
}
