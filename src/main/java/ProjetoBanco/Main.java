package ProjetoBanco;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SIMULAÇÃO BANCÁRIA ===");

        // Criando contas
        ContaCorrente contaCorrente = new ContaCorrente(
                "001",
                "Laryssa",
                1000.0, // limite especial
                25.0 // taxa mensal
        );

        ContaPoupanca contaPoupanca = new ContaPoupanca(
                "002",
                "Laryssa",
                5   // 5% de rendimento
        );

        //abrindo as contas antes de operar
        contaCorrente.abrirConta();
        contaPoupanca.abrirConta();

        // Operações Conta Corrente
        contaCorrente.depositar(500);
        contaCorrente.sacar(200);

        // Operações Conta Poupança
        contaPoupanca.depositar(1000);
        contaPoupanca.aplicarRendimento();

        // Exibindo resultados
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Saldo Conta Corrente: " + contaCorrente.getSaldo());
        System.out.println("Saldo Conta Poupança: " + contaPoupanca.getSaldo());
    }
}