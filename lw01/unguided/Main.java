import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<WashService> wash = new ArrayList<>();

        Scanner sc = new Scanner (Main.class.getResourceAsStream("/washes.txt"));

        while (sc.hasNext()) { //has.next() ngecek ada gak interger yang bisa diambil
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int jumlah = sc.nextInt();

            if (type.equals("CAR")) {
                wash.add(new CarWash(id, days, jumlah));
            }else if (type.equals("MOTORCYCLE")){
                wash.add(new MotorcycleWash(id, days, jumlah));
            }
        }
        sc.close();

        //print
        for (WashService service : wash) {
            System.out.println(service.summary());
        }
    }
}
