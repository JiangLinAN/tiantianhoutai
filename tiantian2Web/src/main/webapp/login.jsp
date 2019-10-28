<%--
  Created by IntelliJ IDEA.
  User: NoRe
  Date: 2019/10/25
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <!--1引入bootstrap的css-->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <!--2jquery-->
    <script src="js/jquery-1.12.4.js" type="text/javascript"></script>
    <!--3bootstrap.js-->
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <!--4 validate-->
    <script src="js/jquery.validate.js" type="text/javascript"></script>
    <!--5 中文库-->
    <script src="js/messages_zh.js" type="text/javascript"></script>
</head>
<body>
<div>
    <div class="container" style="margin-top: 100px">
        <div class="row clearfix">
            <div class="col-md-12 column">
                    <div class="form-group">
                        <label for="username" class="col-sm-7 control-label">账号:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-7 control-label">密码:</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="checkbox">
                                <label><input type="checkbox" />记住账号和密码</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" onclick="login()" style="width: 200px;height: 50px">登 录</button>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="application/javascript">
    function login(){
        $.ajax({
        type:"post",
        url:"http://localhost:8080/tiantian2Web_war_exploded/user/login",
        contentType:"application/Json",
        data:JSON.stringify({
            "username":$("#username").val(),
            "password":$("#password").val()
        }),
        success: function (status) {
        alert(status.msg)
        if (status.msg=="登录成功"){
             location.href="http://localhost:8080/tiantian2Web_war_exploded/goods/allGoods?num=1"
         }
         },
        error: function () {
        alert("登录错误")
        }
    })
    }


</script>
</html>
