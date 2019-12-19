package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;


public class DGraph implements graph{
	private HashMap<Integer, node_data> Node_Hash;
	private HashMap<Integer, HashMap<Integer, edge_data>> Edge_Hash;
	private int ModeCount;
	private int EdgeHashSize;

	/**
	 * Empty Constructor
	 */
	public DGraph() {
		this.Node_Hash=new HashMap<Integer, node_data>();
		this.Edge_Hash=new HashMap<Integer, HashMap<Integer, edge_data>>();
		this.ModeCount=0;
		this.EdgeHashSize=0;
	}

	public DGraph(DGraph g) {
		
		Node_Hash = (HashMap<Integer, node_data>) g.get_Node_Hash().clone();
		Edge_Hash = (HashMap<Integer, HashMap<Integer, edge_data>>) g.get_Edge_Hash().clone();
		ModeCount = g.getMC();
		EdgeHashSize = g.edgeSize();
	}

	/**
	 * Getters.
	 */
	public HashMap<Integer, node_data> get_Node_Hash(){
		return this.Node_Hash;
	}
	public HashMap<Integer, HashMap<Integer, edge_data>> get_Edge_Hash(){
		return this.Edge_Hash;
	}


	/**
	 * Return the node_data by the node_id,
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

		if(this.Edge_Hash.containsKey(src)) {
			HashMap<Integer,edge_data> h=Edge_Hash.get(src);
			if(h.containsKey(dest)) {
				return h.get(dest);
			}
		}	
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
		//Wrong inputs
		if(src==dest)
			throw new RuntimeException("ERR: This isn't multy graph");
		if(w<=0)
			throw new RuntimeException("ERR: Weight cannot be 0 or negetive");

		//if the nodes don't exist
		if(!Node_Hash.containsKey(dest) || !Node_Hash.containsKey(src))
			throw new RuntimeException("ERR:Cannot connect between unknown node");

		EdgeData edge=new EdgeData(src,dest,w);
		//if the src exist in the Edge_Hash
		if(this.Edge_Hash.containsKey(src)) {
			//if we want to change an existing edge, remove it and add it later
			if(getEdge(src,dest)!=null){
				this.Edge_Hash.get(src).remove(dest);
			}//if

			this.Edge_Hash.get(src).put(dest, edge);
			this.ModeCount++;
		}//if
		else {//if the src doesn't exist in the Edge_Hash
			HashMap<Integer, edge_data> h=new HashMap<Integer,edge_data>();
			h.put(dest, edge);
			this.Edge_Hash.put(src, h);			
		}
		this.EdgeHashSize++;
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
		return this.Edge_Hash.get(node_id).values();
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
		if(!this.Edge_Hash.isEmpty()) {
			//remove all the edges that key is the source:
			this.EdgeHashSize-=this.Edge_Hash.get(key).size();
			this.Edge_Hash.remove(key);

			//remove all the edges that key is the destination:
			Iterator<Integer> it = this.Edge_Hash.keySet().iterator();
			while (it.hasNext()) {
				Integer src = it.next();
				if(this.Edge_Hash.get(src).get(key)!=null) {
					this.Edge_Hash.get(src).remove(key);
					this.EdgeHashSize--;
				}//if
			}//while
		}//if
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
		this.ModeCount++;
		this.EdgeHashSize--;
		return this.Edge_Hash.get(src).remove(dest);
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
		return this.EdgeHashSize;
	}//edgeSize


	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	public int getMC() {
		return this.ModeCount;
	}//getMC

}
