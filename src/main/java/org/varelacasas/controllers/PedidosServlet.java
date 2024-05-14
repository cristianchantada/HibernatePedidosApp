package org.varelacasas.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.varelacasas.models.Estado;
import org.varelacasas.models.EstadoCobroConsumicion;
import org.varelacasas.models.entities.Camarero;
import org.varelacasas.models.entities.Consumicion;
import org.varelacasas.models.entities.Pedido;
import org.varelacasas.services.CamareroService;
import org.varelacasas.services.PedidoService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet("/pedidos")
public class PedidosServlet extends HttpServlet {

    @Inject
    private PedidoService service;

    @Inject
    private CamareroService camareroService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Pedido> pedidos = service.getAll();
        List<Camarero> camareros = camareroService.getAll();

        for(Pedido pedido : pedidos){
            pedido.setImporteTotal((float) pedido.getConsumiciones().stream()
                    .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
                    .sum());

            pedido.setImporteSatisfecho((float) pedido.getConsumiciones().stream()
                    .filter(consumicion -> consumicion.getEstadoCobroConsumicion() == EstadoCobroConsumicion.PAGADO)
                    .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
                    .sum());

            pedido.setImporteRestante(pedido.getImporteTotal() - pedido.getImporteSatisfecho());
        }

        req.setAttribute("camareros", camareros);
        req.setAttribute("pedidos", pedidos);
        getServletContext().getRequestDispatcher("/pedidos.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer pedidoId;
        try {
            pedidoId = Integer.valueOf(req.getParameter("pedidoId"));
        } catch (NumberFormatException e) {
            pedidoId = 0;
        }

        Integer camareroId;
        try {
            camareroId = Integer.valueOf(req.getParameter("camarero"));
        } catch (NumberFormatException e) {
            camareroId = 0;
        }

        Pedido pedido = new Pedido();
        if (pedidoId > 0) {
            Optional<Pedido> o = service.get(pedidoId);
            if (o.isPresent()) {
                pedido = o.get();
            }
        }

        Camarero camarero;
        if (camareroId > 0) {
            Optional<Camarero> o = camareroService.get(camareroId);
            if (o.isPresent()) {
                camarero = o.get();
                pedido.setCamareroResponsable(camarero);
                pedido.setEstado(Estado.EN_PROCESO);
                pedido.getFechaHorasPedidos().setFechaHoraEnProceso(LocalDateTime.now());
                service.save(pedido);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/gestionar-pedido?id=" + pedido.getId());

    }
}
