import java.util.*;

// - 알고리즘: 우선순위 큐

class Solution {
    public static class Task implements Comparable<Task> {
        public int request;
        public int time;
        
        public Task(int request, int time) {
            this.request = request;
            this.time = time;
        }
        
        @Override
        public int compareTo(Task b) {
            if(this.time != b.time) {
                return this.time - b.time;
            }
            else {
                return this.request - b.request;
            }
        }
    }
    
    public int solution(int[][] jobs) {
        Map<Integer, List<Integer>> m = new TreeMap<>();  // <request, ArrayList<time>>
        for(int[] job : jobs) {
            int request = job[0];
            int time = job[1];
            
            m.putIfAbsent(request, new ArrayList<>());
            m.get(request).add(time);
        }
        Queue<Integer> requestQu = new LinkedList<>(m.keySet());
        
        PriorityQueue<Task> pq = new PriorityQueue<>();
        int curTime = 0;
        int sum = 0;
        int completeCnt = 0;
        while(completeCnt != jobs.length) {
            while(!requestQu.isEmpty() && requestQu.peek() <= curTime) {
                int request = requestQu.poll();
                for(int time : m.get(request)) {
                    pq.offer(new Task(request, time));
                }
            }
            
            if(!pq.isEmpty()) {
                Task curTask = pq.poll();
                int endTime = curTime + curTask.time;
                sum += (endTime - curTask.request);
                curTime = endTime;
                completeCnt++;
            }
            else {
                curTime++;
            }
        }
        
        return sum / jobs.length;
    }
}

public class PGS_디스크컨트롤러 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
