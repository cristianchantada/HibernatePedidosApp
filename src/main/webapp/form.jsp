<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="layout/header.jsp" />

<h3 class="my-3">${pedido.id!=null && pedido.id>0? "Editar pedido": "Realizar pedido"} (rol de alumno)</h3>
<form action="${pageContext.request.contextPath}/form" method="post">
    <div class="row mb-2">
        <label for="grupo" class="col-form-label col-sm-2">Grupo</label>
        <div class="col-sm-4">
            <select name="grupo" id="grupo" class="form-select">
                <option value="">--- seleccionar ---</option>
                <c:forEach items="${grupos}" var="g">
                    <option value="${g.id}" ${g.id.equals(pedido.grupo.id)? "selected": ""}>${g.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errores != null && not empty errores.grupo}">
              <div style="color:red;">${errores.grupo}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="bar" class="col-form-label col-sm-2">Bar</label>
        <div class="col-sm-4">
            <select name="bar" id="bar" class="form-select">
                <option value="">--- seleccionar ---</option>
                <c:forEach items="${bares}" var="b">
                    <option value="${b.id}" ${b.id.equals(pedido.bar.id)? "selected": ""}>${b.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errores != null && not empty errores.bar}">
              <div style="color:red;">${errores.bar}</div>
        </c:if>
    </div>
    <hr>
    <h3>Seleccionar consumiciones</h3>
    <c:if test="${pedido.consumiciones.size() > 0}">
        <c:forEach items="${pedido.consumiciones}" var="c">
            <div id="consumicionesMod">
                <div class="consumicion">
                    <div class="row mb-2">
                        <label for="producto" class="col-form-label col-sm-2">Producto</label>
                        <div class="col-sm-4">
                            <select name="producto" class="form-select">
                                <option value="">--- seleccionar ---</option>
                                <c:forEach items="${productos}" var="p">
                                    <option value="${p.id}" ${p.id.equals(c.producto.id)? "selected": ""}>${p.nombreProducto}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <label for="alumno" class="col-form-label col-sm-2">Alumno</label>
                        <div class="col-sm-4">
                            <select name="alumno" class="form-select">
                                <option value="">--- seleccionar ---</option>
                                <c:forEach items="${alumnos}" var="a">
                                    <option value="${a.id}" ${a.id.equals(c.alumno.id)? "selected": ""}>${a.nombre} ${a.apellido}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <hr>
            </div>
        </c:forEach>
    </c:if>

    <!-- Nueva consumición -->
    <div id="consumicionesWrapper">
        <div class="consumicion">
        <hr>
            <div class="row mb-2">
                <label for="producto" class="col-form-label col-sm-2">Producto</label>
                    <div class="col-sm-4">
                        <select name="producto" class="form-select">
                            <option value="">--- seleccionar ---</option>
                            <c:forEach items="${productos}" var="p">
                                <option value="${p.id}">
                                    <span>${p.nombreProducto}</span>
                                    <span>${spaces} (${p.precio} €)</span>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
            </div>
            <div class="row mb-2">
                <label for="alumno" class="col-form-label col-sm-2">Alumno</label>
                <div class="col-sm-4">
                    <select name="alumno" class="form-select">
                        <option value="">--- seleccionar ---</option>
                        <c:forEach items="${alumnos}" var="a">
                            <option value="${a.id}">${a.nombre} ${a.apellido}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row my-3">
        <div class="col-sm-2 offset-sm-2">
            <button type="button" class="btn btn-sm btn-success" onclick="agregarConsumicion()">Añadir nueva [+]</button>
        </div>
        <div class="col-sm-2">
            <button type="button" class="btn btn-sm btn-danger" onclick="quitarUltimaConsumicion()">Quitar última [-]</button>
        </div>
    </div>
    <div class="row my-2">
        <div class="col-sm-4 offset-sm-2 my-20">
            <input class="btn btn-primary" type="submit" value="${pedido.id!=null && pedido.id>0? "Editar": "Realizar pedido"}">
        </div>
    </div>
    <input type="hidden" name="id" value="${pedido.id}">
</form>

<jsp:include page="layout/footer.jsp" />

<script>
    function agregarConsumicion() {
        var consumicionClone = document.querySelector('.consumicion').cloneNode(true);
        document.getElementById('consumicionesWrapper').appendChild(consumicionClone);
    }

    function quitarUltimaConsumicion() {
        var consumiciones = document.querySelectorAll('.consumicion');
        if (consumiciones.length > 1) {
            consumiciones[consumiciones.length - 1].remove();
        }
    }
</script>
