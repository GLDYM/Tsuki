package ecru.MapleTree.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.packet.ecru_PacketCompost;
import ecru.MapleTree.network.packet.ecru_PacketCookPot;
import ecru.MapleTree.network.packet.ecru_PacketFountain;
import ecru.MapleTree.network.packet.ecru_PacketGatherItemsB;
import ecru.MapleTree.network.packet.ecru_PacketGrainHopper;
import ecru.MapleTree.network.packet.ecru_PacketGrapeTub;
import ecru.MapleTree.network.packet.ecru_PacketLighthouse;
import ecru.MapleTree.network.packet.ecru_PacketLighthouseB;
import ecru.MapleTree.network.packet.ecru_PacketMomiji;
import ecru.MapleTree.network.packet.ecru_PacketMomijiB;
import ecru.MapleTree.network.packet.ecru_PacketMomijiBootProcessing;
import ecru.MapleTree.network.packet.ecru_PacketMortarB;
import ecru.MapleTree.network.packet.ecru_PacketPlanter;
import ecru.MapleTree.network.packet.ecru_PacketSLight;
import ecru.MapleTree.network.packet.ecru_PacketSLightB;
import ecru.MapleTree.network.packet.ecru_PacketSapling;
import ecru.MapleTree.network.packet.ecru_PacketSunDrying;
import ecru.MapleTree.network.packet.ecru_PacketTeuchiUdon;
import ecru.MapleTree.network.packet.ecru_PacketWineBarrel;
import ecru.MapleTree.network.packet.ecru_PacketWineFaucet;

public class ecru_PacketHandler {
    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(mod_ecru_MapleTree.MODID);

    public static void init() {
        network.registerMessage(ecru_PacketCookPot.Handler.class, ecru_PacketCookPot.class, 0, Side.CLIENT);
        network.registerMessage(ecru_PacketFountain.Handler.class, ecru_PacketFountain.class, 1, Side.CLIENT);
        network.registerMessage(ecru_PacketGrainHopper.Handler.class, ecru_PacketGrainHopper.class, 2, Side.CLIENT);
        network.registerMessage(ecru_PacketGrapeTub.Handler.class, ecru_PacketGrapeTub.class, 3, Side.CLIENT);
        network.registerMessage(ecru_PacketLighthouse.Handler.class, ecru_PacketLighthouse.class, 4, Side.CLIENT);
        network.registerMessage(ecru_PacketLighthouseB.Handler.class, ecru_PacketLighthouseB.class, 5, Side.SERVER);
        network.registerMessage(ecru_PacketSapling.Handler.class, ecru_PacketSapling.class, 6, Side.CLIENT);
        network.registerMessage(ecru_PacketSLight.Handler.class, ecru_PacketSLight.class, 7, Side.CLIENT);
        network.registerMessage(ecru_PacketSLightB.Handler.class, ecru_PacketSLightB.class, 8, Side.SERVER);
        network.registerMessage(ecru_PacketWineBarrel.Handler.class, ecru_PacketWineBarrel.class, 9, Side.CLIENT);
        network.registerMessage(ecru_PacketWineFaucet.Handler.class, ecru_PacketWineFaucet.class, 10, Side.CLIENT);
        network.registerMessage(ecru_PacketMomijiB.Handler.class, ecru_PacketMomijiB.class, 11, Side.SERVER);
        network.registerMessage(ecru_PacketGatherItemsB.Handler.class, ecru_PacketGatherItemsB.class, 12, Side.SERVER);
        network.registerMessage(ecru_PacketMortarB.Handler.class, ecru_PacketMortarB.class, 13, Side.SERVER);
        network.registerMessage(ecru_PacketPlanter.Handler.class, ecru_PacketPlanter.class, 14, Side.CLIENT);
        network.registerMessage(ecru_PacketMomiji.Handler.class, ecru_PacketMomiji.class, 15, Side.CLIENT);
        network.registerMessage(ecru_PacketMomijiBootProcessing.Handler.class, ecru_PacketMomijiBootProcessing.class, 16, Side.SERVER);
        network.registerMessage(ecru_PacketSunDrying.Handler.class, ecru_PacketSunDrying.class, 17, Side.CLIENT);
        network.registerMessage(ecru_PacketTeuchiUdon.Handler.class, ecru_PacketTeuchiUdon.class, 18, Side.CLIENT);
        network.registerMessage(ecru_PacketCompost.Handler.class, ecru_PacketCompost.class, 19, Side.CLIENT);
    }
}
