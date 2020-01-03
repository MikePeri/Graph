package dataStructure;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

import utils.Point3D;

public class NodeData implements node_data,Serializable {
	private int KEY;
	private Point3D LOC;
	private double WEIGHT;
	private String INFO;
	private int TAG;
	
	public NodeData(int kEY, Point3D lOC, double wEIGHT, String iNFO, int tAG) {
		KEY = kEY;
		LOC = lOC;
		WEIGHT = wEIGHT;
		INFO = iNFO;
		TAG = tAG;
	}//NodeData
	
	public NodeData(int key, Point3D loc) {
		this.KEY = key;
		this.LOC = loc;
		this.WEIGHT=Double.MAX_VALUE;
		this.TAG=1;
	}
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
	}//toString
	@Override
	public boolean equals(Object node)
	{
		if(node instanceof node_data)
		{
			if(((node_data) node).getInfo()==this.INFO&&
					((node_data) node).getKey()==this.KEY&&
					((node_data) node).getLocation().equals(this.LOC)&&
					((node_data) node).getTag()==this.TAG&&
					((node_data) node).getWeight()==this.WEIGHT)
				return true;
			return false;
		}//if
		return false;
	}//equals

}
