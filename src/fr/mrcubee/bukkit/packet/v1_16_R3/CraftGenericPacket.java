package fr.mrcubee.bukkit.packet.v1_16_R3;

import fr.mrcubee.bukkit.packet.GenericPacket;
import net.minecraft.server.v1_16_R3.Packet;

/**
 * @author MrCubee
 */
public abstract class CraftGenericPacket implements GenericPacket {

    protected Packet<?> packet;

}
