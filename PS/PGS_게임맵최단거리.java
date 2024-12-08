import java.util.*;
import java.io.*;
import java.awt.*;

// - 알고리즘: BFS

class Solution {
    public static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static int[][] board, dist;
    
    public static void bfs(int x, int y) {
        if(board[x][y] == 0 || dist[x][y] > 0) return;
        
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x,y));
        dist[x][y] = 1;
        
        while(!qu.isEmpty()) {
            Point cur = qu.poll();
            
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx<0 || nx>=board.length || ny<0 || ny>=board[0].length) continue;
                if(board[nx][ny] == 0 || dist[nx][ny] > 0) continue;
                
                qu.offer(new Point(nx,ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }
    
    public int solution(int[][] maps) {
        board = maps;
        dist = new int[maps.length][maps[0].length];
        
        bfs(0,0);
        
        int answer = dist[board.length-1][board[0].length-1];
        return (answer != 0) ? answer : -1;
    }
}

public class PGS_게임맵최단거리 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
