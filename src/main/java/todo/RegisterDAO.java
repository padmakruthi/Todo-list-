package todo;

import java.util.List;

public interface RegisterDAO {
    // User Operations
    int register(Register reg);
    Register login(String email, String password);
    int findregid(String email);
    List<Register> findallregs();
    int updatereg(Register reg);
    int deletereg(int regId);

    // Task Operations
    int addTask(Task task);
    List<Task> findTaskByRegID(int regid);
    int taskCompleted(int taskId, int regId);
}
