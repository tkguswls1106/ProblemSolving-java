import java.util.*;

// [ 이분탐색 기초 정리 ]
// Arrays.binarySearch(배열, 찾을값)
// Collections.binarySearch(리스트, 찾을값)
// 배열(리스트)은 오름차순 정렬되어있어야 한다.
// - 시간복잡도 : O(logN)
// - 동일값 검색에 성공한 경우의 반환값 (0이상 정수값) : 인덱스번호
// - 동일값 검색에 실패한 경우의 반환값 (0미만 음수값) : -(삽입 가능한 인덱스번호) - 1
// ==> 삽입 가능한 인덱스번호 = -(실패 반환값 + 1)  // 즉, 이는 '끝인덱스+1 인덱스' or '찾을값의 초과값이 나타나는 첫 인덱스'를 의미함.

public class BinarySearch_Basic {

    public static void printAll(int arrValue, int listValue) {
        String printStr = String.format("배열: %d, 리스트: %d", arrValue, listValue);
        System.out.println(printStr);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 3, 1, 5, 5, 8 };  // 배열
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 1, 5, 5, 8));  // 리스트

        // value : { 1, 3, 5, 5, 5, 8 }
        // index : { 0, 1, 2, 3, 4, 5 }
        Arrays.sort(arr);
        Collections.sort(list);

        // 검색 성공
        if(Arrays.binarySearch(arr, 5) >= 0) System.out.println("검색 성공!");
        printAll(Arrays.binarySearch(arr, 5), Collections.binarySearch(list, 5));  // ==> 배열: 2, 리스트: 2

        // 검색 실패 & 실패 반환값 출력 : -(삽입 가능한 인덱스번호) - 1 = -6 - 1 = -7 반환.
        if(Arrays.binarySearch(arr, 99) < 0) System.out.println("검색 실패!");
        printAll(Arrays.binarySearch(arr, 99), Collections.binarySearch(list, 99));  // ==> 배열: -7, 리스트: -7
        // 검색 실패 & 삽입 가능한 인덱스번호 출력 : -(실패 반환값 + 1) = -(-7 + 1) = 6 반환.
        printAll(-(Arrays.binarySearch(arr, 99)+1), -(Collections.binarySearch(list, 99)+1));  // ==> 배열: 6, 리스트: 6

        // 검색 실패 & 실패 반환값 출력 : -(삽입 가능한 인덱스번호) - 1 = -0 - 1 = -1 반환.
        printAll(Arrays.binarySearch(arr, -99), Collections.binarySearch(list, -99));  // ==> 배열: -1, 리스트: -1
        // 검색 실패 & 삽입 가능한 인덱스번호 출력 : -(실패 반환값 + 1) = -(-1 + 1) = 0 반환.
        printAll(-(Arrays.binarySearch(arr, -99)+1), -(Collections.binarySearch(list, -99)+1));  // ==> 배열: 0, 리스트: 0
    }
}