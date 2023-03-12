<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
    <link rel="stylesheet" href="css/login.css">
</head>

<body>
    <div class="box">
        <table>
            <tr>
                <form name="success" action="/demo" method="post">
                    <h2>欢迎，恭喜登陆成功</h2>
                    <#--使用JavaScript给a标签绑定submit时间,使超链接发送的是表单的post请求-->
                    <#--<a href='javascript:document.success.submit();'>跳转到人脸页面</a>-->
                    <label style="color:#fff">人脸识别:</label>
                        <input type="submit" name="toDemo" id="toDemo" value="人脸识别">
                </form>
            </tr>
            <br>
            <tr>
                <form name="logout" action="/doLogout">
                    <label style="color:#fff">注销操作:</label>
                    <input type="submit" name="logout" id="logout" value="注销操作">
                </form>
            </tr>
        </table>
    </div>
</body>

</html>