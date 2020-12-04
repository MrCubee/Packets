package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutEntityTeleport extends GenericOutPacket {

    boolean setEntityID(int id);
    boolean setLocationX(double x);
    boolean setLocationY(double y);
    boolean setLocationZ(double z);
    boolean setYaw(float yaw);
    boolean setPitch(float pitch);
    boolean fillFromLocation(Location location);
    boolean setOnTheGround(boolean onGround);
    boolean fillAllFromEntity(Entity entity);

    int getEntityID();
    double getLocationX();
    double getLocationY();
    double getLocationZ();
    float getYaw();
    float getPitch();
    Location getLocation();
    boolean isOnTheGround();

    public static GenericPacketPlayOutEntityTeleport create() {
        Class<?> clazz = Packets.PLAY_OUT_ENTITY_TELEPORT.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutEntityTeleport) result;
    }
}
