import java.util.*;

// [ DP 경로추적 문제 ]

public class DP_BOJ_12852 {
    // - dp 테이블 정의 :
    // dp[i] = i를 1로 만들기위해 필요한 연산 사용 횟수의 최솟값
    public static int[] dp = new int[1000002];
    public static int[] next = new int[1000002];  // next[a] = b (a 다음 경로는 b 임을 의미.)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder stb = new StringBuilder();
        
        // - 점화식 찾기 :
        // 예시로 DP[12]는 무엇인가?를 생각하면 (참고로 밑의 +1의 의미는 연산횟수+1이다.)
        // '3으로 나누거나' => 'DP[12] = DP[12/3] + 1 = DP[4] + 1' => 'DP[k] = DP[k/3] + 1'
        // '2으로 나누거나' => 'DP[12] = DP[12/2] + 1 = DP[6] + 1' => 'DP[k] = DP[k/2] + 1'
        // '1을 빼거나' => 'DP[12] = DP[12-1] + 1 = DP[11] + 1' => 'DP[k] = DP[k-1] + 1'
        // ==> DP[k] = min(DP[k/3]+1, DP[k/2]+1, DP[k-1]+1)

        // - 초기값 할당 :
        dp[1] = 0;

        // - dp값 세팅 :
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + 1;  // 1을 뺄때
            next[i] = i-1;

            if(i%3 == 0 && dp[i] > dp[i/3]+1) {  // 만약 3으로 나눌때 더 최소횟수가 되는가? ('dp[i] = Math.min(dp[i], dp[i/3]+1)'와 같은 원리임.)
                dp[i] = dp[i/3] + 1;
                next[i] = i/3;
            }
            if(i%2 == 0 && dp[i] > dp[i/2]+1) {  // 만약 2으로 나눌때 더 최소횟수가 되는가? ('dp[i] = Math.min(dp[i], dp[i/2]+1)'와 같은 원리임.)
                dp[i] = dp[i/2] + 1;
                next[i] = i/2;
            }
        }

        stb.append(dp[n]).append("\n");
        int cur = n;
        while(cur >= 1) {  // 1로 만들기까지만 허용
            stb.append(cur).append(" ");
            cur = next[cur];  // 다음경로 숫자로 이동.
        }

        System.out.print(stb.toString());
    }
}
