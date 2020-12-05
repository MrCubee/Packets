package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.Location;
import org.bukkit.Material;

public interface GenericPacketPlayOutBlockChange extends GenericOutPacket {

    boolean setLocation(Location location);
    boolean setBlock(Material material, int data);

    Location getLocation();
    Material getBlockType();
    int getData();

    public static GenericPacketPlayOutBlockChange create() {
        Class<?> clazz = Packets.PLAY_OUT_BLOCK_CHANGE.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutBlockChange) result;
    }
}
