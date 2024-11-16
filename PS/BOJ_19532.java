import java.util.*;

// - 알고리즘: 브루트포스
// - 시간복잡도: O(2000*2000=400만) 밖에 안나오므로, 브루트포스를 활용 가능하다.

public class BOJ_19532 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt(), e = sc.nextInt(), f = sc.nextInt();

        for(int x=-999; x<=999; x++) {
            for(int y=-999; y<=999; y++) {
                if(a*x + b*y == c && d*x + e*y == f) {
                    System.out.printf("%d %d", x, y);
                    return;
                }
            }
        }
    }
}
