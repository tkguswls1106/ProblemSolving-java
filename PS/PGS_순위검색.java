import java.util.*;

// - 알고리즘: 부분집합 + 이분탐색 + 문자열 구현

class Solution {
    public static Map<String, ArrayList<Integer>> m = new HashMap<>();  // <cbjc, 점수리스트>
    public static char commonCh = '-';
    public static StringBuilder infoStb, selectStb;
    public static int infoScore;
    
    public static void sub(int cnt) {  // 유사 부분집합
        if(cnt == infoStb.length()) {
            String selectStr = selectStb.toString();
            m.putIfAbsent(selectStr, new ArrayList<>());
            m.get(selectStr).add(infoScore);
            return;
        }
        
        selectStb.append(infoStb.charAt(cnt));
        sub(cnt+1);
        
        selectStb.setCharAt(selectStb.length()-1, commonCh);  // delete Last & append
        sub(cnt+1);
        
        // List 백트래킹이라면 remove()로 선택종류와 길이 모두 되돌아갈 수 있지만,
        // 이 문제 StringBuilder 경우에는 선택종류는 되돌아가도 '-'를 다시 더해주어 길이 자체는 백트래킹으로 되돌아가지 않음.
        // ==> 때문에 한번더 delete 하여 '-' 등의 존재여부 자체를 지움으로써 길이를 줄여주어야함.
        selectStb.deleteCharAt(selectStb.length()-1);  // delete Last
    }
    
    public static int lowerBound(int findNum, ArrayList<Integer> list) {  // 이분탐색으로 이상값 위치 찾기.
        int left = 0;
        int right = list.size();
        while(left < right) {
            int mid = (left + right) / 2;
            if(list.get(mid) >= findNum) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        
        return right;
    }
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        int idx = 0;
        
        for(String str : info) {
            infoStb = new StringBuilder();
            selectStb = new StringBuilder();
            
            StringTokenizer stt = new StringTokenizer(str);
            for(int i=0; i<4; i++) {
                char ch = stt.nextToken().charAt(0);
                infoStb.append(ch);
            }
            infoScore = Integer.parseInt(stt.nextToken());
            sub(0);
        }
        
        for(ArrayList<Integer> list : m.values()) {
            Collections.sort(list);  // 이분탐색을 위해 미리 정렬.
        }
        
        for(String str : query) {
            str = str.replace(" and ", " ");
            StringBuilder stb = new StringBuilder();
            
            StringTokenizer stt = new StringTokenizer(str);
            for(int i=0; i<4; i++) {
                char ch = stt.nextToken().charAt(0);
                stb.append(ch);
            }
            infoScore = Integer.parseInt(stt.nextToken());
            
            ArrayList<Integer> list = m.get(stb.toString());
            if(list != null) {
                int lowerBoundIdx = lowerBound(infoScore, list);
                int lowerBoundCnt = list.size() - lowerBoundIdx;
                answer[idx++] = lowerBoundCnt;
            }
            else {
                answer[idx++] = 0;
            }
        }
        
        return answer;
    }
}

public class PGS_순위검색 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
