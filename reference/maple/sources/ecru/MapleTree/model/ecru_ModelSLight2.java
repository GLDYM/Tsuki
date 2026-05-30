package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelSLight2 extends ModelBase {
    ModelRenderer Light;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Body0;
    ModelRenderer Base1;
    ModelRenderer Base2;

    public ecru_ModelSLight2() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Light = new ModelRenderer(this, 0, 19);
        this.Light.func_78789_a(-1.0f, 4.0f, -1.0f, 2, 3, 2);
        this.Light.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Light.func_78787_b(64, 32);
        this.Light.field_78809_i = true;
        setRotation(this.Light, 0.0f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer(this, 0, 0);
        this.Body1.func_78789_a(-3.0f, 4.0f, -3.0f, 1, 4, 6);
        this.Body1.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body1.func_78787_b(64, 32);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 15, 0);
        this.Body2.func_78789_a(2.0f, 4.0f, -3.0f, 1, 4, 6);
        this.Body2.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body2.func_78787_b(64, 32);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer(this, 0, 12);
        this.Body3.func_78789_a(-2.0f, 4.0f, -3.0f, 4, 4, 1);
        this.Body3.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body3.func_78787_b(64, 32);
        this.Body3.field_78809_i = true;
        setRotation(this.Body3, 0.0f, 0.0f, 0.0f);
        this.Body4 = new ModelRenderer(this, 11, 12);
        this.Body4.func_78789_a(-2.0f, 4.0f, 2.0f, 4, 4, 1);
        this.Body4.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body4.func_78787_b(64, 32);
        this.Body4.field_78809_i = true;
        setRotation(this.Body4, 0.0f, 0.0f, 0.0f);
        this.Body0 = new ModelRenderer(this, 0, 26);
        this.Body0.func_78789_a(-2.5f, 3.0f, -2.5f, 5, 1, 5);
        this.Body0.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body0.func_78787_b(64, 32);
        this.Body0.field_78809_i = true;
        setRotation(this.Body0, 0.0f, 0.0f, 0.0f);
        this.Base1 = new ModelRenderer(this, 52, 6);
        this.Base1.func_78789_a(-1.5f, 0.0f, -1.5f, 3, 1, 3);
        this.Base1.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Base1.func_78787_b(64, 32);
        this.Base1.field_78809_i = true;
        setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.Base2 = new ModelRenderer(this, 60, 0);
        this.Base2.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 3, 1);
        this.Base2.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Base2.func_78787_b(64, 32);
        this.Base2.field_78809_i = true;
        setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Light.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Body3.func_78785_a(f5);
        this.Body4.func_78785_a(f5);
        this.Body0.func_78785_a(f5);
        this.Base1.func_78785_a(f5);
        this.Base2.func_78785_a(f5);
    }

    public void render2(float f) {
        this.Base1.func_78785_a(f);
        this.Light.func_78785_a(f);
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Body3.func_78785_a(f);
        this.Body4.func_78785_a(f);
        this.Body0.func_78785_a(f);
        this.Base2.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }

    public void moveLight(float f, int m) {
        switch (m) {
            case 0:
            default:
                setRotation(this.Body0, -0.0f, 0.0f, 0.0f + f);
                setRotation(this.Body1, -0.0f, 0.0f, 0.0f + f);
                setRotation(this.Body2, -0.0f, 0.0f, 0.0f + f);
                setRotation(this.Body3, -0.0f, 0.0f, 0.0f + f);
                setRotation(this.Body4, -0.0f, 0.0f, 0.0f + f);
                setRotation(this.Light, -0.0f, 0.0f, 0.0f + f);
                setRotation(this.Base2, -0.0f, 0.0f, 0.0f + f);
                break;
            case 1:
                setRotation(this.Body0, (-0.0f) + f, 0.0f, 0.0f);
                setRotation(this.Body1, (-0.0f) + f, 0.0f, 0.0f);
                setRotation(this.Body2, (-0.0f) + f, 0.0f, 0.0f);
                setRotation(this.Body3, (-0.0f) + f, 0.0f, 0.0f);
                setRotation(this.Body4, (-0.0f) + f, 0.0f, 0.0f);
                setRotation(this.Light, (-0.0f) + f, 0.0f, 0.0f);
                setRotation(this.Base2, (-0.0f) + f, 0.0f, 0.0f);
                break;
            case 2:
                setRotation(this.Body0, (-0.0f) + f, 0.0f, 0.0f + f);
                setRotation(this.Body1, (-0.0f) + f, 0.0f, 0.0f + f);
                setRotation(this.Body2, (-0.0f) + f, 0.0f, 0.0f + f);
                setRotation(this.Body3, (-0.0f) + f, 0.0f, 0.0f + f);
                setRotation(this.Body4, (-0.0f) + f, 0.0f, 0.0f + f);
                setRotation(this.Light, (-0.0f) + f, 0.0f, 0.0f + f);
                setRotation(this.Base2, (-0.0f) + f, 0.0f, 0.0f + f);
                break;
        }
    }
}
