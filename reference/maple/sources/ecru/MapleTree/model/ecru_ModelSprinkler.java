package ecru.MapleTree.model;

import ecru.MapleTree.common.TechneModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelSprinkler extends ModelBase {
    TechneModelRenderer MainBase0;
    TechneModelRenderer Base1;
    TechneModelRenderer Body1;
    TechneModelRenderer Body2;
    TechneModelRenderer Body3;
    TechneModelRenderer Body4;
    TechneModelRenderer MainBase1;
    TechneModelRenderer MainBase2;
    TechneModelRenderer Base2;
    TechneModelRenderer Base3;
    TechneModelRenderer BaseF1;
    TechneModelRenderer BaseF2;
    TechneModelRenderer BaseF3;
    TechneModelRenderer BaseF4;
    TechneModelRenderer MainBase3;

    public ecru_ModelSprinkler() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.MainBase0 = new TechneModelRenderer(this, 0, 26);
        this.MainBase0.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 1, 4);
        this.MainBase0.func_78793_a(0.0f, 22.3f, 0.0f);
        this.MainBase0.func_78787_b(64, 32);
        this.MainBase0.field_78809_i = true;
        setRotation(this.MainBase0, 0.0f, 0.0f, 0.0f);
        this.Base1 = new TechneModelRenderer(this, 0, 4);
        this.Base1.func_78789_a(0.0f, 0.0f, 0.0f, 6, 1, 6);
        this.Base1.func_78793_a(-3.0f, 22.8f, -3.0f);
        this.Base1.func_78787_b(64, 32);
        this.Base1.field_78809_i = true;
        setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.Body1 = new TechneModelRenderer(this, 34, 0);
        this.Body1.func_78789_a(-0.5f, -1.0f, -0.5f, 1, 2, 1);
        this.Body1.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body1.func_78787_b(64, 32);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new TechneModelRenderer(this, 32, 9);
        this.Body2.func_78789_a(0.9f, -0.5f, -0.5f, 2, 1, 1);
        this.Body2.func_78793_a(0.0f, 21.0f, 0.0f);
        this.Body2.func_78787_b(64, 32);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, -0.4886922f);
        this.Body3 = new TechneModelRenderer(this, 32, 13);
        this.Body3.func_78789_a(0.9f, -0.5f, -0.5f, 2, 1, 1);
        this.Body3.func_78793_a(0.0f, 21.0f, 0.0f);
        this.Body3.func_78787_b(64, 32);
        this.Body3.field_78809_i = true;
        setRotation(this.Body3, 0.0f, 3.141593f, -0.4886922f);
        this.Body4 = new TechneModelRenderer(this, 30, 4);
        this.Body4.func_78789_a(-1.0f, -0.5f, -1.0f, 2, 1, 2);
        this.Body4.func_78793_a(0.0f, 20.5f, 0.0f);
        this.Body4.func_78787_b(64, 32);
        this.Body4.field_78809_i = true;
        setRotation(this.Body4, 0.0f, 0.0f, 0.0f);
        this.MainBase1 = new TechneModelRenderer(this, 17, 26);
        this.MainBase1.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 1, 4);
        this.MainBase1.func_78793_a(0.0f, 22.3f, 0.0f);
        this.MainBase1.func_78787_b(64, 32);
        this.MainBase1.field_78809_i = true;
        setRotation(this.MainBase1, 0.0f, 0.0f, 0.0f);
        this.MainBase2 = new TechneModelRenderer(this, 34, 26);
        this.MainBase2.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 1, 4);
        this.MainBase2.func_78793_a(0.0f, 22.3f, 0.0f);
        this.MainBase2.func_78787_b(64, 32);
        this.MainBase2.field_78809_i = true;
        setRotation(this.MainBase2, 0.0f, 0.0f, 0.0f);
        this.Base2 = new TechneModelRenderer(this, 0, 0);
        this.Base2.func_78789_a(-6.0f, 0.0f, -0.5f, 12, 1, 1);
        this.Base2.func_78793_a(0.0f, 22.5f, 0.0f);
        this.Base2.func_78787_b(64, 32);
        this.Base2.field_78809_i = true;
        setRotation(this.Base2, 0.0f, -0.7853982f, 0.0f);
        this.Base3 = new TechneModelRenderer(this, 0, 0);
        this.Base3.func_78789_a(-6.0f, 0.0f, -0.5f, 12, 1, 1);
        this.Base3.func_78793_a(0.0f, 22.5f, 0.0f);
        this.Base3.func_78787_b(64, 32);
        this.Base3.field_78809_i = true;
        setRotation(this.Base3, 0.0f, 0.7853982f, 0.0f);
        this.BaseF1 = new TechneModelRenderer(this, 0, 14);
        this.BaseF1.func_78789_a(5.0f, 0.0f, -1.0f, 2, 2, 2);
        this.BaseF1.func_78793_a(0.0f, 22.0f, 0.0f);
        this.BaseF1.func_78787_b(64, 32);
        this.BaseF1.field_78809_i = true;
        setRotation(this.BaseF1, 0.0f, -0.7853982f, 0.0f);
        this.BaseF2 = new TechneModelRenderer(this, 0, 14);
        this.BaseF2.func_78789_a(5.0f, 0.0f, -1.0f, 2, 2, 2);
        this.BaseF2.func_78793_a(0.0f, 22.0f, 0.0f);
        this.BaseF2.func_78787_b(64, 32);
        this.BaseF2.field_78809_i = true;
        setRotation(this.BaseF2, 0.0f, 0.7853982f, 0.0f);
        this.BaseF3 = new TechneModelRenderer(this, 0, 14);
        this.BaseF3.func_78789_a(5.0f, 0.0f, -1.0f, 2, 2, 2);
        this.BaseF3.func_78793_a(0.0f, 22.0f, 0.0f);
        this.BaseF3.func_78787_b(64, 32);
        this.BaseF3.field_78809_i = true;
        setRotation(this.BaseF3, 0.0f, 2.356194f, 0.0f);
        this.BaseF4 = new TechneModelRenderer(this, 0, 14);
        this.BaseF4.func_78789_a(5.0f, 0.0f, -1.0f, 2, 2, 2);
        this.BaseF4.func_78793_a(0.0f, 22.0f, 0.0f);
        this.BaseF4.func_78787_b(64, 32);
        this.BaseF4.field_78809_i = true;
        setRotation(this.BaseF4, 0.0f, -2.356194f, 0.0f);
        this.MainBase3 = new TechneModelRenderer(this, 0, 20);
        this.MainBase3.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 1, 4);
        this.MainBase3.func_78793_a(0.0f, 22.3f, 0.0f);
        this.MainBase3.func_78787_b(64, 32);
        this.MainBase3.field_78809_i = true;
        setRotation(this.MainBase3, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.MainBase0.func_78785_a(f5);
        this.Base1.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Body3.func_78785_a(f5);
        this.Body4.func_78785_a(f5);
        this.MainBase1.func_78785_a(f5);
        this.MainBase2.func_78785_a(f5);
        this.MainBase3.func_78785_a(f5);
        this.Base2.func_78785_a(f5);
        this.Base3.func_78785_a(f5);
        this.BaseF1.func_78785_a(f5);
        this.BaseF2.func_78785_a(f5);
        this.BaseF3.func_78785_a(f5);
        this.BaseF4.func_78785_a(f5);
    }

    public void render2(float f, int meta) {
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Body3.func_78785_a(f);
        this.Body4.func_78785_a(f);
        this.Base1.func_78785_a(f);
        this.Base2.func_78785_a(f);
        this.Base3.func_78785_a(f);
        this.BaseF1.func_78785_a(f);
        this.BaseF2.func_78785_a(f);
        this.BaseF3.func_78785_a(f);
        this.BaseF4.func_78785_a(f);
        switch (meta & 3) {
            case 0:
            default:
                this.MainBase0.func_78785_a(f);
                break;
            case 1:
                this.MainBase1.func_78785_a(f);
                break;
            case 2:
                this.MainBase2.func_78785_a(f);
                break;
            case 3:
                this.MainBase3.func_78785_a(f);
                break;
        }
    }

    public void setBodyRotation(float f) {
        setRotation(this.Body1, 0.0f, f, 0.0f);
        setRotation(this.Body2, 0.0f, f, -0.4886922f);
        setRotation(this.Body3, 0.0f, f + 3.141593f, -0.4886922f);
        setRotation(this.Body4, 0.0f, f, 0.0f);
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
