import java.util.*;

// - 알고리즘: 우선순위큐

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int idx = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 최소힙        
        for(int i=0; i<score.length; i++) {
            pq.offer(score[i]);
            
            if(i < k) {
                answer[idx++] = pq.peek();
            }
            else {
                pq.poll();
                answer[idx++] = pq.peek();
            }
        }
        
        return answer;
    }
}

public class PGS_명예의전당1 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
