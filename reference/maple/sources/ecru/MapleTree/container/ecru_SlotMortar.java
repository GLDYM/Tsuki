package ecru.MapleTree.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ecru_SlotMortar extends Slot {
    public ecru_SlotMortar(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5) {
        super(par2IInventory, par3, par4, par5);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return false;
    }
}
