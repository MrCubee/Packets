package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutRespawn;
import fr.mrcubee.bukkit.player.Difficulty;
import fr.mrcubee.bukkit.world.GameMode;
import fr.mrcubee.bukkit.world.WorldType;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutRespawn implements GenericPacketPlayOutRespawn {

    private PacketPlayOutRespawn packet;

    public CraftGenericPacketPlayOutRespawn() {
        this.packet = new PacketPlayOutRespawn();
    }

    @Override
    public boolean setDimensionID(int id) {
        return Reflection.setValue(this.packet, "a", id);
    }

    @Override
    public boolean setDimension(org.bukkit.World world) {
        if (world == null)
            return false;
        return setDimensionID(((byte) ((CraftWorld) world).getEnvironment().getId()));
    }

    @Override
    public boolean setWorldDifficulty(Difficulty difficulty) {
        if (difficulty == null)
            return false;
        switch (difficulty) {
            case PEACEFUL:
                return Reflection.setValue(this.packet, "b", EnumDifficulty.PEACEFUL);
            case EASY:
                return Reflection.setValue(this.packet, "b", EnumDifficulty.EASY);
            case NORMAL:
                return Reflection.setValue(this.packet, "b", EnumDifficulty.NORMAL);
            case HARD:
                return Reflection.setValue(this.packet, "b", EnumDifficulty.HARD);
        }
        return false;
    }

    @Override
    public boolean setNamedEntityGameMode(GameMode gameMode) {
        if (gameMode == null)
            return false;
        switch (gameMode) {
            case NOT_SET:
                return Reflection.setValue(this.packet, "c", WorldSettings.EnumGamemode.NOT_SET);
            case SURVIVAL:
                return Reflection.setValue(this.packet, "c", WorldSettings.EnumGamemode.SURVIVAL);
            case CREATIVE:
                return Reflection.setValue(this.packet, "c", WorldSettings.EnumGamemode.CREATIVE);
            case ADVENTURE:
                return Reflection.setValue(this.packet, "c", WorldSettings.EnumGamemode.ADVENTURE);
            case SPECTATOR:
                return Reflection.setValue(this.packet, "c", WorldSettings.EnumGamemode.SPECTATOR);
        }
        return false;
    }

    @Override
    public boolean setWorldType(WorldType type) {
        if (type == null)
            return false;
        switch (type) {
            case NORMAL:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.NORMAL);
            case FLAT:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.FLAT);
            case LARGE_BIOMES:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.LARGE_BIOMES);
            case AMPLIFIED:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.AMPLIFIED);
            case CUSTOMIZED:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.CUSTOMIZED);
            case DEBUG_ALL_BLOCK_STATES:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.DEBUG_ALL_BLOCK_STATES);
            case NORMAL_1_1:
                return Reflection.setValue(this.packet, "d", net.minecraft.server.v1_8_R3.WorldType.NORMAL_1_1);
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
        result = setDimension(entity.getWorld());
        result = result & Reflection.setValue(this.packet, "b", entityPlayer.world.getDifficulty());
        result = result & Reflection.setValue(this.packet, "c", entityPlayer.playerInteractManager.getGameMode());
        result = result & Reflection.setValue(this.packet, "d", entityPlayer.world.G());
        return result;
    }

    @Override
    public int getDimensionID() {
        Object result = Reflection.getValue(this.packet, "a");

        if (result == null)
            return -1;
        return (int) result;
    }

    @Override
    public org.bukkit.World getDimension() {
        int dimensionId = getDimensionID();
        CraftServer craftServer;
        WorldServer worldServer;

        if (dimensionId < 0)
            return null;
        craftServer = (CraftServer) Bukkit.getServer();
        worldServer = craftServer.getServer().getWorldServer(dimensionId);
        if (worldServer == null)
            return null;
        return worldServer.getWorld();
    }

    @Override
    public Difficulty getWorldDifficulty() {
        EnumDifficulty enumDifficulty = (EnumDifficulty) Reflection.getValue(this.packet, "b");

        if (enumDifficulty == null)
            return null;
        switch (enumDifficulty) {
            case PEACEFUL:
                return Difficulty.PEACEFUL;
            case EASY:
                return Difficulty.EASY;
            case NORMAL:
                return Difficulty.NORMAL;
            case HARD:
                return Difficulty.HARD;
        }
        return null;
    }

    @Override
    public GameMode getNamedEntityGameMode() {
        WorldSettings.EnumGamemode enumGamemode = (WorldSettings.EnumGamemode) Reflection.getValue(this.packet, "c");

        if (enumGamemode == null)
            return null;
        switch (enumGamemode) {
            case NOT_SET:
                return  GameMode.NOT_SET;
            case SURVIVAL:
                return GameMode.SURVIVAL;
            case CREATIVE:
                return GameMode.CREATIVE;
            case ADVENTURE:
                return GameMode.ADVENTURE;
            case SPECTATOR:
                return GameMode.SPECTATOR;
        }
        return null;
    }

    @Override
    public WorldType getWorldType() {
        net.minecraft.server.v1_8_R3.WorldType worldType = (net.minecraft.server.v1_8_R3.WorldType) Reflection.getValue(this.packet, "d");

        if (worldType == null)
            return null;
        if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.NORMAL))
            return WorldType.NORMAL;
        else if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.FLAT))
            return WorldType.FLAT;
        else if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.LARGE_BIOMES))
            return WorldType.LARGE_BIOMES;
        else if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.AMPLIFIED))
            return WorldType.AMPLIFIED;
        else if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.CUSTOMIZED))
            return WorldType.CUSTOMIZED;
        else if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.DEBUG_ALL_BLOCK_STATES))
            return WorldType.DEBUG_ALL_BLOCK_STATES;
        else if (worldType.equals(net.minecraft.server.v1_8_R3.WorldType.NORMAL_1_1))
            return WorldType.NORMAL_1_1;
        return null;
    }

    @Override
    public boolean sendPlayer(Player player) {
        if (player == null || !player.isOnline())
            return false;
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(this.packet);
        return true;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_RESPAWN;
    }
}
