import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        // TC1: allItems = null
        RuntimeException ex1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));
        assertEquals("allItems list can't be null!", ex1.getMessage());

        // TC2: item with null name
        Item invalidItem = new Item(null, 1, 100, 0);
        List<Item> list2 = List.of(invalidItem);
        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list2, "1234567890123456"));
        assertEquals("Invalid item!", ex2.getMessage());

        // TC3: valid item without discount
        Item item = new Item("Item1", 1, 100, 0);
        double result = SILab2.checkCart(List.of(item), "1234567890123456");
        assertEquals(100, result);
    }

    @Test
    public void testMultipleCondition() {
        // F F F
        Item item1 = new Item("Item", 1, 100, 0.0);
        assertEquals(100, SILab2.checkCart(List.of(item1), "1234567890123456"));

        // T F F
        Item item2 = new Item("Item", 1, 400, 0.0);
        assertEquals(370, SILab2.checkCart(List.of(item2), "1234567890123456")); // 400 - 30

        // F T F
        Item item3 = new Item("Item", 1, 100, 0.1);
        assertEquals(60, SILab2.checkCart(List.of(item3), "1234567890123456")); // -30 + 90

        // F F T
        Item item4 = new Item("Item", 11, 100, 0.0);
        assertEquals(1070, SILab2.checkCart(List.of(item4), "1234567890123456")); // -30 + 1100
    }
}
