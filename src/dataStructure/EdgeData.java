package dataStructure;

public class EdgeData implements edge_data{
	private int SRC;
	private int DST;
	private String ID;
	private double WEIGHT;
	private String INFO;
	private int TAG;
	
	
	
	
	
	public EdgeData(int sRC, int dST, double wEIGHT, String iNFO, int tAG) {
		if(sRC==dST)
			throw new RuntimeException("ERR: This isn't ,multy graph");
		SRC = sRC;
		DST = dST;
		ID = String.valueOf(sRC)+"->"+String.valueOf(dST);
		WEIGHT = wEIGHT;
		INFO = iNFO;
		TAG = tAG;
		
	}
	public EdgeData(int sRC, int dST, double wEIGHT) {
		super();
		SRC = sRC;
		DST = dST;
		ID = String.valueOf(sRC)+"->"+String.valueOf(dST);
		WEIGHT = wEIGHT;
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
	public String getID()
	{
		return this.ID;
	}//String
	@Override
	public boolean equals(Object edge)
	{
		if(edge instanceof EdgeData)
		{
			if(((EdgeData) edge).getDest()==this.DST && 
					((EdgeData) edge).getID()==this.ID&&
					((EdgeData) edge).getInfo()==this.INFO &&
					((EdgeData) edge).getSrc()==this.SRC &&
					((EdgeData) edge).getTag()==this.getTag()&&
					((EdgeData) edge).getWeight()==this.WEIGHT)
				return true;
			return false;
		}//if
		else
			return false;
	}//equals
	

}
