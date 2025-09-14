import java.util.*;
import java.awt.Point;

// - 알고리즘: BFS

class Solution {
    public static int n = 0, m = 0;
    public static boolean[][] visited = new boolean[102][102];
    public static int[] dx = {1, 0, -1 ,0};
    public static int[] dy = {0, 1, 0 ,-1};
    public static List<Integer> sumList = new ArrayList<>();
    public static int sum = 0;
    
    public static void bfs(int x, int y, String[] maps) {
        char ch = maps[x].charAt(y);
        if(ch == 'X' || visited[x][y] == true) return;
        
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        visited[x][y] = true;
        sum += (ch - '0');
        
        while(!qu.isEmpty()) {
            Point cur = qu.poll();
            
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                ch = maps[nx].charAt(ny);
                if(ch == 'X' || visited[nx][ny] == true) continue;
                
                qu.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                sum += (ch - '0');
            }
        }
        
        if(sum != 0) {
            sumList.add(sum);
            sum = 0;
        }
    }
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                bfs(i, j, maps);
            }
        }
        
        int len = sumList.size();
        if(len == 0) return new int[]{-1};
        else if(len == 1) return new int[]{sumList.get(0)};  // 길이가 1일때는 정렬 필요없음.
        Collections.sort(sumList);
        
        int[] answer = sumList.stream()
            .mapToInt(i -> i)
            .toArray();
        return answer;
    }
}

public class PGS_무인도여행 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
