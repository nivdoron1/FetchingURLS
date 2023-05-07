import java.io.IOException;
import java.util.*;

public class UrlAdder {
    private final Source source;
    private static final Set<String> urls =new HashSet<>();
    private final int depth;
    private final int MaximumAmont;
    private List<HtmlBuilder> UrlNames = new ArrayList<>();

    /**
     * Constructor for the UrlAdder class.
     *
     * @param source The source object containing the URL and crawling parameters.
     * @throws IOException If an error occurs while initializing the UrlAdder.
     */
    public UrlAdder(Source source) throws IOException {
        this.source=source;
        this.depth = this.getSource().getDepthFactor();
        this.MaximumAmont = this.getSource().getMaxAmount();
        this.UrlNames.add(new HtmlBuilder(source.getUrl()));

    }
    /**
     * Gets the Source object for the UrlAdder.
     *
     * @return The Source object.
     */
    public Source getSource() {
        return source;
    }

    /**
     * Adds a URL to the set of URLs and checks if it was added successfully.
     *
     * @param url The URL to add.
     * @return True if the URL was added successfully, false otherwise.
     */

    private boolean AddURL(String url){
        int size=urls.size();
        urls.add(url);
        return urls.size() != size;
    }

    /**
     * Checks if the current URL can be executed.
     *
     * @param url The URL to check.
     * @return True if the URL can be executed, false otherwise.
     */
    private boolean canExecuteFile(String url){
        return !this.source.isCrossLevelUniqness() || AddURL(url);
    }

    /**
     * Adds URLs to the UrlNames list by extracting them from the HtmlBuilder object.
     *
     * @param builder The HtmlBuilder object.
     * @throws IOException If an error occurs while creating the URLs.
     */
    private void createUrls(HtmlBuilder builder) throws IOException {
        UrlExtracor extractor = new UrlExtracor(builder.getHtml(), this.MaximumAmont);
        UrlNames.addAll(extractor.getUrlList());
    }
    /**
     * Creates a file from the current HtmlBuilder object and adds its URLs to the UrlNames list.
     *
     * @param depth The depth of the current file.
     * @throws IOException If an error occurs while creating the file.
     */
    private void createFile(int depth) throws IOException {
        HtmlBuilder builder = UrlNames.remove(0);
        createUrls(builder); // add the urls to the list
        if (canExecuteFile(builder.getUrl())) {
            builder.CreateFile(depth);
        }
    }
    /**
     * Runs the URL crawling process.
     *
     * @throws IOException If an error occurs while running the process.
     */

    public void run() throws IOException {
        createFile(0);
        for (int i = 1; i <= depth; i++) {
            int m = (int)Math.pow(MaximumAmont, i);
            for (int j = 0; j<m && j< UrlNames.size(); j++) {
                createFile(i);
            }
        }
    }
}
