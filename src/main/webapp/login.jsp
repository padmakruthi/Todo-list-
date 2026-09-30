<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Task Management System</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 40px; }
    .container { max-width: 400px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; margin-bottom: 20px; }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
    input[type="email"], input[type="password"] { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
    .btn { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
    .btn:hover { background-color: #218838; }
    .link { text-align: center; margin-top: 15px; }
    .msg { text-align: center; margin-bottom: 15px; font-weight: bold; }
    .msg.error { color: red; }
    .msg.success { color: green; }
</style>
</head>
<body>
<div class="container">
    <h2>User Login</h2>
    <% String msg = request.getParameter("msg");
       if ("registered".equals(msg)) { %>
        <div class="msg success">Registration successful! Please login.</div>
    <% } else if ("invalid".equals(msg)) { %>
        <div class="msg error">Invalid Email or Password.</div>
    <% } else if ("loggedout".equals(msg)) { %>
        <div class="msg success">You have been logged out.</div>
    <% } %>
    <form action="LoginServlet" method="post">
        <div class="form-group">
            <label>Email Address:</label>
            <input type="email" name="email" required />
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" required />
        </div>
        <button type="submit" class="btn">Login</button>
    </form>
    <div class="link">
        Don't have an account? <a href="register.jsp">Register Here</a>
    </div>
</div>
</body>
</html>
