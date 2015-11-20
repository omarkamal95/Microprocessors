
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
	private int bufferSize;
	private int misses = 0,hits= 0;
	

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

	DCache(int s, int b, int a, int t, int whp, int wmp,int rp, int bs){
		size = s;
		bytesBlock = b;
		associativity = a;
		accessTime = t;
		writeHitPolicy = whp;
		writeMissPolicy = wmp;
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
