package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.Location;

public interface GenericPacketPlayOutUpdateSign extends GenericOutPacket {

    boolean setLocation(Location location);
    boolean setLines(String... lines);

    Location getLocation();
    String[] getLines();

    public static GenericPacketPlayOutUpdateSign create() {
        Class<?> clazz = Packets.PLAY_OUT_UPDATE_SIGN.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutUpdateSign) result;
    }
}
