import java.util.*;

public class GraphImplementation implements Graph {
	public int[][] graph;
	public int vertices;

	//constructor that will return the number of vertices
	public GraphImplementation(int vertices){
		graph = new int[vertices][vertices];
		this.vertices = vertices;
	}

	//adds directed edge between 2 vertices from src to tar
	public void addEdge(int src, int tar) throws Exception{
		if((src<0 || tar<0 || src>=vertices || tar>=vertices))
			throw new Exception("Wrong");
		graph[src][tar] = 1;
	}

	//returns a list of vertex IDs
	public List<Integer> neighbors(int vertex) throws Exception{
		if(vertex<0 || vertex>=vertices)
			throw new Exception("Wrong!");
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<vertices;i++){
			if(graph[vertex][i]==1)
				list.add(i);
		}
		return list;
	}

	//prints to console an ordering of vertices
	public List<Integer> topologicalSort() {
		List<Integer> list = new ArrayList<Integer>();
		int[]sum = new int[vertices];

		for(int i=0;i<vertices;i++){
			for(int j=0;j<vertices;j++)
				sum[j] += graph[i][j];
		}
		System.out.println();

		for(int i=0;i<vertices;i++){
			int next = findZero(sum);
			if(next == -1){
				System.out.println("ordering cannot be done");
				//return list;
				System.exit(0);
			}
			list.add(next);
			System.out.println(next);
			sum[next] = -1;
			for(int j=0;j<vertices;j++)
				sum[j] -= graph[next][j];
		}
		System.out.println();
		return list;
	}
	
	public int findZero(int[] a){
		for (int i=0; i<a.length; i++){
			if (a[i]==0)
				return i;
		}
		return -1;
	}

}
