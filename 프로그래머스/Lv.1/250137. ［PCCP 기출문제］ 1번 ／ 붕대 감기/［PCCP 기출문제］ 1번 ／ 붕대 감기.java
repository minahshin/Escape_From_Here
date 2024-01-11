class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        
        int lastAttacked = 0;
        
        for(int i=0;i<attacks.length;i++) {
            int successedTime = attacks[i][0] - lastAttacked - 1;
            
            answer += successedTime * bandage[1] + (successedTime / bandage[0]) * bandage[2];
            
            if(answer > health)
                answer = health;
            
            if(answer <= attacks[i][1])
                return -1;
            
            answer -= attacks[i][1];
            lastAttacked = attacks[i][0];
        }
        
        return answer;
    }
}