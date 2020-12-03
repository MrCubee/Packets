package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.ObjectiveAction;
import fr.mrcubee.bukkit.scoreboard.ObjectiveFormat;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutScoreboardObjective extends GenericOutPacket {

    boolean setObjectiveName(String name);
    boolean setObjectiveDisplayName(String displayName);
    boolean setObjectiveFormat(ObjectiveFormat format);
    boolean setAction(ObjectiveAction action);

    String getObjectiveName();
    String getObjectiveDisplayName();
    ObjectiveFormat getObjectiveFormat();
    ObjectiveAction getAction();

    public static GenericPacketPlayOutScoreboardObjective create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_OBJECTIVE.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardObjective) result;
    }
}
