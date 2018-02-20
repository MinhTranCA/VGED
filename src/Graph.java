import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private final int numberOfPathMaximum = 3;

	private int nNode;
	private int nEdge;

	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	private ArrayList<PQNode> pqNodes;
	private ArrayList<NPath> nPaths;

	private HashMap<Integer, Node> indexNodeMap;
	private HashMap<String, PQNode> valuePQNodeMap;
	private HashMap<String, NPath> valueNPathMap;

	public Graph() {
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
		pqNodes = new ArrayList<>();
		nPaths = new ArrayList<>();

		indexNodeMap = new HashMap<>();
		valuePQNodeMap = new HashMap<>();
		valueNPathMap = new HashMap<>();
	}

	private boolean isRangeOfIndex(int idx) {
		return 0 < idx && idx <= nNode;
	}

	private void generateQPNodes() {
		for (Node node : nodes) {
			addQPNode(node.toPQNodeValue());
		}
	}

	private void addQPNode(String value) {
		PQNode tmp = valuePQNodeMap.get(value);
		if (tmp == null) {
			tmp = new PQNode(value, 1);
			pqNodes.add(tmp);
			valuePQNodeMap.put(value, tmp);
		} else {
			tmp.increaseCountBy(1);
		}
	}

	private void addnPath(String value) {
		NPath tmp = valueNPathMap.get(value);
		if (tmp == null) {
			tmp = new NPath(value, 1);
			nPaths.add(tmp);
			valueNPathMap.put(value, tmp);
		} else {
			tmp.increaseCountBy(1);
		}

	}

	private void DFS(Node currNode, int depth, String currResult) {
		String nextResult = currResult.isEmpty() ? currNode.getValue() : currResult + "-" + currNode.getValue();
		if (depth == 1) {
			addnPath(nextResult);
			return;
		}
		ArrayList<Node> nextNodes = currNode.getOutcomingNodes();
		for (Node node: nextNodes) {
			DFS(node, depth - 1, nextResult);
		}
	}

	private void generateNPath() {
		for (int i = 1; i <= numberOfPathMaximum; ++i) {
			for (Node node : nodes) {
				DFS(node, i, "");
			}
		}
	}

	public boolean parseGraphFromData(String data){
		String[] partitions = data.split("\\s+");

		if (partitions.length == 1)
			return false;

		nNode = Integer.parseInt(partitions[1]);
		for (int i = 1; i <= nNode; ++i) {
			Node tmp = new Node(i, partitions[i + 1]);
			indexNodeMap.put(i, tmp);
			nodes.add(tmp);
		}

		nEdge = Integer.parseInt(partitions[nNode + 2]);
		for (int i = nNode + 3; i <= nNode + nEdge * 2 + 1; i += 2) {
			int u = Integer.parseInt(partitions[i]);
			int v = Integer.parseInt(partitions[i + 1]);
			if (isRangeOfIndex(u) && isRangeOfIndex(v)) {
				Node fromNode = indexNodeMap.get(u);
				Node toNode = indexNodeMap.get(v);

				Edge tmp = new Edge(fromNode, toNode);
				edges.add(tmp);

				fromNode.addOutcomingNodes(toNode);
				toNode.addIncomingNode(fromNode);
			}
			else {
				System.out.println("Could not parse the graph due to index out of range");
				return false;
			}
		}
		generateQPNodes();
		generateNPath();
		return true;
	}

	public ArrayList<PQNode> getPqNodes() {
		return pqNodes;
	}

	public ArrayList<NPath> getnPaths() {
		return nPaths;
	}
}
