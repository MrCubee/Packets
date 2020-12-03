package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardScore;
import fr.mrcubee.bukkit.scoreboard.ScoreAction;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutScoreboardScore implements GenericPacketPlayOutScoreboardScore {

    private final PacketPlayOutScoreboardScore packet;

    public CraftGenericPacketPlayOutScoreboardScore() {
        this.packet = new PacketPlayOutScoreboardScore();
    }

    @Override
    public boolean setPlayerName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(packet, "a", name);
    }

    @Override
    public boolean setObjectiveName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(packet, "b", name);
    }

    @Override
    public boolean setScoreValue(int value) {
        return Reflection.setValue(packet, "c", value);
    }

    @Override
    public boolean setScoreAction(ScoreAction scoreAction) {
        if (scoreAction == null)
            return false;
        switch (scoreAction) {
            case CHANGE:
                return Reflection.setValue(packet, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE);
            case REMOVE:
                return Reflection.setValue(packet, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.REMOVE);
        }
        return false;
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
        return Packets.PLAY_OUT_SCOREBOARD_SCORE;
    }
}
