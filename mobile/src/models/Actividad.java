package models;

public class Actividad {

    private int id;
    private String nombre;
    private int plazasMaximas;
    private int plazasOcupadas;

    public Actividad() {}

    public Actividad(int id, String nombre, int plazasMaximas, int plazasOcupadas) {
        this.id = id;
        this.nombre = nombre;
        this.plazasMaximas = plazasMaximas;
        this.plazasOcupadas = plazasOcupadas;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPlazasMaximas() { return plazasMaximas; }
    public int getPlazasOcupadas() { return plazasOcupadas; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPlazasMaximas(int plazasMaximas) { this.plazasMaximas = plazasMaximas; }
    public void setPlazasOcupadas(int plazasOcupadas) { this.plazasOcupadas = plazasOcupadas; }
}