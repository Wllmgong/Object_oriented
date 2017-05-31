package graph;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

/**
 * Name:William Gong Section:0303 ID:113510311 Directory ID:wgong1 Honor Code:I
 * pledge on my honor that i have not give or received any unauthorized
 * assistance on this assignment
 * 
 * This project is about creating a graph using your own design and to implement
 * the dijaksra's theorem into the program. I design the program by creating a
 * map within a map. The first map's key contains the vertex for the map. The
 * value contains the second map. The second map's key is the edges directed to
 * the cost of the edges. The map contains all the value required to design a
 * graph. In addition, the graph is compatible with clique or no clique for all
 * of its methods.
 */
public class Graph<V extends Comparable<V>> {

	private HashMap<V, TreeMap<V, Integer>> vertMap;

	// the private field of map, the is used to create a graph
	// it uses a map within a map.
	/**
	 * the constructor, only used for creating the graph
	 */
	public Graph() {

		vertMap = new HashMap<V, TreeMap<V, Integer>>();
	}

	/**
	 * the addvertex method adds a new vertex to the graph. It is added as a new
	 * key to the first map. Throws illegalargumentexception when the vertex
	 * is already in the map. Throws null if vertex is null
	 * 
	 * @param vertex
	 * @throws IllegalArgumentException
	 */
	public void addVertex(V vertex) throws IllegalArgumentException {

		if (vertex == null)
			throw new NullPointerException();
		//checks nulll
		if (!(isVertex(vertex))) {
			TreeMap<V, Integer> temp = new TreeMap<V, Integer>();
			//create a new map to stores edges and cost
			vertMap.put(vertex, temp);
			//adds the vertex into the map/graph
		}
		else
			throw new IllegalArgumentException();
		//throws exception when the vertex is already in the graph
	}

	/**
	 * check is the given vertex is within the map.
	 * throws nullpointerexception if the vertex is null
	 * @param vertex
	 * @return
	 */
	public boolean isVertex(V vertex) {
		if (vertex == null)
			throw new NullPointerException();
		//checks null
		return vertMap.containsKey(vertex);
		//return true or false if the vertex in the graph
	}
/**
 * returns all the vectors in the graph
 * @return
 */
	public Collection<V> getVertices() {
		return vertMap.keySet();
		//return the vectors
	}
/**
 * it removes the specified vector from the graph. It also removes all the
 * edges that points to the specified vector. Throws nosuchelementexpcetion
 * when the vertex is not in the graph. Throws the nullpointerexception 
 * when the vertex is null.
 * @param vertex
 * @throws NoSuchElementException
 */
	public void removeVertex(V vertex) throws NoSuchElementException {
		if (vertex == null)
			throw new NullPointerException();
		//checks null

		if (isVertex(vertex)) {
			//check if the vertex is in the graph
			vertMap.remove(vertex);
			Set<V> temp = vertMap.keySet();
			for (V t : temp) {
				//check the edges are in the 2nd map
				if (vertMap.get(t).containsKey(vertex))
					vertMap.get(t).remove(vertex);
				//if the edges toward the removed vertex then edges are removed
			}

		} else
			throw new NoSuchElementException();
		//if the element is not in the graph, throws exception
	}
/**
 * It adds a one direction edge that connect the source to the dest. Then the 
 * the edges adds to the second map then add the cost to the value
 * if the source or dest is null, throws nullpointerexception
 * if cost is less than 0, throws illgalarguemntexception
 * @param source
 * @param dest
 * @param cost
 * @throws IllegalArgumentException
 */
	public void addEdge(V source, V dest, int cost)
			throws IllegalArgumentException {
		if (source == null || dest == null)
			throw new NullPointerException();
		//checks for null
		if (cost < 0)
			throw new IllegalArgumentException();
		//checks for less than 0
		if (!(isVertex(source)))
			addVertex(source);
		//check is vertex is in the graph, if not add the vertex
		if (!(isVertex(dest)))
			addVertex(dest);
		//check is vertex is in the graph, if not add the vertex

		vertMap.get(source).put(dest, cost);
		//add the edge to the graph, 2nd map pointed to the dest, with the cost

	}

	/**
	 * returns the edge cost for the specified vertex to the dest
	 * else return -1 when neither are in the graph
	 * @param source
	 * @param dest
	 * @return
	 */
	public int getEdgeCost(V source, V dest) {
		if (source == null || dest == null)
			throw new NullPointerException();
		//checks for null

		if (isVertex(source) && isVertex(dest))
			if (vertMap.get(source).containsKey(dest))
				return vertMap.get(source).get(dest);
		//finds the sources and dest in the graph
		return -1;
		//return -1 if one of the two are not in the graph

	}
/**
 * change the the edges cost for the graph
 * checks for null for the source and dest
 * if newcost is less than 0 then throws illegalargumentexception
 * if the sources or dest not in the graph, then throw nosuchelementexcpetion 
 * @param source
 * @param dest
 * @param newCost
 * @throws IllegalArgumentException
 * @throws NoSuchElementException
 */
	public void changeEdgeCost(V source, V dest, int newCost)
			throws IllegalArgumentException, NoSuchElementException {
		if (source == null || dest == null)
			throw new NullPointerException();
		//check for null
		if (newCost < 0)
			throw new IllegalArgumentException();
		//check for cost less than 0
		if (isVertex(source) && isVertex(dest)) {
			if (vertMap.get(source).containsKey(dest))
				vertMap.get(source).replace(dest, newCost);
			else
				throw new NoSuchElementException();
			//if its not an edge
		}
		//finds the vertexes and finds the cost and changes it to the new cost

		else
			throw new NoSuchElementException();
		//if the vertex is not in the graph

	}
/**
 * it removes the edge from the graph with the given source and dest
 * throw null if source or dest is null
 * throw nosuchelementexcpetion if souce or dest are not in the graph
 * @param source
 * @param dest
 * @throws NoSuchElementException
 */
	public void removeEdge(V source, V dest) throws NoSuchElementException {
		if (source == null || dest == null)
			throw new NullPointerException();
		//checks for null
		if (isVertex(source) && isVertex(dest))
			if (vertMap.get(source).containsKey(dest))
				vertMap.get(source).remove(dest);
			else
				throw new NoSuchElementException();
		//if its not an edge
		else
			throw new NoSuchElementException();
		//if the vertex is not in the graph
	}
/**
 * finds the neighbors of the given vertex using the edges
 * returns nullpointerexception if the vertex is null
 * throw illegalargumentexception when the vertex doesnt exist
 * @param vertex
 * @return
 * @throws IllegalArgumentException
 */
	public Collection<V> getNeighbors(V vertex) throws IllegalArgumentException {
		if (vertex == null)
			throw new NullPointerException();
		//check for null
		if (isVertex(vertex)) {
			return vertMap.get(vertex).keySet();
		}
		//checks the vertex 
		throw new IllegalArgumentException();
		//checks for the edges
	}
/**
 * finds the vertex of the predecessors by using the edges
 * if vertex is null then throws nullpointerexcption
 * if the vertex doesnt exist then throws illegalargumentexcpetion
 * @param vertex
 * @return
 * @throws IllegalArgumentException
 */
	public Collection<V> getPredecessors(V vertex)
			throws IllegalArgumentException {
		if (vertex == null)
			throw new NullPointerException();
		//checks for null
		Set<V> collect = new HashSet<V>();
		Set<V> temp = vertMap.keySet();
		for (V k : temp) {
			Set<V> keyTemp = vertMap.get(k).keySet();
			for (V t : keyTemp)
				if (t.equals(vertex))
					collect.add(k);
		}
		//finds the predecessors
		return collect;
		//return the set
	}
/**
 * this method checks the graph to show that every vertex points to every
 * other vertex. To show that the graph is clique
 *  
 * @return
 */
	public boolean isClique() {
		Set<V> temp = vertMap.keySet();
		
		for (V k : temp) {
			//go through all of the vertex
			HashSet<V> collect = new HashSet<V>();
			for (V t : vertMap.get(k).keySet()) {
				collect.add(t);
				//finds all the edges for the vertex
				//then place them in a set
			}
			collect.add(k);
			if (!(collect.containsAll(temp))) {
				return false;
				//return false, if its doesnt contain all of the edges except
				//for the last one
			}

		}
		return true;

	}
/**
 * the dijkstra's theorem finds the shortest path to the vertex. It first finds
 * all of the neighbors of the sourceVertex, then from the shortest path, it 
 * finds the path to the other methods. It then repeats until all of the vectors
 * have been checked. Then from the stored information, it contains the shortest 
 * path to all of the vertexes and it contains the shortest distance between
 * the vectors.
 * @param sourceVertex
 * @param destVertex
 * @param shortestPath
 * @return
 * @throws IllegalArgumentException
 */
	public int dijkstra(V sourceVertex, V destVertex, List<V> shortestPath)
			throws IllegalArgumentException {
		if (sourceVertex == null || destVertex == null)
			throw new NullPointerException();
		//checks null for the vertexes
		if (!(vertMap.containsKey(sourceVertex) || vertMap
				.containsKey(destVertex)))
			throw new IllegalArgumentException();
		//check if the vertexes are in the graph

		TreeMap<V, Integer> process = new TreeMap<V, Integer>();
		//contains the cost for each path
		TreeMap<V, V> predessor = new TreeMap<V, V>();
		//contain the Predecessor for the vector
		TreeSet<V> checkProcess = new TreeSet<V>();
		//follows the process for the vectors
		Set<V> tempt = new HashSet<V>(vertMap.keySet());

		for (V k : tempt) {
			predessor.put(k, null);
			checkProcess.add(k);
		}
		//fills the predessor with nulls and the vectors
		//fills the checkProcess with the same
		process.put(sourceVertex, 0);
		//the sourceVectex's distance is always 0
		checkProcess.remove(sourceVertex);
		//no need to check the sourceVertex

		Set<V> temp = (Set<V>) getNeighbors(sourceVertex);
		//finds the neighbors
		for (V t : temp) {
			process.put(t, vertMap.get(sourceVertex).get(t));
			predessor.put(t, sourceVertex);
		}
		//finds the neighbors
		predessor.put(sourceVertex, null);
		//place the predessor of the source as null

		while (!(checkProcess.isEmpty())) {
			//the while loop goes through the 
			V check = checkProcess.first();
			//adds the value to check
			int dist = (int) Double.MAX_VALUE;
			//set dist as max value
			for (V n : checkProcess) {
				if (process.containsKey(n))
					if (dist > process.get(n)) {
						dist = process.get(n);
						check = n;
					}
			}
			//finds the distance in the stored distance, if its in there
			//if not then it is added later
			checkProcess.remove(check);
			//remove the vectors, so it wont repeat
			for (V o : vertMap.get(check).keySet()) {
				if (!(o.equals(check)) && checkProcess.contains(o)) {
					if (process.containsKey(o)) {
						if (process.get(o) > (vertMap.get(check).get(o)+dist)) {
							process.put(o, vertMap.get(check).get(o));
							predessor.put(o, check);

						}
						//if the distance is smaller then add new distance
					} else {
						process.put(o, vertMap.get(check).get(o));
						predessor.put(o, check);
					}
					//if there isnt already a distance then add new
				}

			}

		}

		V r = destVertex;
		ArrayList<V> arr = new ArrayList<V>();
//adds the predessor for the desVertex together
		if (r != null) {
			arr.add(r);
			while (!(predessor.get(r) == (null))) {
				V p = predessor.get(r);
				arr.add(p);
				r = p;
			}
			//add predessors to the array
		}
		int distance = 0;
		System.out.println(process.toString());
		System.out.println(predessor.toString());
		for (int i = arr.size() - 1; i >= 0; i--) {
			shortestPath.add(arr.get(i));
			System.out.println(shortestPath);
			distance = distance + process.get(arr.get(i));
		}
		//reverse the ordering of the list, so that it shows from
		//the source to the dest
		//adds the distance together
		return distance;
		//return distance
	}

}
