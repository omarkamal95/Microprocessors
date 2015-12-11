package Main;
public class Instruction {
	
	/* 1= LW(rd,rs,rt) ,2= SW(rd,rs,rt) ,3= JMP(rd,rs,rt) ,4= BEQ(rd,rs,rt) ,5= JALR(rd,rs)
	   6= RET(rd) ,7= ADD(rd,rs,rt) ,8= SUB(rd,rs,rt) , 9= ADDI(rd,rs,rt), 10= NAND(rd,rs,rt)
	   11= MUL(rd,rs,rt)  */	
	int type;
	
	/*1 = Ready to issue, 2= Ready to calculate,
	 3 = Ready to execute, 4 = executing, 5 = Ready to write, 6 = writing, 7 = Ready to commit  */
	int state;
	int pcPos;
	String rs,rt,rd;
	int executionCycles;
	int writeBackCycles;
	int resStationIndex;
	
	public Instruction(int type, String rs, String rt, String rd){
		this.type = type;
		this.state = 1;
		this.rs = rs;
		this.rt = rt;
		this.rd = rd;
	}
	
	public int getAddress(){
		if(type == 1){
			if(rs.equalsIgnoreCase("R0")){
				int res = Main.R0 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R1")){
				int res = Main.R1 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R2")){
				int res = Main.R2 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R3")){
				int res = Main.R3 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R4")){
				int res = Main.R4 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R5")){
				int res = Main.R5 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R6")){
				int res = Main.R6 + Integer.parseInt(rt);
				return res;
			}
			if(rs.equalsIgnoreCase("R7")){
				int res = Main.R7 + Integer.parseInt(rt);
				return res;
			}
		}
		else if(type == 2){
			if(rs.equalsIgnoreCase("R0")){
				int res = Main.R0 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R1")){
				int res = Main.R1 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R2")){
				int res = Main.R2 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R3")){
				int res = Main.R3 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R4")){
				int res = Main.R4 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R5")){
				int res = Main.R5 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R6")){
				int res = Main.R6 + Integer.parseInt(rd);
				return res;
			}
			if(rs.equalsIgnoreCase("R7")){
				int res = Main.R7 + Integer.parseInt(rd);
				return res;
			}
		}
		
		return 0;
	}
	
}
