package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutEntityDestroy;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CraftGenericPacketPlayOutEntityDestroy implements GenericPacketPlayOutEntityDestroy {

    private final PacketPlayOutEntityDestroy packet;

    public CraftGenericPacketPlayOutEntityDestroy() {
        this.packet = new PacketPlayOutEntityDestroy();
    }

    @Override
    public boolean setEntityID(int... id) {
        if (id == null)
            return false;
        return Reflection.setValue(packet, "a", id);
    }

    @Override
    public boolean sendPlayer(Player player) {
        if (player == null || !player.isOnline())
            return false;
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        return true;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_ENTITY_DESTROY;
    }
}
