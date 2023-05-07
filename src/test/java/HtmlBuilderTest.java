import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class HtmlBuilderTest {
    @Test
    void convertFileName() {
        assertEquals("example_com", HtmlBuilder.convertFileName("https://example.com"));
        assertEquals("example_com_page1", HtmlBuilder.convertFileName("https://example.com/page1"));
        assertEquals("example_com_file_with_space", HtmlBuilder.convertFileName("https://example.com/file with space"));
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
