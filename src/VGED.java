import java.util.ArrayList;
import java.util.Collections;

public class VGED {
	private Graph g1;
	private Graph g2;

	private ArrayList<VectorFeature> v1;
	private ArrayList<VectorFeature> v2;

	private double vgedScore = -1;

	public VGED(Graph g1, Graph g2) {
		this.g1 = g1;
		this.g2 = g2;
	}

	private ArrayList<VectorFeature> generateVector(Graph g) {
		ArrayList<VectorFeature> result = new ArrayList<>();
		ArrayList<NPath> nPaths = g.getnPaths();
		ArrayList<PQNode> pqNodes = g.getPqNodes();
		for (NPath nPath : nPaths) {
			result.add(new VectorFeature(nPath.getValue(), nPath.getCount()));
		}
		for (PQNode pqNode : pqNodes) {
			result.add(new VectorFeature(pqNode.getValue(), pqNode.getCount()));
		}
		return result;
	}

	private void computeVGEDScore() {
		v1 = generateVector(g1);
		v2 = generateVector(g2);

		Collections.sort(v1, VectorFeature.VectorFeatureComparator);
		Collections.sort(v2, VectorFeature.VectorFeatureComparator);

		int i2 = 0, sum = 0, distance = 0;

		for (VectorFeature v : v1) sum += v.getCount();
		for (VectorFeature v : v2) sum += v.getCount();

		for (int i1 = 0; i1 < v1.size(); ++i1) {
			if (i2 >= v2.size()) {
				distance += v1.get(i1).getCount();
			} else {
				int compareValue = v1.get(i1).compareTo(v2.get(i2));
				while (i2 < v2.size() && compareValue > 0) {
					distance += v2.get(i2).getCount();
					++i2;
					if (i2 < v2.size())
						compareValue = v1.get(i1).compareTo(v2.get(i2));
				}
				if (i2 >= v2.size() || compareValue < 0)
					distance += v1.get(i1).getCount();
				else {
					distance += Math.abs(v1.get(i1).getCount() - v2.get(i2).getCount());
					++i2;
				}
			}
		}

		while (i2 < v2.size()) {
			distance += v2.get(i2).getCount();
			++i2;
		}

		vgedScore = distance / (double) sum;
		vgedScore = 1 - vgedScore;
	}

	public double getVGEDScore() {
		if (vgedScore == -1)
			computeVGEDScore();
		return vgedScore;
	}
}
