import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: BFS

public class BOJ_9328 {
    public static int n, m;  // h, w
    public static char[][] board;
    public static boolean[][] visited;
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static Set<Character> keyS = new HashSet<>();
    public static int stealCnt = 0;
    public static List<Point> notOpenList = new ArrayList<>();

    public static boolean bfs(int x, int y) {
        boolean isGetKey = false;
        char boardCh = board[x][y];
        if(boardCh == '*' || visited[x][y] == true) return false;

        Queue<Point> qu = new LinkedList<>();
        if('a' <= boardCh && boardCh <= 'z') {
            if(keyS.contains(boardCh) == false) {
                keyS.add(boardCh);
                isGetKey = true;
            }
        }
        else if('A' <= boardCh && boardCh <= 'Z') {
            char openableKey = Character.toLowerCase(boardCh);
            if(keyS.contains(openableKey) == false) {
                notOpenList.add(new Point(x, y));
                return false;
            }
        }
        else if(boardCh == '$') {  // 문서인 경우
            stealCnt++;
        }
        qu.offer(new Point(x, y));
        visited[x][y] = true;
        board[x][y] = '.';

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                boardCh = board[nx][ny];
                if(boardCh == '*' || visited[nx][ny] == true) continue;

                if('a' <= boardCh && boardCh <= 'z') {
                    if(keyS.contains(boardCh) == false) {
                        keyS.add(boardCh);
                        isGetKey = true;
                    }
                }
                else if('A' <= boardCh && boardCh <= 'Z') {
                    char openableKey = Character.toLowerCase(boardCh);
                    if(keyS.contains(openableKey) == false) {
                        notOpenList.add(new Point(nx, ny));
                        continue;
                    }
                }
                else if(boardCh == '$') {  // 문서인 경우
                    stealCnt++;
                }

                qu.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                board[nx][ny] = '.';
            }
        }

        return isGetKey;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        while(tc-- > 0) {
            stt = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stt.nextToken());
            m = Integer.parseInt(stt.nextToken());
            board = new char[n][m];

            List<Point> edgeList = new ArrayList<>();
            String rowStr;
            for(int i=0; i<n; i++) {
                rowStr = br.readLine();
                for(int j=0; j<m; j++) {
                    char boardCh = rowStr.charAt(j);
                    board[i][j] = boardCh;
                    if(i==0 || i==n-1 || j==0 || j==m-1) {
                        edgeList.add(new Point(i, j));
                    }
                }
            }

            rowStr = br.readLine();
            if(!rowStr.equals("0")) {
                for(int i=0; i<rowStr.length(); i++) {
                    char keyCh = rowStr.charAt(i);
                    keyS.add(keyCh);
                }
            }

            boolean isFirstRun = true;
            while(true) {
                visited = new boolean[n][m];  // 주의: 여기서 초기화해야, 열쇠를 얻고 이전 경로로 다시 돌아가 문을 열어볼수있음.
                List<Point> tempList;
                if(isFirstRun == true) {
                    tempList = edgeList;
                    isFirstRun = false;
                }
                else {
                    tempList = new ArrayList<>(notOpenList);
                    notOpenList.clear();
                }

                boolean isGetKey = false;
                for(Point p : tempList) {
                    if(bfs(p.x, p.y) == true) {
                        isGetKey = true;
                    }
                }
                if(isGetKey == false) {
                    notOpenList.clear();  // 여기서도 초기화를 해주어야만함.
                    break;
                }
            }

            stb.append(stealCnt).append("\n");
            keyS.clear();
            stealCnt = 0;
        }

        System.out.print(stb.toString());
    }
}
