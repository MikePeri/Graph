package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

public class DGraph implements graph{
	private HashMap<Integer, node_data> Node_Hash;
	private HashMap<Integer, ArrayList<EdgeData>> Neighbors_Hash;
	
	/**
	 * Constructors:
	 */
	public DGraph() {
		this.Node_Hash=new HashMap<Integer, node_data>();
		this.Neighbors_Hash=new HashMap<Integer, ArrayList<EdgeData>>();
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
		if(!Neighbors_Hash.containsKey(src))
			return null;
		
		ArrayList<EdgeData> n=Neighbors_Hash.get(src);
		for (int i = 0; i < n.size(); i++) {
			if(n.get(i).getDest()==dest)
				return n.get(i);
		}//for
		return null;
	}

	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	public void addNode(node_data n) {
		int key=n.getKey();
		if(Node_Hash.containsKey(key))
			throw new RuntimeException("ERR: this node is already exist");
		Node_Hash.put(key,n);
		ArrayList<EdgeData> empty=new ArrayList<EdgeData>();
		Neighbors_Hash.put(key,empty);
	}

	/**
	 * Connect an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	public void connect(int src, int dest, double w) {
		if(w<=0)
			throw new RuntimeException("Weight cannot be 0 or negetive");
		//if the nodes don't exist
		if(!Node_Hash.containsKey(dest) || !Node_Hash.containsKey(src))
			throw new RuntimeException("Cannot connect between unknown node");
		
		//change the existing edge
		else if(getEdge(src,dest)!=null)
		{
			Neighbors_Hash.remove(getEdge(src,dest));
		}//else if
		
		ArrayList<EdgeData> n=Neighbors_Hash.get(src);
		EdgeData e=new EdgeData(src,dest,w);
		n.add(e);
		
	}

	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 */
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	public node_data removeNode(int key) {
		
	}

	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	public edge_data removeEdge(int src, int dest) {
		edge_data e=getEdge(src,dest);
		Neighbors_Hash.remove(getEdge(src,dest));
		return e;
	}

	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	public int nodeSize() {
		return Node_Hash.size();
	}

	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
