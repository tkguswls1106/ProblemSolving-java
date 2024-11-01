import java.util.*;
import java.io.*;

// [ 트라이 (Trie) ]
// - 개념 특징 :
// 트라이는 문자열 집합을 표현하는 트리 자료 구조로, 문자열에 특화된 대표적인 자료 구조이다.
// 문자열(단어)를 문자(글자1개) 단위로 분해하고 트리 내에 순서대로 이어 저장한다.
// 이로써 마치 연결리스트처럼 매핑되어, 큰 문자열을 매우 빠른 시간복잡도로 비교가 가능해진다. 다만 이는 메모리를 많이 소모하는 단점이 있다.

public class Trie_BOJ_5052 {
    public static class Node {
        public Map<Character, Node> childNodeM;  // <'CurrentNode's char': 단어(문자열) 내 현재 글자(문자), 'childNode': 단어 내 그다음 글자들(자식 노드)>
        public boolean isLast;  // 단어(문자열)의 마지막 글자(문자)인가?

        public Node() {
            childNodeM = new HashMap<>();
            isLast = false;
        }
    }

    public static class Trie {
        public Node rootNode;

        public Trie() {
            rootNode = new Node();
        }

        public void insert(String word) {
            Node node = this.rootNode;  // 현재 노드 (자식 노드로 계속 내려가며 교체될 예정.)

            for(int i=0; i<word.length(); i++) {  // 단어(문자열) 내 글자(문자) 추출
                char ch = word.charAt(i);

                node.childNodeM.putIfAbsent(ch, new Node());  // Map에 ch가 없다면, 새로운 노드 추가.
                node = node.childNodeM.get(ch);  // 자식 노드로 이동하여 교체(재할당).
            }
            node.isLast = true;  // 자식 노드에 마지막 글자임을 명시.
        }

        public boolean contains(String word) {
            Node node = this.rootNode;  // 현재 노드 (자식 노드로 계속 내려가며 교체될 예정.)

            for(int i=0; i<word.length(); i++) {  // 단어(문자열) 내 글자(문자) 추출
                char ch = word.charAt(i);

                node = node.childNodeM.get(ch);  // 자식 노드로 이동하여 교체(재할당).
                if(node == null) {  // 다음 글자가 Map에 존재하지않아, 단어검색 경로가 끊긴 경우
                    return false;
                }
            }

            // - 검색기준 1. 단어 또는 접두사가 일치할때
            // return true;

            // - 검색기준 2. 단어만 일치할때 (= 접두사 일치 X)
            // return node.isLast;

            // - 검색기준 3. 접두사만 일치할때 (= 단어 일치 X. 본인단어를 제외하고자 활용.)
            if(node.isLast && node.childNodeM.isEmpty()) {  // 단어만 일치할때 (= 본인 단어일때. 이는 글자 검색경로 끝까지 본인을 제외한 접두사 일치가 없었음을 의미.)
                return false;  // 문제에 중복 전화번호는 없다고 명시됨. 하지만 본인 외의 '단어 또는 접두사가 일치하는 경우'가 없기에, 검색결과가 존재하지 않는것으로 반환.
            }
            else {
                return true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        while(testcase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            boolean isYes = true;

            List<String> findWordList = new ArrayList<>();
			for(int i=0; i<n; i++) {
				String word = br.readLine();
                findWordList.add(word);
				trie.insert(word);
			}

            for(String findWord : findWordList) {
                if(trie.contains(findWord)) {
                    isYes = false;
                    break;
                }
            }
            stb.append(isYes ? "YES\n" : "NO\n");
        }

        System.out.print(stb.toString());
    }
}
