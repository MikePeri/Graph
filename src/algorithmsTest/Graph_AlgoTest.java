package algorithmsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Queue;

import java.util.List;


import org.junit.jupiter.api.Test;


import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

class Graph_AlgoTest {

	DGraph ACTUAL;
	DGraph EXPECTED;
	Graph_Algo graphAlgo;

	public void graph_Factory()
	{
		this.EXPECTED=new DGraph();
		Point3D p0=new Point3D(0,0);
		Point3D p1=new Point3D(1,1);
		Point3D p2=new Point3D(2,2);
		Point3D p3=new Point3D(3,3);
		Point3D p4=new Point3D(4,4);

		this.EXPECTED.addNode(new NodeData(0, p0));
		this.EXPECTED.addNode(new NodeData(1, p1));
		this.EXPECTED.addNode(new NodeData(2, p2));
		this.EXPECTED.addNode(new NodeData(3, p3));
		this.EXPECTED.addNode(new NodeData(4, p4));

	}


	//@Test
	void testInitGraph() {
		fail("Not yet implemented");
	}


	@Test
	void testSaveAndInit() {
		graph_Factory();
		this.EXPECTED.connect(0, 1, 1);
		this.EXPECTED.connect(1, 2, 2);
		this.EXPECTED.connect(2, 3, 3);
		this.EXPECTED.connect(3, 4, 4);
		this.EXPECTED.connect(0, 4, 4);
		this.graphAlgo= new Graph_Algo((DGraph) this.EXPECTED);
		this.graphAlgo.save("saveTest.txt");
		Graph_Algo expected=new Graph_Algo();
		expected.init("saveTest.txt");
		assertEquals(expected, graphAlgo,"ERR:Failing saving to init and save to file correctly.");
	}//testSave
	@Test
	void equalsTest() {
		graph_Factory();
		this.EXPECTED.connect(0, 1, 1);
		this.EXPECTED.connect(1, 2, 2);
		this.EXPECTED.connect(2, 3, 3);
		this.EXPECTED.connect(3, 4, 4);
		this.EXPECTED.connect(0, 4, 4);
		this.graphAlgo= new Graph_Algo((DGraph) this.EXPECTED);
		Graph_Algo g=new Graph_Algo(EXPECTED);
		assertEquals(graphAlgo, g);
	}//equalsTest
	@Test
	void testIsConnected() {
		graph_Factory();
		this.EXPECTED.connect(0, 1, 1);
		this.EXPECTED.connect(1, 2, 2);
		this.EXPECTED.connect(2, 3, 3);
		this.EXPECTED.connect(3, 4, 4);
		this.EXPECTED.connect(4, 0, 4);
		this.graphAlgo= new Graph_Algo((DGraph) this.EXPECTED);
		
		boolean e=this.graphAlgo.isConnected();
		assertTrue(e,"ERR:Failed to return false when graph is not connected");

	}

		@Test
	void testShortestPathDist() {

		graph_Factory();
		//System.out.println(this.EXPECTED.get_Node_Hash().containsKey(0));
		this.EXPECTED.connect(0, 1, 1);
		this.EXPECTED.connect(1, 2, 2);
		this.EXPECTED.connect(2, 3, 3);
		this.EXPECTED.connect(3, 4, 4);
		this.EXPECTED.connect(0, 4, 11);
		this.graphAlgo= new Graph_Algo((DGraph) this.EXPECTED);
		//System.out.println(this.EXPECTED.get_Edge_Hash().get(0).values());
		double actual=this.graphAlgo.shortestPathDist(0, 4);
		double expected=10;
		assertEquals(expected, actual,"ERR: Failing to get length of shortest path distination. Expected: "+expected+" Actual: "+actual);
	}//testShortestPathDist

	@Test
	void testShortestPath() {
		graph_Factory();
		this.EXPECTED.connect(0, 1, 1);
		this.EXPECTED.connect(1, 2, 2);
		this.EXPECTED.connect(2, 3, 3);
		this.EXPECTED.connect(3, 4, 4);
		this.EXPECTED.connect(0, 4, 4);
		this.graphAlgo= new Graph_Algo((DGraph) this.EXPECTED);
		List<node_data> expected=new ArrayList<node_data>();
		expected.add(this.EXPECTED.get_Node_Hash().get(0));
		expected.add(this.EXPECTED.get_Node_Hash().get(4));
		//Need to save in lexographic way
		List<node_data> actual=this.graphAlgo.shortestPath(0, 4);
		assertEquals(expected, actual,"ERR: Failing to save the shortest path. Expected: "+expected+" Actual: "+actual);
	}

	@Test
	void testTSP() {
		graph_Factory();
		this.EXPECTED.connect(0, 1, 1);
		this.EXPECTED.connect(1, 0, 2);
		this.EXPECTED.connect(0, 2, 3);
		this.EXPECTED.connect(2, 0, 4);
		this.EXPECTED.connect(0, 3, 4);
		this.graphAlgo= new Graph_Algo(this.EXPECTED);
		List<Integer> targets=new ArrayList<Integer>();
		targets.add(0);
		targets.add(1);
		targets.add(2);
		targets.add(3);
		//System.out.println(targets);
		List<node_data> actual=this.graphAlgo.TSP(targets);
		List<node_data> expected=new ArrayList<node_data>();
		//System.out.println(actual);
	}

		@Test
	void testCopy() {
		graph_Factory();
		this.ACTUAL=new DGraph(this.EXPECTED);
		assertEquals(this.EXPECTED, this.ACTUAL,"ERR: Failing to copy Graph");
	}//testCopy
		
}
