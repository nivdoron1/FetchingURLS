import java.io.IOException;

public class ExecuteHtml {
    public static void main(String[] args) throws IOException {
        Source source = new Source("https://www.n12.co.il/",5,3,true);
        UrlAdder urlAdder = new UrlAdder(source);
        urlAdder.run();
    }
}
