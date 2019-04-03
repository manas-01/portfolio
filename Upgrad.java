import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer; 
import java.util.ArrayList;
public class Upgrad{
	public static void main (String[] args){
	    FastReader sc=new FastReader();
	    int n=sc.nextInt();
	    PriorityQueue q=new PriorityQueue();
	    for(int k=0;k<n;k++){
	        String s=sc.next();
	        if(s.equals("ENTER")){
	            String name=sc.next();
	            double marks=sc.nextDouble();
	            int number=sc.nextInt();
	            q.insert(new Box(name,marks,number));
	        }else
	            q.remove();
	    }
	    n=q.size();
	    if(n==0)
	        System.out.println("EMPTY");
	    else
	        for(int i=0;i<n;i++)
	            System.out.println((q.remove()).name);
	}
}
class Box{
    String name;
    double marks;
    int number;
    public Box(String name,double marks,int number){
        this.name=name;
        this.marks=marks;
        this.number=number;
    }
}
// PRIORITY QUEUE CLASS
class PriorityQueue{
    private ArrayList<Box> heap;
    public PriorityQueue(){
        heap=new ArrayList<Box>();
    }
    public int size(){ return heap.size(); }
    public void insert(Box b){
        heap.add(b);
        int childIndex=heap.size()-1;
        int parentIndex=(childIndex-1)/2;
        while(childIndex>0){
            Box x=heap.get(childIndex);
            Box y=heap.get(parentIndex);
            if(x.marks>y.marks){
                Box temp=x;
                heap.set(childIndex,y);
                heap.set(parentIndex,temp);
            }else if(x.marks==y.marks){
                if((x.name).compareTo(y.name)<0){
                    Box temp=x;
                    heap.set(childIndex,y);
                    heap.set(parentIndex,temp);
                }else if((x.name).compareTo(y.name)==0){
                    if(x.number<y.number){
                        Box temp=x;
                        heap.set(childIndex,y);
                        heap.set(parentIndex,temp);
                    }else{
                        return;
                    }
                }else{
                    return;
                }
            }else{
                return;
            }
            childIndex=parentIndex;
            parentIndex=(childIndex-1)/2;
        }
    }
    public Box remove(){
        Box b=heap.get(0);
        int n=heap.size();
        heap.set(0,heap.get(n-1));
        heap.remove(n-1);
        n=heap.size();
        int index=0;
        int minIndex=index;
        int lci=1;
        int rci=2;
        while(lci<n){
            int c=0;
            if(heap.get(lci).marks>heap.get(minIndex).marks){
                minIndex=lci;
                c=1;
            }
            if(rci<n && heap.get(rci).marks>heap.get(minIndex).marks){
                minIndex=rci;
                c=1;
            }
            if(c==0 && heap.get(lci).marks==heap.get(minIndex).marks && (rci>=n || heap.get(rci).marks==heap.get(minIndex).marks)){
                c=0;
                if((heap.get(lci).name).compareTo(heap.get(minIndex).name)<0){
                    minIndex=lci;
                    c=1;
                }
                if(rci<n && (heap.get(rci).name).compareTo(heap.get(minIndex).name)<0){
                    minIndex=rci;
                    c=1;
                }
                if(c==0 && (heap.get(lci).name).equals(heap.get(minIndex).name) && (rci>=n || (heap.get(rci).name).equals(heap.get(minIndex).name))){
                    if(heap.get(lci).number<heap.get(minIndex).number)
                        minIndex=lci;
                    if(rci<n && heap.get(rci).number<heap.get(minIndex).number)
                        minIndex=rci;
                }
            }
            if(minIndex==index)
                break;
            Box temp=heap.get(index);
            heap.set(index,heap.get(minIndex));
            heap.set(minIndex,temp);
            index=minIndex;
            lci=(2*index)+1;
            rci=(2*index)+2;
        }
        return b;
    }
}

// FAST READER CLASS FOR TAKING INPUT FAST
class FastReader { 
    BufferedReader br; 
    StringTokenizer st; 
  
    public FastReader() 
    { 
        br = new BufferedReader(new
        InputStreamReader(System.in)); 
    } 
  
    String next() 
    { 
        while (st == null || !st.hasMoreElements()) 
        { 
            try
            { 
                st = new StringTokenizer(br.readLine()); 
            } 
            catch (IOException  e) 
            { 
                e.printStackTrace(); 
            } 
        } 
        return st.nextToken(); 
    } 
  
    int nextInt() 
    { 
        return Integer.parseInt(next()); 
    } 
  
    long nextLong() 
    { 
        return Long.parseLong(next()); 
    } 
  
    double nextDouble() 
    { 
        return Double.parseDouble(next()); 
    } 
  
    String nextLine() 
    { 
        String str = ""; 
        try
        { 
            str = br.readLine(); 
        } 
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
        return str; 
    } 
} 
