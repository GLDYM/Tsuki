package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelLighthouseIllumination extends ModelBase {
    ModelRenderer Base4;
    ModelRenderer Base3;
    ModelRenderer Base2;
    ModelRenderer Base1;
    ModelRenderer Body1;
    ModelRenderer BodyUnder;
    ModelRenderer LensR1;
    ModelRenderer LensR2;
    ModelRenderer LensL1;
    ModelRenderer LensL2;
    ModelRenderer BodyCenter;

    public ecru_ModelLighthouseIllumination() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Base4 = new ModelRenderer(this, 0, 29);
        this.Base4.func_78789_a(-4.0f, 0.0f, -4.0f, 8, 1, 8);
        this.Base4.func_78793_a(0.0f, 23.0f, 0.0f);
        this.Base4.func_78787_b(128, 64);
        this.Base4.field_78809_i = true;
        setRotation(this.Base4, 0.0f, 0.0f, 0.0f);
        this.Base3 = new ModelRenderer(this, 0, 0);
        this.Base3.func_78789_a(-6.0f, 0.0f, -6.0f, 12, 1, 12);
        this.Base3.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Base3.func_78787_b(128, 64);
        this.Base3.field_78809_i = true;
        setRotation(this.Base3, 0.0f, 0.0f, 0.0f);
        this.Base2 = new ModelRenderer(this, 0, 15);
        this.Base2.func_78789_a(-5.5f, 0.0f, -5.5f, 11, 1, 11);
        this.Base2.func_78793_a(0.0f, 21.0f, 0.0f);
        this.Base2.func_78787_b(128, 64);
        this.Base2.field_78809_i = true;
        setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
        this.Base1 = new ModelRenderer(this, 0, 0);
        this.Base1.func_78789_a(-6.0f, 0.0f, -6.0f, 12, 1, 12);
        this.Base1.func_78793_a(0.0f, 20.0f, 0.0f);
        this.Base1.func_78787_b(128, 64);
        this.Base1.field_78809_i = true;
        setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer(this, 98, 0);
        this.Body1.func_78789_a(-2.0f, -5.5f, -5.5f, 4, 11, 11);
        this.Body1.func_78793_a(0.0f, 13.5f, 0.0f);
        this.Body1.func_78787_b(128, 64);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.BodyUnder = new ModelRenderer(this, 78, 0);
        this.BodyUnder.func_78789_a(-2.5f, 0.0f, -2.0f, 5, 2, 4);
        this.BodyUnder.func_78793_a(0.0f, 18.0f, 0.0f);
        this.BodyUnder.func_78787_b(128, 64);
        this.BodyUnder.field_78809_i = true;
        setRotation(this.BodyUnder, 0.0f, 0.0f, 0.0f);
        this.LensR1 = new ModelRenderer(this, 106, 24);
        this.LensR1.func_78789_a(2.5f, -5.0f, -5.0f, 1, 10, 10);
        this.LensR1.func_78793_a(0.0f, 13.5f, 0.0f);
        this.LensR1.func_78787_b(128, 64);
        this.LensR1.field_78809_i = true;
        setRotation(this.LensR1, 0.0f, 0.0f, 0.0f);
        this.LensR2 = new ModelRenderer(this, 106, 47);
        this.LensR2.func_78789_a(4.0f, -4.0f, -4.0f, 1, 8, 8);
        this.LensR2.func_78793_a(0.0f, 13.5f, 0.0f);
        this.LensR2.func_78787_b(128, 64);
        this.LensR2.field_78809_i = true;
        setRotation(this.LensR2, 0.0f, 0.0f, 0.0f);
        this.LensL1 = new ModelRenderer(this, 82, 24);
        this.LensL1.func_78789_a(-3.5f, -5.0f, -5.0f, 1, 10, 10);
        this.LensL1.func_78793_a(0.0f, 13.5f, 0.0f);
        this.LensL1.func_78787_b(128, 64);
        this.LensL1.field_78809_i = true;
        setRotation(this.LensL1, 0.0f, 0.0f, 0.0f);
        this.LensL2 = new ModelRenderer(this, 86, 47);
        this.LensL2.func_78789_a(-5.0f, -4.0f, -4.0f, 1, 8, 8);
        this.LensL2.func_78793_a(0.0f, 13.5f, 0.0f);
        this.LensL2.func_78787_b(128, 64);
        this.LensL2.field_78809_i = true;
        setRotation(this.LensL2, 0.0f, 0.0f, 0.0f);
        this.BodyCenter = new ModelRenderer(this, 78, 16);
        this.BodyCenter.func_78789_a(-3.0f, -1.5f, -1.5f, 6, 3, 3);
        this.BodyCenter.func_78793_a(0.0f, 13.5f, 0.0f);
        this.BodyCenter.func_78787_b(128, 64);
        this.BodyCenter.field_78809_i = true;
        setRotation(this.BodyCenter, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Base4.func_78785_a(f5);
        this.Base3.func_78785_a(f5);
        this.Base2.func_78785_a(f5);
        this.Base1.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.BodyUnder.func_78785_a(f5);
        this.LensR1.func_78785_a(f5);
        this.LensR2.func_78785_a(f5);
        this.LensL1.func_78785_a(f5);
        this.LensL2.func_78785_a(f5);
        this.BodyCenter.func_78785_a(f5);
    }

    public void render2(float f) {
        this.Base4.func_78785_a(f);
        this.Base3.func_78785_a(f);
        this.Base2.func_78785_a(f);
        this.Base1.func_78785_a(f);
        this.Body1.func_78785_a(f);
        this.BodyUnder.func_78785_a(f);
        this.LensR1.func_78785_a(f);
        this.LensR2.func_78785_a(f);
        this.LensL1.func_78785_a(f);
        this.LensL2.func_78785_a(f);
        this.BodyCenter.func_78785_a(f);
    }

    public void setBodyRotation(float f) {
        setRotation(this.Body1, 0.0f, f, 0.0f);
        setRotation(this.BodyUnder, 0.0f, f, 0.0f);
        setRotation(this.BodyCenter, 0.0f, f, 0.0f);
        setRotation(this.LensR1, 0.0f, f, 0.0f);
        setRotation(this.LensR2, 0.0f, f, 0.0f);
        setRotation(this.LensL1, 0.0f, f, 0.0f);
        setRotation(this.LensL2, 0.0f, f, 0.0f);
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
