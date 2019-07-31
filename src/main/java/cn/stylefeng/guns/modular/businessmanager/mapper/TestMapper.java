package cn.stylefeng.guns.modular.businessmanager.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import cn.stylefeng.guns.modular.businessmanager.model.Test;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-10
 */
@Mapper
public interface TestMapper  {
    
    @Insert("insert into test(test_id) values(#{testId})")
    int  saveTest(Test test);
}
