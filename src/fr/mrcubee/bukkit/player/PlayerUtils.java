package fr.mrcubee.bukkit.player;

import com.mojang.authlib.GameProfile;
import fr.mrcubee.bukkit.Versions;
import fr.mrcubee.util.Reflection;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class PlayerUtils {

    private static Class<?> getCurrentClass() {
        Class<?> clazz = null;

        try {
            clazz = Class.forName("fr.mrcubee.bukkit.player." + Versions.getCurrent() + ".PlayerUtils");
        } catch (ClassNotFoundException ignored) {}
        return clazz;
    }

    public static GameProfile getGameProfile(Player player) {
        Class<?> clazz;

        if (player == null)
            return null;
        clazz = getCurrentClass();
        if (clazz == null)
            return null;
        return (GameProfile) Reflection.executeStaticMethod(clazz, null, "getGameProfile", new Class[] {Player.class}, player);
    }

    public static boolean setGameProfile(Player player, GameProfile gameProfile) {
        Class<?> clazz;

        if (player == null || gameProfile == null)
            return false;
        clazz = getCurrentClass();
        if (clazz == null)
            return false;
        return (boolean) Reflection.executeStaticMethod(clazz, null, "setGameProfile", new Class[] {Player.class}, player);
    }
}
