package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;

import utils.Point3D;
import utils.Range;
import utils.StdDraw;
import dataStructure.DGraph;
import dataStructure.graph;

public class Graph_GUI {

	DGraph Graph;

	public Graph_GUI() {
		this.Graph=new DGraph();
	}

	public Graph_GUI(DGraph g) {
		this.Graph=g;
	}

	public void drawGraph(int width, int height) {
		//check legal parameters:
		if(width<=0 || height<=0) {
			throw new RuntimeException("The input was not legal");
		}
		Range rx=this.rangeX();
		Range ry=this.rangeY();
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(-width/2, width/2);
		StdDraw.setYscale(-height/2, height/2);

		


		Iterator<Integer> it = Graph.get_Edge_Hash().keySet().iterator();
		while (it.hasNext()) {
			Integer v = it.next();
			Point3D src=Graph.get_Node_Hash().get(v).getLocation();
			Iterator<Integer> neighbors = Graph.get_Edge_Hash().get(v).keySet().iterator();
			while(neighbors.hasNext()) {
				Integer u=it.next();
				Point3D dest=Graph.get_Node_Hash().get(u).getLocation();
				StdDraw.line(src.x(), src.y(), dest.x(), dest.y());
			}
		}
	}

	public Range rangeX() {
		double max=Integer.MIN_VALUE;
		double min=Integer.MAX_VALUE;

		Iterator<Integer> it = Graph.get_Node_Hash().keySet().iterator();
		while (it.hasNext()) {
			Integer node=it.next();
			if(Graph.get_Node_Hash().get(node).getLocation().x()>max)
				max=Graph.get_Node_Hash().get(node).getLocation().x();
			else if(Graph.get_Node_Hash().get(node).getLocation().x()<min)
				min=Graph.get_Node_Hash().get(node).getLocation().x();
		}
		Range rx=new Range(min,max);
		return rx;
	}
	
	public Range rangeY() {
		double max=Integer.MIN_VALUE;
		double min=Integer.MAX_VALUE;

		Iterator<Integer> it = Graph.get_Node_Hash().keySet().iterator();
		while (it.hasNext()) {
			Integer node=it.next();
			if(Graph.get_Node_Hash().get(node).getLocation().y()>max)
				max=Graph.get_Node_Hash().get(node).getLocation().y();
			else if(Graph.get_Node_Hash().get(node).getLocation().y()<min)
				min=Graph.get_Node_Hash().get(node).getLocation().y();
		}
		Range ry=new Range(min,max);
		return ry;
	}

}




