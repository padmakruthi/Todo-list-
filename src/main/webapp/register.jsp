<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register - Task Management System</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 40px; }
    .container { max-width: 450px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; margin-bottom: 20px; }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
    input[type="text"], input[type="email"], input[type="password"] { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
    .btn { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
    .btn:hover { background-color: #0056b3; }
    .link { text-align: center; margin-top: 15px; }
    .msg { color: red; text-align: center; margin-bottom: 15px; }
</style>
</head>
<body>
<div class="container">
    <h2>User Registration</h2>
    <% String msg = request.getParameter("msg");
       if ("failed".equals(msg)) { %>
        <div class="msg">Registration Failed. Please try again.</div>
    <% } %>
    <form action="RegisterServlet" method="post">
        <div class="form-group">
            <label>Full Name:</label>
            <input type="text" name="name" required />
        </div>
        <div class="form-group">
            <label>Email Address:</label>
            <input type="email" name="email" required />
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" required />
        </div>
        <div class="form-group">
            <label>Mobile Number:</label>
            <input type="text" name="mobile" required />
        </div>
        <div class="form-group">
            <label>Address:</label>
            <input type="text" name="address" required />
        </div>
        <button type="submit" class="btn">Register</button>
    </form>
    <div class="link">
        Already registered? <a href="login.jsp">Login Here</a>
    </div>
</div>
</body>
</html>
