import java.util.*;
import java.io.*;

// - 알고리즘 : 우선순위큐
// - 시간복잡도 :
// 1. 정렬 풀이 = O(n^2) = 100000^2 = 10000000000 = 100초 연산 => 제한시간(0.1초) 초과
// 2. 우선순위큐 풀이 = O(nlog(n)) = 100000 x 17 = 1700000 = 0.02초 연산 => 제한시간(0.1초) 통과
// ==> 우선순위큐 풀이 사용할것.
// - 문제 Tip :
// 왼쪽인 최대힙을 우선시하며, 구조는 다음과 같음.
// | 최대힙 1 2 | 3 4 최소힙 |

public class BOJ_1655 {
    public static PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());  // 왼쪽 (최대힙)
    public static PriorityQueue<Integer> minPq = new PriorityQueue<>();  // 오른쪽 (최소힙)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            // offer
            int num = Integer.parseInt(br.readLine());
            if(maxPq.size() == minPq.size()) {
                maxPq.offer(num);
            }
            else {  // 이미 maxPq에 하나 더 들어간 상태.
                minPq.offer(num);
            }

            // swap
            if(!maxPq.isEmpty() && !minPq.isEmpty()) {
                if(maxPq.peek() > minPq.peek()) {
                    int p1 = maxPq.poll();
                    int p2 = minPq.poll();
                    maxPq.offer(p2);
                    minPq.offer(p1);
                }
            }

            // answer
            stb.append(maxPq.peek()).append("\n");
        }
        
        System.out.print(stb.toString());
    }
}
