package ProjetoBanco.interfaces;


public interface OperacoesBancarias {
    boolean depositar(double depositar);
    boolean sacar(double sacar);
    //void pagarMensal();
    double consultarSaldo();
}
