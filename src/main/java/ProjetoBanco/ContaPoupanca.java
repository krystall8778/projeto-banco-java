package ProjetoBanco;

import ProjetoBanco.interfaces.AplicarRendimento;

public class ContaPoupanca extends Conta implements AplicarRendimento {

    private double taxaRendimento;


    public ContaPoupanca(String numeroConta, String dono, double taxaRendimento){
        super(numeroConta, dono);

        if(taxaRendimento < 0){
            throw new IllegalArgumentException("Taxa de rendimento não deve ser negativa!");
        }
        this.taxaRendimento = taxaRendimento / 100;
    }

    @Override
    public void aplicarRendimento(){
        if (!isAtiva()) {
            return;
        }
        double rendimento = getSaldo() * taxaRendimento;
        depositar(rendimento);
    }

}
