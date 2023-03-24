package com.app.bank;

import com.app.bank.service.StatementService;
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
public class StatementServiceTests {
    @Autowired
    private StatementService statementService;

    @Test
    public void givenInvalidAccountNo_TryPrintStatement_WhenExceptionThrown_ThenAssertionSucceeds(){
        NoSuchElementException exception  = assertThrows(NoSuchElementException.class,()->{
            statementService.printStatement("wrongAccountNo");
        });

        String expectedMsg = "No value present";
        String actualMsg = exception.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }
}
