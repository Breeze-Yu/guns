package cn.stylefeng.guns.modular.businessmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.businessmanager.entity.FeedBack;

public interface FeedBackMapper  extends BaseMapper<FeedBack> {

	Page<Map<String, Object>> selectFeedBack(@Param("page") Page page,@Param("fbcontent") String fbcontent,@Param("qqorwxnum") String qqorwxnum,
			@Param("nickname") String nickname,@Param("respcontent") String respcontent,@Param("respstate") String respstate);
}
