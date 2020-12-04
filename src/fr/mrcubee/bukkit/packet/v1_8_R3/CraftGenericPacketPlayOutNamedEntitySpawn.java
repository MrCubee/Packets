package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutNamedEntitySpawn;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;

import java.util.UUID;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutNamedEntitySpawn extends CraftGenericOutPacket implements GenericPacketPlayOutNamedEntitySpawn {

    public CraftGenericPacketPlayOutNamedEntitySpawn() {
        this.packet = new PacketPlayOutNamedEntitySpawn();
    }

    @Override
    public boolean setNamedEntityID(int id) {
        return Reflection.setValue(this.packet, "a", id);
    }

    @Override
    public boolean setGameProfileID(UUID uuid) {
        if (uuid == null)
            return false;
        return Reflection.setValue(this.packet, "b", uuid);
    }

    @Override
    public boolean setLocationX(double x) {
        return Reflection.setValue(this.packet, "c", MathHelper.floor(x * 32.0D));
    }

    @Override
    public boolean setLocationY(double y) {
        return Reflection.setValue(this.packet, "d", MathHelper.floor(y * 32.0D));
    }

    @Override
    public boolean setLocationZ(double z) {
        return Reflection.setValue(this.packet, "e", MathHelper.floor(z * 32.0D));
    }

    @Override
    public boolean setYaw(float yaw) {
        return Reflection.setValue(this.packet, "f", (byte) ((int) (yaw * 256.0F / 360.0F)));
    }

    @Override
    public boolean setPitch(float pitch) {
        return Reflection.setValue(this.packet, "g", (byte) ((int) (pitch * 256.0F / 360.0F)));
    }

    @Override
    public boolean fillFromLocation(Location location) {
        boolean result;

        if (location == null)
            return false;
        result = setLocationX(location.getX());
        result = result & setLocationY(location.getY());
        result = result & setLocationZ(location.getZ());
        result = result & setYaw(location.getYaw());
        result = result & setPitch(location.getPitch());
        return result;
    }

    @Override
    public boolean setItemInHandID(int id) {
        return Reflection.setValue(this.packet, "h", id);
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
    public int getNamedEntityID() {
        Object result = Reflection.getValue(this.packet, "a");

        if (result == null)
            return -1;
        return (int) result;
    }

    @Override
    public UUID getGameProfileID() {
        return (UUID) Reflection.getValue(this.packet, "b");
    }

    @Override
    public double getLocationX() {
        Object result = Reflection.getValue(this.packet, "c");

        if (result == null)
            return 0;
        return (double) result;
    }

    @Override
    public double getLocationY() {
        Object result = Reflection.getValue(this.packet, "d");

        if (result == null)
            return 0;
        return (double) result;
    }

    @Override
    public double getLocationZ() {
        Object result = Reflection.getValue(this.packet, "e");

        if (result == null)
            return 0;
        return (double) result;
    }

    @Override
    public float getYaw() {
        Object result = Reflection.getValue(this.packet, "f");

        if (result == null)
            return 0;
        return (float) result;
    }

    @Override
    public float getPitch() {
        Object result = Reflection.getValue(this.packet, "g");

        if (result == null)
            return 0;
        return (float) result;
    }

    @Override
    public Location getLocation() {
        return new Location(null, getLocationX(), getLocationY(), getLocationZ(), getYaw(), getPitch());
    }

    @Override
    public int getItemInHandID() {
        Object result = Reflection.getValue(this.packet, "h");

        if (result == null)
            return -1;
        return (int) result;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_NAMED_ENTITY_SPAWN;
    }
}
