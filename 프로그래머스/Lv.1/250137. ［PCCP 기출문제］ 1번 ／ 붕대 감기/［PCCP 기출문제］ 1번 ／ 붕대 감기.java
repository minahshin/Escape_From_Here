/*
 * Concept : 공격을 당하기 전까지 연속 회복이 가능한 회복 스킬을 사용하며 존버할 때, 게임이 끝날 때 플레이어의 남은 체력은?
 * - 체력 회복량 산출
 * 1) 연속 회복 시간을 모두 채울 경우) (회복 시간 * 회복량) + 보너스 회복
 * 2) 중간에 캔슬될 경우) 실제 회복한 회복 시간 * 회복량
 * - Cautions
 * 1) 최대 체력을 넘어 회복은 불가능
 * 2) 체력이 0 이하면 바로 죽고 return -1
 */

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health; // current HP
        
        int lastAttacked = 0; // pointer of last attacked time

        // Calculate HP through attack arrays
        for(int i=0;i<attacks.length;i++) {
            int successedTime = attacks[i][0] - lastAttacked - 1; // continuously healed time
            
            answer += successedTime * bandage[1] + (successedTime / bandage[0]) * bandage[2]; // calculate current HP ignored max HP

            // check that current HP is higher than max HP
            if(answer > health)
                answer = health;

            // check current HP is lower than attack damage -> GAME OUT(return -1)
            if(answer <= attacks[i][1])
                return -1;

            answer -= attacks[i][1]; // minus damage to calc current HP
            lastAttacked = attacks[i][0]; // update attacked time to continuing heal
        }
        
        return answer;
    }
}
