
public enum Genres {
	FANTASY("Fantasy"), ACTION("Action"), ADVENTURE("Adventure"), CLASSICS("Classics"),
	COMIC("Comic"), DETECTIVE("Detective"), MYSTERY("Mystery"), HISTORICAL("Historical Fiction"),
	HORROR("Horror"), LITERARY("Literary Fiction"), ROMANCE("Romance"), SCIENCE("Science Fiction"),
	SHORT("Short Stories"), THRILLERS("Thrillers"), POETRY("Poetry"), NULL("");
	
	private String name;

	private Genres(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
		
}
