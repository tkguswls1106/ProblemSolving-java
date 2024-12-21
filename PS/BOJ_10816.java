import java.util.*;
import java.io.*;

public class BOJ_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        Map<Integer, Integer> m = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(stt.nextToken());
            m.put(input, m.getOrDefault(input, 0) + 1);
        }

        n = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(stt.nextToken());
            Integer cnt = m.get(input);
            stb.append(cnt != null ? cnt : 0).append(" ");
        }

        System.out.print(stb.toString());
    }
}
