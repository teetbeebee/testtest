package com.tbb.basedata.XTree;

public class XTree {
    public static String CRLF = "\n";

    public void addln(StringBuffer sb, String line) {
        addln(0, sb, line);
    }

    public void addln(int ident, StringBuffer sb, String line) {
        for (int i = 0; i < ident; i++) {
            sb.append(" ");
        }
        sb.append(line);
        sb.append(CRLF);

    }

    public void beginTree(StringBuffer sb, String Name, int i) {
        addln(sb, "<DIV id=treeContainer" + String.valueOf(i) + " align=left>");
        addln(sb, "<SCRIPT type=text/javascript>");
        addln(sb, "var tree = new dTree(); ");
        addln(sb, "tree.add(0,0,\"" + Name
                + "\",\"\",\"\",\"\",\"\",\"\",false,false)");

    }

    public void endTree(StringBuffer sb, int i) {
        addln(sb, "tree.write(document.getElementById(\"treeContainer"
                + String.valueOf(i) + "\"));");
//        addln(sb, "tree.openAll()");
        addln(sb, "</SCRIPT>");

        addln(sb, "</div>");
    }

    public void addNode(StringBuffer sb, XTreeObject root) {
        addln(sb, "tree.add(\"" + root.getCode().trim() + "\",\""
                + root.getFarthercode().trim() + "\",\""
                + root.getName().trim().replaceAll("<.*?>|</.*?>", "")
                + "\",\"" + root.getUrl() + "\",\"" + root.getTarget()
                + "\",\"\",\"\",\"\"," + root.isChecked()// +")");
                + "," + root.isCheckbox() + "); ");
    }

}
