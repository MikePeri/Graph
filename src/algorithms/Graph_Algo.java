package algorithms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import com.google.gson.Gson;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private DGraph Graph;
	@Override
	public void init(graph g) {
		
	}//init
	
	/**
	 * Init a graph from file
	 * @param file_name
	 */
	public void init(String file_name) {
		Gson gson=new Gson();
		try {
			FileReader reader=new FileReader(file_name);
			this.Graph=gson.fromJson(reader, DGraph.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}//catch
		
	}//init

	/** Saves the graph to a Json file.
	 * Using external jar of Gson - Google implementation
	 * @param file_name
	 */
	public void save(String file_name) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		String json=gson.toJson(Graph);
		
		try {
			PrintWriter pw=new PrintWriter(file_name);
			pw.write(json);
			pw.close();	}//try
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return; }//catch
	}

	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return
	 */
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * returns the length of the shortest path between src to dest 
	 * We are using Dikkstra Algorithm to find the length of the shortest path
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return If exist then positive Otherwise we will return -1
	 */
	public double shortestPathDist(int src, int dest) {
		boolean isDirectConnect=this.Graph.get_Edge_Hash().get(src).containsKey(dest);
		if(src==dest)//If they are the same vertices
			return 0;
		else if(isDirectConnect)//If they are directly connected
			return this.Graph.get_Edge_Hash().get(src).get(dest).getWeight();
		else//We need to find the path if it exist by Dijkstra Algo
		{
			Collection<node_data> sptSet=new ArrayList<node_data>();
			node_data start=this.Graph.getNode(src);
			start.setWeight(0);
			while(sptSet.size()!=this.Graph.nodeSize())
			{				
				node_data min=chooseMin(this.Graph.getV(),sptSet);
				sptSet.add(min);
				updateNeighbors(this.Graph.get_Edge_Hash().get(min),min);
			}//while
		}//else
		
		return -1;
	}//shortestPathDist


	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * Note: this is NOT the classical traveling salesman problem, 
	 * as you can visit a node more than once, and there is no need to return to source node - 
	 * just a simple path going over all nodes in the list. 
	 * @param targets
	 * @return
	 */
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph g= new DGraph(this.Graph);
		return g;
	}//copy
/**
 * Choose the next min vertices from the rest vertices
 * @param vertices - Whole vertices
 * @param sptSet - Short Path vertices
 * @return next min vertices
 */
	private node_data chooseMin(Collection<node_data> vertices, Collection<node_data> sptSet) {
		double min=Double.MAX_VALUE;
		int keyMin=0;
		for (node_data node : vertices) {
			if(!sptSet.contains(node) && node.getWeight()<min)
			{
				keyMin=node.getKey();
				min=node.getWeight();
			}//if
		}//for
		return this.Graph.getNode(keyMin);
	}//chooseMin
	/**
	 * Updating the value of the neighbors of given node data
	 * @param hashMap
	 */
	private void updateNeighbors(HashMap<Integer, edge_data> n,node_data min) {
		double minValue=min.getWeight();
		Collection<edge_data> neighbors=n.values();
		for (edge_data edge : neighbors) {
			int dstKey=edge.getDest();
			node_data dst=this.Graph.getNode(dstKey);
			double srcValue=dst.getWeight();//src value
			double edgeWeight=edge.getWeight();//Edge weight
			if(srcValue>(minValue+edgeWeight))//If this source value isnt the min then change.
				this.Graph.getNode(dstKey).setWeight(minValue+edgeWeight);
		}//for
	}//updateNeighbors
}
