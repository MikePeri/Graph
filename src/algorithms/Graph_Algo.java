package algorithms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	private HashMap<node_data, node_data> predessesors;
	/**
	 * Constructor:
	 */
	public Graph_Algo (DGraph graph)
	{
		this.Graph=new DGraph(graph);
	}//Graph_Algo
	@Override
	public void init(graph g) {

	}


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
		Queue<Integer> finished=DFS(this.Graph);
		DGraph transpose = transpose(Graph);
		int count=0;
		Queue<Integer> forDFS=new LinkedList<>();

		//initialize
		Iterator<Integer> itr=transpose.get_Node_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int node=itr.next();
			transpose.getNode(node).setTag(1);
		}

		//check all the nodes: if they are still white, do dfsVisit
		while(finished.peek()!=null) {
			int node=finished.poll();
			count++;
			//if the node is white
			if(transpose.getNode(node).getTag()==1) {
				dfsVisit(transpose,node,forDFS);
				//remove all the nodes that are not white.
				while(finished.peek()!=null && transpose.get_Node_Hash().get(finished.peek()).getTag()!=1)
					finished.remove();
			}
		}
		if(count==1)
			return true;


		return false;
	}

	public Queue<Integer> DFS(DGraph g) {
		Queue<Integer> finish = new LinkedList<>();

		//initialize
		Iterator<Integer> itr=g.get_Node_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int node=itr.next();
			g.getNode(node).setTag(1);
		}
		//check all the nodes: if they are still white, do dfsVisit
		itr=g.get_Node_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int node=itr.next();
			//if the node is white
			if(g.getNode(node).getTag()==1)
				dfsVisit(g,node,finish);
		}


		return finish;
	}

	public void dfsVisit(DGraph g,int node, Queue<Integer> finish){
		HashMap<Integer, edge_data> neighbors=g.get_Edge_Hash().get(node);

		//change color to gray
		g.get_Node_Hash().get(node).setTag(2);


		//if node doesn't have any neighbors
		if(neighbors==null) {
			finish.add(node);
			g.get_Node_Hash().get(node).setTag(3);
			return;
		}

		Iterator<Integer> itr=neighbors.keySet().iterator();
		while (itr.hasNext()) {
			int u=itr.next();
			//if the neighbors are white 
			if(g.get_Node_Hash().get(u).getTag()==1)
				dfsVisit(g, u, finish);			
		}
		finish.add(node);
		g.get_Node_Hash().get(node).setTag(3);
		return;

	}



	public static DGraph transpose(DGraph g) {
		DGraph trans=new DGraph(g.get_Node_Hash(),new HashMap<Integer, HashMap<Integer,edge_data>>());
		Iterator<Integer> itr=g.get_Edge_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int s=itr.next();
			HashMap<Integer,edge_data> src=g.get_Edge_Hash().get(s);
			Iterator<Integer> neighbors=src.keySet().iterator();
			while(neighbors.hasNext()) {
				int d=neighbors.next();
				double weight=g.get_Edge_Hash().get(s).get(d).getWeight();
				trans.connect(d, s, weight);

			}
		}

		return trans;
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
		if(!this.Graph.get_Node_Hash().containsKey(src) || !this.Graph.get_Node_Hash().containsKey(dest))
			throw new RuntimeException("ERR: One of the nodes aren't exist!");
		else if(src==dest)//If they are the same vertices
			return 0;
		else//We need to find the path if it's exist by Dijkstra Algo
		{
			//First Step
			setNodeInfinity(this.Graph.get_Node_Hash());
			Collection<node_data> sptSet=new ArrayList<node_data>();
			node_data start=this.Graph.getNode(src);
			start.setWeight(0);
			predessesors=new HashMap<node_data, node_data>();
			predessesors.put(start, null);
			//Second step
			while(sptSet.size()!=this.Graph.nodeSize())
			{
				System.out.println(sptSet+"\n");
				node_data min=chooseMin(this.Graph.getV(),sptSet);
				System.out.println(min.getLocation()+"\n");
				//				if(min.getKey()==dest)
				//					break;
				sptSet.add(min);
				updateNeighbors(this.Graph.get_Edge_Hash().get(min),min);
			}//while
		}//else
		if(this.Graph.getNode(dest).getWeight()<Double.MAX_VALUE)
			return this.Graph.getNode(dest).getWeight();
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
		double num=shortestPathDist(src, dest);
		List<node_data> path=new ArrayList<node_data>();
		node_data dst=this.Graph.getNode(dest);
		while(dst.getKey()!=src)
		{
			path.add(dst);
			dst=predessesors.get(dst);
		}//while
		return path;
	}//shortestPath

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
			{
				if(predessesors.containsKey(dst))
					predessesors.replace(dst, min);
				else
					predessesors.put(dst, min);
				this.Graph.getNode(dstKey).setWeight(minValue+edgeWeight);
			}//if
		}//for
	}//updateNeighbors

	/**
	 * Set for all the node infinity weight.
	 * @param v - all the vertices
	 */
	private void setNodeInfinity(HashMap<Integer, node_data> v) {
		//		System.out.println(this.Graph.get_Node_Hash()+"\n");
		Collection<node_data> vertices=v.values();
		for (node_data node : vertices) {
			node.setWeight(Double.MAX_VALUE);
		}//for
		//		System.out.println(this.Graph.get_Node_Hash());
	}//setNodeInfinity
}
