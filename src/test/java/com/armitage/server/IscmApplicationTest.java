package com.armitage.server;

import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@Slf4j
public class IscmApplicationTest {

    @Autowired
    private PubSysBasicInfoBiz pubSysBasicInfoBiz;

    @Test
    public void contextLoads() {
        List<PubSysBasicInfo> all = pubSysBasicInfoBiz.findAll(null);
        log.info(all+"===========");

    }
}
