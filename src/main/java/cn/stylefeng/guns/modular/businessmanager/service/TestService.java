package cn.stylefeng.guns.modular.businessmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.stylefeng.guns.core.common.constant.DatasourceEnum;
import cn.stylefeng.guns.modular.businessmanager.mapper.TestMapper;
import cn.stylefeng.guns.modular.businessmanager.model.Test;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;


@Service
public class TestService   {

    @Autowired
    private TestMapper testMapper;
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public void testBiz() {
        Test test = new Test();
        test.setTestId("bizTest");
        testMapper.saveTest(test);
    }
    

    
}
