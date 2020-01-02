package gui;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.Range;

public class Graph_GUI extends JFrame implements ActionListener, MouseListener,Runnable
{
	private DGraph graph;
	private int width;
	private int height; 
	private Range rx;
	private Range ry;
	private Integer mc;

	//this variable is to decide whether to add node by clicking or not: 
	private boolean state;

	public Graph_GUI()
	{
		this(new DGraph());
	}

	public Graph_GUI(DGraph g)
	{
		width=800;
		height=600;
		graph=new DGraph(g);
		rx=this.rangeX();
		ry=this.rangeY();
		state=false;
		mc=graph.getMC();
		initGUI();
	}


	private void initGUI() {
		//declare all the options 
		Menu file, options, Action, select;  
		MenuItem save, isConnected, ShortestPath, TSP, AddLine, AddPoint, SelectNode, SelectEdge;  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuBar mb=new MenuBar();  

		//what will be on the menu bar
		file=new Menu("File");  
		options=new Menu("Options"); 
		Action=new Menu("Action");
		select=new Menu("Select");



		//what will be the menu items
		save=new MenuItem("Save to file");  
		save.addActionListener(this);
		
		isConnected=new MenuItem("Is Connected?");  
		isConnected.addActionListener(this);
		
		ShortestPath=new MenuItem("Shortest Path"); 
		ShortestPath.addActionListener(this);
		
		TSP=new MenuItem("TSP");  
		TSP.addActionListener(this);
		
		AddLine=new MenuItem("Connect");  
		AddLine.addActionListener(this);
		
		AddPoint=new MenuItem("Add a node");
		AddPoint.addActionListener(this);
		
		SelectNode=new MenuItem("Select Node");
		SelectNode.addActionListener(this);
		
		SelectEdge=new MenuItem("Select Edge");   
		SelectEdge.addActionListener(this);


		//for each option in the menu bar, add its item
		file.add(save);

		options.add(isConnected);
		options.add(ShortestPath);  
		options.add(TSP);

		Action.add(AddLine);  
		Action.add(AddPoint); 

		select.add(SelectEdge);
		select.add(SelectNode);

		mb.add(file); 
		mb.add(options); 
		mb.add(Action); 
		mb.add(select); 

		//set the window
		this.setMenuBar(mb);  
		this.setSize(width,height);  
		this.setBackground(Color.WHITE);
		this.setLayout(null);  
		this.setVisible(true);  

		this.addMouseListener(this);
		repaint();
	}




	public void paint(Graphics g){
		super.paint(g);

		double proportionX=width/rx.get_length();
		double proportionY=(0-height)/ry.get_length();

		g.setColor(Color.BLACK);

		Iterator<Integer> it = graph.get_Node_Hash().keySet().iterator();
		while (it.hasNext()) {
			//g.setColor(Color.BLACK);
			Integer v = it.next();
			Point3D src=graph.get_Node_Hash().get(v).getLocation();
			int x0= (int) ((src.x()-rx.get_min())*proportionX);
			int y0=(int) ((src.y()-ry.get_max())*proportionY);
			//draw all the nodes
			g.fillOval(x0, y0, 5, 5);
			g.drawString(Integer.toString(graph.get_Node_Hash().get(v).getKey()), x0, y0+20);

			//it is in try and catch because not all the nodes are in the edge list
			//so it might throw an error, we would like to avoid it
			try {
				Iterator<Integer> neighbors = graph.get_Edge_Hash().get(v).keySet().iterator();
				while(neighbors.hasNext()) {

					Integer u=neighbors.next();

					Point3D dest=graph.get_Node_Hash().get(u).getLocation();
					int x1=(int) ((dest.x()-rx.get_min())*proportionX);
					int y1=(int) ((dest.y()-ry.get_max())*proportionY);

					if(graph.get_Edge_Hash().get(v).get(u).getInfo()!=null &&
							graph.get_Edge_Hash().get(v).get(u).getInfo().equals("Selected")) {
						//g.setFont(Font.decode("BOLD"));
						g.setColor(Color.RED);
					}//if

					g.drawLine(x0, y0, x1, y1);
					g.drawString(Integer.toString(graph.get_Node_Hash().get(u).getKey()), x1, y1+20);

					//add the weight of the edge
					g.drawString(Double.toString(graph.get_Edge_Hash().get(v).get(u).getWeight()),
							x1*3/4+x0*1/4, y1*3/4+y0*1/4);

					//indicate the direction
					//					g.setColor(Color.RED);
					//					g.fillOval(x1*7/8+x0*1/8, y1*7/8+y0*1/8, 8, 8);
					g.setColor(Color.BLACK);

				}//Inner while
			}//try
			catch(Exception e){//don't do anything
			}//catch

		}//while
		
		//set info to null
		it = graph.get_Edge_Hash().keySet().iterator();
		while (it.hasNext()) {
			int v=it.next();
			Iterator<Integer> neighbors = graph.get_Edge_Hash().get(v).keySet().iterator();
			while(neighbors.hasNext()) {
				int u=neighbors.next();
				graph.get_Edge_Hash().get(v).get(u).setInfo(null);
			}//while
		}//while

		
	}//paint

	/**
	 * Finding the limits of x coordinate for Screen creator
	 * @return
	 */
	private Range rangeX() {
		double max=Integer.MIN_VALUE;
		double min=Integer.MAX_VALUE;

		Iterator<Integer> it = graph.get_Node_Hash().keySet().iterator();
		while (it.hasNext()) {
			Integer node=it.next();
			if(graph.get_Node_Hash().get(node).getLocation().x()>max)
				max=graph.get_Node_Hash().get(node).getLocation().x();
			else if(graph.get_Node_Hash().get(node).getLocation().x()<min)
				min=graph.get_Node_Hash().get(node).getLocation().x();
		}//while
		max=max*6/4;//?
		min=min*6/4;//?
		Range rx=new Range(min,max);
		return rx;
	}//RangeX
	/**
	 * Finding the limits of x coordinate for Screen creator
	 * @return
	 */
	private Range rangeY() {
		double max=Integer.MIN_VALUE;
		double min=Integer.MAX_VALUE;

		Iterator<Integer> it = graph.get_Node_Hash().keySet().iterator();
		while (it.hasNext()) {
			Integer node=it.next();
			if(graph.get_Node_Hash().get(node).getLocation().y()>max)
				max=graph.get_Node_Hash().get(node).getLocation().y();
			else if(graph.get_Node_Hash().get(node).getLocation().y()<min)
				min=graph.get_Node_Hash().get(node).getLocation().y();
		}//while
		max=max*6/4;
		min=min*6/4;
		Range ry=new Range(min,max);
		return ry;
	}//rangeY




	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();

		if(str.equals("Save to file")) {
			String s1=JOptionPane.showInputDialog(this, "Please write the file name:");
			s1+=".txt";
			Graph_Algo g=new Graph_Algo(graph);	
			System.out.println("Save to file action");
			repaint();
		}//if

		else if(str.equals("Is Connected?"))
		{
			Graph_Algo g=new Graph_Algo(graph);	
			JOptionPane.showMessageDialog(null, g.isConnected());
			System.out.println("Is connected? action");
			repaint();
		}//else if

		else if(str.equals("Shortest Path")) {

			String s1=JOptionPane.showInputDialog(this, "Type in the ID of the source node:");
			int src=Integer.parseInt(s1);
			String s2=JOptionPane.showInputDialog(this, "Type in the ID of the destination node:");
			int dest=Integer.parseInt(s2);
			List<node_data> path=new ArrayList<node_data>();
			Graph_Algo g=new Graph_Algo(graph);	
			path=g.shortestPath(src, dest);

			for (int i = 0; i < path.size()-1; i++) {
				graph.get_Edge_Hash().get(path.get(i).getKey()).get(path.get(i+1).getKey()).setInfo("Selected");	
			}//for
			repaint();

		}

		else if(str.equals("TSP")) {
			String s1=JOptionPane.showInputDialog(this, "Enter the number of nodes for TSP:");
			int num=Integer.parseInt(s1);
			int i=0;
			List<Integer> targets=new ArrayList<Integer>();
			while(i<num) {
				String s2=JOptionPane.showInputDialog(this, "Type the ID of the node:");
				int node=Integer.parseInt(s2);
				targets.add(node);			
				i++;
			}//while
			Graph_Algo g=new Graph_Algo(graph);	
			List<node_data> tsp=g.TSP(targets);
			for (i = 0; i < tsp.size()-1; i++) {
				graph.get_Edge_Hash().get(tsp.get(i).getKey()).get(tsp.get(i+1).getKey()).setInfo("Selected");	
			}//for
			repaint();
		}//else if

		else if(str.equals("Connect")) {
			String s1=JOptionPane.showInputDialog(this, "Type in the ID of the source node:");
			int src=Integer.parseInt(s1);
			String s2=JOptionPane.showInputDialog(this, "Type in the ID of the destination node:");
			int dest=Integer.parseInt(s2);
			String weight=JOptionPane.showInputDialog(this, "Type in the weight of this edge:");
			int w=Integer.parseInt(weight);
			graph.connect(src, dest, w);
			repaint();
		}//else if

		else if(str.equals("Add a node")) {
			state=true;
		}//else if

		else if(str.equals("Select Node")) {

		}//else if

		else if(str.equals("Select Edge")) {

		}//else if


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("mouseClicked");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(state==true) {
			double x = e.getX();
			double y = e.getY();
			x= x*rx.get_length()/width+rx.get_min();
			y= y*(-ry.get_length())/height+ry.get_max();
			Point3D p = new Point3D(x,y);

			node_data n;
			String s;
			//if user canceled
			try {
				s=JOptionPane.showInputDialog(this, "The coordinates are:"+ x+" , "+ y +", Type in Id:");
			} catch (Exception e2) {
				return;
			}

			int id = Integer.parseInt(s);
			n=new NodeData(id,p);
			graph.addNode(n);

			repaint();
			state=false;
		}//if

	}//mousePressed

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseReleased");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("mouseExited");
	}

	@Override
	public void run() {
		while(true)
		{
			
		}//while
	}//run
}
