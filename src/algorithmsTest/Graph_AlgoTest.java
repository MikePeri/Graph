package algorithmsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {
	
	graph ACTUAL;
	DGraph EXPEXTED=new DGraph();
	Point3D p0=new Point3D(0,0);
	Point3D p1=new Point3D(1,1);
	Point3D p2=new Point3D(2,2);
	Point3D p3=new Point3D(3,3);
	Point3D p4=new Point3D(4,4);
	{
		this.EXPEXTED.addNode(new NodeData(0, p0));
		this.EXPEXTED.addNode(new NodeData(1, p1));
		this.EXPEXTED.connect(0, 1, 1);
		this.EXPEXTED.addNode(new NodeData(2, p2));
		this.EXPEXTED.connect(1, 2, 2);
		this.EXPEXTED.addNode(new NodeData(3, p3));
		this.EXPEXTED.connect(2, 3, 3);
		this.EXPEXTED.addNode(new NodeData(4, p4));
		this.EXPEXTED.connect(3, 4, 4);
		this.EXPEXTED.connect(4, 0, 4);
	}
	Graph_Algo graph_algo= new Graph_Algo( this.EXPEXTED);
	//@Test
	void testInitGraph() {
		fail("Not yet implemented");
	}

//	@Test
	void testInitString() {
		fail("Not yet implemented");
	}

	//@Test
	void testSave() {
		
	}

	@Test
	void testIsConnected() {
		boolean e=this.graph_algo.isConnected();
		assertTrue(e,"ERR:Failed to return false when graph is not connected");
		
	}

//	@Test
	void testShortestPathDist() {
		double actual=this.graph_algo.shortestPathDist(0, 4);
		double expected=4;
		assertEquals(expected, actual,"ERR: Failing to get length of shortest path distination");
	}//testShortestPathDist

//	@Test
	void testShortestPath() {
		fail("Not yet implemented");
	}

//	@Test
	void testTSP() {
		fail("Not yet implemented");
	}

//	@Test
	void testCopy() {
		this.ACTUAL=new DGraph((DGraph) this.EXPEXTED);
		assertEquals(this.EXPEXTED, this.ACTUAL,"ERR: Failing to copy Graph");
	}//testCopy

}
