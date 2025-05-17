// < 성공 방법 1 >
// - 알고리즘: 우선순위큐 (+ 기존 Array sort)

import java.util.*;

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
                return this.time - b.time;  // 실상 이 정렬기준만 있으면 됨.
            }
            else {
                return this.request - b.request;
            }
        }
    }
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        PriorityQueue<Task> pq = new PriorityQueue<>();
        int curTime = 0;
        int sum = 0;
        int arrIdx = 0;
        
        // while문 허용 : 아직 모든 작업을 확인하지않았거나 or 아직 우선순위큐에 담긴 task들을 모두 소비하지 못한경우
        while(arrIdx <= jobs.length-1 || !pq.isEmpty()) {
            while(arrIdx <= jobs.length-1 && jobs[arrIdx][0] <= curTime) {
                int request = jobs[arrIdx][0];
                int time = jobs[arrIdx][1];
                pq.offer(new Task(request, time));
                arrIdx++;
            }
            
            if(!pq.isEmpty()) {
                Task curTask = pq.poll();
                int endTime = curTime + curTask.time;
                sum += (endTime - curTask.request);
                curTime = endTime;
            }
            else {
                int request = jobs[arrIdx][0];
                curTime = request;
            }
        }
        
        return sum / jobs.length;
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 우선순위큐 (+ 신규 TreeMap)

import java.util.*;

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
        Queue<Integer> requestQu = new LinkedList<>(m.keySet());  // 또는 TreeMap의 firstKey() 메소드를 활용할것.
        
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
*/

public class PGS_디스크컨트롤러 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
