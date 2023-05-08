import java.io.IOException;
import java.util.Scanner;

public class App {
    /**
     * Prompts the user for input and initializes the URL crawling process.
     *
     * @param args The command line arguments.
     * @throws IOException If an error occurs during the crawling process.
     **/
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your URL website:");
        String url = scanner.nextLine();

        System.out.println("Enter your max amount of URL's that you want to execute each level:");
        int maxAmount = scanner.nextInt();

        System.out.println("Enter the depth factor:");
        int depthFactor = scanner.nextInt();

        System.out.println("Enter uniqueness write (true/false):");
        boolean crossLevelUniqueness = scanner.nextBoolean();

        Source source = new Source(url, maxAmount, depthFactor, crossLevelUniqueness);
        UrlAdder urlAdder = new UrlAdder(source);
        urlAdder.run();
        System.exit(0);
    }
}
