package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.packet.GenericListener;
import fr.mrcubee.bukkit.packet.GenericListenerManager;
import io.netty.channel.Channel;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @author MrCubee
 */
public class CraftGenericListenerManager extends GenericListenerManager {

    @Override
    public boolean addPlayer(Player player) {
        Map<Player, GenericListener> listeners = getListeners();
        GenericListener genericListener;

        if (player == null || listeners.containsKey(player))
            return false;
        genericListener = new CraftGenericListener(this, player);
        listeners.put(player, genericListener);
        ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline().addBefore("packet_handler",
                GenericListenerManager.NAME_PREFIX + player.getName(), genericListener);
        return true;
    }

    @Override
    public boolean removePlayer(Player player) {
        Map<Player, GenericListener> listeners = getListeners();
        GenericListener genericListener;
        Channel channel;

        if (player == null || (genericListener = listeners.remove(player)) == null)
            return false;
        channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(GenericListenerManager.NAME_PREFIX + player.getName());;
        });
        return true;
    }
}
