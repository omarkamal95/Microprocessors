
public class DCache {
	private int size, bytesBlock, associativity, accessTime;
	// 1 = write back, 2 = write through
	private int writePolicy;
	// 1 = LRU, 2 = Random
	private int replacementPolicy;
	private String [][] content;
	private String [] tag;
	private int [] valid;
	private int bufferSize;
	

	DCache(){
		size = 0;
		bytesBlock = 0;
		associativity = 0;
		accessTime = 0;
		writePolicy = 0;
		content = new String[0][0];
		tag = new String [0];
		valid = new int [0];
		bufferSize = 0;
		replacementPolicy = 0;
	}

	DCache(int s, int b, int a, int t, int wp,int rp, int bs){
		size = s;
		bytesBlock = b;
		associativity = a;
		accessTime = t;
		writePolicy = wp;
		content = new String [size/bytesBlock][bytesBlock];
		tag = new String [size/bytesBlock];
		valid = new int [size/bytesBlock];
		replacementPolicy = rp;
		bufferSize = bs;
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

	public int getWritePolicy() {
		return writePolicy;
	}

	public void setWritePolicy(int writePolicy) {
		this.writePolicy = writePolicy;
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
		
	
	
}
