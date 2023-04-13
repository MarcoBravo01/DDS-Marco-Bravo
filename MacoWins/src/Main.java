import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.lang.Double;
import java.util.*;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {
    public static void main(String[] args) {
    }
}
class VentasDia{
    List <Venta> ventas = new ArrayList<>();
    public double ganaciasDeUnDia(Date fecha){
        return ventas.stream().filter(venta->venta.getFecha().equals(fecha)).mapToDouble(Venta::importe).sum();
    }
}
class Venta{
    List <Item> itemsVendidos = new ArrayList<>();
    Date fecha = new Date();
    public Date getFecha(){return fecha;}
    public double importe(){return itemsVendidos.stream()
            .mapToDouble(item -> item.getPrendaVendida().precio() * item.getCantidad())
            .sum();}
}
class VentaTarjeta extends Venta{
    int cantidadCuotas;
    int coef;
    @Override
    public double importe() {
        return super.importe() +super.importe()*cantidadCuotas*coef;
    }
}
class Item{
    Prenda prendaVendida;
    public Prenda getPrendaVendida(){return prendaVendida;}
    int cantidad;
    public int getCantidad(){return cantidad;}

}
class Prenda{
    int precioPropio;
    Estado estado;
    public double precio(){
        return estado.modificarPrecio(this.precioPropio);
    }
}
abstract class Estado{
    public abstract double modificarPrecio(int precio);
}
class Nueva extends Estado{
    public double modificarPrecio(int precio){
        return 0;
    }
}
class Promocion extends Estado{
    int valorFijo;
    public double modificarPrecio(int precio) {
        return precio - valorFijo;
    }
}
class Liquidacion extends Estado{
    public double modificarPrecio(int precio){
        return precio*0.5;
    }
}
