package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.packet.GenericPacket;
import net.minecraft.server.v1_8_R3.Packet;

public abstract class CraftGenericPacket implements GenericPacket {

    protected Packet<?> packet;

}
