import static org.junit.Assert.*;
import org.junit.Test;

public class BigTestSuite {

    Calculator calc = new Calculator();

    @Test
    public void testAdd(){
        assertEquals(10, calc.add(2,4));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero(){
        calc.divide(10,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegative(){

        BankAccount acc = new BankAccount("John",500);
        acc.deposit(-50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawTooMuch(){

        BankAccount acc = new BankAccount("John",500);
        acc.withdraw(1000);
    }
}