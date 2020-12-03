package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.player.Difficulty;
import fr.mrcubee.bukkit.world.GameMode;
import fr.mrcubee.bukkit.world.WorldType;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;

/**
 * @author MrCubee
 */
public interface GenericPacketPlayOutRespawn extends GenericOutPacket {

    boolean setDimensionID(int id);
    boolean setDimension(World world);
    boolean setWorldDifficulty(Difficulty difficulty);
    boolean setNamedEntityGameMode(GameMode gameMode);
    boolean setWorldType(WorldType type);
    boolean fillAllFromPlayer(HumanEntity entity);

    int getDimensionID();
    World getDimension();
    Difficulty getWorldDifficulty();
    GameMode getNamedEntityGameMode();
    WorldType getWorldType();

    public static GenericPacketPlayOutRespawn create() {
        Class<?> clazz = Packets.PLAY_OUT_RESPAWN.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayOutRespawn) result;
    }
}
