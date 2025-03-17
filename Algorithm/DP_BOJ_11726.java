import java.util.*;

// [ DP 2xn 타일링 문제 ]

public class DP_BOJ_11726 {
    // - dp 테이블 정의 :
    // dp[i] = 2xi 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수
    public static int[] dp = new int[1002];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // - 점화식 찾기 :
        // 세로줄 하나를 채우는법 = '세로 직사각형 1개로 채우는법' + '가로 직사각형 2개로 채우는법'
        // 전자는 한줄이 채워지므로 남은 줄은 dp[i-1]이고, 후자는 두줄이 채워지므로 남은 줄은 dp[i-2]가 됨.
        // ==> dp[i] = dp[i-1] + dp[i-2]
        // (!!! 이 문제는 해당되지않으나, 만약 BOJ_11727 문제처럼 2x2크기의 정사각형도 사용 가능하다면, 
        // 세로줄 하나를 2x2크기의 정사각형 1개로도 채울수 있으므로, 위 점화식에 '+ dp[i-2]'를 한번더 추가해주면 됨. !!!)

        // - 초기값 할당 :
        // 점화식이 'dp[i] = dp[i-1] + dp[i-2]' 이므로, 두가지의 초기값 필요.
        dp[1] = 1;
        dp[2] = 2;

        // - dp값 세팅 :
        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;  // 주의사항: 만약 여기서 10007 연산을 하지않고, 출력문에만 넣어준다면, dp배열내부값이 너무커져서 int를 초과할 수 있음.
        }

        int answer = dp[n];  // 참고로 만약 출력문에서도 연산이 이루어지는 문제의 경우, 마찬가지로 여기서도 10007 연산을 해주어야함.
        System.out.print(answer);
    }
}
