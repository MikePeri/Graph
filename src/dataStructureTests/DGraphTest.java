package dataStructureTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class DGraphTest {
	graph g=new DGraph();
	String ACTUAL;
	String EXPECTED;
	

	@Test
	void testGetNode() {
		Point3D p=new Point3D(1,1,1);
		node_data n=new NodeData(21,p,21);
		g.addNode(n);
		ACTUAL=g.getNode(21).toString();
		EXPECTED=n.toString();
		assertEquals(ACTUAL,EXPECTED,"ERR: GetNode failed to compare between the same nodes");
		
	}

//	@Test
	void testGetEdge() {
		
	}

//	@Test
	void testAddNode() {
		fail("Not yet implemented");
	}

	@Test
	void testConnect() {
		Point3D p1=new Point3D(1,1,1);
		Point3D p2=new Point3D(0,0,0);
		node_data n1=new NodeData(2,p1,1);
		node_data n2=new NodeData(1,p2,2);
		g.addNode(n1);
		g.addNode(n2);
		g.connect(n1.getKey(), n2.getKey(), 3);
		edge_data e=new EdgeData(n1.getKey(), n2.getKey(), 3);
		EXPECTED=e.toString();
		ACTUAL=g.getEdge(n1.getKey(), n2.getKey()).toString();
		assertEquals(ACTUAL,EXPECTED,"ERR: connect failed to compare between the same edges");

	}

//	@Test
	void testGetV() {
		fail("Not yet implemented");
	}

//	@Test
	void testGetE() {
		fail("Not yet implemented");
	}

//	@Test
	void testRemoveNode() {
		fail("Not yet implemented");
	}

//	@Test
	void testRemoveEdge() {
		fail("Not yet implemented");
	}

//	@Test
	void testNodeSize() {
		fail("Not yet implemented");
	}

//	@Test
	void testEdgeSize() {
		fail("Not yet implemented");
	}

//	@Test
	void testGetMC() {
		fail("Not yet implemented");
	}

}
