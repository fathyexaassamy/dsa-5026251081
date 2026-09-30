import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList<String[]> foodList = new LinkedList<>();
        LinkedList<String[]> drinkList = new LinkedList<>();
        LinkedList<String[]> storedOrders = new LinkedList<>();
        LinkedList<String[]> processedOrders = new LinkedList<>();

        Queue<String[]> ordersQueue = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        // 1. Read transactions.txt and store all transactions in LinkedList
        try {
            Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\s+");
                orders.add(data);
        } 
        input.close();

        foodList.add(new String[]("Bakso", 2));
        foodList.add(new String[]("Sate", 1));
        foodList.add(new String[]("Soto", 2));

        drinkList.add(new String[]("Es Teh", 4));
        drinkList.add(new String[]("Es Jeruk", 2));

        // 3. Move transactions from LinkedList to Queue
        while (!foodList.isEmpty()) {
            ordersQueue.offer(foodList.removeFirst());
        }

        // 4. Process Queue using FIFO
        while (!ordersQueue.isEmpty()) {
            String[] order = ordersQueue.poll();

            String name = order[0];
            String food = order[1];
            String drink = order[2];
            int amount = Integer.parseInt(order[3]);

            // Find the corresponding customer
            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    int stock = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        stock += amount;
                        customer[1] = String.valueOf(stock);

                    } else if (type.equals("WITHDRAW")) {
                        if (amount > stock) {
                            // Withdrawal failed, stock remains unchanged
                            failedOrders.push(order);
                        } else {
                            stock -= amount;
                            customer[1] = String.valueOf(stock);
                        }
                    }

                    break;
                }
            }
        }

        // 5. Display final balances
        System.out.println("=== Successfully Processed Orders ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        // 6. Display failed withdrawals in LIFO order
        System.out.println("=== Failed Transactions ===");

        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2]
            );
        }
    }
}


