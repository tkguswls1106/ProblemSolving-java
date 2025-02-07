// < 성공 방법 1 >
// - 알고리즘: 이분 탐색 (with lowerBound & upperBound)

import java.util.*;
import java.io.*;

// [ 이분탐색 (Binary Search) 중복개수 문제 ]
// - 문제 Tip: 중복된 요소들의 개수 = upperBound 인덱스 - lowerBound 인덱스

public class BinarySearch_LowerUpperBound_BOJ_10816 {
    public static int[] arr;

    public static int lowerBound(int findNum) {  // 검색요소의 이상값이 처음 나타나는 위치의 index 반환. 
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] >= findNum) {  // 이상값 검색
                right = mid - 1;  // 더 앞의 위치에도 이상값 요소가 존재하는지 확인하기위함.
            }
            else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static int upperBound(int findNum) {  // 검색요소의 초과값이 처음 나타나는 위치의 index 반환. 
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] > findNum) {  // 초과값 검색
                right = mid - 1;  // 더 앞의 위치에도 초과값 요소가 존재하는지 확인하기위함.
            }
            else {
                left = mid + 1;
            }
        }
        
        return right;
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