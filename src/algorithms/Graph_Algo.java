package algorithms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import dataStructure.DGraph;
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
		}//cathc
		
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

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph g= new DGraph(this.Graph);
		return g;
	}//copy

}
