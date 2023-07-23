package x.y.z.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import x.y.z.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    /**
     * Mybatis提供了ParamNameResolver类来进行参数的封装
     * 单个参数:
     *  POJO类型: 直接使用,属性名和参数占位符名称一致
     *  Map集合：直接使用，键名 和 参数占位符名称一致
     *  Collection: 封装为Map集合,可以使用@Param注解替换Map集合中默认的arg键名
     *      map.put("arg0",collection集合)
     *      map.put("collection",collection集合)
     *  List：封装为Map集合,可以使用@Param注解替换Map集合中默认的arg键名
     *      map.put("arg0",List集合)
     *      map.put("collection",List集合)
     *      map.put("list",List集合)
     *  Array：封装为Map集合,可以使用@Param注解替换Map集合中默认的arg键名
     *      map.put("arg0",数组)
     *      map.put("array",数组);
     *  其他类型
     * 多个参数: 封装为Map集合,可以使用@Param注解替换Map集合中默认的arg键名
     *  map.put("arg0","参数值1")
     *  map.put("param1","参数值1")
     *  map.put("arg1","参数值2")
     *  map.put("param2","参数值2")
     * ----------------------------
     *  map.put("username","参数值1")
     *  map.put("param1","参数值1")
     *  map.put("arg1","参数值2")
     *  map.put("param2","参数值2")
     *
     *
     * 结论：将来都使用@Param注解来修改Map集合中默认的键名,并将修改后的名称来获取值，这样可读性更高
     */
    User select(@Param("username")String username,String password);

    // 使用注解开发(@Select @Insert @Update @Delete)
    // 注解完成简单功能,配置文件完成复杂功能
    @Select("select * from tb_user where id = #{id}")
    User selectById(int id);
}
