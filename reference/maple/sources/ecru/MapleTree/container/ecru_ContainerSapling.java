package ecru.MapleTree.container;

import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

public class ecru_ContainerSapling extends Container {
    private EntityPlayer player;
    private IInventory playerInventory;
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private int buttonId = -1;

    public ecru_ContainerSapling(EntityPlayer player, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 74 + 84 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 74 + 142));
        }
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockMapleSapling && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75142_b() {
        super.func_75142_b();
    }
}
