package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.entity.HumanEntity;

import java.util.UUID;

public interface GenericPacketPlayOutNamedEntitySpawn extends GenericPacket {
    boolean setNamedEntityID(int id);
    boolean setGameProfileID(UUID uuid);
    boolean setLocationX(double x);
    boolean setLocationY(double y);
    boolean setLocationZ(double z);
    boolean setYaw(float yaw);
    boolean setPitch(float pitch);
    boolean setItemInHandID(int id);
    boolean setNamedEntityDataWatcher(HumanEntity entity);
    boolean fillAllFromPlayer(HumanEntity entity);

    public static GenericPacketPlayOutNamedEntitySpawn create() {
        Class<?> clazz = Packets.PLAY_OUT_NAMED_ENTITY_SPAWN.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutNamedEntitySpawn) result;
    }
}
