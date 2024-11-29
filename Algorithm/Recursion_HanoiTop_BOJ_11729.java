import java.util.*;

public class Recursion_HanoiTop_BOJ_11729 {
    public static StringBuilder stb = new StringBuilder();

    public static void hanoi(int start, int mid, int end, int n) {  // start:1, mid:2, end:3 이다. 참고로 특히 첫번째와 세번째 매개변수가 가장 중요함.
        if(n == 1) {
            stb.append(start).append(" ").append(end).append("\n");
        }
        else {
            // < n개의 원반을 1(start)에서 3(end)으로 옮기기 위한 하노이탑 알고리즘 >
            hanoi(start, end, mid, n - 1);  // 과정1. n-1개의 원반을 1(start)에서 2(mid)로 옮김.
            stb.append(start).append(" ").append(end).append("\n");  // 과정2. 남은것들중 가장큰 밑의 원반 하나를 1(start)에서 3(end)으로 옮김.
            hanoi(mid, start, end, n - 1);  // 과정3. 옮겨뒀던 n-1개의 원반을 2(mid)에서 3(end)으로 옮김.
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 하노이탑 최소 이동횟수의 공식 : '2^n - 1' (최소 이동횟수 출력)
        int moveCnt = (int)Math.pow(2, n) - 1;  // pow 함수 앞에 (int)를 적지 않으면, 입력 최대가 20인 pow 함수의 특성상 오차가 커져서 에러가 발생함.
        stb.append(moveCnt).append("\n");

        // 이동순서 출력
        hanoi(1, 2, 3, n);  // 1을 전부 3으로 옮기고 싶을때

        System.out.print(stb.toString());
    }
}
