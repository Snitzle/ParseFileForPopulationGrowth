import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File file = new File("src/populationHistory.txt");

        try {

            ArrayList<long[]> lines = new ArrayList<>();
            Scanner s = new Scanner(file);
            long delta = 0, temp = 0;

            s.nextLine(); // skip text at top of file

            while ( s.hasNextLine()) {

                // Lines are read not words. Parse the line.
                String[] input = s.nextLine().split("\\s+"); // array of the line

                // Remove formatting from population
                String pop = input[1].replaceAll("[,]", "");

                Long year = Long.parseLong(input[0]);
                Long conv = Long.parseLong(pop.trim());

                long[] convLine = {year, conv};
                lines.add(convLine);

            }

//            for ( long[] arr : lines) {
//                System.out.println(Arrays.toString(arr));
//            }

            long reportYear = 0;

            // Start at second value
            for (int i = 1; i < lines.size(); i++) {

                long[] currentRow = lines.get(i);
                long currentYear = currentRow[1];

                long[] previousRow = lines.get(i-1);
                long previousYear = previousRow[1];


                if ( currentYear - previousYear > delta) {
                    delta = currentYear - previousYear;
                    reportYear = currentRow[0];
                }

            }

            System.out.println(reportYear);

        } catch (FileNotFoundException e) {
            System.out.println(e.getClass().getSimpleName());
        }

    }

}
