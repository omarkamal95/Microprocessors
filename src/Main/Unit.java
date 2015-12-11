package Main;
public class Unit {

	// 1= Load/Store, 2= unconditional branch, 3= conditional branch, 4= call/return
	// 5= Arithmetic
	int unitType;


	boolean busy;
	int op;
	String vj,vk;
	int qj,qk;
	int dest;
	String a;

	public Unit(){

	}
	public Unit(int type){
		this.unitType= type;
		busy = false;
		op = 0;
		vj =null;
		vk= null;
		qj =0;
		qk = 0;
		dest = 0;
		a = "";
	}


	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}



	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public String getVj() {
		return vj;
	}

	public void setVj(String vj) {
		this.vj = vj;
	}

	public String getVk() {
		return vk;
	}

	public void setVk(String vk) {
		this.vk = vk;
	}

	public int getQj() {
		return qj;
	}

	public void setQj(int qj) {
		this.qj = qj;
	}

	public int getQk() {
		return qk;
	}

	public void setQk(int qk) {
		this.qk = qk;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}


}
