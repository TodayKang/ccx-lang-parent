<%@ page import="org.apache.commons.lang3.time.DateFormatUtils" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>

<h4>
    <%=  request.getContextPath() %>
    <br>
    当前时间:<%= DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") %>
</h4>

</body>
</html>