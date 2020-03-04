import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private String description = null;
	private boolean isComplete = false;
	private Date deadline = null;

	public Task(String description) {
		super();
		this.description = description;
	}

	public Task(String description, boolean isComplete) {
		super();
		this.description = description;
		this.isComplete = isComplete;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean addDeadline(String datetimestr) {
		try {
			deadline = sdf.parse(datetimestr);
			System.out.println(deadline);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public Date getDeadline() {
		return deadline;
	}

	public String getDeadlineInString() {
		return sdf.format(deadline);
	}

	@Override
	public int compareTo(Task other) {
		return getDeadline().compareTo(other.getDeadline());
	}

}
