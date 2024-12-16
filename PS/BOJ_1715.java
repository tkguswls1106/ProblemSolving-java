import java.util.*;
import java.io.*;

// - 알고리즘: 우선순위큐 + 그리디
// - 문제 Tip: 매번 그리디로 가장 작은 카드묶음부터 묶는다.

public class BOJ_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 최소힙
        while(n-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        int n1, n2, sumN;
        while(pq.size() > 1) {
            n1 = pq.poll();
            n2 = pq.poll();
            sumN = n1 + n2;
            pq.offer(sumN);
            answer += sumN;
        }

        System.out.print(answer);
    }
}
