import java.util.*;
import java.io.*;

// - 알고리즘: 우선순위큐

public class BOJ_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if(Math.abs(a) != Math.abs(b)) {
                    return Math.abs(a) - Math.abs(b);  // 절댓값 기준 오름차순
                }
                else {
                    return a - b;  // 실제값 기준 오름차순
                }
            }
        });

        int x;
        Integer num;
        while(n-- > 0) {
            x = Integer.parseInt(br.readLine());
            if(x != 0) pq.offer(x);
            else {
                num = pq.poll();
                if(num == null) num = 0;
                stb.append(num).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
