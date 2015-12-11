package Main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Queue;


public class Main {
	MainMemory memory;
	static int R1 =0,R2=0,R3=0,R4=0,R5=0,R6=0,R7=0;
	final static int R0 = 0;
	ArrayList<DCache> dcaches;
	ArrayList<ICache> icaches;
	ArrayList<Instruction>  instructionBuffer = new ArrayList<Instruction>();
	int instructionBufferCounter = 0;
	int instructionBufferSize;
	int pc;

	ArrayList<Instruction> instructionsToBeWritten = new ArrayList<Instruction>();

	// Number of instructions that can be issued to the reservation station simultaneously 
	int pipelineWidth;

	String [] assembly;
	ArrayList<String> dataValue;
	int arithmeticExec;
	int uncondBranchExec;
	int condBranchExec;
	int callExec;



	int ROBentries;
	int [] RegSt = new int [8]; // Register status ROB#
	int ROBhead = 1,ROBtail = 1;
	//String [][] RS = new String [9][totalResrvStn];
	ArrayList<Unit> RS = new ArrayList<Unit>();
	ROBentry [] ROB = new ROBentry [ROBentries+1];//ROB starts from 1 not 0 to be consistent
	int ROBcounter = 0;

	public Main(ArrayList<DCache>  d, ArrayList<ICache>  i, MainMemory m, int p, int ibs, int rob,int ld, int un, int cn, int call, int arth,int uncondCycle,int coCycle,int clCycle,int arithmeticCycle, String [] a,int origin, ArrayList<String> data)
	{
		//how does she get the program and start address
		pc = origin;
		dcaches = d;
		icaches = i;
		memory = m;
		pipelineWidth = p;
		instructionBufferSize = ibs;
		ROBentries = rob;
		assembly = a;
		dataValue = data; // what is this?
		ROB = new ROBentry [ROBentries+1];
		instructionBuffer = new ArrayList<Instruction>();
		arithmeticExec = arithmeticCycle;
		uncondBranchExec = uncondCycle;
		condBranchExec = coCycle;
		callExec = clCycle;
		RS = new ArrayList<Unit>();
		for(int j = 0; j < ld; j++){
			Unit x = new Unit(1);
			RS.add(x);
		}
		for(int j = 0; j < un; j++){
			Unit x = new Unit(2);
			RS.add(x);
		}
		for(int j = 0; j < cn; j++){
			Unit x = new Unit(3);
			RS.add(x);
		}
		for(int j = 0; j < call; j++){
			Unit x = new Unit(4);
			RS.add(x);
		}
		for(int j = 0; j < arth; j++){
			Unit x = new Unit(5);
			RS.add(x);
		}
		int j = pc;
		for(int k = 0; k< a.length;k++ ){
			memory.getMemory()[j] = a[k];
			j+=2;
		}
	}

	public int load(int address, String regNo){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		for(int i =0; i < dcaches.size();i++){
			String data = dcaches.get(i).find(address);
			if(data != null){
				found = true;
				totalTime += dcaches.get(i).getAccessTime();
				putInReg(data,regNo);
				break;
			}
			else {
				totalTime += dcaches.get(i).getAccessTime();
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
			cacheMisses = dcaches.size();
		}
		String [] blockData = memory.getBlock(address,dcaches.get(0).getBytesBlock());
		int offset = (int) Math.ceil(Math.log10(dcaches.get(0).getBytesBlock())/Math.log10(2));
		for(int i = 0; i<cacheMisses; i++){
			int begAdd = address - offset;
			for (int j = 0; j<blockData.length;j++){
				dcaches.get(i).put(begAdd,blockData[j]);
				begAdd++;
			}
		}
		return totalTime;

	}

	public String getLoadData(int address){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		String res = null;
		for(int i =0; i < dcaches.size();i++){
			String data = dcaches.get(i).find(address);
			if(data != null){
				found = true;
				res = data;
				totalTime += dcaches.get(i).getAccessTime();
				break;
			}
			else {
				totalTime += dcaches.get(i).getAccessTime();
				cacheMisses++;
			}
		}
		if(!found){
			res = memory.find(address);
			totalTime += memory.getAccessTime();
		}


		return res;

	}


	public int loadSim(int address, String regNo){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		for(int i =0; i < dcaches.size();i++){
			String data = dcaches.get(i).find(address);
			if(data != null){
				found = true;
				totalTime += dcaches.get(i).getAccessTime();
				break;
			}
			else {
				totalTime += dcaches.get(i).getAccessTime();
				cacheMisses++;
			}
		}
		if(!found){
			totalTime += memory.getAccessTime();
		}


		return totalTime;

	}
	public int store(int address, String data){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		boolean bufferFullWrite = false;
		for(int i =0; i < dcaches.size();i++){
			boolean foundInCache = dcaches.get(i).write(address,data);
			if(foundInCache){
				found = true;
				totalTime += dcaches.get(i).getAccessTime();
				if(dcaches.get(i).getWriteHitPolicy() == 2){
					if(dcaches.get(i).bufferFull){
						bufferFullWrite = true;
						for(int j= 0; j< dcaches.get(i).getBufferSize(); j++){
							for (int k = i+1;k <dcaches.size();k++){
								dcaches.get(k).writeIfFound(dcaches.get(i).getAddressBuffer()[j], 
										dcaches.get(i).getDataBuffer()[j]);
							}
							memory.getMemory()[dcaches.get(i).getAddressBuffer()[j]]= dcaches.get(i).getDataBuffer()[j];
						}
						dcaches.get(i).emptyBuffer();
					}
				}
				break;
			}
			else {
				totalTime += dcaches.get(i).getAccessTime();
				cacheMisses++;
			}
		}
		if(!found){
			memory.getMemory()[address] = data;
			totalTime += memory.getAccessTime();
		}
		if(bufferFullWrite){
			totalTime += memory.getAccessTime();
			int i = cacheMisses+2;
			while(i< dcaches.size()){
				totalTime += dcaches.get(i).getAccessTime();
				i++;
			}
		}


		return totalTime;

	}
	public int storeSim(int address,String data){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		boolean bufferFullWrite = false;
		for(int i =0; i < dcaches.size();i++){
			boolean foundInCache = dcaches.get(i).writeSim(address,data);
			if(foundInCache){
				found = true;
				totalTime += dcaches.get(i).getAccessTime();
				if(dcaches.get(i).getWriteHitPolicy() == 2){
					if(dcaches.get(i).bufferFull){
						bufferFullWrite = true;
						dcaches.get(i).bufferFull = false;
					}
				}
				break;
			}
			else {
				totalTime += dcaches.get(i).getAccessTime();
				cacheMisses++;
			}
		}
		if(!found){
			totalTime += memory.getAccessTime();
		}
		if(bufferFullWrite){
			totalTime += memory.getAccessTime();
			int i = cacheMisses+2;
			while(i< dcaches.size()){
				totalTime += dcaches.get(i).getAccessTime();
				i++;
			}
		}


		return totalTime;

	}

	public int fetch(){
		int totalTime =0;
		boolean found = false;
		int cacheMisses = 0;
		for(int i =0; i < icaches.size();i++){
			String data = icaches.get(i).find(pc);
			if(data != null){
				found = true;
				totalTime += icaches.get(i).getAccessTime();
				putInBuffer(data);
				break;
			}
			else {
				totalTime += icaches.get(i).getAccessTime();
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
			cacheMisses = icaches.size();
		}
		String [] blockData = memory.getBlock(pc,icaches.get(0).getBytesBlock());
		int offset = (int) Math.ceil(Math.log10(icaches.get(0).getBytesBlock())/Math.log10(2));
		for(int i = 0; i<cacheMisses; i++){
			int begAdd = pc - offset;
			for (int j = 0; j<blockData.length;j++){
				icaches.get(i).put(begAdd,blockData[j]);
				begAdd++;
			}
		}
		pc+=2;
		return totalTime;
	}

	public void putInBuffer(String data){
		String [] insStrings = data.split(",");
		for(int i = 0; i< insStrings.length; i++)
			insStrings[i].trim();
		int type = 0;
		String rs = null,rt = null,rd = null;
		int branchPC = 0;
		if(insStrings[0].equalsIgnoreCase("lw")){
			type = 1;
			rd = insStrings[1];
			rs = insStrings[2];
			rt = insStrings[3];
		}
		else if(insStrings[0].equalsIgnoreCase("sw")){
			type = 2;
			rt = insStrings[1];
			rs = insStrings[2];
			rd = insStrings[3];
		}
		else if(insStrings[0].equalsIgnoreCase("jmp")){
			type = 3;
			rs = insStrings[1];
			rt = insStrings [2];
			pc = pc + getRegisterValue(rs)+ Integer.parseInt(rt);
		}
		else if(insStrings[0].equalsIgnoreCase("beq")){
			type = 4;
			rs = insStrings[1];
			rt = insStrings[2];
			rd = insStrings[3];
			if(Integer.parseInt(rd)<0){
				branchPC = pc +2;
				pc = pc +  Integer.parseInt(rd);
			}

		}
		else if(insStrings[0].equalsIgnoreCase("jalr")){
			type = 5;
			rd = insStrings[1];
			rs = insStrings[2];
			pc = pc + getRegisterValue(rs);
		}
		else if(insStrings[0].equalsIgnoreCase("ret")){
			type = 6;
			rs = insStrings[1];
			pc = pc + getRegisterValue(rs);

		}
		else if(insStrings[0].equalsIgnoreCase("add")){
			type = 7;
			rd = insStrings[1];
			rs = insStrings[2];
			rt = insStrings[3];
		}
		else if(insStrings[0].equalsIgnoreCase("sub")){
			type = 8;
			rd = insStrings[1];
			rs = insStrings[2];
			rt = insStrings[3];
		}
		else if(insStrings[0].equalsIgnoreCase("addi")){
			type = 9;
			rd = insStrings[1];
			rs = insStrings[2];
			rt = insStrings[3];
		}
		else if(insStrings[0].equalsIgnoreCase("nand")){
			type = 10;
			rd = insStrings[1];
			rs = insStrings[2];
			rt = insStrings[3];
		}
		else if(insStrings[0].equalsIgnoreCase("mul")){
			type = 11;
			rd = insStrings[1];
			rs = insStrings[2];
			rt = insStrings[3];
		}
		Instruction ins= new Instruction(type, rs, rt, rd);
		if(ins.type == 5){
			ins.pcPos = pc+2;
		}
		else if (ins.type == 4){
			ins.pcPos = branchPC;
		}
		instructionBuffer.add(ins);

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

	public static int getRegisterNo(String rs){
		if(rs.equalsIgnoreCase("R0")){
			return 0;
		}
		if(rs.equalsIgnoreCase("R1")){
			return 1;
		}
		if(rs.equalsIgnoreCase("R2")){
			return 2;
		}
		if(rs.equalsIgnoreCase("R3")){
			return 3;
		}
		if(rs.equalsIgnoreCase("R4")){
			return 4;
		}
		if(rs.equalsIgnoreCase("R5")){
			return 5;
		}
		if(rs.equalsIgnoreCase("R6")){
			return 6;
		}
		if(rs.equalsIgnoreCase("R7")){
			return 7;
		}
		return 100;
	}

	public static int getRegisterValue(String rs){
		if(rs.equalsIgnoreCase("R0")){
			return R0;
		}
		if(rs.equalsIgnoreCase("R1")){
			return R1;
		}
		if(rs.equalsIgnoreCase("R2")){
			return R2;
		}
		if(rs.equalsIgnoreCase("R3")){
			return R3;
		}
		if(rs.equalsIgnoreCase("R4")){
			return R4;
		}
		if(rs.equalsIgnoreCase("R5")){
			return R5;
		}
		if(rs.equalsIgnoreCase("R6")){
			return R6;
		}
		if(rs.equalsIgnoreCase("R7")){
			return R7;
		}
		return 100;
	}

	public void issue(Instruction ins){

		if(RegSt[getRegisterNo(ins.rs)] != 0){
			int h = RegSt[getRegisterNo(ins.rs)];
			if(ROB[h].ready){
				RS.get(ins.resStationIndex).vj = ROB[h].value;
				RS.get(ins.resStationIndex).qj = 0;
			}
			else {
				RS.get(ins.resStationIndex).qj = h;
			}
		}
		else {
			RS.get(ins.resStationIndex).vj = ins.rs;
			RS.get(ins.resStationIndex).qj = 0;
		}

		if((ins.type >= 7 && ins.type <=11) || ins.type == 2 || ins.type == 4){ 
			if(RegSt[getRegisterNo(ins.rt)] != 0){
				int h = RegSt[getRegisterNo(ins.rt)];
				if(ROB[h].ready){
					RS.get(ins.resStationIndex).vk = ROB[h].value;
					RS.get(ins.resStationIndex).qk = 0;
				}
				else {
					RS.get(ins.resStationIndex).qk = h;
				}
			}
			else {
				RS.get(ins.resStationIndex).vk = ins.rt;
				RS.get(ins.resStationIndex).qk = 0;
			}
			RegSt[getRegisterNo(ins.rd)] = ROBtail;
		}
		if(ins.type != 2 && ins.type != 3 && ins.type != 4 && ins.type != 6){
			RS.get(ins.resStationIndex).busy = true;
			RS.get(ins.resStationIndex).dest =ROBtail;
			ROB[ROBtail].instruction = ins.type;
			ROB[ROBtail].dest = ins.rd;
			ROB[ROBtail].ready = false;
		}
		else if( ins.type == 2){
			RS.get(ins.resStationIndex).busy = true;
			RS.get(ins.resStationIndex).dest =ROBtail;
			ROB[ROBtail].instruction = ins.type;
			ROB[ROBtail].dest = "" + ins.getAddress();
			ROB[ROBtail].ready = false;
		}
		else{
			RS.get(ins.resStationIndex).busy = true;
			RS.get(ins.resStationIndex).dest =ROBtail;
			ROB[ROBtail].instruction = ins.type;
			ROB[ROBtail].dest = "branch";
			ROB[ROBtail].ready = false;
		}
		ROBcounter++;
		incrementTail();
	}

	public String NAND(int x, int y){
		String xs = integerToBinary(x);
		String ys = integerToBinary(y);
		String res = "";
		for(int i = 0; i< xs.length(); i++){
			char xc = xs.charAt(i);
			char yc = ys.charAt(i);
			if(xc == '0' || yc == '0'){
				res += "1";
			}
			else if( xc == '1' && yc == '1'){
				res += "0";
			}
		}
		return ""+ binaryToInteger(res);
	}

	public void writeBack(Instruction ins){
		//		b ← RS[r].Dest; RS[r].Busy ← no;
		//		∀x(if (RS[x].Qj==b) {RS[x].Vj ← result; RS[x].Qj ← 0});
		//		∀x(if (RS[x].Qk==b) {RS[x].Vk ← result; RS[x].Qk ← 0});
		//		ROB[b].Value ← result; ROB[b].Ready ← yes;
		String res=null; // to be defined according to ins
		if(ins.type == 1){
			res = ""+ins.getAddress();
		}
		else if(ins.type == 2){
			res = ""+getRegisterValue(RS.get(ins.resStationIndex).vk);
		}
		else if(ins.type == 5){
			res = "" +ins.pcPos;
		}
		else if(ins.type == 4){
			if(Integer.parseInt(ins.rd) < 0){
				if(!RS.get(ins.resStationIndex).vj.equalsIgnoreCase(RS.get(ins.resStationIndex).vk)){
					res = "" +ins.pcPos;
				}
				else {
					res = null;
				}
			} 
			else if(Integer.parseInt(ins.rd) >= 0){
				if(RS.get(ins.resStationIndex).vj.equalsIgnoreCase(RS.get(ins.resStationIndex).vk)){
					res = ins.rd;
				}
				else {
					res = null;
				}
			}
		}
		else if(ins.type == 7){
			res =""+ (getRegisterValue(RS.get(ins.resStationIndex).vj) 
					+getRegisterValue(RS.get(ins.resStationIndex).vk));
		}
		else if(ins.type == 9){
			res =""+ (getRegisterValue(RS.get(ins.resStationIndex).vj)  
					+ Integer.parseInt(RS.get(ins.resStationIndex).vk));
		}
		else if(ins.type == 10){
			res =""+ (NAND(getRegisterValue(RS.get(ins.resStationIndex).vj) 
					,getRegisterValue(RS.get(ins.resStationIndex).vk)));
		}
		else if(ins.type == 11){
			res =""+ (getRegisterValue(RS.get(ins.resStationIndex).vj)  
					* getRegisterValue(RS.get(ins.resStationIndex).vk));
		}
		else if(ins.type == 8){
			res =""+ (getRegisterValue(RS.get(ins.resStationIndex).vj)  
					-getRegisterValue(RS.get(ins.resStationIndex).vk));
		}


		int b = RS.get(ins.resStationIndex).dest;
		RS.get(ins.resStationIndex).busy = false;
		for(Unit x: RS){
			if(x.qj == b){
				x.vj = res;
				x.qj = 0;
			}
			if(x.qk == b){
				x.vk = res;
				x.qk = 0;
			}
		}
		ROB[b].value = res;
		ROB[b].ready = true;
	}
	
	public void incrementHead(){
		if(ROBhead == ROBentries){
			ROBhead = 1;
		}
		else {
			ROBhead++;
		}
	}
	public void incrementTail(){
		if(ROBtail == ROBentries){
			ROBtail = 1;
		}
		else {
			ROBtail++;
		}
	}
	
	
	public void commit(){
		for(int i=0;i< pipelineWidth; i++){
			if(ROBcounter>0){
				ROBentry headEntry = ROB[ROBhead];
				if(headEntry.ready){
					if(headEntry.instruction == 1){
						load(Integer.parseInt(headEntry.value), headEntry.dest);
						if (RegSt[getRegisterNo(headEntry.dest)] == ROBhead) {
							RegSt[getRegisterNo(headEntry.dest)] = 0;
						}
					}
					else if(headEntry.instruction ==2 ){
						store(Integer.parseInt(headEntry.dest), headEntry.value);
					}
					else if (headEntry.instruction == 4){
						if(headEntry.value != null){
							pc = Integer.parseInt(headEntry.value);
							clearROB();
							clearRegisterStat();
							clearInstructionBuff();
						}
					}
					else if (headEntry.instruction == 5){
						setRegValue(headEntry.dest,headEntry.value);
						if (RegSt[getRegisterNo(headEntry.dest)] == ROBhead) {
							RegSt[getRegisterNo(headEntry.dest)] = 0;
						}
					}
					else if (headEntry.instruction >= 7 && headEntry.instruction <=11){
						setRegValue(headEntry.dest, headEntry.value);
						if (RegSt[getRegisterNo(headEntry.dest)] == ROBhead) {
							RegSt[getRegisterNo(headEntry.dest)] = 0;
						}
					}
				}
				ROB[ROBhead] = new ROBentry();
				incrementHead();
			}
		}
	}

	public void clearInstructionBuff() {
		instructionBufferCounter = 0;
		instructionBuffer.clear();
	}

	public void clearRegisterStat() {
		for(int i = 0; i< RegSt.length;i++){
			RegSt[i]=0;
		}
	}

	public void clearROB() {
		for(int i = 1; i<ROBentries+1; i++){
			ROB[i] = new ROBentry();
		}
		ROBhead = 1;
		ROBtail = 1;
		ROBcounter = 0;
	}

	public void setRegValue(String rs, String value) {
		
		if(rs.equalsIgnoreCase("R1")){
			 R1 = Integer.parseInt(value);;
		}
		if(rs.equalsIgnoreCase("R2")){
			 R2= Integer.parseInt(value);;
		}
		if(rs.equalsIgnoreCase("R3")){
			 R3= Integer.parseInt(value);;
		}
		if(rs.equalsIgnoreCase("R4")){
			 R4= Integer.parseInt(value);;
		}
		if(rs.equalsIgnoreCase("R5")){
			 R5= Integer.parseInt(value);;
		}
		if(rs.equalsIgnoreCase("R6")){
			 R6= Integer.parseInt(value);;
		}
		if(rs.equalsIgnoreCase("R7")){
			 R7= Integer.parseInt(value);;
		}

	}

	public void runCycle(){
		
		commit();
		
		if(instructionBufferCounter >0){
			for(Instruction ins: instructionBuffer){
				if(ins.state == 1){
					if(ROBcounter < ROBentries){
						if(ins.type == 1 || ins.type == 2){
							for(Unit s : RS){
								if(s.unitType == 1 && !s.busy){
									issue(ins);
									ins.state++;
									ins.resStationIndex = RS.indexOf(s);
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
						if( RS.get(ins.resStationIndex).qj == 0){
							ins.state+=3;
						}
					}
					else {
						if(ins.type == 1){
							if( RS.get(ins.resStationIndex).qj == 0){
								ins.state++;
							}
						}
						else {
							if( RS.get(ins.resStationIndex).qj == 0 && RS.get(ins.resStationIndex).qk == 0){
								ins.state++;
							}
						}
					}
				}
				else if( ins.state == 3){
					if(ins.type == 1){
						int insPosInROB = RS.get(ins.resStationIndex).dest;
						boolean reached = false; 
						boolean storeClash = false;
						while(!reached){
							if(ROB[insPosInROB].instruction ==2){
								if(ROB[insPosInROB].dest.equals(""+ ins.getAddress())){
									storeClash = true;
								}
							}
							if(insPosInROB == ROBtail){
								break;
							}
							else{
								if(insPosInROB == 1){
									insPosInROB = ROBentries;
								} else {
									insPosInROB--;
								}
							}
						}
						if(!storeClash){
							int time = loadSim(ins.getAddress(),ins.rd);
							ins.executionCycles = time;
							ins.executionCycles--;
							ins.state++;
						}

					}

					else if(ins.type == 3){
						if( RS.get(ins.resStationIndex).qj == 0){
							ins.executionCycles = uncondBranchExec;
							ins.executionCycles --;
							ins.state++;
						}
					}
					else if(ins.type == 4 ){
						if( RS.get(ins.resStationIndex).qj == 0 && RS.get(ins.resStationIndex).qk == 0){
							ins.executionCycles = condBranchExec;
							ins.executionCycles --;
							ins.state++;
						}
					}
					else if(ins.type == 5 || ins.type == 6){
						if( RS.get(ins.resStationIndex).qj == 0){
							ins.executionCycles = callExec;
							ins.executionCycles --;
							ins.state++;
						}

					}
					else if(ins.type == 7 || ins.type == 8 || ins.type == 9 || ins.type == 10 || ins.type == 11){
						if( RS.get(ins.resStationIndex).qj == 0 && RS.get(ins.resStationIndex).qk == 0){
							ins.executionCycles = arithmeticExec;
							ins.executionCycles --;
							ins.state++;
						}
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
						if(RS.get(ins.resStationIndex).qk == 0){
							int time = storeSim(ins.getAddress(),""+getRegisterValue(RS.get(ins.resStationIndex).vk));
							ins.writeBackCycles = time;
							ins.writeBackCycles --;
							ins.state++;
							instructionsToBeWritten.add(ins);
						}

					}
					else {
						ins.state +=2;
						if(ins.type!= 3 && ins.type!=4 && ins.type!=6){	
							instructionsToBeWritten.add(ins);
						}

						instructionBuffer.remove(ins);
					}
				}
				else if (ins.state == 6){
					if(ins.writeBackCycles == 0){
						ins.state++;
						instructionsToBeWritten.add(ins);
						instructionBuffer.remove(ins);
					}
					else {
						ins.writeBackCycles--;
					}
				}

			}


		}
	}

	public void run(){
		int i = 0;
		while(memory.getMemory()[pc]!= null && memory.getMemory()[pc]!= "" && i<pipelineWidth ){
			fetch();
			i++;
		}
			runCycle();
			for(Instruction ins: instructionsToBeWritten){
				writeBack(ins);
			}
		
	}



	public static void main (String [] args){
		//		 java.util.Date date= new java.util.Date();
		//		 System.out.println(new Timestamp(date.getTime()));
		//		 java.util.Date date1= new java.util.Date();
		//		 System.out.println(new Timestamp(date1.getTime()));
	}

}
