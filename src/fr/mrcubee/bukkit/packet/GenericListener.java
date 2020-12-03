package fr.mrcubee.bukkit.packet;

import io.netty.channel.ChannelDuplexHandler;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class GenericListener extends ChannelDuplexHandler {

    private final GenericListenerManager manager;

    private final Player player;

    protected GenericListener(GenericListenerManager manager, Player player) {
        this.manager = manager;
        this.player = player;
    }

    public GenericListenerManager getManager() {
        return this.manager;
    }

    public Player getPlayer() {
        return this.player;
    }

}
