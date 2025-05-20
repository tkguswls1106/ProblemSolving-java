import java.util.*;
import java.awt.Point;

// - 알고리즘 : BFS
// - 문제 Tip :
// 일반적으로는 'x=col, y=row'로 보는게 올바르지만,
// 이 문제에서는 실수를 방지하고 간편하게 접근하고자 'x=row, y=col'로 보고 풀어도 무방함.
// !!! 그리고 좌표를 2배로 확장해서 풀이해야지, 겹치는 부분의 테두리 이동 시 문제가 안생김 !!!

class Solution {
    public static int[][] board = new int[102][102];  // -1: 사각형 내부, 1: 사각형 모서리
    public static int[][] dist = new int[102][102];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int xLen = -1, yLen = -1;
    
    public static void fillRectangle(int[] vertexArr) {
        // 추후 불필요한 탐색 범위를 좁히기위함.
        xLen = Math.max(Math.max(xLen, vertexArr[0]+1), vertexArr[2]+1);
        yLen = Math.max(Math.max(yLen, vertexArr[1]+1), vertexArr[3]+1);
        
        // p1 ㅡ p2
        // |     |
        // p3 ㅡ p4
        Point p3 = new Point(vertexArr[0], vertexArr[1]);
        Point p2 = new Point(vertexArr[2], vertexArr[3]);
        
        for(int i=p3.x; i<=p2.x; i++) {
            for(int j=p3.y; j<=p2.y; j++) {
                if(board[i][j] == -1) continue;  // 다른 사각형의 내부라면 건너뜀.
                
                if(i==p3.x || i==p2.x || j==p3.y || j==p2.y) {  // 본인 사각형 모서리의 경우
                    board[i][j] = 1;
                }
                else {  // 본인 사각형 내부의 경우
                    board[i][j] = -1;  // 다른 사각형의 모서리였어도 내부로 덮어씌움.
                }
            }
        }
    }
    
    public static void bfs(int x, int y, int itemX, int itemY) {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        dist[x][y] = 1;
        
        while(!qu.isEmpty()) {
            Point cur = qu.poll();
            
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx<0 || nx>=xLen || ny<0 || ny>=yLen) continue;
                if(board[nx][ny] != 1 || dist[nx][ny] > 0) continue;
                
                qu.offer(new Point(nx, ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                
                if(nx == itemX && ny == itemY) return;  // 탐색 완료. 성능 누수 방지.
            }
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 모든 사각형들의 내부를 -1로 모서리를 1로 표시
        for(int[] vertexArr : rectangle) {
            for(int i=0; i<vertexArr.length; i++) {
                vertexArr[i] *= 2;  // 좌표 2배 확장
            }
            fillRectangle(vertexArr);
        }
        
        // 출력 테스트 (임시)
        // for(int col=10; col>=0; col--) {
        //     StringBuilder stb = new StringBuilder();
        //     for(int row=0; row<=10; row++) {
        //         stb.append(board[row][col] == -1 ? "i" : board[row][col]).append(" ");
        //     }
        //     System.out.println(stb.toString());
        // }
        
        // 1을 탐색하며, 아이템까지의 거리를 측정
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        
        int answer = (dist[itemX*2][itemY*2] - 1) / 2;
        return answer;
    }
}

public class PGS_아이템줍기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
