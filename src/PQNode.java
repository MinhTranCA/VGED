public class PQNode {
	private String value;
	private int count;

	public PQNode(String value, int cnt) {
		this.value = value;
		this.count = cnt;
	}

	public void increaseCountBy(int addition) {
		this.count += addition;
	}

	public String getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}
}
