import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class Hakaton {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        Date startTime = DATE_FORMAT.parse(scanner.nextLine());

        int n = scanner.nextInt();
        scanner.nextLine();
        Map<String, TeamInfo> teamData = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String teamName = parts[0].substring(1, parts[0].length() - 1);
            String timeStr = parts[1];
            char serverId = parts[2].charAt(0);
            String result = parts[3];

            Date requestTime = DATE_FORMAT.parse(timeStr);
            long elapsedTime = (requestTime.getTime() - startTime.getTime()) / 1000;
            TeamInfo info = teamData.computeIfAbsent(teamName, k -> new TeamInfo());

            switch (result) {
                case "ACCESSED":
                    info.markServer(serverId, elapsedTime);
                    break;
                case "DENIED":
                case "FORBIDEN":
                    info.failedAttempt(serverId);
                    break;
            }
        }
        List<Map.Entry<String, TeamInfo>> sortedTeams = new ArrayList<>(teamData.entrySet());
        sortedTeams.sort((e1, e2) -> {
            TeamInfo t1 = e1.getValue();
            TeamInfo t2 = e2.getValue();
            int serverCompare = Integer.compare(t2.servers.size(), t1.servers.size());
            if (serverCompare != 0) return serverCompare;
            int penaltyCompare = Integer.compare(t1.penalty, t2.penalty);
            if (penaltyCompare != 0) return penaltyCompare;
            return e1.getKey().compareTo(e2.getKey());
        });

        int rank = 1;
        for (Map.Entry<String, TeamInfo> entry : sortedTeams) {
            TeamInfo info = entry.getValue();
            System.out.printf("%d \"%s\" %d %d%n", rank++, entry.getKey(), info.servers.size(), info.penalty);
        }
    }



    private static class TeamInfo {
        private final Map<Character, ServerInfo> servers = new HashMap<>();
        private int penalty = 0;

        void markServer(char serverId, long time) {
            ServerInfo serverInfo = servers.computeIfAbsent(serverId, k -> new ServerInfo());
            if (serverInfo.success) return;
            serverInfo.success = true;
            penalty += (int) (time / 60);
            penalty += serverInfo.failedAttempts * 20;
        }

        void failedAttempt(char serverId) {
            ServerInfo serverInfo = servers.computeIfAbsent(serverId, k -> new ServerInfo());
            if (serverInfo.success) return;
            serverInfo.failedAttempts++;
        }
    }

    private static class ServerInfo {
        boolean success = false;
        int failedAttempts = 0;
    }
}

