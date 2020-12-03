package fr.mrcubee.bukkit.packet;

import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public interface GenericOutPacket extends GenericPacket {

    boolean sendPlayer(Player player);

}
