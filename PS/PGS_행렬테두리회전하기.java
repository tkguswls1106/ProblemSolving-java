// - 문제 Tip:
// (x1,y1) (x1,y2)
// (x2,y1) (x2,y2)

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows][columns];
        
        int num = 1;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = num++;
            }
        }
        
        int idx = 0;
        for(int[] quer : queries) {
            int x1 = quer[0]-1, y1 = quer[1]-1, x2 = quer[2]-1, y2 = quer[3]-1;
            
            int curNum = 1;
            int prevNum = arr[x1][y1];
            int minNum = (int) 1e9;
            for(int y=y1+1; y<=y2; y++) {  // 왼 -> 오
                curNum = arr[x1][y];  // 이동 전, 미리 현재값 저장
                arr[x1][y] = prevNum;  // 이동
                minNum = Math.min(minNum, prevNum);  // 이동 후, 최솟값 비교
                prevNum = curNum;  // 다음 요소로 넘어가기 전, prevNum값 갱신
            }
            for(int x=x1+1; x<=x2; x++) {  // 위 -> 아래
                curNum = arr[x][y2];
                arr[x][y2] = prevNum;
                minNum = Math.min(minNum, prevNum);
                prevNum = curNum;
            }
            for(int y=y2-1; y>=y1; y--) {  // 오 -> 왼
                curNum = arr[x2][y];
                arr[x2][y] = prevNum;
                minNum = Math.min(minNum, prevNum);
                prevNum = curNum;
            }
            for(int x=x2-1; x>=x1; x--) {  // 아래 -> 위
                curNum = arr[x][y1];
                arr[x][y1] = prevNum;
                minNum = Math.min(minNum, prevNum);
                prevNum = curNum;
            }
            
            answer[idx++] = minNum;
        }
        
        return answer;
    }
}

public class PGS_행렬테두리회전하기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
