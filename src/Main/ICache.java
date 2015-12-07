package Main;

public class ICache {
	private int size, bytesBlock, associativity, accessTime;
	private String [][] content;
	private String [] tag;
	private int [] valid;
	private int index, offset;
	private int misses = 0,hits = 0;
	

	ICache(){
		size = 0;
		bytesBlock = 0;
		associativity = 0;
		accessTime = 0;
		content = new String[0][0];
		tag = new String [0];
		valid = new int [0];
	}

	ICache(int s, int b, int a, int t){
		size = s;
		bytesBlock = b;
		associativity = a;
		accessTime = t;
		content = new String [size/bytesBlock][bytesBlock];
		tag = new String [size/bytesBlock];
		valid = new int [size/bytesBlock];
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

	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
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
	
}
