package ecru.MapleTree.entity.common;

import java.util.Comparator;

public class BlockPosComparator implements Comparator<ecru_EntityMomijiBlockPos> {
    @Override
    public int compare(ecru_EntityMomijiBlockPos p1, ecru_EntityMomijiBlockPos p2) {
        if (p1.distance < p2.distance) {
            return -1;
        }
        if (p1.distance == p2.distance) {
            return 0;
        }
        return 1;
    }
}
