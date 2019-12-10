package cn.letcafe.mapper;

import cn.letcafe.model.HelloModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HelloMapper {

    /**
     * 插入 并查询id 赋给传入的对象
     */
    @Insert("INSERT INTO hello(key, value) VALUES(#{key}, #{value})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'hello')", before = false, keyProperty = "id", resultType = int.class)
    int insert(HelloModel model);

    /**
     * 根据 ID 查询
     * @param id id
     * @return 插入对象
     */
    @Select("SELECT * FROM hello WHERE id=#{id}")
    HelloModel select(int id);

    /**
     * 查询全部
     * @return 对象List
     */
    @Select("SELECT * FROM hello")
    List<HelloModel> selectAll();

    /**
     * 更新 value
     * @param model update对象
     * @return 是否成功
     */
    @Update("UPDATE hello SET value=#{value} WHERE id=#{id}")
    int updateValue(HelloModel model);

    /**
     * 根据 ID 删除
     * @param id 删除的id
     * @return 是否成功
     */
    @Delete("DELETE FROM hello WHERE id=#{id}")
    int delete(Integer id);

}