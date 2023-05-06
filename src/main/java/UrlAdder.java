import java.io.IOException;
import java.util.*;

public class UrlAdder {
    private final Source source;
    private static final Set<String> urls =new HashSet<>();
    private final int depth;
    private final int MaximumAmont;
    private List<HtmlBuilder> UrlNames = new ArrayList<>();

    public UrlAdder(Source source) throws IOException {
        this.source=source;
        this.depth = this.getSource().getDepthFactor();
        this.MaximumAmont = this.getSource().getMaxAmount();
        this.UrlNames.add(new HtmlBuilder(source.getUrl()));

    }

    public Source getSource() {
        return source;
    }

    private boolean AddURL(String url){
        int size=urls.size();
        urls.add(url);
        return urls.size() != size;
    }

    private boolean canExecuteFile(String url){
        return !this.source.isCrossLevelUniqness() || AddURL(url);
    }

    private void createUrls(String url) throws IOException {
        HtmlBuilder builder = new HtmlBuilder(url);
        UrlExtracor extractor = new UrlExtracor(builder.getHtml(), MaximumAmont);
        UrlNames.addAll(extractor.getUrlList());
    }

    private void createFile(int depth) throws IOException {
        HtmlBuilder builder = UrlNames.remove(0);
        createUrls(builder.getUrl());
        if (canExecuteFile(builder.getUrl())) {
            builder.CreateFile(depth);
        }
    }
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
