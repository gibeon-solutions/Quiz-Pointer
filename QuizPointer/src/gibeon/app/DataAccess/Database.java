package gibeon.app.DataAccess;

public class Database {

	private String name;
	private int version;

	public Database(String n, int v){
		name =n;
		version=v;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
