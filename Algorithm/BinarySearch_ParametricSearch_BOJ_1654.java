import java.util.*;
import java.io.*;

// [ 매개변수 탐색 (Parametric Search) with 이분 탐색 - 기초 ]
// - 개념 특징 :
// 이분(이진) 탐색을 사용하여 조건을 만족하는 최댓값 또는 최솟값을 구하는 알고리즘이다.
// 이는 '최적화 문제'를 '결정 문제'로 바꾸어서 푼다는 특징이 있다.
// 참고로 최적화 문제란, 여러 개의 해답 중에서 주어진 조건을 만족하는 최적의 해답을 찾는 문제이다.
// => ex) 특정 범위 내에서, 가능한 값들을 점진적으로 좁혀나가며 시도해 해답을 찾아감.
// 결정 함수란, 조건을 만족하면 true를, 만족하지 않으면 false를 반환하는 함수로써, 반환 값에 따라서 검사 범위를 변경하게 된다.
// => ex) 특정 범위 내에서, 현재 이 높이를 적용하면 문제의 조건을 만족할 수 있는가? true or false
// - 코드 특징 :
// 이분 탐색을 활용하나, 컬렉션의 binarySearch() 메소드 없이 직접 구현해야 한다.
// 이분 탐색을 활용하나, 이 문제처럼 배열을 정렬하지 않고 시작하는 경우도 있다. (단, 정렬이 필요한 문제도 있음.)
// (예시로 이 문제의 경우, 잘려진 랜선의 길이를 배열에서 가져와 매개변수로 넣어주는것이 아닌, 범위 내의 숫자로 매개변수를 설정하여 활용함. => 때문에 배열의 정렬 필요 X.)

public class BinarySearch_ParametricSearch_BOJ_1654 {
    public static int n;  // 필요한 랜선의 개수
    public static List<Integer> lanList = new ArrayList<>();  // 정렬 필요 X.
    
    // 결정함수 (조건 만족시 true, 불만족시 false)
    // 매개변수 값: 자르려는 각 랜선의 길이, curN 값: 잘려서 나온 총 랜선의 개수
    public static boolean isValidCut(long cutLan) {
        long curN = 0;  // 잘려서 나온 총 랜선의 개수 (그래프 끝으로 갈수록 2^31까지 무한정으로 늘어나기에, long 자료형이어야만 함.)
        for(int lan : lanList) {
            curN += (lan / cutLan);
            if(curN >= n) break;  // 성능 최적화
        }

        // curN이 더 크다는 건 cutLan이 작다는 의미
        // (= 잘려서 나온 총 랜선의 개수가 더 많게 나왔다는건, 문제 조건에 충족한다는 의미임. 그렇기에 잘려진 각 랜선의 길이를 더 길게해서 실험해봐도 됨.)
        return curN >= n;  // curN>=n이 맞다면 결정함수 true 반환. 그렇지않다면 false 반환.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(stt.nextToken());
        n = Integer.parseInt(stt.nextToken());

        long maxLan = -1;
        while(k-- > 0) {
            int lan = Integer.parseInt(br.readLine());
            maxLan = Math.max(maxLan, lan);
            lanList.add(lan);
        }

        // left, right, mid 값 = 자르려는 랜선의 길이 범위를 의미.
        long left = 1;
        long right = maxLan;  // 어차피 최대로 나올수있는 잘린 랜선 하나의 길이(범위)는, 이미 존재하는 랜선중 가장 긴 랜선보다 같거나 작을수밖에 없음.
        while(left <= right) {
            long mid = (left + right) / 2;

            if(isValidCut(mid) == true) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        // left와 right 범위가 안쪽 방향으로 모이며, 최종적으로 밑처럼 while문이 종료됨.
        // - left : 범위를 넘으며, 결국 조건을 만족하지 않는 지점에서 종료됨.
        // - right : 조건을 만족했던 가장 큰 값에서 종료됨.
        // ==> 그러므로, 답은 left가 아닌 right으로 출력해야 옳음.
        System.out.print(right);
    }
}
