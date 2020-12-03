package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardObjective;
import fr.mrcubee.bukkit.scoreboard.ObjectiveAction;
import fr.mrcubee.bukkit.scoreboard.ObjectiveFormat;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CraftGenericPacketPlayOutScoreboardObjective implements GenericPacketPlayOutScoreboardObjective {

    private final PacketPlayOutScoreboardObjective packet;

    public CraftGenericPacketPlayOutScoreboardObjective() {
        packet = new PacketPlayOutScoreboardObjective();
    }

    @Override
    public boolean setObjectiveName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(packet, "a", name);
    }

    @Override
    public boolean setObjectiveDisplayName(String displayName) {
        if (displayName == null)
            return false;
        return Reflection.setValue(packet, "b", displayName);
    }

    @Override
    public boolean setObjectiveFormat(ObjectiveFormat format) {
        if (format == null)
            return false;
        return Reflection.setValue(packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.values()[format.ordinal()]);
    }

    @Override
    public boolean setAction(ObjectiveAction action) {
        if (action == null)
            return false;
        return Reflection.setValue(packet, "d", action.ordinal());
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
        return Packets.PLAY_OUT_SCOREBOARD_OBJECTIVE;
    }
}
