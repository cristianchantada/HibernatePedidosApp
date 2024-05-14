<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<jsp:include page="layout/header.jsp" />
<h3 class="my-3">Listado de pedidos (rol de camarero)</h3>
<table class="table table-hover table-striped">
    <tr>
        <th>Fecha y hora de entrada</th>
        <th>Estado</th>
        <th>Bar</th>
        <th>Grupo</th>
        <th>Importe total</th>
        <th>Importe satisfecho</th>
        <th>Importe restante</th>
        <th>Camarero</th>
        <th>Acción</th>
    </tr>
    <c:set var="formatter" value='<%= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") %>' />
    <c:forEach items="${pedidos}" var="p">
    <c:set var="formattedDateTime" value='${p.fechaHorasPedidos.fechaHoraPedido.format(formatter)}' />
    <tr>
        <td>${formattedDateTime} horas</td>
        <td>${p.estado}</td>
        <td>${p.bar.nombre}</td>
        <td>${p.grupo.nombre}</td>
        <c:choose>
            <c:when test="${p.estado != 'CANCELADO'}">
                <td><fmt:formatNumber value="${p.importeTotal}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</td>
            </c:when>
            <c:otherwise>
                 <td> 0,00 €</td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${p.estado != 'CANCELADO'}">
                <td><fmt:formatNumber value="${p.importeSatisfecho}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</td>
            </c:when>
            <c:otherwise>
                 <td> 0,00 €</td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${p.estado != 'CANCELADO'}">
               <td><fmt:formatNumber value="${p.importeRestante}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</td>
            </c:when>
            <c:otherwise>
                 <td> 0,00 €</td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${p.camareroResponsable == null && p.estado != 'CANCELADO'}">
                <form action="${pageContext.request.contextPath}/pedidos" method="post">
                    <div class="row mb-2">
                        <td>
                            <select name="camarero" id="camarero" class="form-select">
                                <option value="">--- seleccionar ---</option>
                                <c:forEach items="${camareros}" var="c">
                                    <option value="${c.id}" ${g.id.equals(pedido.camarero.id)? "selected": ""}>${c.nombre} ${c.apellido}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </div>
            </c:when>
            <c:otherwise>
                <td>${p.camareroResponsable.nombre} ${p.camareroResponsable.apellido}</td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${p.estado == 'CERRADO'}">
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/gestionar-pedido?id=${p.id}">Cerrado</a></td>
            </c:when>
            <c:when test="${p.camareroResponsable == null}">
                    <c:choose>
                        <c:when test="${p.estado == 'CANCELADO'}">
                            <td><a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/gestionar-pedido?id=${p.id}">Cancelado</a></td>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${p.estado == 'EN_MODIFICACION'}">
                                    <div class="row mb-2">
                                      <div>
                                        <td><input class="btn btn-sm btn-light disabled" type="submit" value="En modificación"></td>
                                      </div>
                                    </div>
                                    <input type="hidden" name="pedidoId" value="${p.id}">
                                </c:when>
                                <c:otherwise>
                                    <div class="row mb-2">
                                      <div>
                                        <td><input class="btn btn-sm btn-danger" type="submit" value="Gestionar"></td>
                                      </div>
                                    </div>
                                    <input type="hidden" name="pedidoId" value="${p.id}">
                                </c:otherwise>
                            </c:choose>
                            </form>
                        </c:otherwise>
                    </c:choose>
            </c:when>
            <c:otherwise>
                <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/gestionar-pedido?id=${p.id}">Ver pedido</a></td>
            </c:otherwise>
        </c:choose>
    </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp" />
