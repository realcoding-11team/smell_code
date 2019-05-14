package acmicpc;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	static int INF = 1000000;
	/*
	 * 1. 모음 끝
	 * 2. 자음 하나끝
	 * 3. 자음 두개끝
	 */
	static int[][] length1;
	static int[][] length2;
	static int[][] length3;
	static int[] di = {1,0,-1,0};
	static int[] dj = {0,1,0,-1};
	
	
	static String solve(int n, int m,int[][] map) {
		length1 = new int[n][m];
		length2 = new int[n][m];
		length3 = new int[n][m];
		
		//Substitute Algorithm
		for (int i=0;i<n;i++) {
			Arrays.fill(length1,INF);
			Arrays.fill(length2,INF);
			Arrays.fill(length3,INF);
		}
		if(!isJaeum(map[0][0])) return "BAD";
		if(isJaeum(map[1][0]) && isJaeum(map[0][1])) return "BAD";
		dfs(0,0,map);
		int ret = length1[n-1][m-1]<length2[n-1][m-1] ? length1[n-1][m-1]:length2[n-1][m-1];
		if (ret == INF) return "BAD";
		return Integer.toString(ret);
	}
	static void dfs(int i, int j, int[][] map) {
		if(!isJaeum(map[1][0])) {
			length1[1][0] = 1;
			dfs(1,0,1,1,map);
			dfs(1,0,2,0,map);
		}
		if(!isJaeum(map[0][1])) {
			length1[0][1] = 1;
			dfs(0,1,1,1,map);
			dfs(0,1,0,2,map);
		}
	}
	static void dfs(int pi, int pj, int i, int j, int[][] map) {
		boolean isUpdate = false;
		if(isJaeum(map[i][j])) {
			if(length2[i][j] > length1[pi][pj]) {
				length2[i][j] = length1[pi][pj];
				isUpdate = true;
			}
			if(length3[i][j] > length2[pi][pj]+1) {
				length3[i][j] = length2[pi][pj]+1;
				isUpdate = true;
			}
		}
		else {
			if(length1[i][j] > length2[pi][pj]+1) {
				length1[i][j] = length2[pi][pj]+1;
				isUpdate = true;
			}
			if(length1[i][j] > length3[pi][pj]) {
				length1[i][j] = length3[pi][pj];
				isUpdate = true;
			}
		}
		int nextI;
		int nextJ;
		
		if (isUpdate) {
			for(int k=0;k<4;k++) {
				//Remove Assignments to Parameters
				nextI = i+di[k];
				nextJ = j+dj[k];
				
				//Extract Method
				if(isRange(nextI, nextJ, map))
					dfs(i,j,nextI,nextJ,map);
			}
		}
	}
	static boolean isJaeum(int ch) {
		return (ch < 14);
	}
	static boolean isRange(int i, int j, int[][] map) {
		return i>-1 && j>-1 && i<map.length && j<map[0].length;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		int m = scan.nextInt();
		int[][] map = new int[n][m];
		for (int i=0;i<n;i++) 
			for (int j=0;j<m;j++) 
				map[i][j] = scan.nextInt();
		
		//Inline Temp
		System.out.println(solve(n, m, map));
		
		scan.close();
	}
}
