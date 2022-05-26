import java.util.List;

import javax.swing.JFrame;

public abstract class CreatorRecord {
	List<Record> records;
	
	CreatorRecord(List<Record> records) {
		this.records = records;
	}
	
	abstract void createNew(JFrame frame);
	abstract void editRecord(Record rec);
	abstract void addRecord(Record rec);
	abstract void deleteRecord(Record rec);
}
