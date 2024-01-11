import java.util.*;
import java.util.stream.IntStream;

/*
 * Concept : A -> B로 선물을 건넨 현황과 A의 선물 지수(준 선물 - 받은 선물)을 통해 다음 달에 받을 예상 선물을 계산
 * 선물을 받는 조건들
 * 1) 선물 주고 받은 기록 O) "선물을 더 많이 준" 사람이 다음 달 선물 획득
 * 2) 선물 기록 X || 선물을 준 횟수 동일) "선물 지수가 더 높은" 사람이 다음 달 선물 획득
 */

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0; // 미사용 변수, 제거 요망
        
        // give a gift from A to B : [A][B]
        int[][] gifted = new int[friends.length][friends.length]; // A -> B 선물 제공 현황, 표현은 [A][B]로 간주
        Map<String, Integer> giftScore = new HashMap<>(); // 선물 지수 계산
        Map<String, Integer> pointer = new HashMap<>(); // O(1)로 위치를 빠르게 찾기 위한 Map
        int[] expected = new int[friends.length]; // 각 친구들의 예상되는 선물 개수, 후위 연산자 사용을 위해 일반 array 사용

        // Initialization
        for(int i=0;i<friends.length;i++) {
            giftScore.put(friends[i], 0);
            pointer.put(friends[i], i);
        }

        // Through the gift records, update gift status & calculate gift score
        for(int i=0;i<gifts.length;i++) {
            // Separate giver and receiver
            StringTokenizer st = new StringTokenizer(gifts[i]);
            String from = st.nextToken();
            String to = st.nextToken();

            // update status
            gifted[pointer.get(from)][pointer.get(to)]++;

            // calculate score
            giftScore.compute(from, (k, v) -> ++v);
            giftScore.compute(to, (k, v) -> --v);
        }

        // Between giver[i] and receiver[j], get who will get present via condition
        for(int i=0;i<friends.length;i++) {
            // Don't need to compare same person
            for(int j=i+1;j<friends.length;j++) {

                // 1st condition) who gave more gifts to each other?
                if(gifted[i][j] > gifted[j][i])
                    expected[i]++;
                else if(gifted[i][j] < gifted[j][i])
                    expected[j]++;

                // 2nd condition) who has higher gift score?
                else {
                    if(giftScore.get(friends[i]) > giftScore.get(friends[j]))
                        expected[i]++;
                    else if(giftScore.get(friends[i]) < giftScore.get(friends[j]))
                        expected[j]++;
                }
            }
        }

        // Get max value from expected gifts
        return IntStream.of(expected).max().getAsInt();
    }
}
