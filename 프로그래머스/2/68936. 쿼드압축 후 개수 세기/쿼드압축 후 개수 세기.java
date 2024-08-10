class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        return getQuadCompressByRecursive(0, 0, arr.length, arr, answer);
    }
    
    public int[] getQuadCompressByRecursive(int r, int c, int length, int[][] arr, int[] answer) {
        
        if(length == 1) {
            answer[arr[r][c]]++;
            return answer;
        }
        
        boolean isDifferent = false;
        
        for(int i=r;i<r+length;i++) {
            for(int j=c;j<c+length;j++) {
                if(arr[r][c] != arr[i][j]) {
                    int halfLength = length/2;
                    getQuadCompressByRecursive(r, c, halfLength, arr, answer);
                    getQuadCompressByRecursive(r+halfLength, c, halfLength, arr, answer);
                    getQuadCompressByRecursive(r, c+halfLength, halfLength, arr, answer);
                    getQuadCompressByRecursive(r+halfLength, c+halfLength, halfLength, arr, answer);
                    isDifferent = true;
                    break;
                }
            }
            if(isDifferent)
                break;
        }
        
        if(!isDifferent) {
            answer[arr[r][c]]++;
        }
        
        return answer;
    }
}