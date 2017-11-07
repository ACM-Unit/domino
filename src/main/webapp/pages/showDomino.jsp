<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 style="font-size: 30pt; text-align: center" >My Market</h2>
<c:forEach items="${showDomino}" var="domino">
    <div style ="float: left; margin: 3px; width: 40px">
        <div style="float: left; border: solid black 1px; width: 40px"><img src="/images/${domino.firstNum}-1.jpg" style="height: 40px"/><img
                src="/images/${domino.secondNum}-1.jpg" style="height: 40px"/>
        </div>
    </div>
    <input name="ids" type="hidden" value="${ids}">
    <input name="marketname" type="hidden" value="-">
</c:forEach>

