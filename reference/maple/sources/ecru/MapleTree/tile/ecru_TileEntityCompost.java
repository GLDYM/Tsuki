package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketCompost;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class ecru_TileEntityCompost extends TileEntity {
    private ItemStack[] itemStacks = new ItemStack[2];
    private long TIMERCOUNT = 10;
    private long timer = this.TIMERCOUNT;
    private int mushroomType = -1;
    private boolean viewFlg = false;

    /*  JADX ERROR: Failed to decode insn: 0x0018: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    public void func_145845_h() {
        /*  JADX ERROR: Failed to decode insn: 0x0018: MOVE_MULTI
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            */
        /*  JADX ERROR: Method code generation error
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.IContainer.get(jadx.api.plugins.input.data.attributes.IJadxAttrType)" because "cont" is null
            	at jadx.core.codegen.RegionGen.declareVars(RegionGen.java:70)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:282)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            */
        /*
            r6 = this;
            r0 = r6
            net.minecraft.world.World r0 = r0.field_145850_b
            boolean r0 = r0.field_72995_K
            if (r0 == 0) goto L3a
            r0 = r6
            boolean r0 = r0.viewFlg
            if (r0 != 0) goto L39
            r0 = r6
            r1 = r0
            long r1 = r1.timer
            r2 = 1
            long r1 = r1 - r2
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.timer = r1
            r0 = 0
            int r-1 = (r-1 > r0 ? 1 : (r-1 == r0 ? 0 : -1))
            if (r-1 >= 0) goto L39
            r-1 = r6
            net.minecraft.world.World r-1 = r-1.field_145850_b
            r0 = r6
            int r0 = r0.field_145851_c
            r1 = r6
            int r1 = r1.field_145848_d
            r2 = r6
            int r2 = r2.field_145849_e
            r-1.func_147471_g(r0, r1, r2)
            r-1 = r6
            r0 = 1
            r-1.viewFlg = r0
            return
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ecru.MapleTree.tile.ecru_TileEntityCompost.func_145845_h():void");
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void sendItemInfo(ecru_TileEntityCompost tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        if (!this.field_145850_b.field_72995_K) {
            ecru_PacketHandler.network.sendToAll(new ecru_PacketCompost(x, y, z, this.mushroomType));
        }
    }

    public void setMushroomType(int m) {
        this.mushroomType = m;
        func_70296_d();
        sendItemInfo(this);
    }

    public int getMushroomType() {
        return this.mushroomType;
    }

    public void func_70296_d() {
        super.func_70296_d();
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.mushroomType = nbttagcompound.func_74762_e("mushroomType");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("mushroomType", this.mushroomType);
    }
}
