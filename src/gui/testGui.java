package gui;

import dataStructure.DGraph;
import dataStructure.NodeData;
import utils.Point3D;

public class testGui {

	public static void main(String[] args) {
		DGraph EXPECTED=new DGraph();
		Point3D p0=new Point3D(0,0);
		Point3D p1=new Point3D(10,10);
		Point3D p2=new Point3D(-20,20);
		Point3D p3=new Point3D(30,-30);
		Point3D p4=new Point3D(-40,-300);
		Point3D p5=new Point3D(15,60);

		EXPECTED.addNode(new NodeData(0, p0));
		EXPECTED.addNode(new NodeData(1, p1));
		EXPECTED.addNode(new NodeData(2, p2));
		EXPECTED.addNode(new NodeData(3, p3));
		EXPECTED.addNode(new NodeData(4, p4));
		EXPECTED.addNode(new NodeData(5, p5));
		
		EXPECTED.connect(0, 1, 1);
		EXPECTED.connect(1, 2, 2);
		EXPECTED.connect(2, 3, 3);
		EXPECTED.connect(3, 4, 4);
		EXPECTED.connect(0, 4, 4);
		EXPECTED.connect(1, 0, 7);
		EXPECTED.connect(0, 5, 6);
		EXPECTED.connect(2, 5, 1);
		
		Graph_GUI GraphGUI=new Graph_GUI(EXPECTED);
		GraphGUI.drawGraph(500, 500);

	}

}
