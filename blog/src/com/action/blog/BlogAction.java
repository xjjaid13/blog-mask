package com.action.blog;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.action.BaseAction;
import com.po.BlogSubject;
import com.po.BlogType;
import com.po.User;
import com.service.BlogSubjectMapperService;
import com.service.BlogTypeMapperService;
import com.util.DataHandle;

@Controller
@RequestMapping("blog")
public class BlogAction extends BaseAction{

	@Autowired
	BlogTypeMapperService blogTypeMapperService;
	
	@Autowired
	BlogSubjectMapperService blogSubjectMapperService;
	
	@RequestMapping("myIndex")
	public String myIndex(HttpSession session, Model model,
			HttpServletRequest request){
		return "blog/index";
	}
	
	@RequestMapping("myReturnBlogTypeTree")
	public void myReturnRssTypeTree(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		String id = DataHandle.returnValue(request, "id");
		User user = returnUser(session);
		JSONObject jsonObject = createJosnObject();
		BlogType blogType = new BlogType();
		JSONArray jsonArray = new JSONArray();
		response.setContentType("application/json; charset=utf-8");
		if("#".equals(id)){
			jsonObject.put("id", "0");
			jsonObject.put("parent", "#");
			jsonObject.put("text", "订阅");
			if(blogTypeMapperService.isChildren(-1, user.getUserId())){
				jsonObject.put("children", true);
			}
			jsonArray.add(jsonObject);
			response.getWriter().write(jsonArray.toString());
		}else{
			blogType.setUserId(user.getUserId());
			if("root".equals(id)){
				blogType.setParentId(0);
			}else{
				blogType.setParentId(Integer.parseInt(id));
			}
			List<BlogType> blogTypeList = blogTypeMapperService.selectList(blogType);
			if(blogTypeList != null && blogTypeList.size() > 0){
				for(BlogType blogTypeNew : blogTypeList){
					jsonObject = new JSONObject();
					jsonObject.put("id", blogTypeNew.getBlogTypeId() + "");
					jsonObject.put("parent", blogTypeNew.getParentId() + "");
					jsonObject.put("text", blogTypeNew.getTypeName());
					if(blogTypeMapperService.isChildren(blogTypeNew.getBlogTypeId(), user.getUserId())){
						jsonObject.put("children", true);
					}
					jsonArray.add(jsonObject); 
				}
			}
			response.getWriter().write(jsonArray.toString());
		}
	}
	
	@RequestMapping("myAddBlogType")
	public void myAddRssType(BlogType blogType,HttpServletResponse response,HttpSession session) throws IOException{
		User user = returnUser(session);
		blogType.setUserId(user.getUserId());
		
		int parentId = blogType.getParentId();
		if(parentId == 0){
			blogType.setParentString(";0;");
		}else{
			BlogType blogTypeParent = new BlogType();
			blogTypeParent.setBlogTypeId(parentId);
			blogTypeParent = blogTypeMapperService.select(blogType);
			blogType.setParentString(blogTypeParent.getParentString() + blogTypeParent.getBlogTypeId() + ";");
		}
		
		int rssTypeId = blogTypeMapperService.insertAndReturnId(blogType);
		JSONObject jsonObject = createJosnObject();
		jsonObject.put("rssTypeId", rssTypeId);
		writeResult(response, jsonObject);
	}
	
	@RequestMapping("myUpdateBlogType")
	public void myUpdateRssType(BlogType blogType,HttpServletResponse response) throws IOException{
		blogTypeMapperService.update(blogType);
		writeResult(response, createJosnObject());
	}
	
	@RequestMapping("myDleteBlogType")
	public void myDleteRssType(BlogType blogType,HttpServletResponse response) throws IOException{
		blogTypeMapperService.delete(blogType);
		writeResult(response, createJosnObject());
	}
	
	@RequestMapping("myBlogView")
	public String myBlogView(BlogSubject blogSubject,Model model){
		List<BlogSubject> blogSubjectList = blogSubjectMapperService.selectSubjectByType(blogSubject);
		model.addAttribute("blogSubjectList", blogSubjectList);
		return "blog/subject";
	}
	
}
