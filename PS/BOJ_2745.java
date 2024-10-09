import java.util.*;

// - 알고리즘: 진법(진수) 변환

public class BOJ_2745 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int jin = sc.nextInt();

        int num = Integer.parseInt(str, jin);
        System.out.print(num);
    }
}
