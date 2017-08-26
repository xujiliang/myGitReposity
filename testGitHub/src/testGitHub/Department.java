package testGitHub;

public class Department {
	private String name;
	private String grnder;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrnder() {
		return grnder;
	}
	public void setGrnder(String grnder) {
		this.grnder = grnder;
	}
	@Override
	public String toString() {
		return "Department [name=" + name + ", grnder=" + grnder + "]";
	}
	
}
