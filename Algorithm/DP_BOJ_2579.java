import java.util.*;
import java.io.*;

// [ DP 계단 문제 ]

// - 번외(다른 점화식 도출 풀이방식):
// 참고로 난 이 방식으로 풀진 않았음.
// 1. 마지막 계단에서 한칸 내려가는 경우:
// step1 = stair[i] + stair[i - 1] + dp[i - 3]
// 2.마지막 계단에서 두칸을 내려가는 경우:
// step2 = stair[i] + dp[i - 2]
// ==> dp[i] = max(step1, step2)

public class DP_BOJ_2579 {
    // - dp 테이블 정의 :
    // dp[i][k] = i번째 계단을 포함하여, i번째 계단까지 밟았을때의 최댓값. (k = 1~2으로, 본인계단을 포함하여 연속으로 몇계단째 밟았는지 의미.)
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
        // 3. 주의사항
        // 하지만 이후 dp값 세팅에서 for문의 i를 3부터 시작하므로, dp[2][2]는 할당을 지나치게 되어 여기서 미리 할당해주어야 함. :
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
