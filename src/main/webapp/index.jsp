<jsp:include page="layout/header.jsp" />

<h3 class="my-3">Pedidos Cristian App</h3>
<h5>Esta es la interfaz de desarrollo</h5>

<ul class="list-group">
    <li class="list-group-item"><div class="alert alert-info">Utiliza el enlace inferior para probar como camarero: </div></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/pedidos">mostrar pedidos</a></li>
    <li class="list-group-item"><div class="alert alert-info">Utiliza los enlaces inferiores para probar como alumno :</div></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/form">crear un pedido</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/modificar">modificar un pedido</a></li>
</ul>
<jsp:include page="layout/footer.jsp" />