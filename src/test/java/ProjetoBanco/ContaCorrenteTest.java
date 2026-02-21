package ProjetoBanco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaCorrenteTest {

    ContaCorrente conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaCorrente("123", "laryssa", 500, 20);
        conta.abrirConta();

        conta.depositar(200);
    }

    @Test
    public void deveDepositarCorretamente(){
        assertEquals(200, conta.getSaldo());
    }

    @Test
    public void naoDeveAutorizarSaqueMaiorQSaldoMaisLimite (){
        boolean resultado = conta.sacar(800);
        assertFalse(resultado);
    }

    @Test
    public void devePermitirSaqueUsandoLimite () {
        boolean resultado = conta.sacar(600);
        assertTrue(resultado);
    }

    @Test
    public void deveCobrarTaxaMensal () {
        conta.pagarMensal();
        assertEquals(180, conta.getSaldo());
    }

}
