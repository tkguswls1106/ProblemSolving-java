// < 성공 방법 1 >
// - 알고리즘: 트리 + DFS

import java.util.*;
import java.io.*;

public class BOJ_11725 {
    public static ArrayList<Integer>[] adj = new ArrayList[100002];
    public static int[] visited = new int[100002];
    public static Map<Integer, Integer> m = new TreeMap<>();  // <현재노드, 부모노드>

    public static void dfs(int start) {
        for(int next : adj[start]) {
            if(visited[next] == 1) continue;

            visited[next] = 1;
            if(next >= 2) m.put(next, start);
            dfs(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        // 다차원배열 초기화
        for (int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer stt;
        int start, end;
        n--;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            if(end == 1) {
                end = start;
                start = 1;
            }

            adj[start].add(end);
            if(start == 1) continue;
            adj[end].add(start);
        }

        // DFS 알고리즘 활용
        visited[1] = 1;
        dfs(1);

        for(int value : m.values()) {
            stb.append(value).append("\n");
        }
        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 트리 + BFS

import java.util.*;
import java.io.*;

public class BOJ_11725 {
    public static ArrayList<Integer>[] adj = new ArrayList[100002];
    public static int[] visited = new int[100002];
    public static Map<Integer, Integer> m = new TreeMap<>();  // <현재노드, 부모노드>

    public static void bfs(int start) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        visited[start] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();

            for(int next : adj[cur]) {
                if(visited[next] == 1) continue;

                qu.offer(next);
                visited[next] = 1;
                if(next >= 2) m.put(next, cur);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        // 다차원배열 초기화
        for (int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer stt;
        int start, end;
        n--;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            if(end == 1) {
                end = start;
                start = 1;
            }

            adj[start].add(end);
            if(start == 1) continue;
            adj[end].add(start);
        }

        // BFS 알고리즘 활용
        bfs(1);

        for(int value : m.values()) {
            stb.append(value).append("\n");
        }
        System.out.print(stb.toString());
    }
}
*/