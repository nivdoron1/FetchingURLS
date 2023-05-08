import java.io.IOException;
import java.util.*;

public class UrlAdder {
    private final Source source;
    private static final Set<String> existingUrls =new HashSet<>();
    private final int depth;
    private final int maximumAmount;
    private List<HtmlBuilder> urlsToAdd = new ArrayList<>();

    /**
     * Constructor for the UrlAdder class.
     *
     * @param source The source object containing the URL and crawling parameters.
     * @throws IOException If an error occurs while initializing the UrlAdder.
     */
    public UrlAdder(Source source) throws IOException {
        this.source=source;
        this.depth = this.getSource().getDepthFactor();
        this.maximumAmount = this.getSource().getMaxAmount();
        try{
            this.urlsToAdd.add(new HtmlBuilder(source.getUrl()));
        } catch (IOException e) {
        System.err.println("Error initializing UrlAdder: " + e.getMessage());
    }
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

    private boolean addUrl(String url){
        int size= existingUrls.size();
        existingUrls.add(url);
        return existingUrls.size() != size;
    }

    /**
     * Checks if the current URL can be executed.
     *
     * @param url The URL to check.
     * @return True if the URL can be executed, false otherwise.
     */
    boolean canExecuteFile(String url){
        return !this.source.isCrossLevelUniqueness() || addUrl(url);
    }

    /**
     * Adds URLs to the UrlNames list by extracting them from the HtmlBuilder object.
     *
     * @param builder The HtmlBuilder object.
     */
    private void createUrls(HtmlBuilder builder){
        UrlExtractor extractor = new UrlExtractor(builder.getHtml(), this.maximumAmount);
        urlsToAdd.addAll(extractor.getUrlList());
    }
    /**
     * Creates a file from the current HtmlBuilder object and adds its URLs to the UrlNames list.
     *
     * @param depth The depth of the current file.
     * @throws IOException If an error occurs while creating the file.
     */
    private void createFile(int depth) throws IOException {
        HtmlBuilder builder = urlsToAdd.remove(0);
        createUrls(builder); // add the urls to the list
        if (canExecuteFile(builder.getUrl())) {
            builder.createFile(depth);
        }
    }
    /**
     * Runs the URL crawling process.
     *
     * @throws IOException If an error occurs while running the process.
     */

    public void run() throws IOException {
        try{
            createFile(0);
            for (int i = 1; i <= depth; i++) {
                int maximumUrls = (int)Math.pow(maximumAmount, i);
                for (int j = 0; j<maximumUrls && j< urlsToAdd.size(); j++) {
                    createFile(i);
                }
            }
        }catch (IOException e){
            System.err.println("Error running URL crawling process: " + e.getMessage());
        }
    }
}
