package fr.mrcubee.bukkit.events;

import fr.mrcubee.bukkit.packet.GenericListenerManager;
import fr.mrcubee.bukkit.packet.GenericOutPacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author MrCubee
 */
public final class PacketSendEvent extends PacketListenerEvent {

    private static final HandlerList handlers = new HandlerList();

    private Player receiver;
    private GenericOutPacket packet;

    private PacketSendEvent(GenericListenerManager manager, Player sender, GenericOutPacket packet) {
        super(manager);
        this.receiver = sender;
        this.packet = packet;
    }

    public GenericOutPacket getPacket() {
        return this.packet;
    }
    
    public Player getReceiver() {
        return this.receiver;
    }

    @Override
    public HandlerList getHandlers() {
        return PacketSendEvent.handlers;
    }

    @Override
    public String getEventName() {
        return "PacketSendEvent";
    }

    public static HandlerList getHandlerList() {
        return PacketSendEvent.handlers;
    }

    public static PacketSendEvent create(GenericListenerManager manager, Player receiver, GenericOutPacket packet) {
        if (receiver == null || packet == null)
            return null;
        return new PacketSendEvent(manager, receiver, packet);
    }

    public static PacketSendEvent createAndCall(GenericListenerManager manager, Player player, GenericOutPacket packet) {
        PacketSendEvent event = create(manager, player, packet);

        if (event == null)
            return null;
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event;
    }
}

