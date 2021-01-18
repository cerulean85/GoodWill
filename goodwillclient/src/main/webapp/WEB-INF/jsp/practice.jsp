<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>작업 목록</title>

    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.7.0/moment.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>


    <script>

        $(document).ready(function() {

            $('#search_organization').click(function (e) {

                alert('qwfqwf')
                $.ajax({
                    url: "http://localhost:7072/get_work_table",
                    type: 'GET',
                    // data: mData,
                    // dataType: "json",
                    success: function (d) {

                    },
                    fail: function (e) {

                    },
                    error: function (xhr) {

                    }
                });
            });


        });

    </script>
</head>
<body>

<table>
    <button id="search_organization" type="button" class="action" style="width: 80px; height: 3.2rem;" >찾기</button>
    <c:forEach var="p" items="${list}">
        <tr>
            <td>${p.groupId}</td>
            <td>${p.topicName}</td>
            <td>${p.keyword}</td>
            <td>${p.startDate}</td>
            <td>${p.endDate}</td>
            <td>${p.currentState}</td>
            <td>${p.finishedWorkCount / p.totalWorkCount}</td>
        </tr>
    </c:forEach>
    <tr>
    <td>333</td>
    </tr>
<%--<c:forEach var="p" items="${list}">--%>
<%--${p.keyword}--%>
<%--</c:forEach>--%>
</table>

</body>
</html>