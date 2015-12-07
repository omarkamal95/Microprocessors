package Main;
import java.util.ArrayList;
import java.util.Date;


public class DCache {
	private int size, bytesBlock, associativity, accessTime;
	
	// 1 = write back, 2 = write through
	private int writeHitPolicy;
	
	// 1 = Write Around 2 = Write Allocate
	private int writeMissPolicy;
	
	// 1 = LRU, 2 = Random
	private int replacementPolicy;
	
	private String [][] content;
	private String [] tag;
	private int [] valid, dirty;
	private int bufferSize, index, offset;
	private int misses = 0,hits= 0;
	private ArrayList<java.util.Date> lastUsed= new ArrayList<java.util.Date> ();
	

	DCache(){
		size = 0;
		bytesBlock = 0;
		associativity = 0;
		accessTime = 0;
		writeHitPolicy = 0;
		writeMissPolicy = 0;
		content = new String[0][0];
		tag = new String [0];
		valid = new int [0];
		bufferSize = 0;
		replacementPolicy = 0;
		dirty = new int [0];
	}

	public DCache(int s, int b, int a, int t, int whp,int rp, int bs){
		size = s;
		bytesBlock = b;
		associativity = a;
		accessTime = t;
		writeHitPolicy = whp;
		content = new String [size/bytesBlock][bytesBlock];
		tag = new String [size/bytesBlock];
		valid = new int [size/bytesBlock];
		replacementPolicy = rp;
		if(writeHitPolicy == 1){
			bufferSize = bs;
		}
		if (writeHitPolicy == 0){
			dirty = new int [size/bytesBlock];
		}
		 index = (int) Math.ceil(Math.log10(size/bytesBlock)/Math.log10(2));
		 offset = (int) Math.ceil(Math.log10(bytesBlock)/Math.log10(2));
	}
	
	
	private void write(int address, String data) {
		

	}
	
	public void put(int address, String data){
		String addr = Main.integerToBinary(address);
		String addrIndex = addr.substring(addr.length() - index - offset, addr.length()- offset);
		String addrOffset = addr.substring(addr.length() - offset, addr.length());
		String addrTag = addr.substring(0, addr.length() - index - offset);
		
		int intAddr = Main.binaryToInteger(addrIndex);
		int intOffset = Main.binaryToInteger(addrOffset);
		int startIndex = intAddr*associativity;
		
		boolean added = false;
		for(int i = 0; i< associativity; i++){
			if(valid[startIndex] == 1){
				if(tag[startIndex].equals(addrTag)){
					content[startIndex][intOffset] =data;
					added = true;
					lastUsed.set(startIndex, new java.util.Date());
				}
			}
			startIndex ++;
		}
		
		if(!added){
			startIndex = intAddr*associativity;
			for(int i = 0; i< associativity; i++){
				if(valid[startIndex] == 0){
					tag[startIndex] = addrTag;
					content [startIndex][intOffset] = data;
					added = true;
					lastUsed.set(startIndex, new java.util.Date());
				}
				startIndex ++;
			}
		}
		
		
		startIndex = intAddr*associativity;
		if(!added){
			if(replacementPolicy == 1){
				Date lastDate = lastUsed.get(startIndex);
				int replaceInd = startIndex;
				for(int i = 0; i< associativity; i++){
					if(lastUsed.get(startIndex).compareTo(lastDate)<0){
						lastDate = lastUsed.get(startIndex);
						replaceInd = startIndex;
					}
					startIndex ++;
				}
				tag[replaceInd] = addrTag;
				content [replaceInd][intOffset] = data;
				added = true;
				lastUsed.set(replaceInd, new java.util.Date());
			}
			else {
				tag[startIndex] = addrTag;
				content [startIndex][intOffset] = data;
				added = true;
				lastUsed.set(startIndex, new java.util.Date());
			}
		}
		
	}
	
	public String find(int address){
		String addr = Main.integerToBinary(address);
		String addrIndex = addr.substring(addr.length() - index - offset, addr.length()- offset);
		String addrOffset = addr.substring(addr.length() - offset, addr.length());
		String addrTag = addr.substring(0, addr.length() - index - offset);
		
		
		int intAddr = Main.binaryToInteger(addrIndex);
		int intOffset = Main.binaryToInteger(addrOffset);
		int startIndex = intAddr*associativity;
		for(int i = 0; i< associativity; i++){
			if(valid[startIndex] == 1){
				if(tag[startIndex].equals(addrTag)){
					lastUsed.set(startIndex, new java.util.Date());
					return content[startIndex][intOffset];
				}
			}
			startIndex ++;
		}
		return null;
		
		
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getBytesBlock() {
		return bytesBlock;
	}

	public void setBytesBlock(int bytesBlock) {
		this.bytesBlock = bytesBlock;
	}
	
	public int getAssociativity() {
		return associativity;
	}

	public void setAssociativity(int associativity) {
		this.associativity = associativity;
	}

	public int getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(int accessTime) {
		this.accessTime = accessTime;
	}

	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getWriteHitPolicy() {
		return writeHitPolicy;
	}

	public void setWriteHitPolicy(int writeHitPolicy) {
		this.writeHitPolicy = writeHitPolicy;
	}

	public int getWriteMissPolicy() {
		return writeMissPolicy;
	}

	public void setWriteMissPolicy(int writeMissPolicy) {
		this.writeMissPolicy = writeMissPolicy;
	}

	public String[][] getContent() {
		return content;
	}

	public void setContent(String[][] content) {
		this.content = content;
	}

	public String[] getTag() {
		return tag;
	}

	public void setTag(String[] tag) {
		this.tag = tag;
	}

	public int[] getValid() {
		return valid;
	}

	public void setValid(int[] valid) {
		this.valid = valid;
	}

	public int getReplacementPolicy() {
		return replacementPolicy;
	}

	public void setReplacementPolicy(int replacementPolicy) {
		this.replacementPolicy = replacementPolicy;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public int[] getDirty() {
		return dirty;
	}

	public void setDirty(int[] dirty) {
		this.dirty = dirty;
	}
		
	
	
}
