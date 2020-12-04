package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.scoreboard.TeamAction;
import fr.mrcubee.bukkit.scoreboard.TeamNameTagVisibility;
import org.bukkit.ChatColor;

import java.util.List;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutScoreboardTeam extends GenericOutPacket {

    boolean setName(String name);
    boolean setDisplayName(String name);
    boolean setTeamPrefix(String prefix);
    boolean setTeamSuffix(String suffix);
    boolean setTeamNameTagVisibility(TeamNameTagVisibility option);
    boolean setTeamColor(ChatColor chatColor);
    List<String> getMembersNameList();
    boolean setAction(TeamAction action);
    boolean setAllowFriendlyFire(boolean fire);
    boolean setSeeFriendlyInvisibles(boolean invisible);

    String getName();
    String getDisplayName();
    String getTeamPrefix();
    String getTeamSuffix();
    TeamNameTagVisibility getTeamNameTagVisibility();
    ChatColor getTeamColor();
    TeamAction getAction();
    boolean allowFriendlyFire();
    boolean canSeeFriendlyInvisibles();

    public static GenericPacketPlayOutScoreboardTeam create() {
        Class<?> clazz = Packets.PLAY_OUT_SCOREBOARD_TEAM.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutScoreboardTeam) result;
    }
}
