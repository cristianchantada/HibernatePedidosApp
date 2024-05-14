package org.varelacasas.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.varelacasas.models.Estado;
import org.varelacasas.models.entities.Pedido;
import org.varelacasas.services.PedidoService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/despachar")
public class CambiarEstadoPedidoServlet extends HttpServlet {

    @Inject
    private PedidoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer pedidoId;
        try {
            pedidoId = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            pedidoId = 0;
        }

        Pedido pedido;
        if (pedidoId > 0) {
            Optional<Pedido> o = service.get(pedidoId);
            if (o.isPresent()) {
                pedido = o.get();
                pedido.setEstado(Estado.DESPACHADO);
                pedido.getFechaHorasPedidos().setFechaHoraDespachado(LocalDateTime.now());
                service.save(pedido);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/gestionar-pedido?id=" + pedidoId);
    }
}
