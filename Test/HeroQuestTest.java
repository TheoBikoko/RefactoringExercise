import Hero.HeroQuest;
import Hero.Item;
import Hero.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroQuestTest {
    private HeroQuest heroQuest;
    private Item item;
    private Player player;

    @BeforeEach
    void SetUp() {
        player = new Player();
        item = new Item();
        heroQuest = new HeroQuest(player, item);
        player.setPlayerName("Conan");
        player.setPlayerHealth(100);
        player.setPlayerStrength(20);
        player.setPlayerMagic(10);
        player.setPlayerCraftingSkill(10);
    }

    @BeforeEach
    void SetUpSecond() {
        item.setItemName("Amulet of Strength");
        item.setItemKind("Strength");
        item.setItemPower(10);
    }

    @Test
    void playerToString() {
        String expected = """
                Conan's Attributes:
                Health: 100
                Strength: 20
                Magic: 10
                Crafting Skill: 10
                """;

        String result = heroQuest.playerToString();

        assertEquals(expected, result);
    }

    @Test
    void playerFallsDown() {
        player.setPlayerStrength(3);
        heroQuest.playerFallsDown();
        assertEquals(90, player.getPlayerHealth());
    }

    @Test
    void playerFallsDownNoDamage() {
        heroQuest.playerFallsDown();
        assertEquals(100, player.getPlayerHealth());
    }

    @Test
    void itemToString() {
        var result = heroQuest.itemToString();
        var expected = "Item: Amulet of Strength\nKind: Strength\nPower: 10\n";
        assertEquals(expected, result);
    }

    @Test
    void itemReduceByUsage() {
        heroQuest.itemReduceByUsage();
        assertEquals(5, item.getItemPower());
    }

    @Test
    void itemReduceByUsageToJunk() {
        item.setItemPower(1);
        heroQuest.itemReduceByUsage();
        heroQuest.itemReduceByUsage();
        assertEquals(0, item.getItemPower());
        assertEquals("Junk", item.getItemKind());
    }

    @Test
    void itemApplyEffectToPlayer() {
        heroQuest.itemApplyEffectToPlayer();
        assertEquals(30, player.getPlayerStrength());
    }

    @Test
    void itemApplyEffectToPlayerJunk() {
        item.setItemKind("Junk");
        heroQuest.itemApplyEffectToPlayer();
        assertEquals(20, player.getPlayerStrength());
    }

    @Test
    void itemRepair() {
        heroQuest.itemRepair();
        assertEquals(26, item.getItemPower());
    }
}
