package org.pop.rs.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pop.rs.model.Message;
import org.pop.rs.model.RideMessage;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceTest {
    @Mock
    AccountService accountService;
    @Mock
    List<Message> messages;
    @Mock
    List<RideMessage> rideMessages;
    @InjectMocks
    MessageService messageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Message> messageList = new ArrayList<>();
        Message message = new Message();
        message.setRid("rid");
        message.setAid("2");
        messageService.add(message);
        messageList.add(message);
        messageService = new MessageService(messageList);
    }

    @Test
    public void testAdd()  {
        Message result = messageService.add(new Message());
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindById()  {
        Message result = messageService.findById("1");
        Assert.assertEquals("1", result.getMid());
    }

    @Test
    public void testFindByRid()  {
        List<Message> result = messageService.findByRid("rid");
        Assert.assertEquals(1,result.size());
    }
}
