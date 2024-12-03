import java.util.*;
import java.io.*;

// [ 스위핑 (Sweeping) - 기초 (with 라인 스위핑 문제) ]
// - 개념 특징 :
// '쓸다'라는 뜻이며, 보통 한 쪽 방향부터 시작해서 다른 방향으로 진행하며 탐색하는 과정을 구현하는 것을 의미한다.
// 자료형이 1차원인 경우 라인 스위핑, 2차원인 경우 평면, 3차원인 경우 공간 스위핑이라고 이야기한다.
// - 문제 Tip :
// (시작점, 끝점) 형태로 주어지는 선분을 pair로 넣고 커스텀 오름차순으로 정렬한 후,
// 왼쪽부터 스위핑하면서 조건에 따라 겹치는 선분들을 병합하며 총 선분의 합을 계산하면 된다.
// 이러한 유형은 크게 밑의 3가지 조건을 생각하면 된다.
// 1. 겹치지 않음
//    1-1. if: 이전선분 끝점보다 현재선분 시작점이 더 큼 (= 두 선분이 겹치지 않음)
// 2. 겹침
//    2-1. else-if: 이전선분 끝점보다 현재선분 끝점이 더 큼 (= 두 선분이 일부만 겹침)
//    2-2. else-else: 현재선분이 이전선분 안에 포함됨 (= 두 선분이 전체가 겹침)

public class Sweeping_BOJ_2170 {
    public static class Pair implements Comparable<Pair> {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair b) {
            if(this.x != b.x) {
                return this.x - b.x;  // x 기준 오름차순
            }
            else {
                return this.y - b.y;  // x 같을때, y 기준 오름차순
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt;

        List<Pair> pList = new ArrayList<>();
        int x, y;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            x = Integer.parseInt(stt.nextToken());
            y = Integer.parseInt(stt.nextToken());
            pList.add(new Pair(x, y));
        }
        Collections.sort(pList);  // 커스텀 정렬 실시.

        Pair prevLine = pList.get(0);
        if(n == 1) {
            System.out.print(prevLine.y - prevLine.x);
            return;
        }
        pList.remove(0);

        int sum = prevLine.y - prevLine.x;
        for(Pair line : pList) {
            if(prevLine.y < line.x) {  // 이전선분 끝점보다 현재선분 시작점이 더 큼 (겹치지 않음)
                sum += (line.y - line.x);
                prevLine = line;
            }
            else {  // (겹침)
                if(prevLine.y < line.y) {  // 전체말고 일부만 겹치는경우 (일부만 겹침) 
                    sum += (line.y - prevLine.y);  // 선분연장처럼 삐져나온 남은길이만 더해줌.
                    prevLine = line;  // 실상은 끝점이 중요한것이므로, line으로 덮어씌워저장해도 상관없음.
                }
                // else : 현재선분이 이전선분 안에 포함되어 전체가 겹침 (전체가 겹침) => 예시로, prevLine:{1 4}, line:{2 3}
            }
        }

        System.out.print(sum);
    }
}
