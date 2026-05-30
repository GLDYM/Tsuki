package ecru.MapleTree.entity.common;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;

public class ecru_cropHarvest {
    protected World world;
    protected int x;
    protected int y;
    protected int z;
    protected int minerId;
    protected Block block;
    public EntityPlayer dummyPlayer;

    public ecru_cropHarvest(World world, Block block, int i, int j, int k) {
        this.dummyPlayer = null;
        this.world = world;
        this.block = block;
        this.x = i;
        this.y = j;
        this.z = k;
        GameProfile gp = new GameProfile(UUID.randomUUID(), "EntityMomiji");
        this.dummyPlayer = FakePlayerFactory.get((WorldServer) world, gp);
    }

    public void cropDrop(ItemStack stack) {
        if (stack.field_77994_a > 0) {
            EntityItem entityitem = new EntityItem(this.world, this.x + 0.5f, this.y + 1.0f, this.z + 0.5f, stack);
            entityitem.field_145804_b = 10;
            this.world.func_72838_d(entityitem);
        }
    }

    public void harvestBlock() {
        if (this.dummyPlayer == null) {
            return;
        }
        this.world.func_147443_d(this.minerId, this.x, this.y, this.z, -1);
        Block block = this.world.func_147439_a(this.x, this.y, this.z);
        int meta = this.world.func_72805_g(this.x, this.y, this.z);
        BlockEvent.BreakEvent breakEvent = new BlockEvent.BreakEvent(this.x, this.y, this.z, this.world, block, meta, this.dummyPlayer);
        MinecraftForge.EVENT_BUS.post(breakEvent);
        if (!breakEvent.isCanceled()) {
            List<ItemStack> stacks = getItemStackFromBlock((WorldServer) this.world, this.x, this.y, this.z);
            if (stacks != null) {
                for (ItemStack s : stacks) {
                    if (s != null) {
                        cropDrop(s);
                    }
                }
            }
            this.world.func_72889_a((EntityPlayer) null, 2001, this.x, this.y, this.z, Block.func_149682_b(block) + (meta << 12));
            breakTileBlock(this.world, this.x, this.y, this.z);
            if (block.hasTileEntity(meta)) {
                this.world.func_147475_p(this.x, this.y, this.z);
            }
            this.world.func_147468_f(this.x, this.y, this.z);
        }
    }

    public List<ItemStack> getItemStackFromBlock(WorldServer world, int i, int j, int k) {
        Block block = world.func_147439_a(i, j, k);
        if (block == null || block.isAir(world, i, j, k)) {
            return null;
        }
        int meta = world.func_72805_g(i, j, k);
        ArrayList<ItemStack> dropsList = block.getDrops(world, i, j, k, meta, 0);
        float dropChance = ForgeEventFactory.fireBlockHarvesting(dropsList, world, block, i, j, k, meta, 0, 1.0f, false, this.dummyPlayer);
        ArrayList<ItemStack> returnList = new ArrayList<>();
        Iterator i$ = dropsList.iterator();
        while (i$.hasNext()) {
            ItemStack s = i$.next();
            if (world.field_73012_v.nextFloat() <= dropChance) {
                returnList.add(s);
            }
        }
        return returnList;
    }

    public void breakTileBlock(World world, int i, int j, int k) {
        TileEntity tile = world.func_147438_o(i, j, k);
        if ((tile instanceof IInventory) && !world.field_72995_K) {
            dropItems(world, (IInventory) tile, i, j, k);
            wipeInventory((IInventory) tile);
        }
    }

    public void dropItems(World world, IInventory inv, int i, int j, int k) {
        for (int slot = 0; slot < inv.func_70302_i_(); slot++) {
            ItemStack items = inv.func_70301_a(slot);
            if (items != null && items.field_77994_a > 0) {
                dropItems(world, inv.func_70301_a(slot).func_77946_l(), i, j, k);
            }
        }
    }

    public void dropItems(World world, ItemStack stack, int i, int j, int k) {
        if (stack == null || stack.field_77994_a <= 0) {
            return;
        }
        double d = (world.field_73012_v.nextFloat() * 0.7f) + ((1.0f - 0.7f) * 0.5d);
        double d1 = (world.field_73012_v.nextFloat() * 0.7f) + ((1.0f - 0.7f) * 0.5d);
        double d2 = (world.field_73012_v.nextFloat() * 0.7f) + ((1.0f - 0.7f) * 0.5d);
        EntityItem entityitem = new EntityItem(world, i + d, j + d1, k + d2, stack);
        entityitem.field_145804_b = 10;
        world.func_72838_d(entityitem);
    }

    public void wipeInventory(IInventory inv) {
        for (int slot = 0; slot < inv.func_70302_i_(); slot++) {
            inv.func_70299_a(slot, (ItemStack) null);
        }
    }
}
