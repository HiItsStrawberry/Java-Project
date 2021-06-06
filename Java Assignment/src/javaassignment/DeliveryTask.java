package javaassignment;

public class DeliveryTask {
    
    private DeliveryStaff owner, role;
    private int task;

    public DeliveryTask(DeliveryStaff owner, DeliveryStaff role, int task) {
        this.owner = owner;
        this.role = role;
        this.task = task;
    }

    public DeliveryStaff getOwner() {
        return owner;
    }

    public void setOwner(DeliveryStaff owner) {
        this.owner = owner;
    }

    public DeliveryStaff getRole() {
        return role;
    }

    public void setRole(DeliveryStaff role) {
        this.role = role;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }
      
}
