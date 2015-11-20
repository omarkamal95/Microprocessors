
public class ICache {
	private int size, bytesBlock, associativity, accessTime;
	private String [][] content;
	private String [] tag;
	private int [] valid;
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
	
}
