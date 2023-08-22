package x.y.z.service;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface AccountService {
    // 事务一般加在接口上,降低耦合
    // rollbackFor:遇到某类异常进行回滚(非Error和RuntimeException)
    @Transactional(readOnly = true,timeout = -1)
    void transfer(String out,String in,Double money);
}
