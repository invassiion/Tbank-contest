
import java.util.Scanner;

public class FromIntervalToLine {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        StringBuilder result = new StringBuilder();
        String[] intervals = input.split(",");
        boolean isFirst = true;
        for (String interval : intervals) {
            if (interval.contains("-")) {
                String[] bounds = interval.split("-");
                int start = Integer.parseInt(bounds[0]);
                int end = Integer.parseInt(bounds[1]);

                for (int i = start; i <= end; i++){
                   if (!isFirst) {
                       result.append(" ");
                   }
                   result.append(i);
                   isFirst = false;
                }
            } else {
                if (!isFirst) {
                    result.append(" ");
                }
                result.append(Integer.parseInt(interval));
                isFirst = false;
            }
        }
        System.out.println(result.toString());
    }

}
