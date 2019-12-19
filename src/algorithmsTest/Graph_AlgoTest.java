package algorithmsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {
	graph ACTUAL=new DGraph();
	Point3D p0=new Point3D(0,0);
	Point3D p1=new Point3D(1,1);
	Point3D p2=new Point3D(2,2);
	Point3D p3=new Point3D(3,3);
	Point3D p4=new Point3D(4,4);
	{
	ACTUAL.addNode(new NodeData(0, p0));
	ACTUAL.addNode(new NodeData(1, p1));
	ACTUAL.addNode(new NodeData(2, p2));
	ACTUAL.addNode(new NodeData(3, p3));
	ACTUAL.addNode(new NodeData(4, p4));
	}
	//@Test
	void testInitGraph() {
		fail("Not yet implemented");
	}

//	@Test
	void testInitString() {
		fail("Not yet implemented");
	}

//	@Test
	void testSave() {
		fail("Not yet implemented");
	}

//	@Test
	void testIsConnected() {
		fail("Not yet implemented");
	}

//	@Test
	void testShortestPathDist() {
		fail("Not yet implemented");
	}

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
		
	}//testCopy

}
