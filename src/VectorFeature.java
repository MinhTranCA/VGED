import java.util.Comparator;

public class VectorFeature {
	private String value;
	private int count;

	public VectorFeature(String value, int count) {
		this.value = value;
		this.count = count;
	}

	public String getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}

	public static Comparator<VectorFeature> VectorFeatureComparator = new Comparator<VectorFeature>() {
		public int compare(VectorFeature v1, VectorFeature v2) {
			String v1Value = v1.getValue().toLowerCase();
			String v2Value = v2.getValue().toLowerCase();
			//ascending order
			return v1Value.compareTo(v2Value);
		}
	};

	public int compareTo(VectorFeature v) {
		return this.value.toLowerCase().compareTo(v.getValue().toLowerCase());
	}
}
