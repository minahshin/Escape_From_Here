import java.util.*;

/*
 * Concept : 숫자를 지워가면서 가장 큰 수를 만들기, 숫자 섞기 x
 * IDEA : Stack을 이용하여 숫자를 넣고 빼며 풀기
 */

class Solution {
    public String solution(String number, int k) {
        char[] numArray = number.toCharArray(); // 숫자 순회용 array
        Stack<Character> stack = new Stack(); // 숫자 순회를 하며 큰 수를 저장해둘 stack
        int totalCount = numArray.length - k;
        
        // 숫자를 1개씩 순회하기
        for(char n : numArray) {
            // 여전히 빼야할 숫자가 있고, 스택이 0보다 클 경우
            if(k > 0 && stack.size() > 0) { 
                // 스택 맨 위의 숫자가 현재 순회 중인 숫자보다 작을 때 계속 반복
                while(stack.peek() < n) {
                    stack.pop(); // 스택의 요소 빼기
                    k--; // 숫자를 빼므로 k--
                    
                    if(k == 0 || stack.empty()) // k == 0되면 더이상 while문으로 숫자를 뺄 수 없으므로 탈출
                        break;
                }
            }
            
            // testcase 대응용 : 222111, 3인 경우 답이 222111이 되므로 아래와 같이 필요한 개수만 push 하도록 추가
            if(stack.size() < totalCount)
                stack.push(n);
        }
        
        // 스택 요소를 꺼내서 답으로 반환
        StringBuilder sb = new StringBuilder();
        for(char s : stack)
            sb.append(s);
        
        return sb.toString();
    }
}