import java.util.*;
import java.io.*;

public class BOJ_10867 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        Set<Integer> s = new TreeSet<>();
        while(n-- > 0) {
            s.add(Integer.parseInt(stt.nextToken()));
        }

        for(int num : s) {
            stb.append(num).append(" ");
        }
        System.out.print(stb.toString());
    }
}
