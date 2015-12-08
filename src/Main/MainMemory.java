package Main;

public class MainMemory {
private String [] memory = new String[64*(2^10)];
int accessTime = 0;


MainMemory(){
	accessTime = 0;
}

public MainMemory(int time){
	accessTime = time;
}

public String find(int address){
	return memory[address];
}

public String [] getBlock(int address, int bytesBlock){
	int offset = (int) Math.ceil(Math.log10(bytesBlock)/Math.log10(2));
	
	String addr = Main.integerToBinary(address);
	String addrOffset = addr.substring(addr.length() - offset, addr.length());
	int intOffset = Main.binaryToInteger(addrOffset);
	int begAdd = address - intOffset;
	
	String [] block = new String [bytesBlock];
	for(int i = 0; i<bytesBlock ; i++){
		block[i] = memory[begAdd];
		begAdd++;
	}
	return block;
}

public int getAccessTime() {
	return accessTime;
}

public void setAccessTime(int accessTime) {
	this.accessTime = accessTime;
}

public String[] getMemory() {
	return memory;
}

public void setMemory(String[] memory) {
	this.memory = memory;
}


}
