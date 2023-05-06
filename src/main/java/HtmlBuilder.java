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

    // constructor
    public HtmlBuilder(String url) throws IOException {
        this.url=url;
        this.html = fetchHtml();
    }

    public String getUrl() {
        return url;
    }

    public Document getHtml() {
        return html;
    }

    //read the html file
    private Document fetchHtml() throws IOException {
        Document document = null;
        document = Jsoup.connect(getUrl()).get();
        return document;
    }
    //create a new file in the depth directory
    public void CreateFile(int depth)throws IOException {
        File dir = new File(String.valueOf(depth));
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileName = convertFileName(url);
        File file = new File(dir,fileName+".html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(html.html());
        writer.close();
    }
    public static String convertFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return fileName;
        }
        String regex = "[^a-zA-Z0-9\\.\\-\\_]+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(fileName).replaceAll("_");
    }
}
