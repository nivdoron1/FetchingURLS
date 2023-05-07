import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class HtmlBuilderTest {
    @Test
    void convertFileName() {
        assertEquals("example_com", HtmlBuilder.convertFileName("https://www.ynet.co.il/"));
        assertEquals("example_com_page1", HtmlBuilder.convertFileName("https://www.ynet.co.il/home/0,7340,L-,00.html"));
        assertEquals("example_com_file_with_space", HtmlBuilder.convertFileName("https://www.ynet.co.il/  with space"));
    }

    @Test
    void fetchHtml() {
        try {
            HtmlBuilder builder = new HtmlBuilder("https://www.ynet.co.il/");
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while fetching HTML");
        }
    }
}
