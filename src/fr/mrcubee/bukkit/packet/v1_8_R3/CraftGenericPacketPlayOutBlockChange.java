package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutBlockChange;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;

public class CraftGenericPacketPlayOutBlockChange extends CraftGenericOutPacket implements GenericPacketPlayOutBlockChange {

    public CraftGenericPacketPlayOutBlockChange() {
        this.packet = new PacketPlayOutBlockChange();
    }

    @Override
    public boolean setLocation(Location location) {
        if (location == null)
            return false;
        return Reflection.setValue(this.packet, "a", new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @Override
    public boolean setBlock(Material material, int data) {
        Block block;
        IBlockData iBlockData;

        if (material == null)
            return false;
        block = CraftMagicNumbers.getBlock(material);
        if (block == null)
            return false;
        iBlockData = block.fromLegacyData(data);
        if (iBlockData == null)
            return false;
        return Reflection.setValue(this.packet, "block", iBlockData);
    }

    @Override
    public Location getLocation() {
        BlockPosition blockPosition = (BlockPosition) Reflection.getValue(this.packet, "a");

        if (blockPosition == null)
            return null;
        return new Location(null, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    @Override
    public Material getBlockType() {
        IBlockData iBlockData = (IBlockData) Reflection.getValue(this.packet, "block");

        if (iBlockData == null)
            return null;
        return Material.getMaterial(CraftMagicNumbers.getId(iBlockData.getBlock()));
    }

    @Override
    public int getData() {
        IBlockData iBlockData = (IBlockData) Reflection.getValue(this.packet, "block");

        if (iBlockData == null)
            return 0;
        return iBlockData.getBlock().toLegacyData(iBlockData);
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_BLOCK_CHANGE;
    }
}
