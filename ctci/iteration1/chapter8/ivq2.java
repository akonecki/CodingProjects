import java.util.Queue;
import java.util.LinkedList;

public class ivq2 {
    private class CallCenter {
        private Queue<Employee> employees = new LinkedList<Employee>();
        private Employee mRespondant = null;
        private Employee mManager = null;
        private Employee mDirector = null;

        // Could do three queues one for each type
        // Current emplementation is more along the lines of all employees in the 
        // same queue with pointers to the next available.

        private Employee getFirstAvailableEmployee(Employee employeeType) {
            
        }
    }

    private abstract class Employee {
        private boolean isAvailable = false;

        public boolean getAvailability() {
            return this.isAvailable;
        }

        public void setAvailability(boolean available) {
            this.isAvailable = available;
        }

        public abstract void escalate();
        public abstract void dispatchCall();
    }

    private class Respondant extends Employee {
        public void escalate() {
            // escalates to a manager
        }

        public void dispatchCall() {

        }
    }

    private class Manager extends Employee {
        public void escalate() {
            // escalates to a director
        }

        public void dispatchCall() {
            
        }
    }

    private class Director extends Employee {
        public void escalate() {
            // doesnt escalate
        }

        public void dispatchCall() {
            
        }
    }
}