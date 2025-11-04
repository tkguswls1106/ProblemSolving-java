import java.util.*;
import java.io.*;

// - 알고리즘: 이항 계수 (조합론) with DP

public class BOJ_1010 {
    public static int[][] dp = new int[32][32];

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
        return dp[n][r] = memoBP(n-1, r-1) + memoBP(n-1, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        
        StringTokenizer stt;
        int r, n, combinationCnt;  // combinationCnt = nCr
        while(tc-- > 0) {
            stt = new StringTokenizer(br.readLine());
            r = Integer.parseInt(stt.nextToken());
            n = Integer.parseInt(stt.nextToken());

            combinationCnt = memoBP(n, r);
            stb.append(combinationCnt).append("\n");
        }
        
        System.out.print(stb.toString());
    }
}
