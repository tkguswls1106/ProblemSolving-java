import java.util.*;
import java.io.*;

// - 알고리즘: HashMap (이분탐색으로도 풀 수 있으나, 이 문제는 Set이 더 빠름.)

public class BOJ_2776 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        int n, m, num;
        StringTokenizer stt;
        Set<Integer> s;
        while(t-- > 0) {
            s = new HashSet<>();

            n = Integer.parseInt(br.readLine());
            stt = new StringTokenizer(br.readLine());
            while(n-- > 0) {
                num = Integer.parseInt(stt.nextToken());
                s.add(num);
            }

            m = Integer.parseInt(br.readLine());
            stt = new StringTokenizer(br.readLine());
            while(m-- > 0) {
                num = Integer.parseInt(stt.nextToken());
                String printStr = s.contains(num) ? "1\n" : "0\n";
                stb.append(printStr);
            }
        }

        System.out.print(stb.toString());
    }
}
