import java.util.*;

public class RestorePassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        String charSet = scanner.nextLine();
        int k = scanner.nextInt();

        Set<Character> chars = new HashSet<>();
        for (char c : charSet.toCharArray()) {
            chars.add(c);
        }


        String password = "-1";

        int left = 0;

        Map<Character, Integer> substringCounts = new HashMap<>();
        int matchedChars = 0;

        for (int right = 0; right < sequence.length(); right++) {
            char rightChar = sequence.charAt(right);
            substringCounts.put(rightChar,substringCounts.getOrDefault(rightChar, 0) + 1);

            if (chars.contains(rightChar) && substringCounts.get(rightChar) == 1) {
                matchedChars++;
            }

            while (right - left + 1 > k) {
                char leftChar = sequence.charAt(left);
                substringCounts.put(leftChar, substringCounts.get(leftChar) -1);
                if (chars.contains(leftChar) && substringCounts.get(leftChar) == 0) {
                    matchedChars--;
                }
                left++;
            }

            if (matchedChars == chars.size()) {
                String currentPassword = sequence.substring(left, right + 1);

                if (password.equals("-1") || currentPassword.length() > password.length() ||
                        (currentPassword.length() == password.length() && left > sequence.lastIndexOf(password)) ) {
                    password = currentPassword;
                }
            }
        }
        System.out.println(password);
    }

}
