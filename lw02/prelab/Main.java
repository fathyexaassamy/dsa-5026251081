import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();

        // 1. Read transactions.txt and store all transactions in LinkedList
        try {
            Scanner scanner = new Scanner(new File("transactions.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\s+");

                String name = data[0];
                String type = data[1];
                String amount = data[2];

                transactions.add(new String[]{name, type, amount});

                // 2. Add customer only on their first appearance
                boolean customerExists = false;

                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        customerExists = true;
                        break;
                    }
                }

                if (!customerExists) {
                    customers.add(new String[]{name, "0"});
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("transactions.txt tidak ditemukan.");
            return;
        }

        // 3. Move transactions from LinkedList to Queue
        while (!transactions.isEmpty()) {
            transactionQueue.offer(transactions.removeFirst());
        }

        // 4. Process Queue using FIFO
        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            // Find the corresponding customer
            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            // Withdrawal failed, balance remains unchanged
                            failedTransactions.push(transaction);
                        } else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        // 5. Display final balances
        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        // 6. Display failed withdrawals in LIFO order
        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {
            String[] transaction = failedTransactions.pop();

            System.out.println(
                transaction[0] + " " +
                transaction[1] + " " +
                transaction[2]
            );
        }
    }
}

