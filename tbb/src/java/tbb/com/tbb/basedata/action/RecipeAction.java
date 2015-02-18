package com.tbb.basedata.action;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;
import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.PageList;
import com.newbee.tmf.core.QueryParams;
import com.newbee.tmf.util.RequestUtils;
import com.newbee.tmf.util.ValueUtils;
import com.tbb.basedata.domain.Recipe;
import com.tbb.basedata.domain.Restraunt;
import com.tbb.basedata.service.RecipeService;
import com.tbb.basedata.service.RestrauntService;

public class RecipeAction extends BaseDispatchAction
{
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return mapping.findForward("add");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String recipe_id = request.getParameter("recipe_id");

		if (null == recipe_id || "".equals(recipe_id.trim()))
		{
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		RecipeService service = RecipeService.getInstance();
		Recipe recipe = service.retrieveRecipe(recipe_id);
		
		if (null == recipe)
		{
			throw new BaseException("查看编号为“" + recipe_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("recipe", recipe);

		return mapping.findForward("view");
	}

	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = upload.parseRequest(request);
		Iterator it = items.iterator();
		
		String id = UUID.randomUUID().toString();
		String filename = "";
		String temp;
		
		Map<String, String> map = new HashMap<String, String>();

		while (it.hasNext()) {
			FileItem item = (FileItem) it.next();
			String fieldName = item.getFieldName();
			if (StringUtils.isBlank(fieldName))
				continue;
			if (item.isFormField()) { 
				temp = item.getString("UTF-8");
				map.put(fieldName, temp);
//				System.out.println(item.getFieldName() + ":" + temp);
			} else {
				if ("recipe_img".equals(fieldName)) {
					String src_name = item.getName();
					filename = id + src_name.substring(src_name.lastIndexOf("."));
					String path = request.getSession().getServletContext().getRealPath("/upload")+ "/" + filename;
					File file = new File(path);
					item.write(file);
				}
			}
		}

		Recipe recipe = new Recipe();


		// 取页面参数
//		RequestUtils.getParameter(request, map);
		// 一次性赋值
//		ValueUtils.populate(recipe, it, "UTF-8");
		ValueUtils.populate(recipe, map);
		recipe.setRecipe_id(id);
		recipe.setRecipe_pic(filename);
		
		RecipeService service = RecipeService.getInstance();
		
		// 检测编码是否重复
		if (service.retrieveRecipe(recipe.getRecipe_id()) != null)
		{
			throw new BaseException("新增的ID：“" + recipe.getRecipe_id()
					+ "”已存在，不能新增！");
		}

		service.createRecipe(recipe);
		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String recipe_id = request.getParameter("id");
		if (null == recipe_id || "".equals(recipe_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		RecipeService os = RecipeService.getInstance();

		Recipe recipe = os.retrieveRecipe(recipe_id);
		if (null == recipe)
		{
			throw new BaseException("修改编号为“" + recipe_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("recipe", recipe);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Recipe recipe = new Recipe();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(recipe, map);

		RecipeService service = RecipeService.getInstance();
		service.updateRecipe(recipe);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] recipe_ids = request.getParameterValues("pk");
		if (recipe_ids == null || recipe_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		RecipeService os = RecipeService.getInstance();
		for (String recipe_id : recipe_ids)
		{
			os.deleteRecipe(recipe_id);
		}
		String recipe_id = request.getParameter("recipe_id");
		
		os.deleteRecipe(recipe_id);
		return this.query(mapping, form, request, response, context);
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();

		RecipeService recipes = RecipeService.getInstance();
		PageList recipeList = recipes.queryRecipeForPageList(params, pageIndex, pageSize);

		request.setAttribute("recipeList", recipeList);
		return mapping.findForward("query");
	}
	
	public ActionForward recipeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String restraunt_id = request.getParameter("id");
		if (null == restraunt_id || "".equals(restraunt_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}
		RestrauntService restrauntService = RestrauntService.getInstance();
		Restraunt restraunt = restrauntService.retrieveRestraunt(restraunt_id);

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();

		RecipeService recipes = RecipeService.getInstance();
		PageList recipeList = recipes.queryRecipeForPageList(params, pageIndex, pageSize);
		
		request.setAttribute("restraunt", restraunt);
		request.setAttribute("recipeList", recipeList);
		return mapping.findForward("query");
	}
	
	public static void main(String[] args){
		String aa = "aa.txt";
		int i = aa.lastIndexOf(".");
		System.out.println("ss" + aa.substring(i));
	}


}


