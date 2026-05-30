package ecru.MapleTree.entity.model;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ecru_ModelMomiji extends ModelBiped {
    public TechneModelRenderer Head;
    TechneModelRenderer Main_down;
    TechneModelRenderer Main_front;
    TechneModelRenderer Main_left;
    TechneModelRenderer Main_right;
    TechneModelRenderer Main_back;
    public TechneModelRenderer Body;
    TechneModelRenderer R_stick;
    TechneModelRenderer L_stick;
    TechneModelRenderer Bottom1;
    TechneModelRenderer Bottom2;
    TechneModelRenderer Bottom3;
    public TechneModelRenderer R_hand;
    public TechneModelRenderer L_hand;
    TechneModelRenderer R_leg;
    TechneModelRenderer L_leg;
    public TechneModelRenderer Body2;
    TechneModelRenderer Bit1;
    TechneModelRenderer Bit2;
    TechneModelRenderer Face;
    TechneModelRenderer Box;
    float field_78095_p = 0.0f;
    private float FACE_OFFSET = 0.01f;

    public int getMomijiSize() {
        return 32;
    }

    public ecru_ModelMomiji() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head = new TechneModelRenderer(this, 0, 0);
        this.Head.func_78789_a(-4.5f, -9.0f, -2.0f, 9, 9, 9);
        this.Head.func_78793_a(-0.5f, 15.0f, 0.0f);
        this.Head.func_78787_b(128, 64);
        this.Head.field_78809_i = true;
        setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Main_down = new TechneModelRenderer(this, 68, 0);
        this.Main_down.func_78789_a(0.0f, 0.0f, 0.0f, 15, 1, 15);
        this.Main_down.func_78793_a(-8.0f, 19.0f, -7.0f);
        this.Main_down.func_78787_b(128, 64);
        this.Main_down.field_78809_i = true;
        setRotation(this.Main_down, 0.0f, 0.0f, 0.0f);
        this.Main_front = new TechneModelRenderer(this, 92, 18);
        this.Main_front.func_78789_a(0.0f, 0.0f, 0.0f, 17, 5, 1);
        this.Main_front.func_78793_a(-9.0f, 15.0f, -8.0f);
        this.Main_front.func_78787_b(128, 64);
        this.Main_front.field_78809_i = true;
        setRotation(this.Main_front, 0.0f, 0.0f, 0.0f);
        this.Main_left = new TechneModelRenderer(this, 96, 33);
        this.Main_left.func_78789_a(0.0f, 0.0f, 0.0f, 1, 5, 15);
        this.Main_left.func_78793_a(7.0f, 15.0f, -7.0f);
        this.Main_left.func_78787_b(128, 64);
        this.Main_left.field_78809_i = true;
        setRotation(this.Main_left, 0.0f, 0.0f, 0.0f);
        this.Main_right = new TechneModelRenderer(this, 63, 33);
        this.Main_right.func_78789_a(0.0f, 0.0f, 0.0f, 1, 5, 15);
        this.Main_right.func_78793_a(-9.0f, 15.0f, -7.0f);
        this.Main_right.func_78787_b(128, 64);
        this.Main_right.field_78809_i = true;
        setRotation(this.Main_right, 0.0f, 0.0f, 0.0f);
        this.Main_back = new TechneModelRenderer(this, 92, 25);
        this.Main_back.func_78789_a(0.0f, 0.0f, 0.0f, 17, 5, 1);
        this.Main_back.func_78793_a(-9.0f, 15.0f, 8.0f);
        this.Main_back.func_78787_b(128, 64);
        this.Main_back.field_78809_i = true;
        setRotation(this.Main_back, 0.0f, 0.0f, 0.0f);
        this.Body = new TechneModelRenderer(this, 37, 0);
        this.Body.func_78789_a(-1.5f, 0.0f, -1.5f, 3, 3, 3);
        this.Body.func_78793_a(-0.5f, 15.0f, 0.5f);
        this.Body.func_78787_b(128, 64);
        this.Body.field_78809_i = true;
        setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.R_stick = new TechneModelRenderer(this, 48, 11);
        this.R_stick.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 4, 1);
        this.R_stick.func_78793_a(-3.5f, 19.0f, -4.5f);
        this.R_stick.func_78787_b(128, 64);
        this.R_stick.field_78809_i = true;
        setRotation(this.R_stick, 0.0f, 0.0f, 0.0f);
        this.L_stick = new TechneModelRenderer(this, 53, 11);
        this.L_stick.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 4, 1);
        this.L_stick.func_78793_a(2.5f, 19.0f, -4.5f);
        this.L_stick.func_78787_b(128, 64);
        this.L_stick.field_78809_i = true;
        setRotation(this.L_stick, 0.0f, 0.0f, 0.0f);
        this.Bottom1 = new TechneModelRenderer(this, 68, 18);
        this.Bottom1.func_78789_a(-2.5f, -0.5f, -2.5f, 5, 1, 5);
        this.Bottom1.func_78793_a(-0.5f, 20.5f, -4.5f);
        this.Bottom1.func_78787_b(128, 64);
        this.Bottom1.field_78809_i = true;
        setRotation(this.Bottom1, 0.0f, 0.0f, 0.0f);
        this.Bottom2 = new TechneModelRenderer(this, 68, 18);
        this.Bottom2.func_78789_a(-2.5f, -0.5f, -2.5f, 5, 1, 5);
        this.Bottom2.func_78793_a(-5.0f, 20.5f, 5.0f);
        this.Bottom2.func_78787_b(128, 64);
        this.Bottom2.field_78809_i = true;
        setRotation(this.Bottom2, 0.0f, 0.0f, 0.0f);
        this.Bottom3 = new TechneModelRenderer(this, 68, 18);
        this.Bottom3.func_78789_a(-2.5f, -0.5f, -2.5f, 5, 1, 5);
        this.Bottom3.func_78793_a(4.0f, 20.5f, 5.0f);
        this.Bottom3.func_78787_b(128, 64);
        this.Bottom3.field_78809_i = true;
        setRotation(this.Bottom3, 0.0f, 0.0f, 0.0f);
        this.R_hand = new TechneModelRenderer(this, 37, 7);
        this.R_hand.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.R_hand.func_78793_a(-2.5f, 16.0f, 0.0f);
        this.R_hand.func_78787_b(128, 64);
        this.R_hand.field_78809_i = true;
        setRotation(this.R_hand, -1.784573f, 0.2230717f, 0.0f);
        this.L_hand = new TechneModelRenderer(this, 42, 7);
        this.L_hand.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.L_hand.func_78793_a(1.5f, 16.0f, 0.0f);
        this.L_hand.func_78787_b(128, 64);
        this.L_hand.field_78809_i = true;
        setRotation(this.L_hand, -1.821752f, -0.1858931f, 0.0f);
        this.R_leg = new TechneModelRenderer(this, 37, 14);
        this.R_leg.func_78789_a(-0.5f, -0.5f, -0.5f, 1, 4, 1);
        this.R_leg.func_78793_a(-1.5f, 18.5f, -0.5f);
        this.R_leg.func_78787_b(128, 64);
        this.R_leg.field_78809_i = true;
        setRotation(this.R_leg, 1.570796f, -0.5235988f, 0.0f);
        this.L_leg = new TechneModelRenderer(this, 42, 14);
        this.L_leg.func_78789_a(-0.5f, -0.5f, -0.5f, 1, 4, 1);
        this.L_leg.func_78793_a(0.5f, 18.5f, -0.5f);
        this.L_leg.func_78787_b(128, 64);
        this.L_leg.field_78809_i = true;
        setRotation(this.L_leg, 1.570796f, 0.5235988f, 0.0f);
        this.Body2 = new TechneModelRenderer(this, 0, 20);
        this.Body2.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 2, 4);
        this.Body2.func_78793_a(-0.5f, 16.5f, 0.5f);
        this.Body2.func_78787_b(128, 64);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.3316126f, 0.0f, 0.0f);
        this.Bit1 = new TechneModelRenderer(this, 0, 28);
        this.Bit1.func_78789_a(4.0f, -5.0f, -1.5f, 3, 1, 3);
        this.Bit1.func_78793_a(0.0f, 3.0f, 0.0f);
        this.Bit1.func_78787_b(128, 64);
        this.Bit1.field_78809_i = true;
        setRotation(this.Bit1, 0.0f, 0.0f, 0.5205006f);
        this.Bit2 = new TechneModelRenderer(this, 0, 28);
        this.Bit2.func_78789_a(4.0f, -5.0f, -1.5f, 3, 1, 3);
        this.Bit2.func_78793_a(0.0f, 3.0f, 0.0f);
        this.Bit2.func_78787_b(128, 64);
        this.Bit2.field_78809_i = true;
        setRotation(this.Bit2, 0.0f, 3.141593f, 0.5205006f);
        this.Face = new TechneModelRenderer(this, 0, 55);
        this.Face.func_78789_a(-4.5f, -9.0f, -2.0f, 9, 9, 0);
        this.Face.func_78793_a(-0.5f, 15.0f, -0.01f);
        this.Face.func_78787_b(128, 64);
        this.Face.field_78809_i = true;
        setRotation(this.Face, 0.0f, 0.0f, 0.0f);
        this.Box = new TechneModelRenderer(this, 50, 0);
        this.Box.func_78789_a(-1.5f, -0.5f, -2.0f, 3, 1, 4);
        this.Box.func_78793_a(4.8f, 17.5f, 5.0f);
        this.Box.func_78787_b(128, 64);
        this.Box.field_78809_i = true;
        setRotation(this.Box, 0.4089647f, 0.4461433f, -0.1858931f);
    }

    public static final float mh_sin(float f) {
        float f2 = f % 6.2831855f;
        return MathHelper.func_76126_a(f2 < 0.0f ? 360.0f + f2 : f2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        ecru_EntityMomiji entitymomiji = (ecru_EntityMomiji) entity;
        boolean modeA = entitymomiji.getAttackMode() == 1 || entitymomiji.getAttackMode() == 2;
        _setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float eye = f1 + f2 + f3;
        boolean eyeFlg = false;
        if (0.0f > mh_sin(eye * 0.05f) + mh_sin(eye * 0.13f) + mh_sin(eye * 0.7f) + 2.55f) {
            eyeFlg = true;
        }
        if (this.field_78091_s) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f / 3.5f, 1.0f / 3.5f, 1.0f / 3.5f);
            if (modeA) {
                GL11.glTranslatef(0.0f, 3.75f, 0.0f);
            } else if (entitymomiji.isSitting() || entitymomiji.func_70115_ae()) {
                GL11.glTranslatef(0.0f, 3.7f, 0.0f);
            } else {
                GL11.glTranslatef(0.0f, 2.1875f, 0.0f);
            }
            if (!modeA) {
                this.Main_down.func_78785_a(f5);
                this.Main_front.func_78785_a(f5);
                this.Main_left.func_78785_a(f5);
                this.Main_right.func_78785_a(f5);
                this.Main_back.func_78785_a(f5);
                this.R_stick.func_78785_a(f5);
                this.L_stick.func_78785_a(f5);
                this.Bottom1.func_78785_a(f5);
                this.Bottom2.func_78785_a(f5);
                this.Bottom3.func_78785_a(f5);
                this.Box.func_78785_a(f5);
            }
            this.Head.func_78785_a(f5);
            this.Body.func_78785_a(f5);
            this.R_hand.func_78785_a(f5);
            this.L_hand.func_78785_a(f5);
            this.L_leg.func_78785_a(f5);
            this.R_leg.func_78785_a(f5);
            this.Body2.func_78785_a(f5);
            if (entitymomiji.isFreedom()) {
                this.Bit1.func_78785_a(f5);
                this.Bit2.func_78785_a(f5);
            }
            if (eyeFlg) {
                this.Face.func_78785_a(f5);
            }
            if (entitymomiji.func_110143_aJ() <= 10.0f && entitymomiji.isSitting()) {
                this.Face.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            return;
        }
        GL11.glPushMatrix();
        GL11.glScalef(1.0f / 1.5f, 1.0f / 1.5f, 1.0f / 1.5f);
        if (modeA) {
            GL11.glTranslatef(0.0f, 0.742f, 0.0f);
        } else if (entitymomiji.isSitting() || entitymomiji.func_70115_ae()) {
            GL11.glTranslatef(0.0f, 0.8f, 0.0f);
        } else {
            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
        }
        if (!modeA) {
            this.Main_down.func_78785_a(f5);
            this.Main_front.func_78785_a(f5);
            this.Main_left.func_78785_a(f5);
            this.Main_right.func_78785_a(f5);
            this.Main_back.func_78785_a(f5);
            this.R_stick.func_78785_a(f5);
            this.L_stick.func_78785_a(f5);
            this.Bottom1.func_78785_a(f5);
            this.Bottom2.func_78785_a(f5);
            this.Bottom3.func_78785_a(f5);
            this.Box.func_78785_a(f5);
        }
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.R_hand.func_78785_a(f5);
        this.L_hand.func_78785_a(f5);
        this.L_leg.func_78785_a(f5);
        this.R_leg.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        if (entitymomiji.isFreedom()) {
            this.Bit1.func_78785_a(f5);
            this.Bit2.func_78785_a(f5);
        }
        if (eyeFlg) {
            this.Face.func_78785_a(f5);
        }
        if (entitymomiji.func_110143_aJ() <= 10.0f && entitymomiji.isSitting()) {
            this.Face.func_78785_a(f5);
        }
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void _setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        ecru_EntityMomiji entity = (ecru_EntityMomiji) par7Entity;
        this.field_78095_p = entity.func_70678_g(f5);
        setRotation(entity.motion);
        this.Head.field_78795_f = f4 / 57.295776f;
        this.Head.field_78796_g = f3 / 57.295776f;
        this.Face.field_78795_f = f4 / 57.295776f;
        this.Face.field_78796_g = f3 / 57.295776f;
        if (entity.getAttackMode() == 1 || entity.getAttackMode() == 2) {
            this.Bit1.func_78793_a(-0.5f, 3.0f, -3.0f);
            this.Bit2.func_78793_a(-0.5f, 3.0f, -3.0f);
            if (entity.isSitting() || entity.func_70115_ae()) {
                this.Face.func_78793_a(-0.5f, 21.0f, 0.0f - this.FACE_OFFSET);
                this.Head.func_78793_a(-0.5f, 21.0f, 0.0f);
                this.Body.func_78793_a(-0.5f, 21.0f, 0.5f);
                setRotation(this.Body, 0.0f, 0.0f, 0.0f);
                this.Body2.func_78793_a(-0.5f, 22.0f, 0.5f);
                setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
                this.R_hand.func_78793_a(-2.5f, 21.0f, 1.0f);
                setRotation(this.R_hand, -1.134464f, -0.3665191f, 0.0f);
                this.L_hand.func_78793_a(1.5f, 21.0f, 1.0f);
                setRotation(this.L_hand, -1.134464f, 0.3665191f, 0.0f);
                this.R_leg.func_78793_a(-1.5f, 23.5f, -0.5f);
                setRotation(this.R_leg, -1.570796f, 0.2617994f, 0.0f);
                this.L_leg.func_78793_a(0.5f, 23.5f, -0.5f);
                setRotation(this.L_leg, -1.570796f, -0.2617994f, 0.0f);
            } else {
                this.Face.func_78793_a(0.0f, 17.0f, (-2.0f) - this.FACE_OFFSET);
                this.Head.func_78793_a(0.0f, 17.0f, -2.0f);
                this.Body.func_78793_a(0.0f, 17.0f, -1.5f);
                setRotation(this.Body, 0.0f, 0.0f, 0.0f);
                this.Body2.func_78793_a(0.0f, 18.5f, -1.5f);
                setRotation(this.Body2, 0.3316126f, 0.0f, 0.0f);
                this.R_hand.func_78793_a(-2.0f, 16.5f, -1.5f);
                setRotation(this.R_hand, 0.0f, 0.0f, 0.2268928f);
                this.L_hand.func_78793_a(2.0f, 16.5f, -1.5f);
                setRotation(this.L_hand, 0.0f, 0.0f, -0.2268928f);
                this.R_leg.func_78793_a(-0.8f, 20.5f, -1.5f);
                setRotation(this.R_leg, 0.0f, 0.0f, 0.0f);
                this.L_leg.func_78793_a(0.8f, 20.5f, -1.5f);
                setRotation(this.L_leg, 0.0f, 0.0f, 0.0f);
            }
            if (!entity.isSitting() && !entity.func_70115_ae()) {
                this.R_hand.field_78795_f = MathHelper.func_76134_b((f * 0.6662f) + 3.1415927f) * 2.0f * f1 * 0.5f;
                this.L_hand.field_78795_f = MathHelper.func_76134_b(f * 0.6662f) * 2.0f * f1 * 0.5f;
                this.R_hand.field_78808_h = 0.0f;
                this.L_hand.field_78808_h = 0.0f;
                this.R_hand.field_78808_h = 0.2268928f;
                this.L_hand.field_78808_h = -0.2268928f;
                this.R_leg.field_78795_f = MathHelper.func_76134_b(f * 0.6662f) * 1.4f * f1;
                this.L_leg.field_78795_f = MathHelper.func_76134_b((f * 0.6662f) + 3.1415927f) * 1.4f * f1;
                this.R_leg.field_78796_g = 0.0f;
                this.L_leg.field_78796_g = 0.0f;
            }
            if (!entity.isSitting() && !entity.func_70115_ae()) {
                this.R_hand.field_78796_g = 0.0f;
                this.L_hand.field_78796_g = 0.0f;
                float f6 = this.field_78095_p;
                this.Body.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927f * 2.0f) * 0.2f;
                this.Body2.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927f * 2.0f) * 0.2f;
                this.R_hand.field_78798_e = (MathHelper.func_76126_a(this.Body.field_78796_g) * 5.0f) - 1.5f;
                this.R_hand.field_78800_c = ((-MathHelper.func_76134_b(this.Body.field_78796_g)) * 5.0f) + 3.0f;
                this.L_hand.field_78798_e = ((-MathHelper.func_76126_a(this.Body.field_78796_g)) * 5.0f) - 1.5f;
                this.L_hand.field_78800_c = (MathHelper.func_76134_b(this.Body.field_78796_g) * 5.0f) - 3.0f;
                this.R_hand.field_78796_g += this.Body.field_78796_g;
                this.L_hand.field_78796_g += this.Body.field_78796_g;
                this.L_hand.field_78795_f += this.Body.field_78796_g;
                float f62 = 1.0f - this.field_78095_p;
                float f63 = f62 * f62;
                float f7 = MathHelper.func_76126_a((1.0f - (f63 * f63)) * 3.1415927f);
                float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927f) * (-(this.Head.field_78795_f - 0.7f)) * 0.75f;
                this.R_hand.field_78795_f = (float) (this.R_hand.field_78795_f - ((f7 * 1.2d) + f8));
                this.R_hand.field_78796_g += this.Body.field_78796_g * 2.0f;
                this.R_hand.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927f) * (-0.4f)) + 0.2268928f;
            }
            this.R_hand.field_78808_h += (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
            this.L_hand.field_78808_h -= (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
            this.R_hand.field_78795_f += MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
            this.L_hand.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
            return;
        }
        this.Main_down.func_78793_a(-8.0f, 19.0f, -7.0f);
        setRotation(this.Main_down, 0.0f, 0.0f, 0.0f);
        this.Main_front.func_78793_a(-9.0f, 15.0f, -8.0f);
        setRotation(this.Main_front, 0.0f, 0.0f, 0.0f);
        this.Main_left.func_78793_a(7.0f, 15.0f, -7.0f);
        setRotation(this.Main_left, 0.0f, 0.0f, 0.0f);
        this.Main_right.func_78793_a(-9.0f, 15.0f, -7.0f);
        setRotation(this.Main_right, 0.0f, 0.0f, 0.0f);
        this.Main_back.func_78793_a(-9.0f, 15.0f, 8.0f);
        setRotation(this.Main_back, 0.0f, 0.0f, 0.0f);
        this.Body.func_78793_a(-0.5f, 15.0f, 0.5f);
        setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.R_stick.func_78793_a(-3.5f, 19.0f, -4.5f);
        setRotation(this.R_stick, 0.0f, 0.0f, 0.0f);
        this.L_stick.func_78793_a(2.5f, 19.0f, -4.5f);
        setRotation(this.L_stick, 0.0f, 0.0f, 0.0f);
        this.Bottom1.func_78793_a(-0.5f, 20.5f, -4.5f);
        this.Bottom2.func_78793_a(-5.0f, 20.5f, 5.0f);
        this.Bottom3.func_78793_a(4.0f, 20.5f, 5.0f);
        this.R_hand.func_78793_a(-2.5f, 16.0f, 0.0f);
        this.L_hand.func_78793_a(1.5f, 16.0f, 0.0f);
        this.R_leg.func_78793_a(-1.5f, 18.5f, -0.5f);
        setRotation(this.R_leg, 1.570796f, -0.5235988f, 0.0f);
        this.L_leg.func_78793_a(0.5f, 18.5f, -0.5f);
        setRotation(this.L_leg, 1.570796f, 0.5235988f, 0.0f);
        this.Body2.func_78793_a(-0.5f, 16.5f, 0.5f);
        setRotation(this.Body2, 0.3316126f, 0.0f, 0.0f);
        this.Bit1.func_78793_a(0.0f, 3.0f, 0.0f);
        this.Bit2.func_78793_a(0.0f, 3.0f, 0.0f);
        this.Box.func_78793_a(4.8f, 17.5f, 5.0f);
        setRotation(this.Box, 0.4089647f, 0.4461433f, -0.1858931f);
        this.Bit1.func_78793_a(0.0f, 3.0f, 0.0f);
        this.Bit2.func_78793_a(0.0f, 3.0f, 0.0f);
        if (!entity.isSitting()) {
            this.Head.func_78793_a(-0.5f, 15.0f, 0.0f);
            this.Body.func_78793_a(-0.5f, 15.0f, 0.5f);
            setRotation(this.Body, 0.0f, 0.0f, 0.0f);
            this.R_hand.func_78793_a(-2.5f, 16.0f, 0.0f);
            setRotation(this.R_hand, -1.784573f, 0.2230717f, 0.0f);
            this.L_hand.func_78793_a(1.5f, 16.0f, 0.0f);
            setRotation(this.L_hand, -1.821752f, -0.1858931f, 0.0f);
            this.R_leg.func_78793_a(-1.5f, 18.5f, -0.5f);
            setRotation(this.R_leg, 1.570796f, -0.5235988f, 0.0f);
            this.L_leg.func_78793_a(0.5f, 18.5f, -0.5f);
            setRotation(this.L_leg, 1.570796f, 0.5235988f, 0.0f);
            this.Body2.func_78793_a(-0.5f, 16.5f, 0.5f);
            setRotation(this.Body2, 0.3316126f, 0.0f, 0.0f);
            this.Bit1.func_78793_a(0.0f, 3.0f, 0.0f);
            this.Bit2.func_78793_a(0.0f, 3.0f, 0.0f);
            this.Face.func_78793_a(-0.5f, 15.0f, 0.0f - this.FACE_OFFSET);
        } else if (entity.func_110143_aJ() <= 10.0f) {
            this.Head.func_78793_a(0.5f, 17.0f, 6.0f);
            setRotation(this.Head, -1.22173f, 0.0f, 0.0f);
            this.Main_down.func_78793_a(8.0f, 20.0f, -7.0f);
            setRotation(this.Main_down, 0.0f, -0.002272f, -3.141593f);
            this.Main_front.func_78793_a(9.0f, 24.0f, -8.0f);
            setRotation(this.Main_front, 0.0f, -0.002272f, -3.141593f);
            this.Main_left.func_78793_a(-7.0f, 24.0f, -7.0f);
            setRotation(this.Main_left, 0.0f, -0.002272f, -3.141593f);
            this.Main_right.func_78793_a(9.0f, 24.0f, -7.0f);
            setRotation(this.Main_right, 0.0f, -0.002272f, -3.141593f);
            this.Main_back.func_78793_a(9.0f, 24.0f, 8.0f);
            setRotation(this.Main_back, 0.0f, -0.002272f, -3.141593f);
            this.Body.func_78793_a(0.5f, 17.0f, 6.0f);
            setRotation(this.Body, -1.553343f, 0.0f, 0.0f);
            this.R_stick.func_78793_a(-2.5f, 20.0f, -4.5f);
            setRotation(this.R_stick, 0.0f, -0.002272f, -3.141593f);
            this.L_stick.func_78793_a(3.5f, 20.0f, -4.5f);
            setRotation(this.L_stick, 0.0f, -0.002272f, -3.141593f);
            this.Bottom1.func_78793_a(0.5f, 18.5f, -4.5f);
            setRotation(this.Bottom1, 0.0f, -0.002272f, -3.141593f);
            this.Bottom2.func_78793_a(-4.0f, 18.5f, 5.0f);
            setRotation(this.Bottom2, 0.0f, -0.002272f, -3.141593f);
            this.Bottom3.func_78793_a(5.0f, 18.5f, 5.0f);
            setRotation(this.Bottom3, 0.0f, -0.002272f, -3.141593f);
            this.R_hand.func_78793_a(-0.5f, 17.0f, 5.5f);
            setRotation(this.R_hand, -1.784573f, 2.007645f, 0.0f);
            this.L_hand.func_78793_a(1.5f, 17.0f, 5.5f);
            setRotation(this.L_hand, -1.821752f, -2.082002f, 0.0f);
            this.R_leg.func_78793_a(-0.5f, 16.7f, 2.0f);
            setRotation(this.R_leg, -1.570796f, 0.2617994f, 0.0f);
            this.L_leg.func_78793_a(1.5f, 16.7f, 2.0f);
            setRotation(this.L_leg, -1.570796f, -0.2617994f, 0.0f);
            this.Body2.func_78793_a(0.5f, 17.3f, 4.0f);
            setRotation(this.Body2, -1.570796f, 0.0f, 0.0f);
            this.Bit1.func_78793_a(0.0f, 8.0f, 0.0f);
            setRotation(this.Bit1, 0.0f, 0.0f, 0.5205006f);
            this.Bit2.func_78793_a(0.0f, 8.0f, 0.0f);
            setRotation(this.Bit2, 0.0f, 3.141593f, 0.5205006f);
            this.Face.func_78793_a(0.5f, 17.0f, 6.0f - this.FACE_OFFSET);
            setRotation(this.Face, -1.22173f, 0.0f, 0.0f);
            this.Box.func_78793_a(5.8f, 18.0f, 1.0f);
            setRotation(this.Box, 0.2974289f, 0.0743572f, 0.0f);
            this.R_hand.field_78808_h += (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
            this.L_hand.field_78808_h -= (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
            this.R_hand.field_78795_f += MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
            this.L_hand.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
            this.R_leg.field_78808_h += (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
            this.L_leg.field_78808_h -= (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
            this.R_leg.field_78795_f += MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
            this.L_leg.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
        } else {
            this.Head.func_78793_a(-0.5f, 12.0f, 6.0f);
            this.Body.func_78793_a(-0.5f, 12.0f, 6.5f);
            this.R_hand.func_78793_a(-2.5f, 12.0f, 6.0f);
            this.L_hand.func_78793_a(1.5f, 12.0f, 6.0f);
            if (entity.func_110143_aJ() < 20.0f) {
                setRotation(this.R_hand, -0.148353f, 0.9948377f, 0.8901179f);
                setRotation(this.L_hand, -0.148353f, -0.9948377f, -0.8901179f);
            } else {
                setRotation(this.R_hand, -0.5934119f, -0.5934119f, 0.0f);
                setRotation(this.L_hand, -0.5934119f, 0.5934119f, 0.0f);
                this.R_hand.field_78808_h += (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
                this.L_hand.field_78808_h -= (MathHelper.func_76134_b(f2 * 0.09f) * 0.05f) + 0.05f;
                this.R_hand.field_78795_f += MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
                this.L_hand.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067f) * 0.05f;
            }
            this.R_leg.func_78793_a(-1.5f, 15.5f, 6.5f);
            setRotation(this.R_leg, -0.5235988f, -0.2094395f, 0.0f);
            this.L_leg.func_78793_a(0.5f, 15.5f, 6.5f);
            setRotation(this.L_leg, -0.5235988f, 0.2094395f, 0.0f);
            this.Body2.func_78793_a(-0.5f, 13.5f, 6.5f);
            setRotation(this.Body2, 0.3316126f, 0.0f, 0.0f);
            this.Bit1.func_78793_a(0.0f, 0.0f, 6.0f);
            this.Bit2.func_78793_a(0.0f, 0.0f, 6.0f);
            this.Face.func_78793_a(-0.5f, 12.0f, 6.0f - this.FACE_OFFSET);
        }
        if (entity.isSitting()) {
            return;
        }
        if (f3 > 0.0f) {
            this.R_hand.field_78796_g = ((f3 / 57.295776f) / 3.0f) + 0.2230717f;
            this.R_stick.field_78808_h = -((f3 / 57.295776f) / 3.0f);
            setRotation(this.L_hand, -1.821752f, -0.1858931f, 0.0f);
            return;
        }
        if (f3 < 0.0f) {
            this.L_hand.field_78796_g = ((f3 / 57.295776f) / 3.0f) - 0.1858931f;
            this.L_stick.field_78808_h = -((f3 / 57.295776f) / 3.0f);
            setRotation(this.R_hand, -1.784573f, 0.2230717f, 0.0f);
        }
    }

    public void setRotation(int f) {
        float m = 6.2831855f * (f / 360.0f);
        setRotation(this.Bottom1, 0.0f, m, 0.0f);
        setRotation(this.Bottom2, 0.0f, m, 0.0f);
        setRotation(this.Bottom3, 0.0f, m, 0.0f);
        setRotation(this.Bit1, 0.0f, -m, 0.5205006f);
        setRotation(this.Bit2, 0.0f, -(m + 3.141593f), 0.5205006f);
    }

    private float func_78172_a(float p_78172_1_, float p_78172_2_) {
        return (Math.abs((p_78172_1_ % p_78172_2_) - (p_78172_2_ * 0.5f)) - (p_78172_2_ * 0.25f)) / (p_78172_2_ * 0.25f);
    }

    public void func_78086_a(EntityLivingBase p_78086_1_, float f, float f1, float f2) {
        ecru_EntityMomiji entity = (ecru_EntityMomiji) p_78086_1_;
        this.Head.field_78808_h = entity.getInterestedAngle(f2);
        this.Face.field_78808_h = entity.getInterestedAngle(f2);
        int i = entity.field_70737_aN;
    }
}
