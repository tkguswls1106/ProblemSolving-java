import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_17404 {
    public static int[][] arr = new int[1002][3];  // 0: R, 1: G, 2: B

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer stt;
        int r, g, b;
        for(int i=1; i<=n; i++) {
            stt = new StringTokenizer(br.readLine());
            r = Integer.parseInt(stt.nextToken());
            g = Integer.parseInt(stt.nextToken());
            b = Integer.parseInt(stt.nextToken());
            arr[i][0] = r;
            arr[i][1] = g;
            arr[i][2] = b;
        }

        int minAnswer = Integer.MAX_VALUE;
        int[][] dp;
        for(int startColor=0; startColor<3; startColor++) {
            // - 초기값 할당 :
            dp = new int[n+1][3];
            dp[1][startColor] = arr[1][startColor];
            for(int restColor=0; restColor<3; restColor++) {
                if(startColor != restColor) {
                    int INF = 1000 * 1000 + 2;  // infinite 용도로 부여할 최대수치값 = '집의 개수 N은 최대 1000개' x '색칠비용 최대 1000' + '넉넉하게 2'
                    dp[1][restColor] = INF;  // 참고로, 'dp[1][i] = INF'로 설정한 값은 마지막 값인 'dp[n][endColor]'까지 전부 전이되지는 않으니 걱정안해도됨.
                }
            }

            // - dp값 세팅 (with 점화식) :
            for(int i=2; i<=n; i++) {
                r = arr[i][0];
                g = arr[i][1];
                b = arr[i][2];
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + r;  // 현재: R, 이전: G or B
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + g;  // 현재: G, 이전: R or B
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + b;  // 현재: B, 이전: R or G
            }

            for(int endColor=0; endColor<3; endColor++) {
                if(startColor != endColor) {
                    minAnswer = Math.min(minAnswer, dp[n][endColor]);
                }
            }
        }        

        System.out.print(minAnswer);
    }
}
