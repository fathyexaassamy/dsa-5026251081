import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<PrintJob> jobs = new ArrayList<>();

        Scanner sc = new Scanner (new File("lw01/prelab/jobs.txt"));

        while (sc.hasNext()) {
            String type = sc.next();
            String id = sc.next();
            int pages = sc.nextInt();

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            }else if (type.equals("COLOUR")){
                jobs.add(new ColourPrint(id, pages));
            }
        }

        //print
        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}