import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;


import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlExtracorTest {
    @Test
    void urlSplitter() {
        UrlExtracor extractor = new UrlExtracor(null, 0);
        List<String> urls = extractor.URLSplitter("https://example.com https://example.org");
        assertEquals(2, urls.size());
        assertEquals("https://example.com", urls.get(0));
        assertEquals("https://example.org", urls.get(1));
    }

    @Test
    void getUrlList() {
        try {
            HtmlBuilder builder = new HtmlBuilder("https://example.com");
            UrlExtracor extractor = new UrlExtracor(builder.getHtml(), 5);
            List<HtmlBuilder> urls = extractor.getUrlList();
            // Add assertions based on the expected URLs.
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while getting URL list");
        }
    }
}
