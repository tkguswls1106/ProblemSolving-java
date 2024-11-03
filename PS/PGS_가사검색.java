// < 성공 방법 1 >
// - 알고리즘: Trie

import java.util.*;

class Solution {
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

        public int countContains(String word) {
            Node node = this.rootNode;  // 현재 노드 (자식 노드로 계속 내려가며 교체될 예정.)
            int markLen = 0;  // 물음표 길이 (남은 길이)

            for(int i=0; i<word.length(); i++) {  // 단어(문자열) 내 글자(문자) 추출
                char ch = word.charAt(i);

                if(ch != '?') {
                    node = node.childNodeM.get(ch);  // 자식 노드로 이동하여 교체(재할당).
                    if(node == null) {  // 다음 글자가 Map에 존재하지않아, 단어검색 경로가 끊긴 경우
                        return 0;
                    }
                }
                else {
                    markLen = word.length() - i;
                    break;
                }
            }

            // - 검색기준: 단어 또는 접두사가 일치할때
            return countChildNodes(node, markLen);  // 첫 접두사 검색에 성공한 노드의 다음노드들부터, 남은 길이가 일치되는 밑의 자식들을 전부 개수셈.
        }

        public int countChildNodes(Node node, int markLen) {
            if(node == null) return 0;
            if(markLen == 0) return node.isLast ? 1 : 0;

            int cnt = 0;
            for(Node childNode : node.childNodeM.values()) {
                cnt += countChildNodes(childNode, markLen-1);
            }
            return cnt;
        }
    }

    public static String getReverseStr(String str) {
        StringBuilder stb = new StringBuilder(str);
        return stb.reverse().toString();
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie forwardTrie = new Trie();  // 정방향 트라이
        Trie backwardTrie = new Trie();  // 역방향 트라이

        for(String word : words) {
            forwardTrie.insert(word);
           	backwardTrie.insert(getReverseStr(word)); 
        }

        Map<String, Integer> cacheMap = new HashMap<>();
        int idx = 0;
        for(String quer : queries) {
            if(cacheMap.containsKey(quer)) {
                answer[idx++] = cacheMap.get(quer);
            }
            else {
                if(quer.charAt(0) != '?') {
                    answer[idx] = forwardTrie.countContains(quer);
                }
                else {
                    answer[idx] = backwardTrie.countContains(getReverseStr(quer));
                }
                cacheMap.put(quer, answer[idx]);
                idx++;
            }
        }

        return answer;
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: Trie (with Trie 길이별 배열)

import java.util.*;

class Solution {
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

        public int countContains(String word) {
            Node node = this.rootNode;  // 현재 노드 (자식 노드로 계속 내려가며 교체될 예정.)

            for(int i=0; i<word.length(); i++) {  // 단어(문자열) 내 글자(문자) 추출
                char ch = word.charAt(i);

                if(ch != '?') {
                    node = node.childNodeM.get(ch);  // 자식 노드로 이동하여 교체(재할당).
                    if(node == null) {  // 다음 글자가 Map에 존재하지않아, 단어검색 경로가 끊긴 경우
                        return 0;
                    }
                }
                else {
                    return countChildNodes(node);
                }
            }

            return countChildNodes(node);
        }

        public int countChildNodes(Node node) {
            if(node == null) return 0;
            int cnt = node.isLast ? 1 : 0;  // 현재 노드가 마지막 글자인 경우 1 카운트.

            for(Node childNode : node.childNodeM.values()) {
                cnt += countChildNodes(childNode);
            }
            return cnt;
        }
    }

    public static String getReverseStr(String str) {
        StringBuilder stb = new StringBuilder(str);
        return stb.reverse().toString();
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Trie[] forwardTrieArr = new Trie[10002];  // 정방향 트라이 길이별 배열
        Trie[] backwardTrieArr = new Trie[10002];  // 역방향 트라이 길이별 배열
        for(int i = 0; i < forwardTrieArr.length; i++) {
            forwardTrieArr[i] = new Trie();
            backwardTrieArr[i] = new Trie();
        }

        for(String word : words) {
            forwardTrieArr[word.length()].insert(word);
            backwardTrieArr[word.length()].insert(getReverseStr(word)); 
        }

        Map<String, Integer> cacheMap = new HashMap<>();
        int idx = 0;
        for(String quer : queries) {
            int len = quer.length();

            if(cacheMap.containsKey(quer)) {
                answer[idx++] = cacheMap.get(quer);
            }
            else {
                if(quer.charAt(0) != '?') {
                    answer[idx] = forwardTrieArr[len].countContains(quer);
                }
                else {
                    answer[idx] = backwardTrieArr[len].countContains(getReverseStr(quer));
                }
                cacheMap.put(quer, answer[idx]);
                idx++;
            }
        }

        return answer;
    }
}
*/

public class PGS_가사검색 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
