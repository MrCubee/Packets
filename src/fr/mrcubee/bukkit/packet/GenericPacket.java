package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import org.bukkit.entity.Player;

public interface GenericPacket {

    boolean sendPlayer(Player player);
    Packets getPacket();

}
