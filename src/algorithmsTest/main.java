package algorithmsTest;

import java.util.ArrayList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DGraph EXPECTED=new DGraph();
		Point3D p0=new Point3D(-25,0);
		Point3D p1=new Point3D(20,20);
		
		Point3D p2=new Point3D(-13,19);
		Point3D p3=new Point3D(19.5,35);
		
		Point3D p4=new Point3D(-20,-30);
		Point3D p5=new Point3D(60,30);
		
		Point3D p6=new Point3D(-60,-50);
		Point3D p7=new Point3D(60,50);

		EXPECTED.addNode(new NodeData(0, p0));
		EXPECTED.addNode(new NodeData(1, p1));
		EXPECTED.addNode(new NodeData(2, p2));
		EXPECTED.addNode(new NodeData(3, p3));
		EXPECTED.addNode(new NodeData(4, p4));
		EXPECTED.addNode(new NodeData(5, p5));
		EXPECTED.addNode(new NodeData(6, p6));
		EXPECTED.addNode(new NodeData(7, p7));
		
		
		EXPECTED.connect(2, 0, 1);
		EXPECTED.connect(1, 2, 2);
		EXPECTED.connect(1, 3, 3);
		EXPECTED.connect(0, 1, 4);
		EXPECTED.connect(3, 0, 4);
		EXPECTED.connect(4, 0, 4);
		EXPECTED.connect(1, 0, 7);
		EXPECTED.connect(7, 4, 6);
		EXPECTED.connect(6, 7, 1);
		
		
		Graph_Algo algo= new Graph_Algo(EXPECTED);
		List<Integer> targets=new ArrayList<Integer>();
		targets.add(1);
		targets.add(4);
		targets.add(6);
		List<node_data> actual=algo.TSP(targets);
		System.out.println(actual);
		//System.out.println(algo.shortestPath(4, 6));
		
		
	}

}
