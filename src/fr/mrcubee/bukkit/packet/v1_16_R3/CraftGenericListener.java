package fr.mrcubee.bukkit.packet.v1_16_R3;

import fr.mrcubee.bukkit.PacketDirection;
import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.events.PacketReceiveEvent;
import fr.mrcubee.bukkit.events.PacketSendEvent;
import fr.mrcubee.bukkit.packet.GenericListener;
import fr.mrcubee.bukkit.packet.GenericListenerManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class CraftGenericListener extends GenericListener {

    protected CraftGenericListener(GenericListenerManager manager, Player player) {
        super(manager, player);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object packetObj) throws Exception {
        Packets packetType = Packets.getFromPacketClass(packetObj.getClass());
        CraftGenericInPacket genericInPacket;
        PacketReceiveEvent event;

        if (packetType == null || packetType.getDirection() != PacketDirection.IN) {
            super.channelRead(context, packetObj);
            return;
        }
        genericInPacket = (CraftGenericInPacket) packetType.createPacket();
        if (genericInPacket == null) {
            super.channelRead(context, packetObj);
            return;
        }
        genericInPacket.packet = (Packet<?>) packetObj;
        event = PacketReceiveEvent.createAndCall(getManager(), getPlayer(), genericInPacket);
        if (event != null && event.isCancelled())
            return;
        super.channelRead(context, packetObj);
    }

    @Override
    public void write(ChannelHandlerContext context, Object packetObj, ChannelPromise promise) throws Exception {
        Packets packetType = Packets.getFromPacketClass(packetObj.getClass());
        CraftGenericOutPacket genericOutPacket;
        PacketSendEvent event;

        if (packetType == null || packetType.getDirection() != PacketDirection.OUT) {
            super.write(context, packetObj, promise);
            return;
        }
        genericOutPacket = (CraftGenericOutPacket) packetType.createPacket();
        if (genericOutPacket == null) {
            super.write(context, packetObj, promise);
            return;
        }
        genericOutPacket.packet = (Packet<?>) packetObj;
        event = PacketSendEvent.createAndCall(getManager(), getPlayer(), genericOutPacket);
        if (event != null && event.isCancelled())
            return;
        super.write(context, packetObj, promise);
    }
}
