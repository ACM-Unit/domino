<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/js/jquery.js" type="text/javascript"></script>
</head>
<script>
    function saves() {
        var ids = '${ids}';
        var formData = $('#saveChains');
        var input = $("<input>")
            .attr("type", "hidden")
            .attr("name", "ids").val(ids);
        $('#saveChains').append($(input));
        if ($('#chainName').val() != '') {
            $('#waiting').css('display', 'block');
            $.ajax({
                type: "POST",
                url: '/save-chains',
                data: formData.serialize(),
                success: function (data) {
                    $('#waiting').css('display', 'none');
                    if (data != 'Error, name already exists') {
                        var span = $('<span>').appendTo('#block-of-saved-chains');
                        var form = $('<form>')
                            .attr("id", "saved-chains")
                            .attr("action", "/get-market")
                            .attr("method", "post").appendTo(span);
                        var input1 = $("<input>")
                            .attr("type", "hidden")
                            .attr("name", "ids")
                            .attr("value", "${ids}").appendTo(form);
                        var input2 = $("<input>")
                            .attr("style", "float: left;  width:80%")
                            .attr("type", "submit")
                            .attr("value", data).appendTo(form);
                        var input3 = $("<input>")
                            .attr("name", "marketname")
                            .attr("type", "hidden")
                            .attr("value", "-").appendTo(form);
                        var input4 = $("<input>")
                            .attr("id", "market")
                            .attr("name", "market")
                            .attr("type", "hidden")
                            .attr("value", data).appendTo(form);
                        var input5 = $("<input>")
                            .attr("id", "begin")
                            .attr("name", "begin")
                            .attr("type", "hidden")
                            .attr("value", "1").appendTo(form);
                        var input6 = $("<input>")
                            .attr("id", "end")
                            .attr("name", "end")
                            .attr("type", "hidden")
                            .attr("value", "0").appendTo(form);
                        var input7 = $("<input>")
                            .attr("id", "title")
                            .attr("name", "title")
                            .attr("type", "hidden")
                            .attr("value", "${title}").appendTo(form);
                        var inSpan = $('<span>')
                            .attr("onclick", "deleteChain($(this), '" + data + "')")
                            .attr("style", "float: right; width:19%; background-color: red; cursor: pointer; margin-left: 1%; margin-top: 2px")
                            .attr("value", "del").text('del').appendTo(form);

                    } else {
                        alert(data);
                    }
                },
                error: function (e) {
                    console.log(e);
                }
            });
        } else {
            alert("input name of market")
        }
    }
    function deleteChain(span, name) {
        var formData = $('#saved-chains');
        formData.find("#market").val(name);
        $.ajax({
            type: "POST",
            url: '/delete-chains',
            data: formData.serialize(),
            success: function (data) {
                span.parents('span').remove();
                $('#waiting').css('display', 'none');
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
</script>
<body style="margin-left:4%; margin-right: 10%; background-color: darkgrey;  text-align: center">
<a href="/domino" style="float:left; margin-left: 20px"> to main page</a>
<a href="/start-game" style="float:right; margin-right: 20px">start</a>
<h2 style="font-size: 40pt; text-align: center">Chains</h2>
<c:if test="${marketSize != 0 && saved != true}">
    <form style="float:left; width: 80%; text-align: left;" id="saveChains">
        <input name="chainName" id="chainName" type="text">
        <input type="hidden" name="title" value="${title}">
        <span style="text-align: center; margin: 10px; border: solid 2px red; display: inline-block; background-color: white; width: 70px; height: 20px; cursor: pointer"
              onclick="saves()">save</span>
    </form>
</c:if>
<div style="float: left; width: 80%; ">
    <c:if test="${timeout==true}">
        <h2 style="float: left; width: 60%">time out</h2></br>
        <h2 style="float: left; width: 60%">too much result...</h2>
    </c:if>
    <c:if test="${timeout==false}">
        <c:forEach items="${markets}" var="market" begin="${begin}" end="${end}" varStatus="loop">
            <div style="float: left; width: 100%; display: inline-block; margin-top:20px">
                <c:forEach items="${market}" var="domino">
                    <c:if test="${domino.firstNum != domino.secondNum}">
                        <div style="float: left; width: 61px; margin-left: 1px;">
                            <div style="float: left; border: solid black 1px;  margin-top: 15px;  width: 61px"><img
                                    src="/images/${domino.firstNum}.jpg" style="height: 30px; float: left;"/>
                                <div style="float: left; height: 30px; width: 1px; background-color: lightgrey"></div>
                                <img
                                        src="/images/${domino.secondNum}.jpg" style="height: 30px; float: left;"/>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${domino.firstNum == domino.secondNum}">
                        <div style="float: left; width: 30px; margin-left: 1px;">
                            <div style="float: left; border: solid black 1px;  width: 30px"><img
                                    src="/images/${domino.firstNum}-1.jpg" style="height: 30px"/>
                                <div style="height: 1px; width: 30px; background-color: lightgrey"></div>
                                <img
                                        src="/images/${domino.secondNum}-1.jpg" style="height: 30px"/>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <c:if test="${loop.index==end}">
                <form id="result" action="/get-market" method="post">
                    <input type="hidden" name="ids" value="${ids}">
                    <input type="hidden" name="begin" value="${begin}">
                    <input type="hidden" name="end" value="${end}">
                    <input type="hidden" name="marketname" value="-">
                    <input type="hidden" name="title" value="${title}">
                    too much result...<input type="submit"value="more?"/>
                </form>
            </c:if>
        </c:forEach>
    </c:if>
</div>
<div id="block-of-saved-chains" style="float:right; width: 20%;">
    <h2>saved chains</h2>
    <c:forEach items="${names}" var="name">
        <span>
        <form id="saved-chains" action="/get-market" method="post">
            <input type="hidden" name="ids" value="${ids}">
            <input style="float: left; width:80%" type="submit" value="${name}">
            <input type="hidden" name="marketname" value="${name}">
            <input type="hidden" name="title" value="${title}">
            <input type="hidden" name="begin" value="1">
            <input type="hidden" name="end" value="0">
            <input id="market" type="hidden" name="market" value="${name}">
            <span onclick="deleteChain($(this), '${name}')"
                  style="float: right; width:19%; background-color: red; cursor: pointer; margin-left: 1%; margin-top: 2px"
                  name="del"
                  value="del">del</span>
        </form>
        </span>
    </c:forEach>
</div>
<div style="display: none; position: fixed; z-index:9999; top:0; left:0; height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/images/loader.gif') 50% 50% no-repeat; }"
     id="waiting"></div>
</body>
</html>
