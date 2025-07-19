import java.util.*;
import java.io.*;

// - 알고리즘: 우선순위큐

public class BOJ_1417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n == 1) {
            br.readLine();  // 정상 종료를 위해서는, 문제상 입력을 모두 받긴해야함.
            System.out.print(0);
            return;
        }
        int dasomCnt = Integer.parseInt(br.readLine());  // 다솜이의 표

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // 최대힙
        n--;
        while(n-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while(true) {
            int maxCnt = pq.poll();
            if(dasomCnt > maxCnt) break;
            
            dasomCnt++;
            answer++;
            pq.offer(maxCnt - 1);
        }
        
        System.out.print(answer);
    }
}
