<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ToDo List</title>
    <link rel="stylesheet" href="resources/css/main-page.css">
</head>
<body>

<div class="todo-container">
    <h1>ToDo List</h1>

    <div class="tabs">
        <a href="?filter=all" class="tab ${filter == 'all' || empty filter ? 'active' : ''}">
            All (${numberOfAllTasks})
        </a>
        <a href="?filter=active" class="tab ${filter == 'active' ? 'active' : ''}">
            Active (${numberOfActiveTasks})
        </a>
        <a href="?filter=done" class="tab ${filter == 'done' ? 'active' : ''}">
            Done (${numberOfDoneTasks})
        </a>
    </div>

    <div class="task-container-wrapper">
        <c:choose>
            <c:when test="${empty tasks}">
                <div class="empty-state">
                    <c:choose>
                        <%-- Проверяем на 'all' или если фильтр вообще не задан в URL --%>
                        <c:when test="${param.filter == 'all' || empty param.filter}">
                            <p class="empty-title">There are no tasks</p>
                        </c:when>

                        <%-- Сравниваем со строкой 'active' --%>
                        <c:when test="${param.filter == 'active'}">
                            <p class="empty-title">There are no active tasks</p>
                        </c:when>

                        <%-- Сравниваем со строкой 'done' --%>
                        <c:when test="${param.filter == 'done'}">
                            <p class="empty-title">There are no done tasks</p>
                        </c:when>
                    </c:choose>
                </div>
            </c:when>
            <c:otherwise>
                <ul class="task-list">
                    <c:forEach items="${tasks}" var="task">
                        <li class="task-row">
                            <div class="task-box">
                                <span class="${task.status == 'DONE' ? 'task-text completed' : 'task-text'}">${task.title}</span>
                                <c:choose>
                                    <c:when test="${task.status == 'ACTIVE'}">
                                        <form action="done-task" method="post" style="margin: 0; display: flex;">
                                            <input type="hidden" name="id" value="${task.id}">
                                            <input type="hidden" name="filter" value="${param.filter}">
                                            <button type="submit" class="btn-check">✓</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="width: 26px; height: 26px; flex-shrink: 0;"></div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <form action="delete-task" method="post" style="margin: 0; display: flex;">
                                <input type="hidden" name="id" value="${task.id}">
                                <input type="hidden" name="filter" value="${param.filter}">
                                <button type="submit" class="btn-delete" title="Delete task">
                                    <span class="trash-icon"></span>
                                </button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

    <form action="add-task" method="post" class="add-form">
        <input type="text" name="title" class="input-task" placeholder="Enter new task..." required>
        <button type="submit" class="btn-add">Add</button>
    </form>
</div>

</body>
</html>
