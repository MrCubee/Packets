package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.ObjectiveLocation;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutScoreboardDisplayObjective extends GenericOutPacket {

    boolean setObjectiveLocation(ObjectiveLocation location);
    boolean setObjectiveName(String name);

    ObjectiveLocation getObjectiveLocation();
    String getObjectiveName();

    public static GenericPacketPlayOutScoreboardDisplayObjective create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_DISPLAY_OBJECTIVE.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardDisplayObjective) result;
    }
}
