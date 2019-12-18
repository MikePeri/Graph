package dataStructure;

import java.awt.Color;
import java.util.HashMap;

import utils.Point3D;

public class NodeData implements node_data {
	private int KEY;
	private Point3D LOC;
	private double WEIGHT;
	private String INFO;
	private int TAG;
	private HashMap<Integer, node_data> Neighbors;
	
	public NodeData(int kEY, Point3D lOC, double wEIGHT, String iNFO, int tAG) {
		KEY = kEY;
		LOC = lOC;
		WEIGHT = wEIGHT;
		INFO = iNFO;
		TAG = tAG;
	}//NodeData
	
	public NodeData(int key, Point3D loc, double w) {
		KEY = key;
		LOC = loc;
		WEIGHT = w;
	}

	@Override
	public int getKey() {
		return this.KEY;
	}

	@Override
	public Point3D getLocation() {	
		return LOC;
	}//getLocation

	@Override
	public void setLocation(Point3D p) {
		this.LOC=p;
	}//setLocation

	@Override
	public double getWeight() {
		return this.WEIGHT;
	}

	@Override
	public void setWeight(double w) {
		this.WEIGHT=w;
		
	}

	@Override
	public String getInfo() {
		return INFO;
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
		str+="ID: "+KEY+", Location: ("+LOC.toString()+") , Weight: "+WEIGHT;
		return str;
	}
	
	
	
//	/**
//	 * Set new neighbor
//	 * @param dest - id of destination
//	 * @param dst - destination Node
//	 */
//	public void setNeighbors(int keyDest,node_data node_Dest)
//	{
//		this.Neighbors.put(keyDest, node_Dest);
//	}//setNeighbors
//	/**
//	 * Remove neighbor
//	 * @param dest - id of destination
//	 */
//	public void removeNeighbors(int keyDest)
//	{
//		this.Neighbors.remove(keyDest);
//	}//setNeighbors
//	
//	/**
//	 * if dest is neighbor of this
//	 * @param dest
//	 * @return true if dest is a neighbor of this
//	 */
//	public boolean isNeighbors(int keyDest)
//	{
//		return this.Neighbors.containsKey(keyDest);
//	}//setNeighbors

}
