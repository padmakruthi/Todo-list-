package todo;

public class Task {
    private int tid;         // Task ID
    private String td;       // Task Description / Title
    private String ps;       // Priority / Status (e.g. Pending, Completed)
    private int pks;         // Primary key reference (regid)

    // Constructors
    public Task() {}

    public Task(int tid, String td, String ps, int pks) {
        this.tid = tid;
        this.td = td;
        this.ps = ps;
        this.pks = pks;
    }

    public Task(String td, String ps, int pks) {
        this.td = td;
        this.ps = ps;
        this.pks = pks;
    }

    // Getters and Setters
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public int getPks() {
        return pks;
    }

    public void setPks(int pks) {
        this.pks = pks;
    }

    // Convenient Aliases
    public int getTaskId() {
        return tid;
    }

    public String getTaskDescription() {
        return td;
    }

    public String getPriorityStatus() {
        return ps;
    }

    public int getRegId() {
        return pks;
    }
}
