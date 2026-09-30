<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="todo.Register, todo.Task, todo.RegisterDAOImpl, java.util.List" %>
<%
    Register user = (Register) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Task> taskList = RegisterDAOImpl.getInstance().findTaskByRegID(user.getRegId());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard - Task Management</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 20px; }
    .header { background: #343a40; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; border-radius: 6px; }
    .header h2 { margin: 0; }
    .logout-btn { background: #dc3545; color: white; padding: 8px 15px; text-decoration: none; border-radius: 4px; font-size: 14px; }
    .logout-btn:hover { background: #c82333; }
    .content { max-width: 900px; margin: 30px auto; background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    .add-task-form { margin-bottom: 30px; background: #e9ecef; padding: 20px; border-radius: 6px; }
    .add-task-form h3 { margin-top: 0; }
    .form-inline { display: flex; gap: 10px; }
    .form-inline input[type="text"], .form-inline select { flex: 1; padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
    .form-inline button { padding: 10px 20px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
    table, th, td { border: 1px solid #ddd; }
    th, td { padding: 12px; text-align: left; }
    th { background-color: #007bff; color: white; }
    .status-completed { color: green; font-weight: bold; }
    .status-pending { color: #d9534f; font-weight: bold; }
    .action-btn { background: #28a745; color: white; padding: 6px 12px; text-decoration: none; border-radius: 4px; font-size: 13px; }
    .action-btn:hover { background: #218838; }
</style>
</head>
<body>

<div class="header">
    <h2>Task Management System</h2>
    <div>
        <span>Welcome, <strong><%= user.getName() %></strong> (ID: <%= user.getRegId() %>)</span> &nbsp;&nbsp;
        <a href="LogoutServlet" class="logout-btn">Logout</a>
    </div>
</div>

<div class="content">
    <div class="add-task-form">
        <h3>Add New Task</h3>
        <form action="AddTaskServlet" method="post" class="form-inline">
            <input type="text" name="td" placeholder="Enter task description..." required />
            <select name="ps">
                <option value="Pending">Pending</option>
                <option value="High Priority">High Priority</option>
                <option value="Medium Priority">Medium Priority</option>
                <option value="Low Priority">Low Priority</option>
            </select>
            <button type="submit">Add Task</button>
        </form>
    </div>

    <h3>Your Tasks</h3>
    <% if (taskList == null || taskList.isEmpty()) { %>
        <p style="color: #777;">No tasks found. Add a task above to get started!</p>
    <% } else { %>
        <table>
            <thead>
                <tr>
                    <th>Task ID</th>
                    <th>Task Description</th>
                    <th>Status / Priority</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (Task t : taskList) { %>
                <tr>
                    <td><%= t.getTid() %></td>
                    <td><%= t.getTd() %></td>
                    <td>
                        <span class="<%= "Completed".equalsIgnoreCase(t.getPs()) ? "status-completed" : "status-pending" %>">
                            <%= t.getPs() %>
                        </span>
                    </td>
                    <td>
                        <% if (!"Completed".equalsIgnoreCase(t.getPs())) { %>
                            <a href="TaskCompletedServlet?taskid=<%= t.getTid() %>&regid=<%= user.getRegId() %>" class="action-btn">Mark Completed</a>
                        <% } else { %>
                            <span>Done ✔</span>
                        <% } %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>
</div>

</body>
</html>
