import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

public class renditTest{

    @Test
    public void testRendit() {
        
       top8 yourObject = new top8();
        double[] rating = {3.5, 4.2, 2.8, 5.0, 3.0};
        String[][] emrat = {
                {"John"},
                {"Alice"},
                {"Bob"},
                {"Eve"},
                {"Charlie"}
        };

        yourObject.rendit(emrat, rating);

        double[] expectedRating = {5.0, 4.2, 3.5, 3.0, 2.8};
        String[][] expectedEmrat = {
                {"Eve"},
                {"Alice"},
                {"John"},
                {"Charlie"},
                {"Bob"}
        };

        assertArrayEquals(expectedRating, rating, 0.001, "Array 'rating' should be sorted in descending order");
        assertArrayEquals(expectedEmrat, emrat, "Array 'emrat' should be sorted accordingly");
    }
}