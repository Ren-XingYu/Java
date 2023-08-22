package x.y.z.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import x.y.z.dao.AccountDao;
import x.y.z.service.AccountService;
import x.y.z.service.LogService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private LogService logService;

    @Override
    public void transfer(String out, String in, Double money) {
        try {
            accountDao.outMoney(out,money);
            accountDao.inMoney(in,money);
        } finally {
            logService.log(out,in,money);
        }
    }
}
