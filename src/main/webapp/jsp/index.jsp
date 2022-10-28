<%@ page import="com.tictactoe.model.Sign" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link href="../static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <title>Tic-Tac-Toe</title>
</head>

<h1>Tic-Tac-Toe</h1>

<table>
    <tr>
        <td onclick="window.location='/logic?click=0'"><img src=${data.get(0).getSign()}></td>
        <td onclick="window.location='/logic?click=1'"><img src=${data.get(1).getSign()}></td>
        <td onclick="window.location='/logic?click=2'"><img src=${data.get(2).getSign()}></td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=3'"><img src=${data.get(3).getSign()}></td>
        <td onclick="window.location='/logic?click=4'"><img src=${data.get(4).getSign()}></td>
        <td onclick="window.location='/logic?click=5'"><img src=${data.get(5).getSign()}></td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=6'"><img src=${data.get(6).getSign()}></td>
        <td onclick="window.location='/logic?click=7'"><img src=${data.get(7).getSign()}></td>
        <td onclick="window.location='/logic?click=8'"><img src=${data.get(8).getSign()}></td>
    </tr>
</table>

<hr>

<h2>Level selected: ${level.getLevel()}</h2>

<!-- HTML button -->

<button onclick=popup("#openModal")>Change level</button>

<hr>
<c:set var="CROSSES" value="<%=Sign.CROSS%>"/>
<c:set var="NOUGHTS" value="<%=Sign.NOUGHT%>"/>

<c:if test="${winner == CROSSES}">
    <h1>CROSSES WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${winner == NOUGHTS}">
    <h1>NOUGHTS WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${draw}">
    <h1>IT'S A DRAW</h1>
    <br>
    <button onclick="restart()">Start again</button>
</c:if>

<!-- HTML modal window -->
<div id="openModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Choose level: </h3>
                <a href="#close" title="Close" class="close">×</a>
            </div>
            <div class="modal-body">
                <div>
                    <button onclick="restart('low')">LOW</button>
                </div>
                <br>
                <div>
                    <button onclick="restart('middle')">MIDDLE</button>
                </div>
                <br>
                <div>
                    <button onclick="restart('advanced')">ADVANCED</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- CSS modal window -->
<style>
    /* Default pop-up styles */
    .modal {
        position: fixed; /* fixed position */
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0,0,0,0.5); /* background */
        z-index: 1050;
        opacity: 0; /* modal window is transparent by default */
        -webkit-transition: opacity 200ms ease-in;
        -moz-transition: opacity 200ms ease-in;
        transition: opacity 200ms ease-in; /* transition animation */
        pointer-events: none; /* element isn't visible for mouse events */
        margin: 0;
        padding: 0;
    }
    /* When displaying a modal window */
    .modal:target {
        opacity: 1; /* making the window visible */
        pointer-events: auto; /* element is visible for mouse events */
        overflow-y: auto; /* adding scrolling along the y axis, when an element doesn't fit on the page */
    }
    /* Width of the modal window and its margins from the screen */
    .modal-dialog {
        position: relative;
        width: auto;
        margin: 10px;
    }
    @media (min-width: 576px) {
        .modal-dialog {
            max-width: 500px;
            margin: 30px auto; /* displaying the window in the center */
        }
    }
    /* Block styles with window content */
    .modal-content {
        text-align: center;
        position: relative;
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        background-color: #fff;
        -webkit-background-clip: padding-box;
        background-clip: padding-box;
        border: 1px solid rgba(0,0,0,.2);
        border-radius: .3rem;
        outline: 0;
    }
    @media (min-width: 768px) {
        .modal-content {
            -webkit-box-shadow: 0 5px 15px rgba(0,0,0,.5);
            box-shadow: 0 5px 15px rgba(0,0,0,.5);
        }
    }
    /* Window title styles */
    .modal-header {
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
        -webkit-box-pack: justify;
        -webkit-justify-content: space-between;
        -ms-flex-pack: justify;
        justify-content: space-between;
        padding: 15px;
        border-bottom: 1px solid #eceeef;
    }
    .modal-title {
        margin-top: 0;
        margin-bottom: 0;
        line-height: 1.5;
        font-size: 1.25rem;
        font-weight: 500;
    }
    /* Button "х" styles ("Close")  */
    .close {
        float: right;
        font-family: sans-serif;
        font-size: 24px;
        font-weight: 700;
        line-height: 1;
        color: #000;
        text-shadow: 0 1px 0 #fff;
        opacity: .5;
        text-decoration: none;
    }
    /* Styles for the closing button in focus or hover */
    .close:focus, .close:hover {
        color: #000;
        text-decoration: none;
        cursor: pointer;
        opacity: .75;
    }
    /* Block styles of the main window content */
    .modal-body {
        position: relative;
        -webkit-box-flex: 1;
        -webkit-flex: 1 1 auto;
        -ms-flex: 1 1 auto;
        flex: 1 1 auto;
        padding: 15px;
        overflow: auto;
    }
</style>

<script>
    function restart() {
        console.log("level " + `${level.getLevel()}`)

        $.ajax({
            url: '/restart?level=' + `${level.getLevel()}`,
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.assign("/start");
            }
        });
    }

    function restart(name) {
        $.ajax({
            url: '/restart?level=' + name,
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.replace("/start");
            }
        });
    }

    function popup(name) {
        window.open(name);
    }
</script>

</body>
</html>