package ecru.MapleTree.help;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ecru_EntityDummyPlayer extends Entity {
    private double offsetX;
    private double offsetY;
    private double offsetZ;
    public int tickCount;

    public ecru_EntityDummyPlayer(World par1World) {
        super(par1World);
    }

    public void func_70071_h_() {
        EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClient().field_71439_g;
        func_70107_b(((Entity) entityClientPlayerMP).field_70165_t, ((Entity) entityClientPlayerMP).field_70163_u - 1.0d, ((Entity) entityClientPlayerMP).field_70161_v);
    }

    public void func_70030_z() {
        super.func_70030_z();
    }

    protected void func_70088_a() {
        this.field_70158_ak = true;
    }

    public void func_70106_y() {
    }

    protected void func_70037_a(NBTTagCompound nbt) {
    }

    protected void func_70014_b(NBTTagCompound nbt) {
    }

    public void func_70015_d(int seconds) {
    }

    public boolean func_70027_ad() {
        return false;
    }

    public void func_70078_a(Entity entity) {
    }

    public boolean func_70097_a(DamageSource damageSource, float damage) {
        return false;
    }

    public void func_70091_d(double x, double y, double z) {
    }

    public void func_70108_f(Entity entity) {
    }

    public float func_70053_R() {
        return 0.0f;
    }

    public boolean func_70038_c(double x, double y, double z) {
        return false;
    }

    public void func_70077_a(EntityLightningBolt lb) {
    }

    public boolean func_70075_an() {
        return false;
    }

    public void func_70107_b(double x, double y, double z) {
        resetPosition();
    }

    public void func_70082_c(float yaw, float pitch) {
    }

    public int func_70070_b(float partialTickTime) {
        return 15728640;
    }

    public float func_70013_c(float partialTickTime) {
        return 0.0f;
    }

    private void resetPosition() {
        EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClient().field_71439_g;
        if (entityClientPlayerMP != null) {
            super.func_70107_b(((Entity) entityClientPlayerMP).field_70165_t, ((Entity) entityClientPlayerMP).field_70163_u, ((Entity) entityClientPlayerMP).field_70161_v);
        }
    }

    public void destroy() {
        this.field_70128_L = true;
    }
}
