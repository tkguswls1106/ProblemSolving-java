import java.util.*;
import java.io.*;
import java.awt.*;

// [ 트리 긴지름 문제 ]
// - 문제 Tip :
// 이 문제는 DFS로 풀었음.
// 과정 1. 특정 정점에서부터 한번 dfs를 돌아서 가장 멀리 있는 리프노드지점을 찾는다.
// 과정 2. 찾은 해당 지점에서부터 다시 dfs를 돌아서 가장 멀리 있는 지점과의 거리를 구하면 된다.

public class Tree_BOJ_1967 {
    public static ArrayList<Point>[] adj = new ArrayList[10002];  // 'new Point[10002];'로 작성하면 틀림. 헷갈리지말것!
    public static int[] visited = new int[10002];
    public static int maxEndPoint = 0, maxDistSum = 0;

    public static void dfs(int start, int distSum) {
        if(maxDistSum < distSum) {
            maxEndPoint = start;
            maxDistSum = distSum;
        }

        for(Point nextAdj : adj[start]) {  // 'ArrayList<Point>'로 작성하면 틀림. 헷갈리지말것!
            int next = nextAdj.x;
            int betweenDist = nextAdj.y;

            if(visited[next] == 1) continue;

            visited[next] = 1;
            dfs(next, distSum + betweenDist);
            // 이 문제의 경우에는 여기서 'visited[next] = 0'를 작성하지않아도 된다. (왜냐면, 이건 백트래킹으로써의 push&pop 으로 되돌아가는 용도의 dfs가 아니기때문임.)
            // - 주의: 백트래킹의 dfs에서만 "'push & visited=1 & dfs호출' 이후 'pop & visited=0'" 이러한 과정을 거치기때문에,
            // 이처럼 백트래킹이 아닌 일반적인 dfs를 사용하는 문제의 경우에는, 어차피 백트래킹처럼 pop으로 되돌아가질 않기때문에, 차후 pop이나 visited=0 의 과정은 작성하지 말아야한다.
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());

        // 다차원배열 초기화
        for (int i=0; i<=n; i++) {  // index = 1 ~ n
            adj[i] = new ArrayList<>();
        }

        int start, end, betweenDist;
        n--;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            betweenDist = Integer.parseInt(stt.nextToken());

            adj[start].add(new Point(end, betweenDist));
            adj[end].add(new Point(start, betweenDist));
        }

        visited[1] = 1;  // 루트노드부터 시작.
        dfs(1, 0);
        visited = new int[10002];  // 재초기화
        maxDistSum = 0;  // 재초기화

        visited[maxEndPoint] = 1;  // 찾은지점부터 시작.
        dfs(maxEndPoint, 0);

        System.out.print(maxDistSum);
    }
}