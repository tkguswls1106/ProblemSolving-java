import java.util.*;
import java.io.*;

// - 알고리즘: 조합 + 응용
// - 문제 함정키워드 및 주의사항:
// 1. "고른 수열은 비내림차순이어야 한다" :
// 예제 출력에서 확인할 수 있듯, 이 조건 때문에 '1 9'는 가능하지만 거꾸로 '9 1'은 불가능함.
// 따라서 순열이 아니라 조합을 활용하는 것이 더 적절함.
// 2. "수열은 사전 순으로 증가하는 순서로 출력해야 한다" :
// 차례대로 나열된 배열이 아닌 무작위 순서의 정수를 입력받는데, 수열은 사전 순으로 증가하는 순서로 출력해야하므로, 입력받은 배열을 오름차순 정렬 후 시작할것.
// 3. "같은 수를 여러 번 골라도 된다" :
// 비록 문제에서 '입력될 N개의 자연수는 모두 다른 수'라고 따로 명시되어있지 않아, 배열 내 중복제거가 필요할 것처럼 보이지만 이는 틀림.
// 이 문제는 조합 문제이므로 중복제거 없이, 같은 재귀(for문) 레벨에서 중복을 체크해 건너뛰는 방식으로 처리하면 됨. 참고로 다른 레벨에서는 같은 수 선택이 허용됨.
// + etc) 만약 여기에 "같은 수를 여러 번 골라도 된다"는 조건이 추가된다면, 'BOJ_15657' 문제처럼 재귀의 'i+1'을 'i'로 변경하여 조합을 중복조합으로만 바꿔주면 됨.

public class Combination_BOJ_15664 {
    public static int n, m;
    public static List<Integer> allList = new ArrayList<>();
    public static List<Integer> selected = new ArrayList<>();
    public static StringBuilder stb = new StringBuilder();

    public static void comb(int start, int cnt) {
        if(cnt == m) {
            for(int num : selected) {
                stb.append(num).append(" ");
            }
            stb.append("\n");
            return;
        }
        
        for(int i=start; i<n; i++) {
            if(start < i && allList.get(i-1) == allList.get(i)) continue;  // 같은 재귀(for문) 레벨에서의 중복을 체크. (다른 레벨에서는 허용됨.)

            selected.add(allList.get(i));
            comb(i+1, cnt+1);

            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        stt = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            allList.add(Integer.parseInt(stt.nextToken()));
        }
        Collections.sort(allList);  // 주의: 코드의 상단 주석에서 설명했듯, 정렬은 필요하지만 중복제거는 적용하면 안됨.

        comb(0, 0);

        System.out.print(stb.toString());
    }
}
