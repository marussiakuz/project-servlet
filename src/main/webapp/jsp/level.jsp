<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="static/level.css" rel="stylesheet">
    <title>Level</title>
</head>
<body>
<div class="container">
<h1>Choose level: </h1>
<div>
<button onclick="window.location='/start?level=low'">LOW</button>
</div>
<br>
<div>
<button onclick="window.location='/start?level=middle'">MIDDLE</button>
</div>
<br>
<div>
<button onclick="window.location='/start?level=advanced'">ADVANCED</button>
</div>
</div>
</body>
</html>
