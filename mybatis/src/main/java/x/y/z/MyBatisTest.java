package x.y.z;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import x.y.z.domain.Brand;
import x.y.z.domain.User;
import x.y.z.mapper.BrandMapper;
import x.y.z.mapper.UserMapper;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testXml() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        // 存在硬编码, 使用mapper代理方式解决
        List<User> userList = session.selectList("x.y.z.mapper.UserMapper.selectAll");

        System.out.println(userList);

        session.close();
    }

    @Test
    public void testProxy() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        // mapper代理开发
        UserMapper userMapper = session.getMapper(UserMapper.class);

        List<User> users = userMapper.selectAll();

        System.out.println(users);

        session.close();
    }

    @Test
    public void testBrand() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        // mapper代理开发
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.getAll();

        System.out.println(brands);

        session.close();
    }

    @Test
    public void testBrandInsert() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        // 设置自动提交事务
        //SqlSession session = sqlSessionFactory.openSession(true);

        // mapper代理开发
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);

        Brand apple=new Brand();
        apple.setBrandName("iPhone15");
        apple.setCompanyName("Apple");
        apple.setDescription("Apple Inc.");
        apple.setOrdered(2);
        apple.setStatus(1);
        brandMapper.add(apple);
        // 需要设置主键返回才能获取到
        System.out.println(apple.getId());

        // 提交事务(mybatis默认需要手动提交事务)
        session.commit();

        session.close();
    }
}
