package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.Location;

public interface GenericPacketPlayOutOpenSignEditor extends GenericOutPacket {

    boolean setLocation(Location location);

    Location getLocation();

    public static GenericPacketPlayOutOpenSignEditor create() {
        Class<?> clazz = Packets.PLAY_OUT_OPEN_SIGN_EDITOR.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutOpenSignEditor) result;
    }
}
