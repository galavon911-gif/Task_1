import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {50.0f, new float[]{10.0f, 20.0f}, 130.0f},  // (50 * 2) + 10 + 20 = 130
                {0.0f, new float[]{15.5f}, 15.5f},           // (0 * 2) + 15.5 = 15.5
                {100.0f, new float[]{}, 200.0f},             // Без ингредиентов: 100 * 2 = 200
                {75.5f, new float[]{0.0f}, 151.0f}           // Бесплатный ингредиент: 75.5 * 2 = 151
        });
    }

    @Test
    public void testGetPriceCalculatesCorrectly() {
        Burger burger = new Burger();

        // Создаем мок булочки и задаем цену через стаб
        Bun mockBun = Mockito.mock(Bun.class);
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        // Создаем моки ингредиентов динамически на основе параметров
        for (float price : ingredientPrices) {
            Ingredient mockIngredient = Mockito.mock(Ingredient.class);
            Mockito.when(mockIngredient.getPrice()).thenReturn(price);
            burger.addIngredient(mockIngredient);
        }

        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }
}