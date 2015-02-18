package com.newbee.tmf.util;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;

public class HtmlTool {

  public static final String COLOR_ACTION = "#000000";

  public static final String COLOR_ACTION_ACTIVE = "#0000FF";

  public static final String COLOR_ACTION_IMAGE = "#FFFFFF";

  public static final String FIRST_PAGE = "9";

  public static final String FIRST_PAGE_TITLE = "首页";

  public static final String LAST_PAGE = ":";

  public static final String LAST_PAGE_TITLE = "尾页";

  public static final String NEXT_PAGE = "8";

  public static final String NEXT_PAGE_TITLE = "下一页";

  public static final String NO_PAGE_ITEMS = "没有符合条件的记录。";

  public static final String PAGE_NAVG_STYLE = "main_pageTable_navg";

  public static final String PREV_PAGE = "7";

  public static final String PREV_PAGE_TITLE = "上一页";

  public String a(String title, String href, String text) {
    StringBuffer sb = new StringBuffer();
    sb.append("<a ");

    if (title != null) {
      sb.append("title=\"").append(title).append("\" ");
    }
    if (href != null) {
      sb.append("href=\"").append(href).append("\" ");
    }
    sb.append(">");
    if (text != null) {
      sb.append(text);
    }
    sb.append("</a>");
    return sb.toString();
  }

  public String doAction(String action) {
    StringBuffer sb = new StringBuffer();
    sb.append("onClick=\"").append(action).append("\"");

    return sb.toString();
  }

  public String getActionStyle() {
    StringBuffer sb = new StringBuffer();
    sb.append("style=\"cursor:hand;\" ").append(
        "onMouseOver=\"changeActionColor('" + COLOR_ACTION_ACTIVE + "\');\" ")
        .append("onMouseOut=\"changeActionColor('" + COLOR_ACTION + "');\" ");

    return sb.toString();
  }

  public String getActionStyle(String normal, String active) {
    StringBuffer sb = new StringBuffer();
    sb.append("style=\"cursor:hand;\" ").append(
        "onMouseOver=\"changeActionColor('" + active + "\');\" ").append(
        "onMouseOut=\"changeActionColor('" + normal + "');\" ");

    return sb.toString();
  }

  public String getImageActionStyle() {
    StringBuffer sb = new StringBuffer();
    sb.append(
        "style=\"cursor:hand; border:1px solid " + COLOR_ACTION_IMAGE + "\" ")
        .append(
            "onMouseOver=\"changeActionColor('" + COLOR_ACTION_ACTIVE
                + "\');\" ").append(
            "onMouseOut=\"changeActionColor('" + COLOR_ACTION_IMAGE + "');\" ");

    return sb.toString();
  }

  public String getImageActionStyle(String normal, String active) {
    StringBuffer sb = new StringBuffer();
    sb.append("style=\"cursor:hand; border:1px solid " + normal + "\" ")
        .append("onMouseOver=\"changeActionColor('" + active + "\');\" ")
        .append("onMouseOut=\"changeActionColor('" + normal + "');\" ");

    return sb.toString();
  }

  public String makeAction(String action, String text) {

    StringBuffer sb = new StringBuffer();
    sb.append("<span ").append("style=\"cursor:hand;\" ").append(
        "onMouseOver=\"this.style.color=\'#0000FF\';\" ").append(
        "onMouseOut=\"this.style.color='#000000';\" ").append("onClick=\"")
        .append(action).append("\" ").append(">").append(text)
        .append("</span>");

    return sb.toString();

  }

  /**
   * 显示当前页号和总页数
   * 
   * @param pageList
   *          PageList
   * @return String
   */
  public String pageCurrentPage(PageList pageList) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    sb.append("第").append(pageList.getPageIndex()).append("/").append(
        pageList.getPageCount()).append("页");
    return sb.toString();
  }

  /**
   * 显示分页导航
   * 
   * @param pageList
   * @param action_name
   *          进行分页的javascript函数名
   * @return
   */
  public String pageNavg(PageList pageList, String action_name) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }

    StringBuffer sb = new StringBuffer();
    String action = "";

    // 当前第几页
    sb.append(pageCurrentPage(pageList)).append("  ");

    // 第一页
    sb.append("<span ");
    if (!pageList.isFirstPage()) {
      action = action_name + "(" + 1 + ")";
      sb.append("title=\"").append(FIRST_PAGE_TITLE).append("\" ").append(
          getActionStyle()).append(" ").append(doAction(action));
    }
    sb.append(">").append("<font class=\"").append(PAGE_NAVG_STYLE).append(
        "\">").append(FIRST_PAGE).append("</font>").append("</span>");

    // 上一页
    sb.append("<span ");
    if (pageList.isPreviousPageAvailable()) {
      action = action_name + "(" + pageList.getPrevPageIndex() + ")";
      sb.append("title=\"").append(PREV_PAGE_TITLE).append("\" ").append(
          getActionStyle()).append(" ").append(doAction(action));
    }
    sb.append(">").append("<font class=\"").append(PAGE_NAVG_STYLE).append(
        "\">").append(PREV_PAGE).append("</font>").append("</span>");

    // 下一页
    sb.append("<span ");
    if (pageList.isNextPageAvailable()) {
      action = action_name + "(" + pageList.getNextPageIndex() + ")";
      sb.append("title=\"").append(NEXT_PAGE_TITLE).append("\" ").append(
          getActionStyle()).append(" ").append(doAction(action));
    }
    sb.append(">").append("<font class=\"").append(PAGE_NAVG_STYLE).append(
        "\">").append(NEXT_PAGE).append("</font>").append("</span>");

    // 最后一页
    sb.append("<span ");
    if (!pageList.isLastPage()) {
      action = action_name + "(" + pageList.getPageCount() + ")";
      sb.append("title=\"").append(LAST_PAGE_TITLE).append("\" ").append(
          getActionStyle()).append(" ").append(doAction(action));
    }
    sb.append(">").append("<font class=\"").append(PAGE_NAVG_STYLE).append(
        "\">").append(LAST_PAGE).append("</font>").append("</span>");

    // 转到第几页
    sb
        .append("  转到第<input type=\"text\" name=\"page\" class=\"main_text_rect\" size=\"3\">页&nbsp;<input type=\"button\" value=\"Go\" class=\"main_button\" onClick=\"");
    sb.append(action_name).append("(page.value)\">");

    return sb.toString();
  }

  /**
   * 显示分页导航
   * 
   * @param pageList
   *          PageList
   * @param url
   *          String
   * @param key
   *          String
   * @return String
   */
  public String pageNavg(PageList pageList, String url, String key) {
    StringBuffer sb = new StringBuffer();
    sb.append(pageNavgFirst(pageList, url, key)).append("").append(
        pageNavgPrev(pageList, url, key)).append("").append(
        pageNavgNext(pageList, url, key)).append("").append(
        pageNavgLast(pageList, url, key)).append("");

    return sb.toString();
  }

  public String pageNavgEx(PageList pageList, String url, String key) {
    StringBuffer sb = new StringBuffer();
    sb.append(pageNavg(pageList, url, key)).append("").append(
        pageNavgGo(pageList, url, key)).append("");

    return sb.toString();
  }

  public String pageNavgFirst(PageList pageList, String url, String key) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }

    if (pageList.isFirstPage()) {
      return FIRST_PAGE;
    } else {
      StringBuffer sb = new StringBuffer();
      sb.append(url).append("&").append(key).append("=").append(1);
      return a(FIRST_PAGE_TITLE, sb.toString(), FIRST_PAGE);
    }
  }

  public String pageNavgGo(PageList pageList, String url, String key) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }

    return "";
  }

  public String pageNavgLast(PageList pageList, String url, String key) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }

    if (pageList.isLastPage()) {
      return LAST_PAGE;
    } else {
      StringBuffer sb = new StringBuffer();
      sb.append(url).append("&").append(key).append("=").append(
          pageList.getPageCount());
      return a(LAST_PAGE_TITLE, sb.toString(), LAST_PAGE);
    }

  }

  public String pageNavgNext(PageList pageList, String url, String key) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }

    if (pageList.isNextPageAvailable()) {
      StringBuffer sb = new StringBuffer();
      sb.append(url).append("&").append(key).append("=").append(
          pageList.getNextPageIndex());
      return a(NEXT_PAGE_TITLE, sb.toString(), NEXT_PAGE);
    } else {
      return NEXT_PAGE;
    }

  }

  public String pageNavgPrev(PageList pageList, String url, String key) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return "";
    }

    if (pageList.isPreviousPageAvailable()) {
      StringBuffer sb = new StringBuffer();
      sb.append(url).append("&").append(key).append("=").append(
          pageList.getPrevPageIndex());
      return a(PREV_PAGE_TITLE, sb.toString(), PREV_PAGE);
    } else {
      return PREV_PAGE;
    }
  }

  /**
   * 显示记录总数
   * 
   * @param pageList
   *          PageList
   * @return String
   */
  public String pageTotalCount(PageList pageList) {
    if ((pageList == null) || (pageList.getCount() <= 0)) {
      return NO_PAGE_ITEMS;
    }
    StringBuffer sb = new StringBuffer();
    sb.append("共").append(pageList.getCount()).append("条记录");
    return sb.toString();
  }

  /**
   * 通过一个List和一个选中项生成一个带反显的下拉列表
   * 
   * @param list
   *          用于下拉列表的List
   * @param selected
   *          需要被选中的选项
   * @return
   */
  public String match(List list, Object key) {
    String sKey = toString(key);
    // 开始遍历列表list
    for (int i = 0; i < list.size(); i++) {
      Map map = (Map) list.get(i);
      String value = toString(map.get("value"));
      String label = toString(map.get("label"));
      // 判断value是否与匹配,如果是则返回show值
      if (value.equals(sKey)) {
        return label;
      }
    }
    // 没有匹配项，返回空
    return "";
  }

  public String select(List list, Object selected) {
    String strSelected = toString(selected);
    StringBuffer sb = new StringBuffer();
    // 开始遍历下拉列表list
    for (int i = 0; i < list.size(); i++) {
      Object entry = list.get(i);
      String value;
      String label;
      if (entry instanceof Map) {
        Map map = (Map) entry;
        /** @done 注意空指针异常处理 */
        value = toString(map.get("value"));
        label = toString(map.get("label"));
      } else {
        value = toString(entry);
        label = toString(entry);
      }

      sb.append("<option value=\"");
      sb.append(value).append("\"");
      // 判断该选项是否被选中,如果是则添加selected标记
      if (value.equals(strSelected)) {
        sb.append(" selected");
      }
      sb.append(">").append(label).append("</option>\r\n");
    }
    return sb.toString();
  }

  private String toString(Object o) {
    if (o == null) {
      return "";
    } else {
      return o.toString();
    }
  }

  /**
   * 构建javascript树时往一个节点添加一个子节点(依赖于xtree控件)
   * 
   * @param node
   *          节点名称
   * @param subNodeId
   *          子节点id
   * @param subNodeName
   *          子节点显示name
   * @param action
   *          子节点上的动作(可以是链接或javascript函数)
   * @return 拼接好的字符串
   */
  public static String addNodeOfTree(String node, String subNodeId,
      String subNodeName, String action) {
    // tree.add(itemGZ100000);
    StringBuffer sb = new StringBuffer();
    String subNode = "node" + subNodeId;
    sb.append("  var ").append(subNode).append(" = new WebFXTreeItem(\"");
    sb.append(subNodeName).append("\",\"");
    sb.append(action).append("\");\r\n");

    sb.append("  ").append(node).append(".add(").append(subNode).append(
        ");\r\n");

    return sb.toString();
  }

  /**
   * 把\r\n转化为<br>
   * 显示在html中显示换行
   * 
   * @param input
   * @return
   */
  public static String brHtml(String input) {
    if (input == null)
      return "";
    String re = input.replaceAll("\r\n", "<br/>");
    re = re.replaceAll("\r", "<br/>");
    return re;
  }

}
