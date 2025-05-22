// < 실패 방법 >
// - 답은 맞는듯하나, 메모리 초과로 실패.
// - 알고리즘 : BFS + 다중 HashSet

import java.util.*;
import java.io.*;

public class BOJ_1987_Fail {
    public static int n, m;
    public static char[][] board = new char[22][22];
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int maxCnt = 1;

    public static class Vertex {
        public int x;
        public int y;
        public Set<Character> chSet;  // visited 용도로도 활용.

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            this.chSet = new HashSet<>();
        }

        public Vertex(int x, int y, Set<Character> chSet) {
            this.x = x;
            this.y = y;
            this.chSet = new HashSet<>(chSet);
        }

        public boolean contains(char ch) {
            return this.chSet.contains(ch);
        }

        public void add(char ch) {
            this.chSet.add(ch);
        }

        public int getSize() {
            return this.chSet.size();
        }
    }

    public static void bfs(int x, int y) {
        Queue<Vertex> qu = new LinkedList<>();
        Vertex vertex = new Vertex(x, y);
        qu.offer(vertex);
        vertex.add(board[x][y]);
        
        while(!qu.isEmpty()) {
            Vertex cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(cur.contains(board[nx][ny]) == true) continue;

                vertex = new Vertex(nx, ny, cur.chSet);
                qu.offer(vertex);
                vertex.add(board[nx][ny]);
                maxCnt = Math.max(maxCnt, vertex.getSize());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<m; j++) {
                char inputCh = inputStr.charAt(j);
                board[i][j] = inputCh;
            }
        }

        bfs(0, 0);
        
        System.out.print(maxCnt);
    }
}
