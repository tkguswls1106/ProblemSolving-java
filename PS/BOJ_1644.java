import java.util.*;

// - 알고리즘: 에라토스테네스의 체 + 투 포인터

public class BOJ_1644 {
    public static int n;
    public static Boolean[] isPrimeArr;
    public static List<Integer> primeList = new ArrayList<>();

    public static void runEratos() {  // 에라토스테네스의 체
        isPrimeArr = new Boolean[n+1];  // 투포인터의 구간 내 누적합 문제이므로, n이 아닌 n+1 크기로 설정.
        Arrays.fill(isPrimeArr, true);
        isPrimeArr[0] = isPrimeArr[1] = false;

        for(int i=2; i*i<=n; i++) {
            if(isPrimeArr[i] == true) {
                for(int j=2; i*j<=n; j++) {
                    isPrimeArr[i*j] = false;
                }
            }
        }

        for(int i=2; i<isPrimeArr.length; i++) {
            if(isPrimeArr[i] == true) primeList.add(i);
        }
        primeList.add(0);  // 투포인터의 누적합 계산시, 끝 인덱스 확인을 위함. 반드시 맨뒤에 넣을것!!! (ex. 53 입력)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        runEratos();

        int start = 0, end = 0;
        int sum = 0, cnt = 0;
        while(start<=end && end<primeList.size()) {  // 누적합이므로 웬만해서는 start'<='end 활용을 추천.
            if(sum < n) {
                // System.out.printf("- sum: %d / startIdx: %d, endIdx: %d / startValue: %d, endValue: %d / maxIdx: %d\n", sum, start, end, primeList.get(start), primeList.get(end), primeList.size()-1);
                sum += primeList.get(end);
                end++;
            }
            else if(sum > n) {
                sum -= primeList.get(start);
                start++;
            }
            else {  // else if(sum == n)
                // System.out.printf("--- sum: %d / startIdx: %d, endIdx: %d / startValue: %d, endValue: %d / maxIdx: %d\n", sum, start, end, primeList.get(start), primeList.get(end), primeList.size()-1);
                cnt++;
                sum -= primeList.get(start);
                start++;
            }
        }

        System.out.print(cnt);
    }
}
