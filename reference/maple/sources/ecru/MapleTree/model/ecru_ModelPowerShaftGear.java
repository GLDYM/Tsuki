package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelPowerShaftGear extends ModelBase {
    ModelRenderer Base1;
    ModelRenderer Base2;
    ModelRenderer Base3;
    ModelRenderer Base4;
    ModelRenderer Body1;

    public ecru_ModelPowerShaftGear() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Base1 = new ModelRenderer(this, 46, 0);
        this.Base1.func_78789_a(-3.0f, -8.0f, -3.0f, 6, 14, 6);
        this.Base1.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Base1.func_78787_b(128, 64);
        this.Base1.field_78809_i = true;
        setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.Base2 = new ModelRenderer(this, 0, 30);
        this.Base2.func_78789_a(-8.0f, -3.0f, -3.0f, 16, 6, 6);
        this.Base2.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Base2.func_78787_b(128, 64);
        this.Base2.field_78809_i = true;
        setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
        this.Base3 = new ModelRenderer(this, 0, 0);
        this.Base3.func_78789_a(-3.0f, -3.0f, -8.0f, 6, 6, 16);
        this.Base3.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Base3.func_78787_b(128, 64);
        this.Base3.field_78809_i = true;
        setRotation(this.Base3, 0.0f, 0.0f, 0.0f);
        this.Base4 = new ModelRenderer(this, 0, 44);
        this.Base4.func_78789_a(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.Base4.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Base4.func_78787_b(128, 64);
        this.Base4.field_78809_i = true;
        setRotation(this.Base4, 0.0f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer(this, 88, 0);
        this.Body1.func_78789_a(-5.0f, 0.0f, -5.0f, 10, 2, 10);
        this.Body1.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body1.func_78787_b(128, 64);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Base1.func_78785_a(f5);
        this.Base2.func_78785_a(f5);
        this.Base3.func_78785_a(f5);
        this.Base4.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
    }

    public void render2(float f) {
        this.Base1.func_78785_a(f);
        this.Base2.func_78785_a(f);
        this.Base3.func_78785_a(f);
        this.Base4.func_78785_a(f);
        this.Body1.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setShaftGear(float f) {
        setRotation(this.Body1, 0.0f, f, 0.0f);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }
}
