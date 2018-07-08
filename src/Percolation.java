import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;


public class Percolation  {
    
   private static int top = 0;
   private int bottom;
   private int size;
   private boolean Grid[][];
   private int openSites = 0;
   
   
   private WeightedQuickUnionUF QU;
   
   public Percolation(int n)  { // create n-by-n grid, with all sites blocked
        this.size = n;
        this.bottom = n*size + 1;
        this.Grid = new boolean[n][n];
        this.QU   = new WeightedQuickUnionUF(n*size + 2);
        int end;
        
        // connect all top guys with top node in data structure
        for(int i=1; i < size + 1; i++){
            end = size*size+1;
            //System.out.println(QU.count());
            QU.union(0 , i);
            //QU.union(end,end -i);
        }
        //System.out.println(QU.count());
        //System.out.println(this.bottom);
       
   }
   
   public    void open(int row, int col) {  // open site (row, col) if it is not open already
       row--; //using a system with 0 as start
       col--;
       int spot = 0;
       int me = row*size + col + 1;
       
       Grid[row][col] = true;
       // Quick find data structure here if others around it are open, connect to them
       if(row !=0){
           if(Grid[row-1][col] == true){//top
               spot = (row-1)*size+col +1; //2 UnFind
               QU.union(me,spot);
           }
       }
       if(row != size-1){
           if(Grid[row+1][col] == true){//bottom
               spot =(row+1)*size+col + 1; //8 UnFind
               QU.union(me,spot);
           }
       }
       if(col != size-1){
           if(Grid[row][col+1] == true){//right
               spot =(row)*size+(col+1) + 1; //6 UnFind
               QU.union(me,spot);
           }
       }
       if(col != 0){
           if(Grid[row][col-1] == true){//left
               spot =(row)*size+(col-1) +1; //4 UnFind
               QU.union(me,spot);
           }
       }
       
       openSites += 1;
   }  
   public boolean isOpen(int row, int col) {
       row--;
       col--;
       
       if(Grid[row][col] == true){
          
           return true;
       }else{
           return false;
       }
        
       } // is site (row, col) open?
   public boolean isFull(int row, int col)  { // is site (row, col) full?
       row--;
       col--;
       
       int me = row*size + col + 1;
       
       if( 0 <= row && row < size && 0 <= col && col < size && Grid[row][col] == true){
           return QU.connected(me, top);
           
       }else{
           //throw new IndexOutOfBoundsException();
           return false;
       }
       
   
   } 
   public     int numberOfOpenSites()  {
       
       return this.openSites;
       
       }    // number of open sites
   public boolean percolates()   {// does the system percolate?
       boolean answer = false;
       // for loop to check if bottom values are open
       for(int i=size; i >= 0; i--){
           //System.out.println(i);
           if(this.isFull(size-1,i)){
               answer = true;
           }
       }
       System.out.println(answer);
       return answer;
       
       }        

   public static void main(String[] args)  {  // test client (optional)
       
       int row = 0;
       int col = 0;
       
       row++;
       col++;
       Percolation perc = new Percolation(3);
       //System.out.println(perc.isFull(row, col));
       System.out.println(perc.percolates());
       perc.open(row,col);
       
       perc.open(row,col+1);
       perc.open(row,col+2);
       System.out.println(perc.isFull(row, col+2));
       
       System.out.println(perc.percolates());
         
       //System.out.println(perc.isOpen(row, col));
       //System.out.println(perc.isFull(row, col));
       
       
   
   } 
}

