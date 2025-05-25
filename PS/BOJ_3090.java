import java.util.*;
import java.io.*;

// - 알고리즘 : 매개변수 탐색 (with 이분 탐색)
// - 문제 Tip :
// 탐색(이동) 시, 현재요소(curNum)를 변경하면 다음요소(rNum or lNum) 변경 시 이전 관계가 다시 틀어질 수 있으므로,
// 반드시 다음요소를 기준으로 비교하고 다음요소를 변경해야만함.

public class BOJ_3090 {
    public static int n, t;
    public static int[] arr;

    public static boolean isValid(int maxDist, int[] tempArr) {
        int cnt = 0;
        int lNum = 0, curNum = 0, rNum = 0;

        // 왼쪽에서 오른쪽 방향으로 이동
        for(int i=0; i<n-1; i++) {
            curNum = tempArr[i];
            rNum = tempArr[i+1];

            if(rNum-curNum > maxDist) {  // 주의: 현재요소가 아닌 다음요소를 기준으로 비교하고, 다음요소를 변경해야만함.
                int cutDist = (rNum - curNum) - maxDist;
                tempArr[i+1] -= cutDist;
                cnt += cutDist;
                if(tempArr[i] < 1 || cnt > t) return false;
            }
        }

        // 오른쪽에서 왼쪽 방향으로 이동
        for(int i=n-1; i>=1; i--) {
            curNum = tempArr[i];
            lNum = tempArr[i-1];

            if(lNum-curNum > maxDist) {  // 주의: 현재요소가 아닌 다음요소를 기준으로 비교하고, 다음요소를 변경해야만함.
                int cutDist = (lNum - curNum) - maxDist;
                tempArr[i-1] -= cutDist;
                cnt += cutDist;
                if(tempArr[i] < 1 || cnt > t) return false;
            }
        }

        return cnt <= t;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        t = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }

        int left = 0;  // 최소 차이는 두숫자가 같으면 0이 될 수 있음.
        int right = (int) 1e9 - 1;
        int[] answerArr = new int[0];
        while(left <= right) {
            int mid = (left + right) / 2;
            int[] tempArr = arr.clone();  // 깊은 복사

            if(isValid(mid, tempArr) == true) {
                answerArr = tempArr;  // 얕은 복사 (굳이 깊은복사로 참조를 제거할 필요가 없기 때문.)
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        StringBuilder stb = new StringBuilder();
        for(int num : answerArr) {
            stb.append(num).append(" ");
        }

        System.out.print(stb.toString());
    }
}
