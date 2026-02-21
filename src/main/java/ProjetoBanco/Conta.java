package ProjetoBanco;

import ProjetoBanco.interfaces.OperacoesBancarias;

public abstract class Conta implements OperacoesBancarias {

    private final String numeroconta;
    private final String dono;
    private double saldo;
    private boolean ativa;

    public Conta(String numeroConta, String dono) {
        this.numeroconta = numeroConta;
        this.dono = dono;
        this.saldo = 0;
        this.ativa = false;
    }

    public void abrirConta() {
        setAtiva(true);
    }

    public void fecharConta() {
        if (saldo == 0) {
            this.ativa = false;
        }
    }

    @Override
    public boolean depositar(double valor) {
        if (!ativa || valor <= 0) {
            return false;
        }
        creditar(valor);
        aposAlteracaoSaldo();
        return true;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            return false;
        }
        if (!ativa || valor > saldo) {
            return false;
        }
        debitar(valor);
        return true;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    protected void debitar(double valor){
        this.saldo -= valor;
        aposAlteracaoSaldo();
    }

    protected void creditar(double valor){
        this.saldo += valor;
    }

    public String getNumeroConta() {
        return numeroconta;
    }

    protected void aposAlteracaoSaldo(){

    }

    //public void setNumeroConta(String numeroConta) {
    //this.numeroconta = numeroConta;
    //}

    public String getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    private void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}


