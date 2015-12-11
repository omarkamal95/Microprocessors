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
		
	}
}
