import java.util.ArrayList;

public class Node {
	private int index;
	private String value;
	private ArrayList<Node> incomingNodes = new ArrayList<>();
	private ArrayList<Node> outcomingNodes = new ArrayList<>();

	public Node(int index, String value) {
		this.index = index;
		this.value = value;
	}

	public void addIncomingNode(Node inNode) {
		incomingNodes.add(inNode);
	}

	public void addOutcomingNodes(Node outNode) {
		outcomingNodes.add(outNode);
	}

	public int getIncomingNodesNumber() {
		return incomingNodes.size();
	}

	public int getOutcomingNodesNumber() {
		return outcomingNodes.size();
	}

	public ArrayList<Node> getOutcomingNodes() {
		return outcomingNodes;
	}

	public String getValue() {
		return value;
	}

	public String toPQNodeValue() {
		return value + "-" + Integer.toString(getIncomingNodesNumber()) + "-" + Integer.toString(getOutcomingNodesNumber());
	}
}
