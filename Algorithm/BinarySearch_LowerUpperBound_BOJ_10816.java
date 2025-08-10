// < 성공 방법 1 >
// - 알고리즘: 이분 탐색 (with lowerBound & upperBound)

import java.util.*;
import java.io.*;

// [ 이분탐색 (Binary Search) 중복개수 문제 ]
// - 문제 Tip: 중복된 요소들의 개수 = upperBound 인덱스 - lowerBound 인덱스

public class BinarySearch_LowerUpperBound_BOJ_10816 {
    public static int[] arr;

    /*
    // 검색요소의 동일값이 처음 나타나는 위치의 index 반환. (없으면 -1 반환)
    public static int binarySearchFirst(int findNum) {
        int left = 0;
        int right = arr.length - 1;
        int findResult = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] == findNum) {
                findResult = mid;  // 검색 성공.
                right = mid - 1;
            }
            else if(arr[mid] > findNum) {
                right = mid - 1;
            }
            else {  // (arr[mid] < findNum) 경우
                left = mid + 1;
            }
        }

        return findResult;  // -1 반환 시, 검색 실패.
    }
    */

    /*
    // 검색요소의 동일값이 나타나는 위치 중 임의의 index 반환. (없으면 -1 반환)
    public static int binarySearchRandom(int findNum) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] > findNum) {
                right = mid - 1;
            }
            else if(arr[mid] < findNum) {
                left = mid + 1;
            }
            else {
                return mid;  // 검색 성공.
            }
        }

        return -1;  // 검색 실패.
    }
    */

    // 검색요소의 이상값이 처음 나타나는 위치의 index 반환. (없으면 가까운 초과값 index 반환)
    public static int lowerBound(int findNum) {
        int left = 0;
        int right = arr.length;  // 배열의 상한위치까지 탐색하므로, 'arr.length-1' 말고 'arr.length' 사용.
        while(left < right) {  // 위치의 중복검사 무한루프를 방지하고자, 'left<=right' 말고 'left<right' 사용.
            int mid = (left + right) / 2;
            if(arr[mid] >= findNum) {  // 이상값 검색
                right = mid;  // 현재위치를 포함한 더 앞쪽의 탐색을 위해, 'mid-1' 말고 'mid' 사용.
            }
            else {
                left = mid + 1;
            }
        }

        return right;  // right=mid로 할당해두기 때문.
    }

    // 검색요소의 초과값이 처음 나타나는 위치의 index 반환. (없으면 가까운 초과값 index 반환 => 마지막 index+1 반환)
    public static int upperBound(int findNum) {
        int left = 0;
        int right = arr.length;  // 배열의 상한위치까지 탐색하므로, 'arr.length-1' 말고 'arr.length' 사용.
        while(left < right) {  // 위치의 중복검사 무한루프를 방지하고자, 'left<=right' 말고 'left<right' 사용.
            int mid = (left + right) / 2;
            if(arr[mid] > findNum) {  // 초과값 검색
                right = mid;  // 현재위치를 포함한 더 앞쪽의 탐색을 위해, 'mid-1' 말고 'mid' 사용.
            }
            else {
                left = mid + 1;
            }
        }
        
        return right;  // right=mid로 할당해두기 때문.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stt.nextToken());
        }
        Arrays.sort(arr);
        
        int m = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            int findNum = Integer.parseInt(stt.nextToken());

            // !!! 주의사항 : 중복 가능성있는 요소를 검색할때는 컬렉션의 binarySearch() 메소드를 사용하면 안됨. (검색된 맨앞 인덱스의 반환 보장 X) !!!
            // => lowerBound 및 upperBound 이분탐색을 컬렉션 메소드없이 직접 구현해야함.
            int lowerboundIdx = lowerBound(findNum);
            int upperboundIdx = upperBound(findNum);

            int cnt = upperboundIdx - lowerboundIdx;  // 중복된 요소들의 개수 구하기
            stb.append(cnt).append(" ");
        }

        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: Map

import java.util.*;
import java.io.*;

public class BinarySearch_LowerUpperBound_BOJ_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        Map<Integer, Integer> m = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(stt.nextToken());
            m.put(input, m.getOrDefault(input, 0) + 1);
        }

        n = Integer.parseInt(br.readLine());
        stt = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int input = Integer.parseInt(stt.nextToken());
            Integer cnt = m.get(input);
            stb.append(cnt != null ? cnt : 0).append(" ");
        }

        System.out.print(stb.toString());
    }
}
*/