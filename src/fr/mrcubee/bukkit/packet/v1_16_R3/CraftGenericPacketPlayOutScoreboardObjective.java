package fr.mrcubee.bukkit.packet.v1_16_R3;

import fr.mrcubee.bukkit.Packets;
import fr.mrcubee.bukkit.packet.GenericPacketPlayOutScoreboardObjective;
import fr.mrcubee.bukkit.scoreboard.ObjectiveAction;
import fr.mrcubee.bukkit.scoreboard.ObjectiveFormat;
import fr.mrcubee.util.Reflection;
import net.minecraft.server.v1_16_R3.IScoreboardCriteria;
import net.minecraft.server.v1_16_R3.PacketPlayOutScoreboardObjective;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftChatMessage;

/**
 * @author MrCubee
 */
public class CraftGenericPacketPlayOutScoreboardObjective extends CraftGenericOutPacket implements GenericPacketPlayOutScoreboardObjective {

    public CraftGenericPacketPlayOutScoreboardObjective() {
        packet = new PacketPlayOutScoreboardObjective();
    }

    @Override
    public boolean setObjectiveName(String name) {
        if (name == null)
            return false;
        return Reflection.setValue(this.packet, "a", name);
    }

    @Override
    public boolean setObjectiveDisplayName(String displayName) {
        if (displayName == null)
            return false;
        return Reflection.setValue(this.packet, "b", CraftChatMessage.fromStringOrNull(displayName));
    }

    @Override
    public boolean setObjectiveFormat(ObjectiveFormat format) {
        if (format == null)
            return false;
        switch (format) {
            case INTEGER:
                return Reflection.setValue(this.packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
            case HEARTS:
                return Reflection.setValue(this.packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.HEARTS);
        }
        return false;
    }

    @Override
    public boolean setAction(ObjectiveAction action) {
        if (action == null)
            return false;
        switch (action) {
            case CREATE:
                return Reflection.setValue(this.packet, "d", 0);
            case REMOVE:
                return Reflection.setValue(this.packet, "d", 1);
            case UPDATE:
                return Reflection.setValue(this.packet, "d", 2);
        }
        return false;
    }

    @Override
    public String getObjectiveName() {
        return (String) Reflection.getValue(this.packet, "a");
    }

    @Override
    public String getObjectiveDisplayName() {
        return (String) Reflection.getValue(this.packet, "b");
    }

    @Override
    public ObjectiveFormat getObjectiveFormat() {
        IScoreboardCriteria.EnumScoreboardHealthDisplay result = (IScoreboardCriteria.EnumScoreboardHealthDisplay) Reflection.getValue(this.packet, "c");

        if (result == null)
            return null;
        switch (result) {
            case INTEGER:
                return ObjectiveFormat.INTEGER;
            case HEARTS:
                return ObjectiveFormat.HEARTS;
        }
        return null;
    }

    @Override
    public ObjectiveAction getAction() {
        Object result = Reflection.getValue(this.packet, "d");

        if (result == null)
            return null;
        switch ((int) result) {
            case 0:
                return ObjectiveAction.CREATE;
            case 1:
                return ObjectiveAction.REMOVE;
            case 2:
                return ObjectiveAction.UPDATE;
        }
        return null;
    }

    @Override
    public Packets getPacket() {
        return Packets.PLAY_OUT_SCOREBOARD_OBJECTIVE;
    }
}
