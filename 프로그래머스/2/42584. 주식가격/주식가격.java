import java.util.*;

class Solution {
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length]; // 정답 저장용 배열
        
        // price 하락 check용 stack, [0] = value, [1] = birth(offset)
        Stack<int[]> stack = new Stack<>();
        
        // prices 원소 순회
        for(int i=0;i<prices.length;i++) {
            
            // stack에 원소가 있고, stack의 가장 최근 원소가 현재 검사중인 prices의 원소보다 클 때까지 pop 반복
            while(!stack.empty() && stack.peek()[0] > prices[i]) {
                int[] tmp = stack.pop(); // 꺼내서
                answer[tmp[1]] = i - tmp[1]; // answer 업데이트
            }
            
            // 현재 prices 원소 push
            stack.push(new int[]{prices[i], i});
        }
        
        // 마지막까지 남은 요소 정리
        for(int[] element : stack) {
            answer[element[1]] = prices.length - 1 - element[1];
        }
        
        return answer;
    }
}