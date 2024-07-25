package org.ustinian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ustinian.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    public String hello(HelloObject helloObject) {
        logger.info("接收到：{}", helloObject.getMessage());
        return "这是掉用的返回值，id=" + helloObject.getId();
    }
}
