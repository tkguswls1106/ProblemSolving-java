import java.util.*;
import java.io.*;

// [ 이항 계수 (조합론의 Binomial Coefficient) with DP ]
// - 개념 특징 :
// 알고리즘에서 이항계수란, 주어진 집합에서 원하는 개수만큼 순서없이 뽑는 조합의 "개수"를 의미한다.
// 따라서 이는 '단순히 조합의 개수만 구하면 되며, 구해야 할 테스트 케이스가 여러개인 문제'에서 백트래킹 방식보다 유리하다.
// 참고로 문제의 '(n / r) 이항계수' 표기는 'nCr'과 같은 의미이다.
// - 코드 특징 :
// DP의 탑다운 방식으로 메모이제이션 재귀를 사용해 풀이하면 된다. (시간복잡도는 O(n*r))
// 이는 밑의 '파스칼 삼각형의 점화식'을 이용하며, 참고로 'nCn = nC0 = nP0 = 1' 임을 주의해야한다.
// ==> nCr = (n-1)C(r-1) + (n-1)Cr

public class Combination_BinomialCoefficient_BOJ_11050 {
    public static int[][] dp = new int[12][12];

    public static int memoBP(int n, int r) {  // Binomial Coefficient
        // 이미 계산한적 있는 문제라면 그대로 반환.
        if(dp[n][r] != 0) {
            return dp[n][r];
        }

        // nC0 = nC(n-0) = nCn = 1
        if(n == r || r == 0) {
            return dp[n][r] = 1;
        }

        // nCr = (n-1)C(r-1) + (n-1)Cr
        return dp[n][r] = memoBP(n-1, r-1) + memoBP(n-1, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int r = Integer.parseInt(stt.nextToken());  // 문제상으로는 k

        int combinationCnt = memoBP(n, r);
        System.out.print(combinationCnt);
    }
}
