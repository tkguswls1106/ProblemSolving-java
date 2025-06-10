import java.util.*;
import java.io.*;

// [ 스위핑 (Sweeping) - '최대로 겹친 선분들의 개수' 문제 (with 라인 스위핑) ]
// - 코드 특징 :
// 이 문제는 '선분의 끝 점에서 겹치는 것은 겹치는 것으로 세지 않는다.'라는 조건이 있으므로,
// Event 클래스의 커스텀 정렬시, 같은 좌표일 경우, 끝점인 좌표가 시작점인 좌표보다 앞에 오도록 정렬해야함.
// 예시로 {1시작,5끝}과 {5시작,10끝} 선분들이 있을 때,
// * '시작점 < 끝점' 잘못된 정렬 : '1시작(1) 5시작(1+1=2) 5끝(2-1=1) 10끝(1-1=0)' 정렬하면, 5시작때 겹치지 않아야하지만 2회로 측정되는 문제가 발생함.
// * '끝점 < 시작점' 올바른 정렬 : '1시작(1) 5끝(1-1=0) 5시작(0+1=1) 10끝(1-1=0)' 정렬하면, 5끝때 겹친개수를 빼주며 1회로 측정되어 올바르게 작동함.
// !!! 단, 위 조건이 없는 문제라면 반대로 '시작점 < 끝점' 정렬방식이 옳은 방식이 됨. !!!
// etc) 이러한 운용 방식을 '이벤트 정렬'이라고 부르기도함.

public class Sweeping_BOJ_1689 {
    public static class Event implements Comparable<Event> {
        public int x;  // 단일 좌표
        public int type;  // 시작점: -1, 끝점: 1

        public Event(int x, int type) {
            this.x = x;
            this.type = type;
        }

        @Override
        public int compareTo(Event b) {
            if(this.x != b.x) {
                return this.x - b.x;
            }
            else {
                return b.type - this.type;
                // '선분의 끝 점에서 겹치는 것은 겹치는 것으로 세지 않는다.' 조건으로 인해, '끝점 < 시작점'이 올바른 정렬방식임.
                // !!! 만약 위 조건이 없는 문제라면, 'return this.type - b.type;'으로 '시작점 < 끝점' 정렬방식을 사용하면 됨. !!!
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Event> evList = new ArrayList<>();
        StringTokenizer stt;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stt.nextToken());
            int e = Integer.parseInt(stt.nextToken());
            evList.add(new Event(s, -1));  // 시작점
            evList.add(new Event(e, 1));  // 끝점
        }
        Collections.sort(evList);
        
        if(n == 1) {
            System.out.print(1);
            return;
        }

        Event prevEvent = evList.get(0);
        int cnt = 1, maxCnt = cnt;
        for(int i=1; i<evList.size(); i++) {
            Event event = evList.get(i);
            boolean isStart = (event.type == -1);

            if(isStart == true) {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
            }
            else {
                cnt--;
            }
        }

        System.out.print(maxCnt);
    }
}
