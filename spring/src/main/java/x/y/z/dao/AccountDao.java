package x.y.z.dao;

import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

public interface AccountDao {
    @Update("update tb_account set money = money - #{money} where name = #{name}")
    void outMoney(String out, Double money);

    @Update("update tb_account set money = money + #{money} where name = #{name}")
    void inMoney(String in, Double money);
}
