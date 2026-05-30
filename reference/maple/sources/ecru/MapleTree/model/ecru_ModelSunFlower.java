package ecru.MapleTree.model;

import ecru.MapleTree.common.TechneModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ecru_ModelSunFlower extends ModelBase {
    TechneModelRenderer mStem;
    TechneModelRenderer tubularFlower1;
    TechneModelRenderer sStem1;
    TechneModelRenderer leaf1;
    TechneModelRenderer sStem2;
    TechneModelRenderer leaf2;
    TechneModelRenderer mStemTop;
    TechneModelRenderer tubularFlower2;
    TechneModelRenderer sStem3;
    TechneModelRenderer sStem4;
    TechneModelRenderer sStem5;
    TechneModelRenderer sStem6;
    TechneModelRenderer leaf3;
    TechneModelRenderer leaf4;
    TechneModelRenderer leaf5;
    TechneModelRenderer leaf6;
    TechneModelRenderer sStem7;
    TechneModelRenderer leaf7;
    TechneModelRenderer mStem2;
    TechneModelRenderer sStem8;
    TechneModelRenderer leaf8;
    TechneModelRenderer mStem3;
    TechneModelRenderer sStem9;
    TechneModelRenderer sStem10;
    TechneModelRenderer leaf9;
    TechneModelRenderer leaf10;
    TechneModelRenderer ligulateFlower1;
    TechneModelRenderer ligulateFlower2;
    TechneModelRenderer ligulateFlower3;
    TechneModelRenderer ligulateFlower4;
    TechneModelRenderer ligulateFlowerS1;
    TechneModelRenderer ligulateFlowerS2;
    TechneModelRenderer ligulateFlowerS3;
    TechneModelRenderer ligulateFlowerS4;

    public ecru_ModelSunFlower() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.mStem = new TechneModelRenderer(this, 56, 0);
        this.mStem.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 12, 2);
        this.mStem.func_78793_a(0.0f, 12.0f, 0.0f);
        this.mStem.func_78787_b(64, 32);
        this.mStem.field_78809_i = true;
        setRotation(this.mStem, 0.0f, 0.0f, 0.0f);
        this.tubularFlower1 = new TechneModelRenderer(this, 0, 0);
        this.tubularFlower1.func_78789_a(-3.0f, -3.0f, 0.0f, 6, 6, 2);
        this.tubularFlower1.func_78793_a(0.0f, 11.0f, 0.0f);
        this.tubularFlower1.func_78787_b(64, 32);
        this.tubularFlower1.field_78809_i = true;
        setRotation(this.tubularFlower1, 0.2792527f, 0.0f, 0.0f);
        this.leaf1 = new TechneModelRenderer(this, 40, 27);
        this.leaf1.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf1.func_78793_a(2.5f, 18.3f, 0.0f);
        this.leaf1.func_78787_b(64, 32);
        this.leaf1.field_78809_i = true;
        setRotation(this.leaf1, -3.141593f, 0.0f, -0.1570796f);
        this.sStem2 = new TechneModelRenderer(this, 56, 24);
        this.sStem2.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem2.func_78793_a(0.0f, 20.0f, 0.0f);
        this.sStem2.func_78787_b(64, 32);
        this.sStem2.field_78809_i = true;
        setRotation(this.sStem2, 0.0f, 0.0f, -2.583087f);
        this.leaf2 = new TechneModelRenderer(this, 40, 27);
        this.leaf2.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf2.func_78793_a(-2.5f, 18.3f, 0.0f);
        this.leaf2.func_78787_b(64, 32);
        this.leaf2.field_78809_i = true;
        setRotation(this.leaf2, 0.0f, 0.0f, -2.984513f);
        this.mStemTop = new TechneModelRenderer(this, 54, 15);
        this.mStemTop.func_78789_a(-1.5f, -1.5f, -2.0f, 3, 3, 2);
        this.mStemTop.func_78793_a(0.0f, 11.0f, 0.0f);
        this.mStemTop.func_78787_b(64, 32);
        this.mStemTop.field_78809_i = true;
        setRotation(this.mStemTop, 0.2792527f, 0.0f, 0.0f);
        this.sStem3 = new TechneModelRenderer(this, 56, 24);
        this.sStem3.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.sStem3.func_78787_b(64, 32);
        this.sStem3.field_78809_i = true;
        setRotation(this.sStem3, 0.0f, 0.8726646f, -2.583087f);
        this.sStem4 = new TechneModelRenderer(this, 56, 24);
        this.sStem4.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem4.func_78793_a(0.0f, 22.0f, 0.0f);
        this.sStem4.func_78787_b(64, 32);
        this.sStem4.field_78809_i = true;
        setRotation(this.sStem4, 0.0f, 1.867502f, -2.583087f);
        this.sStem5 = new TechneModelRenderer(this, 56, 24);
        this.sStem5.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem5.func_78793_a(0.0f, 16.0f, 0.0f);
        this.sStem5.func_78787_b(64, 32);
        this.sStem5.field_78809_i = true;
        setRotation(this.sStem5, 0.0f, -0.9424778f, -2.583087f);
        this.sStem6 = new TechneModelRenderer(this, 56, 24);
        this.sStem6.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem6.func_78793_a(0.0f, 19.0f, 0.0f);
        this.sStem6.func_78787_b(64, 32);
        this.sStem6.field_78809_i = true;
        setRotation(this.sStem6, 0.0f, -2.059489f, -2.583087f);
        this.leaf3 = new TechneModelRenderer(this, 40, 27);
        this.leaf3.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf3.func_78793_a(-1.6f, 9.3f, 1.8f);
        this.leaf3.func_78787_b(64, 32);
        this.leaf3.field_78809_i = true;
        setRotation(this.leaf3, 0.0f, 0.8726646f, -2.984513f);
        this.leaf5 = new TechneModelRenderer(this, 40, 27);
        this.leaf5.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf5.func_78793_a(-1.4f, 14.3f, -2.0f);
        this.leaf5.func_78787_b(64, 32);
        this.leaf5.field_78809_i = true;
        setRotation(this.leaf5, 0.0f, -0.9424778f, -2.984513f);
        this.leaf6 = new TechneModelRenderer(this, 40, 27);
        this.leaf6.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf6.func_78793_a(1.1f, 17.3f, -2.2f);
        this.leaf6.func_78787_b(64, 32);
        this.leaf6.field_78809_i = true;
        setRotation(this.leaf6, 0.0f, -2.059489f, -2.984513f);
        this.leaf4 = new TechneModelRenderer(this, 40, 27);
        this.leaf4.func_78789_a(0.0f, 1.0f, -2.0f, 8, 1, 4);
        this.leaf4.func_78793_a(0.8f, 22.2f, 2.8f);
        this.leaf4.func_78787_b(64, 32);
        this.leaf4.field_78809_i = true;
        setRotation(this.leaf4, 0.0f, 1.867502f, -2.984513f);
        this.sStem7 = new TechneModelRenderer(this, 56, 24);
        this.sStem7.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem7.func_78793_a(0.0f, 15.0f, 0.0f);
        this.sStem7.func_78787_b(64, 32);
        this.sStem7.field_78809_i = true;
        setRotation(this.sStem7, 0.0f, 2.356194f, -2.583087f);
        this.leaf7 = new TechneModelRenderer(this, 40, 27);
        this.leaf7.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf7.func_78793_a(1.8f, 13.3f, 1.8f);
        this.leaf7.func_78787_b(64, 32);
        this.leaf7.field_78809_i = true;
        setRotation(this.leaf7, 0.0f, 2.356194f, -2.984513f);
        this.mStem2 = new TechneModelRenderer(this, 45, 0);
        this.mStem2.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 16, 2);
        this.mStem2.func_78793_a(0.0f, 8.0f, 0.0f);
        this.mStem2.func_78787_b(64, 32);
        this.mStem2.field_78809_i = true;
        setRotation(this.mStem2, 0.0f, 0.0f, 0.0f);
        this.sStem8 = new TechneModelRenderer(this, 56, 24);
        this.sStem8.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem8.func_78793_a(0.0f, 10.0f, 0.0f);
        this.sStem8.func_78787_b(64, 32);
        this.sStem8.field_78809_i = true;
        setRotation(this.sStem8, 0.0f, -2.443461f, -2.583087f);
        this.leaf8 = new TechneModelRenderer(this, 40, 27);
        this.leaf8.func_78789_a(0.0f, -1.0f, -2.0f, 8, 1, 4);
        this.leaf8.func_78793_a(2.1f, 8.5f, -1.5f);
        this.leaf8.func_78787_b(64, 32);
        this.leaf8.field_78809_i = true;
        setRotation(this.leaf8, 0.0f, -2.443461f, -2.984513f);
        this.sStem1 = new TechneModelRenderer(this, 56, 24);
        this.sStem1.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem1.func_78793_a(0.0f, 20.0f, 0.0f);
        this.sStem1.func_78787_b(64, 32);
        this.sStem1.field_78809_i = true;
        setRotation(this.sStem1, 0.0f, -3.141593f, -2.583087f);
        this.tubularFlower2 = new TechneModelRenderer(this, 17, 0);
        this.tubularFlower2.func_78789_a(-3.0f, -3.0f, 0.0f, 6, 6, 2);
        this.tubularFlower2.func_78793_a(0.0f, 11.0f, 0.0f);
        this.tubularFlower2.func_78787_b(64, 32);
        this.tubularFlower2.field_78809_i = true;
        setRotation(this.tubularFlower2, 0.2792527f, 0.0f, 0.0f);
        this.mStem3 = new TechneModelRenderer(this, 40, 0);
        this.mStem3.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 3, 1);
        this.mStem3.func_78793_a(0.0f, 21.0f, 0.0f);
        this.mStem3.func_78787_b(64, 32);
        this.mStem3.field_78809_i = true;
        setRotation(this.mStem3, 0.0f, 0.0f, 0.0f);
        this.sStem9 = new TechneModelRenderer(this, 56, 24);
        this.sStem9.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem9.func_78793_a(0.0f, 21.0f, 0.0f);
        this.sStem9.func_78787_b(64, 32);
        this.sStem9.field_78809_i = true;
        setRotation(this.sStem9, 0.0f, -3.141593f, -2.583087f);
        this.sStem10 = new TechneModelRenderer(this, 56, 24);
        this.sStem10.func_78789_a(0.0f, -1.0f, -0.5f, 3, 1, 1);
        this.sStem10.func_78793_a(0.0f, 21.0f, 0.0f);
        this.sStem10.func_78787_b(64, 32);
        this.sStem10.field_78809_i = true;
        setRotation(this.sStem10, 0.0f, 0.0f, -2.583087f);
        this.leaf9 = new TechneModelRenderer(this, 24, 29);
        this.leaf9.func_78789_a(0.0f, 0.0f, -1.0f, 4, 1, 2);
        this.leaf9.func_78793_a(2.6f, 20.4f, 0.0f);
        this.leaf9.func_78787_b(64, 32);
        this.leaf9.field_78809_i = true;
        setRotation(this.leaf9, -3.141593f, 0.0f, -0.1570796f);
        this.leaf10 = new TechneModelRenderer(this, 24, 29);
        this.leaf10.func_78789_a(0.0f, 0.0f, -1.0f, 4, 1, 2);
        this.leaf10.func_78793_a(-2.6f, 20.4f, 0.0f);
        this.leaf10.func_78787_b(64, 32);
        this.leaf10.field_78809_i = true;
        setRotation(this.leaf10, 0.0f, 0.0f, -2.984513f);
        this.ligulateFlower1 = new TechneModelRenderer(this, 0, 9);
        this.ligulateFlower1.func_78789_a(-6.0f, 0.5f, 0.5f, 12, 2, 1);
        this.ligulateFlower1.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlower1.func_78787_b(64, 32);
        this.ligulateFlower1.field_78809_i = true;
        setRotation(this.ligulateFlower1, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlower2 = new TechneModelRenderer(this, 0, 9);
        this.ligulateFlower2.func_78789_a(-6.0f, -2.5f, 0.5f, 12, 2, 1);
        this.ligulateFlower2.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlower2.func_78787_b(64, 32);
        this.ligulateFlower2.field_78809_i = true;
        setRotation(this.ligulateFlower2, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlower3 = new TechneModelRenderer(this, 0, 15);
        this.ligulateFlower3.func_78789_a(0.5f, -6.0f, 0.5f, 2, 12, 1);
        this.ligulateFlower3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlower3.func_78787_b(64, 32);
        this.ligulateFlower3.field_78809_i = true;
        setRotation(this.ligulateFlower3, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlower4 = new TechneModelRenderer(this, 0, 15);
        this.ligulateFlower4.func_78789_a(-2.5f, -6.0f, 0.5f, 2, 12, 1);
        this.ligulateFlower4.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlower4.func_78787_b(64, 32);
        this.ligulateFlower4.field_78809_i = true;
        setRotation(this.ligulateFlower4, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlowerS1 = new TechneModelRenderer(this, 10, 15);
        this.ligulateFlowerS1.func_78789_a(3.0f, -5.0f, 0.5f, 2, 2, 1);
        this.ligulateFlowerS1.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlowerS1.func_78787_b(64, 32);
        this.ligulateFlowerS1.field_78809_i = true;
        setRotation(this.ligulateFlowerS1, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlowerS2 = new TechneModelRenderer(this, 10, 15);
        this.ligulateFlowerS2.func_78789_a(-5.0f, -5.0f, 0.5f, 2, 2, 1);
        this.ligulateFlowerS2.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlowerS2.func_78787_b(64, 32);
        this.ligulateFlowerS2.field_78809_i = true;
        setRotation(this.ligulateFlowerS2, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlowerS3 = new TechneModelRenderer(this, 10, 15);
        this.ligulateFlowerS3.func_78789_a(3.0f, 3.0f, 0.5f, 2, 2, 1);
        this.ligulateFlowerS3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlowerS3.func_78787_b(64, 32);
        this.ligulateFlowerS3.field_78809_i = true;
        setRotation(this.ligulateFlowerS3, 0.2792527f, 0.0f, 0.0f);
        this.ligulateFlowerS4 = new TechneModelRenderer(this, 10, 15);
        this.ligulateFlowerS4.func_78789_a(-5.0f, 3.0f, 0.5f, 2, 2, 1);
        this.ligulateFlowerS4.func_78793_a(0.0f, 11.0f, 0.0f);
        this.ligulateFlowerS4.func_78787_b(64, 32);
        this.ligulateFlowerS4.field_78809_i = true;
        setRotation(this.ligulateFlowerS4, 0.2792527f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.mStem.func_78785_a(f5);
        this.tubularFlower1.func_78785_a(f5);
        this.sStem1.func_78785_a(f5);
        this.leaf1.func_78785_a(f5);
        this.sStem2.func_78785_a(f5);
        this.leaf2.func_78785_a(f5);
        this.mStemTop.func_78785_a(f5);
        this.tubularFlower2.func_78785_a(f5);
        this.sStem3.func_78785_a(f5);
        this.sStem4.func_78785_a(f5);
        this.sStem5.func_78785_a(f5);
        this.sStem6.func_78785_a(f5);
        this.leaf3.func_78785_a(f5);
        this.leaf4.func_78785_a(f5);
        this.leaf5.func_78785_a(f5);
        this.leaf6.func_78785_a(f5);
        this.sStem7.func_78785_a(f5);
        this.leaf7.func_78785_a(f5);
    }

    public void render3(float f, int meta) {
        this.mStem.func_78785_a(f);
        this.sStem1.func_78785_a(f);
        this.leaf1.func_78785_a(f);
        this.sStem2.func_78785_a(f);
        this.leaf2.func_78785_a(f);
        this.mStemTop.func_78785_a(f);
        this.ligulateFlower1.func_78785_a(f);
        this.ligulateFlower2.func_78785_a(f);
        this.ligulateFlower3.func_78785_a(f);
        this.ligulateFlower4.func_78785_a(f);
        this.ligulateFlowerS1.func_78785_a(f);
        this.ligulateFlowerS2.func_78785_a(f);
        this.ligulateFlowerS3.func_78785_a(f);
        this.ligulateFlowerS4.func_78785_a(f);
        if ((meta & 7) <= 4) {
            this.tubularFlower1.func_78785_a(f);
        } else {
            this.tubularFlower2.func_78785_a(f);
        }
    }

    public void render2(float f) {
        this.mStem2.func_78785_a(f);
        this.sStem1.func_78785_a(f);
        this.leaf1.func_78785_a(f);
        this.sStem2.func_78785_a(f);
        this.leaf2.func_78785_a(f);
        this.sStem3.func_78785_a(f);
        this.sStem4.func_78785_a(f);
        this.sStem5.func_78785_a(f);
        this.sStem6.func_78785_a(f);
        this.leaf3.func_78785_a(f);
        this.leaf4.func_78785_a(f);
        this.leaf5.func_78785_a(f);
        this.leaf6.func_78785_a(f);
        this.sStem7.func_78785_a(f);
        this.leaf7.func_78785_a(f);
        this.sStem8.func_78785_a(f);
        this.leaf8.func_78785_a(f);
    }

    public void render1(float f) {
        this.mStem2.func_78785_a(f);
        this.sStem3.func_78785_a(f);
        this.sStem5.func_78785_a(f);
        this.sStem7.func_78785_a(f);
        this.leaf3.func_78785_a(f);
        this.leaf5.func_78785_a(f);
        this.leaf7.func_78785_a(f);
        this.sStem8.func_78785_a(f);
        this.leaf8.func_78785_a(f);
    }

    public void render0(float f) {
        this.mStem3.func_78785_a(f);
        this.sStem9.func_78785_a(f);
        this.leaf9.func_78785_a(f);
        this.sStem10.func_78785_a(f);
        this.leaf10.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, par7Entity);
    }

    public void moveFlower(float f, int meta) {
        if ((meta & 7) <= 4) {
            setRotation(this.tubularFlower1, f, 0.0f, 0.0f);
        } else {
            setRotation(this.tubularFlower2, f, 0.0f, 0.0f);
        }
        setRotation(this.mStemTop, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlower1, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlower2, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlower3, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlower4, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlowerS1, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlowerS2, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlowerS3, f, 0.0f, 0.0f);
        setRotation(this.ligulateFlowerS4, f, 0.0f, 0.0f);
    }

    public void moveLeaf(float f, int m) {
        switch (m) {
            case 1:
                setRotation(this.leaf1, -3.141593f, 0.0f, (-0.1570796f) + f);
                break;
            case 2:
                setRotation(this.leaf2, 0.0f, 0.0f, (-2.984513f) + f);
                break;
            case 3:
                setRotation(this.leaf3, 0.0f, 0.8726646f, (-2.984513f) + f);
                break;
            case 4:
                setRotation(this.leaf4, 0.0f, 1.867502f, (-2.984513f) + f);
                break;
            case 5:
                setRotation(this.leaf5, 0.0f, -0.9424778f, (-2.984513f) + f);
                break;
            case 6:
                setRotation(this.leaf6, 0.0f, -2.059489f, (-2.984513f) + f);
                break;
            case 7:
                setRotation(this.leaf7, 0.0f, 2.356194f, (-2.984513f) + f);
                break;
            case 8:
                setRotation(this.leaf8, 0.0f, -2.443461f, (-2.984513f) + f);
                break;
        }
    }
}
