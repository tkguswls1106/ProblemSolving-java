import java.util.*;
import java.io.*;

// - 알고리즘 : DP

public class BOJ_11052 {
    public static int[] arr = new int[1002];
    public static int[] dp = new int[1002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }

        // - 점화식 찾기 :
        // 총 k(i)개의 카드를 구매해야할때, 현재 구매할 카드가 cur개짜리 카드팩이라면?
        // => dp[k] = arr[cur] + dp[k-cur];
        
        // - 초기값 할당 :
        dp[1] = arr[1];

        // - dp값 세팅 :
        for(int i=2; i<=n; i++) {
            for(int cur=1; cur<=i; cur++) {
                int curPrice = arr[cur] + dp[i-cur];
                dp[i] = Math.max(dp[i], curPrice);
            }
        }
        // 아래 방식도 가능하긴함.
        /*
        for(int cur=1; cur<=n; cur++) {  // 필수 카드팩 선택
            int pickCnt = cur;
            int pickValue = arr[cur];
            for(int i=1; i<=n; i++) {  // 나머지 카드팩들 중 여러개 선택
                int restN = i - pickCnt;
                if(restN < 0) continue;
                dp[i] = Math.max(dp[i], dp[restN] + pickValue);
            }
        }
        */

        System.out.print(dp[n]);
    }
}
