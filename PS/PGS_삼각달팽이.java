class Solution {
    // 아래이동: 1 0 , 우측이동: 0 1 , 왼쪽위대각선이동: -1 -1
    public static int[] dx = {1, 0, -1};
    public static int[] dy = {0, 1, -1};
    
    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                arr[i][j] = -1;  // 이동할 직각삼각형 범위 지정.
            }
        }

        int breakCnt=0, x=-1, y=0, num=0;
        for(int i=0; ;) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=n || ny<0 || ny>=n || arr[nx][ny] != -1) {
                breakCnt++;
                if(breakCnt == 3) break;
                
                i = (i+1)%3;  // 밑의 주석코드와 같은 원리임.
                // i++;
                // if(i==3) i=0;
            }
            else {
                breakCnt = 0;
                arr[nx][ny] = ++num;
                
                x = nx;
                y = ny;
            }
        }
        
        int[] answer = new int[num];
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                num = arr[i][j];
                if(num > 0) answer[idx++] = num;
            }
        }
        
        return answer;
    }
}

public class PGS_삼각달팽이 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
