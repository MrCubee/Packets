package fr.mrcubee.bukkit.setting;

public enum ChatVisibility {
    FULL(0, "options.chat.visibility.full"),
    SYSTEM(1, "options.chat.visibility.system"),
    HIDDEN(2, "options.chat.visibility.hidden");

    public final int id;
    public final String optionId;

    ChatVisibility(int id, String optionId) {
        this.id = id;
        this.optionId = optionId;
    }

    public static ChatVisibility getFromID(int id) {
        for (ChatVisibility chatVisibility : ChatVisibility.values())
            if (chatVisibility.id == id)
                return chatVisibility;
        return null;
    }
}
