package org.pop.rs.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilsTest {

    @Test
    public void testGenerateId()  {
        String result = Utils.generateId("1");
        Assert.assertEquals("2", result);
    }

    @Test
    public void testFormatFloat()  {
        Float result = Utils.formatFloat(Float.valueOf(1.1f));
        Assert.assertEquals(Float.valueOf(1.1f), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testFormatFloat2()  {
        Float result = Utils.formatFloat("sf");
        Assert.assertEquals(Float.valueOf(1.1f), result);
    }

    @Test
    public void testIsSameDay()  {
        boolean result = Utils.isSameDay(new GregorianCalendar(2020, Calendar.APRIL, 5, 0, 0).getTime(), new GregorianCalendar(2020, Calendar.APRIL, 5, 0, 0).getTime());
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsBetween()  {
        boolean result = Utils.isBetween(new GregorianCalendar(2020, Calendar.APRIL, 5, 0, 0).getTime(), new GregorianCalendar(2020, Calendar.APRIL, 5, 0, 0).getTime(),
                new GregorianCalendar(2020, Calendar.APRIL, 5, 0, 0).getTime());
        Assert.assertFalse(result);
    }

    @Test
    public void testGetTomorrowDate()  {
        Date result = Utils.getTomorrowDate(new GregorianCalendar(2020, Calendar.APRIL, 5, 0, 0).getTime());
        Assert.assertEquals(new GregorianCalendar(2020, Calendar.APRIL, 6, 0, 0).getTime(), result);
    }

    @Test
    public void testMain()  {
        Utils.main(new String[] { "args" });
    }

    @Test
    public void testInValidValidDate()  {
        boolean result = Utils.isValidDate("date");
        Assert.assertFalse(result);
    }

    @Test
    public void testInValidTime()  {
        boolean result = Utils.isValidTime("time");
        Assert.assertFalse(result);
    }

    @Test
    public void testValidDate()  {
        boolean result = Utils.isValidDate("12-Mar-1984");
        Assert.assertTrue(result);
    }

    @Test
    public void testValidTime()  {
        boolean result = Utils.isValidTime("10:22");
        Assert.assertTrue(result);
    }

}

