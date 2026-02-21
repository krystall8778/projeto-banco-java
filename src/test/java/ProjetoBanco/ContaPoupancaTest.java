package ProjetoBanco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaPoupancaTest {

    ContaPoupanca conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaPoupanca("123", "Tifanny", 5);
        conta.abrirConta();

        conta.depositar(1000);

    }

    @Test
    public void deveDepositarCorretamente() {
        assertEquals(1000, conta.getSaldo());

    }

    @Test
    public void devePermitirSaqueSeHouverSaldo() {
        boolean resultado = conta.sacar(200);
        assertTrue(resultado);
    }

    @Test
    public void naoDevePermitirSaqueMaiorQSaldo() {
        boolean resultado = conta.sacar(1090);
        assertFalse(resultado);
    }

    @Test
    public void deveAplicarTaxaDeRendimentoCorretamente() {
        conta.aplicarRendimento();
        assertEquals(1050, conta.getSaldo());
    }

    @Test
    public void naoPermitidoDepositarValorNegativo() {
        boolean resultado = conta.depositar(-1000);
        assertFalse(resultado);
    }

    @Test
    public void naoPermitirSaqueComValorNegativo() {
        boolean resultado = conta.sacar(-10);
        assertFalse(resultado);
    }

    @Test
    public void saqueDoMesmoValorDoSaldo() {
        conta.sacar(1000);
        assertEquals(0, conta.getSaldo());
    }

    @Test
    public void taxaDeRendimentoComSaldoZero() {
        conta.sacar(1000);
        conta.aplicarRendimento();
        assertEquals(0, conta.getSaldo());
    }


}

