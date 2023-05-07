import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.regex.Pattern;

public class HtmlBuilder {
    private final String url;
    private final Document html;

    /**
     * Constructor for the HtmlBuilder class.
     *
     * @param url The URL to fetch the HTML content from.
     * @throws IOException If an error occurs while fetching the HTML content.
     */
    public HtmlBuilder(String url) throws IOException {
        this.url=url;
        this.html = fetchHtml();
    }
    /**
     * Gets the URL for the HtmlBuilder instance.
     *
     * @return The URL as a string.
     */
    public String getUrl() {
        return url;
    }
    /**
     * Gets the fetched HTML content for the HtmlBuilder instance.
     *
     * @return The fetched HTML content as a Document object.
     */
    public Document getHtml() {
        return html;
    }


    private Document fetchHtml() throws IOException {
        Document document = null;
        document = Jsoup.connect(getUrl()).get();
        return document;
    }
    /**
     * Creates a new HTML file in the depth directory.
     *
     * @param depth The depth level for the directory.
     * @throws IOException If an error occurs while creating the file.
     */
    public void createFile(int depth)throws IOException {
        File dir = new File(String.valueOf(depth));
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileName = convertFileName(url);
        File file = new File(dir,fileName+".html");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(html.html());
        }
        catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }

    }
    /**
     * Converts the given file name into a valid file name by replacing invalid characters.
     *
     * @param fileName The original file name.
     * @return The converted file name as a string.
     */
    public static String convertFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return fileName;
        }
        String regex = "[^a-zA-Z0-9\\.\\-\\_]+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(fileName).replaceAll("_");
    }
}
