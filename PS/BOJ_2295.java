// < 성공 방법 >
// - 알고리즘: 이분탐색

import java.util.*;
import java.io.*;

public class BOJ_2295 {    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr = Arrays.stream(arr).boxed()
                    .sorted((a,b) -> b - a)  // 이 배열은 내림차순 정렬해둠. (차후 큰값부터 sum을 검색하기위해.)
                    .mapToInt(i->i)
                    .toArray();

        Set<Integer> twoSet = new TreeSet<>();
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {  // 중복선택 가능
                twoSet.add(arr[i] + arr[j]);
            }
        }
        int[] twoArr = twoSet.stream().mapToInt(i->i).toArray();  // 오름차순 정렬해두어야, 이분탐색 가능.

        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {  // 중복선택 가능
                // a + b + c = sum
                // two + c = sum
                // two = sum - c
                // ==> twoArr에서 위 two값을 이분탐색하여 빠르게 검색.
                int two = arr[i] - arr[j];  // sum이 큰값부터
                if(Arrays.binarySearch(twoArr, two) >= 0) {  // 이분탐색 검색에 성공한 경우
                    int answer = arr[i];  // answer = sum
                    System.out.print(answer);
                    return;
                }
            }
        }
    }
}
