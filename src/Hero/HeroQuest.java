package Hero;

import java.util.Objects;

public class HeroQuest {
    static final private int MINIMUM_STRENGTH_REQUIRED_TO_AVOID_DAMAGE = 5;
    static final private int HEALTH_POINTS_DECREASED = 10;
    public static final int REPAIR_COST = 4;
    private final Player player;
    private final Item item;

    public HeroQuest(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    public String playerToString() {
        String playerName = player.getPlayerName();
        int playerHealth = player.getPlayerHealth();
        int playerStrength = player.getPlayerStrength();
        int playerMagic = player.getPlayerMagic();
        int playerCraftingSkill = player.getPlayerCraftingSkill();

        return String.format("%s's Attributes:\nHealth: %d\nStrength: %d\nMagic: %d\nCrafting Skill: %d\n",
                playerName, playerHealth, playerStrength, playerMagic, playerCraftingSkill);
    }

    public void playerFallsDown() {
        int playerStrength = player.getPlayerStrength();
        int playerHealth = player.getPlayerHealth();

        System.out.println("Player drops off a cliff.");

        if (playerStrength < MINIMUM_STRENGTH_REQUIRED_TO_AVOID_DAMAGE) {
            player.setPlayerHealth(playerHealth - HEALTH_POINTS_DECREASED);
            System.out.println("Player's strength is too small. Health decreases by " + HEALTH_POINTS_DECREASED + " points.");
        }
    }

    public String itemToString() {
        String itemName = item.getItemName();
        String itemKind = item.getItemKind();
        int itemPower = item.getItemPower();

        return String.format("Item: %s\nKind: %s\nPower: %d\n", itemName, itemKind, itemPower);
    }

    public void itemReduceByUsage() {
        int itemPower = item.getItemPower();
        String itemKind = item.getItemKind();

        System.out.printf("Using the item with kind '%s' and power %d%n", itemKind, itemPower);

        item.setItemPower(itemPower / 2);

        if (itemPower == 0) {
            item.setItemKind("Junk");
        }
    }

    public void itemApplyEffectToPlayer() {
        String itemName = item.getItemName();
        String itemKind = item.getItemKind();
        int itemPower = item.getItemPower();
        int playerHealth = player.getPlayerHealth();
        int playerStrength = player.getPlayerStrength();
        int playerMagic = player.getPlayerMagic();

        System.out.printf("Applying the effect of %s (%s):%n", itemName, itemKind);

        if (Objects.equals(itemKind, "Health")) {
            player.setPlayerHealth(playerHealth + itemPower);
        } else if (Objects.equals(itemKind, "Strength")) {
            player.setPlayerStrength(playerStrength + itemPower);
        } else if (Objects.equals(itemKind, "Magic")) {
            player.setPlayerMagic(playerMagic + itemPower);
        }
    }

    public void itemRepair() {
        int itemPower = item.getItemPower();
        int playerCraftingSkill = player.getPlayerCraftingSkill();

        System.out.println("Using the repair skill to fix the item:");

        int repairAmount = (playerCraftingSkill * 2) - REPAIR_COST;

        item.setItemPower(itemPower + repairAmount);

        System.out.printf("Repaired the item by %d points. Item's Durability: %d%n", repairAmount, itemPower);
    }
}