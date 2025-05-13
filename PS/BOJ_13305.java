import java.util.*;
import java.io.*;
import java.math.*;  // import java.math.BigInteger;

// - 알고리즘 : 그리디
// - 문제 Tip :
// 예시로 각 주유소의 리터당 비용이 '8 9 9 6 7 7 3 2 1 1' 이라면,
// '8 8 8 6 6 6 3 2 1 X' 처럼 최소가 나올때까지 현재의 최소값으로 미리 주유시키면 됨.

public class BOJ_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> distList = new ArrayList<>();
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n-1; i++) {
            int dist = Integer.parseInt(stt.nextToken());  // 숫자 범위가 최대 10억이므로, int 자료형으로도 충분함.
            distList.add(dist);
        }

        List<Integer> costList = new ArrayList<>();
        stt = new StringTokenizer(br.readLine());
        int minCost = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            int cost = Integer.parseInt(stt.nextToken());  // 숫자 범위가 최대 10억이므로, int 자료형으로도 충분함.
            if(i != n-1) {  // 어차피 마지막 주유소는 계산에 이용되지않음.
                minCost = Math.min(minCost, cost);
                costList.add(minCost);
            }
        }
        
        BigInteger sum = BigInteger.valueOf(0);
        for(int i=0; i<distList.size(); i++) {
            BigInteger dist = BigInteger.valueOf(distList.get(i));
            BigInteger cost = BigInteger.valueOf(costList.get(i));

            BigInteger wayCost = dist.multiply(cost);  // 곱셈 : wayCost = dist * cost
            sum = sum.add(wayCost);  // 덧셈 : sum += wayCost
        }

        System.out.print(sum);
    }
}
