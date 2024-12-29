import java.util.*;

// - 알고리즘: 유클리드 호제법 (GCD, LCM)

public class BOJ_2609 {
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        else return gcd(b, a%b);
    }

    public static int lcm(int a, int b) {
        return a*b / gcd(a,b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        System.out.printf("%d\n%d", gcd(num1,num2), lcm(num1,num2));
    }
}
