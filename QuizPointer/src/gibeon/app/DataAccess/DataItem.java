package gibeon.app.DataAccess;

import gibeon.app.entities.Item;

public class DataItem implements Item {

	public final String user, dates, packages, counts;
	public final int id;

	public DataItem(int id, String user, String dates, String packages,
			String counts) {
		super();
		this.id = id;
		this.user = user;
		this.dates = dates;
		this.packages = packages;
		this.counts = counts;
	}

	@Override
	public boolean isSection() {
		return false;
	}

	@Override
	public String data() {
		// TODO Auto-generated method stub
		return user + "#" + id;
	}

}