import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if(args.length == 0) {
            System.out.println("Please provide a valid task tracker command");
            return;
        }

        if(args[0].equals("add")){
            if(args.length < 2){
                System.out.println("Please provide a description for the task.");
                return;
            }

            Task task = taskManager.addTask(args[1]);
            System.out.println("Output: Task added successfully. ID: " + task.getId());
        }
        else if(args[0].equals("list")){
            if(args.length < 2){
                System.out.println("Please provide a type of listing for the task.");
                return;
            }
            
            switch(args[1]){
                case "all":
                    List<Task> allTasks = taskManager.listTasks();
                    for (Task t : allTasks) {
                        System.out.println("ID: " + t.getId() + ", Description: " + t.getDescription() + ", Status: " + t.getStatus() + ", Created At: " + t.getCreatedAt() + ", Updated At: " + t.getUpdatedAt());
                    }
                    break;
                case "done":
                    List<Task> doneTasks = taskManager.listDoneTasks();
                    for (Task t : doneTasks) {
                        System.out.println("ID: " + t.getId() + ", Description: " + t.getDescription() + ", Created At: " + t.getCreatedAt() + ", Updated At: " + t.getUpdatedAt());
                    }
                    break;
                case "todo":
                    List<Task> todoTasks = taskManager.listTodoTasks();
                    for (Task t : todoTasks) {
                        System.out.println("ID: " + t.getId() + ", Description: " + t.getDescription() + ", Created At: " + t.getCreatedAt() + ", Updated At: " + t.getUpdatedAt());
                    }
                    break;
                case "inprogress":
                    List<Task> inProgressTasks = taskManager.listInProgressTasks();
                    for (Task t : inProgressTasks) {
                        System.out.println("ID: " + t.getId() + ", Description: " + t.getDescription() + ", Created At: " + t.getCreatedAt() + ", Updated At: " + t.getUpdatedAt());
                    }
                    break;
                default:
                    System.out.println("Unknown listing type: " + args[1]);
            }
        }
        else if(args[0].equals("update")){
            if(args.length < 3){
                System.out.println("Please provide the task ID and new description.");
                return;
            }

            int id = Integer.parseInt(args[1]);
            String description = args[2];

            Task task = taskManager.updateTask(id, description);
            if(task != null) {
                System.out.println("Output: Task updated successfully. ID: " + task.getId() + "" + ", Description: " + task.getDescription() + ", Status: " + task.getStatus());
            }
        }
        else if(args[0].equals("mark-in-progress")){
            if(args.length < 2){
                System.out.println("Please provide the task ID to mark as in progress.");
                return;
            }

            int id = Integer.parseInt(args[1]);
            Task task = taskManager.markInProgress(id);
            if (task != null) {
                System.out.println("Output: Task marked as in progress successfully. ID: " + task.getId());
            }
        }
        else if(args[0].equals("mark-done")){
            if(args.length < 2){
                System.out.println("Please provide the task ID to mark as done.");
                return;
            }

            int id = Integer.parseInt(args[1]);
            Task task = taskManager.markDone(id);
            if (task != null) {
                System.out.println("Output: Task marked as done successfully. ID: " + task.getId());
            }
        }
        else if(args[0].equals("delete")){
            if(args.length < 2){
                System.out.println("Please provide the task ID to delete.");
                return;
            }

            int id = Integer.parseInt(args[1]);
            Task task = taskManager.deleteTask(id);
            if (task != null) {
                System.out.println("Output: Task deleted successfully. ID: " + task.getId());
            }
        }
        else {
            System.out.println("Unknown command: " + args[0]);
        }
    }
}