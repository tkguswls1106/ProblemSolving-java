import java.util.*;

class Solution {
    public static String getSuffixMessage(String str) {
        if(str.equals("Enter")) return "님이 들어왔습니다.";
        else return "님이 나갔습니다.";
    }
    
    public String[] solution(String[] record) {
        Map<String, String> m = new HashMap<>();  // <id, nickname>
        
        int len = 0;
        for(String str : record) {
            String[] strArr = str.split(" ");
            if(strArr.length == 3) m.put(strArr[1], strArr[2]);  // Enter or Change
            if(!strArr[0].equals("Change")) len++;  // Enter or Leave
        }
        String[] answer = new String[len];
        
        int idx = 0;
        for(String str : record) {
            String[] strArr = str.split(" ");
            if(!strArr[0].equals("Change")) {
                String suffixMessage = getSuffixMessage(strArr[0]);
                answer[idx++] = m.get(strArr[1]) + suffixMessage;
            }
        }
        
        return answer;
    }
}

public class PGS_오픈채팅방 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
