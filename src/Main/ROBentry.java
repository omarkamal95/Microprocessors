package Main;
public class ROBentry {


	String dest; //R-
	String value;
	boolean ready;
	
	/* 1= LW(rd,rs,rt) ,2= SW(rd,rs,rt) ,3= JMP(rd,rs,rt) ,4= BEQ(rd,rs,rt) ,5= JALR(rd,rs)
	   6= RET(rd) ,7= ADD(rd,rs,rt) ,8= SUB(rd,rs,rt) , 9= ADDI(rd,rs,rt), 10= NAND(rd,rs,rt)
	   11= MUL(rd,rs,rt)  */
	int instruction;
	
	public ROBentry(){
		dest = null;
		value = null;
		ready = false;
		instruction = 0;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public int getInstruction() {
		return instruction;
	}

	public void setInstruction(int instruction) {
		this.instruction = instruction;
	}
	
	
}
