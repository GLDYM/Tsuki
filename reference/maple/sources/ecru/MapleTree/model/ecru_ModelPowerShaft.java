package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelPowerShaft extends ModelBase {
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;

    public ecru_ModelPowerShaft() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Body1 = new ModelRenderer(this, 0, 0);
        this.Body1.func_78789_a(-2.0f, -7.5f, -2.0f, 4, 15, 4);
        this.Body1.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Body1.func_78787_b(64, 32);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 18, 8);
        this.Body2.func_78789_a(-2.5f, 7.0f, -2.5f, 5, 1, 5);
        this.Body2.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Body2.func_78787_b(64, 32);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer(this, 18, 0);
        this.Body3.func_78789_a(-2.5f, -8.0f, -2.5f, 5, 1, 5);
        this.Body3.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Body3.func_78787_b(64, 32);
        this.Body3.field_78809_i = true;
        setRotation(this.Body3, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Body3.func_78785_a(f5);
    }

    public void render2(float f) {
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Body3.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setShaft(float f) {
        setRotation(this.Body1, 0.0f, f, 0.0f);
        setRotation(this.Body2, 0.0f, f, 0.0f);
        setRotation(this.Body3, 0.0f, f, 0.0f);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }
}
