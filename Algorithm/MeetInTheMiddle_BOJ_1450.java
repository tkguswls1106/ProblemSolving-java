import java.util.*;
import java.io.*;

// [ 중간에서 만나기 (Meet in the middle) ]
// - 개념 특징 :
// 이 문제를 예시로, 만약 부분집합 알고리즘만 활용한다면 O(2^30) 시간복잡도로 1초의 연산시간을 훌쩍 넘음.
// 따라서 절반으로 나누어 2번을 계산한다면, O(2^15 x 2) 시간복잡도로 1초 이내로 풀이가 가능해짐.
// 이러한 알고리즘을 '중간에서 만나기'라고 부름.
// - 코드 특징 :
// 배열을 반으로 나눔 -> 각자 계산을 진행 -> 계산된 새로운 두 리스트를 활용하여 비교 진행.
// 풀이 상세과정으로는, 'leftSum[?] + rightSum[?] = Sum' 이므로
// leftSum 리스트의 원소를 순회하면서, 'Sum - leftSum[i]' 이하값이 rightSum 리스트에 몇개 존재하는지 구하면됨.
// - 문제 알고리즘 : 중간에서 만나기 + 이분 탐색 + 부분집합

public class MeetInTheMiddle_BOJ_1450 {
    public static int n, c;
    public static int[] arr;
    public static int[] leftArr, rightArr;
    public static List<Integer> leftSumList = new ArrayList<>(), rightSumList = new ArrayList<>();
    public static int answer = 0;

    public static int upperBound(int findNum, List<Integer> list) {  // findNum 초과값의 인덱스 검색
        int left = 0;
        int right = list.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(list.get(mid) > findNum) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static void sub(int selectCnt, long sum, int[] array, List<Integer> sumList) {  // array의 부분집합 확인 (리스트는 참조로 원격add 가능.)
        if(selectCnt == array.length) {
            if(sum <= (long) c) {  // 가방 0개일때인 공집합도 포함.
                sumList.add((int) sum);
            }
            return;
        }

        sub(selectCnt + 1, sum + array[selectCnt], array, sumList);
        sub(selectCnt + 1, sum, array, sumList);  // 추가 안함.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        c = Integer.parseInt(stt.nextToken());
        arr = new int[n];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int weight = Integer.parseInt(stt.nextToken());
            arr[i] = weight;
        }
        Arrays.sort(arr);  // 이로 인해, leftArr와 rightArr 모두 정렬 상태됨.

        // Arrays.copyOfRange(원본배열, 시작인덱스, 끝인덱스+1)
        leftArr = Arrays.copyOfRange(arr, 0, n/2+1);
        rightArr = Arrays.copyOfRange(arr, n/2+1, arr.length);

        sub(0, 0, leftArr, leftSumList);
        sub(0, 0, rightArr, rightSumList);
        Collections.sort(rightSumList);

        // - 풀이법 -
        // leftSum[?] + rightSum[?] = Sum 이므로,
        // leftSum 리스트의 원소를 순회하면서, 'Sum - leftSum[i]' 이하값이 rightSum 리스트에 몇개 존재하는지 구하면됨.
        for(int leftSum : leftSumList) {
            int findNum = (int) ((long) c - leftSum);  // (leftSumList 기준)
            int upperboundIdx = upperBound(findNum, rightSumList);  // (rightSumList 기준)

            int cnt = upperboundIdx - 0;  // 이하값 개수 = 초과값 인덱스 - 맨앞 인덱스
            answer += cnt;
        }

        System.out.print(answer);
    }
}
