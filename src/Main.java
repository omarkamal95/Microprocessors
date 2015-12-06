import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Queue;


public class Main {
	MainMemory memory;
	int R1,R2,R3,R4,R5,R6,R7;
	final int R0 = 0;
	DCache dcaches [];
	ICache icaches [];
	String [] instructionBuffer;
	int instructionBufferCounter;

	public void load(int address, String regNo){
		for(int i =0; i < dcaches.length;i++){
			String data = dcaches[i].find(address);
			if(data != null){
				putInReg(data,regNo);
				break;
			}
			/// dont know how to put blocks in cache if block size different
		}
	}
	
	public void fetch(){
		
	}
	
	
	public void putInReg(String data, String regNo){
		int intData = Integer.parseInt(data);
		if(regNo.equalsIgnoreCase("R1")){
			R1 = intData;
		}
		if(regNo.equalsIgnoreCase("R2")){
			R2 = intData;
		}
		if(regNo.equalsIgnoreCase("R3")){
			R3 = intData;
		}
		if(regNo.equalsIgnoreCase("R4")){
			R4 = intData;
		}
		if(regNo.equalsIgnoreCase("R5")){
			R5 = intData;
		}
		if(regNo.equalsIgnoreCase("R6")){
			R6 = intData;
		}
		if(regNo.equalsIgnoreCase("R7")){
			R7 = intData;
		}
	}

	public static int binaryToInteger(String binary) {
		char[] numbers = binary.toCharArray();
		int result = 0;
		for(int i=numbers.length - 1; i>=0; i--)
			if(numbers[i]=='1')
				result += Math.pow(2, (numbers.length-i - 1));
		return result;
	}
	
	public static String integerToBinary(int x){
		String res = Integer.toBinaryString(x);
		while(res.length()<16){
			res = "0"+res;
		}
		return res;
	}
	
	public void runCycle(){
		if(instructionBufferCounter >0){
			//issue
		}
		if(instructionBufferCounter < instructionBuffer.length){
			fetch();
		}
		
	}
	
	public static void main (String [] args){
		 java.util.Date date= new java.util.Date();
		 System.out.println(new Timestamp(date.getTime()));
		 java.util.Date date1= new java.util.Date();
		 System.out.println(new Timestamp(date1.getTime()));
	}

}
