import java.util.*;

// - 알고리즘: 순열
// - 또다른 풀이법:
// https://isshosng.tistory.com/149
// 위 링크의 DFS 알고리즘 방식은 내 풀이와 유사하나,
// 코드가 더 간결하여 참고용으로 기재함.

class Solution {
    public static List<Pair> list = new ArrayList<>();
    // public static List<Integer> selected = new ArrayList<>();  // 문제상 불필요한 리스트임.
    public static boolean[] visited = new boolean[10];
    public static int maxVisitCnt = -1;
    
    public static class Pair {
        public int limit;
        public int cost;
        
        public Pair(int[] arr) {
            this.limit = arr[0];
            this.cost = arr[1];
        }
    }
    
    public static void perm(int cnt, int visitCnt, int rest) {  // 순열
        if(cnt == list.size()) {
            maxVisitCnt = Math.max(maxVisitCnt, visitCnt);
            return;
        }
        
        for(int i=0; i<list.size(); i++) {
            if(visited[i] == true) continue;
            
            visited[i] = true;
            int nextVisitCnt = (rest >= list.get(i).limit) ? visitCnt+1 : visitCnt;
            perm(cnt+1, nextVisitCnt, rest - list.get(i).cost);
            
            visited[i] = false;
        }
    }
     
    public int solution(int k, int[][] dungeons) {
        for(int[] arr : dungeons) {
            if(arr[0] <= k) list.add(new Pair(arr));
        }
        
        perm(0, 0, k);
        
        return maxVisitCnt;
    }
}

public class PGS_피로도 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
