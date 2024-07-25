class Solution {
    
    public int solution(int num1, int num2) {
        int answer = 0; // 답을 담아서 리턴하는 변수
        // 1. num1에서 num2를 빼고, answer에 할당한다
        answer = num1 - num2;
        return answer; // 2. 1에서 계산된 값(answer)을 반환해준다
    }
}