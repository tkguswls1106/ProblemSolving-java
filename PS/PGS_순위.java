import java.util.*;

// - 알고리즘 : 플로이드 워셜 (단, 가중치 없이 모든 정점끼리의 이동 및 순위 가능여부만 판단.)
// - 문제 Tip :
// 선수(정점)의 수가 최대 100명이므로, O(V^3) = O(1000000) 연산 횟수로서
// 플로이드 워셜 알고리즘으로 최대 0.01초 안에 충분히 풀이가 가능함.

class Solution {
    public static int[][] board = new int[102][102];  // dp 의미
    
    public int solution(int n, int[][] results) {  // n == v 의미
        for(int[] result : results) {
            // 0: 본인vs본인 or 측정불가, 1: 승리, -1: 패배
            board[result[0]][result[1]] = 1;
            board[result[1]][result[0]] = -1;  // 비록 졌으나, 순위는 매길수있기에 표기 필요함.
        }
        
        for(int viaNode=1; viaNode<=n; viaNode++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    int startToVia = board[i][viaNode];
                    int viaToEnd = board[viaNode][j];
                    
                    if(startToVia == 1 && viaToEnd == 1) {
                        board[i][j] = 1;
                        board[j][i] = -1;
                    }
                    else if(startToVia == -1 && viaToEnd == -1) {
                        board[i][j] = -1;
                        board[j][i] = 1;
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i=1; i<=n; i++) {
            boolean isCanRank = true;
            for(int j=1; j<=n; j++) {
                if(i == j) continue;  // '본인vs본인'은 초기값 그대로 0이기 때문임.
                if(board[i][j] == 0) {
                    isCanRank = false;
                    break;
                }
            }
            if(isCanRank == true) answer++;  // 본인 이외의 모든 정점과 순위를 매길 수 있는 경우
        }
        
        return answer;
    }
}

public class PGS_순위 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
