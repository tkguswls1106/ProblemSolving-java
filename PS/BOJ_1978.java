import java.util.*;
import java.io.*;

public class BOJ_1978 {
    public static boolean isPrimeNumber(int num) {
        if(num < 2) return false;

        for(int i=2; i<=Math.sqrt(num); i++) {  // i*i<=num. 어차피 여기서 2,3 등의 범위는 무시처리됨.
            // System.out.printf("num: %d, i: %d, numi: %d\n", num, i, num%i)
            if(num%i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int n = Integer.parseInt(br.readLine());  // n은 어차피 100 이하.
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(stt.nextToken());
            if(isPrimeNumber(input)) answer++;
        }

        System.out.print(answer);
    }
}
