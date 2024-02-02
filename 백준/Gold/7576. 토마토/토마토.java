import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Tomato {
		int r;
		int c;

		public Tomato(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int cSize = Integer.parseInt(st.nextToken());
		int rSize = Integer.parseInt(st.nextToken());

		boolean[][] tomatoBox = new boolean[rSize + 2][cSize + 2];

		for (int i = 0; i < tomatoBox.length; i++) {
			Arrays.fill(tomatoBox[i], true);
		}

		List<Tomato> ripenTomatoes = new ArrayList<Tomato>();

		for (int i = 1; i <= rSize; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= cSize; j++) {
				int tomatoStatus = Integer.parseInt(st.nextToken());
				if (tomatoStatus == 0)
					tomatoBox[i][j] = false;
				else if (tomatoStatus == 1)
					ripenTomatoes.add(new Tomato(i, j));
			}
		}

		System.out.print(getDays(tomatoBox, ripenTomatoes));

	}

	private static int getDays(boolean[][] tomatoBox, List<Tomato> firstRipen) {
		int answer = 0;

		Queue<Tomato> tomato = new LinkedList<Tomato>();

		for (int i = 0; i < firstRipen.size(); i++) {
			tomato.offer(firstRipen.get(i));
		}

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, -1, 0, 1 };

		while (!tomato.isEmpty()) {

			int size = tomato.size();
			
			for (int s = 0; s < size; s++) {
				Tomato current = tomato.poll();

				for (int i = 0; i < 4; i++) {
					if (!tomatoBox[current.r + dr[i]][current.c + dc[i]]) {
						tomato.offer(new Tomato(current.r + dr[i], current.c + dc[i]));
						tomatoBox[current.r + dr[i]][current.c + dc[i]] = true;
					}
				}
			}
			answer++;

		}

		for (int i = 1; i < tomatoBox.length - 1; i++) {
			for (int j = 1; j < tomatoBox[0].length - 1; j++) {
				if (!tomatoBox[i][j])
					return -1;
			}
		}

		return answer - 1; // 마지막에 1이 더해지므로
	}

}
