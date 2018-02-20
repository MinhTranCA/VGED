import utils.FileIO;

public class TestVGED {
	private static String dataFolder = "data/";
	private static String input1 = dataFolder + "Ref_Graph.txt";
	private static String input2 = dataFolder + "SMT_Graph.txt";
	private static String output = dataFolder + "output.txt";

	private static String processOneGraph(String data1, String data2) {
		Graph g1 = new Graph();
		Graph g2 = new Graph();
		boolean isParsed1 = g1.parseGraphFromData(data1);
		boolean isParsed2 = g2.parseGraphFromData(data2);
		if (isParsed1 && isParsed2) {
			System.out.println("Parsed successfully");
			VGED vged = new VGED(g1, g2);
			return Double.toString(vged.getVGEDScore());
		} else {
			return "Error parsing";
		}
	}

	private static void processing(String data1, String data2) {
		String[] graph1 = data1.split("\n\n");
		String[] graph2 = data2.split("\n\n");
		String result = "";
		if (graph1.length != graph2.length) {
			result = "The number of graphs is not the same";
		} else {
			for (int i = 0; i < graph1.length; ++i){
				result += processOneGraph(graph1[i], graph2[i]) + "\n";
			}
		}
		FileIO.writeStringToFile(output, result);
	}

	public static void main(String[] args) {
		System.out.println("START...");
		String data1 = FileIO.readStringFromFile(input1);
		String data2 = FileIO.readStringFromFile(input2);
		TestVGED.processing(data1, data2);
		System.out.printf("DONE!!!");
	}
}
