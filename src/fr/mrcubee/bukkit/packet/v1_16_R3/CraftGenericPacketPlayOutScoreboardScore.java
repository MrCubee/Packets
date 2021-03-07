package fr.mrcubee.bukkit.packet.v1_16_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardScore;
import fr.mrcubee.bukkit.packet.v1_8_R3.CraftGenericOutPacket;
import fr.mrcubee.bukkit.scoreboard.ScoreAction;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutScoreboardScore extends CraftGenericOutPacket implements GenericPacketPlayOutScoreboardScore {

    public CraftGenericPacketPlayOutScoreboardScore() {
        this.packet = new PacketPlayOutScoreboardScore();
    }

    @Override
    public boolean setPlayerName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(this.packet, "a", name);
    }

    @Override
    public boolean setObjectiveName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(this.packet, "b", name);
    }

    @Override
    public boolean setScoreValue(int value) {
        return Reflection.setValue(this.packet, "c", value);
    }

    @Override
    public boolean setScoreAction(ScoreAction scoreAction) {
        if (scoreAction == null)
            return false;
        switch (scoreAction) {
            case CHANGE:
                return Reflection.setValue(this.packet, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE);
            case REMOVE:
                return Reflection.setValue(this.packet, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.REMOVE);
        }
        return false;
    }

    @Override
    public String getPlayerName() {
        return (String) Reflection.getValue(this.packet, "a");
    }

    @Override
    public String getObjectiveName() {
        return (String) Reflection.getValue(this.packet, "b");
    }

    @Override
    public int getScoreValue() {
        Object result = Reflection.getValue(this.packet, "c");

        if (result == null)
            return 0;
        return (int) result;
    }

    @Override
    public ScoreAction getScoreAction() {
        PacketPlayOutScoreboardScore.EnumScoreboardAction result = (PacketPlayOutScoreboardScore.EnumScoreboardAction) Reflection.getValue(this.packet, "c");

        if (result == null)
            return null;
        switch (result) {
            case CHANGE:
                return ScoreAction.CHANGE;
            case REMOVE:
                return ScoreAction.REMOVE;
        }
        return null;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_SCOREBOARD_SCORE;
    }
}
