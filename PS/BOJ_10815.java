import java.util.*;
import java.io.*;

public class BOJ_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        Set<Integer> s = new HashSet<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            s.add(Integer.parseInt(stt.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            stb.append(s.contains(Integer.parseInt(stt.nextToken())) ? 1 : 0).append(" ");
        }

        System.out.print(stb.toString());
    }
}
