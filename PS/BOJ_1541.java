import java.util.*;

// - 알고리즘: 그리디
// - 문제 Tip: 식의 결과값이 최소가 되려면, 가능한 가장 큰 값을 빼주어야함.

public class BOJ_1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.next();

        StringTokenizer minusStt, plusStt;
        minusStt = new StringTokenizer(inputStr, "-");  // 뺄셈을 기준으로 분리.
        int answer = 0;
        boolean isFirst = true;
    
        while(minusStt.hasMoreTokens()) {
            plusStt = new StringTokenizer(minusStt.nextToken(), "+");  // 덧셈을 기준으로 분리.
            int sum = Integer.parseInt(plusStt.nextToken());
            while(plusStt.hasMoreTokens()) {
                sum += Integer.parseInt(plusStt.nextToken());
            }

            if(isFirst) {
                answer += sum;
                isFirst = false;
            }
            else answer -= sum;
        }

        System.out.print(answer);
    }
}
