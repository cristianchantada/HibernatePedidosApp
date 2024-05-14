<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<jsp:include page="layout/header.jsp" />

<c:set var="formatter" value='<%= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") %>' />
<c:set var="formattedDateTime" value='${pedido.fechaHorasPedidos.fechaHoraPedido.format(formatter)}' />
<c:set var="hourFormatter" value='<%= DateTimeFormatter.ofPattern("HH:mm:ss") %>' />

<h4 class="my-3">Pedido del bar ${pedido.bar.nombre} con id ${pedido.id}</h4>
<table class="table table-hover table-striped">
    <thead>
        <tr>
            <th>Fecha y hora de entrada</th>
            <td>${formattedDateTime} horas</td>
        </tr>
        <tr>
            <th>Estado</th>
            <td>${pedido.estado}</td>
        </tr>
        <tr>
            <th>Camarero</th>
            <td>${pedido.camareroResponsable.nombre} ${pedido.camareroResponsable.apellido}</td>
        </tr>
        <tr>
            <th>Grupo</th>
            <td>${pedido.grupo.nombre}</td>
        </tr>
        <tr>
            <th class="text-decoration-underline" colspan="2" >Registro de cobros</th>
        </tr>
        <tr>
            <th>Estado de cobro</th>
            <td>${pedido.estadoCobro}</td>
        </tr>
        <tr>
            <th>Importe total</th>
            <c:choose>
                <c:when test="${pedido.estado != 'CANCELADO'}">
                    <td><fmt:formatNumber value="${pedido.importeTotal}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</td>
                </c:when>
                <c:otherwise>
                     <td> 0,00 €</td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <th>Importe satisfecho</th>
            <c:choose>
                <c:when test="${pedido.estado != 'CANCELADO'}">
                    <td><fmt:formatNumber value="${pedido.importeSatisfecho}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</td>
                </c:when>
                <c:otherwise>
                     <td> 0,00 €</td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <th>Importe restante</th>
                  <c:choose>
                      <c:when test="${pedido.estado != 'CANCELADO'}">
                         <td><fmt:formatNumber value="${pedido.importeRestante}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</td>
                      </c:when>
                      <c:otherwise>
                           <td> 0,00 €</td>
                      </c:otherwise>
                  </c:choose>
        </tr>
        <c:if test="${pedido.fechaHorasPedidos.fechaHoraEnProceso != null || pedido.fechaHorasPedidos.fechaHoraCancelado != null || pedido.fechaHorasPedidos.fechaHoraModificado != null}">
            <tr>
                <th class="text-decoration-underline" colspan="2">Registro de tiempos del pedido:</th>
            </tr>
        </c:if>
        <c:if test="${pedido.fechaHorasPedidos.fechaHoraModificado != null}">
            <c:set var="horaModificado" value='${pedido.fechaHorasPedidos.fechaHoraModificado.format(hourFormatter)}' />
            <tr>
                <th>Hora modificación</th>
                <td>${horaModificado} hrs.</td>
            </tr>
        </c:if>
        <c:if test="${pedido.fechaHorasPedidos.fechaHoraEnProceso != null}">
            <c:set var="horaEnProceso" value='${pedido.fechaHorasPedidos.fechaHoraEnProceso.format(hourFormatter)}' />
            <tr>
                <th>Hora en preparación</th>
                <td>${horaEnProceso} hrs.</td>
            </tr>
        </c:if>
        <c:if test="${pedido.fechaHorasPedidos.fechaHoraDespachado != null}">
            <c:set var="horaDespachado" value='${pedido.fechaHorasPedidos.fechaHoraDespachado.format(hourFormatter)}' />
            <tr>
                <th>Hora despachado</th>
                <td>${horaDespachado} hrs.</td>
            </tr>
        </c:if>
        <c:if test="${pedido.fechaHorasPedidos.fechaHoraCerrado != null}">
            <c:set var="horaCerrado" value='${pedido.fechaHorasPedidos.fechaHoraCerrado.format(hourFormatter)}' />
            <tr>
                <th>Hora de pago y cierre</th>
                <td>${horaCerrado} hrs.</td>
            </tr>
        </c:if>
        <c:if test="${pedido.fechaHorasPedidos.fechaHoraCancelado != null}">
            <c:set var="horaCancelado" value='${pedido.fechaHorasPedidos.fechaHoraCancelado.format(hourFormatter)}' />
            <tr>
                <th>Hora de cancelación</th>
                <td>${horaCancelado} hrs.</td>
            </tr>
        </c:if>
    </thead>
    <tbody>
        <c:forEach items="${pedido.consumiciones}" var="c" varStatus="status">
            <tr class="table-primary">
                <th colspan="2">Consumición Id: ${c.id}</th>
            </tr>
            <tr>
                <th>Alumno</th>
                <td>${c.alumno.nombre} ${c.alumno.apellido}</td>
            </tr>
            <tr>
                <th>Producto</th>
                <td>${c.producto.nombreProducto}</td>
            </tr>
            <tr>
                <th>Precio</th>
                <td>${c.producto.precio} €</td>
            </tr>
            <tr>
                <th>Estado de cobro</th>
                <td>${c.estadoCobroConsumicion}</td>
            </tr>
           <c:if test="${pedido.camareroResponsable != null}">
                <tr>
                <c:choose>
                    <c:when test="${c.estadoCobroConsumicion eq 'PAGADO'}">
                        <td colspan="2" ></td>
                    </c:when>
                    <c:when test="${pedido.estado eq 'DESPACHADO'}">
                         <td colspan="2" ><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/cobrar-consumicion?id=${pedido.id}&consumicion-id=${c.id}">Cobrar consumición</a></td>
                    </c:when>
                    <c:otherwise>
                         <td colspan="2" ><a class="btn btn-sm btn-success disabled" href="#">Cobrar consumición</a></td>
                    </c:otherwise>
                </c:choose>
                </tr>
           </c:if>
        </c:forEach>
        <c:if test="${pedido.camareroResponsable != null && pedido.estado != 'CERRADO' && pedido.estado != 'DESPACHADO'}">
            <tr>
                <th colspan="2">Cambiar estado</th>
            </tr>
            <tr>
                <td colspan="2"><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/despachar?id=${pedido.id}">Despachado</a></td>
            </tr>
        </c:if>
        <tr>
            <td colspan="2"><a class="btn btn-info my-10" href="${pageContext.request.contextPath}/pedidos">Volver</a></td>
        </tr>
    </tbody>
</table>
<jsp:include page="layout/footer.jsp" />