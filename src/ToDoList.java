import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class ToDoList {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();

	public void addTask(Task task) {
		tasks.put(task.getDescription(), task);
	}

	public void completeTask(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			task.setComplete(true);
		};
	}

	public boolean getStatus(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null) {
			return task.isComplete();
		}
		;
		return false;
	}

	public Task getTask(String description) {
		return tasks.get(description);
	}

	public Task removeTask(String description) {
		return tasks.remove(description);
	}

	public Collection<Task> getAllTasks() {
		return tasks.values();
	}

	public Collection<Task> getCompletedTasks() {
		Collection<Task> completedTasks = new ArrayList<Task>();
		Collection<Task> allTasks = new ArrayList<Task>();
		allTasks = getAllTasks();
		for (Task task : allTasks)
			if (task.isComplete() == true)
				completedTasks.add(task);
		return completedTasks;
	}
	
	public void deleteCompletedTasks() {
		Iterator<Map.Entry<String, Task>> iterator = tasks.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Task> entry = iterator.next();
			String key = entry.getKey();
			Task task = tasks.get(key);
			if(task.isComplete()) {
				iterator.remove();
			}
		}
	}
	
	public Collection<Task> getTaskByDeadline() {
		List<Task> mapValues = new ArrayList<Task>(tasks.values());
		Collections.sort(mapValues);
		return mapValues;
	}
	
	public int printReminder() {
		Collection<Task> allTasks = getAllTasks();
		Date now = new Date();
		Instant nowInstant = now.toInstant();
		int counter = 0;
		for (Task task : allTasks) {
			Date taskdate = task.getDeadline();
			Instant taskInstant = taskdate.toInstant();
			long diff = taskInstant.toEpochMilli() - nowInstant.toEpochMilli();
			long offset = 24*60*60*1000;
			
			if(diff < offset) {
				counter++;
				System.out.println("Task needs to be done in day " + task.getDeadlineInString() + " " + task.getDescription());
			}
		}
			return counter;
	}
	
}
