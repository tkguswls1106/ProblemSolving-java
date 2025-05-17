import java.util.*;

// - 알고리즘: 스택

class Solution {
    public static class Plan {
        public String name;
        public int startTime;
        public int restTime;
        
        public Plan(String[] arr) {
            this.name = arr[0];
            this.startTime = getMinute(arr[1]);
            this.restTime = Integer.parseInt(arr[2]);
        }
        
        public int getEndTime() {
            return this.startTime + this.restTime;
        }
    }
    
    public static int getMinute(String timeStr) {
        int sumMinute = 0;
        StringTokenizer stt = new StringTokenizer(timeStr, ":");
        sumMinute += Integer.parseInt(stt.nextToken()) * 60;
        sumMinute += Integer.parseInt(stt.nextToken());
        return sumMinute;
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;
        
        List<Plan> planList = new ArrayList<>();
        for(String[] arr : plans) {
            Plan plan = new Plan(arr);
            planList.add(plan);
        }
        Collections.sort(planList, (planA, planB) -> {
            return planA.startTime - planB.startTime;  // 시작시각 기준 오름차순 정렬
        });
        
        Stack<Plan> st = new Stack<>();  // 잠시 멈춘 과제들
        for(int i=0; i<=planList.size()-2; i++) {
            Plan curP = planList.get(i);
            Plan nextP = planList.get(i+1);
            
            // 현재 plan을 언제까지 진행가능한지 판단
            if(curP.getEndTime() > nextP.startTime) {  // 도중 멈춰야할 과제일 경우
                curP.restTime -= nextP.startTime - curP.startTime;
                st.push(curP);
            }
            else {  // 멈추지 않고 끝까지 진행가능한 과제일 경우
                answer[idx++] = curP.name;  // 진행 완료
                
                int curTime = curP.getEndTime();
                while(curTime < nextP.startTime && !st.isEmpty()) {  // 멈췄던 과제 진행
                    Plan stP = st.pop();
                    if(curTime + stP.restTime <= nextP.startTime) {  // 완전히 끝낼수있는 경우
                        answer[idx++] = stP.name;
                        curTime += stP.restTime;
                    }
                    else {  // 완전히 끝내지못하고 또다시 멈춰야하는 경우
                        stP.restTime -= nextP.startTime - curTime;
                        st.push(stP);
                        break;  // 'curTime = nextP.startTime;' 코드와 효과 동일.
                    }
                }
            }
        }
        
        answer[idx++] = planList.get(planList.size()-1).name;
        while(!st.isEmpty()) {
            Plan stP = st.pop();
            answer[idx++] = stP.name;
        }
        
        return answer;
    }
}

public class PGS_과제진행하기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
