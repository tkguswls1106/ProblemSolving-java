import java.util.*;
import java.io.*;

// - 알고리즘: 우선순위큐

public class BOJ_14235 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // 최대힙
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stt.nextToken());
            for(int i=0; i<num; i++) {
                pq.offer(Integer.parseInt(stt.nextToken()));
            }

            if(num == 0) {
                if(!pq.isEmpty()) stb.append(pq.poll());
                else stb.append(-1);
                stb.append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
