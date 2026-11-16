import java.io.File;
import java.util.Scanner;

public class Main {

    // প্রতিটি ডিরেক্টরি ও সাবডিরেক্টরির সাইজ রিকার্সিভলি বের করার মেথড
    public static long calculateDirectorySize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else if (file.isDirectory()) {
                    size += calculateDirectorySize(file);
                }
            }
        }

        // প্রতিটি ডিরেক্টরি ও সাবডিরেক্টরির আলাদা স্পেস প্রিন্ট করা (Additional Functionality)
        System.out.printf("Directory: %s -> %d bytes (%.2f KB | %.2f MB)%n",
                dir.getPath(), size, size / 1024.0, size / (1024.0 * 1024.0));

        return size;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter directory path: ");
        String path = scanner.nextLine();

        File directory = new File(path);

        // ডিরেক্টরি আছে কি না চেক করা
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Error: The specified directory does not exist.");
        } else {
            System.out.println("\n--- Individual Directory & Subdirectory Sizes ---");
            long totalBytes = calculateDirectorySize(directory);

            double totalKB = totalBytes / 1024.0;
            double totalMB = totalBytes / (1024.0 * 1024.0);

            System.out.println("\n--- Total Size ---");
            System.out.println("Bytes: " + totalBytes + " B");
            System.out.printf("Kilobytes: %.2f KB%n", totalKB);
            System.out.printf("Megabytes: %.2f MB%n", totalMB);
        }

        scanner.close();
    }
}