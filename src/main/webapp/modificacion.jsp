<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<jsp:include page="layout/header.jsp" />
<h3>Modificar pedido (rol de alumno)</h3>
<table class="table table-hover table-striped">
    <tr>
        <th>Fecha y hora</th>
        <th>Bar</th>
        <th>Estado</th>
        <th>Grupo</th>
        <th>Selecciona</th>
        <th>Cancelación</th>
    </tr>
    <c:set var="formatter" value='<%= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") %>' />
    <c:forEach items="${pedidos}" var="p">
        <c:set var="formattedDateTime" value='${p.fechaHorasPedidos.fechaHoraPedido.format(formatter)}' />
        <tr>
            <td>${formattedDateTime} horas</td>
            <td>${p.bar.nombre}</td>
            <td>${p.estado}</td>
            <td>${p.grupo.nombre}</td>
            <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/form?id=${p.id}">Modificar</a></td>
            <td><a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/cancelar?id=${p.id}">Cancelar</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp" />
