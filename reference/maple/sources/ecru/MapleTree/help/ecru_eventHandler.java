package ecru.MapleTree.help;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockGrapeTub;
import ecru.MapleTree.block.ecru_BlockPlanter;
import ecru.MapleTree.block.ecru_BlockPowerShaftGear;
import ecru.MapleTree.block.ecru_BlockSprinkler;
import ecru.MapleTree.block.ecru_BlockSunDrying;
import ecru.MapleTree.block.ecru_BlockTeuchiUdon;
import ecru.MapleTree.block.ecru_BlockWineBarrel;
import ecru.MapleTree.client.ClientProxy;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ecru_eventHandler {
    public float prevVignetteBrightness = 1.0f;
    Block targetBlock;
    MovingObjectPosition mouseOverBlock;
    int tBlockX;
    int tBlockY;
    int tBlockZ;
    public static int x;
    public static int y;
    public static int z;
    private EntityPlayer DummyPlayer;
    ecru_EntityDummyPlayer entity;
    protected static final ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
    public static int LINE_MODE_SPRINKLER = 1;
    public static int LINE_MODE_POWER_SHAFT_GEAR = 2;
    public static int lineMode = 0;
    public static boolean onMouse = false;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft game = FMLClientHandler.instance().getClient();
        if (game.field_71462_r == null && game.field_71441_e != mod_ecru_MapleTree.lastWorld) {
            WorldClient worldClient = FMLClientHandler.instance().getClient().field_71441_e;
            this.DummyPlayer = FMLClientHandler.instance().getClient().field_71439_g;
            if (worldClient == null) {
                return;
            }
            if (this.entity != null) {
                this.entity.destroy();
            }
            this.entity = new ecru_EntityDummyPlayer(worldClient);
            this.entity.func_70107_b(this.DummyPlayer.field_70165_t, this.DummyPlayer.field_70163_u, this.DummyPlayer.field_70161_v);
            worldClient.func_72838_d(this.entity);
            mod_ecru_MapleTree.lastWorld = game.field_71441_e;
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (!mod_ecru_MapleTree.helpTip) {
            lineMode = 0;
            onMouse = false;
            return;
        }
        Minecraft game = FMLClientHandler.instance().getClient();
        EntityClientPlayerMP entityClientPlayerMP = game.field_71439_g;
        World world = game.field_71441_e;
        this.mouseOverBlock = game.field_71476_x;
        MovingObjectPosition target = this.mouseOverBlock;
        if (world == null || entityClientPlayerMP == null || game.field_71462_r != null || !Minecraft.func_71382_s() || target == null) {
            lineMode = 0;
            onMouse = false;
            z = 0;
            y = 0;
            x = 0;
            return;
        }
        if (this.mouseOverBlock != null) {
            int targetX = game.field_71476_x.field_72311_b;
            int targetY = game.field_71476_x.field_72312_c;
            int targetZ = game.field_71476_x.field_72309_d;
            Block block = world.func_147439_a(targetX, targetY, targetZ);
            if (block == Blocks.field_150350_a) {
                lineMode = 0;
                onMouse = false;
                z = 0;
                y = 0;
                x = 0;
                return;
            }
            if (block instanceof ecru_BlockGrapeTub) {
                ecru_helpGrapeTub grapeTub = new ecru_helpGrapeTub();
                grapeTub.draw(world, block, targetX, targetY, targetZ);
            }
            if (block instanceof ecru_BlockPlanter) {
                ecru_helpPlanter planter = new ecru_helpPlanter();
                planter.draw(world, block, targetX, targetY, targetZ);
            }
            if (block instanceof ecru_BlockWineBarrel) {
                ecru_helpWineBarrel wineBarrel = new ecru_helpWineBarrel();
                wineBarrel.draw(world, block, targetX, targetY, targetZ);
            }
            if (block instanceof ecru_BlockSunDrying) {
                ecru_helpSunDrying sunDrying = new ecru_helpSunDrying();
                sunDrying.draw(world, block, targetX, targetY, targetZ);
            }
            if (block instanceof ecru_BlockTeuchiUdon) {
                ecru_helpTeuchiUdon teuchiUdon = new ecru_helpTeuchiUdon();
                teuchiUdon.draw(world, block, targetX, targetY, targetZ);
            }
            if ((block instanceof ecru_BlockPowerShaftGear) && entityClientPlayerMP.func_71045_bC() != null && (entityClientPlayerMP.func_71045_bC().func_77973_b() instanceof ItemHoe)) {
                lineMode = LINE_MODE_POWER_SHAFT_GEAR;
                onMouse = true;
                x = targetX;
                y = targetY;
                z = targetZ;
                return;
            }
            if ((block instanceof ecru_BlockSprinkler) && entityClientPlayerMP.func_71045_bC() != null && (entityClientPlayerMP.func_71045_bC().func_77973_b() instanceof ItemHoe)) {
                lineMode = LINE_MODE_SPRINKLER;
                onMouse = true;
                x = targetX;
                y = targetY;
                z = targetZ;
                return;
            }
            lineMode = 0;
            onMouse = false;
            z = 0;
            y = 0;
            x = 0;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void inputKey(InputEvent.KeyInputEvent event) {
        if (ClientProxy.inputKey.func_151468_f() && FMLClientHandler.instance().getClient().field_71462_r == null) {
            mod_ecru_MapleTree.helpTip = !mod_ecru_MapleTree.helpTip;
            FMLClientHandler.instance().getClient().field_71439_g.func_145747_a(new ChatComponentTranslation("Mapletree help Tips : " + mod_ecru_MapleTree.helpTip, new Object[0]));
        }
    }
}
