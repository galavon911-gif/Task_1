import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final String enumName;
    private final IngredientType expectedType;

    public IngredientTypeTest(String enumName, IngredientType expectedType) {
        this.enumName = enumName;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"SAUCE", IngredientType.SAUCE},
                {"FILLING", IngredientType.FILLING}
        });
    }

    @Test
    public void testEnumSize() {
        // Проверяем, что в Enum ровно 2 элемента и не появилось лишних без ведома разработчика
        Assert.assertEquals(2, IngredientType.values().length);
    }
}