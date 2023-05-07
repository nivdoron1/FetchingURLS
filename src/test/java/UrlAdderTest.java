import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

class UrlAdderTest {
    @Test
    void run() {
        Source source = new Source("https://www.ynet.co.il/", 5, 2, true);
        try {
            UrlAdder urlAdder = new UrlAdder(source);
            assertDoesNotThrow(() -> urlAdder.run());
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while running UrlAdder");
        }
    }
}
