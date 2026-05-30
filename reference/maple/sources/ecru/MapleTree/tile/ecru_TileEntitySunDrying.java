package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketSunDrying;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntitySunDrying extends TileEntity {
    private final Random random = new Random();
    private long TIMERCOUNT = 20;
    private long timer = this.TIMERCOUNT;
    private int countTimer = 0;
    private int countTimerMax = 0;
    private boolean finished = false;
    private ItemStack item_in;
    private ItemStack item_out;
    private ItemStack item_org;

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.countTimer = nbttagcompound.func_74762_e("countTimer");
        this.countTimerMax = nbttagcompound.func_74762_e("countTimerMax");
        this.finished = nbttagcompound.func_74767_n("finished");
        NBTTagList itemsTagList = nbttagcompound.func_150295_c("Items", 10);
        NBTTagCompound itemTagCompound = itemsTagList.func_150305_b(0);
        this.item_in = ItemStack.func_77949_a(itemTagCompound);
        NBTTagCompound itemTagCompound2 = itemsTagList.func_150305_b(1);
        this.item_out = ItemStack.func_77949_a(itemTagCompound2);
        NBTTagCompound itemTagCompound3 = itemsTagList.func_150305_b(2);
        this.item_org = ItemStack.func_77949_a(itemTagCompound3);
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("countTimer", this.countTimer);
        nbttagcompound.func_74768_a("countTimerMax", this.countTimerMax);
        nbttagcompound.func_74757_a("finished", this.finished);
        NBTTagList itemsTagList = new NBTTagList();
        NBTTagCompound itemTagCompound = new NBTTagCompound();
        this.item_in.func_77955_b(itemTagCompound);
        itemsTagList.func_74742_a(itemTagCompound);
        NBTTagCompound itemTagCompound2 = new NBTTagCompound();
        this.item_out.func_77955_b(itemTagCompound2);
        itemsTagList.func_74742_a(itemTagCompound2);
        NBTTagCompound itemTagCompound3 = new NBTTagCompound();
        this.item_org.func_77955_b(itemTagCompound3);
        itemsTagList.func_74742_a(itemTagCompound3);
        nbttagcompound.func_74782_a("Items", itemsTagList);
    }

    public Packet func_145844_m() {
        sendItemInfo(this, true);
        return null;
    }

    private void sendItemInfo(ecru_TileEntitySunDrying tileEntity, boolean mode) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        ItemStack[] is = {this.item_in, this.item_out};
        ecru_PacketHandler.network.sendToAll(new ecru_PacketSunDrying(x, y, z, mode, this.countTimer, this.countTimerMax, this.finished, is));
    }

    public void setItemIn(ItemStack i) {
        this.item_in = i;
    }

    public void setItemOut(ItemStack i) {
        this.item_out = i;
    }

    public void setItemOrg(ItemStack i) {
        this.item_org = i;
    }

    public ItemStack getItemIn() {
        return this.item_in;
    }

    public ItemStack getItemOut() {
        return this.item_out;
    }

    public ItemStack getItemOrg() {
        return this.item_org;
    }

    public void setFinished(boolean b) {
        this.finished = b;
    }

    public int getCountTimer() {
        return this.countTimer;
    }

    public int getCountTimerMax() {
        return this.countTimerMax;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setCountTimer(int i) {
        this.countTimer = i;
    }

    public void setCountTimerMax(int i) {
        this.countTimerMax = i;
    }

    public void func_145845_h() {
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        long ti = world.func_72820_D() % 24000;
        if (world.func_72957_l(i, j + 1, k) >= 15 && this.countTimer < this.countTimerMax && !world.func_72896_J() && ti >= 0 && ti <= 12000 && world.func_72937_j(i, j, k)) {
            this.countTimer++;
        }
        if (this.countTimer % 5 == 0 && !this.finished) {
            sendItemInfo(this, false);
        }
        if (this.countTimer >= this.countTimerMax && !this.finished) {
            this.finished = true;
            sendItemInfo(this, true);
        }
        func_70296_d();
    }
}
