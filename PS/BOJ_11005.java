import java.util.*;

// - 알고리즘: 진법(진수) 변환

public class BOJ_11005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num10 = sc.nextInt();
        int jin = sc.nextInt();

        String jinStr = Integer.toString(num10, jin);  // 소문자로 반환됨.
        jinStr = jinStr.toUpperCase();  // 대문자로 변환.
        System.out.print(jinStr);
    }
}
