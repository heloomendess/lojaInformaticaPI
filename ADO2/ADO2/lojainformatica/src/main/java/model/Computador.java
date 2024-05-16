package model;

public class Computador {
    private static String marca = "HeloisaMendes";
    private String HD, processador;
    private int id;

    
    //GETTERS E SETTERS
    public static String getMarca() {
        return marca;
    }

    public static void setMarca(String marca) {
        Computador.marca = marca;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String Processador) {
        this.processador = Processador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    //CONSTRUTORES
    public Computador() {
    }

    //construtor para enviar ao banco
    public Computador(String HD, String processador) {
        this.HD = HD;
        this.processador = processador;
    }

    //construtor para receber do banco
    public Computador(String HD, String processador, int id, String marca) {
        this.HD = HD;
        this.processador = processador;
        this.id = id;
        this.marca = marca;
    }
    
    
}
