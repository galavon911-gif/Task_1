import org.junit.Assert;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTest {

    @Test
    public void testGetTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Кетчуп", 17.0f);
        Assert.assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void testGetNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Котлета", 110.5f);
        Assert.assertEquals("Котлета", ingredient.getName());
    }

    @Test
    public void testGetPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Майонез", 15.0f);
        Assert.assertEquals(15.0f, ingredient.getPrice(), 0.001);
    }
}