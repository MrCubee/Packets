package fr.mrcubee.bukkit.player.v1_8_R3;

import com.mojang.authlib.GameProfile;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static GameProfile getGameProfile(Player player) {
        if (player == null)
            return null;
        return ((CraftPlayer) player).getProfile();
    }

    public static boolean setGameProfile(Player player, GameProfile gameProfile) {
        EntityHuman entityHuman;

        if (player == null || gameProfile == null)
            return false;
        entityHuman = ((CraftPlayer) player).getHandle();
        return Reflection.setValue(entityHuman, "bH", gameProfile);
    }

}
