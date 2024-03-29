package com.app.bank;

import com.app.bank.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTests {
    @Autowired
    private TransactionService transactionService;

    @Test
    public void givenInvalidAccountNo_TryDeposit_WhenExceptionThrown_ThenAssertionSucceeds(){
        NoSuchElementException exception  = assertThrows(NoSuchElementException.class,()->{
            transactionService.deposit("wrongAccountNo");
        });

        String expectedMsg = "No value present";
        String actualMsg = exception.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    public void givenInvalidAccountNo_TryWithDraw_WhenExceptionThrown_ThenAssertionSucceeds(){
        NoSuchElementException exception  = assertThrows(NoSuchElementException.class,()->{
            transactionService.withdraw("wrongAccountNo");
        });

        String expectedMsg = "No value present";
        String actualMsg = exception.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }
}
