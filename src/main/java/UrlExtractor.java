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

public class UrlExtractor {

    private final Document html;
    private final int maximumAmount;
    private static final String regex = "^(https?|ftp):\\/\\/[\\-a-zA-Z0-9@:%._\\+~#=]{1,256}" +
            "\\.[a-zA-Z0-9()]{1,6}\\/[-a-zA-Z0-9@:%_\\+.~#?&\\/=]*" +
            "(?<!\\.(jpeg|jpg|gif|png|bmp|tiff|webp|pdf|txt|doc|docx|xls|xlsx|ppt|pptx|zip|tar|gz))$";

    /**
     * Constructor for the UrlExtractor class.
     *
     * @param html          The HTML content to extract URLs from.
     * @param maximumAmount The maximum amount of URLs to return.
     */

    public UrlExtractor(Document html, int maximumAmount) {
        this.html = html;
        this.maximumAmount = maximumAmount;
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
            String newUrl = urlMatcher.group().replaceAll("\\s","");
            if (newUrl.matches(regex)) {
                list.add(newUrl);
            }
        }
        return list;
    }
    /**
     * Gets a list of HtmlBuilder objects from the extracted URLs.
     *
     * @return A list of HtmlBuilder objects.
     * @throws Exception If an error occurs while fetching the HTML content of the URLs.
     */
    public List<HtmlBuilder> getUrlList() {
        Elements urlLists = html.select("a[href]");
        List<String> urls = new LinkedList<>();
        for (Element e : urlLists) {
            String url = e.absUrl("href");
            urls.addAll(URLSplitter(url));
        }
        Set<String> urlsSet = new HashSet<>(urls);
        List<HtmlBuilder> urlBuilders = new LinkedList<>();
        Iterator<String> it = urlsSet.iterator();
        for (int i = 0; i < this.maximumAmount && it.hasNext(); i++) {
            String url = it.next();
            try{
                urlBuilders.add(new HtmlBuilder(url));
            }catch (IOException e) {
                System.err.println("Error fetching HTML content: " + e.getMessage());
            }
        }
        return urlBuilders;
    }
}
