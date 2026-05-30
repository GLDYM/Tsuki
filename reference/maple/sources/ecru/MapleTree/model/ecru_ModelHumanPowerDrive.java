package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelHumanPowerDrive extends ModelBase {
    ModelRenderer Base1;
    ModelRenderer Base2;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Base3;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Grip1;
    ModelRenderer Grip2;
    ModelRenderer Grip3;
    ModelRenderer Grip4;

    public ecru_ModelHumanPowerDrive() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Base1 = new ModelRenderer(this, 0, 0);
        this.Base1.func_78789_a(-8.0f, 0.0f, -8.0f, 16, 1, 16);
        this.Base1.func_78793_a(0.0f, 23.0f, 0.0f);
        this.Base1.func_78787_b(64, 32);
        this.Base1.field_78809_i = true;
        setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.Base2 = new ModelRenderer(this, 0, 18);
        this.Base2.func_78789_a(-5.0f, 0.0f, -5.0f, 10, 2, 10);
        this.Base2.func_78793_a(0.0f, 21.0f, 0.0f);
        this.Base2.func_78787_b(64, 32);
        this.Base2.field_78809_i = true;
        setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer(this, 0, 49);
        this.Body1.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 8, 4);
        this.Body1.func_78793_a(0.0f, 13.0f, 0.0f);
        this.Body1.func_78787_b(64, 32);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 18, 51);
        this.Body2.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 4, 6);
        this.Body2.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body2.func_78787_b(64, 32);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
        this.Base3 = new ModelRenderer(this, 0, 31);
        this.Base3.func_78789_a(-5.0f, 0.0f, -5.0f, 10, 1, 10);
        this.Base3.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Base3.func_78787_b(64, 32);
        this.Base3.field_78809_i = true;
        setRotation(this.Base3, 0.0f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer(this, 0, 62);
        this.Body3.func_78789_a(-24.0f, -0.5f, -0.5f, 48, 1, 1);
        this.Body3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Body3.func_78787_b(64, 32);
        this.Body3.field_78809_i = true;
        setRotation(this.Body3, 0.0f, 0.0f, 0.0f);
        this.Body4 = new ModelRenderer(this, 0, 62);
        this.Body4.func_78789_a(-24.0f, -0.5f, -0.5f, 48, 1, 1);
        this.Body4.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Body4.func_78787_b(64, 32);
        this.Body4.field_78809_i = true;
        setRotation(this.Body4, 0.0f, 1.570796f, 0.0f);
        this.Grip1 = new ModelRenderer(this, 44, 57);
        this.Grip1.func_78789_a(24.0f, -1.0f, -1.0f, 8, 2, 2);
        this.Grip1.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Grip1.func_78787_b(64, 32);
        this.Grip1.field_78809_i = true;
        setRotation(this.Grip1, 0.0f, 0.0f, 0.0f);
        this.Grip2 = new ModelRenderer(this, 44, 57);
        this.Grip2.func_78789_a(24.0f, -1.0f, -1.0f, 8, 2, 2);
        this.Grip2.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Grip2.func_78787_b(64, 32);
        this.Grip2.field_78809_i = true;
        setRotation(this.Grip2, 0.0f, 1.570796f, 0.0f);
        this.Grip3 = new ModelRenderer(this, 44, 57);
        this.Grip3.func_78789_a(24.0f, -1.0f, -1.0f, 8, 2, 2);
        this.Grip3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Grip3.func_78787_b(64, 32);
        this.Grip3.field_78809_i = true;
        setRotation(this.Grip3, 0.0f, -1.570796f, 0.0f);
        this.Grip4 = new ModelRenderer(this, 44, 57);
        this.Grip4.func_78789_a(24.0f, -1.0f, -1.0f, 8, 2, 2);
        this.Grip4.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Grip4.func_78787_b(64, 32);
        this.Grip4.field_78809_i = true;
        setRotation(this.Grip4, 0.0f, -3.141593f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Base1.func_78785_a(f5);
        this.Base2.func_78785_a(f5);
        this.Base3.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Body3.func_78785_a(f5);
        this.Body4.func_78785_a(f5);
    }

    public void render2(float f) {
        this.Base1.func_78785_a(f);
        this.Base2.func_78785_a(f);
        this.Base3.func_78785_a(f);
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Body3.func_78785_a(f);
        this.Body4.func_78785_a(f);
        this.Grip1.func_78785_a(f);
        this.Grip2.func_78785_a(f);
        this.Grip3.func_78785_a(f);
        this.Grip4.func_78785_a(f);
    }

    public void render3(float f) {
        this.Base1.func_78785_a(f);
        this.Base2.func_78785_a(f);
        this.Base3.func_78785_a(f);
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }

    public void move(float f) {
        setRotation(this.Body1, 0.0f, f, 0.0f);
        setRotation(this.Body2, 0.0f, f, 0.0f);
        setRotation(this.Body3, 0.0f, f, 0.0f);
        setRotation(this.Body4, 0.0f, f + 1.570796f, 0.0f);
        setRotation(this.Grip1, 0.0f, f, 0.0f);
        setRotation(this.Grip2, 0.0f, f + 1.570796f, 0.0f);
        setRotation(this.Grip3, 0.0f, f - 1.570796f, 0.0f);
        setRotation(this.Grip4, 0.0f, f - 3.141593f, 0.0f);
    }
}
