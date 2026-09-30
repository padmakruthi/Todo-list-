package todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Singleton Implementation of RegisterDAO
public class RegisterDAOImpl implements RegisterDAO {
    private Connection con;
    private Statement stmt;
    private PreparedStatement pstmt1, pstmt2;
    private ResultSet rs;

    // Singleton Static Instance
    private static RegisterDAO dao;

    // Private Constructor (Singleton Pattern)
    private RegisterDAOImpl() {
        try {
            con = DBconn.getConn();
            if (con != null) {
                stmt = con.createStatement();
                pstmt1 = con.prepareStatement("INSERT INTO register(name, email, password, mobile, address) VALUES(?, ?, ?, ?, ?)");
                pstmt2 = con.prepareStatement("INSERT INTO task(taskdescription, prioritystatus, regid) VALUES(?, ?, ?)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Singleton Factory Method: "Class which is having only one instance per JVM is called as Singleton Class"
    public static RegisterDAO getInstance() {
        if (dao == null) {
            dao = new RegisterDAOImpl();
        }
        return dao;
    }

    @Override
    public int register(Register reg) {
        int status = 0;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO register(name, email, password, mobile, address) VALUES(?, ?, ?, ?, ?)"
            );
            pstmt.setString(1, reg.getName());
            pstmt.setString(2, reg.getEmail());
            pstmt.setString(3, reg.getPassword());
            pstmt.setString(4, reg.getMobile());
            pstmt.setString(5, reg.getAddress());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Register login(String email, String password) {
        Register user = null;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM register WHERE email = ? AND password = ?"
            );
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                user = new Register(
                    res.getInt("regid"),
                    res.getString("name"),
                    res.getString("email"),
                    res.getString("password"),
                    res.getString("mobile"),
                    res.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int findregid(String email) {
        int regId = 0;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT regid FROM register WHERE email = ?"
            );
            pstmt.setString(1, email);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                regId = res.getInt("regid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regId;
    }

    @Override
    public List<Register> findallregs() {
        List<Register> list = new ArrayList<>();
        try {
            Connection connection = DBconn.getConn();
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM register");
            while (res.next()) {
                Register reg = new Register(
                    res.getInt("regid"),
                    res.getString("name"),
                    res.getString("email"),
                    res.getString("password"),
                    res.getString("mobile"),
                    res.getString("address")
                );
                list.add(reg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updatereg(Register reg) {
        int status = 0;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE register SET name=?, mobile=?, address=? WHERE regid=?"
            );
            pstmt.setString(1, reg.getName());
            pstmt.setString(2, reg.getMobile());
            pstmt.setString(3, reg.getAddress());
            pstmt.setInt(4, reg.getRegId());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deletereg(int regId) {
        int status = 0;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM register WHERE regid=?"
            );
            pstmt.setInt(1, regId);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int addTask(Task task) {
        int status = 0;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO task(taskdescription, prioritystatus, regid) VALUES(?, ?, ?)"
            );
            pstmt.setString(1, task.getTd());
            pstmt.setString(2, task.getPs());
            pstmt.setInt(3, task.getPks());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Task> findTaskByRegID(int regid) {
        List<Task> list = new ArrayList<>();
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM task WHERE regid = ?"
            );
            pstmt.setInt(1, regid);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Task t = new Task(
                    res.getInt("taskid"),
                    res.getString("taskdescription"),
                    res.getString("prioritystatus"),
                    res.getInt("regid")
                );
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int taskCompleted(int taskId, int regId) {
        int status = 0;
        try {
            Connection connection = DBconn.getConn();
            PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE task SET prioritystatus = 'Completed' WHERE taskid = ? AND regid = ?"
            );
            pstmt.setInt(1, taskId);
            pstmt.setInt(2, regId);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
