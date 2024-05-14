package org.varelacasas.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.varelacasas.models.EstadoCobroConsumicion;
import org.varelacasas.models.entities.Camarero;
import org.varelacasas.models.entities.Pedido;
import org.varelacasas.services.CamareroService;
import org.varelacasas.services.PedidoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/gestionar-pedido")
public class GestionarPedidoServlet extends HttpServlet {

    @Inject
    private PedidoService service;

    @Inject
    private CamareroService camareroService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = Integer.valueOf((String) req.getAttribute("id"));
        }

        Pedido pedido = new Pedido();
        if (id > 0) {
            Optional<Pedido> o = service.get(id);
            if (o.isPresent()) {
                pedido = o.get();
            }
        }

        if(pedido.getCamareroResponsable() == null) {
            List<Camarero> camareros = camareroService.getAll();
            req.setAttribute("camareros", camareros);
        }

        pedido.setImporteTotal((float) pedido.getConsumiciones().stream()
                .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
                .sum());

       pedido.setImporteSatisfecho((float) pedido.getConsumiciones().stream()
               .filter(consumicion -> consumicion.getEstadoCobroConsumicion() == EstadoCobroConsumicion.PAGADO)
               .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
               .sum());

       pedido.setImporteRestante(pedido.getImporteTotal() - pedido.getImporteSatisfecho());

        req.setAttribute("pedido", pedido);
        getServletContext().getRequestDispatcher("/gestion.jsp").forward(req, resp);

    }
}
