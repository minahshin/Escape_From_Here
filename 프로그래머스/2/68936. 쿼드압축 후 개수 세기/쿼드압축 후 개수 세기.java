// IDEA : 재귀 + 완전 탐색을 통해 모든 경우의 수 찾기
// 1. 시작점 r, c, 길이, arr, 정답 배열을 가지고 정답 배열을 리턴하는 함수 작성
// 2. 시작점을 기준으로 탐색하면서 길이를 각각 1/2로 나눈 1개의 구역에서 모든 수가 같은 경우에는 압축값++를 하고 반복문을 나감
// 3. 같지 않은 경우는 구역 내부에서 길이를 각각 1/2로 나누어 4개의 구역을 2번과 같이 탐색
// 4. 길이가 1이 되면, 더 이상 압축이 불가하므로 값의 개수를 더하고 함수를 return함

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
