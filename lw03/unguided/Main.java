import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<String> registerd = new LinkedHashSet<>();
        Set<String> checkedIn = new LinkedHashSet<>(); 

        Scanner sc1 = new Scanner(
                Main.class.getResourceAsStream("registrations.txt")
        );


        while (sc1.hasNextLine()) {

            String id = sc1.nextLine();//ngambil nama
            registerd.add(id);
        }

        sc1.close();

        Scanner sc2 = new Scanner(
                Main.class.getResourceAsStream("checkins.txt")
        );

        int rejectedAttemps = 0;

        System.out.println("===== Event Check-In Results =====:");

        while (sc2.hasNextLine()) {
            String id = sc2.nextLine();

            if (!registerd.contains(id)) {
                System.out.println(id + " Rejected (not registered)");
               rejectedAttemps++;
                } else if (checkedIn.contains(id)) {
                    System.out.println(id +"Rejected (already checked in)");
                rejectedAttemps++;
                } else {
                    checkedIn.add(id);
                    System.out.println("Checked in: " + id);
                }
            }

            int absentAttempts = registerd.size() - checkedIn.size();

            System.out.println("===== Final Event Summary =====");
            System.out.println("Registered students: " + registerd.size());
            System.out.println("Successful check-ins: " + checkedIn.size());
            System.out.println("Absent attempts: " + absentAttempts);
            System.out.println("Rejected attempts: " + rejectedAttemps);
        }
        
    }