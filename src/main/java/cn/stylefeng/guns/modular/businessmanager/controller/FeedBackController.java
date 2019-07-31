/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.businessmanager.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.DatasourceEnum;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.dictmap.FeedBackDict;
import cn.stylefeng.guns.core.common.constant.dictmap.NoticeMap;
import cn.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.constant.state.BizLogType;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.businessmanager.entity.FeedBack;
import cn.stylefeng.guns.modular.businessmanager.service.FeedBackService;
import cn.stylefeng.guns.modular.businessmanager.wrapper.FeedBackWrapper;
import cn.stylefeng.guns.modular.system.entity.Dept;
import cn.stylefeng.guns.modular.system.entity.Notice;
import cn.stylefeng.guns.modular.system.model.RoleDto;
import cn.stylefeng.guns.modular.system.service.NoticeService;
import cn.stylefeng.guns.modular.system.service.OperationLogService;
import cn.stylefeng.guns.modular.system.warpper.DeptWrapper;
import cn.stylefeng.guns.modular.system.warpper.LogWrapper;
import cn.stylefeng.guns.modular.system.warpper.NoticeWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/feedback")
public class FeedBackController extends BaseController {

    private String PREFIX = "/modular/businessmanager/feedback/";

    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "feedback.html";
    }
    
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String fbcontent,
            @RequestParam(required = false) String qqorwxnum,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String respcontent,
            @RequestParam(required = false) String respstate) {

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        //根据条件查询操作日志
        Page<Map<String, Object>> result = feedBackService.selectFeedBack(page, fbcontent,qqorwxnum,nickname,respcontent,respstate);

        return LayuiPageFactory.createPageInfo(new FeedBackWrapper(result).wrap());
    }
    
 
    @RequestMapping("/returnMessage/{recordid}")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public String returnMessage(@PathVariable("recordid") String recordid,Model model) {

        if (ToolUtil.isEmpty(recordid)) {
            throw new RequestEmptyException();
        }

        //缓存部门修改前详细信息
        FeedBack fb = feedBackService.getById(recordid);
        LogObjectHolder.me().set(fb);
        model.addAllAttributes(BeanUtil.beanToMap(fb));
        return PREFIX + "feedback_edit.html";
    }
    
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改意见反馈", key = "name", dict = FeedBackDict.class)
    @ResponseBody
    public ResponseData edit(FeedBack fb) {
        feedBackService.editFeedback(fb);
        return SUCCESS_TIP;
    }


}
