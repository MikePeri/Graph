package algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


import com.google.gson.Gson;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{
	private DGraph Graph;
	private HashMap<node_data, node_data> predessesors;
	/**
	 * Constructor:
	 */
	public Graph_Algo ()
	{
		this.Graph=new DGraph(new DGraph());
	}//Graph_Algo
	public Graph_Algo (graph g)
	{
		init(g);
	}//Graph_Algo
	@Override
	public void init(graph g) {
		this.Graph=new DGraph((DGraph) g);
	}//init


	/**
	 * Init a graph from file
	 * @param file_name
	 */
	public void init(String file_name) {
		DGraph g = new DGraph(); 
	       
        try
        {    
            FileInputStream file = new FileInputStream(file_name); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            g = (DGraph)in.readObject();  
            in.close(); 
            file.close(); 
            this.Graph=new DGraph(g); 
            //System.out.println("Object has been deserialized"); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 

	}//init

	/** Saves the graph to a Json file.
	 * Using external jar of Gson - Google implementation
	 * @param file_name
	 */
	public void save(String file_name) {
		// TODO Auto-generated method stub
		try
        {    
            FileOutputStream file = new FileOutputStream(file_name); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(Graph); 
              
            out.close(); 
            file.close(); 
              
            //System.out.println("Object has been serialized"); 
        }//try   
        catch(IOException ex) 
        { 
        	System.out.println("IOException is caught"); 
//            ex.printStackTrace();
//            return;
        }//catch 
	}//save

	
	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return
	 */
	public boolean isConnected() {
		
		
		DGraph transpose = transpose(Graph);
		return (isConnectedHelp(this.Graph) && isConnectedHelp(transpose));
	}//isConnected
	
	public boolean isConnectedHelp(DGraph g) {

		Queue<Integer> finished=DFS(this.Graph);
		int count=0;
		Queue<Integer> forDFS=new LinkedList<>();

		//initialize
		Iterator<Integer> itr=g.get_Node_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int node=itr.next();
			g.getNode(node).setTag(1);
		}

		//check all the nodes: if they are still white, do dfsVisit
		while(finished.peek()!=null) {
			int node=finished.poll();
			count++;
			//if the node is white
			if(g.getNode(node).getTag()==1) {
				dfsVisit(g,node,forDFS);
				//remove all the nodes that are not white.
				while(finished.peek()!=null && g.get_Node_Hash().get(finished.peek()).getTag()!=1)
					finished.remove();
			}
		}
		if(count==1)
			return true;
		return false;
		
		
	}
/**
 * 
 * @param g
 * @return
 */
	public Queue<Integer> DFS(DGraph g) {
		Queue<Integer> finish = new LinkedList<>();

		//initialize
		Iterator<Integer> itr=g.get_Node_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int node=itr.next();
			g.getNode(node).setTag(1);
		}//while
		//check all the nodes: if they are still white, do dfsVisit
		itr=g.get_Node_Hash().keySet().iterator();
		while(itr.hasNext()) {
			int node=itr.next();
			//if the node is white
			if(g.getNode(node).getTag()==1)
				dfsVisit(g,node,finish);
		}//while


		return finish;
	}//DFS
	/**
	 * 
	 * @param g
	 * @param node
	 * @param finish
	 */
	public void dfsVisit(DGraph g,int node, Queue<Integer> finish){
		HashMap<Integer, edge_data> neighbors=g.get_Edge_Hash().get(node);

		//change color to gray
		g.get_Node_Hash().get(node).setTag(2);


		//if node doesn't have any neighbors
		if(neighbors==null) {
			finish.add(node);
			g.get_Node_Hash().get(node).setTag(3);
			return;
		}//if

		Iterator<Integer> itr=neighbors.keySet().iterator();
		while (itr.hasNext()) {
			int u=itr.next();
			//if the neighbors are white
			if(g.get_Node_Hash().get(u).getTag()==1)
				dfsVisit(g, u, finish);
		}//while
		finish.add(node);
		g.get_Node_Hash().get(node).setTag(3);
		return;

	}//dfsVisit


/**
 * 
 * @param g
 * @return
 */
	public static DGraph transpose(DGraph g) {
		//create a new graph with the same nodes but a new HashMap of edges
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

			}//while
		}//while

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
		if(!this.Graph.get_Node_Hash().containsKey(src) || !this.Graph.get_Node_Hash().containsKey(dest))
			throw new RuntimeException("ERR: One of the nodes aren't exist!");
		else if(src==dest)//If they are the same vertices
			return 0;
		else//We need to find the path if it's exist by Dijkstra Algo
		{
			Dijkstra(src,dest);
		}//else
		if(this.Graph.getNode(dest).getWeight()<Double.MAX_VALUE)
			return this.Graph.getNode(dest).getWeight();
		return -1;
	}//shortestPathDist
	/**
	 * 
	 * @param src - SRC node to start the Algo
	 */
	public void Dijkstra(int src,int dest)
	{
		setNodeInfinity(this.Graph.get_Node_Hash());
		Collection<node_data> sptSet=new ArrayList<node_data>();
		node_data start=this.Graph.getNode(src);
		start.setWeight(0);
		predessesors=new HashMap<node_data, node_data>();
		predessesors.put(start, null);
		//Second step
		while(sptSet.size()!=this.Graph.nodeSize())
		{
			//System.out.println("sptSet: "+sptSet+"\n");
			node_data min=chooseMin(this.Graph.getV(),sptSet);
			if(min.getKey()==dest)
				break;
			//System.out.println("key min node: "+min.getKey()+"\n");
			sptSet.add(min);
			//System.out.println("Before: "+this.Graph.get_Node_Hash().values());
			if(this.Graph.get_Edge_Hash().containsKey(min.getKey()))
			{
				//System.out.println("Neighbors of min: "+this.Graph.get_Edge_Hash().get(min.getKey()).values());
				relaxation(this.Graph.get_Edge_Hash().get(min.getKey()),min);
			}//if
			//System.out.println("After: "+this.Graph.get_Node_Hash().values()+"\n");
		}//while
	}//Dijkstra

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
		//System.out.println("("+src+","+dest+")= "+num);
		List<node_data> path=new ArrayList<node_data>();
		if(this.Graph.getNode(dest).getWeight()==Integer.MAX_VALUE)
			return path;

		node_data dst=this.Graph.getNode(dest);
		path.add(dst);
		while(dst.getKey()!=src)
		{
			dst=predessesors.get(dst);
			path.add(dst);
		}//while
		if(path.size()>0) {
			ArrayList<node_data> short_path=Reverse(path);
			//printForIlana(short_path);
			return short_path;
		}//if
		System.out.println("The isn't such a shortest path");
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
		double len=0;
		targets=removeDuplicate(targets);//O(n) remove all the duplicates
		if(targets.size()==0)
			return null;
		//If there is only one target
		else if(targets.size()==1)
		{
			node_data v=this.Graph.get_Node_Hash().get(targets.get(0));
			List<node_data> tsp=new ArrayList<node_data>();
			tsp.add(v);
			return tsp;
		}//else if
		//If there is only two targets: take the better path
		else if(targets.size()==2)
		{
			List<node_data> sp1=shortestPath(targets.get(0), targets.get(1));
			double sp1Len=sp1.get(sp1.size()-1).getWeight();
			List<node_data> sp2=shortestPath(targets.get(1), targets.get(0));
			double sp2Len =sp2.get(sp1.size()-1).getWeight();
			if(sp1.size()!=0 && sp2.size()!=0)
			{
				if(sp1Len>=sp2Len)
				{
					printPath(sp2,sp2Len);
					return sp2;
				}//if
				printPath(sp1,sp1Len);
				return sp1;
			}//if
			else if(sp1.size()!=0)
			{
				printPath(sp1,sp1Len);
				return sp1;
			}//if
			else if(sp2.size()!=0)
			{
				printPath(sp2,sp2Len);
				return sp2;
			}//else if
			else
				return null;
		}//else if
		else
		{
			for (int j = 0; j < 1000; j++) {
				List<node_data> sp=new ArrayList<node_data>();
				List<Integer> spKeys=new ArrayList<Integer>();
				//System.out.println("Target List: ");
				//System.out.println(targets);
				for (int i = 1; i < targets.size(); i++) {
					List<node_data> path=shortestPath(targets.get(i-1), targets.get(i));
					len+=this.Graph.get_Node_Hash().get(targets.get(i)).getWeight();
					//System.out.println("This path: "+path);
					if(path.isEmpty())//If there isnt shortest path next shuffle
						break;
					if(i==1)
						sp.addAll(path.subList(0,path.size()));
					else
						sp.addAll(path.subList(1,path.size()));
					spKeys.addAll(NodeToKeyConverter(path));
					//System.out.println("Whole path: "+sp);
					if (spKeys.containsAll(targets))
					{
						printPath(sp,len);
						return sp;
					}//if
				}//for
				len=0;
				Collections.shuffle(targets);
			}//for
			return null;
		}//else
	}//TSP

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
		int keyMin=Integer.MAX_VALUE;
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
	 * @param hashMap - present fast access to neighbors
	 */
	private void relaxation(HashMap<Integer, edge_data> n,node_data min) {
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
			//System.out.println(edge.getSrc()+" weight: "+this.Graph.getNode(dstKey).getWeight());
		}//for
		//System.out.println("After: "+n.values());
	}//updateNeighbors

	/** 
	 * Set all the weight nodes graph to infinity.
	 * @param v - all the vertices
	 */
	private void setNodeInfinity(HashMap<Integer, node_data> v) {
		//		System.out.println(this.Graph.get_Node_Hash()+"\n");
		Collection<node_data> vertices=v.values();
		for (node_data node : vertices) {
			node.setWeight(Integer.MAX_VALUE);
		}//for
		//		System.out.println(this.Graph.get_Node_Hash());
	}//setNodeInfinity
	/**
	 * Reverse the List for proper shortest path
	 * @param path - The shortest path from the end
	 * @return The shortest path from the begining
	 */
	public ArrayList<node_data> Reverse(List<node_data> path)
	{
		ArrayList<node_data> reverse=new ArrayList<node_data>();
		for (int i = path.size()-1; i >=0; i--) {
			reverse.add(path.get(i));
		}//for
		return reverse;
	}//reverse
	/**
	 * Printing the List path
	 */
	public void printPath(List<node_data> path,double len)
	{
		for (node_data node : path) {
			System.out.print("->"+node.getKey());
		}//for
		System.out.println("\t Path len: "+len);
	}//printForIlana
	/**
	 * Equals Function
	 */
	@Override
	public boolean equals(Object a)
	{
		if(a instanceof graph_algorithms)
				return this.Graph.equals(((Graph_Algo) a).getGraph());
		return false;
	}//equals
	/**
	 * Getters:
	 * @return
	 */
	public DGraph getGraph()
	{
		return Graph;
	}//getGraph
	/**
	 * TSP help function
	 * @param list
	 * @return the list without duplicates
	 */
	private List<Integer> removeDuplicate(List<Integer> list)
	{
		List<Integer> set=new ArrayList<Integer>();
		for (Integer i : list) {
			if(!set.contains(i))
				set.add(i);
		}//for
		return set;
	}//removeDuplicate
	/**
	 * Convert List of nodes to their keys
	 * @param nodes
	 * @return Integer key list of given nodes
	 */
	private List<Integer> NodeToKeyConverter(List<node_data> nodes)
	{
		List<Integer> keys=new ArrayList<Integer>();
		for (node_data node : nodes) {
			keys.add(node.getKey());
		}//for
		return keys;
	}//NodeToKeyConverter
}
