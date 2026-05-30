package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketWineFaucet;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityWineFaucet extends TileEntity {
    public boolean isMove = false;
    public boolean isMove_old = false;

    public void setIsMove(boolean m) {
        this.isMove = m;
    }

    public boolean getIsMove() {
        return this.isMove;
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void sendItemInfo(ecru_TileEntityWineFaucet tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        if (!this.field_145850_b.field_72995_K) {
            ecru_PacketHandler.network.sendToAll(new ecru_PacketWineFaucet(x, y, z, this.isMove));
        }
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
    }

    public void func_145845_h() {
        if (this.isMove) {
            int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta | 2, 3);
        } else {
            int meta2 = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta2 & 13, 3);
        }
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.isMove != this.isMove_old) {
            this.isMove_old = this.isMove;
            sendItemInfo(this);
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    private void update(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 1) == 1) {
            wineMove(world, i, j, k);
        } else {
            this.isMove = false;
        }
    }

    private boolean wineMove(World world, int i, int j, int k) {
        int thisMeta = world.func_72805_g(i, j, k);
        int x = i;
        int z = k;
        switch (thisMeta & 12) {
            case 0:
                z++;
                break;
            case 4:
                x--;
                break;
            case 8:
                z--;
                break;
            case 12:
                x++;
                break;
        }
        Block tub_id = world.func_147439_a(x, j, z);
        int tub_meta = world.func_72805_g(x, j, z);
        TileEntity tile = world.func_147438_o(x, j, z);
        if (tile instanceof ecru_TileEntityGrapeTub) {
            ecru_TileEntityGrapeTub tub_tile = (ecru_TileEntityGrapeTub) world.func_147438_o(x, j, z);
            Block bar_id = world.func_147439_a(i, j - 1, k);
            int bar_meta = world.func_72805_g(i, j - 1, k);
            TileEntity tile2 = world.func_147438_o(i, j - 1, k);
            if (tile2 instanceof ecru_TileEntityWineBarrel) {
                ecru_TileEntityWineBarrel bar_tile = (ecru_TileEntityWineBarrel) world.func_147438_o(i, j - 1, k);
                if (tub_id == mod_ecru_MapleTree.blockGrapeStompTub && (tub_meta & 1) == 1 && tub_tile != null && bar_id == mod_ecru_MapleTree.blockWineBarrel && (bar_meta & 12) == 0 && bar_tile != null) {
                    int bar_wineQuantity = bar_tile.getWineQuantity();
                    int bar_wineQuantityMax = bar_tile.getWineQuantityMax();
                    int tub_grapeNum = tub_tile.getGrapeNum();
                    int tub_stompTime = tub_tile.getStompTime();
                    int tub_stompTimeMax = tub_tile.getStompTimeMax();
                    if (tub_grapeNum > 0 && bar_wineQuantity + 200 <= bar_wineQuantityMax && tub_stompTime >= tub_stompTimeMax) {
                        this.isMove = true;
                        bar_tile.addWineQuantity(200);
                        tub_tile.subGrapeNum(1);
                        return true;
                    }
                    this.isMove = false;
                    world.func_72921_c(i, j, k, thisMeta & 12, 3);
                    return true;
                }
                this.isMove = false;
                world.func_72921_c(i, j, k, thisMeta & 12, 3);
                return true;
            }
            world.func_72921_c(i, j, k, thisMeta & 12, 3);
            return true;
        }
        world.func_72921_c(i, j, k, thisMeta & 12, 3);
        return true;
    }
}
