<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css">
</head>

<body>
    <div class="box">
        <h2>登录页面</h2>
        <form role="form" action = "/doLogin" method="post">
            <div class="inputBox">
                <input type="text" name="username" id="username">
                <label>账号:</label>
            </div>
            <div class="inputBox">
                <input type="password" name="password" id="password">
                <label>密码:</label>
            </div>
            <input type="submit" id = "login" value = "登录">
        </form>
        <br>
        <label style="color:#fff">如果没有账号?</label>
        <br>
        <form action="/register" method="post">
            <input type="submit" id="register" value="注册">
        </form>
    </div>
</body>

<script>

</script>

</html>