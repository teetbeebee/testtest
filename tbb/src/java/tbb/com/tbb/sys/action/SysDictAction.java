package com.tbb.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tbb.manage.SysDictManage;
import com.tbb.sys.domain.SysDict;
import com.tbb.sys.service.SysDictService;

public class SysDictAction extends BaseDispatchAction
{

	@SuppressWarnings ("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();

		params.put("orderBy", "dict_type,sort_order desc,code_id");

		SysDictService sds = SysDictService.getInstance();
		PageList sysDictList = sds.querySysDictForPageList(params, pageIndex,
				pageSize);

		request.setAttribute("sysDictList", sysDictList);

		return mapping.findForward("query");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return mapping.findForward("add");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		SysDict sysDict = new SysDict();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(sysDict, map);

		SysDictService sds = SysDictService.getInstance();
		sds.createSysDict(sysDict);

		javax.servlet.ServletContext application = this.servlet
				.getServletContext();
		SysDictManage sdm = SysDictManage.getInstance();
		sdm.reloadSysDict();
		application.setAttribute("sysDictList", sdm.getSysDictList());

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	@SuppressWarnings ("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		String dict_type = request.getParameter("dict_type");
		String code_id = request.getParameter("code_id");

		if (null == dict_type || "".equals(dict_type.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		if (null == code_id || "".equals(code_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		HashMap map = new HashMap();
		map.put("dict_type", dict_type);
		map.put("code_id", code_id);

		SysDictService sds = SysDictService.getInstance();

		SysDict sysDict = sds.retrieveSysDict(map);
		if (null == sysDict)
		{
			throw new BaseException("修改编号为“" + dict_type + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("sysDict", sysDict);

		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		SysDict sysDict = new SysDict();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);

		// 一次性赋值
		ValueUtils.populate(sysDict, map);

		SysDictService sds = SysDictService.getInstance();

		sds.updateSysDict(sysDict);

		javax.servlet.ServletContext application = this.servlet
				.getServletContext();
		SysDictManage sdm = SysDictManage.getInstance();
		sdm.reloadSysDict();
		application.setAttribute("sysDictList", sdm.getSysDictList());

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	@SuppressWarnings ("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		String dict_type = request.getParameter("dict_type");
		String code_id = request.getParameter("code_id");

		if (null == dict_type || "".equals(dict_type.trim()))
		{
			throw new BaseException("对象编号为空！");
		}

		if (null == code_id || "".equals(code_id.trim()))
		{
			throw new BaseException("对象编号为空！");
		}

		HashMap map = new HashMap();
		map.put("dict_type", dict_type);
		map.put("code_id", code_id);

		SysDictService sds = SysDictService.getInstance();

		SysDict sysDict = sds.retrieveSysDict(map);
		if (null == sysDict)
		{
			throw new BaseException("编号为“" + dict_type + "”的对象为空或不存在！");
		}

		request.setAttribute("sysDict", sysDict);

		return mapping.findForward("view");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] pks = request.getParameterValues("pk");

		if (pks == null || pks.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		SysDictService sds = SysDictService.getInstance();

		for (String pk : pks)
		{

			HashMap map = new HashMap();
			map.put("dict_type", pk.split("@@")[0]);
			map.put("code_id", pk.split("@@")[1]);

			sds.deleteSysDict(map);
		}

		javax.servlet.ServletContext application = this.servlet
				.getServletContext();
		SysDictManage sdm = SysDictManage.getInstance();
		sdm.reloadSysDict();
		application.setAttribute("sysDictList", sdm.getSysDictList());

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	public ActionForward ajax_query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		try
		{
			PrintWriter out = response.getWriter();
			StringBuffer outString = new StringBuffer(1024);
			String dict_type = request.getParameter("dict_type");
			String code_id = request.getParameter("code_id");
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("dict_type", dict_type);
			params.put("code_id", code_id);
			SysDictService sds = SysDictService.getInstance();

			List item = sds.querySysDictForList(params);
			for (int i = 0; i < item.size(); i++)
			{
				SysDict sysDict = (SysDict)item.get(i);
				code_id = sysDict.getCode_id();
				String name = sysDict.getCode_value();
				outString.append(code_id);
				outString.append("$");
				outString.append(name);
				if (i < item.size() - 1)
				{
					outString.append("@");
				}
			}
			out.println(ValueUtils.escape(outString.toString()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
