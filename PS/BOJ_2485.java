import java.util.*;
import java.io.*;

// - 문제 Tip: 가로수들의 사이 간격을 세고, 이들의 최대공약수(GCD)를 구한다.

public class BOJ_2485 {
    public static int gcd(int a, int b) {  // 최대공약수를 구하는 메소드
        if(b == 0) return a;
        else return gcd(b, a%b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> placeList = new ArrayList<>();
        int cnt = n;
        while(cnt-- > 0) {
            int place = Integer.parseInt(br.readLine());
            placeList.add(place);
        }
        Collections.sort(placeList);

        int gcdResult = -1;
        for(int i=0; i<placeList.size()-1; i++) {
            int dist = placeList.get(i+1) - placeList.get(i);  // 가로수 사이의 간격
            gcdResult = (gcdResult != -1) ? gcd(gcdResult, dist) : dist;
        }

        // 새로 심은 뒤 전체 가로수의 수 = (최대위치-최소위치)/최대공약수 + 1
        int allTreeCnt = (placeList.get(n-1) - placeList.get(0)) / gcdResult + 1;
        // 새로 심을 가로수의 수 = 새로 심은 뒤 전체 가로수의 수 - 기존 전체 가로수의 수
        System.out.print(allTreeCnt - placeList.size());
    }
}
