package fr.mrcubee.bukkit.player.v1_8_R3;

import com.mojang.authlib.GameProfile;
import fr.mrcubee.bukkit.setting.ChatVisibility;
import fr.mrcubee.bukkit.setting.MainHand;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 */
public class PlayerUtils {

    public static GameProfile getGameProfile(Player player) {
        if (player == null)
            return null;
        return ((CraftPlayer) player).getProfile();
    }

    public static boolean setGameProfile(Player player, GameProfile gameProfile) {
        EntityPlayer entityPlayer;

        if (player == null || gameProfile == null)
            return false;
        entityPlayer = ((CraftPlayer) player).getHandle();
        if (entityPlayer == null)
            return false;
        return Reflection.setValue(entityPlayer, "bH", gameProfile);
    }

    public static String getLocale(Player player) {
        EntityPlayer entityPlayer;

        if (player == null)
            return null;
        entityPlayer = ((CraftPlayer) player).getHandle();
        if (entityPlayer == null)
            return null;
        return entityPlayer.locale;
    }

    public static ChatVisibility getChatVisibility(Player player) {
        EntityPlayer entityPlayer;
        EntityHuman.EnumChatVisibility enumChatVisibility;

        if (player == null)
            return null;
        entityPlayer = ((CraftPlayer) player).getHandle();
        if (entityPlayer == null)
            return null;
        enumChatVisibility = entityPlayer.getChatFlags();
        if (enumChatVisibility == null)
            return null;
        return ChatVisibility.getFromID(enumChatVisibility.a());
    }

    public static Boolean isChatColor(Player player) {
        EntityPlayer entityPlayer;
        byte value;

        if (player == null)
            return null;
        entityPlayer = ((CraftPlayer) player).getHandle();
        if (entityPlayer == null)
            return null;
        return (Boolean) Reflection.getValue(entityPlayer, "bS");
    }

    public static MainHand getMainHand(Player player) {
        EntityPlayer entityPlayer;
        byte value;

        if (player == null)
            return null;
        entityPlayer = ((CraftPlayer) player).getHandle();
        if (entityPlayer == null)
            return null;
        value = entityPlayer.getDataWatcher().getByte(10);
        if (value < 0 || value > 1)
            return null;
        return MainHand.values()[value];
    }
}
