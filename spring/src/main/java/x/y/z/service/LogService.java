package x.y.z.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface LogService {
    // 无论成功还是失败,都需要记录日志,可以通过事务的传播行为控制(不加入事务管理员的事务,开启一个新的事务)
    // propagation:事务的传播行为:开启一个新事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void log(String out, String in, Double money);
}
