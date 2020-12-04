package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutEntityDestroy;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutEntityDestroy extends CraftGenericOutPacket implements GenericPacketPlayOutEntityDestroy {

    public CraftGenericPacketPlayOutEntityDestroy() {
        this.packet = new PacketPlayOutEntityDestroy();
    }

    @Override
    public boolean setEntityID(int... id) {
        if (id == null)
            return false;
        return Reflection.setValue(this.packet, "a", id);
    }

    @Override
    public int[] getEntityID() {
        return (int[]) Reflection.getValue(this.packet, "a");
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_ENTITY_DESTROY;
    }
}
