import java.util.*;
import java.io.*;

// - 알고리즘: HashSet

public class BOJ_1269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> s = new HashSet<>();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        int cntA = Integer.parseInt(stt.nextToken());
        int cntB = Integer.parseInt(stt.nextToken());
        int cntAll = cntA + cntB;

        stt = new StringTokenizer(br.readLine());
        while(cntA-- > 0) {
            s.add(Integer.parseInt(stt.nextToken()));
        }
        stt = new StringTokenizer(br.readLine());
        while(cntB-- > 0) {
            s.add(Integer.parseInt(stt.nextToken()));
        }

        int answer = cntAll - (cntAll - s.size()) * 2;
        System.out.print(answer);
    }
}
