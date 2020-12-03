package fr.mrcubee.bukkit;

import org.bukkit.Bukkit;

/**
 * @author MrCubee
 */
public enum Versions {

    v1_7_R4(5),
    v1_8_R1(47),
    v1_8_R2(47),
    v1_8_R3(47),
    v1_9_R1(107),
    v1_9_R2(110),
    v1_10_R1(210),
    v1_11_R1(316),
    v1_12_R1(340),
    v1_15_R1(573),
    v1_16_R1(736);

    private final int number;

    Versions(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public static Versions getFromName(String name) {
        Versions[] values = Versions.values();

        for (Versions version : values)
            if (version.toString().equalsIgnoreCase(name))
                return version;
        return null;
    }

    public static Versions getCurrent() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();

        return getFromName(packageName.substring(packageName.lastIndexOf('.') + 1));
    }
}
