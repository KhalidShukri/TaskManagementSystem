import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> task;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;
//    constructor

    public TaskManager() {
        this.task = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    public void run(){
        while (true) {
            System.out.println("\n ---------Task Management System---------");
            System.out.println("1.Add a Task");
            System.out.println("2.View all Tasks");
            System.out.println("3.Mark Task as Completed");
            System.out.println("4.Remove a Task");
            System.out.println("5.View Task by Priority");
            System.out.println("6.View Overdue Tasks");
            System.out.println("7.Exit");
            System.out.print("Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addTaskMenu();
                    break;
                case 2:
                    viewAllTasks();
                    break;
                case 3:
                    markTaskCompletedMenu();
                    break;
                case 4:
                    removeTaskMenu();
                    break;
                case 5:
                    viewTaskByPriorityMenu();
                    break;
                case 6:
                    viewOverDueTask();
                    break;

                case 7:
                    System.out.println("Thanks for using task Management System");
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again");


            }
        }
    }
    public void addTaskMenu(){
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Due Date(yyyy-MM-dd): ");
        LocalDate dueDate = null;
        while(dueDate == null){
            try{
                dueDate = LocalDate.parse(scanner.nextLine(),dateFormatter);
            } catch (DateTimeParseException e){
                System.out.println("Invalid Date Format. Please enter the yyyy-MM-dd: ");

            }
        }
        System.out.print("Enter Priority(HIGH MEDIUM LOW): ");
        Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());
        task.add(new Task(description,dueDate,priority,false));
        System.out.println("Task Added Successfully");
    }
    public void viewAllTasks(){
        if(task.isEmpty()){
            System.out.println("No Tasks Yet");
            return;
        }
        for(int i=0;i<task.size();i++){
            System.out.println((i+1)+ ". " + task.get(i));
        }
    }
    public void markTaskCompletedMenu(){
        viewAllTasks();
        if(task.isEmpty()){
            return;
        }
        System.out.print("Enter the number of the task to mark as completed");
        int taskNumber = scanner.nextInt();
        if(taskNumber>0 && taskNumber <= task.size()){
            Task tasks = task.get(taskNumber - 1);
            tasks.setCompleted(true);
            System.out.println("Task marked as completed: "+ tasks.getDescription());


        } else{
            System.out.println("Invalid task Number.");
        }
    }
    public void removeTaskMenu(){
        viewAllTasks();
        if(task.isEmpty()){
            return;
        }
        System.out.print("Enter the number of the task to remove");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();
        if (taskNumber > 0 && taskNumber <= task.size()){
           Task removedTask = task.remove(taskNumber-1);
            System.out.println("Task Removed: "+ removedTask.getDescription());

        } else {
            System.out.println("Invalid Task Number");
        }
    }
    public void viewTaskByPriorityMenu(){
        System.out.print("Enter Priority to view (HIGH,MEDIUM,LOW)");
        Task.Priority Priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());
        List<Task> filteredTask = task.stream()
                .filter(tasks -> tasks.getPriority() == Priority)
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
        if(filteredTask.isEmpty()){
            System.out.println("No taks with Priority:  " + Priority);
            return;
        }
        System.out.println("Task with Priority: " + Priority + ":");
        for(int i = 0;i< filteredTask.size();i++){
            System.out.println((i-1) + "." + filteredTask.get(i));

        }
    }
    public void viewOverDueTask(){
        LocalDate today = LocalDate.now();
        List<Task> overDueTasks = task.stream()
                .filter(tasks -> !tasks.isCompleted() && tasks.getDueDate().isBefore(today))
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();

        if (overDueTasks.isEmpty()){
            System.out.println("No Tasks Found");
            return;
        }
        for (int i = 0;i<=overDueTasks.size();i++){
            System.out.println((i-1) + "." + task.get(i));
        }

    }
}
