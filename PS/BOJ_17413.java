// < 성공 방법 1 >
// - 알고리즘: 문자열 (StringBuilder + StringBuilder)
// 성공방법 1. StringBuilder : 원시타입 char를 직접 다뤄 박싱/언박싱 비용이 없음. 최적화된 reverse() 메소드로 한번에 뒤집기 가능.
// 성공방법 2. Stack<Character> : 래퍼타입 Character 사용으로 박싱이 발생해 추가적인 메모리 오버헤드 발생. 반복적인 push()/pop() 연산 실행.
// ==> 성공방법2 보다 메모리 효율과 속도가 우수함.

import java.util.*;
import java.io.*;

public class BOJ_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder allStb = new StringBuilder();
        StringBuilder inStb = new StringBuilder();

        char[] chArr = br.readLine().toCharArray();
        boolean isBracket = false;
        for(int i=0; i<chArr.length; i++) {
            if(chArr[i] == '<') {
                if(inStb.length() > 0) {
                    inStb.reverse();
                    allStb.append(inStb);
                    inStb.setLength(0);  // 'inStb = new StringBuilder();' 보다 성능이 우수함.
                }
                allStb.append('<');
                isBracket = true;
            }
            else if(isBracket == true) {
                allStb.append(chArr[i]);
                if(chArr[i] == '>') {
                    isBracket = false;
                }
            }
            else {  // isBracket == false
                if(chArr[i] == ' ') {
                    if(inStb.length() > 0) {
                        inStb.reverse();
                        allStb.append(inStb);
                        inStb.setLength(0);  // 'inStb = new StringBuilder();' 보다 성능이 우수함.
                    }
                    allStb.append(' ');
                }
                else {
                    inStb.append(chArr[i]);
                }
            }
        }

        if(inStb.length() > 0) {
            inStb.reverse();
            allStb.append(inStb);
        }

        System.out.print(allStb.toString());
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: 문자열 (StringBuilder + Stack)
// 성공방법 1. StringBuilder : 원시타입 char를 직접 다뤄 박싱/언박싱 비용이 없음. 최적화된 reverse() 메소드로 한번에 뒤집기 가능.
// 성공방법 2. Stack<Character> : 래퍼타입 Character 사용으로 박싱이 발생해 추가적인 메모리 오버헤드 발생. 반복적인 push()/pop() 연산 실행.
// ==> 성공방법1 보다 메모리 효율과 속도가 좋지않음.

import java.util.*;
import java.io.*;

public class BOJ_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder allStb = new StringBuilder();
        Stack<Character> inSt = new Stack<>();

        char[] chArr = br.readLine().toCharArray();
        boolean isBracket = false;
        for(int i=0; i<chArr.length; i++) {
            if(chArr[i] == '<') {
                while(!inSt.isEmpty()) {
                    allStb.append(inSt.pop());
                }
                allStb.append('<');
                isBracket = true;
            }
            else if(isBracket == true) {
                allStb.append(chArr[i]);
                if(chArr[i] == '>') {
                    isBracket = false;
                }
            }
            else {  // isBracket == false
                if(chArr[i] == ' ') {
                    while(!inSt.isEmpty()) {
                        allStb.append(inSt.pop());
                    }
                    allStb.append(' ');
                }
                else {
                    inSt.push(chArr[i]);
                }
            }
        }

        while(!inSt.isEmpty()) {
            allStb.append(inSt.pop());
        }

        System.out.print(allStb.toString());
    }
}
*/