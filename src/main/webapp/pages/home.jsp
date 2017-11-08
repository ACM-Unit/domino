<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/js/jquery.js" type="text/javascript"></script>
</head>
<body style="background-color: darkgrey;  text-align: center">
<a href="/domino" style="float:left; margin-left: 20px"> to main page</a>
<h2 style="font-size: 40pt; text-align: center">DOMINO</h2>
<form id="domino">
    <div style="width: 100%; display: inline-block">
    <c:forEach items="${dominoes}" var="domino">
        <div style="float: left; margin: 3px; width: 40px">
            <div style="float: left; border: solid black 1px; width: 40px"><img src="<c:url value='../images/${domino.firstNum}-1.jpg'/>"
                                                                                style="height: 40px"/>
                <img src="<c:url value='../images/${domino.secondNum}-1.jpg'/>" style="height: 40px"/>
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
            id="showLongest" onclick="show('Longest')">show longest
    </span>
    <span style="float: left; margin: 10px; display: none; background-color: white; width: 150px; height: 20px; cursor: pointer"
            id="showAll" onclick="show('All')">show all
    </span>
    <input type="hidden" name="begin" value="1">
    <input type="hidden" name="end" value="0">
    <span style="float: left; margin: 10px; display: none; background-color: white; width: 150px; height: 20px; cursor: pointer"
          id="clear" onclick="getClear()">clear</span>
    <input id="title" type="hidden" name="title" value="">
</form>
<div style="display: none; position: fixed; z-index:9999; top:0; left:0; height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/images/loader.gif') 50% 50% no-repeat; }" id="waiting"></div>
</body>
</html>
<script>
    function show(value) {
        $('#title').val(value);
        $('#chain').attr('method', "post");
        $('#chain').attr('action', "/get-market").submit();
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
        $('#waiting').css('display', 'block');
        $.ajax({
            type: "POST",
            url: '/get-domino',
            data: formData.serialize(),
            success: function (data) {
                $('#waiting').css('display', 'none');
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
        var regexpNum = new RegExp("^[2-9]$");
        var num = $('#num').val();
        if(num!='' && (regexpNum.test(num) || (num>1 && num<28))) {
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
            alert('input number between 2 and 28');
        }
    }
</script>