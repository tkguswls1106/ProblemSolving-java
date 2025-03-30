import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_2096 {
    public static int[][] arr = new int[100002][3];
    public static int[][] minDp = new int[100002][3];
    public static int[][] maxDp = new int[100002][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(stt.nextToken());
            arr[i][1] = Integer.parseInt(stt.nextToken());
            arr[i][2] = Integer.parseInt(stt.nextToken());
        }

        // - 점화식 찾기 :
        // dp[i][0] = MaxOrMin(dp[i-1][0], dp[i-1][1]) + arr[i][0];
        // dp[i][1] = MaxOrMin(dp[i-1][0], dp[i-1][1], dp[i-1][2]) + arr[i][1];
        // dp[i][2] = MaxOrMin(dp[i-1][1], dp[i-1][2]) + arr[i][2];

        // - 초기값 할당 :
        minDp[0][0] = maxDp[0][0] = arr[0][0];
        minDp[0][1] = maxDp[0][1] = arr[0][1];
        minDp[0][2] = maxDp[0][2] = arr[0][2];

        // - dp값 세팅 :
        for(int i=1; i<n; i++) {
            // min
            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + arr[i][0];
            minDp[i][1] = Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]) + arr[i][1];
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + arr[i][2];
            
            // max
            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + arr[i][0];
            maxDp[i][1] = Math.max(Math.max(maxDp[i-1][0], maxDp[i-1][1]), maxDp[i-1][2]) + arr[i][1];
            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + arr[i][2];
        }

        int minValue = Math.min(Math.min(minDp[n-1][0], minDp[n-1][1]), minDp[n-1][2]);
        int maxValue = Math.max(Math.max(maxDp[n-1][0], maxDp[n-1][1]), maxDp[n-1][2]);
        stb.append(maxValue).append(" ").append(minValue);

        System.out.print(stb.toString());
    }
}
