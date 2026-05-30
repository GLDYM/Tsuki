package ecru.MapleTree.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class TechneModelRenderer extends ModelRenderer {
    private int displayList;
    private boolean compiled;

    public TechneModelRenderer(ModelBase par1ModelBase, int par2, int par3) {
        super(par1ModelBase);
        func_78784_a(par2, par3);
    }

    @SideOnly(Side.CLIENT)
    public void func_78785_a(float par1) {
        if (!this.field_78807_k && this.field_78806_j) {
            if (!this.compiled) {
                compileDisplayList(par1);
            }
            GL11.glTranslatef(this.field_82906_o, this.field_82908_p, this.field_82907_q);
            if (this.field_78795_f != 0.0f || this.field_78796_g != 0.0f || this.field_78808_h != 0.0f) {
                GL11.glPushMatrix();
                GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
                if (this.field_78796_g != 0.0f) {
                    GL11.glRotatef(this.field_78796_g * 57.295776f, 0.0f, 1.0f, 0.0f);
                }
                if (this.field_78808_h != 0.0f) {
                    GL11.glRotatef(this.field_78808_h * 57.295776f, 0.0f, 0.0f, 1.0f);
                }
                if (this.field_78795_f != 0.0f) {
                    GL11.glRotatef(this.field_78795_f * 57.295776f, 1.0f, 0.0f, 0.0f);
                }
                GL11.glCallList(this.displayList);
                if (this.field_78805_m != null) {
                    for (int var2 = 0; var2 < this.field_78805_m.size(); var2++) {
                        ((ModelRenderer) this.field_78805_m.get(var2)).func_78785_a(par1);
                    }
                }
                GL11.glPopMatrix();
            } else if (this.field_78800_c == 0.0f && this.field_78797_d == 0.0f && this.field_78798_e == 0.0f) {
                GL11.glCallList(this.displayList);
                if (this.field_78805_m != null) {
                    for (int var22 = 0; var22 < this.field_78805_m.size(); var22++) {
                        ((ModelRenderer) this.field_78805_m.get(var22)).func_78785_a(par1);
                    }
                }
            } else {
                GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
                GL11.glCallList(this.displayList);
                if (this.field_78805_m != null) {
                    for (int var23 = 0; var23 < this.field_78805_m.size(); var23++) {
                        ((ModelRenderer) this.field_78805_m.get(var23)).func_78785_a(par1);
                    }
                }
                GL11.glTranslatef((-this.field_78800_c) * par1, (-this.field_78797_d) * par1, (-this.field_78798_e) * par1);
            }
            GL11.glTranslatef(-this.field_82906_o, -this.field_82908_p, -this.field_82907_q);
        }
    }

    @SideOnly(Side.CLIENT)
    private void compileDisplayList(float par1) {
        this.displayList = GLAllocation.func_74526_a(1);
        GL11.glNewList(this.displayList, 4864);
        Tessellator var2 = Tessellator.field_78398_a;
        for (int var3 = 0; var3 < this.field_78804_l.size(); var3++) {
            ((ModelBox) this.field_78804_l.get(var3)).func_78245_a(var2, par1);
        }
        GL11.glEndList();
        this.compiled = true;
    }
}
