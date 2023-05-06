import java.io.IOException;
import java.util.Scanner;

public class ExecuteHtml {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your URL website:");
        String url = scanner.nextLine();

        System.out.println("Enter your max amount of URL's that you want to execute each level:");
        int maxAmount = scanner.nextInt();

        System.out.println("Enter the depth factor:");
        int depthFactor = scanner.nextInt();

        System.out.println("Enter uniqueness write (true/false):");
        boolean crossLevelUniqness = scanner.nextBoolean();

        Source source = new Source(url, maxAmount, depthFactor, crossLevelUniqness);
        UrlAdder urlAdder = new UrlAdder(source);
        urlAdder.run();
    }
}
