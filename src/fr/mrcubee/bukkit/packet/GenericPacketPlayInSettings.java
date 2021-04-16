package fr.mrcubee.bukkit.packet;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.setting.ChatVisibility;
import fr.mrcubee.bukkit.setting.MainHand;

public interface GenericPacketPlayInSettings extends GenericInPacket {

    String getLocale();
    int getViewDistance();
    ChatVisibility getChatVisibility();
    boolean isChatColor();
    MainHand getMainHand();

    public static GenericPacketPlayInSettings create() {
        Class<?> clazz = Packets.PLAY_IN_SETTINGS.getGenericPacketClass();
        Object result = null;

        if (clazz == null)
            return null;
        try {
            result = clazz.newInstance();
        } catch (Exception ignored) {}
        return (GenericPacketPlayInSettings) result;
    }
}
