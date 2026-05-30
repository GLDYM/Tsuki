package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelStoneMortar extends ModelBase {
    ModelRenderer Body1;
    ModelRenderer Power;
    ModelRenderer Body2;
    ModelRenderer Top;
    ModelRenderer Side3;
    ModelRenderer Side1;
    ModelRenderer Side2;

    public ecru_ModelStoneMortar() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer(this, 0, 0);
        this.Body1.func_78789_a(-5.5f, 0.0f, -5.5f, 11, 6, 11);
        this.Body1.func_78793_a(0.0f, 17.0f, 0.0f);
        this.Body1.func_78787_b(128, 64);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Power = new ModelRenderer(this, 0, 56);
        this.Power.func_78789_a(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Power.func_78793_a(7.5f, 19.0f, 0.0f);
        this.Power.func_78787_b(128, 64);
        this.Power.field_78809_i = true;
        setRotation(this.Power, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 0, 38);
        this.Body2.func_78789_a(-4.5f, 0.0f, -4.5f, 9, 1, 9);
        this.Body2.func_78793_a(0.0f, 23.0f, 0.0f);
        this.Body2.func_78787_b(128, 64);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
        this.Top = new ModelRenderer(this, 0, 18);
        this.Top.func_78789_a(-5.5f, -4.0f, -5.5f, 11, 8, 11);
        this.Top.func_78793_a(0.0f, 13.0f, 0.0f);
        this.Top.func_78787_b(128, 64);
        this.Top.field_78809_i = true;
        setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        this.Side3 = new ModelRenderer(this, 0, 49);
        this.Side3.func_78789_a(0.0f, -1.5f, -1.5f, 1, 3, 3);
        this.Side3.func_78793_a(5.5f, 19.0f, 0.0f);
        this.Side3.func_78787_b(128, 64);
        this.Side3.field_78809_i = true;
        setRotation(this.Side3, 0.0f, 0.0f, 0.0f);
        this.Side1 = new ModelRenderer(this, 9, 49);
        this.Side1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.Side1.func_78793_a(-2.5f, 19.0f, -6.5f);
        this.Side1.func_78787_b(128, 64);
        this.Side1.field_78809_i = true;
        setRotation(this.Side1, 0.0f, 0.0f, 0.0f);
        this.Side2 = new ModelRenderer(this, 22, 49);
        this.Side2.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.Side2.func_78793_a(-2.5f, 19.0f, 5.5f);
        this.Side2.func_78787_b(128, 64);
        this.Side2.field_78809_i = true;
        setRotation(this.Side2, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.Power.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Top.func_78785_a(f5);
        this.Side3.func_78785_a(f5);
        this.Side1.func_78785_a(f5);
        this.Side2.func_78785_a(f5);
    }

    public void render2(float f) {
        this.Body1.func_78785_a(f);
        this.Power.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Top.func_78785_a(f);
        this.Side3.func_78785_a(f);
        this.Side1.func_78785_a(f);
        this.Side2.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setTop(float f) {
        setRotation(this.Top, 0.0f, f, 0.0f);
    }

    public void setPower(float f) {
        setRotation(this.Power, f, 0.0f, 0.0f);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }
}
