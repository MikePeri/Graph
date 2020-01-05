# Graph

This project's main purpose is to create a directed graph with a road map in mind - resembling the waze algorithm.

After you clone this project into your workspace, you can make your own directed graph. Make sure to download all the packages.
For each package, there will be a test package, where you can understang how to use thier respective classes. 

For the gui part, you can initialize an empty window, and by clicking on the "Actions" option, 
you can create your own Directed Graph through it.

Afterwards, by clicking on the "Options" in the menu, you can execute three types of algorithms: 

1. isConnected, which returns true if and only if there is a valid path from EVREY node to each.

2. shortestPath, which returns the the shortest path between src to dest - as an ordered List of nodes.

3. TSP, which computes a relatively short path which visit each node in the targets List.

If you already have a graph saved, you can open it by clicking "file"->"open a file". By clicking on "save to file", you can save the 
graph you created.
