import java.util.*;
import java.io.*;

// [ 배낭 문제 (냅색 Knapsack) with DP ]
// - 개념 특징 :
// https://sskl660.tistory.com/88
// 위 링크의 사진 설명이 이해하기 용이해,
// 추후 복습 시 개념 참고용으로 기재함.

public class Knapsack_BOJ_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());  // 물건 개수
        int k = Integer.parseInt(stt.nextToken());  // 배낭에 넣을(담을) 수 있는 최대 무게

        // item[0][]: 물건을 배낭에 안넣을 경우, item[1~n][]: 물건을 배낭에 넣을 경우
        // item[][0]: 무게, item[][1]: 가치
        int[][] item = new int[n+1][2];  // 물건을 배낭에 안넣을 경우를 고려해, n+1 크기로 선언. (DP 점화식 계산을 위해 패딩 필요.)
        for(int i=1; i<=n; i++) {
            stt = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(stt.nextToken());  // 무게
            int value = Integer.parseInt(stt.nextToken());  // 가치
            item[i][0] = weight;
            item[i][1] = value;
        }

        int[][] dp = new int[n+1][k+1];  // 물건을 배낭에 안넣을 경우를 고려해, n+1 크기와 마찬가지로 k+1 크기로 선언. (DP 점화식 계산을 위해 패딩 필요.)
        for(int i=1; i<=n; i++) {  // n 물건'까지' 담았을때
            int weight = item[i][0];
            int value = item[i][1];
            for(int j=1; j<=k; j++) {  // 배낭에 담을 수 있는 최대 무게가 j(1~k)일때
                int prevValueSum = dp[i-1][j];  // 바로 이전 물건'까지'의 최적해 가치합
                // 주의: 만약 이 코드줄에서 'int diffValueSum = dp[i-1][j-weight];'를 선언한다면,
                //      j-weight 값이 음수임에도 배열 인덱스를 조회해 에러나는 경우가 있으므로 조심할것.

                if(weight <= j) {  // 현재 물건을 넣을 수 있는 경우 (참고로 이항하면 '0 <= j-weight')
                    int diffValueSum = dp[i-1][j-weight];  // 현재 물건을 추가한다고 가정할 때, 총 무게가 최대인 j에 딱 맞춰지도록 남은 무게'까지'의 당시 최적해 가치합을 가져옴
                    dp[i][j] = Math.max(prevValueSum, diffValueSum + value);
                }
                else {  // 현재 물건을 넣을 수 없는 경우
                    dp[i][j] = prevValueSum;  // 이전 물건'까지' 담았을때의 가치합 값을 그대로 유지.
                }
            }
        }

        // 모든 n가지 물건을 모두 고려해보았을때, 최대인 k무게에서의 최적해 가치합을 출력.
        System.out.print(dp[n][k]);
    }
}
