import java.time.LocalDate;
public class Task {
//    Data
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Boolean completed;


    public enum Priority{
        HIGH,MEDIUM,LOW

    }
//    constructors


    public Task(String description, LocalDate dueDate, Priority priority, Boolean completed) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }
//    Getters

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Boolean isCompleted() {
        return completed;
    }

//    Setters

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    @Override
    public String toString(){
        return String.format("%s (Due: %s, Priority: %s,Completed: %s)",description,dueDate,priority,completed ? "Yes":"No");
    }
}
