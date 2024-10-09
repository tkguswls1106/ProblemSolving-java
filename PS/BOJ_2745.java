import java.util.*;

// - 알고리즘: 진법(진수) 변환

public class BOJ_2745 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String jinStr = sc.next();
        int jin = sc.nextInt();

        int num10 = Integer.parseInt(jinStr, jin);
        System.out.print(num10);
    }
}
