package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Versions;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MrCubee
 */
public abstract class GenericListenerManager {

    protected static final String NAME_PREFIX = "MrCubee_";

    private Map<Player, GenericListener> listeners;

    protected GenericListenerManager() {
        this.listeners = new HashMap<Player, GenericListener>();
    }

    public abstract boolean addPlayer(Player player);
    public abstract boolean removePlayer(Player player);

    protected Map<Player, GenericListener> getListeners() {
        return this.listeners;
    }

    private static Class<?> getGenericListenerManagerClass() {
        Class<?> clazz = null;

        try {
            clazz = Class.forName("fr.mrcubee.bukkit.packet." + Versions.getCurrent() + ".CraftGenericListenerManager");
        } catch (ClassNotFoundException ignored) {}
        return clazz;
    }

    public static GenericListenerManager create() {
        Class<?> clazz = getGenericListenerManagerClass();
        GenericListenerManager result = null;

        if (clazz == null)
            return null;
        try {
            result = (GenericListenerManager) clazz.newInstance();
        } catch (Exception ignored) {}
        return result;
    }

}
