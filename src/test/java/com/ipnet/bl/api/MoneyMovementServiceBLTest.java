package com.ipnet.bl.api;

import com.ipnet.bl.ali.AliServiceImpl;
import com.ipnet.blservice.AliService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class MoneyMovementServiceBLTest {
//    @Autowired
//    private AliService impl;
    @Autowired
    private MoneyMovementServiceBL service;

    @Test
    public void retrieveDestacc() throws IOException {
        String result = service.retrieveDestacc("SandboxUser1","9188baa47f6a0d176380b195e69979e8f4a324148dd8fd64092c537e5231c02dc2de71d5aebb569aca955548dd8a619c9754a8e24d29a426ee91088ef8172071f095670449464d046de5841433255758cf9eaf1aff08a94dfeb812bb42c5da23cb1bd02e7d489a7873c2780919d34c5e36d0fee2f185b58fdb6c751a5f290f0fa13afb634a4ea7e6683e6c2340571440caa7031c2e91660362b0e3a6c822259cea432c6ae4dc5cc07908532df70a3a474fafb986bf7b0d922be6db7c0051eb73fbdc76732bceb26faaf40a1a5bbaf43e7c671ee4f749a1cf4a3db5a44a217553a9565dea05d7afee46523717a1beac92b897462183bbd9cfc1e323d3f272beb4");
        System.out.println(result);
    }

    @Test
    public void createTransfer() {
    }

    @Test
    public void confirmTransfer() {
    }
}