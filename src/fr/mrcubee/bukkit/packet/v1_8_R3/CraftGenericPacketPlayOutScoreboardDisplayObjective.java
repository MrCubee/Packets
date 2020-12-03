package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardDisplayObjective;
import fr.mrcubee.bukkit.scoreboard.ObjectiveLocation;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;


public class CraftGenericPacketPlayOutScoreboardDisplayObjective implements GenericPacketPlayOutScoreboardDisplayObjective {

    private final PacketPlayOutScoreboardDisplayObjective packet;

    public CraftGenericPacketPlayOutScoreboardDisplayObjective() {
        this.packet = new PacketPlayOutScoreboardDisplayObjective();
    }

    @Override
    public boolean setObjectiveLocation(ObjectiveLocation location) {
        if (location == null)
            return false;
        return Reflection.setValue(packet, "a", location.ordinal());
    }

    @Override
    public boolean setObjectiveName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(packet, "b", name);
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
        return Packets.PLAY_OUT_SCOREBOARD_DISPLAY_OBJECTIVE;
    }
}
