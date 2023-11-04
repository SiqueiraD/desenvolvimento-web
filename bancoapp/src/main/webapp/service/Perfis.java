package src.main.webapp.service;

public enum Perfis {
    GERENTEGERAL(2),
    GERENTEDECONTA(3),
    CLIENTE(4);
    private int valor;

    Perfis(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
