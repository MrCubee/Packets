package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.player.Difficulty;
import fr.mrcubee.bukkit.world.GameMode;
import fr.mrcubee.bukkit.world.WorldType;
import org.bukkit.entity.HumanEntity;

public interface GenericPacketPlayOutRespawn extends GenericPacket {

    boolean setDimensionID(int id);
    boolean setWorldDifficulty(Difficulty difficulty);
    boolean setNamedEntityGameMode(GameMode gameMode);
    boolean setWorldType(WorldType type);
    boolean fillAllFromPlayer(HumanEntity entity);

    public static GenericPacketPlayOutRespawn create() {
        Class<?> clazz = Packets.PLAY_OUT_RESPAWN.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutRespawn) result;
    }
}
