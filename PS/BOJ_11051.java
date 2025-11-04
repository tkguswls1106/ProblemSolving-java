import java.util.*;
import java.io.*;

// - 알고리즘: 이항 계수 (조합론) with DP

public class BOJ_11051 {
    public static int[][] dp = new int[1002][1002];
    public static int divNum = 10007;

    public static int memoBP(int n, int r) {  // Binomial Coefficient
        // 이미 계산한적 있는 문제라면 그대로 반환.
        if(dp[n][r] != 0) {
            return dp[n][r];
        }

        // nC0 = nC(n-0) = nCn = 1
        if(n == r || r == 0) {
            return dp[n][r] = 1;
        }

        // nCr = (n-1)C(r-1) + (n-1)Cr
        return dp[n][r] = (memoBP(n-1, r-1) + memoBP(n-1, r)) % divNum;  // % 10007
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int r = Integer.parseInt(stt.nextToken());  // 문제에서는 k

        int combinationCnt = memoBP(n, r);
        System.out.print(combinationCnt);
    }
}
