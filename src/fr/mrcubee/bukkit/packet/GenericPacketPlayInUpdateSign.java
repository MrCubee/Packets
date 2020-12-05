package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.Location;

public interface GenericPacketPlayInUpdateSign extends GenericInPacket {

    Location getLocation();
    String[] getLines();

    public static GenericPacketPlayInUpdateSign create() {
        Class<?> clazz = Packets.PLAY_IN_UPDATE_SIGN.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayInUpdateSign) result;
    }
}
