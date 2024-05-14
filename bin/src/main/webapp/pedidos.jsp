<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.reflect.*" %>
<%@ page import="org.varelacasas.mongo.controllers.PedidoDao, org.varelacasas.mongo.models.pedido"%>


<%

    List<pedido> pedidosLista = (List<pedido>) request.getAttribute("pedidosLista");
    String nombreBar = pedidosLista.get(0).getBar().getNombre();

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado pedidos</title>
</head>
<body>
    <header>
        <h1> Bar <%= nombreBar %></h1>
        <h2>Listado de productos</h2>
    </header>
    <main>
        <table>
            <tr>
                <th>Fecha y hora entrada</th>
                <th>Estado</th>
                <th>Grupo</th>
                <th>Camarero</th>
                <th>Importe pendiente</th>
                <th></th>
                <th></th>
            </tr>
        <% for(pedido aPedido : pedidosLista){ %>
            <a href=""><tr>
                <td><%= aPedido.getFechaHoraPedido() %></td>
                <td><%= aPedido.getEstado() %></td>
                <td><%= aPedido.getGrupo().getNombre() %></td>
                <td><%= aPedido.getCamarero().getNombre() %></td>
                <td> ImportePendiente aquí </td>
            </tr></a>
        <% } %>
        </table>


    </main>
</body>
</html>