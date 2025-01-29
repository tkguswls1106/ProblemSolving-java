import java.util.*;
import java.io.*;

// - 알고리즘: 에라토스테네스의 체

public class BOJ_6588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        // 에라토스테네스의 체 (소수 찾기)
        Boolean[] isPrimeArr = new Boolean[1000001];
        Arrays.fill(isPrimeArr, true);
        isPrimeArr[0] = isPrimeArr[1] = false;
        for(int i=2; i*i<=1000000; i++) {
            if(isPrimeArr[i] == true) {
                for(int j=2; i*j<=1000000; j++) {
                    isPrimeArr[i*j] = false;
                }
            }
        }

        while(true) {
            int inputNum = Integer.parseInt(br.readLine());
            if(inputNum == 0) {
                System.out.print(stb.toString());
                return;
            }

            boolean isBreak = false;
            for(int a=3; a<=inputNum/2; a+=2) {
                int b = inputNum - a;
                if(isPrimeArr[a] && isPrimeArr[b]) {
                    stb.append(inputNum).append(" = ").append(a).append(" + ").append(b).append("\n");  // String.format으로 하면 시간초과됨.
                    isBreak = true;
                    break;
                }
            }
            if(!isBreak) stb.append("Goldbach's conjecture is wrong.\n");
        }
    }
}
