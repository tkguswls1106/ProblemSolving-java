import java.util.*;
import java.io.*;

// [ 안쪽방향 문제 with 투 포인터 (Two Pointer) ]

// [ 누적합 vs 슬라이딩윈도우 vs 안쪽방향문제 (+ 특징) ]
// - 누적 합 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / end++ 사용.
// - 슬라이딩 윈도우 : 누적합 덧셈 O -> new int[n+1] / 포인터들은 모두 앞에서 뒤 방향으로 이동 -> start<=end / 구간 길이는 고정 -> end++ 대신 nextEnd++ 사용.
// - 안쪽방향 문제 : 누적합 덧셈 X -> new int[n] / 포인터들은 서로 반대쪽에서 안쪽 방향으로 이동 -> start<end / end-- 사용.
// etc-1) 비교변수의 초기값에는 아직 현재 구간을 적용하지 말고, while문 내부의 첫 과정에서 현재 구간 값을 확인 후 적용하여 진행할것.
// etc-2) 누적합 문제에서, 만약 maxSum = Integer.MIN_VALUE 말고 -1로 선언한다면, 음수 합계인 sum값이 -1보다 작아 maxSum값이 갱신되지않을수있어 추천하지않음.

public class TwoPointer_InwardDirection_BOJ_3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];  // 투포인터의 구간 내 누적합 방식이 아닌, 포인터 방향을 안쪽으로 이동하며 두 수를 뽑아 계산하는것이므로 n+1 길이까지는 필요 X.

        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);  // 뽑을 수를 크기별로 탐색하기위해 정렬시킴.
        int x = Integer.parseInt(br.readLine());

        int start = 0, end = arr.length-1;  // end를 맨오른쪽 끝에서부터 시작.
        int cnt = 0;
        while(start<end && end<arr.length) {  // 뽑는 두 수가 i<j라고 명시되었으므로, 기존 투포인터의 누적합 방식인 '<=' 말고 start'<'end 를 적용. 
            int sum = arr[start] + arr[end];
            if(sum == x) {
                cnt++;
                start++;
            }
            else if(sum > x) {
                end--;  // 각 포인터가 양끝에서 시작하므로, 방향이 안쪽으로가야함.
            }
            else {  // (sum < x)
                start++;  // 각 포인터가 양끝에서 시작하므로, 방향이 안쪽으로가야함.
            }
        }

        System.out.print(cnt);
    }
}
