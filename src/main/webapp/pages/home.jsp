<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="/js/jquery.js" type="text/javascript"></script>
</head>
<body style="background-color: darkgrey;  text-align: center">
<h2 style="font-size: 40pt; text-align: center">DOMINO</h2>
<form id="domino">
    <div style="width: 100%; display: inline-block">
    <c:forEach items="${dominoes}" var="domino">
        <div style="float: left; margin: 3px; width: 40px">
            <div style="float: left; border: solid black 1px; width: 40px"><img src="/images/${domino.firstNum}-1.jpg"
                                                                                style="height: 40px"/>
                <img
                    src="/images/${domino.secondNum}-1.jpg" style="height: 40px"/>
            </div>
        </div>
    </c:forEach>
    </div>
    <span id="random"
          style="float: left; margin: 10px; background-color: white; width: 150px;  height: 20px; cursor: pointer"
          onclick="getDomino()">get random market</span>
    <span id="randomNum"
          style="float: left; margin: 10px; background-color: white; width: 150px;  height: 20px; cursor: pointer"
          onclick="getRandomDomino()">get market</span>
    <input style="float: left; width: 40px; margin: 10px" id="num" name="num" type="text"/> <span id="items" style="float: left; margin: 10px">items</span>
</form>
<form id="chain">
    <div style="float: left; width: 100%" id="market"></div>
    <span style="float: left; margin: 10px; display: none; background-color: white; width: 150px; height: 20px; cursor: pointer"
            id="showLongest" onclick="showLongest()">show longest
    </span>
    <span style="float: left; margin: 10px; display: none; background-color: white; width: 150px; height: 20px; cursor: pointer"
            id="showAll" onclick="showAll()">show all
    </span>
    <span style="float: left; margin: 10px; display: none; background-color: white; width: 150px; height: 20px; cursor: pointer"
          id="clear" onclick="getClear()">clear</span>
</form>

</body>
</html>
<script>
    function showAll() {
        $('#chain').attr('method', "post");
        $('#chain').attr('action', "/get-chain").submit();
    }
    function showLongest() {
        $('#chain').attr('method', "get");
        $('#chain').attr('action', "/get-chain").submit();
    }
    function getClear(){
        $('#market').html(' ');
        $('#showAll').css('display', 'none');
        $('#showLongest').css('display', 'none');
        $('#clear').css('display', 'none');
        $('#random').css('display', 'block');
        $('#randomNum').css('display', 'block');
        $('#num').css('display', 'block');
        $('#items').css('display', 'block');
    }
    function getDomino() {
        var formData = $('#Domino');
        $.ajax({
            type: "POST",
            url: '/get-domino',
            data: formData.serialize(),
            success: function (data) {
                $('#market').html(data);
                $('#showAll').css('display', 'block');
                $('#showLongest').css('display', 'block');
                $('#clear').css('display', 'block');
                $('#random').css('display', 'none');
                $('#randomNum').css('display', 'none');
                $('#num').css('display', 'none');
                $('#items').css('display', 'none');
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
    function getRandomDomino() {
        var formData = $('#Domino');
        var regexpNum = new RegExp("^[2-6]$");
        var num = $('#num').val();
        if(num!='' && regexpNum.test(num)) {
            $.ajax({
                type: "GET",
                url: '/get-domino?num=' + num,
                data: formData.serialize(),
                success: function (data) {
                    $('#market').html(data);
                    $('#showAll').css('display', 'block');
                    $('#showLongest').css('display', 'block');
                    $('#clear').css('display', 'block');
                    $('#random').css('display', 'none');
                    $('#randomNum').css('display', 'none');
                    $('#num').css('display', 'none');
                    $('#items').css('display', 'none');
                },
                error: function (e) {
                    console.log(e);
                }
            });
        }else{
            alert('input number between 2 and 6');
        }
    }
</script>