package fr.mrcubee.bukkit.packet.v1_16_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardDisplayObjective;
import fr.mrcubee.bukkit.scoreboard.ObjectiveLocation;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_16_R3.PacketPlayOutScoreboardDisplayObjective;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutScoreboardDisplayObjective extends CraftGenericOutPacket implements GenericPacketPlayOutScoreboardDisplayObjective {

    public CraftGenericPacketPlayOutScoreboardDisplayObjective() {
        this.packet = new PacketPlayOutScoreboardDisplayObjective();
    }

    @Override
    public boolean setObjectiveLocation(ObjectiveLocation location) {
        if (location == null)
            return false;
        switch (location) {
            case LIST:
                return Reflection.setValue(this.packet, "a", 0);
            case SIDEBAR:
                return Reflection.setValue(this.packet, "a", 1);
            case BELOW_NAME:
                return Reflection.setValue(this.packet, "a", 2);
        }
        return false;
    }

    @Override
    public boolean setObjectiveName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(this.packet, "b", name);
    }

    @Override
    public ObjectiveLocation getObjectiveLocation() {
        Object result = Reflection.getValue(this.packet, "a");

        if (result == null)
            return null;
        switch ((int) result) {
            case 0:
                return ObjectiveLocation.LIST;
            case 1:
                return ObjectiveLocation.SIDEBAR;
            case 2:
                return ObjectiveLocation.BELOW_NAME;
        }
        return null;
    }

    @Override
    public String getObjectiveName() {
        return (String) Reflection.getValue(this.packet, "b");
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_SCOREBOARD_DISPLAY_OBJECTIVE;
    }
}
