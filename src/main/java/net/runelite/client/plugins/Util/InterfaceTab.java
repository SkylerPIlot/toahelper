package net.runelite.client.plugins.Util;

public enum InterfaceTab {
    COMBAT(0),
    STATS(1),
    QUEST(2),
    INVENTORY(3),
    EQUIPMENT(4),
    PRAYER(5),
    SPELLBOOK(6),
    CLAN(7),
    ACCOUNT_MANAGMENT(8),
    FRIENDS(9),
    LOGOUT(10),
    OPTIONS(11),
    EMOTES(12),
    MUSIC(13);

    private final int id;

    private InterfaceTab(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}