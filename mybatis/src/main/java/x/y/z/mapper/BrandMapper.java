package x.y.z.mapper;

import org.apache.ibatis.annotations.Param;
import x.y.z.domain.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    List<Brand> getAll();

    Brand getById(Integer id);

    // 散装参数封装: @Param注解标识mapper文件中参数占位符名称,例如status参数传递给sql语句中status的占位符
    //List<Brand> selectByCondition(@Param("status") int status,@Param("companyName") String companyName,@Param("brandName") String brandName);

    // 实体参数封装: 对象的属性名称要和mapper文件中参数占位符名称一致
    //List<Brand> selectByCondition(Brand brand);

    // map参数封装: map对象的键的名称要和mapper文件中参数占位符名称一致
    List<Brand> selectByCondition(Map<String,Object> map);

    List<Brand> selectByConditionSingle(Brand brand);

    void add(Brand brand);

    int update(Brand brand);

    void deleteById(Integer id);

    // 如果不用Param注解,mybatis默认会将数组参数，封装为一个Map集合,map集合的key为array,值为数组
    void deleteByIds(@Param("ids") Integer[] ids);
}
