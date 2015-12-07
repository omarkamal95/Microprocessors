package Main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Queue;


public class Main {
	MainMemory memory;
	static int R1,R2,R3,R4,R5,R6,R7;
	final int R0 = 0;
	DCache dcaches [];
	ICache icaches [];
	ArrayList<Instruction>  instructionBuffer = new ArrayList<Instruction>();
	int instructionBufferCounter;
	int instructionBufferSize;
	int pc;
	
	int arithmeticExec;
	int uncondBranchExec;
	int condBranchExec;
	int callExec;
	
	
	
	int ROBentries;
	int [] RegSt = new int [8]; // Register status ROB#
	int ROBhead = 0,ROBtail = 0;
	//String [][] RS = new String [9][totalResrvStn];
	ArrayList<Unit> RS = new ArrayList<Unit>();
	ROBentry [] ROB = new ROBentry [ROBentries];
	int ROBcounter;
	
	
	public int load(int address, String regNo){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		for(int i =0; i < dcaches.length;i++){
			String data = dcaches[i].find(address);
			if(data != null){
				found = true;
				totalTime += dcaches[i].getAccessTime();
				putInReg(data,regNo);
				break;
			}
			else {
				totalTime += dcaches[i].getAccessTime();
				cacheMisses++;
			}
		}
		boolean foundInMem = false;
		if(!found){
			String data = memory.find(address);
			putInReg(data,regNo);
			totalTime += memory.getAccessTime();
			foundInMem = true;
		}
		
		if(foundInMem){
			cacheMisses = dcaches.length;
		}
		String [] blockData = memory.getBlock(address,dcaches[0].getBytesBlock());
		int offset = (int) Math.ceil(Math.log10(dcaches[0].getBytesBlock())/Math.log10(2));
		for(int i = 0; i<cacheMisses; i++){
			int begAdd = address - offset;
			for (int j = 0; j<blockData.length;j++){
				dcaches[i].put(begAdd,blockData[j]);
				begAdd++;
			}
		}
		return totalTime;

	}
	public int store(int address){
		return 0;
	}
	
	public int fetch(){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		for(int i =0; i < icaches.length;i++){
			String data = icaches[i].find(pc);
			if(data != null){
				found = true;
				totalTime += icaches[i].getAccessTime();
				putInBuffer(data);
				break;
			}
			else {
				totalTime += icaches[i].getAccessTime();
				cacheMisses++;
			}
		}
		boolean foundInMem = false;
		if(!found){
			String data = memory.find(pc);
			putInBuffer(data);
			totalTime += memory.getAccessTime();
			foundInMem = true;
		}
		
		if(foundInMem){
			cacheMisses = icaches.length;
		}
		String [] blockData = memory.getBlock(pc,icaches[0].getBytesBlock());
		int offset = (int) Math.ceil(Math.log10(icaches[0].getBytesBlock())/Math.log10(2));
		for(int i = 0; i<cacheMisses; i++){
			int begAdd = pc - offset;
			for (int j = 0; j<blockData.length;j++){
				icaches[i].put(begAdd,blockData[j]);
				begAdd++;
			}
		}
		pc++;
		return totalTime;
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
	
	public void issue(Instruction ins){
		
	}
	
	public void execute(Instruction ins){
		
	}
	
	public void runCycle(){
		if(instructionBufferCounter >0){
			for(Instruction ins: instructionBuffer){
				if(ins.state == 1){
					if(ROBcounter < ROBentries){
						if(ins.type == 1 || ins.type == 2){
							for(Unit s : RS){
								if(s.unitType == 1 && !s.busy){
									issue(ins);
									ins.state++;
								}
							}
						}
						else if(ins.type == 3){
							for(Unit s : RS){
								if(s.unitType == 2 && !s.busy){
									issue(ins);
									ins.state+=2;
								}
							}

						}
						else if(ins.type == 4 ){
							for(Unit s : RS){
								if(s.unitType == 3 && !s.busy){
									issue(ins);
									ins.state+=2;
								}
							}

						}
						else if(ins.type == 5 || ins.type == 6){
							for(Unit s : RS){
								if(s.unitType == 4 && !s.busy){
									issue(ins);
									ins.state+=2;
								}
							}

						}
						else if(ins.type == 7 || ins.type == 8 || ins.type == 9 || ins.type == 10 || ins.type == 11){
							for(Unit s : RS){
								if(s.unitType == 5 && !s.busy){
									issue(ins);
									ins.state+=2;
								}
							}
						}
					}
				}
				else if (ins.state == 2){
					if(ins.type == 2){
						ins.state+=3;
						execute(ins);
					}
					else {
						ins.state++;
					}
				}
				else if( ins.state == 3){
					if(ins.type == 1){
						int time = load(ins.getAddress(),ins.rd);
						ins.executionCycles = time;
						ins.executionCycles --;
						ins.state++;
						execute(ins);
					}
					
					else if(ins.type == 3){
						ins.executionCycles = uncondBranchExec;
						ins.executionCycles --;
						ins.state++;
						execute(ins);
					}
					else if(ins.type == 4 ){
						ins.executionCycles = condBranchExec;
						ins.executionCycles --;
						ins.state++;
						execute(ins);
					}
					else if(ins.type == 5 || ins.type == 6){
						ins.executionCycles = callExec;
						ins.executionCycles --;
						ins.state++;
						execute(ins);

					}
					else if(ins.type == 7 || ins.type == 8 || ins.type == 9 || ins.type == 10 || ins.type == 11){
						ins.executionCycles = arithmeticExec;
						ins.executionCycles --;
						ins.state++;
						execute(ins);
					}
				}
				else if(ins.state == 4){
					if(ins.executionCycles == 0){
						ins.state++;
					}
					else {
						ins.executionCycles--;
					}
					
				}
				else if (ins.state == 5){
					 if(ins.type == 2){
						int time = store(ins.getAddress());
						ins.writeBackCycles = time;
						ins.writeBackCycles --;
						ins.state++;
						writeBack(ins);
						
					}
					 else {
						 ins.state +=2;
						writeBack(ins);

					 }
				}
				else if (ins.state == 6){
					if(ins.writeBackCycles == 0){
						ins.state++;
					}
					else {
						ins.writeBackCycles--;
					}
				}
				else if( ins.state == 7){
					commit(ins);
					instructionBuffer.remove(ins);
				}
			}
		}
		if(instructionBufferCounter < instructionBufferSize){
			fetch();
		}
		
	}
	
	
	public void putInBuffer(String data){
		//to be done
	}
	
	public static void main (String [] args){
		 java.util.Date date= new java.util.Date();
		 System.out.println(new Timestamp(date.getTime()));
		 java.util.Date date1= new java.util.Date();
		 System.out.println(new Timestamp(date1.getTime()));
	}

}
