package fr.mrcubee.bukkit.packet.v1_8_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayInSettings;
import fr.mrcubee.bukkit.packet.GenericPacketPlayInUpdateSign;
import fr.mrcubee.bukkit.setting.ChatVisibility;
import fr.mrcubee.bukkit.setting.MainHand;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_8_R3.EnumChatVisibility;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayInSettings;
import net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign;
import org.bukkit.Location;

public class CraftGenericPacketPlayInSettings extends CraftGenericInPacket implements GenericPacketPlayInSettings {

    public CraftGenericPacketPlayInSettings() {
        this.packet = new PacketPlayInSettings();
    }

    @Override
    public String getLocale() {
        return (String) Reflection.getValue(this.packet, "a");
    }

    @Override
    public int getViewDistance() {
        Integer integer = (Integer) Reflection.getValue(this.packet, "b");

        return (integer == null) ? 0 : integer;
    }

    @Override
    public ChatVisibility getChatVisibility() {
        EnumChatVisibility enumChatVisibility = (EnumChatVisibility) Reflection.getValue(this.packet, "c");

        if (enumChatVisibility == null)
            return null;
        return ChatVisibility.getFromID(enumChatVisibility.a());
    }

    @Override
    public boolean isChatColor() {
        Boolean chatColor = (Boolean) Reflection.getValue(this.packet, "d");

        if (chatColor == null)
            return false;
        return chatColor;
    }

    @Override
    public MainHand getMainHand() {
        Integer integer = (Integer) Reflection.getValue(this.packet, "e");

        return (integer == null) ? MainHand.RIGHT : MainHand.values()[integer];
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_IN_SETTINGS;
    }
}
