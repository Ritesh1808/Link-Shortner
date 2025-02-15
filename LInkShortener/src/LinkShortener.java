import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LinkShortener {
    private HashMap<String, String> urlMap; // Store short and long URL mappings
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private Random random;

    public LinkShortener() {
        urlMap = new HashMap<>();
        random = new Random();
    }

    // Method to generate a short URL
    public String shortenURL(String longURL) {
        if (urlMap.containsValue(longURL)) {
            for (String key : urlMap.keySet()) {
                if (urlMap.get(key).equals(longURL)) {
                    return key; // Return existing short URL
                }
            }
        }

        String shortURL;
        do {
            shortURL = generateShortURL();
        } while (urlMap.containsKey(shortURL));  // Ensure uniqueness

        urlMap.put(shortURL, longURL);
        return shortURL;
    }

    // Method to expand a short URL
    public String expandURL(String shortURL) {
        return urlMap.getOrDefault(shortURL, "Invalid short URL");
    }

    // Generates a random 6-character short URL
    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortURL.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortURL.toString();
    }

    // Main method for CLI interaction
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Link Shortener Menu =====");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                System.out.print("Enter Long URL: ");
                String longURL = scanner.nextLine();
                String shortURL = linkShortener.shortenURL(longURL);
                System.out.println("Shortened URL: " + shortURL);
            } else if (choice == 2) {
                System.out.print("Enter Short URL: ");
                String shortURL = scanner.nextLine();
                System.out.println("Original URL: " + linkShortener.expandURL(shortURL));
            } else {
                System.out.println("Exiting program...");
                break;
            }
        }
        scanner.close();
    }
}
