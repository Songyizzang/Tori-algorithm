import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569 {

   private static int N;
   private static int H;
   private static int M;
   private static int[][][] map;
   
   private static int[] dr = {-1,1,0,0,0,0};
   private static int[] dc = {0,0,-1,1,0,0};
   private static int[] dh = {0,0,0,0,-1,1};

   private static class Node {
      int h;
      int r;
      int c;
      public Node(int h, int r, int c) {
         this.h = h;
         this.r = r;
         this.c = c;
      }
      
   }
   public static void main(String[] args) throws Exception {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
   
      M = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      
      map = new int[H][N][M];
      Queue<Node> que = new LinkedList<>();
      
      boolean isAllZero = true;
      for (int h = 0; h < H; h++) {
         for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
               map[h][i][j] = Integer.parseInt(st.nextToken());
               if(map[h][i][j] == 1) {
                  que.add(new Node(h,i,j));
                  isAllZero = false;
               }
            }
         }   
      }
      
      if(isAllZero) {
         System.out.println(0);
         return;
      }
      
      
      int day = 0;
      while(!que.isEmpty()) {
         int n = que.size();
         for (int i = 0; i < n; i++) {
            Node cur = que.poll(); // 익은 토마토
            int r = cur.r;
            int c = cur.c;
            int h = cur.h;
            for (int d= 0;  d < 6; d++) {
               int nr = cur.r - dr[d];
               int nc = cur.c - dc[d];
               int nh = cur.h - dh[d];
               
               if(nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H) {
                  if(map[nh][nr][nc] == 0) { // 안익은 토마토도 이제 익게 됨
                     que.add(new Node(nh, nr,nc));
                     map[nh][nr][nc] = 1;
                  }
               }

            }
         }
         
         if(!que.isEmpty()) {
            day++;   
         }
      }

      
      
      boolean imPossible = false;
      outer : for (int h = 0; h < H; h++) {
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
               if(map[h][i][j] == 0) {
                  imPossible = true;
                  break outer;
               }
            }
         }
      }
      
      if(imPossible) {
         System.out.println(-1);
      } else {
         System.out.println(day);
      }
      

   }

}