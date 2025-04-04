// < 성공 방법 1 >
// dp[] 1차원 배열로 푸는 방법.

import java.util.*;
import java.io.*;

// [ DP 계단 문제 ]

// - dp[] 1차원 풀이방식 Tip :
// arr[i] = 포함된것이 확정일때 (위치 O)
// dp[i] = arr[i]를 포함하든안하든 i위치까지 도달했을때의 최댓값 (위치 OorX)
// 1. OorX X  // i계단을 밟지않는 경우 => i-1계단은 모두 가능하므로 dp[i-1] 사용.
// dp1 = dp[i-1] + (i 포함X)
//     = dp[i-1]
// 2. OorX X O  // i-1계단을 밟지않고, i계단을 밟는 경우 => i-2계단은 모두 가능하므로 dp[i-2] 사용.
// dp2 = dp[i-2] + (i-1 포함X) + arr[i]
//     = dp[i-2] + arr[i]
// 3. OorX X O O  // i계단과 i-1계단을 밟고, i-2계단을 밟지않는 경우 => i-3계단은 모두 가능하므로 dp[i-3] 사용.
// dp3 = dp[i-3] + (i-2 포함X) + arr[i-1] + arr[i]
//     = dp[i-3] + arr[i-1] + arr[i]
// ==> dp[i] = max(dp1, dp2, dp3)
// 참고로 이 문제는 마지막 계단을 반드시 포함하므로, dp1 없이 'max(dp2, dp3)'로 적용할것.

// - {번외} dp[] 1차원 풀이방식 Tip (설명만 다름) :
// 1. 마지막 계단에서 한칸 내려가는 경우:
// step1 = stair[i] + (stair[i - 1] + dp[i - 3])
// 2. 마지막 계단에서 두칸을 내려가는 경우:
// step2 = stair[i] + dp[i - 2]
// ==> dp[i] = max(step1, step2)

public class DP_BOJ_2579 {
    // - dp 테이블 정의 :
    public static int[] dp = new int[302];  // stair[i]를 포함하든안하든 i위치까지 도달했을때의 최댓값 (위치 OorX)
    public static int[] stair = new int[302];  // 포함된것이 확정일때 (위치 O)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        // - 점화식 찾기 :
        // 1. OorX X 경우
        // dp1 = dp[i-1] + (i 포함X)
        //     = dp[i-1]
        // 2. OorX X O 경우
        // dp2 = dp[i-2] + (i-1 포함X) + stair[i]
        //     = dp[i-2] + stair[i]
        // 3. OorX X O O 경우
        // dp3 = dp[i-3] + (i-2 포함X) + stair[i-1] + stair[i]
        //     = dp[i-3] + stair[i-1] + stair[i]
        // ==> dp[i] = max(dp1, dp2, dp3)
        // 참고로 이 문제는 마지막 계단을 반드시 포함하므로, dp1 없이 'max(dp2, dp3)'로 적용할것.

        // - 초기값 할당 :
        dp[1] = stair[1];
        if(2 <= n) {
            dp[2] = stair[1] + stair[2];
        }
        if(3 <= n) {
            // int dp1 = dp[3-1];
            int dp2 = dp[3-2] + stair[3];
            int dp3 = stair[3-1] + stair[3];  // dp[3-3] + stair[3-1] + stair[3]
            dp[3] = Math.max(dp2, dp3);
        }

        // - dp값 세팅 :
        for(int i=4; i<=n; i++) {
            // int dp1 = dp[i-1];
            int dp2 = dp[i-2] + stair[i];
            int dp3 = dp[i-3] + stair[i-1] + stair[i];

            dp[i] = Math.max(dp2, dp3);
        }

        int answer = dp[n];
        System.out.print(answer);
    }
}

/*
// < 성공 방법 2 >
// dp[][] 2차원 배열로 푸는 방법.

import java.util.*;
import java.io.*;

// - dp[][] 2차원 풀이방식 Tip :
// 1. 현재포함 연속 한계단 오른 경우 dp[i][1]
// => 이전계단은 연속이 끊겼던 상태임 dp[i-2][]
// => i-2 계단까지 '한번연속 dp[i-2][1]'과 '두번연속 dp[i-2][2]' 모두 가능.
// 2. 현재포함 연속 두계단 오른 경우 dp[i][2]
// => 바로 이전계단을 반드시 밟았다는 의미임 dp[i-1][]
// => i-1 계단까지는 두번연속은 안되고 '한번연속 dp[i-1][1]'만 가능.

public class DP_BOJ_2579 {
    // - dp 테이블 정의 :
    // dp[i][k] = i번째 계단을 포함하여, i번째 계단까지 밟았을때의 최댓값. (k = 1~2로서, 현재계단을 포함하여 연속으로 몇계단째 밟았는지를 의미.)
    public static int[][] dp = new int[302][3];  // [3]의 인덱스에서 1~2만 사용 예정.
    public static int[] stair = new int[302];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        // - 점화식 찾기 :
        // < 본인계단 포함해서 1연속 계단인 경우 >
        // 바로 이전계단이 없어야하므로, 이전에 다음다음계단을 올라온것임.
        // 다음다음계단 오를땐 그 이전의 계단은 어떻게 올랐던 상관없이 가능함.
        // dp[i][1] = dp[i-2][1] + stair[i]  // O
        // dp[i][1] = dp[i-2][2] + stair[i]  // O
        // ==> 첫번째 점화식으로, 'dp[i][1] = max(dp[i-2][1], dp[i-2][2]) + stair[i]'
        // < 본인계단 포함해서 2연속 계단인 경우 >
        // 이전계단이 하나밖에 안됨.
        // dp[i][2] = dp[i-1][1] + stair[i]  // O
        // dp[i][2] = dp[i-1][2] + stair[i]  // X
        // ==> 두번째 점화식으로, 'dp[i][2] = dp[i-1][1] + stair[i]'.

        // - 초기값 할당 :
        // 1. 첫번째 점화식인 'dp[i][1] = max(dp[i-2][1], dp[i-2][2]) + stair[i]' :
        // dp[3][1] 필요 초기값 = dp[3-2][1], dp[3-2][2]
        // => dp[1][1], dp[1][2]
        // 2. 두번째 점화식인 'dp[i][2] = dp[i-1][1] + stair[i]' :
        // dp[3][2] 필요 초기값 = dp[3-1][1]
        // => dp[2][1]
        // 3. 주의사항 :
        // 하지만 이후 dp값 세팅에서 for문의 i를 3부터 시작하므로, dp[2][2]는 할당을 지나치게 되어 여기서 미리 할당해주어야 함.
        // => dp[2][2]
        dp[1][1] = stair[1];
        dp[1][2] = 0;  // 방법 없음.
        dp[2][1] = stair[2];
        dp[2][2] = dp[1][1] + stair[2];  // 'dp[i][2] = dp[i-1][1] + stair[i]'

        // - dp값 세팅 :
        for(int i=3; i<=n; i++) {
            dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stair[i];
            dp[i][2] = dp[i-1][1] + stair[i];
        }

        int answer = Math.max(dp[n][1], dp[n][2]);
        System.out.print(answer);
    }
}
*/