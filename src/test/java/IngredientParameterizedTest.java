import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Барбекю", 30.0f},
                {IngredientType.FILLING, "Бекон", 50.0f},
                {IngredientType.SAUCE, "", 0.0f},           // Граничное значение: пустое имя и нулевая цена
                {IngredientType.FILLING, null, -15.0f}       // Граничное значение: null-имя и отрицательная цена
        });
    }

    @Test
    public void testIngredientGetTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
    }

    @Test
    public void testIngredientGetNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(name, ingredient.getName());
    }

    @Test
    public void testIngredientGetPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(price, ingredient.getPrice(), 0.001);
    }
}