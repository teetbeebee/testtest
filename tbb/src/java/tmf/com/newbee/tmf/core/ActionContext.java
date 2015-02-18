package com.newbee.tmf.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionContext implements Context {

  // request中Context的标识名
  public static String WMF_ACTION_CONTEXT_KEY = "ctx";

  // request中查询参数对象的标识名
  public static String WMF_QUERY_PARAMS_KEY = "params";

  // 对应Struts中的request的标识名
  public static String WMF_CTX_REQUEST = "wmf_ctx_request";

  // 对应Struts中的response的标识名
  public static String WMF_CTX_RESPONSE = "wmf_ctx_response";

  // 对应Struts中的form的标识名
  public static String WMF_CTX_FORM = "wmf_ctx_form";

  // 对应Struts中的mapping的标识名
  public static String WMF_CTX_MAPPING = "wmf_ctx_mapping";

  // 客户请求的查询参数,包括分页/排序及查询过滤条件,用于回显
  public static String WMF_QUERY_PARAMS = "wmf_ctx_params";

  // 当前登录用户
  public static String WMF_USER = "wmf_user";

  private Map<String, Object> ctx = new HashMap<String, Object>();

  public ActionContext() {
    // 添加保存查询参数的对象
    Map<String, String> map = new HashMap<String, String>();
    put(WMF_QUERY_PARAMS, map);
  }

  public Object get(String key) {
    return ctx.get(key);
  }

  public void put(String key, Object value) {
    ctx.put(key, value);
  }

  public Object remove(String key) {
    return ctx.remove(key);
  }

  public boolean contains(String key) {
    return ctx.containsKey(key);
  }

  public void clear() {
    ctx.clear();
  }

  public List getKeys() {
    return new ArrayList(ctx.keySet());
  }

  /**
   * 查询参数对象，包含了各查询条件/分页/排序等信息
   * 
   * @return Map
   */
  public QueryParams getQueryParams() {
    return (QueryParams) ctx.get(WMF_QUERY_PARAMS);
  }

  public void setQueryParams(QueryParams params) {
    ctx.put(WMF_QUERY_PARAMS, params);
  }

  /**
   * 查询参数对象的标识，用于在页面中取值反显
   * 
   * @return String
   */
  public String getQueryParamsKey() {
    /** @todo 未完成设计 */
    return WMF_QUERY_PARAMS_KEY;
  }

  /**
   * 查询参数对象的字符串表示,用于在不同页面之间传递查询对象,以便页面处理完后重定向回 原查询页面,并保持原查询条件
   * 
   * @return String
   */
  public String getQueryParamsValue() {
    /** @todo 未完成设计 */
    return com.newbee.tmf.util.ValueUtils.appendUrlParameter("", getQueryParams()
        .getParameterMap());
  }
}
