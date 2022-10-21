<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="static/level.css" rel="stylesheet">
    <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>Level</title>
</head>
<body>
<div class="container">
<h1>Choose level: </h1>
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

<script>
    function restart(level) {
        $.ajax({
            url: '/restart?level=' + level,
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.replace("/start");
            }
        });
    }
</script>

</body>
</html>
