package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Chapter3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> disks = new ArrayList<Integer>();
		/*disks.add(5);
		disks.add(4);
		*/
		disks.add(5);
		disks.add(4);
		disks.add(3);
		disks.add(2);
		disks.add(1);
		Chapter3 c3 = new Chapter3();
		c3.addDisks(disks, c3.towerA);
		c3.move(c3.towerA,c3.towerC,c3.towerB,"",5);
		//c3.move(c3.towerB,c3.towerC,c3.towerA);
	}
	
	public Stack<Integer> towerA;
	public Stack<Integer> towerB;
	public Stack<Integer> towerC;
	public int numDisks;
	public int maxDisk;
	public int cont;
	
	
	public Chapter3(){
		towerA = new Stack<>();
		towerB = new Stack<>();
		towerC = new Stack<>();
		numDisks = 0;
		maxDisk = 0;
		cont = 0;
	}
	
	public void move2(Stack<Integer> start, Stack<Integer> finish, Stack<Integer> pivot, String indent, int mover) {
		//System.out.println(mover);
		
		if(mover == 1){
			finish.push(start.pop());
			printTowers(indent);
		}else{
			if(mover%2==0){
				System.out.println("M1");
				move2(start,pivot,finish,indent+">",mover-1);
				System.out.println("M2");
				move2(pivot,finish,start,indent+">",mover-1);
			}else{
				System.out.println("M3");
				move2(start,finish,pivot,indent+">",mover-1);
			}
		}
	}
	
	/*
	In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
	different sizes which can slide onto any tower. The puzzle starts with disks sorted
	in ascending order of size from top to bottom (i.e., each disk sits on top of an
	even larger one). You have the following constraints:
	(1) Only one disk can be moved at a time.
	(2) A disk is slid off the top of one tower onto the next tower.
	(3) A disk can only be placed on top of a larger disk.
	Write a program to move the disks from the first tower to the last using stacks.
	*/
	public void move(Stack<Integer> start, Stack<Integer> finish, Stack<Integer> pivot, String indent,int num) {
		
		Integer prevTop = 0;
		if(!start.empty())
		prevTop = start.peek();
		while(!start.isEmpty() && cont++ < 100){
			System.out.println(num);
			printTowers(indent);
			if(start.size()%2 == 1){
				if(finish.isEmpty() || (finish.peek() > start.peek())){
					if(prevTop == start.peek())
						num--;
					finish.push(start.pop());
					if(!start.empty())
						prevTop = start.peek();
				}else{
					Stack<Integer> nf= new Stack<Integer>();
					nf.addAll(finish);
					finish.removeAllElements();
					move(nf,pivot,start,indent+">",nf.size());
					finish.addAll(nf);
				}
			}else{
				if(pivot.isEmpty() || (pivot.peek() > start.peek())){
					if(prevTop == start.peek())
						num--;
					pivot.push(start.pop());
					if(!start.empty())
						prevTop = start.peek();
				}else{
					Stack<Integer> nf= new Stack<Integer>();
					nf.addAll(pivot);
					pivot.removeAllElements();
					move(nf,finish,start,indent+">",nf.size());
					pivot.addAll(nf);
				}
			}
		}
	}
	
	public void solve2(Stack<Integer> start, Stack<Integer> finish, Stack<Integer> pivot, String indent) {
		System.out.println(cont++);
		
		while(!start.isEmpty() && cont < 50){
			if(start.size() == 1){
				if(finish.isEmpty() || (finish.peek() > start.peek())){
					finish.push(start.pop());
					printTowers(indent);
				}
			}else{
				if(start.size() == 2){
					if(pivot.isEmpty() || (pivot.peek() > start.peek())){
						pivot.push(start.pop());
						printTowers(indent);
						finish.push(start.pop());
						printTowers(indent);
						finish.push(pivot.pop());
						printTowers(indent);
					}
				}else{
					if(start.size() == 3){
						finish.push(start.pop());
						printTowers(indent);
						pivot.push(start.pop());
						printTowers(indent);
						pivot.push(finish.pop());
						printTowers(indent);
						finish.push(start.pop());
						printTowers(indent);
						start.push(pivot.pop());
						printTowers(indent);
						finish.push(pivot.pop());
						printTowers(indent);
						finish.push(start.pop());
						printTowers(indent);
					}
				}
			}
		}
	}
	
	/*public void move2(Stack<Integer> start, Stack<Integer> finish, Stack<Integer> pivot) {
		System.out.println(cont++);
		if(!start.isEmpty() && start.size() % 2 == 0){
			if(pivot.isEmpty() || pivot.peek() < start.peek()){
				pivot.push(start.pop());
				if(finish.isEmpty() || (finish.peek() > start.peek()){
					
				}
			}
		}
	}*/
	
	public void addDisks(List<Integer> disks, Stack<Integer> tower) {
		for (Integer disk : disks) {
			tower.push(disk);
			numDisks++;
			if(disk > maxDisk){
				maxDisk = disk;
			}
		}
	}
	
	public String[] getTowerDrawArray(Stack<Integer> tower, int numElements, int maxSize) {
		int numRows = 0;
		String[] bottomRows = new String[numElements];
		String spacing = new String(new char[numElements]).replace("\0", " ");
		String halfDisk = spacing + "|" + spacing;
		for(int i=0;i<numElements;i++){
			bottomRows[i] = halfDisk;
		}
		for (Integer integer : tower) {
			int offset = maxSize - integer;
			spacing = new String(new char[offset]).replace("\0", " ");
			halfDisk = new String(new char[integer]).replace("\0", "_");
			bottomRows[numElements-numRows-1] = spacing + halfDisk + integer + halfDisk + spacing;
			numRows++;
		}
		return bottomRows;
	}
	
	public void printTowers(String indent){
		String[] tA = getTowerDrawArray(towerA, numDisks, maxDisk);
		String[] tB = getTowerDrawArray(towerB, numDisks, maxDisk);
		String[] tC = getTowerDrawArray(towerC, numDisks, maxDisk);
		
		for(int i =0;i<numDisks;i++){
			System.out.println(indent + tA[i] + " " + tB[i] + " " + tC[i]);
		}
		System.out.println("================================");
	}
}
