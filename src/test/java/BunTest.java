import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {

    @Test
    public void testGetNameReturnsCorrectName() {
        Bun bun = new Bun("Бриошь", 110.0f);
        Assert.assertEquals("Бриошь", bun.getName());
    }

    @Test
    public void testGetPriceReturnsCorrectPrice() {
        Bun bun = new Bun("Кунжутная", 80.4f);
        Assert.assertEquals(80.4f, bun.getPrice(), 0.001);
    }
}
