package ecru.MapleTree.entity.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.ai.ecru_EntityAIFishing;
import ecru.MapleTree.entity.container.ecru_ContainerMomiji;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketMomijiB;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiMomiji extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui12.png");
    ecru_EntityMomiji momiji;
    private Random rand;
    private int updateCounter;
    private float prevAD;

    public ecru_GuiMomiji(EntityPlayer player, ecru_EntityMomiji entity) {
        super(new ecru_ContainerMomiji(player, entity));
        this.prevAD = -1.0f;
        this.rand = new Random();
        this.field_147000_g = 244;
        this.momiji = entity;
        this.updateCounter = 0;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        this.field_146292_n.add(new GuiButton(1, i + 64, j + 57, 10, 10, "+"));
        this.field_146292_n.add(new GuiButton(2, i + 64, j + 70, 10, 10, "+"));
        this.field_146292_n.add(new GuiButton(3, i + 64, j + 83, 10, 10, "+"));
        this.field_146292_n.add(new GuiButton(4, i + 116, j + 42, 32, 12, "reset"));
    }

    protected void func_146979_b(int i, int j) {
        String status;
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.NAME"), 8, 6, 4210752);
        this.field_146289_q.func_78276_b("Inventory", 8, 153, 4210752);
        String name = this.momiji.getOwnerName();
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.OWNER") + " : " + name, 65, 6, 4210752);
        int st = this.momiji.isSitting() ? 13 : this.momiji.isFreedom() ? 12 : 11;
        switch (st) {
            case 11:
                status = StatCollector.func_74838_a("MapleTree.text.MOMIJI.SERVANT");
                break;
            case 12:
                status = StatCollector.func_74838_a("MapleTree.text.MOMIJI.FREEDOM");
                break;
            case 13:
                status = StatCollector.func_74838_a("MapleTree.text.MOMIJI.SITTING");
                break;
            default:
                status = StatCollector.func_74838_a("MapleTree.text.MOMIJI.UNKNOWN");
                break;
        }
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.STATUS") + " : " + status, 64, 30, 4210752);
        this.field_146289_q.func_78276_b("Lv : " + this.momiji.momijiLv, 64, 43, 4210752);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.ATTACK") + " Lv." + this.momiji.stateBonusAttackLv + " / " + this.momiji.getStateBonusAttackLvCap(), 77, 57, 4210752);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.DEFENSE") + " Lv." + this.momiji.stateBonusDefenseLv + " / " + this.momiji.getStateBonusDefenseLvCap(), 77, 70, 4210752);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.SPEED") + " Lv." + this.momiji.stateBonusSpeedLv + " / " + this.momiji.getStateBonusSpeedLvCap(), 77, 83, 4210752);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.MOMIJI.BONUSPOINT") + " : " + this.momiji.bonusPoint, 64, 95, 4210752);
        this.field_146289_q.func_78276_b("EXP", 20, 104, 4210752);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.momiji.isSitting()) {
        }
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef(0 + 35, (0 + 75) - 0, 50.0f);
        GL11.glScalef(-40.0f, 40.0f, 40.0f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        float f2 = this.momiji.field_70761_aq;
        float f3 = this.momiji.field_70177_z;
        float f4 = this.momiji.field_70759_as;
        float f5 = this.momiji.field_70125_A;
        float f8 = (this.field_147003_i + 35) - i;
        float f9 = (this.field_147009_r + 40) - j;
        GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef((-((float) Math.atan(f9 / 40.0f))) * 20.0f, 1.0f, 0.0f, 0.0f);
        this.momiji.field_70761_aq = ((float) Math.atan(f8 / 40.0f)) * 20.0f;
        ecru_EntityMomiji ecru_entitymomiji = this.momiji;
        ecru_EntityMomiji ecru_entitymomiji2 = this.momiji;
        float fAtan = ((float) Math.atan(f8 / 40.0f)) * 40.0f;
        ecru_entitymomiji2.field_70177_z = fAtan;
        ecru_entitymomiji.field_70759_as = fAtan;
        this.momiji.field_70125_A = (-((float) Math.atan(f9 / 40.0f))) * 20.0f;
        GL11.glTranslatef(0.0f, this.momiji.field_70129_M, 0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        RenderManager.field_78727_a.func_147940_a(this.momiji, 0.0d, 0.0d, 0.0d, 0.0f, 1.0f);
        this.momiji.field_70761_aq = f2;
        this.momiji.field_70177_z = f3;
        this.momiji.field_70759_as = f4;
        this.momiji.field_70125_A = f5;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable(32826);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
        GL11.glDisable(3553);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
        RenderHelper.func_74520_c();
        GL11.glEnable(32826);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mousePointMessage(i, j);
    }

    protected void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int expPoint = this.momiji.expPoint;
        int nextExpPoint = this.momiji.nextExpData[this.momiji.momijiLv];
        int expPointWidth = (int) (123.0d * (expPoint / nextExpPoint));
        func_73729_b(xStart + 44, yStart + 106, 0, 253, expPointWidth, 3);
        drawHeathArmor(0, 0);
    }

    protected void drawHeathArmor(int par1, int par2) {
        boolean var3 = (this.momiji.field_70172_ad / 3) % 2 == 1;
        if (this.momiji.field_70172_ad < 10) {
            var3 = false;
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(field_110324_m);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int lhealth = MathHelper.func_76123_f(this.momiji.func_110143_aJ());
        int llasthealth = MathHelper.func_76123_f(this.momiji.field_70735_aL);
        IAttributeInstance var10 = this.momiji.func_110148_a(SharedMonsterAttributes.field_111267_a);
        int i = par2 - 39;
        float var14 = (float) var10.func_111126_e();
        float var15 = this.momiji.func_110139_bj();
        int var16 = MathHelper.func_76123_f(((var14 + var15) / 2.0f) / 10.0f);
        int var17 = Math.max(10 - (var16 - 2), 3);
        float var19 = var15;
        int var21 = -1;
        if (this.momiji.func_70644_a(Potion.field_76428_l)) {
            var21 = this.updateCounter % MathHelper.func_76123_f(var14 + 5.0f);
        }
        for (int li = MathHelper.func_76123_f((var14 + var15) / 2.0f) - 1; li >= 0; li--) {
            int var23 = 16;
            if (this.momiji.func_70644_a(Potion.field_76436_u)) {
                var23 = 16 + 36;
            } else if (this.momiji.func_70644_a(Potion.field_82731_v)) {
                var23 = 16 + 72;
            }
            int var25 = MathHelper.func_76123_f((li + 1) / 10.0f);
            int ldrawx = this.field_147003_i + ((li % 10) * 8) + 86;
            int ldrawy = this.field_147009_r + 7 + (var25 * var17);
            if (lhealth <= 4) {
                ldrawy += this.rand.nextInt(2);
            }
            if (li == var21) {
                ldrawy -= 2;
            }
            func_73729_b(ldrawx, ldrawy, var3 ? 25 : 16, 0, 9, 9);
            if (var3) {
                if ((li * 2) + 1 < llasthealth) {
                    func_73729_b(ldrawx, ldrawy, var23 + 54, 0, 9, 9);
                }
                if ((li * 2) + 1 == llasthealth) {
                    func_73729_b(ldrawx, ldrawy, var23 + 63, 0, 9, 9);
                }
            }
            if (var19 > 0.0f) {
                if (var19 == var15 && var15 % 2.0f == 1.0f) {
                    func_73729_b(ldrawx, ldrawy, var23 + 153, 0, 9, 9);
                } else {
                    func_73729_b(ldrawx, ldrawy, var23 + 144, 0, 9, 9);
                }
                var19 -= 2.0f;
            } else {
                if ((li * 2) + 1 < lhealth) {
                    func_73729_b(ldrawx, ldrawy, var23 + 36, 0, 9, 9);
                }
                if ((li * 2) + 1 == lhealth) {
                    func_73729_b(ldrawx, ldrawy, var23 + 45, 0, 9, 9);
                }
            }
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        this.updateCounter++;
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        ecru_PacketHandler.network.sendToServer(new ecru_PacketMomijiB(this.momiji.func_145782_y(), par1GuiButton.field_146127_k));
    }

    private void mousePointMessage(int par5, int par6) {
        String s;
        RenderHelper.func_74518_a();
        int mo_x = (par5 - this.field_147003_i) - 1;
        int mo_y = (par6 - this.field_147009_r) - 1;
        if (mo_x >= 116 && mo_x <= 148 && mo_y >= 42 && mo_y <= 54) {
            List msg = new ArrayList();
            String s2 = StatCollector.func_74838_a("MapleTree.text.MOMIJI.MESSAGE1");
            ItemStack it = new ItemStack(mod_ecru_MapleTree.Item_treeManure, 1, 0);
            msg.add(String.format(s2, it.func_82833_r()));
            msg.add(StatCollector.func_74838_a("MapleTree.text.MOMIJI.MESSAGE2"));
            func_146283_a(msg, mo_x - 150, mo_y - 20);
        }
        if (mo_x >= 8 && mo_x <= 59 && mo_y >= 19 && mo_y <= 79) {
            updateAttackDamage();
            List msg2 = new ArrayList();
            int max = ecru_EntityAIFishing.LV[this.momiji.fishingLv];
            if (this.momiji.getAttackMode() == 2) {
                s = StatCollector.func_74838_a("MapleTree.text.MOMIJI.MODE." + this.momiji.getAttackMode()) + " Lv." + this.momiji.fishingLv + "  next : " + this.momiji.fishingCount + "/" + max;
            } else {
                s = StatCollector.func_74838_a("MapleTree.text.MOMIJI.MODE." + this.momiji.getAttackMode());
            }
            msg2.add(s);
            String s3 = StatCollector.func_74838_a("MapleTree.text.MOMIJI.ATTACK") + " : " + this.momiji.attackDamage;
            msg2.add(s3);
            func_146283_a(msg2, mo_x - 5, mo_y);
        }
        RenderHelper.func_74520_c();
    }

    private void updateAttackDamage() {
        if (this.prevAD == this.momiji.stateBonusAttackData[this.momiji.stateBonusAttackLv]) {
            return;
        }
        this.momiji.setTotalAttackDamage();
        this.prevAD = this.momiji.stateBonusAttackData[this.momiji.stateBonusAttackLv];
    }
}
