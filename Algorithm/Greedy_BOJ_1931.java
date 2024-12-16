import java.util.*;
import java.io.*;

// [ 그리디(Greedy) 회의실배정 문제 (Task Scheduling) ]
// - 문제 Tip :
// 다음 회의를 고르는 기준을, 끝나는 시간이 가장 빠른 회의를 선택하게 하면 된다.
// 단, (2,2)처럼 시작하자마자 끝나는 경우의 회의가 있을수 있으므로, 위의 정렬 우선기준 다음의 두번째 정렬 기준으로는 시작시간이 빠른 기준으로 정렬할 수 있도록 해야한다.
// ==> 정렬 우선순위 1: 끝나는시간 오름차순, 정렬 우선순위 2: 시작시간 오름차순

public class Greedy_BOJ_1931 {
    public static class Pair implements Comparable<Pair> {
        public int start;
        public int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair b) {
            if(this.end != b.end) {
                return this.end - b.end;
            }
            else {
                return this.start - b.start;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Pair> pList = new ArrayList<>();
        StringTokenizer stt;
        int start, end;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stt.nextToken());
            end = Integer.parseInt(stt.nextToken());
            pList.add(new Pair(start, end));
        }
        Collections.sort(pList);

        Pair prevTask = pList.get(0);
        pList.remove(0);
        int cnt = 1;

        for(Pair task : pList) {
            if(prevTask.end <= task.start) {  // '이전 회의 종료시간'보다 '다음 회의 시작시간'이 같거나 빨라야함.
                cnt++;
                prevTask = task;
            }
        }

        System.out.print(cnt);
    }
}
