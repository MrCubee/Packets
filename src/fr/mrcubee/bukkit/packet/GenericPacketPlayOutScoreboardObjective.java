package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.ObjectiveAction;
import fr.mrcubee.bukkit.scoreboard.ObjectiveFormat;
import org.bukkit.Bukkit;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutScoreboardObjective extends GenericPacket {

    boolean setObjectiveName(String name);
    boolean setObjectiveDisplayName(String displayName);
    boolean setObjectiveFormat(ObjectiveFormat format);
    boolean setAction(ObjectiveAction action);

    public static GenericPacketPlayOutScoreboardObjective create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_OBJECTIVE.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardObjective) result;
    }
}
