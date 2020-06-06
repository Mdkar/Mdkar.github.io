import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Java2048 {
	
	private ArrayList<ArrayList<Integer>> board;
	private ArrayList<Integer> set;
	private Random randy = new Random();
	
	public Java2048(){
		board = new ArrayList<ArrayList<Integer>>();
		set = new ArrayList<Integer>();
		for(int r = 0; r < 4; r++){
			board.add(new ArrayList<Integer>());
			for(int c = 0; c < 4; c++)
				board.get(r).add(0);
		}
		//board.get(randy.nextInt(4)).set(randy.nextInt(4),(1+randy.nextInt(2))*2);
		nextTile();
		nextTile();
		//System.out.println(board);
	}
	
	public void nextTile() {
		int r = -1;
		int c = -1;
		
		do {
			r = randy.nextInt(4);
			c = randy.nextInt(4);
		} while(board.get(r).get(c)!=0);
		board.get(r).set(c,(1+randy.nextInt(2))*2);
	}

	public int down(){
		int val = 0;
		for(int c = 0; c < 4; c++){
			set.clear();
			for(int r = 3; r >= 0; r--){
				set.add(board.get(r).get(c));
				//System.out.println("Copying to set");
				//System.out.print(set);
			}
			val+=shiftSet();
			
			for(int r = 3; r >= 0; r--){
				board.get(r).set(c,set.get(3-r));
				//System.out.println("Copying to board");
				//System.out.println(board.get(r).get(c));
			}
		}
		return val;
	}
	
	public int up(){
		int val = 0;
		for(int c = 0; c < 4; c++){
			set.clear();
			for(int r = 0; r < 4; r++)
				set.add(board.get(r).get(c));
			val+=shiftSet();
			for(int r = 0; r < 4; r++)
				board.get(r).set(c,set.get(r));
		}
		return val;
	}
	
	public int left(){
		int val = 0;
		for(int r = 0; r < 4; r++){
			set.clear();
			for(int c = 0; c < 4; c++)
				set.add(board.get(r).get(c));
			//System.out.println(set);
			val+=shiftSet();
			for(int c = 0; c < 4; c++){
				board.get(r).set(c,set.get(c));
				//System.out.println("Copying to board");
				//System.out.println(board.get(r).get(c));	
			}
		}
		//System.out.println(board);
		return val;
	}
	
	public int right(){
		int val = 0;
		for(int r = 0; r < 4; r++){
			set.clear();
			for(int c = 3; c >= 0; c--)
				set.add(board.get(r).get(c));
			val+=shiftSet();
			for(int c = 3; c >= 0; c--)
				board.get(r).set(c,set.get(3-c));
		}
		return val;
	}
	
	private int shiftSet(){
		int val = 0;
		int prev = 0;
		int i;
		if(!isEmptySet())
			while(set.indexOf(0) != -1){
				set.remove(set.indexOf(0));
				//System.out.println(set + " removed 0\n");
			}
		for(i = 0; i < set.size() && !isEmptySet(); i++){
//			while(set.get(0)==0){
//				set.remove(i);
//				System.out.println(set + " removed 0\n");
//			}
			if(set.get(i)==prev){
				set.remove(i);
				i--;
				set.set(i, prev*2);
				val+=prev*2;
				prev = 0;
				//System.out.println(set + " did shift");
			}
			else
				prev = set.get(i);
			//System.out.println(prev);
		}
		while(set.size() < 4){
			set.add(0);
		}
		//System.out.println(set + " made size 4");
		return val;
	}
	
	public boolean isEmptySet(){
		int sum = 0;
		for(int a: set)
			sum+=a;
		return sum == 0;
	}
	
	public boolean isMovePossible(){
		boolean space = false;
		for(int r = 0; r < 4; r++)
			for(int c = 0; c < 4; c++){
				if(board.get(r).get(c)==0)
					space = true;
			}
		if(space)
			return true;
		for(int r = 0; r < 4; r++)
			for(int c = 0; c < 3; c++)
				if(board.get(c).get(r) == board.get(c+1).get(r) || board.get(r).get(c) == board.get(r).get(c+1))
					return true;
		return false;
	}
	public boolean isSignificant(String s){
		ArrayList<ArrayList<Integer>> board1 = new ArrayList<ArrayList<Integer>>();
		for(int r = 0; r < 4; r++){
			board1.add(new ArrayList<Integer>());
			for(int c = 0; c < 4; c++)
				board1.get(r).add(board.get(r).get(c));
		}
		if(s.equals("left"))
			left();
		if(s.equals("right"))
			right();
		if(s.equals("up"))
			up();
		if(s.equals("down"))
			down();
//		for(int r = 0; r < 4; r++)
//			for(int c = 0; c < 4; c++)
//				product*=board.get(r).get(c);
		if(board.equals(board1)){
			board = board1;
			return false;
		}
		else return true;
			
	}

	public ArrayList<ArrayList<Integer>> getBoard() {
		return board;
	}
}
