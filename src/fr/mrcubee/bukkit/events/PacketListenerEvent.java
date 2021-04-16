package fr.mrcubee.bukkit.events;

import fr.mrcubee.bukkit.packet.GenericListener;
import fr.mrcubee.bukkit.packet.GenericListenerManager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author MrCubee
 */
public abstract class PacketListenerEvent extends Event implements Cancellable {

    private final GenericListenerManager manager;
    private boolean cancelled;

    protected PacketListenerEvent(GenericListenerManager listener) {
        this.manager = listener;
        this.cancelled = false;
    }

    public GenericListenerManager getListenerManager() {
        return this.manager;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public abstract HandlerList getHandlers();

    @Override
    public abstract String getEventName();
}
