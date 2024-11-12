import java.util.*;

// - 알고리즘: 그리디
// 두 큐를 합이 '큰 쪽에서 작은쪽으로' 숫자를 추출해서 더해주며, '이 행위'를 '양쪽의 합이 같아질 때까지 반복'하여 풂.
// - 시간복잡도: O(N=30만)

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> qu1 = new LinkedList<>();
        Queue<Integer> qu2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        for(int num : queue1) {
            qu1.offer(num);
            sum1 += num;
        }
        for(int num : queue2) {
            qu2.offer(num);
            sum2 += num;
        }
        
        int cnt = 0;
        while(sum1 != sum2) {
            if(cnt >= queue1.length * 4) {
                // queue1, queue2의 모든 원소가 자리바꿈하여 다시 원래의 위치로 오기 위한 횟수
                // = (queue1.length + queue2.length) * 2 = queue1.length * 4
                return -1;
            }
            
            if(sum1 > sum2) {
                int num = qu1.poll();
                sum1 -= num;
                qu2.offer(num);
                sum2 += num;
            }
            else {
                int num = qu2.poll();
                sum2 -= num;
                qu1.offer(num);
                sum1 += num;
            }
            cnt++;
        }
        
        return cnt;
    }
}

public class PGS_두큐합같게만들기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
