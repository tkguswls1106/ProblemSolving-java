import java.util.*;
import java.io.*;

// - 알고리즘: 에라토스테네스의 체

public class BOJ_21919 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer stt = new StringTokenizer(br.readLine());
        Set<Integer> s = new HashSet<>();
        int maxNum = -1;
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            s.add(num);
            maxNum = Math.max(maxNum, num);
        }

        boolean[] primeArr = new boolean[maxNum + 1];
        Arrays.fill(primeArr, true);
        primeArr[0] = primeArr[1] = false;
        for(int i=2; i*i<=maxNum; i++) {
            if(primeArr[i] == true) {
                for(int j=i*i; j<=maxNum; j+=i) {
                    primeArr[j] = false;
                }
            }
        }

        long answer = 1;
        for(int num : s) {
            if(primeArr[num] == true) {
                answer *= num;
            }
        }

        System.out.print(answer != 1 ? answer : -1);
    }
}
