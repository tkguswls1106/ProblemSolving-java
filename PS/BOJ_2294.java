import java.util.*;
import java.io.*;

// - 알고리즘 : DP

public class BOJ_2294 {
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
        dp[0] = 0;  // 총 0원이 되려면 안뽑는경우인 동전의 개수가 0개임.
        int maxValue = n*100000 + 2;
        for(int i=1; i<=k; i++){
            dp[i]= maxValue;
        }

        // - dp값 세팅
        for(int cur=1; cur<=n; cur++) {
            int pickCoin = arr[cur];  // 반드시 선택한 코인 (순서 고정으로 인한, 중복조합의 순열화 방지용)
            for(int sum=1; sum<=k; sum++) {
                int restSum = sum - pickCoin;  // 선택코인을 뺀 나머지합산을 위한 DP값
                if(restSum < 0) continue;
                dp[sum] = Math.min(dp[sum], dp[restSum]+1);
            }
        }

        System.out.print(dp[k]!=maxValue ? dp[k] : -1);
    }
}
