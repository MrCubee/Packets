package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutPlayerInfo;
import fr.mrcubee.bukkit.player.PlayerInfoAction;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutPlayerInfo extends CraftGenericOutPacket implements GenericPacketPlayOutPlayerInfo {

    private PacketPlayOutPlayerInfo.EnumPlayerInfoAction action;
    private final ArrayList<EntityPlayer> players;

    public CraftGenericPacketPlayOutPlayerInfo() {
        this.players = new ArrayList<EntityPlayer>();
    }

    @Override
    public boolean setAction(PlayerInfoAction playerInfoAction) {
        if (playerInfoAction == null)
            return false;
        switch (playerInfoAction) {
            case ADD_PLAYER:
                this.action = PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER;
                return true;
            case UPDATE_GAME_MODE:
                this.action = PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_GAME_MODE;
                return true;
            case UPDATE_LATENCY:
                this.action = PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_LATENCY;
                return true;
            case UPDATE_DISPLAY_NAME:
                this.action = PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME;
                return true;
            case REMOVE_PLAYER:
                this.action = PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER;
                return true;
        }
        return false;
    }

    @Override
    public boolean addPlayer(HumanEntity... humansEntity) {
        EntityHuman entityHuman;

        if (humansEntity == null)
            return false;
        for (HumanEntity humanEntity :  humansEntity) {
            entityHuman = ((CraftHumanEntity) humanEntity).getHandle();
            if (entityHuman instanceof EntityPlayer)
                this.players.add((EntityPlayer) entityHuman);
        }
        return true;
    }

    @Override
    public void clear() {
        this.players.clear();
    }

    @Override
    public boolean sendPlayer(Player player) {
        PacketPlayOutPlayerInfo packet;

        if (player == null || !player.isOnline())
            return false;
        packet = new PacketPlayOutPlayerInfo(this.action, this.players);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        return true;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_PLAYER_INFO;
    }
}
