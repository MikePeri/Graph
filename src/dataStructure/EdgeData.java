package dataStructure;

public class EdgeData implements edge_data{
	private int SRC;
	private int DST;
	private double WEIGHT;
	private String INFO;
	private int TAG;
	
	public EdgeData(int SRC, int DST, double w) {
		this.SRC=SRC;
		this.DST=DST;
		this.WEIGHT=w;
	}
	
	
	
	@Override
	public int getSrc() {
		return this.SRC;
	}

	@Override
	public int getDest() {
		return this.DST;
	}

	@Override
	public double getWeight() {
		return this.WEIGHT;
	}

	@Override
	public String getInfo() {
		return this.INFO;
	}

	@Override
	public void setInfo(String s) {
		this.INFO=s;
		
	}

	@Override
	public int getTag() {
	    return this.TAG;
	}

	@Override
	public void setTag(int t) {
		this.TAG=t;
		
	}
	
	public String toString() {
		String str="";
		str="Source: "+SRC+", Destination: "+DST+", Weight: "+WEIGHT;
		return str;
	}

}
