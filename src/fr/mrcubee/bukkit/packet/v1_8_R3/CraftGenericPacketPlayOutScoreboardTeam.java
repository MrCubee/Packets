package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardTeam;
import fr.mrcubee.bukkit.scoreboard.TeamAction;
import fr.mrcubee.bukkit.scoreboard.TeamNameTagVisibility;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.EnumChatFormat;
import net.minecraft.server.v1_8_R3.EnumColor;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeamBase;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutScoreboardTeam implements GenericPacketPlayOutScoreboardTeam {

    private PacketPlayOutScoreboardTeam packet;

    public CraftGenericPacketPlayOutScoreboardTeam() {
        this.packet = new PacketPlayOutScoreboardTeam();
    }

    @Override
    public boolean setName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(this.packet, "a", name);
    }

    @Override
    public boolean setDisplayName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(this.packet, "b", name);
    }

    @Override
    public boolean setTeamPrefix(String prefix) {
        if (prefix == null)
            return false;
        return Reflection.setValue(this.packet, "c", prefix);
    }

    @Override
    public boolean setTeamSuffix(String suffix) {
        if (suffix == null)
            return false;
        return Reflection.setValue(this.packet, "d", suffix);
    }

    @Override
    public boolean setTeamNameTagVisibility(TeamNameTagVisibility option) {
        if (option == null)
            return false;
        switch (option) {
            case ALWAYS:
                return Reflection.setValue(this.packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e);
            case NEVER:
                return Reflection.setValue(this.packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.NEVER.e);
            case HIDE_FOR_OTHER_TEAMS:
                return Reflection.setValue(this.packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS.e);
            case HIDE_FOR_OWN_TEAM:
                return Reflection.setValue(this.packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM.e);
        }
        return false;
    }

    private EnumChatFormat convertFromChatColor(ChatColor chatColor) {
        EnumChatFormat[] colors;

        if (chatColor == null)
            return null;
        colors = EnumChatFormat.values();
        for (EnumChatFormat color : colors)
            if (color.toString().equals(chatColor.toString()))
                return color;
        return null;
    }

    @Override
    public boolean setTeamColor(ChatColor chatColor) {
        EnumChatFormat result = convertFromChatColor(chatColor);

        if (result == null)
            return false;
        return Reflection.setValue(this.packet, "f", result.b());
    }

    @Override
    public List<String> getMembersNameList() {
        return (List<String>) Reflection.getValue(this.packet, "g");
    }

    @Override
    public boolean setAction(TeamAction action) {
        if (action == null)
            return false;
        switch (action) {
            case CREATE:
                return Reflection.setValue(this.packet, "h", 0);
            case REMOVE:
                return Reflection.setValue(this.packet, "h", 1);
            case UPDATE:
                return Reflection.setValue(this.packet, "h", 2);
            case ADD_PLAYER:
                return Reflection.setValue(this.packet, "h", 3);
            case REMOVE_PLAYER:
                return Reflection.setValue(this.packet, "h", 4);
        }
        return false;
    }

    @Override
    public boolean setAllowFriendlyFire(boolean fire) {
        int value = canSeeFriendlyInvisibles() ? 2 : 0;

        if (fire)
            value |= 1;
        return Reflection.setValue(this.packet, "i", value);
    }

    @Override
    public boolean setSeeFriendlyInvisibles(boolean invisible) {
        int value = allowFriendlyFire() ? 1 : 0;

        if (invisible)
            value |= 2;
        return Reflection.setValue(this.packet, "i", value);
    }

    @Override
    public String getName() {
        return (String) Reflection.getValue(this.packet, "a");
    }

    @Override
    public String getDisplayName() {
        return (String) Reflection.getValue(this.packet, "b");
    }

    @Override
    public String getTeamPrefix() {
        return (String) Reflection.getValue(this.packet, "c");
    }

    @Override
    public String getTeamSuffix() {
        return (String) Reflection.getValue(this.packet, "d");
    }

    @Override
    public TeamNameTagVisibility getTeamNameTagVisibility() {
        String result = (String) Reflection.getValue(this.packet, "e");

        if (result == null)
            return null;
        if (ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e.equals(result))
            return TeamNameTagVisibility.ALWAYS;
        else if (ScoreboardTeamBase.EnumNameTagVisibility.NEVER.e.equals(result))
            return TeamNameTagVisibility.NEVER;
        else if (ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS.e.equals(result))
            return TeamNameTagVisibility.HIDE_FOR_OTHER_TEAMS;
        else if (ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM.e.equals(result))
            return TeamNameTagVisibility.HIDE_FOR_OWN_TEAM;
        return null;
    }

    private ChatColor convertFromChatFormat(EnumChatFormat chatFormat) {
        ChatColor[] colors;

        if (chatFormat == null)
            return null;
        colors = ChatColor.values();
        for (ChatColor color : colors)
            if (color.toString().equals(chatFormat.toString()))
                return color;
        return null;
    }

    @Override
    public ChatColor getTeamColor() {
        Object result = Reflection.getValue(this.packet, "f");

        if (result == null)
            return null;
        return convertFromChatFormat(EnumChatFormat.a((int) result));
    }

    @Override
    public TeamAction getAction() {
        Object result = Reflection.getValue(this.packet, "h");

        if (result == null)
            return null;
        switch ((int) result) {
            case 0:
                return TeamAction.CREATE;
            case 1:
                return TeamAction.REMOVE;
            case 2:
                return TeamAction.UPDATE;
            case 3:
                return TeamAction.ADD_PLAYER;
            case 4:
                return TeamAction.REMOVE_PLAYER;
        }
        return null;
    }

    @Override
    public boolean allowFriendlyFire() {
        Object result = Reflection.getValue(this.packet, "i");

        if (result == null)
            return false;
        return (((int) result) & 1) != 0;
    }

    @Override
    public boolean canSeeFriendlyInvisibles() {
        Object result = Reflection.getValue(this.packet, "i");

        if (result == null)
            return false;
        return (((int) result) & 2) != 0;
    }

    @Override
    public boolean sendPlayer(Player player) {
        if (player == null || !player.isOnline())
            return false;
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(this.packet);
        return true;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_SCOREBOARD_TEAM;
    }
}
