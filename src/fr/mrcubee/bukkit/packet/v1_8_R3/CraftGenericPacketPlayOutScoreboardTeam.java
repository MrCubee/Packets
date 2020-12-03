package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardTeam;
import fr.mrcubee.bukkit.scoreboard.TeamNameTagVisibility;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeamBase;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class CraftGenericPacketPlayOutScoreboardTeam implements GenericPacketPlayOutScoreboardTeam {

    private final PacketPlayOutScoreboardTeam packet;

    public CraftGenericPacketPlayOutScoreboardTeam() {
        this.packet = new PacketPlayOutScoreboardTeam();
    }

    @Override
    public boolean setName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(packet, "a", name);
    }

    @Override
    public boolean setDisplayName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(packet, "b", name);
    }

    @Override
    public boolean setTeamPrefix(String prefix) {
        if (prefix == null)
            return false;
        return Reflection.setValue(packet, "c", prefix);
    }

    @Override
    public boolean setTeamSuffix(String suffix) {
        if (suffix == null)
            return false;
        return Reflection.setValue(packet, "d", suffix);
    }

    @Override
    public boolean setTeamNameTagVisibility(TeamNameTagVisibility option) {
        if (option == null)
            return false;
        switch (option) {
            case ALWAYS:
                return Reflection.setValue(packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e);
            case NEVER:
                return Reflection.setValue(packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.NEVER.e);
            case HIDE_FOR_OTHER_TEAMS:
                return Reflection.setValue(packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS.e);
            case HIDE_FOR_OWN_TEAM:
                return Reflection.setValue(packet, "e", ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM.e);
        }
        return false;
    }

    @Override
    public List<String> getMembersNameList() {
        return (List<String>) Reflection.getValue(packet, "g");
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
        return Packets.PLAY_OUT_SCOREBOARD_TEAM;
    }
}
