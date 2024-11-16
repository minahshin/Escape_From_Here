import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * IDEA :
 * Concept :
 */

public class Main {
    static int pathCount = 0; // 파이프를 둘 수 있는 최대 개수
    static boolean isFoundPath = false; // 각 행 마다 길을 찾는지 flag

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        char[][] map = new char[row][col];

        for(int i=0;i<row;i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<row;i++) {
            makePath(map, row, col, i, 0);
            isFoundPath = false;
        }

        System.out.print(pathCount);

    }

    /**
     * DFS를 통해 파이프를 둘 수 있는 개수를 셈
     * @param map map 정보
     * @param sizeRow 행의 크기
     * @param sizeCol 열의 크기
     * @param r 현재 위치의 열 값
     * @param c 현재 위치의 행 값
     */
    private static void makePath(char[][] map, int sizeRow, int sizeCol, int r, int c) {
        // 길이 막혔거나 이미 길을 찾았다면 종료
        if(map[r][c] == 'x' || isFoundPath) {
            return;
        }

        // DFS를 통해 열의 끝에 접근했다면 파이프 수를 늘리고 종료
        if(c == sizeCol - 1) {
            pathCount++;
            map[r][c] = 'x';
            isFoundPath = true;
            return;
        }

        // DFS 시작
        map[r][c] = 'x';

        if(r > 0)
            makePath(map, sizeRow, sizeCol, r-1, c+1);

        makePath(map, sizeRow, sizeCol, r, c+1);

        if(r < sizeRow - 1)
            makePath(map, sizeRow, sizeCol, r+1, c+1);
    }

}
