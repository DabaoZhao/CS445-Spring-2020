package org.pop.rs.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceTest {
    @Mock
    List<Account> accounts;
    @InjectMocks
    AccountService accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Account account = new Account("1", "firstName", "lastName", "phone", true);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        accountService = new AccountService(accountList);
    }

    @Test
    public void testAdd()  {
        Account result = accountService.add(new Account("2", "firstName", "lastName", "phone", true));
        Assert.assertEquals("2", result.getAid());
    }

    @Test
    public void testUpdate()  {
        boolean result = accountService.update("1", new Account("aid", "firstName", "lastName", "phone", true));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testFindById()  {
        Account result = accountService.findById("1");
        Assert.assertEquals("1",result.getAid());
    }

    @Test
    public void testUpdateStatus()  {
        accountService.updateStatus("1");
    }

    @Test
    public void testDelete()  {
        accountService.delete("1");
    }
}