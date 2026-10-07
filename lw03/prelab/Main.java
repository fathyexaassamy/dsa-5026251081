
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {

        System.out.println("===== Problem 1 =====");

        List<String> playlist = new ArrayList<>();

        InputStream inputStream =
                Main.class.getResourceAsStream("playlist.txt");

        if (inputStream == null) {
            System.out.println("playlist.txt not found");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+", 3);

            String operation = parts[0];

            if (operation.equals("ADD")) {

                String song = parts[1];

                playlist.add(song);

            } else if (operation.equals("INSERT")) {

                int index = Integer.parseInt(parts[1]);
                String song = parts[2];

                playlist.add(index, song);

            } else if (operation.equals("REMOVE")) {

                String song = parts[1];

                playlist.remove(song);
            }
        }

        scanner.close();

        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println();
    }


    public static void problem2() {

        System.out.println("===== Problem 2 =====");

        Set<String> participants = new LinkedHashSet<>();

        int duplicateRegistrations = 0;

        InputStream inputStream =
                Main.class.getResourceAsStream("participants.txt");

        if (inputStream == null) {
            System.out.println("participants.txt not found");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                continue;
            }

            if (participants.contains(name)) {

                duplicateRegistrations++;

            } else {

                participants.add(name);
            }
        }

        scanner.close();

        System.out.println(
                "Unique participants: " + participants.size()
        );

        int number = 1;

        for (String participant : participants) {

            System.out.println(
                    number + ". " + participant
            );

            number++;
        }

        System.out.println(
                "Duplicate registrations: "
                        + duplicateRegistrations
        );

        System.out.println();
    }

    public static void problem3() {

        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory =
                new LinkedHashMap<>();

        int failedSales = 0;

        InputStream inputStream =
                Main.class.getResourceAsStream("inventory.txt");

        if (inputStream == null) {
            System.out.println("inventory.txt not found");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {

                if (inventory.containsKey(product)) {

                    int currentStock =
                            inventory.get(product);

                    inventory.put(
                            product,
                            currentStock + quantity
                    );

                } else {

                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {

                if (inventory.containsKey(product)
                        && inventory.get(product) >= quantity) {

                    int currentStock =
                            inventory.get(product);

                    inventory.put(
                            product,
                            currentStock - quantity
                    );

                } else {

                    failedSales++;
                }
            }
        }

        scanner.close();

        for (Map.Entry<String, Integer> entry
                : inventory.entrySet()) {

            System.out.println(
                    entry.getKey() + ": "
                            + entry.getValue()
            );
        }

        System.out.println(
                "Failed sales: " + failedSales
        );
    }
}