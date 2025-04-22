import java.util.*;
import java.io.*;

// - 알고리즘: 문자열 (StringBuilder + Stack)

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
