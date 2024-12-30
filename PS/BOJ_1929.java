import java.util.*;

// - 알고리즘: 에라토스테네스의 체

public class BOJ_1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        boolean[] primeArr = new boolean[n + 1];  // 어차피 이 문제에선 stream 활용안하므로, Boolean 말고 boolean 사용.
        Arrays.fill(primeArr, true);
        primeArr[0] = primeArr[1] = false;

        for(int i=2; i*i<=n; i++) {
            if(primeArr[i] == true) {
                for(int j=2; i*j<=n; j++) {
                    primeArr[i*j] = false;
                }
            }
        }

        StringBuilder stb = new StringBuilder();
        for(int i=m; i<=n; i++) {
            if(primeArr[i] == true) {
                stb.append(i).append("\n");
            }
        }

        System.out.print(stb.toString());
    }
}
