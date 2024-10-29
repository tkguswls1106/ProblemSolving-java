import java.util.*;

// [ DP (바텀업 방식) - 기초 ]

// - DP 방식 설명:
// * Top-Down(탑다운):
// 하향식임. 메모이제이션을 활용한 재귀함수를 이용하며, 큰 문제를 위해 작은 문제를 호출한다.
// 단점으로는, 메모리를 많이 사용하여 스택 오버플로우 가능성이 높아질수도 있다.
// * Bottom-Up(바텀업):
// 상향식임. 재귀함수를 사용하지 않는대신, 반복문을 사용하여 DP테이블에 전부 미리 값을 할당하고 시작한다. 그렇게 먼저 계산한 값을 이용해서 답을 구한다.
// 단점으로는, 미리 값을 할당하고 시작하기에, 문제를 해결하는 데 있어 필요하지 않은 부분까지 모두 구하다 보니 시간복잡도가 더 걸릴수도 있다.
// 바텀업이 보통 정석적인 DP의 방식이라고들 한다.

// - DP 문제 풀이과정:
// * 과정 1. DP테이블 정의하기:
// DP[i]는 i를 1로 만들기위해 필요한 연산 사용 횟수의 최솟값
// * 과정 2. 점화식 찾기:
// 예시로 DP[12]는 무엇인가?를 생각하면 (참고로 밑의 +1의 의미는 연산횟수+1이다.)
// '3으로 나누거나' => 'DP[12] = DP[12/3] + 1 = DP[4] + 1' => 'DP[k] = DP[k/3] + 1'
// '2으로 나누거나' => 'DP[12] = DP[12/2] + 1 = DP[6] + 1' => 'DP[k] = DP[k/2] + 1'
// '1을 빼거나' => 'DP[12] = DP[12-1] + 1 = DP[11] + 1' => 'DP[k] = DP[k-1] + 1'
// 결론적으로 DP[k]는 위의 세조건중 최솟값으로 결정되면 됨.
// ==> DP[k] = min(DP[k/3]+1, DP[k/2]+1, DP[k-1]+1)
// * 과정 3. 초기값 정의하기:
// 이 문제의 경우에는, 'DP[k] = DP[k/3] + 1' 처럼 항이 하나이므로, 한가지의 초기값 할당이면 된다.
// 그리고 1(=i)을 1로 만들기위해 필요한 연산 사용 횟수의 최솟값은 0이므로, 초기값으로 'DP[1] = 0'만 할당해주면 충분하다.
// 하지만 예를들어 피보나치의 경우에는, 점화식이 'DP[i] = DP[i-1] + DP[i-2]'로써 두가지의 초기값 할당이 필요하다.

public class DP_BOJ_1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // - 과정 1. DP테이블 정의하기 :
        // DP[i]는 i를 1로 만들기위해 필요한 연산 사용 횟수의 최솟값
        // - 과정 2. 점화식 찾기 :
        // DP[k] = min(DP[k/3]+1, DP[k/2]+1, DP[k-1]+1)
        int[] dp = new int[1000002];

        // - 과정 3. 초기값 정의 :
        // 1(=i)을 1로 만들기위해 필요한 연산 사용 횟수의 최솟값은 0
        dp[1] = 0;

        // - 과정 4. 점화식으로 모든 DP 테이블에 값 세팅
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + 1;
            if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
            if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
        }

        System.out.print(dp[n]);
    }
}