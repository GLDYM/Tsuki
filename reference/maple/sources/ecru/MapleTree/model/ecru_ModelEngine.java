package ecru.MapleTree.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelEngine extends ModelBase {
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Top2;
    ModelRenderer Top1;
    ModelRenderer Power1;
    ModelRenderer Power2;
    ModelRenderer Arm1;
    ModelRenderer Arm3;
    ModelRenderer Arm4;
    ModelRenderer Arm5;
    ModelRenderer Arm6;
    ModelRenderer Arm2;
    ModelRenderer Top2a;
    ModelRenderer Top1a;

    public ecru_ModelEngine() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer(this, 0, 0);
        this.Body1.func_78789_a(-7.0f, 0.0f, -6.0f, 14, 2, 12);
        this.Body1.func_78793_a(0.0f, 22.0f, 0.0f);
        this.Body1.func_78787_b(64, 32);
        this.Body1.field_78809_i = true;
        setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 0, 17);
        this.Body2.func_78789_a(-7.0f, 0.0f, -4.0f, 14, 7, 8);
        this.Body2.func_78793_a(0.0f, 15.0f, 0.0f);
        this.Body2.func_78787_b(64, 32);
        this.Body2.field_78809_i = true;
        setRotation(this.Body2, 0.0f, 0.0f, 0.0f);
        this.Top2 = new ModelRenderer(this, 0, 33);
        this.Top2.func_78789_a(-7.0f, 0.0f, -2.0f, 15, 7, 4);
        this.Top2.func_78793_a(-0.5f, 11.0f, 7.0f);
        this.Top2.func_78787_b(64, 32);
        this.Top2.field_78809_i = true;
        setRotation(this.Top2, -0.6981317f, 0.0f, 0.0f);
        this.Top1 = new ModelRenderer(this, 0, 45);
        this.Top1.func_78789_a(-7.0f, 0.0f, -2.0f, 15, 7, 4);
        this.Top1.func_78793_a(-0.5f, 11.0f, -7.0f);
        this.Top1.func_78787_b(64, 32);
        this.Top1.field_78809_i = true;
        setRotation(this.Top1, 0.6981317f, 0.0f, 0.0f);
        this.Power1 = new ModelRenderer(this, 41, 33);
        this.Power1.func_78789_a(-1.0f, -2.5f, -2.5f, 1, 5, 5);
        this.Power1.func_78793_a(-7.0f, 19.0f, 0.0f);
        this.Power1.func_78787_b(64, 32);
        this.Power1.field_78809_i = true;
        setRotation(this.Power1, 0.0f, 0.0f, 0.0f);
        this.Power2 = new ModelRenderer(this, 41, 45);
        this.Power2.func_78789_a(0.0f, -2.5f, -2.5f, 1, 5, 5);
        this.Power2.func_78793_a(7.0f, 19.0f, 0.0f);
        this.Power2.func_78787_b(64, 32);
        this.Power2.field_78809_i = true;
        setRotation(this.Power2, 0.0f, 0.0f, 0.0f);
        this.Arm1 = new ModelRenderer(this, 45, 17);
        this.Arm1.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Arm1.func_78793_a(-5.0f, 9.0f, -9.0f);
        this.Arm1.func_78787_b(64, 32);
        this.Arm1.field_78809_i = true;
        setRotation(this.Arm1, 0.6981317f, 0.0f, 0.0f);
        this.Arm3 = new ModelRenderer(this, 45, 17);
        this.Arm3.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Arm3.func_78793_a(5.0f, 9.0f, -9.0f);
        this.Arm3.func_78787_b(64, 32);
        this.Arm3.field_78809_i = true;
        setRotation(this.Arm3, 0.6981317f, 0.0f, 0.0f);
        this.Arm4 = new ModelRenderer(this, 45, 17);
        this.Arm4.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Arm4.func_78793_a(-5.0f, 9.0f, 9.0f);
        this.Arm4.func_78787_b(64, 32);
        this.Arm4.field_78809_i = true;
        setRotation(this.Arm4, -0.6981317f, 0.0f, 0.0f);
        this.Arm5 = new ModelRenderer(this, 45, 17);
        this.Arm5.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Arm5.func_78793_a(0.0f, 9.0f, 9.0f);
        this.Arm5.func_78787_b(64, 32);
        this.Arm5.field_78809_i = true;
        setRotation(this.Arm5, -0.6981317f, 0.0f, 0.0f);
        this.Arm6 = new ModelRenderer(this, 45, 17);
        this.Arm6.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Arm6.func_78793_a(5.0f, 9.0f, 9.0f);
        this.Arm6.func_78787_b(64, 32);
        this.Arm6.field_78809_i = true;
        setRotation(this.Arm6, -0.6981317f, 0.0f, 0.0f);
        this.Arm2 = new ModelRenderer(this, 45, 17);
        this.Arm2.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Arm2.func_78793_a(0.0f, 9.0f, -9.0f);
        this.Arm2.func_78787_b(64, 32);
        this.Arm2.field_78809_i = true;
        setRotation(this.Arm2, 0.6981317f, 0.0f, 0.0f);
        this.Top2a = new ModelRenderer(this, 57, 0);
        this.Top2a.func_78789_a(-8.0f, 0.0f, -2.0f, 16, 4, 6);
        this.Top2a.func_78793_a(0.0f, 10.0f, 7.0f);
        this.Top2a.func_78787_b(64, 32);
        this.Top2a.field_78809_i = true;
        setRotation(this.Top2a, -0.6981317f, 0.0f, 0.0f);
        this.Top1a = new ModelRenderer(this, 57, 11);
        this.Top1a.func_78789_a(-8.0f, 0.0f, -4.0f, 16, 4, 6);
        this.Top1a.func_78793_a(0.0f, 10.0f, -7.0f);
        this.Top1a.func_78787_b(64, 32);
        this.Top1a.field_78809_i = true;
        setRotation(this.Top1a, 0.6981317f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.Body2.func_78785_a(f5);
        this.Top2.func_78785_a(f5);
        this.Top1.func_78785_a(f5);
        this.Power1.func_78785_a(f5);
        this.Power2.func_78785_a(f5);
        this.Arm1.func_78785_a(f5);
        this.Arm3.func_78785_a(f5);
        this.Arm4.func_78785_a(f5);
        this.Arm5.func_78785_a(f5);
        this.Arm6.func_78785_a(f5);
        this.Arm2.func_78785_a(f5);
        this.Top2a.func_78785_a(f5);
        this.Top1a.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }

    public void render2(float f) {
        this.Body1.func_78785_a(f);
        this.Body2.func_78785_a(f);
        this.Top2.func_78785_a(f);
        this.Top1.func_78785_a(f);
        this.Power1.func_78785_a(f);
        this.Power2.func_78785_a(f);
        this.Arm1.func_78785_a(f);
        this.Arm3.func_78785_a(f);
        this.Arm4.func_78785_a(f);
        this.Arm5.func_78785_a(f);
        this.Arm6.func_78785_a(f);
        this.Arm2.func_78785_a(f);
        this.Top2a.func_78785_a(f);
        this.Top1a.func_78785_a(f);
    }

    public void setPower(float f) {
        setRotation(this.Power1, f, 0.0f, 0.0f);
        setRotation(this.Power2, 6.2831855f - f, 0.0f, 0.0f);
    }

    public void setArm1(int no, float f, float f1) {
        switch (no) {
            case 1:
                this.Arm1.field_78797_d = f + f1;
                this.Arm1.field_78798_e = (f * 0.84444445f) - f1;
                break;
            case 2:
                this.Arm2.field_78797_d = f + f1;
                this.Arm2.field_78798_e = (f * 0.84444445f) - f1;
                break;
            case 3:
                this.Arm3.field_78797_d = f + f1;
                this.Arm3.field_78798_e = (f * 0.84444445f) - f1;
                break;
            case 4:
                this.Arm4.field_78797_d = f + f1;
                this.Arm4.field_78798_e = (-(f * 0.84444445f)) + f1;
                break;
            case 5:
                this.Arm5.field_78797_d = f + f1;
                this.Arm5.field_78798_e = (-(f * 0.84444445f)) + f1;
                break;
            case 6:
                this.Arm6.field_78797_d = f + f1;
                this.Arm6.field_78798_e = (-(f * 0.84444445f)) + f1;
                break;
        }
    }
}
