import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;


class HtmlBuilderTest {
    @Test
    void testConvertFileName_NullInput() {
        String fileName = null;
        String expected = null;
        assertEquals(expected, HtmlBuilder.convertFileName(fileName));
    }

    @Test
    void testConvertFileName_EmptyInput() {
        String fileName = "";
        String expected = "";
        assertEquals(expected, HtmlBuilder.convertFileName(fileName));
    }

    @Test
    void testConvertFileName_ValidInput() {
        String fileName = "file-name_123.txt";
        String expected = "file-name_123.txt";
        assertEquals(expected, HtmlBuilder.convertFileName(fileName));
    }

    @Test
    void testConvertFileName_InvalidCharacters() {
        String fileName = "file!@#^&*()_name.txt";
        String expected = "file__name.txt";
        assertEquals(expected, HtmlBuilder.convertFileName(fileName));
    }

    @Test
    void testConvertFileName_MixedCharacters() {
        String fileName = "file!@#-_name$%^123&*.txt";
        String expected = "file_-_name_123_.txt";
        assertEquals(expected, HtmlBuilder.convertFileName(fileName));
    }

    @Test
    void fetchHtml() {
        try {
            HtmlBuilder builder = new HtmlBuilder("https://example.com");
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while fetching HTML");
        }
    }
}
