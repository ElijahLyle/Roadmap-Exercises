public class Task {
    private int id;
    private String description;
    private String status;
    private int createdAt;
    private int updatedAt;

    /**
     * Constructor for the Task class.
     * @param id
     * @param description
     * @param status
     * @param createdAt
     * @param updatedAt
     */
    public Task(int id, String description, String status, int createdAt, int updatedAt) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task(){
        
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String setDescription(String description) {
        this.description = description;
        return description;
    }

    public String setStatus(String status) {
        this.status = status;
        return status;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public int setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
        return updatedAt;
    }

    public int setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
        return createdAt;
    }
}
