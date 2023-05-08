import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class UrlAdderTest {
    @Test
    void run() {
        Source source = new Source("https://www.ynet.co.il/", 5, 2, true);
        try {
            UrlAdder urlAdder = new UrlAdder(source);
            assertDoesNotThrow(urlAdder::run);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while running UrlAdder");
        }
    }
    private UrlAdder urlAdderCrossLevelUniquenessTrue;
    private UrlAdder urlAdderCrossLevelUniquenessFalse;

    @BeforeEach
    void setUp() throws IOException {
        Source sourceCrossLevelUniquenessTrue = new Source("https://www.example.com", 2, 2, true);
        urlAdderCrossLevelUniquenessTrue = new UrlAdder(sourceCrossLevelUniquenessTrue);

        Source sourceCrossLevelUniquenessFalse = new Source("https://www.example.com", 2, 2, false);
        urlAdderCrossLevelUniquenessFalse = new UrlAdder(sourceCrossLevelUniquenessFalse);
    }

    @Test
    void testCanExecuteFile_CrossLevelUniquenessTrue() {
        String url = "https://www.example.com/test-page";
        assertTrue(urlAdderCrossLevelUniquenessTrue.canExecuteFile(url), "URL should be executable if it's unique");

        assertFalse(urlAdderCrossLevelUniquenessTrue.canExecuteFile(url), "URL should not be executable if it's not unique");
    }

    @Test
    void testCanExecuteFile_CrossLevelUniquenessFalse() {
        String url = "https://www.example.com/test-page";
        assertTrue(urlAdderCrossLevelUniquenessFalse.canExecuteFile(url), "URL should be executable regardless of uniqueness");
        assertTrue(urlAdderCrossLevelUniquenessFalse.canExecuteFile(url), "URL should be executable regardless of uniqueness");
    }
}
