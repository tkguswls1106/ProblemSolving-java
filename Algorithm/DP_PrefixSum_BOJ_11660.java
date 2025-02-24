import java.util.*;
import java.io.*;

// [ DP 누적합 (DP Prefix Sum) - 2차원 배열 ]
// - DP 누적합에서, 1차원 배열 vs 2차원 배열 :
// 합계(DP 점화식)를 계산할 영역의 정의 방식이 다름.
// '1차원 배열의 DP 누적합' 방식은, 단순히 이전 좌표부터 이후 좌표까지인 연속된 범위의 합계를 의미.
// '2차원 배열의 DP 누적합' 방식은, 이전 좌표부터 이후 좌표까지 사각형 영역을 그렸을때 그 범위 내의 합계를 의미.
// - DP 누적합 vs 투포인터 누적합 :
// DP의 누적합 문제는 '구간 내 합계(sum)를 구하라'를 바탕으로함.
// 반면, 투포인터의 누적합 문제는 '구간 내 합계(sum)가 num인 경우를 찾아라'를 바탕으로함.

public class DP_PrefixSum_BOJ_11660 {
    public static int[][] arr = new int[1025][1025];
    public static int[][] dp = new int[1025][1025];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());

        for(int i=1; i<=n; i++) {  // 반드시 맨위 가로줄을 0값으로 할당해 띄우고 시작. (0행 띄우고 1행부터 시작)
            stt = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {  // 반드시 맨왼쪽 세로줄을 0값으로 할당해 띄우고 시작. (0열 띄우고 1열부터 시작)
                arr[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        // - 초기값 정의 (0행과 0열을 0값으로 할당하고 띄워 시작했기에, 이 과정은 없어도됨.)
        // dp[1][1] = arr[1][1];

        // - 점화식으로 모든 DP 테이블에 값 세팅
        // DP 테이블 정의 : 처음위치(1,1)부터 특정위치(i,j)까지 테두리를 따라 직사각형을 그렸을때의 범위 내 합계
        // ('1차원 누적합'은 단순히 연속된 범위를 의미하는 반면, '2차원 누적합'은 사각형 영역의 범위를 의미.)
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                // (0행과 0열을 0값으로 할당하고 띄워 시작했기에, 이 과정은 없어도됨.)
                // if(i==1 && j==1) continue;

                // DP 점화식 ('처음위치부터 특정위치까지'의 사각형영역 합계)
                // = 이전 범위 누적합 + 현재 값
                // = (위쪽 사각형 누적합 + 왼쪽 사각형 누적합 - 중복덧셈된 좌상단 사각형 누적합) + 현재값
                // 그림 참고 : https://nahwasa.com/entry/%EB%88%84%EC%A0%81-%ED%95%A9prefix-sum-2%EC%B0%A8%EC%9B%90-%EB%88%84%EC%A0%81%ED%95%A9prefix-sum-of-matrix-with-java?pidx=12
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }
        
        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(stt.nextToken());
            int y1 = Integer.parseInt(stt.nextToken());
            int x2 = Integer.parseInt(stt.nextToken());
            int y2 = Integer.parseInt(stt.nextToken());

            // 답 공식 ('특정위치A부터 특정위치B까지'의 사각형영역 합계)
            // = B전체 누적합 - A이전 누적합
            // = B까지 전체 사각형 누적합 - A이전의 위쪽 사각형 누적합 - A이전의 왼쪽 사각형 누적합 + A이전의 중복차감된 좌상단 사각형 누적합
            // 그림 참고 : https://nahwasa.com/entry/%EB%88%84%EC%A0%81-%ED%95%A9prefix-sum-2%EC%B0%A8%EC%9B%90-%EB%88%84%EC%A0%81%ED%95%A9prefix-sum-of-matrix-with-java?pidx=10
            int sum = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            stb.append(sum).append("\n");
        }

        System.out.print(stb.toString());
    }
}
