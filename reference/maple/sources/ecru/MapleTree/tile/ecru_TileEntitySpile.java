package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntitySpile extends TileEntity {
    private long tim;
    private long stack = 0;
    private long TIMERCOUNT = 16;
    private long timer = this.TIMERCOUNT;

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.tim = nbttagcompound.func_74763_f("tim");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74772_a("tim", this.tim);
    }

    public long getTime() {
        return this.tim;
    }

    public void setTime(long byte0) {
        this.tim = byte0;
    }

    public void func_145845_h() {
        int xx;
        int yy;
        int zz;
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        World world = this.field_145850_b;
        int thisMeta = world.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        switch (thisMeta >> 2) {
            case 0:
                xx = 0;
                yy = 0;
                zz = 1;
                break;
            case 1:
                xx = -1;
                yy = 0;
                zz = 0;
                break;
            case 2:
                xx = 0;
                yy = 0;
                zz = -1;
                break;
            case 3:
                xx = 1;
                yy = 0;
                zz = 0;
                break;
            default:
                System.out.println("=== ecru_TileEntitySpile error ===");
                return;
        }
        Block id = world.func_147439_a(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz);
        int meta = world.func_72805_g(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz);
        if (id != mod_ecru_MapleTree.blockMapleWoodSyrup || (meta & 3) != 2) {
            if (id == mod_ecru_MapleTree.blockMapleWoodSyrup && (meta & 3) == 3) {
                this.stack = 0L;
                return;
            }
            return;
        }
        ecru_TileEntityMapleWoodSyrup tileentity = (ecru_TileEntityMapleWoodSyrup) world.func_147438_o(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz);
        if (tileentity.SubtractionTime(1L) >= 0) {
            Block dId = world.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            int dMeta = world.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            if (dId == mod_ecru_MapleTree.blockCauldron && dMeta < 10) {
                this.stack++;
            }
            if (this.stack >= 100 && dId == mod_ecru_MapleTree.blockCauldron && dMeta < 10) {
                world.func_72921_c(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, dMeta + 1, 3);
                this.stack = 0L;
                return;
            }
            return;
        }
        world.func_72921_c(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz, meta | 3, 3);
    }
}
