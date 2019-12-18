package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

public class DGraph implements graph{
	private HashMap<Integer, node_data> Node_Hash;
	private HashMap<String, edge_data> Edge_Hash;
	private int ModeCount;
	
	/**
	 * Empty Constructor
	 */
	public DGraph() {
		this.Node_Hash=new HashMap<Integer, node_data>();
		this.Edge_Hash=new HashMap<String, edge_data>();
		this.ModeCount=0;
	}
	
	
	
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	public node_data getNode(int key) {
		if(!Node_Hash.containsKey(key))
			return null;
		return Node_Hash.get(key);
	}

	/**
	 * return the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the edge (src,dest), null if none.
	 */
	public edge_data getEdge(int src, int dest) {
		if(!Node_Hash.containsKey(src)||!Node_Hash.containsKey(dest))
			return null;
		String id=String.valueOf(src)+"->"+String.valueOf(dest);
		if(this.Edge_Hash.containsKey(id))
			return this.Edge_Hash.get(id);
		return null;
	}//getEdge

	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	public void addNode(node_data n) {
		int key=n.getKey();
		if(n.getWeight()<0)
			throw new RuntimeException("ERR: Weight cannot be negative");
		if(Node_Hash.containsKey(key))
			throw new RuntimeException("ERR: This node already exist");
		Node_Hash.put(key,n);
		this.ModeCount++;
	}//addNode

	/**
	 * Connect an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	public void connect(int src, int dest, double w) {
		String key=keyParser(src, dest);
		if(src==dest)
			throw new RuntimeException("ERR: This isn't multy graph");
		if(w<=0)
			throw new RuntimeException("ERR: Weight cannot be 0 or negetive");
		//if the nodes don't exist
		if(!Node_Hash.containsKey(dest) || !Node_Hash.containsKey(src))
			throw new RuntimeException("ERR:Cannot connect between unknown node");
		
		//change the existing edge
		else if(getEdge(src,dest)!=null)
		{
			this.Edge_Hash.remove(key);
		}//else if
		
		
		EdgeData edge=new EdgeData(src,dest,w);
		this.Edge_Hash.put(key, edge);
		this.ModeCount++;
	}//connect

	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 */
	public Collection<node_data> getV() {
		return this.Node_Hash.values();
	}//getV

	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	public Collection<edge_data> getE(int node_id) {
		return this.Edge_Hash.values();
	}//getE

	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	public node_data removeNode(int key) {
		this.ModeCount++;
		return this.Node_Hash.remove(key);
	}//removeNode

	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	public edge_data removeEdge(int src, int dest) {
		String key=keyParser(src,dest);
		this.ModeCount++;
		return this.Edge_Hash.remove(key);
	}//removeEdge

	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	public int nodeSize() {
		return Node_Hash.size();
	}//nodeSize

	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	public int edgeSize() {
		return Edge_Hash.size();
	}//edgeSize
	

	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	public int getMC() {
		return this.ModeCount;
	}
	/**
	 * Parser from (int src,int dst) to String key
	 * @param src 
	 * @param dst
	 * @return The unique key for Edge Hash map
	 */
	private String keyParser(int src, int dst)
	{
		String id=String.valueOf(src)+"->"+String.valueOf(dst);
		return id;
	}//idParser

}
