package algorithmsTest;

import java.util.ArrayList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DGraph graph=new DGraph();
		Point3D p0=new Point3D(0,0);
		Point3D p1=new Point3D(1,1);
		Point3D p2=new Point3D(2,2);
		Point3D p3=new Point3D(3,3);
		Point3D p4=new Point3D(4,4);
		
		graph.addNode(new NodeData(0, p0));
		graph.addNode(new NodeData(1, p1));
		graph.addNode(new NodeData(2, p2));
		graph.addNode(new NodeData(3, p3));
		graph.addNode(new NodeData(4, p4));
		
		graph.connect(0, 1, 1);
		graph.connect(1, 0, 2);
		
		graph.connect(0, 2, 3);
		graph.connect(2, 0, 3);
		
		graph.connect(0, 3, 4);
		graph.connect(3, 0, 1);
		
		graph.connect(0, 4, 4);
		graph.connect(4, 0, 1);
		
//		graph.connect(0, 1, 1);
//		graph.connect(1, 2, 2);
//		graph.connect(2, 3, 3);
//		graph.connect(4, 2, 1);
//		graph.connect(0, 4, 4);
		
		Graph_Algo algo= new Graph_Algo(graph);
		List<Integer> targets=new ArrayList<Integer>();
		targets.add(3);
		targets.add(4);
		targets.add(0);
		targets.add(2);
		targets.add(1);
		List<node_data> actual=algo.TSP(targets);
		//System.out.println(actual);
	}

}
