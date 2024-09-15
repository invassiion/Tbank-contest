import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CompletedProcess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        scanner.nextLine();

        List<List<Integer>> graph = new ArrayList<>();
        long[] executionTime = new long[n + 1];
        long[] completionTime = new long[n + 1];
        int[] inDegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            executionTime[i] = scanner.nextLong();
            inDegree[i] = 0;
            while (scanner.hasNextInt()) {
                int dependency = scanner.nextInt();
                graph.get(dependency).add(i);
                inDegree[i]++;
            }
            scanner.nextLine();
        }

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <=n; i++) {
            if (inDegree[i] == 0) {
                list.offer(i);
                completionTime[i] = executionTime[i];
            }
        }

        while (!list.isEmpty()) {
            int current = list.poll();
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] ==0) {
                    list.offer(neighbor);
                }
                completionTime[neighbor] = Math.max(completionTime[neighbor], completionTime[current] + executionTime[neighbor]);
            }
        }

        long maxCompletionTime = 0;
        for (int i = 1; i <= n; i++) {
            maxCompletionTime = Math.max(maxCompletionTime, completionTime[i]);
        }

        System.out.println(maxCompletionTime);
    }
}
