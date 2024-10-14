import java.util.*;
import java.io.*;

public class BOJ_11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Long, Integer> m = new HashMap<>();
        while(n-- > 0) {
            long num = Long.parseLong(br.readLine());
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        long answer = -1;
        int maxCnt = -1;
        for(Map.Entry<Long, Integer> entry : m.entrySet()) {
            long card = entry.getKey();
            int cnt = entry.getValue();
            if(maxCnt < cnt) {
                answer = card;
                maxCnt = cnt;
            }
            else if(maxCnt == cnt) {
                answer = Math.min(answer, card);
            }
        }

        System.out.print(answer);
    }
}
