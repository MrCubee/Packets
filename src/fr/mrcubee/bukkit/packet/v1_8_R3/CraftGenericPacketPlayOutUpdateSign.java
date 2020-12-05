package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutUpdateSign;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateSign;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;

public class CraftGenericPacketPlayOutUpdateSign extends CraftGenericOutPacket implements GenericPacketPlayOutUpdateSign {

    public CraftGenericPacketPlayOutUpdateSign() {
        this.packet = new PacketPlayOutUpdateSign();
    }

    @Override
    public boolean setLocation(Location location) {
        boolean result;

        if (location == null || location.getWorld() == null)
            return false;
        result = Reflection.setValue(this.packet, "a", ((CraftWorld) location.getWorld()).getHandle());
        result = result & Reflection.setValue(this.packet, "b", new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        return result;
    }

    @Override
    public boolean setLines(String... lines) {
        IChatBaseComponent[] iChatBaseComponents;

        if (lines == null || lines.length > 4)
            return false;
        iChatBaseComponents = new IChatBaseComponent[4];
        for (int i = 0; i < 4; i++) {
            if (i < lines.length)
                iChatBaseComponents[i] = CraftChatMessage.fromString(lines[i])[0];
            else
                iChatBaseComponents[i] = CraftChatMessage.fromString("")[0];
        }
        return Reflection.setValue(this.packet, "c", iChatBaseComponents);
    }

    @Override
    public Location getLocation() {
        Object object = Reflection.getValue(this.packet, "a");
        World world;
        BlockPosition blockPosition;

        if (object == null)
            return null;
        world = ((net.minecraft.server.v1_8_R3.World) object).getWorld();
        object = Reflection.getValue(this.packet, "b");
        if (object == null)
            return null;
        blockPosition = (BlockPosition) object;
        return new Location(world, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    @Override
    public String[] getLines() {
        IChatBaseComponent[] iChatBaseComponents = (IChatBaseComponent[]) Reflection.getValue(this.packet, "c");
        String[] result;

        if (iChatBaseComponents == null)
            return null;
        result = new String[4];
        for (int i = 0; i < 4; i++) {
            if (i < iChatBaseComponents.length)
                result[i] = iChatBaseComponents[i].c();
            else
                result[i] = "";
        }
        return result;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_UPDATE_SIGN;
    }
}
