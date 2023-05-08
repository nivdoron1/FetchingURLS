import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlExtracorTest {
    @Test
    void urlSplitter() {
        UrlExtractor extractor = new UrlExtractor(null, 0);
        List<String> urls = extractor.URLSplitter("https://www.ynet.co.il/ https://www.ynet.co.il/entertainment");
        assertEquals(2, urls.size());
        assertEquals("https://www.ynet.co.il/", urls.get(0));
        assertEquals("https://www.ynet.co.il/entertainment", urls.get(1));
    }

}
