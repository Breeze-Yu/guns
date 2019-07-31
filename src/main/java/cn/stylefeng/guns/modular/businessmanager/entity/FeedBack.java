package cn.stylefeng.guns.modular.businessmanager.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

@TableName("FEEDBACK")
public class FeedBack implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	 /**
     * 主键id
     */
	@TableId(value = "recordid")
	private String recordid;
    
    @TableField(value = "account")
	private String account;//客户账户
    
    @TableField(value = "qqorwxnum")
	private String qqorwxnum;
    
    @TableField(value = "phonenum")
	private String phonenum;
	
    @TableField(value = "nickname")
	private String nickname;
	
    @TableField(value = "fbcontent")
	private String fbcontent;
	
    @TableField(value = "respcontent")
	private String respcontent;
	
    @TableField(value = "fbtime")
	private String fbtime;
	
    @TableField(value = "resptime" , fill = FieldFill.UPDATE)
	private Date resptime;
	
    @TableField(value = "respondent")
	private String respondent;//回复人
	
    @TableField(value = "fbpicture")
	private byte[] fbpicture;
	
    @TableField(value = "respstate")
	private String respstate;
	
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getQqorwxnum() {
		return qqorwxnum;
	}
	public void setQqorwxnum(String qqorwxnum) {
		this.qqorwxnum = qqorwxnum;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFbcontent() {
		return fbcontent;
	}
	public void setFbcontent(String fbcontent) {
		this.fbcontent = fbcontent;
	}
	public String getRespcontent() {
		return respcontent;
	}
	public void setRespcontent(String respcontent) {
		this.respcontent = respcontent;
	}
	public String getFbtime() {
		return fbtime;
	}
	public void setFbtime(String fbtime) {
		this.fbtime = fbtime;
	}
	public Date getResptime() {
		return resptime;
	}
	public void setResptime(Date resptime) {
		this.resptime = resptime;
	}
	public String getRespondent() {
		return respondent;
	}
	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}
	public byte[] getFbpicture() {
		return fbpicture;
	}
	public void setFbpicture(byte[] fbpicture) {
		this.fbpicture = fbpicture;
	}
	public String getRespstate() {
		return respstate;
	}
	public void setRespstate(String respstate) {
		this.respstate = respstate;
	}
	
}
