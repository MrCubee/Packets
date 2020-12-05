package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Versions;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author MrCubee
 */
public abstract class GenericListenerManager {

    protected final Map<Player, GenericListener> listeners;

    protected GenericListenerManager() {
        this.listeners = new WeakHashMap<Player, GenericListener>();
    }

    public abstract boolean addPlayer(Player player);
    public abstract boolean removePlayer(Player player);

    public boolean containPlayer(Player player) {
        if (player == null)
            return false;
        return this.listeners.containsKey(player);
    }

    private static Class<?> getGenericListenerManagerClass() {
        Class<?> clazz = null;

        try {
            clazz = Class.forName("fr.mrcubee.bukkit.packet." + Versions.getCurrent() + ".CraftGenericListenerManager");
        } catch (ClassNotFoundException ignored) {}
        return clazz;
    }

    public static GenericListenerManager create(String name) {
        Class<?> clazz = getGenericListenerManagerClass();
        Constructor<?> constructor;
        GenericListenerManager result = null;

        if (clazz == null)
            return null;
        try {
            constructor = (Constructor<?>) clazz.getConstructor(String.class);
            result = (GenericListenerManager) constructor.newInstance(name);
        } catch (Exception ignored) {}
        return result;
    }
}
