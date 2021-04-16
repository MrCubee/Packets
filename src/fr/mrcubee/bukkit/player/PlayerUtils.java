package fr.mrcubee.bukkit.player;

import com.mojang.authlib.GameProfile;
import fr.mrcubee.bukkit.Versions;
import fr.mrcubee.bukkit.setting.ChatVisibility;
import fr.mrcubee.bukkit.setting.MainHand;
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

    public static String getLocale(Player player) {
        Class<?> clazz;

        if (player == null)
            return null;
        clazz = getCurrentClass();
        if (clazz == null)
            return null;
        return (String) Reflection.executeStaticMethod(clazz, null, "getLocale", new Class[] {Player.class}, player);
    }

    public static ChatVisibility getChatVisibility(Player player) {
        Class<?> clazz;

        if (player == null)
            return null;
        clazz = getCurrentClass();
        if (clazz == null)
            return null;
        return (ChatVisibility) Reflection.executeStaticMethod(clazz, null, "getChatVisibility", new Class[] {Player.class}, player);
    }

    public static Boolean isChatColor(Player player) {
        Class<?> clazz;

        if (player == null)
            return false;
        clazz = getCurrentClass();
        if (clazz == null)
            return false;
        return (Boolean) Reflection.executeStaticMethod(clazz, null, "isChatColor", new Class[] {Player.class}, player);
    }

    public static MainHand getMainHand(Player player) {
        Class<?> clazz;

        if (player == null)
            return null;
        clazz = getCurrentClass();
        if (clazz == null)
            return null;
        return (MainHand) Reflection.executeStaticMethod(clazz, null, "getMainHand", new Class[] {Player.class}, player);
    }
}
