package fr.mrcubee.bukkit.events;

import fr.mrcubee.bukkit.packet.GenericInPacket;
import fr.mrcubee.bukkit.packet.GenericListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author MrCubee
 */
public final class PacketReceiveEvent extends PacketListenerEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Player receiver;
    private final GenericInPacket packet;

    private PacketReceiveEvent(GenericListenerManager manager, Player receiver, GenericInPacket packet) {
        super(manager);
        this.receiver = receiver;
        this.packet = packet;
    }

    public GenericInPacket getPacket() {
        return this.packet;
    }

    public Player getReceiver() {
        return this.receiver;
    }

    @Override
    public HandlerList getHandlers() {
        return PacketReceiveEvent.handlers;
    }

    @Override
    public String getEventName() {
        return "PacketReceiveEvent";
    }

    public static HandlerList getHandlerList() {
        return PacketReceiveEvent.handlers;
    }

    public static PacketReceiveEvent create(GenericListenerManager manager, Player receiver, GenericInPacket packet) {
        if (receiver == null || packet == null)
            return null;
        return new PacketReceiveEvent(manager, receiver, packet);
    }

    public static PacketReceiveEvent createAndCall(GenericListenerManager manager, Player player, GenericInPacket packet) {
        PacketReceiveEvent event = create(manager, player, packet);

        if (event == null)
            return null;
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event;
    }
}

