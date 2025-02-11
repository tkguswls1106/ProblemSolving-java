import java.util.*;
import java.io.*;

// [ DP 누적합 (DP Prefix Sum) ]
// - 문제 함정 :
// 이 문제는 일반적인 구현으로도 풀수는 있지만,
// 시간제한이 1초인 반면, N과 M의 최댓값이 각각 100000이라서, 구현으로 풀게되면 O(NM=100억)까지 시간복잡도가 나올수있어 구현 방식으로 풀면 안된다.
// 그러므로, DP를 활용한 누적합(Prefix sum) 알고리즘으로 풀어야만 한다.
// - DP 누적합 vs 투포인터 누적합 :
// DP의 누적합 문제는 '구간 내 합계(sum)를 구하라'를 바탕으로함.
// 반면, 투포인터의 누적합 문제는 '구간 내 합계(sum)가 num인 경우를 찾아라'를 바탕으로함.

public class DP_PrefixSum_BOJ_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int arr[] = new int[n+1];
        int dp[] = new int[n+1];

        stt = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }

        // - 과정 1. DP테이블 정의하기 :
        // dp[i] = arr[1]부터 arr[i]까지 더한 합계
        // - 과정 2. 점화식 찾기 :
        // 'dp[i] = arr[1]부터 arr[i]까지 더한 합계' => 'dp[i] = dp[i-1] + arr[i]'
        // 차후 활용 공식 도출 => 'arr[a] 부터 arr[b]까지 더한 합계' => 'dp[b] - dp[a-1]'

        // - 과정 3. 초기값 정의 :
        // dp[1] 필요.
        dp[1] = arr[1];

        // - 과정 4. 점화식으로 모든 DP 테이블에 값 세팅
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + arr[i];
        }

        StringBuilder stb = new StringBuilder();
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(stt.nextToken());
            int j = Integer.parseInt(stt.nextToken());

            // 'arr[a] 부터 arr[b]까지 더한 합계' => 'dp[b] - dp[a-1]'
            int sum = dp[j] - dp[i-1];
            stb.append(sum).append("\n");  // stb.append(sum + "\n"); 보다 성능이 좋음.
        }

        System.out.print(stb.toString());
    }
}
