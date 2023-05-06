import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class UrlExtracor {

    private final Document html;
    private final int maximumAmount;

    public UrlExtracor(Document html, int maximumAmount) {
        this.html = html;
        this.maximumAmount = maximumAmount;
    }

    public List<HtmlBuilder> getUrlList() {
        Elements url_lists = html.select("a[href]");
        List<String> urls = url_lists.stream().map(e -> {
            String url = e.absUrl("href");
            String regex = "^(https?|ftp):\\/\\/[\\-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\/[-a-zA-Z0-9@:%_\\+.~#?&\\/=]*(?<!\\.(jpeg|jpg|gif|png|bmp|tiff|webp|pdf|txt|doc|docx|xls|xlsx|ppt|pptx|zip|tar|gz))$";
            if (url.matches(regex)) {
                return url;
            }
            return null;
        }).toList();
        Set<String> targetSet = new HashSet<>(urls);
        targetSet.remove(null);
        List<HtmlBuilder> url_names = new ArrayList<>();
        Iterator<String> it = targetSet.iterator();
        for (int i = 0; i < maximumAmount && it.hasNext(); i++) {
            String url = it.next();
            try {
                url_names.add(new HtmlBuilder(url));
            } catch (HttpStatusException e) {
                System.err.println("HTTP error fetching URL: " + url + ", Status: " + e.getStatusCode());
            } catch (IOException e) {
                System.err.println("Error fetching URL: " + url);
            }
        }
        return url_names;
    }
}
