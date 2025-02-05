import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_1003 {
    public static int[][] dp = new int[42][2];  // [k][0]: N=k일때 0출력횟수, [k][1]: N=k일때 1출력횟수
    public static List<Integer> nList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        int maxN = -1;
        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            maxN = Math.max(maxN, n);
            nList.add(n);
        }
        
        dp[0][0] = dp[1][1] = 1;
        dp[0][1] = dp[1][0] = 0;

        for(int k=2; k<=maxN; k++) {
            dp[k][0] = dp[k-2][0] + dp[k-1][0];
            dp[k][1] = dp[k-2][1] + dp[k-1][1];
        }

        for(int n : nList) {
            stb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }
        System.out.print(stb.toString());
    }
}
