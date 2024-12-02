// [ 트리 (Tree) ]
// - 개념 특징 :
// 트리란 자료구조의 일종이며, '사이클이 없이' '모든 정점이 연결'되어있는 무방향의 '그래프'이다. (중요 1)
// 모든 정점이 연결되어있지만 사이클이 없기에, 정점의 개수가 V개이면 간선의 개수는 V-1개이다. (중요 2)
// 사이클이 존재하지 않으므로, 한 정점에서 다른 정점으로 갈 수 있는 방법(경로로써의 경우의 수)은 단 1가지뿐이다. (중요 3-1)
// 즉, 혹시나 또다른 더 빠른 고속도로 간선이있는가하고 직통길이 존재하는지 의심할 필요가 없다. (중요 3-2)
// 어차피 경로의 경우의 수는 1가지뿐이기에, 멀리있으면 직통길없이 일일이 노드들을 타고타고 가야만 한다. (중요 3-3)
// 하나의 노드에 자식 노드가 여러개일 수 있다.
// 루트 노드는 존재할 수도 있지만, 아니면 따로 존재하지않고 그저 그래프 형태처럼 생길 수도 있다.
// 기본적으로 트리는 그래프의 특별한 한 종류이기 때문에, BFS 및 DFS 알고리즘을 적용시킬 수 있다.
// - 문제 Tip :
// 문제에서 노드의 개수는 N개이고, 다음 N-1개의 줄에 트리상에 연결된 두 점을 입력해준다고 했다.
// 이는 트리의 성질을 비추어보았을때, 모든 간선을 알려준다는 의미이므로, 혹시나 연결된 또다른 간선은 없는지 추가적으로 의심할 필요가 없다.
// 이에 더불어, 사이클이 존재하지 않으므로, 직통길없이 한 정점에서 다른 정점까지의 경로로써의 경우의 수는 단 1가지 뿐임을 명심하자.

// < DFS 방식 >
// - 주특징 : BFS가 아닌 DFS방식으로 트리를 탐색.

import java.util.*;
import java.io.*;
import java.awt.*;

public class Tree_BOJ_1240 {
    // adj[start]에 {그다음정점, 거리} 이렇게 값이 삽입됨.
    public static ArrayList<Point>[] adj = new ArrayList[1002];  // 'new Point[1002];'로 작성하면 틀림. 헷갈리지말것!
    public static int[] visited = new int[1002];
    public static int finalEnd;
    public static StringBuilder stb = new StringBuilder();

    public static void dfs(int start, int distSum) {
        if(start == finalEnd) {
            stb.append(distSum).append("\n");
            return;
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
        int m = Integer.parseInt(stt.nextToken());

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

            adj[start].add(new Point(end, betweenDist));  // 이렇게되면 adj[start]에 여러 Point가 쌓이므로, 차후엔 adj[주요인덱스][쌓이는만큼인덱스].x or .y 이런식으로 불러내야함.
            adj[end].add(new Point(start, betweenDist));  // 이렇게되면 adj[start]에 여러 Point가 쌓이므로, 차후엔 adj[주요인덱스][쌓이는만큼인덱스].x or .y 이런식으로 불러내야함.
        }

        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            finalEnd = end;

            visited[start] = 1;  // 이 문제의 dfs는 백트래킹과 다른 dfs이므로, 사실상 이처럼 먼저 visited=1을 선언하고 가도 좋음.
            dfs(start, 0);

            visited = new int[1002];
        }

        System.out.print(stb.toString());
    }
}

/*
// < BFS 방식 >
// - 주특징 : DFS가 아닌 BFS방식으로 트리를 탐색.

import java.util.*;
import java.io.*;
import java.awt.*;

public class Tree_BOJ_1240 {
    // adj[start]에 {그다음정점, 거리} 이렇게 값이 삽입됨.
    public static ArrayList<Point>[] adj = new ArrayList[1002];  // 'new Point[1002];'로 작성하면 틀림. 헷갈리지말것!
    public static int[] dist = new int[1002];
    public static StringBuilder stb = new StringBuilder();

    public static void bfs(int start) {
        // if(dist[start] > 0) return;  // 사실상 이건 필요없음.

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        dist[start] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(Point nextAdj : adj[cur]) {  // 'ArrayList<Point>'로 작성하면 틀림. 헷갈리지말것!
                int next = nextAdj.x;
                int betweenDist = nextAdj.y;

                if(dist[next] > 0) continue;

                qu.offer(next);
                dist[next] = dist[cur] + betweenDist;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());

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

            adj[start].add(new Point(end, betweenDist));  // 이렇게되면 adj[start]에 여러 Point가 쌓이므로, 차후엔 adj[주요인덱스][쌓이는만큼인덱스].x or .y 이런식으로 불러내야함.
            adj[end].add(new Point(start, betweenDist));  // 이렇게되면 adj[start]에 여러 Point가 쌓이므로, 차후엔 adj[주요인덱스][쌓이는만큼인덱스].x or .y 이런식으로 불러내야함.
        }

        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            
            bfs(start);
            stb.append(dist[end] - 1).append("\n");  // 애초에 dist값을 0이 아닌 1로 시작했기에, 결과 출력할때는 -1 해주고 출력해야함.

            dist = new int[1002];
        }

        System.out.print(stb.toString());
    }
}
*/