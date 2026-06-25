import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Spy
    private ArrayList<Ingredient> spyIngredients = new ArrayList<>();

    @Before
    public void setUp() {
        burger = new Burger();
        // Внедряем spy-список напрямую в объект
        burger.ingredients = spyIngredients;
    }

    @Test
    public void testSetBunsSetsBunCorrectly() {
        burger.setBuns(mockBun);
        Assert.assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientCallsListAdd() {
        burger.addIngredient(mockIngredient1);

        // Проверяем, что ингредиент добавился в список
        Assert.assertEquals(1, burger.ingredients.size());
        // Проверяем, что метод add списка был действительно вызван (работа @Spy)
        Mockito.verify(spyIngredients).add(mockIngredient1);
    }

    @Test
    public void testRemoveIngredientCallsListRemove() {
        burger.ingredients.add(mockIngredient1);
        burger.removeIngredient(0);

        Assert.assertTrue(burger.ingredients.isEmpty());
        Mockito.verify(spyIngredients).remove(0);
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        burger.ingredients.add(mockIngredient1); // индекс 0
        burger.ingredients.add(mockIngredient2); // индекс 1

        burger.moveIngredient(0, 1);

        // Проверяем, что элементы поменялись местами
        Assert.assertEquals(mockIngredient2, burger.ingredients.get(0));
        Assert.assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetReceiptGeneratesCorrectFormat() {
        // Подготовка стабов (Stubs)
        Mockito.when(mockBun.getName()).thenReturn("Белая булка");
        Mockito.when(mockBun.getPrice()).thenReturn(80.0f);

        // Мокаем перечисление (Enum) типа ингредиента. Предположим, там есть SAUCE.
        // Замените IngredientType.SAUCE на ваш реальный Enum, если он называется иначе.
        Mockito.when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient1.getName()).thenReturn("Кетчуп");
        Mockito.when(mockIngredient1.getPrice()).thenReturn(30.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        // Проверяем наличие ключевых строк в чеке
        Assert.assertTrue(receipt.contains("(==== Белая булка ====)"));
        Assert.assertTrue(receipt.contains("= sauce Кетчуп ="));
        Assert.assertTrue(receipt.contains("Price: 190,000000") || receipt.contains("Price: 190.000000"));
    }
}