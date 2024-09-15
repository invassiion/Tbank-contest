import java.util.Scanner;

public class SimpleNum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        int count = 0;

        for (int i = start; i<= end; i++) {
            if (i > 1 && !isPrime(i)) {
                int dividerCount = countDividers(i);
                if (isPrime(dividerCount)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static int countDividers(int n) {
        int count = 0;
        for (int i = 1; i<= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i == i) {
                    count++;
                } else {
                    count += 2;
                }
            }
        }

        return count;
    }


    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <=number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
