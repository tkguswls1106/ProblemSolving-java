import java.util.*;
import java.io.*;

// [ 플로이드 워셜 알고리즘 (Floyd-Warshall Algorithm) with DP ]
// - 개념 특징 :
// 그래프(인접 행렬)의 모든 정점에서 출발하여, 다른 모든 정점까지의 최단 경로를 구하는 알고리즘이다.
// 다익스트라와 달리 '가중치가 음수인 간선'이 있어도 동작하지만, 사이클 내 모든 가중치 합이 음수가 되는 '음수 사이클'이 없어야만 한다.
// - 시간복잡도 :
// 정점 개수가 V, 간선 개수가 E 일때
// 3중 for문으로 경유지(V) 포함여부를 고려한 출발지(V)부터 도착지(V)까지의 최소 거리를 계산 => O(V^3)
// 따라서 간선 개수는 시간복잡도에 영향을 주지 않으며, 정점 개수가 적을 때 더욱 효과적인 알고리즘임.
// - BFS vs 다익스트라 vs 플로이드 워셜 :
// * BFS : '단순 최단 이동거리 혹은 이동횟수를 구하는 것'을 목적으로 함.
// * 다익스트라, 플로이드 워셜 : '각 이동 간의 비용을 고려해 최소한의 힘으로 가장 빠른 길을 찾는 것'을 목적으로 함.
//   * 다익스트라 : '하나의 정점'에서 '다른 모든 정점'으로의 최단 경로를 구하는 알고리즘. 시간복잡도 O(ElogV)
//   * 플로이드 워셜 : '모든 정점'에서 '다른 모든 정점'으로의 최단 경로를 구하는 알고리즘. 시간복잡도 O(V^3)

public class FloydWarshall_BOJ_11404 {
    public static int v, e;
    public static int[][] dp = new int[102][102];  // minDistSumArr. 실상 [V+1][V+1] 길이만큼만 선언하면 됨.
    public static final int INF = 100000 * 100 + 2;  // 주의: '100000(가중치 최대값) x 100(정점 최대개수)'로 설정. 'Integer.MAX_VALUE' 사용하면 viaDistSum 계산에서 오버플로우 발생.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        v = Integer.parseInt(br.readLine());  // 정점의 개수 (문제상 n)
        e = Integer.parseInt(br.readLine());  // 간선의 개수 (문제상 m)
        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                if(i == j) continue;  // 자기자신에게 이동하므로, 그대로 0 가중치 유지.
                dp[i][j] = INF;
            }
        }

        StringTokenizer stt;
        int start, end, betweenDist;
        while(e-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            betweenDist = Integer.parseInt(stt.nextToken());  // cost 이면서 distSum을 의미.

            dp[start][end] = Math.min(dp[start][end], betweenDist);  // 문제상 '시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다' 명시되어 있으므로, 그중 최소비용의 간선을 선택.
            // ==> 이때문에 플로이드 워셜의 시간복잡도는 간선의 개수(e)와는 관련이 없고, 오직 정점의 개수(v)에만 영향을 받음.
        }

        // 플로이드 워셜 메인로직
        // * dp[i][j] : 'i → ... → j' 최소 전체 거리합 (차후 갱신 가능)
        // * dp[i][viaNode] + dp[viaNode][j] : 'i → ... → viaNode → ... → j' 경유지포함 전체 거리합 (dp 갱신 후보)
        for(int viaNode=1; viaNode<=v; viaNode++) {  // 중간에 경유할 노드 번호 (경유지)
            for(int i=1; i<=v; i++) {  // 첫 출발할 노드 번호 (출발지)
                for(int j=1; j<=v; j++) {  // 마지막 도착할 노드 번호 (도착지)
                    int viaDistSum = dp[i][viaNode] + dp[viaNode][j];
                    dp[i][j] = Math.min(dp[i][j], viaDistSum);
                }
            }
        }

        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                stb.append(dp[i][j] != INF ? dp[i][j] : 0).append(" ");
            }
            stb.append("\n");
        }
        System.out.print(stb.toString());
    }
}
