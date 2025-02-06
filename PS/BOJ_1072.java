import java.util.*;

// - 알고리즘: 이분탐색

public class BOJ_1072 {
    public static int getZ(int x, int y) {
        return (int) ((long) y * 100 / x);  // 어차피 소수점은 버리라고 문제에 명시되어있음.
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        int z = getZ(x, y);  // current Z
        if(z >= 99) {  // 승률 99%에서 100%로 올리는것은 불가능. 그리고 100% 위는 없음.
            System.out.print(-1);
            return;
        }

        int answer = -1;
        int left = 1, right = 1000000000;  // 추가게임 횟수 기준임. (y 결과값 기준 아님. 헷갈리지말것!)
        while(left <= right) {
            int mid = (left + right) / 2;  // 추가게임 횟수
            int nextX = x + mid;
            int nextY = y + mid;
            int nextZ = getZ(nextX, nextY);

            if(nextZ > z) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.print(answer);
    }
}
