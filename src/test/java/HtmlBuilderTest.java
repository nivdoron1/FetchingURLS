import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        HtmlBuilder builder = new HtmlBuilder("https://example.com");
    }
}
