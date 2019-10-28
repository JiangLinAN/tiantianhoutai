<%--
  Created by IntelliJ IDEA.
  User: NoRe
  Date: 2019/10/28
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品</title>
</head>
<body>
<h2>修改界面</h2>
<table>
<form id="updateForm" enctype="multipart/form-data">
        <tr><input name="id" value="${good.id}" readonly></tr>
        <tr><input name="goodName" value="${good.goodName}"></tr>
        <tr><input type="file" name="source"><img height="21px" id="simg"></tr>
        <tr><input type="button" value="上传" onclick="upload();"></tr>
</form>
    <img id="img"/>
</table>
</body>
<script>
    function upload() {
        //创建xhr
        xhr = new XMLHttpRequest();
        xhr.open("post","${pageContext.request.contextPath}/goods/addPicture");
        //设置监听：获取响应内容
        xhr.onreadystatechange=function(){
            if(xhr.readyState==4){
                ret = xhr.responseText //接受响应内容：图片在服务器的存储位置(访问路径)
                console.log(ret)
                //回显上传的图片
                document.getElementById("img").src="/tiantian2Web_war_exploded/image/"+ret;
            }
        }
        //FomrData(form的dom对象)
        xhr.send(new FormData(document.forms[0])) //发送请求，携带form的所有数据
    }
</script>
</html>
