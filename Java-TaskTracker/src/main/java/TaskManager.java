import java.util.ArrayList;
import java.util.List;
import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskManager {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public TaskManager() {
        loadTasks();
    }

    private int getCurrentDateAsYYYYMMDD() {
        return Integer.parseInt(java.time.LocalDate.now()
                .format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE));
    }

    public Task addTask(String description) {
        int currentDate = getCurrentDateAsYYYYMMDD();
        Task task = new Task(nextId, description, "todo", currentDate, currentDate);
        tasks.add(task);
        nextId++;
        saveTasks();
        return task;
    }

    public Task updateTask(int id, String description) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                task.setUpdatedAt(getCurrentDateAsYYYYMMDD());
                saveTasks();
                return task;
            }
        }
        System.out.println("TaskManager: Task with ID " + id + " not found.");
        return null;
    }

    public Task deleteTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                saveTasks();
                return task;
            }
        }
        System.out.println("TaskManager: Task with ID " + id + " not found.");
        return null;
    }

    public Task markDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("done");
                task.setUpdatedAt(getCurrentDateAsYYYYMMDD());
                saveTasks();
                return task;
            }
        }
        System.out.println("TaskManager: Task with ID " + id + " not found.");
        return null;
    }

    public Task markInProgress(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("inprogress");
                task.setUpdatedAt(getCurrentDateAsYYYYMMDD());
                saveTasks();
                return task;
            }
        }
        System.out.println("TaskManager: Task with ID " + id + " not found.");
        return null;
    }

    public Task getTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        System.out.println("TaskManager: Task with ID " + id + " not found.");
        return null;        
    }

    public List<Task> listTasks() {
        List<Task> listedTasks = new ArrayList<>();
        for(Task task : tasks) {
            listedTasks.add(task);
        }
        return listedTasks;
    }

    public List<Task> listDoneTasks(){
        List<Task> doneTasks = new ArrayList<>();
        for(Task task : tasks) {
            if(task.getStatus().equals("done")){
                doneTasks.add(task);
            }
        }
        return doneTasks;
    }

    public List<Task> listTodoTasks(){
        List<Task> todoTasks = new ArrayList<>();
        for(Task task : tasks) {
            if(task.getStatus().equals("todo")){
                todoTasks.add(task);
            }
        }
        return todoTasks;
    }

    public List<Task> listInProgressTasks(){
        List<Task> inProgressTasks = new ArrayList<>();
        for(Task task : tasks) {
            if(task.getStatus().equals("inprogress")){
                inProgressTasks.add(task);
            }
        }
        return inProgressTasks;
    }

    private void saveTasks(){
        try {
            objectMapper.writeValue(new File("data/tasks.json"), tasks);
        } 
        catch (Exception e) {
            System.out.println("TaskManager: Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks(){
        try {
            File file = new File("data/tasks.json");

            if(file.exists()){
                tasks = objectMapper.readValue(file, new TypeReference<List<Task>>(){});

                nextId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
            }
        } 
        catch (Exception e) {
            System.out.println("TaskManager: Error loading tasks: " + e.getMessage());
        }
    }
}
