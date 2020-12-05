package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.packet.GenericListener;
import fr.mrcubee.bukkit.packet.GenericListenerManager;
import io.netty.channel.Channel;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class CraftGenericListenerManager extends GenericListenerManager {

    private final String pipeLineNamePrefix;

    public CraftGenericListenerManager(String name) {
        StringBuilder nameBuilder = new StringBuilder();

        nameBuilder.append("MrCubee_Listener_");
        nameBuilder.append(name);
        nameBuilder.append("_");
        this.pipeLineNamePrefix = nameBuilder.toString();
    }

    @Override
    public boolean addPlayer(Player player) {
        GenericListener genericListener;

        if (player == null || this.listeners.containsKey(player))
            return false;
        genericListener = new CraftGenericListener(this, player);
        this.listeners.put(player, genericListener);
        ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline().addBefore("packet_handler",
                this.pipeLineNamePrefix + player.getName(), genericListener);
        return true;
    }

    @Override
    public boolean removePlayer(Player player) {
        Channel channel;

        if (player == null || this.listeners.remove(player) == null)
            return false;
        channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(this.pipeLineNamePrefix + player.getName());;
        });
        return true;
    }
}
