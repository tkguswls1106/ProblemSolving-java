// < 성공 방법 1 >
// - 알고리즘: 좌표압축 (with 이분탐색)

import java.util.*;
import java.io.*;
import java.util.stream.*;

// [ 좌표압축 ]
// - 개념 특징 :
// 수의 범위가 매우 크게 주어질 때, 값에 무관하게 단지 대소관계만을 알면 될 때 이용하는 알고리즘이다.
// 또한 이는 원소의 순위를 매기는 랭킹 문제에도 응용이 가능하다.
// 기본적으로 해당 알고리즘은 정렬, 이분탐색을 이용하는 편이다.
// - 시간복잡도 :
// 좌표압축을 이용하지않고 풀면, 시간복잡도가 O(N^2)로써 시간초과가 뜬다.
// 그래서 이를 이분탐색 등등을 활용한 좌표압축으로 풀면 성공적인 풀이가 가능하다.

public class CoordinateCompression_BOJ_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        
        List<Integer> originList = new ArrayList<>();
        while(n-- > 0) {
            int inputNum = Integer.parseInt(stt.nextToken());
            originList.add(inputNum);
        }
        List<Integer> distinctSortList = originList.stream()
                                            .distinct()
                                            .sorted()
                                            .collect(Collectors.toList());
                                            
        StringBuilder stb = new StringBuilder();
        for(int findNum : originList) {
            int idx = Collections.binarySearch(distinctSortList, findNum);  // 인덱스 번호 == 그 미만의 원소 개수 (예시로 인덱스번호가 2보다 작은것은 0,1 이므로, 이는 2개로써 인덱스번호와 같음.)
            stb.append(idx + " ");
        }

        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 좌표압축 (with HashMap)

import java.util.*;
import java.io.*;

public class CoordinateCompression_BOJ_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        
        List<Integer> originList = new ArrayList<>();
        List<Integer> sortList = new ArrayList<>();
        while(n-- > 0) {
            int inputNum = Integer.parseInt(stt.nextToken());
            originList.add(inputNum);
            sortList.add(inputNum);
        }
        Collections.sort(sortList);

        Map<Integer, Integer> m = new HashMap<>();
        int rank = 0;
        for(int num : sortList) {
            if(!m.containsKey(num)) {
                m.put(num, rank++);
            }
        }
                
        StringBuilder stb = new StringBuilder();
        for(int findNum : originList) {
            stb.append(m.get(findNum) + " ");
        }

        System.out.print(stb.toString());
    }
}
*/