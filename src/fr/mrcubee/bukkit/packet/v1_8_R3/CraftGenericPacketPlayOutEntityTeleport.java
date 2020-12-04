package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutEntityTeleport;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutEntityTeleport extends CraftGenericOutPacket implements GenericPacketPlayOutEntityTeleport {

    public CraftGenericPacketPlayOutEntityTeleport() {
        this.packet = new PacketPlayOutEntityTeleport();
    }

    @Override
    public boolean setEntityID(int id) {
        return Reflection.setValue(this.packet, "a", id);
    }

    @Override
    public boolean setLocationX(double x) {
        return Reflection.setValue(this.packet, "b", MathHelper.floor(x * 32.0D));
    }

    @Override
    public boolean setLocationY(double y) {
        return Reflection.setValue(this.packet, "c", MathHelper.floor(y * 32.0D));
    }

    @Override
    public boolean setLocationZ(double z) {
        return Reflection.setValue(this.packet, "d", MathHelper.floor(z * 32.0D));
    }

    @Override
    public boolean setYaw(float yaw) {
        return Reflection.setValue(this.packet, "e", ((byte) ((int) (yaw * 256.0F / 360.0F))));
    }

    @Override
    public boolean setPitch(float pitch) {
        return Reflection.setValue(this.packet, "f", ((byte) ((int) (pitch * 256.0F / 360.0F))));
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
    public boolean setOnTheGround(boolean onGround) {
        return Reflection.setValue(this.packet, "g", onGround);
    }

    @Override
    public boolean fillAllFromEntity(Entity entity) {
        boolean result;
        net.minecraft.server.v1_8_R3.Entity nmsEntity;

        if (entity == null)
            return false;
        nmsEntity = ((CraftEntity) entity).getHandle();
        result = setEntityID(nmsEntity.getId());
        result = result & setLocationX(nmsEntity.locX);
        result = result & setLocationY(nmsEntity.locY);
        result = result & setLocationZ(nmsEntity.locZ);
        result = result & setYaw(nmsEntity.yaw);
        result = result & setPitch(nmsEntity.pitch);
        result = result & setOnTheGround(((CraftEntity) entity).getHandle().onGround);
        return result;
    }

    @Override
    public int getEntityID() {
        Object result = Reflection.getValue(this.packet, "a");

        if (result == null)
            return -1;
        return (int) result;
    }

    @Override
    public double getLocationX() {
        Object result = Reflection.getValue(this.packet, "b");

        if (result == null)
            return 0;
        return (double) result;
    }

    @Override
    public double getLocationY() {
        Object result = Reflection.getValue(this.packet, "c");

        if (result == null)
            return 0;
        return (double) result;
    }

    @Override
    public double getLocationZ() {
        Object result = Reflection.getValue(this.packet, "d");

        if (result == null)
            return 0;
        return (double) result;
    }

    @Override
    public float getYaw() {
        Object result = Reflection.getValue(this.packet, "e");

        if (result == null)
            return 0;
        return (float) result;
    }

    @Override
    public float getPitch() {
        Object result = Reflection.getValue(this.packet, "f");

        if (result == null)
            return 0;
        return (float) result;
    }

    @Override
    public Location getLocation() {
        return new Location(null, getLocationX(), getLocationY(), getLocationZ(), getYaw(), getPitch());
    }

    @Override
    public boolean isOnTheGround() {
        Object result = Reflection.getValue(this.packet, "g");

        if (result == null)
            return false;
        return (boolean) result;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_ENTITY_TELEPORT;
    }

}
