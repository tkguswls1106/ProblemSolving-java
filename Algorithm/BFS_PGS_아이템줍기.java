import java.util.*;
import java.awt.Point;

// [ BFS '겹친 사각형의 최외곽 테두리' 문제 ]
// - 코드 특징 :
// 이 문제는 좌표를 2배로 확장해서 풀어야만, 사각형들이 겹칠 때 테두리 구분이 명확해짐.
// 만약 확장하지 않으면, 아래처럼 의도와 다른 겹쳐진 모양으로 테두리가 연결되어 잘못된 경로가 생길 수 있음.
// 의도한 테두리 모양 (2배 확장) vs 잘못된 테두리 모양 (기본형. 확장 X)
// * * *
// *                          * *
// * * *                      * *
// - 유사 문제 :
// 참고로 'BOJ_2636' 치즈 최외곽 문제는 이 문제와 달리, 테두리의 꼭짓점 경로를 따질 필요 없이
// 그저 주어진 좌표중 외부와 닿는 겉면만을 판별하면 되므로, 좌표 확장이 필요없음.
// 때문에 단순히 치즈(사각형)가 아닌곳들을 BFS 순회해서 visited 적용시키면, 최외곽 테두리만 확인 가능해지는 방식을 활용했던 것임.
// - 문제 Tip :
// 일반적으로는 'x=col, y=row'로 보는게 올바르지만,
// 이 문제에서는 실수를 방지하고 간편하게 접근하고자 'x=row, y=col'로 보고 풀어도 무방함.

class Solution {
    public static int[][] board = new int[102][102];  // -1: 사각형 내부, 1: 사각형 모서리, 0: 초기화용
    public static int[][] dist = new int[102][102];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int xLen = -1, yLen = -1;  // 추후 불필요한 탐색 범위를 좁히기위함.
    
    public static void fillRectangle(int[] vertexArr) {
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
        
        // 1을 탐색하며, 아이템까지의 거리를 측정
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        
        int answer = (dist[itemX*2][itemY*2] - 1) / 2;
        return answer;
    }
}

public class BFS_PGS_아이템줍기 {
    public static void init() {
        Solution.board = new int[102][102];
        Solution.dist = new int[102][102];
        Solution.xLen = -1;
        Solution.yLen = -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int answer1 = solution.solution(new int[][]{{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}}, 1, 3, 7, 8);
        init();
        int answer2 = solution.solution(new int[][]{{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}}, 9, 7, 6, 1);
        init();
        int answer3 = solution.solution(new int[][]{{1,1,5,7}}, 1, 1, 4, 7);
        init();
        int answer4 = solution.solution(new int[][]{{2,1,7,5},{6,4,10,10}}, 3, 1, 7, 10);
        init();
        int answer5 = solution.solution(new int[][]{{2,2,5,5},{1,3,6,4},{3,1,4,6}}, 1, 4, 6, 3);

        System.out.println(answer1);  // => 17
        System.out.println(answer2);  // => 11
        System.out.println(answer3);  // => 9
        System.out.println(answer4);  // => 15
        System.out.println(answer5);  // => 10
    }
}
