package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.ObjectiveLocation;

public interface GenericPacketPlayOutScoreboardDisplayObjective extends GenericPacket {

    boolean setObjectiveName(String name);
    boolean setObjectiveLocation(ObjectiveLocation location);

    public static GenericPacketPlayOutScoreboardDisplayObjective create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_DISPLAY_OBJECTIVE.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardDisplayObjective) result;
    }
}
