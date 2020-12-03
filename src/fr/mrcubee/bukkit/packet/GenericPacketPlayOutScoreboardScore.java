package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.ScoreAction;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutScoreboardScore extends GenericOutPacket {

    boolean setPlayerName(String name);
    boolean setObjectiveName(String name);
    boolean setScoreValue(int value);
    boolean setScoreAction(ScoreAction scoreAction);

    String getPlayerName();
    String getObjectiveName();
    int getScoreValue();
    ScoreAction getScoreAction();

    public static GenericPacketPlayOutScoreboardScore create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_SCORE.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardScore) result;
    }
}
