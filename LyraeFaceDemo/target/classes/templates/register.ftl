<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="box">
        <h2>注册页面</h2>
        <form role="form" action="/doRegister" method="post">
            <div class="inputBox">
                <input type="text" name="username" id="username">
                <label>请输入姓名:</label>
            </div>
            <div class="inputBox">
                <input type="password" name="password" id="password">
                <label>请输入密码：</label>
            </div>
            <input type="submit" id="register" value="提交">
        </form>
    </div>
</body>
</html>