package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.ScoreAction;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutScoreboardScore extends GenericPacket {

    boolean setPlayerName(String name);
    boolean setObjectiveName(String name);
    boolean setScoreValue(int value);
    boolean setScoreAction(ScoreAction scoreAction);

    public static GenericPacketPlayOutScoreboardScore create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_SCORE.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardScore) result;
    }
}
