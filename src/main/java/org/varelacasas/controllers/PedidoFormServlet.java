package org.varelacasas.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.varelacasas.models.Estado;
import org.varelacasas.models.entities.*;
import org.varelacasas.services.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/form")
public class PedidoFormServlet extends HttpServlet {

    @Inject
    private PedidoService pedidoService;
    @Inject
    private AlumnoService alumnoService;
    @Inject
    private BarService barService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private ProductoService productoService;
    @Inject
    private ConsumicionService consumicionService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0;
        }

        Pedido pedido = new Pedido();
        if (id > 0) {
            Optional<Pedido> o = pedidoService.get(id);
            if (o.isPresent()) {
                pedido = o.get();
                pedido.setEstado(Estado.EN_MODIFICACION);
                pedido.getFechaHorasPedidos().setFechaHoraModificado(LocalDateTime.now());
                pedidoService.save(pedido);
            }
        }

        List<Alumno> alumnos = alumnoService.getAll();
        List<Bar> bares = barService.getAll();
        List<Grupo> grupos = grupoService.getAll();
        List<Producto> productos = productoService.getAll();

        List<Consumicion> consumiciones = new ArrayList<>();
        consumiciones.add(new Consumicion());

        req.setAttribute("pedido", pedido);
        req.setAttribute("grupos", grupos);
        req.setAttribute("alumnos", alumnos);
        req.setAttribute("bares", bares);
        req.setAttribute("productos", productos);
        req.setAttribute("consumiciones", consumiciones);

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer grupoId;
        try {
            grupoId = Integer.valueOf(req.getParameter("grupo"));
        } catch (NumberFormatException e){
            grupoId = 0;
        }

        Integer barId;
        try {
            barId = Integer.valueOf(req.getParameter("bar"));
        } catch (NumberFormatException e){
            barId = 0;
        }

        String[] productosSeleccionados = req.getParameterValues("producto");
        String[] alumnosSeleccionados = req.getParameterValues("alumno");

        Integer[] alumnoIdArray = new Integer[alumnosSeleccionados.length];
        Integer[] productoIdArray = new Integer[productosSeleccionados.length];
        Map<String, String> errores = new HashMap<>();

        for(int i = 0; i < productosSeleccionados.length; i++){
            try {
                alumnoIdArray[i] = Integer.valueOf(alumnosSeleccionados[i]);
            } catch (NumberFormatException e){
                alumnoIdArray[i] = 0;
                errores.put("alumno", "No puede haber ningún alumno vacío");
            }

            try {
                productoIdArray[i] = Integer.valueOf(productosSeleccionados[i]);
            } catch (NumberFormatException e){
                productoIdArray[i] = 0;
                errores.put("producto", "No puede haber ningún producto vacío");
            }
        }

        if (grupoId.equals(0)){
            errores.put("grupo", "el grupo es requerido!");
        }

        if (barId.equals(0)){
            errores.put("bar", "el bar es requerido!");
        }

        Integer pedidoId;
        Pedido pedido = new Pedido();
        try {
            pedidoId = Integer.valueOf(req.getParameter("id"));
            if (pedidoId > 0) {
                Optional<Pedido> o = pedidoService.get(pedidoId);
                if (o.isPresent()) {
                    pedido = o.get();
                }
            }
        } catch (NumberFormatException e){
            pedido.setId(null);
        }

        Grupo grupo;
        if (grupoId > 0) {
            Optional<Grupo> o = grupoService.get(grupoId);
            if (o.isPresent()) {
                grupo = o.get();
                pedido.setGrupo(grupo);
            }
        }

        Bar bar;
        if (barId > 0) {
            Optional<Bar> o = barService.get(barId);
            if (o.isPresent()) {
                bar = o.get();
                pedido.setBar(bar);
            }
        }

        pedido.getConsumiciones().clear();
        List<Consumicion> listaConsumiciones = new ArrayList<>();
        for(int i = 0; i < productoIdArray.length; i++) {

            Integer alumnoId = alumnoIdArray[i];
            Integer productoId = productoIdArray[i];

            Alumno alumno = new Alumno();
            if (alumnoId > 0) {
                Optional<Alumno> o = alumnoService.get(alumnoId);
                if (o.isPresent()) {
                    alumno = o.get();
                }
            }

            Producto producto = new Producto();
            if (productoId > 0) {
                Optional<Producto> o = productoService.get(productoId);
                if (o.isPresent()) {
                    producto = o.get();
                }
            }

            Consumicion consumicion = new Consumicion();
            consumicion.setAlumno(alumno);
            consumicion.setProducto(producto);
            pedido.getConsumiciones().add(consumicion);
        }

        if (errores.isEmpty()) {
            pedido.setEstado(Estado.EN_COLA);
            pedidoService.save(pedido);
            resp.sendRedirect(req.getContextPath() + "/pedidos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("grupos", grupoService.getAll());
            req.setAttribute("alumnos", alumnoService.getAll());
            req.setAttribute("bares", barService.getAll());
            req.setAttribute("productos", productoService.getAll());
            req.setAttribute("pedido", pedido);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
