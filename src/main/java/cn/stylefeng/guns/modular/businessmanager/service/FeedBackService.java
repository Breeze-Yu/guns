package cn.stylefeng.guns.modular.businessmanager.service;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.DatasourceEnum;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.businessmanager.entity.FeedBack;
import cn.stylefeng.guns.modular.businessmanager.mapper.FeedBackMapper;
import cn.stylefeng.guns.modular.system.entity.Role;
import cn.stylefeng.guns.modular.system.model.RoleDto;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FeedBackService extends ServiceImpl<FeedBackMapper, FeedBack> {

	static SimpleDateFormat sf_date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public Page<Map<String, Object>> selectFeedBack(Page page,String fbcontent,String qqorwxnum,String nickname,String respcontent,String respstate){
    	return this.baseMapper.selectFeedBack(page,fbcontent,qqorwxnum,nickname,respcontent,respstate);
    }
	
	@DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional(rollbackFor = Exception.class)
    public void editFeedback(FeedBack fb) {

        if (ToolUtil.isOneEmpty(fb,fb.getRecordid())) {
            throw new RequestEmptyException();
        }
    	ShiroUser user = ShiroKit.getUser();
    	fb.setRespondent(user.getName());
        fb.setResptime(new Date());
        FeedBack old = this.getById(fb.getRecordid());
        BeanUtil.copyProperties(fb, old);
        this.updateById(old);
    }

}
