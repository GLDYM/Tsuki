package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelBiofuelPD extends ModelBase {
    ModelRenderer Base;
    ModelRenderer BaseTop;
    ModelRenderer BaseBottom;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer Body7;

    public ecru_ModelBiofuelPD() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Base = new ModelRenderer(this, 55, 22);
        this.Base.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 15, 4);
        this.Base.func_78793_a(0.0f, 8.5f, 0.0f);
        this.Base.func_78787_b(128, 64);
        this.Base.field_78809_i = true;
        setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.BaseTop = new ModelRenderer(this, 0, 0);
        this.BaseTop.func_78789_a(-6.0f, -1.0f, -6.0f, 12, 1, 12);
        this.BaseTop.func_78793_a(0.0f, 9.0f, 0.0f);
        this.BaseTop.func_78787_b(128, 64);
        this.BaseTop.field_78809_i = true;
        setRotation(this.BaseTop, 0.0f, 0.0f, 0.0f);
        this.BaseBottom = new ModelRenderer(this, 48, 0);
        this.BaseBottom.func_78789_a(-5.0f, -1.0f, -5.0f, 10, 1, 10);
        this.BaseBottom.func_78793_a(0.0f, 24.0f, 0.0f);
        this.BaseBottom.func_78787_b(128, 64);
        this.BaseBottom.field_78809_i = true;
        setRotation(this.BaseBottom, 0.0f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer(this, 76, 49);
        this.Body1.func_78789_a(-6.5f, -1.0f, -6.5f, 13, 2, 13);
        this.Body1.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body1.func_78787_b(128, 64);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 88, 36);
        this.Body2.func_78789_a(-5.0f, -1.0f, -5.0f, 10, 2, 10);
        this.Body2.func_78793_a(0.0f, 20.0f, 0.0f);
        this.Body2.func_78787_b(128, 64);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer(this, 84, 22);
        this.Body3.func_78789_a(-5.5f, -1.0f, -5.5f, 11, 2, 11);
        this.Body3.func_78793_a(0.0f, 18.0f, 0.0f);
        this.Body3.func_78787_b(128, 64);
        this.Body3.field_78809_i = true;
        setRotation(this.Body3, 0.0f, 0.0f, 0.0f);
        this.Body4 = new ModelRenderer(this, 88, 9);
        this.Body4.func_78789_a(-5.0f, -1.0f, -5.0f, 10, 2, 10);
        this.Body4.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Body4.func_78787_b(128, 64);
        this.Body4.field_78809_i = true;
        setRotation(this.Body4, 0.0f, 0.0f, 0.0f);
        this.Body5 = new ModelRenderer(this, 0, 51);
        this.Body5.func_78789_a(-5.5f, -1.0f, -5.5f, 11, 2, 11);
        this.Body5.func_78793_a(0.0f, 14.0f, 0.0f);
        this.Body5.func_78787_b(128, 64);
        this.Body5.field_78809_i = true;
        setRotation(this.Body5, 0.0f, 0.0f, 0.0f);
        this.Body6 = new ModelRenderer(this, 0, 38);
        this.Body6.func_78789_a(-5.0f, -1.0f, -5.0f, 10, 2, 10);
        this.Body6.func_78793_a(0.0f, 12.0f, 0.0f);
        this.Body6.func_78787_b(128, 64);
        this.Body6.field_78809_i = true;
        setRotation(this.Body6, 0.0f, 0.0f, 0.0f);
        this.Body7 = new ModelRenderer(this, 0, 22);
        this.Body7.func_78789_a(-6.5f, -1.0f, -6.5f, 13, 2, 13);
        this.Body7.func_78793_a(0.0f, 10.0f, 0.0f);
        this.Body7.func_78787_b(128, 64);
        this.Body7.field_78809_i = true;
        setRotation(this.Body7, 0.0f, 0.0f, 0.0f);
    }

    public void render2(float f) {
        this.Base.func_78785_a(f);
        this.BaseTop.func_78785_a(f);
        this.BaseBottom.func_78785_a(f);
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Body3.func_78785_a(f);
        this.Body4.func_78785_a(f);
        this.Body5.func_78785_a(f);
        this.Body6.func_78785_a(f);
        this.Body7.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }

    public void move(int num, float f) {
        switch (num) {
            case 1:
                setRotation(this.Body1, 0.0f, f, 0.0f);
                break;
            case 2:
                setRotation(this.Body2, 0.0f, f, 0.0f);
                break;
            case 3:
                setRotation(this.Body3, 0.0f, f, 0.0f);
                break;
            case 4:
                setRotation(this.Body4, 0.0f, f, 0.0f);
                break;
            case 5:
                setRotation(this.Body5, 0.0f, f, 0.0f);
                break;
            case 6:
                setRotation(this.Body6, 0.0f, f, 0.0f);
                break;
            case 7:
                setRotation(this.Body7, 0.0f, f, 0.0f);
                break;
        }
    }
}
