import java.util.*;
import java.io.*;

// [ 연결리스트(LinkedList) + ListIterator ]

public class LinkedList_ListIterator_BOJ_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt;
        String str = br.readLine();

        LinkedList<Character> list = new LinkedList<>();
        for(char ch : str.toCharArray()) {
            list.addLast(ch);
        }

        ListIterator<Character> iter = list.listIterator();  // 참고로 '양방향 ListIterator'는 '단방향 Iterator'와는 다른것임.
        while(iter.hasNext()) {  // 커서를 맨뒤로 이동하고 시작.
            iter.next();
        }

        int n = Integer.parseInt(br.readLine());
        String op;
        char addCh;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            op = stt.nextToken();

            if(op.equals("L")) {
                if(!iter.hasPrevious()) continue;
                iter.previous();  // 커서(사실상 사이사이가 아닌 실제 요소를 가리킴)를 앞으로 이동
            }
            else if(op.equals("D")) {
                if(!iter.hasNext()) continue;
                iter.next();  // 커서를 뒤로 이동
            }
            else if(op.equals("B")) {
                if(!iter.hasPrevious()) continue;
                iter.previous();  // 과정 1. 커서를 앞으로 이동
                iter.remove();  // 과정 2. 현재 커서의 요소를 삭제
                // ==> 커서 왼쪽의 요소를 삭제 (과정 두번 필요)
            }
            else {
                addCh = stt.nextToken().charAt(0);
                iter.add(addCh);  // 과정 1. 현재 커서의 앞에 요소를 추가
                // ==> 커서 왼쪽에 요소를 추가 (과정 한번에 가능)
            }
        }

        StringBuilder stb = new StringBuilder();
        for(char ch : list) {
            stb.append(ch);
        }
        System.out.print(stb.toString());
    }
}
