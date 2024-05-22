
class Process {
    private int pid;
    private int arrivalTime;
    private int burstTime;
    private int turnAroundTime;
    private int responseTime;
    private int waitingTime;
    private int remainingBurst;
    private int priority;

    Process(int processID, int arrivalTime, int burstTime, int priority) {
        this.pid = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingBurst = burstTime;

    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getRemainingBurst() {
        return remainingBurst;
    }

    public void setRemainingBurst(int remainingBurst) {
        this.remainingBurst = remainingBurst;
    }

    public void printProcessInfo() {
        System.out.println("pid: " + getPid());
        System.out.println("Arrival Time: " + getArrivalTime());
        System.out.println("Bust Time: " + getBurstTime());
        System.out.println("Priority: " + getPriority());
        System.out.println("Turnaround Time: " + getTurnAroundTime());
        System.out.println("Response Time: " + getResponseTime());
        System.out.println("Waiting Time: " + getWaitingTime());
    }

    public boolean isArrived(int currentTime) {
        return (this.getArrivalTime() <= currentTime);
    }

    public boolean isCompleted() {
        return this.getRemainingBurst() == 0;
    }

}
