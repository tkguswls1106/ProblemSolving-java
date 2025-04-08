import java.util.*;
import java.io.*;

// [ DP 동전 문제 ]

public class DP_BOJ_2293 {
    public static int[] arr = new int[102];
    public static int[] dp = new int[10002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());

        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // - 초기값 할당 :
        dp[0] = 1;  // 총 0원이 되려면 안뽑는경우인 1가지가 존재함.

        // - dp값 세팅 (정답!) :
        for(int cur=1; cur<=n; cur++) {
            int pickCoin = arr[cur];  // 반드시 선택한 코인 (순서 고정으로 인한, 중복조합의 순열화 방지용)
            for(int sum=1; sum<=k; sum++) {
                int restSum = sum - pickCoin;  // 선택코인을 뺀 나머지합산을 위한 DP값
                if(restSum < 0) continue;
                dp[sum] += dp[restSum];
                // 만약 경우의수가 아닌, 동전의 최소 사용개수를 묻는 문제였다면
                // dp[sum] = Math.min(dp[sum], dp[restSum]+1);
            }
        }

        /*
        // - dp값 세팅 (오답!) :
        // 이 방식은 '2 1'과 '1 2'를 다르게 계산하여 순열로 처리. -> 문제의 '순서만 다른 것은 같은 경우이다.'에 위배됨.
        for(int sum=1; sum<=k; sum++) {
            for(int cur=1; cur<=n && arr[cur]<=sum; cur++) {
                int dpCnt = dp[sum - arr[cur]];  // 경우의 수
                dp[sum] += dpCnt;
            }
        }
        */

        System.out.print(dp[k]);
    }
}
