package zui;

import org.junit.Test;
import org.junit.Assert;
public class DemoTest{
    @Test
    public void testSay(){
        Demo d = new Demo();
        String s = d.say("maven");
        Assert.assertEquals("hello maven", s);
    }
}