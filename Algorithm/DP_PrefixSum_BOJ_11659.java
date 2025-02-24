// < 성공 방법 1 - Ver.1 (개선 전, 알고리즘 직관적인 코드) >
// arr[][] 배열과 dp[][] 배열을 모두 사용하는 방법.
// => 'arr[][] 배열 미사용' 방법보다 공간복잡도가 비효율적이지만, 알고리즘 파악에 보다 직관적임.
// (개념 정리를 위해, 이 방안을 채택하여 주요 코드로 기재함.)

import java.util.*;
import java.io.*;

// [ DP 누적합 (DP Prefix Sum) - 1차원 배열 ]
// - 문제 함정 :
// 이 문제는 일반적인 구현으로도 풀수는 있지만,
// 시간제한이 1초인 반면, N과 M의 최댓값이 각각 100000이라서, 구현으로 풀게되면 O(NM=100억)까지 시간복잡도가 나올수있어 구현 방식으로 풀면 안된다.
// 그러므로, DP를 활용한 누적합(Prefix sum) 알고리즘으로 풀어야만 한다.
// - DP 누적합에서, 1차원 배열 vs 2차원 배열 :
// 합계(DP 점화식)를 계산할 영역의 정의 방식이 다름.
// '1차원 배열의 DP 누적합' 방식은, 단순히 이전 좌표부터 이후 좌표까지인 연속된 범위의 합계를 의미.
// '2차원 배열의 DP 누적합' 방식은, 이전 좌표부터 이후 좌표까지 사각형 영역을 그렸을때 그 범위 내의 합계를 의미.
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

/*
// < 성공 방법 1 - Ver.2 (개선 후, 공간복잡도 우수한 코드) >
// dp[][] 배열만 사용하는 방법. (arr[][] 배열 미사용)
// => 'arr[][] 배열과 dp[][] 배열을 모두 사용' 방법보다 알고리즘의 직관성이 떨어지지만, 공간복잡도가 보다 효율적임.

import java.util.*;
import java.io.*;

public class DP_PrefixSum_BOJ_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());
        int dp[] = new int[n+1];  // arr[][] 배열 없이, dp[][] 배열만 단독 사용. (공간복잡도 우수)

        stt = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            if(i==1) {
                // 초기값 정의.
                dp[i] = num;
                continue;
            }

            // 점화식으로 모든 DP 테이블에 값 세팅.
            dp[i] = dp[i-1] + num;
        }

        StringBuilder stb = new StringBuilder();
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(stt.nextToken());
            int j = Integer.parseInt(stt.nextToken());

            // 'a번째입력숫자 부터 b번째입력숫자까지 더한 합계' => 'dp[b] - dp[a-1]'
            int sum = dp[j] - dp[i-1];
            stb.append(sum).append("\n");  // stb.append(sum + "\n"); 보다 성능이 좋음.
        }

        System.out.print(stb.toString());
    }
}
*/