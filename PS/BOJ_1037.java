import java.util.*;
import java.io.*;

public class BOJ_1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        int maxValue = -1, minValue = (int) 1e9;
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(cnt-- > 0) {
            int input = Integer.parseInt(stt.nextToken());
            maxValue = Math.max(maxValue, input);
            minValue = Math.min(minValue, input);
        }

        System.out.print(maxValue * minValue);
    }
}
