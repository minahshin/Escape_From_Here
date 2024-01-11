import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // give a gift from A to B : [A][B]
        int[][] gifted = new int[friends.length][friends.length];
        Map<String, Integer> giftScore = new HashMap<>();
        Map<String, Integer> pointer = new HashMap<>();
        int[] expected = new int[friends.length];
        
        for(int i=0;i<friends.length;i++) {
            giftScore.put(friends[i], 0);
            pointer.put(friends[i], i);
        }
        
        for(int i=0;i<gifts.length;i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            String from = st.nextToken();
            String to = st.nextToken();
            
            gifted[pointer.get(from)][pointer.get(to)]++;
            
            giftScore.compute(from, (k, v) -> ++v);
            giftScore.compute(to, (k, v) -> --v);
        }
        
        for(int i=0;i<friends.length;i++) {
            for(int j=i+1;j<friends.length;j++) {
                if(gifted[i][j] > gifted[j][i])
                    expected[i]++;
                else if(gifted[i][j] < gifted[j][i])
                    expected[j]++;
                else {
                    if(giftScore.get(friends[i]) > giftScore.get(friends[j]))
                        expected[i]++;
                    else if(giftScore.get(friends[i]) < giftScore.get(friends[j]))
                        expected[j]++;
                }
            }
        }
        
        return IntStream.of(expected).max().getAsInt();
    }
}