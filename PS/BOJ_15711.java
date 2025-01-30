import java.util.*;
import java.io.*;

// - 알고리즘: 에라토스테네스의 체 (with 골드바흐의 추측)
// - 문제 Tip:
// '4이상의 짝수 = 소수 + 소수' 표현 가능. (골드바흐의 추측)
// '3이하의 수 = 소수 + 소수' 표현 불가능.
// '3이상의 홀수 = 짝수 + 홀수' 표현 반드시 성립.

public class BOJ_15711 {
    // < 2000001 배열길이 도출과정 >
    // sum-2가 소수인지 확인해야함
    // -> sum 범위 = A(2 x 10^12) + B(2 x 10^12) = 4 x 10^12
    // -> 소수 확인은 실상 ?x?로써 앞의 하나만 알면 되기에, 범위는 (4 x 10^12)의 제곱근임.
    // -> (4 x 10^12)의 제곱근 = 2 x 10^6 = 2000000
    // -> isPrimeArr 배열길이를 2000001로 설정.
    public static boolean[] isPrimeArr = new boolean[2000001];
    public static List<Long> primeList = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void addStb(boolean isYes) {
        stb.append(isYes ? "YES\n" : "NO\n");
    }

    public static void runEratos() {
        Arrays.fill(isPrimeArr, true);
        isPrimeArr[0] = isPrimeArr[1] = false;

        // 이미 범위를 제곱근해주었으므로, for문도 기존 에라토스방식대로 사용하지않고 변형해야함.
        for(int i=2; i<=2000000; i++) {
            if(isPrimeArr[i] == true) {
                primeList.add((long) i);
                for(int j=2; i*j<=2000000; j++) {  // i*j = sum
                    isPrimeArr[i*j] = false;
                }
            }
        }
    }

    public static boolean isPrime(long sum) {
        if(sum <= 2000000) return isPrimeArr[(int) sum];
    
        // 만약 확인허용가능한 배열길이 초과시
        for(long primeNum : primeList) {
            if(sum%primeNum == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        runEratos();
        
        long a, b, sum;
        StringTokenizer stt;
        while(t-- > 0) {
            stt = new StringTokenizer(br.readLine());
            a = Long.parseLong(stt.nextToken());
            b = Long.parseLong(stt.nextToken());
            sum = a + b;

            if(sum < 4) {  // 합이 3이하 경우 => 소수+소수 불가능.
                addStb(false);
            }
            else if(sum%2 == 0) {  // 합이 4이상 && 짝수 경우 => 소수+소수 가능.
                addStb(true);
            }
            else {  // 합이 4이상 && 홀수 경우
                long innerHol = sum - 2;  // '3이상의 홀수(sum) = 짝수(2) + 홀수(innerHol)'이며, 짝수 소수는 2밖에 안됨.
                if(isPrime(innerHol) == true) {  
                    addStb(true);
                }
                else {
                    addStb(false);
                }
            }
        }

        System.out.print(stb.toString());
    }
}
