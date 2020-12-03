package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.PacketDirection;
import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.events.PacketReceiveEvent;
import fr.mrcubee.bukkit.events.PacketSendEvent;
import fr.mrcubee.bukkit.packet.GenericInPacket;
import fr.mrcubee.bukkit.packet.GenericListener;
import fr.mrcubee.bukkit.packet.GenericListenerManager;
import fr.mrcubee.bukkit.packet.GenericOutPacket;
import fr.mrcubee.util.Reflection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
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
        GenericInPacket genericOutPacket;
        PacketReceiveEvent event;

        if (packetType == null || packetType.getDirection() != PacketDirection.IN) {
            super.channelRead(context, packetObj);
            return;
        }
        genericOutPacket = (GenericInPacket) packetType.createPacket();
        if (genericOutPacket == null || !Reflection.setValue(packetType.getGenericPacketClass(), genericOutPacket, "packet", packetObj)) {
            super.channelRead(context, packetObj);
            return;
        }
        event = PacketReceiveEvent.createAndCall(getManager(), getPlayer(), genericOutPacket);
        if (event != null && event.isCancelled())
            return;
        super.channelRead(context, packetObj);
    }

    @Override
    public void write(ChannelHandlerContext context, Object packetObj, ChannelPromise promise) throws Exception {
        Packets packetType = Packets.getFromPacketClass(packetObj.getClass());
        GenericOutPacket genericOutPacket;
        PacketSendEvent event;

        if (packetType == null || packetType.getDirection() != PacketDirection.OUT) {
            super.write(context, packetObj, promise);
            return;
        }
        genericOutPacket = (GenericOutPacket) packetType.createPacket();
        if (genericOutPacket == null || !Reflection.setValue(packetType.getGenericPacketClass(), genericOutPacket, "packet", packetObj)) {
            super.write(context, packetObj, promise);
            return;
        }
        event = PacketSendEvent.createAndCall(getManager(), getPlayer(), genericOutPacket);
        if (event != null && event.isCancelled())
            return;
        super.write(context, packetObj, promise);
    }
}
