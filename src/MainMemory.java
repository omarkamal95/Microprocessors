
public class MainMemory {
private String [] memory = new String[64*(2^10)];
int accessTime = 0;

MainMemory(int time){
	accessTime = time;
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
