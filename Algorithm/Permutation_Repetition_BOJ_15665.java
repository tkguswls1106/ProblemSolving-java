// < 성공 방법 1 >
// - 알고리즘: 중복순열 + 응용

import java.util.*;

// - 문제 함정키워드 및 주의사항:
// 1. "중복되는 수열을 여러 번 출력하면 안되며" :
// 중복순열이라 여러번 뽑아도되지만, 중복되는 '수열'을 여러번 출력하면 안되기에, 입력받은 배열 내 요소들의 중복을 제거하고 시작할것.
// 2. "둘째 줄에 N개의 수가 주어진다" & "수열은 사전 순으로 증가하는 순서로 출력해야 한다" :
// 차례대로 나열된 배열이 아닌 무작위 순서의 정수를 입력받는데, 수열은 사전 순으로 증가하는 순서로 출력해야하므로, 입력받은 배열을 오름차순 정렬 후 시작할것.
// ==> 입력받은 배열의 중복제거 및 오름차순 정렬 후 시작할것.
// ==> 풀이방법 1. 입력값들을 TreeSet에 담고, List로 변환.
// ==> 풀이방법 2. 입력값들을 배열 또는 리스트에 담고, stream & distinct & sorted 적용.

public class Permutation_Repetition_BOJ_15665 {
    public static int n, m;
    public static List<Integer> selected = new ArrayList<>();
    public static List<Integer> allList;
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) stb.append(num + " ");
            stb.append("\n");
            return;
        }

        for(int i=0; i<n; i++) {
            selected.add(allList.get(i));
            perm(cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        Set<Integer> s = new TreeSet<>();  // 중복제거 및 오름차순 정렬
        for(int i=0; i<n; i++) {
            s.add(sc.nextInt());
        }
        allList = new ArrayList<>(s);
        n = allList.size();  // 중복제거 후 n 업데이트

        // 또는 이 코드를 활용해도됨.
        // for(int i=0; i<n; i++) {
        //     allList.add(sc.nextInt());
        // }
        // allList = allList.stream()
        //                 .distinct()  // 중복제거
        //                 .sorted()  // 오름차순 정렬
        //                 .collect(Collectors.toList());
        // n = allList.size();  // 중복제거 후 n 업데이트

        perm(0);
        System.out.print(stb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 중복순열 + 응용

import java.util.*;

public class Permutation_Repetition_BOJ_15665 {
    public static int n, m;
    public static List<Integer> selected = new ArrayList<>();
    public static int[] arr;
    public static StringBuilder stb = new StringBuilder();

    public static void perm(int cnt) {
        if(cnt == m) {
            for(int num : selected) stb.append(num + " ");
            stb.append("\n");
            return;
        }

        for(int i=0; i<n; i++) {
            selected.add(arr[i]);
            perm(cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        arr = Arrays.stream(arr)
                    .distinct()  // 중복제거
                    .sorted()  // 오름차순 정렬
                    .toArray();
        n = arr.length;  // 중복제거 후 n 업데이트

        perm(0);
        System.out.print(stb.toString());
    }
}
*/