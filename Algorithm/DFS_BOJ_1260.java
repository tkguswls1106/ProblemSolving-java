// [ DFS ]

// < 성공 방법 1 - Ver.2 (개선된 코드) >
// 'int[][]' 사용 방법
// => 'ArrayList<Integer>[]' 방법보다 코드가 더 복잡하지만, 별도의 정점번호 정렬과정이 필요없음.

import java.util.*;
import java.io.*;

public class DFS_BOJ_1260 {
    public static int n, m, v;
    public static int[][] board = new int[1002][1002];
    public static int[] visited = new int[1002];
    public static StringBuilder stb = new StringBuilder();

    public static void dfs(int cur) {
        for(int next=1; next<=n; next++) {
            int isCanGo = board[cur][next];
            int isVisited = visited[next];
            if(isCanGo == 0 || isVisited == 1) continue;

            stb.append(next).append(" ");
            visited[next] = 1;
            dfs(next);
        }
    }

    public static void bfs(int x) {
        if(visited[x] == 1) return;

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();
            stb.append(cur).append(" ");  // 여기에 작성해야함.

            for(int next=1; next<=n; next++) {
                int isCanGo = board[cur][next];
                int isVisited = visited[next];
                if(isCanGo == 0 || isVisited == 1) continue;

                qu.offer(next);
                visited[next] = 1;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        v = Integer.parseInt(stt.nextToken());

        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());
            int end = Integer.parseInt(stt.nextToken());
            board[start][end] = 1;
            board[end][start] = 1;
        }

        stb.append(v).append(" ");
        visited[v] = 1;
        dfs(v);
        stb.append("\n");

        visited = new int[n+2];
        bfs(v);
        
        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 1 - Ver.1 >
// 'int[][]' 사용 방법
// => 'ArrayList<Integer>[]' 방법보다 코드가 더 복잡하지만, 별도의 정점번호 정렬과정이 필요없음.

import java.util.*;
import java.io.*;

public class DFS_BOJ_1260 {
    public static int n, m, v;
    public static int[][] board = new int[1002][1002];
    public static int[] visited = new int[1002];

    public static StringBuilder stb = new StringBuilder();

    public static void dfs(int cur) {  // 참고로 dfs(int cur, int cnt) 이렇게해서 if(cnt == n) 할필요없음.
        if(visited[cur] == 1) return;  // 사실 이것도 굳이 필요없음.

        stb.append(cur + " ");
        visited[cur] = 1;

        for(int next=1; next<=n; next++) {
            int isCanGo = board[cur][next];
            int isVisited = visited[next];

            if(isCanGo == 0 || isVisited == 1) continue;

            dfs(next);
        }
    }

    public static void bfs(int cur) {
        if(visited[cur] == 1) return;  // 사실 이것도 굳이 필요없음.

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(cur);
        visited[cur] = 1;

        while(!qu.isEmpty()) {
            int cur2 = qu.poll();
            stb.append(cur2 + " ");  // 여기에 작성해야함.

            for(int next=1; next<=n; next++) {
                int isCanGo = board[cur2][next];
                int isVisited = visited[next];

                if(isCanGo == 0 || isVisited == 1) continue;

                qu.offer(next);
                visited[next] = 1;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        v = Integer.parseInt(stt.nextToken());

        while(m-- > 0) {  // Java에서는 while(m--) 라고 작성하면 에러남. 
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());
            int end = Integer.parseInt(stt.nextToken());
            board[start][end] = 1;
            board[end][start] = 1;
        }

        dfs(v);
        stb.setCharAt(stb.length()-1, '\n');  // 마지막 공백문자를 줄바꿈문자로 교체
        visited = new int[n+2];

        bfs(v);
        stb.deleteCharAt(stb.length()-1);  // 마지막 공백문자를 제거

        System.out.print(stb.toString());
    }
}
*/

/*
// < 성공 방법 2 >
// 'ArrayList<Integer>[]' 사용 방법
// => 'int[][]' 방법보다 코드가 더 간단하지만, 별도의 정점번호 정렬과정이 필요함.

import java.util.*;
import java.io.*;

public class DFS_BOJ_1260 {
    public static int n, m, v;
    public static ArrayList<Integer>[] board = new ArrayList[1002];
    public static int[] visited = new int[1002];
    public static StringBuilder stb = new StringBuilder();

    public static void dfs(int cur) {
        for(int next : board[cur]) {
            if(visited[next] == 1) continue;

            stb.append(next).append(" ");
            visited[next] = 1;
            dfs(next);
        }
    }

    public static void bfs(int x) {
        if(visited[x] == 1) return;

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = 1;

        while(!qu.isEmpty()) {
            int cur = qu.poll();
            stb.append(cur).append(" ");  // 여기에 작성해야함.

            for(int next : board[cur]) {
                if(visited[next] == 1) continue;

                qu.offer(next);
                visited[next] = 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        v = Integer.parseInt(stt.nextToken());
        for(int i=1; i<=n; i++) {  // 어차피 0인덱스는 사용하지않으므로, 굳이 초기화 X.
            board[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stt.nextToken());
            int end = Integer.parseInt(stt.nextToken());
            board[start].add(end);
            board[end].add(start);
        }

        for(int i=1; i<=n; i++) {  // 'ArrayList<Integer>[]' 방법이기에, 별도의 정점번호 정렬과정이 필요.
            Collections.sort(board[i]);  // 작은 정점번호부터 방문하기 위함.
        }

        stb.append(v).append(" ");
        visited[v] = 1;
        dfs(v);
        stb.append("\n");

        visited = new int[n+2];
        bfs(v);
        
        System.out.print(stb.toString());
    }
}
*/