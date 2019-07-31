package cn.stylefeng.guns.modular.businessmanager.model;

import lombok.Data;

@Data
public class FeedBack {
	
	private String recordid;	
	private String account;//客户账户
	private String qqorwxnum;
	private String phonenum;
	private String nickname;
	private String fbcontent;
	private String respcontent;//回复人
	private String fbtime;
	private String resptime;
	private String respondent;
	private byte[] fbpicture;
	private String respstate;
	

}
