<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="/js/jquery.js" type="text/javascript"></script>
</head>
<script>
    function saves(){
        var ids = '${ids}';
        var formData = $('#saveChains');
        var input = $("<input>")
            .attr("type", "hidden")
            .attr("name", "ids").val(ids);
        $('#chainName').append($(input));
        if($('#chainName').val()!='') {
        $.ajax({
            type: "POST",
            url: '/save-chains',
            data: formData.serialize(),
            success: function (data) {
                alert(data);
            },
            error: function (e) {
                console.log(e);
            }
        });
        }else{
            alert("input name of market")
        }
    }
</script>
<body style="background-color: darkgrey;  text-align: center">
<h2 style="font-size: 40pt; text-align: center">Markets</h2>
<form id = "saveChains">
<input name="chainName" id="chainName" type="text">
<span onclick="saves()">save</span>
</form>
<div style="float: left; width: 80%; ">
<c:forEach items="${markets}" var="market">
    <div style="float: left; width: 100%; display: inline-block; margin-top:20px">
        <c:forEach items="${market}" var="domino">
            <c:if test="${domino.firstNum != domino.secondNum}">
                <div style="float: left; width: 81px; margin-left: 1px;">
                    <div style="float: left; border: solid black 1px;  margin-top: 20px;  width: 81px"><img
                            src="/images/${domino.firstNum}.jpg" style="height: 40px; float: left;"/>
                        <div style="float: left; height: 40px; width: 1px; background-color: lightgrey"></div><img
                            src="/images/${domino.secondNum}.jpg" style="height: 40px; float: left;"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${domino.firstNum == domino.secondNum}">
                <div style="float: left; width: 40px; margin-left: 1px;" >
                    <div style="float: left; border: solid black 1px;  width: 40px"><img
                            src="/images/${domino.firstNum}-1.jpg" style="height: 40px"/>
                        <div style="height: 1px; width: 40px; background-color: lightgrey"></div><img
                            src="/images/${domino.secondNum}-1.jpg" style="height: 40px"/>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</c:forEach>
</div>
<div style="float:right; width: 10%;">
    <h2>saved markets</h2>
    <c:forEach items="${names}" var="name">
        <form action="/market-name" method="post">
        <input style="float: left; width:120px" type="submit" name="marketname" value="${name}">
        </form>
    </c:forEach>
</div>
</body>
</html>

