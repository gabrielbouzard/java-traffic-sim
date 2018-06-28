package gabriel_bouzard_myproject.cmd;

public class MinMax {

	private Double x;
	private Double y;
	
	public MinMax(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	public Double getMin() {
		return x;
	}
	
	public Double getMax() {
		return y;
	}
	
	public String toString() {
		if (y != null) return "[min="+x+",max="+y+"]";
		else return "["+x+"]";
	}
}
