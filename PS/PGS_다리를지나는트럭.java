import java.util.*;

// - 알고리즘: Queue

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> qu = new LinkedList<>();
        for(int i=0; i<bridge_length; i++) {  // 다리길이만큼 미리 대기시간용도의 대체숫자(-1)을 넣어줌.
            qu.offer(-1);
        }
        
        int truck;
        int sum = 0, time = 0, cnt = 0;  // 올라간 트럭무게합, 경과시간, 지나간 성공트럭 대수
        for(int i=0; i<truck_weights.length; i++) {
            time++;
            truck = qu.poll();
            if(truck != -1) {
                sum -= truck;
                cnt++;
                if(cnt >= truck_weights.length) return time;
            }
            
            if(sum + truck_weights[i] > weight) {  // 무게 초과 예정시
                qu.offer(-1);
                i--;
            }
            else {
                qu.offer(truck_weights[i]);
                sum += truck_weights[i];
            }
        }
        
        while(!qu.isEmpty()) {
            time++;
            truck = qu.poll();
            if(truck != -1) {
                cnt++;
                if(cnt >= truck_weights.length) return time;
            }
        }
        
        return time;
    }
}

public class PGS_다리를지나는트럭 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
