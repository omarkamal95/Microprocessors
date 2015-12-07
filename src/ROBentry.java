
public class ROBentry {

	// 0: branch (no destination result) 1: store(mem address destination) 
	// 2: Regs opr (ALU or load-has reg destination)
	int type; //might be redundant 
	String dest; //R-
	String value;
	boolean ready;
	
	public ROBentry(){
		
	}
}
