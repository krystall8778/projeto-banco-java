package ProjetoBanco;

import ProjetoBanco.interfaces.CobrancaMensal;

public class ContaCorrente extends Conta implements CobrancaMensal {

    private double taxaMensal;
    private double limiteEspecial;
    //private boolean emusochequeespecial;

    public ContaCorrente(String numeroConta, String dono, double limiteEspecial, double taxaMensal){
        super(numeroConta, dono);

        if (limiteEspecial < 0){
            throw new IllegalArgumentException("Limite Especial não pode ser negativo.");
        }
        if (taxaMensal < 0 ) {
            throw new IllegalArgumentException("Taxa mensal não pode ser negativa.");
        }
        this.limiteEspecial = limiteEspecial;
        this.taxaMensal = taxaMensal;
    }

    @Override
    public void pagarMensal() {
        if (!isAtiva()) {
            return;
        }
        debitar(taxaMensal);

    }

    @Override
    public boolean sacar(double valor){
        if (valor <=0){
            return false;
        }
        if (!isAtiva()){
            return false;
        }
        if (valor > getSaldoDisponivelParaSaque()){
            return false;
        }
        debitar(valor);
        return true;
    }

    public boolean isEmUsoChequeEspecial(){
        return getSaldo() < 0;
    }

    public double getValorUsadoChequeEspecial(){
        if (getSaldo() < 0){
            return Math.abs(getSaldo());
        } else {
            return 0;
        }
    }

    public double getLimiteDisponivel(){
        return limiteEspecial - getValorUsadoChequeEspecial();
    }

    public double getSaldoDisponivelParaSaque(){
        return getSaldo() + limiteEspecial;
    }

}

