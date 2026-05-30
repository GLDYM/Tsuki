package ecru.MapleTree.model;

import ecru.MapleTree.common.TechneModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelFountain extends ModelBase {
    TechneModelRenderer Base1;
    TechneModelRenderer BodyTop;
    TechneModelRenderer Body1;
    TechneModelRenderer Body2;
    TechneModelRenderer Body3;
    TechneModelRenderer Body4;
    TechneModelRenderer Body5;
    private float[] angleData = {0.3488f, 0.31392002f, 0.27904f, 0.24416001f, 0.20928001f, 0.1744f, 0.13952f, 0.10464001f, 0.06976f, 0.03488f, 0.0f};
    private final float pBase = 0.6081317f;

    public ecru_ModelFountain() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Base1 = new TechneModelRenderer(this, 0, 9);
        this.Base1.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 3, 2);
        this.Base1.func_78793_a(0.0f, 21.0f, 0.0f);
        this.Base1.func_78787_b(64, 32);
        this.Base1.field_78809_i = true;
        setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.BodyTop = new TechneModelRenderer(this, 0, 0);
        this.BodyTop.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 3, 1);
        this.BodyTop.func_78793_a(0.0f, 22.0f, 0.0f);
        this.BodyTop.func_78787_b(64, 32);
        this.BodyTop.field_78809_i = true;
        setRotation(this.BodyTop, 0.0f, 0.0f, 0.0f);
        this.Body1 = new TechneModelRenderer(this, 0, 0);
        this.Body1.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 3, 1);
        this.Body1.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body1.func_78787_b(64, 32);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.6981317f, 0.0f, 0.0f);
        this.Body2 = new TechneModelRenderer(this, 0, 0);
        this.Body2.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 3, 1);
        this.Body2.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body2.func_78787_b(64, 32);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.6981317f, 0.0f, 0.0f);
        this.Body3 = new TechneModelRenderer(this, 0, 0);
        this.Body3.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 3, 1);
        this.Body3.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body3.func_78787_b(64, 32);
        this.Body3.field_78809_i = true;
        setRotation(this.Body3, 0.6981317f, 0.0f, 0.0f);
        this.Body4 = new TechneModelRenderer(this, 0, 0);
        this.Body4.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 3, 1);
        this.Body4.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body4.func_78787_b(64, 32);
        this.Body4.field_78809_i = true;
        setRotation(this.Body4, 0.6981317f, 0.0f, 0.0f);
        this.Body5 = new TechneModelRenderer(this, 0, 0);
        this.Body5.func_78789_a(-0.5f, -4.0f, -0.5f, 1, 3, 1);
        this.Body5.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body5.func_78787_b(64, 32);
        this.Body5.field_78809_i = true;
        setRotation(this.Body5, 0.6981317f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Base1.func_78785_a(f5);
        this.BodyTop.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Body3.func_78785_a(f5);
        this.Body4.func_78785_a(f5);
    }

    public void render2(float f, int mode) {
        this.Base1.func_78785_a(f);
        switch (mode) {
            case 0:
                this.BodyTop.func_78785_a(f);
                break;
            case 1:
                this.Body1.func_78785_a(f);
                break;
            case 2:
                this.Body1.func_78785_a(f);
                this.Body2.func_78785_a(f);
                break;
            case 3:
                this.Body1.func_78785_a(f);
                this.Body2.func_78785_a(f);
                this.Body3.func_78785_a(f);
                break;
            case 4:
                this.BodyTop.func_78785_a(f);
                this.Body1.func_78785_a(f);
                this.Body2.func_78785_a(f);
                this.Body3.func_78785_a(f);
                this.Body4.func_78785_a(f);
                break;
            case 5:
                this.BodyTop.func_78785_a(f);
                this.Body1.func_78785_a(f);
                this.Body2.func_78785_a(f);
                this.Body3.func_78785_a(f);
                this.Body4.func_78785_a(f);
                this.Body5.func_78785_a(f);
                break;
            case 6:
                this.Body1.func_78785_a(f);
                this.Body2.func_78785_a(f);
                break;
        }
    }

    public void setBodyRotation(float f, int meta, int ang) {
        setRotation(this.Base1, 0.0f, f, 0.0f);
        switch (meta & 7) {
            case 0:
                setRotation(this.BodyTop, 0.0f, f, 0.0f);
                break;
            case 1:
                setRotation(this.Body1, 0.6081317f + this.angleData[ang], f, 0.0f);
                break;
            case 2:
                setRotation(this.Body1, 0.6081317f + this.angleData[ang], f, 0.0f);
                setRotation(this.Body2, 0.6081317f + this.angleData[ang], f + 3.14f, 0.0f);
                break;
            case 3:
                setRotation(this.Body1, 0.6081317f + this.angleData[ang], f, 0.0f);
                setRotation(this.Body2, 0.6081317f + this.angleData[ang], f + 2.09f, 0.0f);
                setRotation(this.Body3, 0.6081317f + this.angleData[ang], f + 4.18f, 0.0f);
                break;
            case 4:
                setRotation(this.BodyTop, 0.0f, f, 0.0f);
                setRotation(this.Body1, 0.6081317f + this.angleData[ang], f, 0.0f);
                setRotation(this.Body2, 0.6081317f + this.angleData[ang], f + 1.57f, 0.0f);
                setRotation(this.Body3, 0.6081317f + this.angleData[ang], f + 3.14f, 0.0f);
                setRotation(this.Body4, 0.6081317f + this.angleData[ang], f + 4.71f, 0.0f);
                break;
            case 5:
                setRotation(this.BodyTop, 0.0f, f, 0.0f);
                setRotation(this.Body1, 0.6081317f + this.angleData[ang], f, 0.0f);
                setRotation(this.Body2, 0.6081317f + this.angleData[ang], f + 1.256f, 0.0f);
                setRotation(this.Body3, 0.6081317f + this.angleData[ang], f + 2.512f, 0.0f);
                setRotation(this.Body4, 0.6081317f + this.angleData[ang], f + 3.768f, 0.0f);
                setRotation(this.Body5, 0.6081317f + this.angleData[ang], f + 5.024f, 0.0f);
                break;
            case 6:
                setRotation(this.Body1, 0.6081317f + this.angleData[ang], f, 0.0f);
                setRotation(this.Body2, 0.6081317f + this.angleData[ang], f + 0.872f, 0.0f);
                break;
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }
}
