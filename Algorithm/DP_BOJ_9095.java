import java.util.*;
import java.io.*;

// [ DP 합의 경우 문제 ]

public class DP_BOJ_9095 {
    // - dp 테이블 정의 :
    // dp[i] = i를 1,2,3의 합으로 나타내는 방법의 수 (참고로 순서 존재함. 예시로 1+2와 2+1은 다름.)
    public static int dp[] = new int[13];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // - 점화식 찾기 :
        // 예시로 4는 3+'1', 2+'2', 1+'3'로 나타낼 수 있음.
        // 4 = ? + 단순숫자1 = (4-1) + 단순숫자1 = (3을 만드는 경우) + 단순숫자1 => dp[3]
        // 4 = ? + 단순숫자2 = (4-2) + 단순숫자2 = (2를 만드는 경우) + 단순숫자2 => dp[2]
        // 4 = ? + 단순숫자3 = (4-3) + 단순숫자3 = (1을 만드는 경우) + 단순숫자3 => dp[1]
        // ==> 'dp[4] = dp[4-1] + dp[4-2] + dp[4-3] = dp[3] + dp[2] + dp[1]' => 점화식은 'dp[k] = dp[k-1] + dp[k-2] + dp[k-3]'

        // - 초기값 할당 :
        // 점화식이 'dp[k] = dp[k-1] + dp[k-2] + dp[k-3]' 이므로, 세가지의 초기값 필요.
        dp[1] = 1;  // 1
        dp[2] = 2;  // 1 1 , 2
        dp[3] = 4;  // 1 1 1 , 1 2 , 2 1 , 3

        // - dp값 세팅 :
        for(int i=4; i<11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        StringBuilder stb = new StringBuilder();
        int n;
        while(t-- > 0) {
            n = Integer.parseInt(br.readLine());
            stb.append(dp[n]).append("\n");
        }

        System.out.print(stb.toString());
    }
}
