package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.player.PlayerInfoAction;
import org.bukkit.entity.HumanEntity;

public interface GenericPacketPlayOutPlayerInfo extends GenericPacket {

    boolean setAction(PlayerInfoAction playerInfoAction);
    boolean addPlayer(HumanEntity... humansEntity);
    void clear();

    public static GenericPacketPlayOutPlayerInfo create() {
        Class<?> clazz = Packets.PLAY_OUT_PLAYER_INFO.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutPlayerInfo) result;
    }
}
