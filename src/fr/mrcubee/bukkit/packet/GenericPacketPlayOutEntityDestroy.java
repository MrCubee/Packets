package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutEntityDestroy extends GenericOutPacket {

    boolean setEntityID(int... id);

    int[] getEntityID();

    public static GenericPacketPlayOutEntityDestroy create() {
        Class<?> clazz = Packets.PLAY_OUT_ENTITY_DESTROY.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutEntityDestroy) result;
    }
}
