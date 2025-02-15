import java.util.*;
import java.io.*;

// - 알고리즘: 중간에서 만나기 + 이분 탐색 + 부분집합
// - 문제 Tip: 부분집합 알고리즘만 활용시, O(2^40)으로 1초의 연산시간을 넘으므로, 중간에서 만나기 알고리즘도 함께 운용할것.

public class BOJ_1208 {
    public static int n, s;
    public static int[] arr;
    public static int[] leftArr, rightArr;
    public static List<Integer> leftSumList = new ArrayList<>(), rightSumList = new ArrayList<>();
    public static long answer = 0;

    public static int lowerBound(int findNum) {  // (rightSumList 기준)
        int left = 0;
        int right = rightSumList.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(rightSumList.get(mid) >= findNum) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static int upperBound(int findNum) {  // (rightSumList 기준)
        int left = 0;
        int right = rightSumList.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(rightSumList.get(mid) > findNum) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static void sub(int selectCnt, int sum, int[] array, List<Integer> sumList) {  // array의 부분집합 확인 (리스트는 참조로 원격add 가능.)
        if(selectCnt == array.length) {
            sumList.add((int) sum);
            return;
        }

        sub(selectCnt + 1, sum + array[selectCnt], array, sumList);
        sub(selectCnt + 1, sum, array, sumList);  // 추가 안함.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        s = Integer.parseInt(stt.nextToken());
        arr = new int[n];

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(stt.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);  // 이로 인해, leftArr와 rightArr 모두 정렬 상태됨.

        leftArr = Arrays.copyOfRange(arr, 0, n/2+1);
        rightArr = Arrays.copyOfRange(arr, n/2+1, arr.length);

        sub(0, 0, leftArr, leftSumList);
        sub(0, 0, rightArr, rightSumList);
        Collections.sort(rightSumList);

        for(int leftSum : leftSumList) {
            int findNum = s - leftSum;  // (leftSumList 기준)
            int lowerBoundIdx = lowerBound(findNum);  // (rightSumList 기준)
            int upperboundIdx = upperBound(findNum);  // (rightSumList 기준)

            int cnt = upperboundIdx - lowerBoundIdx;  // 중복값 개수 = 초과값 인덱스 - 이상값 인덱스
            answer += cnt;
        }

        if(s == 0) answer--;  // 크기가 양수인 부분집합이라 했으므로, 공집합을 제외.
        System.out.print(answer);
    }
}
