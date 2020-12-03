package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutRespawn;
import fr.mrcubee.bukkit.player.Difficulty;
import fr.mrcubee.bukkit.world.GameMode;
import fr.mrcubee.bukkit.world.WorldType;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutRespawn implements GenericPacketPlayOutRespawn {

    private final PacketPlayOutRespawn packet;

    public CraftGenericPacketPlayOutRespawn() {
        this.packet = new PacketPlayOutRespawn();
    }

    @Override
    public boolean setDimensionID(int id) {
        return Reflection.setValue(packet, "a", id);
    }

    @Override
    public boolean setWorldDifficulty(Difficulty difficulty) {
        if (difficulty == null)
            return false;
        switch (difficulty) {
            case PEACEFUL:
                return Reflection.setValue(packet, "b", EnumDifficulty.PEACEFUL);
            case EASY:
                return Reflection.setValue(packet, "b", EnumDifficulty.EASY);
            case NORMAL:
                return Reflection.setValue(packet, "b", EnumDifficulty.NORMAL);
            case HARD:
                return Reflection.setValue(packet, "b", EnumDifficulty.HARD);
        }
        return false;
    }

    @Override
    public boolean setNamedEntityGameMode(GameMode gameMode) {
        if (gameMode == null)
            return false;
        switch (gameMode) {
            case NOT_SET:
                return Reflection.setValue(packet, "c", WorldSettings.EnumGamemode.NOT_SET);
            case SURVIVAL:
                return Reflection.setValue(packet, "c", WorldSettings.EnumGamemode.SURVIVAL);
            case CREATIVE:
                return Reflection.setValue(packet, "c", WorldSettings.EnumGamemode.CREATIVE);
            case ADVENTURE:
                return Reflection.setValue(packet, "c", WorldSettings.EnumGamemode.ADVENTURE);
            case SPECTATOR:
                return Reflection.setValue(packet, "c", WorldSettings.EnumGamemode.SPECTATOR);
        }
        return false;
    }

    @Override
    public boolean setWorldType(WorldType type) {
        if (type == null)
            return false;
        switch (type) {
            case NORMAL:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.NORMAL);
            case FLAT:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.FLAT);
            case LARGE_BIOMES:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.LARGE_BIOMES);
            case AMPLIFIED:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.AMPLIFIED);
            case CUSTOMIZED:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.CUSTOMIZED);
            case DEBUG_ALL_BLOCK_STATES:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.DEBUG_ALL_BLOCK_STATES);
            case NORMAL_1_1:
                return Reflection.setValue(packet, "d", net.minecraft.server.v1_8_R3.WorldType.NORMAL_1_1);
        }
        return false;
    }

    @Override
    public boolean fillAllFromPlayer(HumanEntity entity) {
        boolean result;
        EntityHuman entityHuman;
        EntityPlayer entityPlayer;

        if (entity == null)
            return false;
        entityHuman = ((CraftHumanEntity) entity).getHandle();
        if (!(entityHuman instanceof EntityPlayer))
            return false;
        entityPlayer = (EntityPlayer) entityHuman;
        if (!(entityPlayer.world instanceof WorldServer))
            return false;
        result = setDimensionID(((byte) ((WorldServer) entityPlayer.world).getWorld().getEnvironment().getId()));
        result = result & Reflection.setValue(packet, "b", entityPlayer.world.getDifficulty());
        result = result & Reflection.setValue(packet, "c", entityPlayer.playerInteractManager.getGameMode());
        result = result & Reflection.setValue(packet, "d", entityPlayer.world.G());
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
        return Packets.PLAY_OUT_RESPAWN;
    }
}
