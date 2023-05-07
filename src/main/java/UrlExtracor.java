import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtracor {

    private final Document html;
    private final int MaximumAmount;
    private static final String regex = "^(https?|ftp):\\/\\/[\\-a-zA-Z0-9@:%._\\+~#=]{1,256}" +
            "\\.[a-zA-Z0-9()]{1,6}\\/[-a-zA-Z0-9@:%_\\+.~#?&\\/=]*" +
            "(?<!\\.(jpeg|jpg|gif|png|bmp|tiff|webp|pdf|txt|doc|docx|xls|xlsx|ppt|pptx|zip|tar|gz))$";

    /**
     * Constructor for the UrlExtracor class.
     *
     * @param html          The HTML content to extract URLs from.
     * @param maximumAmount The maximum amount of URLs to return.
     */

    public UrlExtracor(Document html, int maximumAmount) {
        this.html = html;
        this.MaximumAmount = maximumAmount;
    }

    /**
     * Splits the given URL into a list of valid URLs.
     *
     * @param url The URL to split.
     * @return A list of valid URLs.
     */

    protected List<String> URLSplitter(String url)
    {
        String decodedText = URLDecoder.decode(url, StandardCharsets.UTF_8);
        Pattern urlPattern = Pattern.compile("https?://\\S+");
        Matcher urlMatcher = urlPattern.matcher(decodedText);
        List<String> list = new LinkedList<>();
        while (urlMatcher.find()) {
            list.add(urlMatcher.group());
        }
        return list;
    }
    /**
     * Gets a list of HtmlBuilder objects from the extracted URLs.
     *
     * @return A list of HtmlBuilder objects.
     * @throws IOException If an error occurs while fetching the HTML content of the URLs.
     */
    public List<HtmlBuilder> getUrlList() throws IOException {
        Elements url_lists = html.select("a[href]");
        List<String> urls = new ArrayList<>();
        for (Element e : url_lists) {
            String url = e.absUrl("href");
            List<String> splitter = URLSplitter(url);
            for(String u : splitter)
            {
                if (u.matches(regex)) {
                    urls.add(u);
                }
            }
        }
        Set<String> targetSet = new HashSet<>(urls);
        targetSet.remove(null);
        List<HtmlBuilder> UrlNames = new ArrayList<>();
        Iterator<String> it = targetSet.iterator();
        for (int i = 0; i < this.MaximumAmount && it.hasNext(); i++) {
            String url = it.next();
                UrlNames.add(new HtmlBuilder(url));
        }
        return UrlNames;
    }
}
