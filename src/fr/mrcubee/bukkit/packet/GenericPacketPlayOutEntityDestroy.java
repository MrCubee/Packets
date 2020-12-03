package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutEntityDestroy extends GenericPacket {

    boolean setEntityID(int... id);

    public static GenericPacketPlayOutEntityDestroy create() {
        Class<?> clazz = Packets.PLAY_OUT_ENTITY_DESTROY.getPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutEntityDestroy) result;
    }
}
