package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutOpenSignEditor;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import org.bukkit.Location;

public class CraftGenericPacketPlayOutOpenSignEditor extends CraftGenericOutPacket implements GenericPacketPlayOutOpenSignEditor {

    public CraftGenericPacketPlayOutOpenSignEditor() {
        this.packet = new PacketPlayOutOpenSignEditor();
    }

    @Override
    public boolean setLocation(Location location) {
        if (location == null)
            return false;
        return Reflection.setValue(this.packet, "a", new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @Override
    public Location getLocation() {
        BlockPosition blockPosition = (BlockPosition) Reflection.getValue(this.packet, "a");

        if (blockPosition == null)
            return null;
        return new Location(null, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_OPEN_SIGN_EDITOR;
    }
}
