package cn.stylefeng.guns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.stylefeng.guns.modular.businessmanager.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GunsApplication.class)
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Test
	public void test() {
		testService.testBiz();
	}

}
