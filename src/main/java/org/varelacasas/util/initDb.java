package org.varelacasas.util;

import jakarta.persistence.EntityManager;
import org.varelacasas.models.Estado;
import org.varelacasas.models.EstadoCobro;
import org.varelacasas.models.EstadoCobroConsumicion;
import org.varelacasas.models.entities.*;

import java.util.ArrayList;
import java.util.List;

public class initDb {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            /*Producto producto = new Producto();
            producto.setNombreProducto("bolsa de golosinas");
            producto.setPrecio(0.90F);

            Producto producto2 = new Producto();
            producto2.setNombreProducto("patatas fritas");
            producto2.setPrecio(1.90F);

            Alumno alumno = new Alumno();
            alumno.setNombre("Lucía");
            alumno.setApellido1("Míguez");

            Alumno alumno2 = new Alumno();
            alumno2.setNombre("Paula");
            alumno2.setApellido1("Míguez");

            List<Consumicion> consumiciones = new ArrayList<>();

            Consumicion consumicion = new Consumicion(alumno, producto);
            consumicion.setEstadoCobroConsumicion(EstadoCobroConsumicion.PENDIENTE);
            Consumicion consumicion2 = new Consumicion(alumno2, producto2);
            consumicion2.setEstadoCobroConsumicion(EstadoCobroConsumicion.PAGADO);

            consumiciones.add(consumicion);
            consumiciones.add(consumicion2);

            Pedido pedido = new Pedido();
            pedido.setListaConsumiciones(consumiciones);

            Bar bar = new Bar();
            bar.setNombre("Mocambo");
            pedido.setBar(bar);
            Grupo grupo = new Grupo();
            grupo.setNombre("Mecanicos Monkeys");
            pedido.setGrupo(grupo);
            Camarero camarero = new Camarero();
            camarero.setNombre("Lamia");
            pedido.setCamareroResponsable(camarero);
            pedido.setEstado(Estado.EN_COLA);
            pedido.setEstadoCobro(EstadoCobro.NADA);*/

            Producto producto = new Producto();
            producto.setNombreProducto("fanta de naranja");
            producto.setPrecio(1.90F);

            Producto producto2 = new Producto();
            producto2.setNombreProducto("magdalenas");
            producto2.setPrecio(1.10F);

            Alumno alumno = new Alumno();
            alumno.setNombre("María");
            alumno.setApellido1("Varela");

            Alumno alumno2 = new Alumno();
            alumno2.setNombre("Javier");
            alumno2.setApellido1("Fernández");

            List<Consumicion> consumiciones = new ArrayList<>();

            Consumicion consumicion = new Consumicion(alumno, producto);
            consumicion.setEstadoCobroConsumicion(EstadoCobroConsumicion.PENDIENTE);
            Consumicion consumicion2 = new Consumicion(alumno2, producto2);
            consumicion2.setEstadoCobroConsumicion(EstadoCobroConsumicion.PAGADO);

            consumiciones.add(consumicion);
            consumiciones.add(consumicion2);

            Pedido pedido = new Pedido();
            pedido.setConsumiciones(consumiciones);

            Bar bar = new Bar();
            bar.setNombre("Mar y Bellota");
            pedido.setBar(bar);
            Grupo grupo = new Grupo();
            grupo.setNombre("Administrativos al poder");
            pedido.setGrupo(grupo);
            Camarero camarero = new Camarero();
            camarero.setNombre("Ariel");
            camarero.setApellido("Lamela");
            pedido.setCamareroResponsable(camarero);
            pedido.setEstado(Estado.EN_PROCESO);
            pedido.setEstadoCobro(EstadoCobro.PARCIAL);

            em.persist(pedido);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }


    }
}
