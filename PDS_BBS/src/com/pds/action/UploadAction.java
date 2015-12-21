package com.pds.action;

import java.io.File;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.User;
import com.pds.util.FileUpload;

public class UploadAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
    private File pic;//句柄
    private String picFileName;//源文件名
    private String picContentType;//源文件的文件类型
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	public String execute()
	{
	   String filePath = "";
	   User user = (User) session.get("user");
	   if(user != null){
		   String userName = user.getUsername();
			try {
				//这里得到的是文件在硬盘上的地址  稍后需要处理一下
				filePath = FileUpload.imgFileUpload(pic, picFileName, userName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ((filePath!=null) ||(filePath!="")){
				new UserDaoImpl().updateUserImgByUserName(userName, filePath.substring(filePath.indexOf("\\PDS_BBS")));
			    this.addActionMessage("文件路径为:"+filePath);	
			    return SUCCESS;
			}
	   }
		return null;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
}
