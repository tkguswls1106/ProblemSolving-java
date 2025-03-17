import java.util.*;

// - 알고리즘: DP

public class BOJ_11727 {
    public static int[] dp = new int[1002];  // dp[i] = 2xi 직사각형을 채우는 방법의 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // - 점화식 찾기 :
        // 세로줄 하나를 채우는법 = '세로 직사각형 1개로 채우는법' + '가로 직사각형 2개로 채우는법' + '2x2크기의 정사각형 1개로 채우는법'
        // 첫번째는 한줄이 채워지므로 남은 줄은 dp[i-1]이고, 두번째와 세번째는 두줄이 채워지므로 남은 줄은 dp[i-2]가 됨.
        // ==> dp[i] = dp[i-1] + dp[i-2] + dp[i-2] = dp[i-1] + (dp[i-2] * 2)

        // - 초기값 할당 :
        dp[1] = 1;  // 세로 한줄로
        dp[2] = 3;  // 세로 두줄로 or 가로 두줄로 or 사각형 하나로

        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
        }
        
        System.out.print(dp[n]);
    }
}
