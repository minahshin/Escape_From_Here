/*
 * Concept : BruteForce로 조건에 맞는 최대 방문 수 찾기
 * IDEA : 순열 (nPn)으로 방문하며 피로도 체크
 */
class Solution {
    public int solution(int k, int[][] dungeons) {
        int[] answer = new int[1]; // 총 방문 수 체크용 참조 자료형
        
        // 순열을 이용한 탐색
        calcByPerm(k, dungeons, 0, new boolean[dungeons.length], answer);
        
        return answer[0];
    }
    
    // 순열을 이용한 던전 순서 체크
    private void calcByPerm(int k, int[][] dungeons, int depth, boolean[] visited, int[] answer) {
        
        // 이미 전체 던전을 돌 수 있는 경우에는 빠져나오기
        if(answer[0] == dungeons.length)
            return;
        
        // 순열 체크
        for(int i=0;i<dungeons.length;i++) {
            // 던전을 방문한 적 없고, 해당 던전이 요구하는 최소 필요 피로도보다 현재 피로도가 높은 경우에만 탐색
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true; // 방문 체크
                answer[0] = Math.max(depth + 1, answer[0]); // depth가 0부터 시작하므로 1을 더해준다
                
                if(answer[0] == dungeons.length) // 만약 모든 던전을 돌 수 있다면 탐색 멈추기
                    break;
                
                calcByPerm(k - dungeons[i][1], dungeons, depth+1, visited, answer); // 다음 순서를 찾아 떠나기
                visited[i] = false; // 방문 체크 해제
            }
        }
    }
}