
import java.util.Scanner;

public class EternalWinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] records = new int[n];

        for (int i = 0; i < n; i++){
            records[i] = scanner.nextInt();
        }

        if (restoreSnowData(records, n)) {
            System.out.println("YES");
            for (int i = 0; i < n; i++) {
               if (i == 0) {
                   System.out.print(records[i] + " ");
               } else {
                   System.out.print((records[i] - records[i - 1]) + " ");
               }
            }
        } else {
            System.out.println("NO");
        }

    }

    private static boolean restoreSnowData(int[] records, int n) {
        int lastMeaning = 0;

        for (int i = 0; i < n; i++) {
            if (records[i] != -1) {
                if (records [i] < lastMeaning) {
                    return  false;
                }
                lastMeaning = records[i];
            } else {
                int nextMeaning = findNextMeaning(records,i,n);
                if (nextMeaning != -1 && nextMeaning < lastMeaning) {
                    return false;
                }
                records[i] = lastMeaning + 1;
                lastMeaning = records[i];
            }
        }
        return true;
    }

    private static int findNextMeaning(int[] records, int startIndex, int n) {
        for (int i = startIndex + 1; i < n; i++) {
            if (records[i] != -1) {
                return records[i];
            }
        }
        return -1;

    }


}
