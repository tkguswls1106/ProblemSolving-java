// [ DP 피보나치 수열 - TopDown & BottomUp 방식 ]
// - 피보나치 주의사항 :
// 피보나치 수열은 N이 커질수록 값이 급속도로 증가하므로, long 자료형 사용.
// - 문제 주의사항 :
// 문제에 '0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. n이 주어졌을 때, n번째 피보나치 수를 구하라.'
// 라고 명시되어있으므로, dp[0]=0 으로 작성해도 좋음.

// < TopDown 방식 >
// - 주특징 : 반복문이 아닌 재귀함수를 사용.
// 다만, 이러한 탑다운보단 바텀업 방식을 더 추천함.

import java.util.*;

public class DP_Fibonacci_BOJ_2748 {
    public static long[] dp = new long[92];  

    public static long fibo(int x) {  // 탑다운 방식이므로 반복문 대신 재귀함수 사용. (x는 dp[i]에서의 i라고 생각하면됨.)
        // 점화식이 'dp[i] = dp[i-1] + dp[i-2]'로써 두가지의 종료조건이 필요.
        if(x == 0) return 0;
        if(x == 1) return 1;

        // 이미 계산한적 있는 문제라면 그대로 반환.
        if(dp[x] != 0) return dp[x];

        // 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환.
        dp[x] = fibo(x-1) + fibo(x-2);  // fibo(i-2) + fibo(i-1)
        return dp[x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long answer = fibo(n);
        System.out.print(answer);
    }
}

/*
// < BottomUp 방식 >
// - 주특징 : 재귀함수가 아닌 반복문을 사용.

import java.util.*;

public class DP_Fibonacci_BOJ_2748 {
    public static long[] dp = new long[92];  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 점화식이 'dp[i] = dp[i-1] + dp[i-2]'로써 두가지의 초기값 할당이 필요.
        dp[0] = 0;
        dp[1] = 1;

        // 바텀업 방식이므로 재귀함수 대신 반복문 사용으로, DP 테이블에 미리 값을 세팅.
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];  // dp[i-2] + dp[i-1]
        }

        long answer = dp[n];
        System.out.print(answer);
    }
}
*/