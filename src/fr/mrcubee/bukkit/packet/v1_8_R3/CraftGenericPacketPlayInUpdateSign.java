package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayInUpdateSign;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign;
import org.bukkit.Location;

public class CraftGenericPacketPlayInUpdateSign extends CraftGenericInPacket implements GenericPacketPlayInUpdateSign {

    public CraftGenericPacketPlayInUpdateSign() {
        this.packet = new PacketPlayInUpdateSign();
    }

    @Override
    public Location getLocation() {
        BlockPosition blockPosition = (BlockPosition) Reflection.getValue(this.packet, "a");

        if (blockPosition == null)
            return null;
        return new Location(null, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    @Override
    public String[] getLines() {
        IChatBaseComponent[] iChatBaseComponents = (IChatBaseComponent[]) Reflection.getValue(this.packet, "b");
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
        return Packets.PLAY_IN_UPDATE_SIGN;
    }
}
