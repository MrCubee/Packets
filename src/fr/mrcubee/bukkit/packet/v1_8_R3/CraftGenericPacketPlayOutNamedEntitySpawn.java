package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutNamedEntitySpawn;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CraftGenericPacketPlayOutNamedEntitySpawn implements GenericPacketPlayOutNamedEntitySpawn {

    private final PacketPlayOutNamedEntitySpawn packet;

    public CraftGenericPacketPlayOutNamedEntitySpawn() {
        this.packet = new PacketPlayOutNamedEntitySpawn();
    }

    @Override
    public boolean setNamedEntityID(int id) {
        return Reflection.setValue(packet, "a", id);
    }

    @Override
    public boolean setGameProfileID(UUID uuid) {
        if (uuid == null)
            return false;
        return Reflection.setValue(packet, "b", uuid);
    }

    @Override
    public boolean setLocationX(double x) {
        return Reflection.setValue(packet, "c", MathHelper.floor(x * 32.0D));
    }

    @Override
    public boolean setLocationY(double y) {
        return Reflection.setValue(packet, "d", MathHelper.floor(y * 32.0D));
    }

    @Override
    public boolean setLocationZ(double z) {
        return Reflection.setValue(packet, "e", MathHelper.floor(z * 32.0D));
    }

    @Override
    public boolean setYaw(float yaw) {
        return Reflection.setValue(packet, "f", (byte) ((int) (yaw * 256.0F / 360.0F)));
    }

    @Override
    public boolean setPitch(float pitch) {
        return Reflection.setValue(packet, "g", (byte) ((int) (pitch * 256.0F / 360.0F)));
    }

    @Override
    public boolean setItemInHandID(int id) {
        return Reflection.setValue(packet, "h", id);
    }

    @Override
    public boolean setNamedEntityDataWatcher(HumanEntity entity) {
        EntityHuman entityHuman;

        if (entity == null)
            return false;
        entityHuman =  ((CraftHumanEntity) entity).getHandle();
        if (entityHuman.getDataWatcher() == null)
            return false;
        return Reflection.setValue(packet, "i", entityHuman.getDataWatcher());
    }

    @Override
    public boolean fillAllFromPlayer(HumanEntity entity) {
        boolean result;
        EntityHuman entityHuman;
        ItemStack itemStack;

        if (entity == null)
            return false;
        entityHuman = ((CraftHumanEntity) entity).getHandle();
        result = setNamedEntityID(entityHuman.getId());
        result = result & setGameProfileID(entityHuman.getProfile().getId());
        result = result & setLocationX(entityHuman.locX);
        result = result & setLocationY(entityHuman.locY);
        result = result & setLocationZ(entityHuman.locZ);
        result = result & setYaw(entityHuman.yaw);
        result = result & setPitch(entityHuman.pitch);
        itemStack = entityHuman.inventory.getItemInHand();
        result = result & setItemInHandID(itemStack == null ? 0 : Item.getId(itemStack.getItem()));
        result = result & setNamedEntityDataWatcher(entity);
        return result;
    }

    @Override
    public boolean sendPlayer(Player player) {
        if (player == null || !player.isOnline())
            return false;
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        return true;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_NAMED_ENTITY_SPAWN;
    }
}
