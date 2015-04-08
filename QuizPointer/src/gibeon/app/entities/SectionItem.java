package gibeon.app.entities;

public class SectionItem implements Item {

	private final String title;

	public SectionItem(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public boolean isSection() {
		return true;
	}

	@Override
	public String data() {
		return "";
	}

}
