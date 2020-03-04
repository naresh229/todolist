import org.junit.*;
import static org.junit.Assert.*;

import java.util.Collection;

public class ToDoListTest {
	private Task task1;
	private Task task2;
	private Task task3;
	private ToDoList todoList;

	public ToDoListTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		task1 = new Task("desc 1");
		task2 = new Task("desc 2");
		task3 = new Task("desc 3");

		todoList = new ToDoList();
	}

	@After
	public void tearDown() throws Exception {
		task1 = null;
		task2 = null;
		task3 = null;

		todoList = null;
	}

	@Test
	public void testAddTask() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		assertEquals(1, todoList.getAllTasks().size());
		assertEquals(task1, todoList.getTask(task1.getDescription()));
	}

	@Test
	public void testgetStatus() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		assertEquals(false, todoList.getStatus(task1.getDescription()));
		todoList.completeTask(task1.getDescription());
		assertEquals(true, todoList.getStatus(task1.getDescription()));
	}

	@Test
	public void testRemoveTask() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		todoList.addTask(task2);
		;

		todoList.removeTask(task1.getDescription());
		assertNull(todoList.getTask(task1.getDescription()));
	}

	@Test
	public void testGetCompletedTasks() {
		task1.setComplete(true);
		task3.setComplete(true);
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);

		Collection<Task> tasks = todoList.getCompletedTasks();
		assertEquals(2, tasks.size());
	}

	@Test
	public void testListAllTask() {

		todoList.addTask(task1);
		todoList.addTask(task2);

		Collection<Task> tasks = todoList.getAllTasks();
		assertEquals(2, tasks.size());
	}

	@Test
	public void testAddDeadlineToTask() {
		assertNotNull(todoList);
		todoList.addTask(task1);
		String valid_datetime = "2019/11/16 12:08:43";
		assertTrue("Date time string is invalid", task1.addDeadline(valid_datetime));
		assertEquals("Date time parsing is not working", valid_datetime, task1.getDeadlineInString());
		String invalid_datetime = "abcd banana";
		assertFalse("Date time string should be invalid", task1.addDeadline(invalid_datetime));
	}
	
	@Test
	public void deleteCompletedTask() {
		task1.setComplete(true);
		task2.setComplete(false);
		task3.setComplete(true);
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		
		todoList.deleteCompletedTasks();
		
		Collection<Task> tasks = todoList.getAllTasks();
		assertEquals(1, tasks.size());
	}
	
	@Test
	public void testSortByDeadline() {
		task1.addDeadline("2019/11/16 12:08:43");
		task2.addDeadline("2019/11/14 12:08:43");
		task3.addDeadline("2019/11/15 12:08:43");
		todoList.addTask(task1);
		todoList.addTask(task2);
		todoList.addTask(task3);
		Collection<Task> tasks = todoList.getTaskByDeadline();
		Task[] tasksArr = tasks.toArray(new Task[tasks.size()]);
		assertEquals(tasksArr[0], task2);
		assertEquals(tasksArr[1], task3);
		assertEquals(tasksArr[2], task1);
	}
}
