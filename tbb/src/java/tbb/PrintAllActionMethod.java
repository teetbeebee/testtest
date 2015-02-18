import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class PrintAllActionMethod {

	// 包的根路径
	private static String packageRootPath = "D:\\tbb\\server\\tbb\\src\\java\\tbb\\";

	private static List<String> actionList = new ArrayList<String>();

	public static void main(String[] args) throws SecurityException,
			ClassNotFoundException {

		File file = new File(packageRootPath);
		if (!file.exists()) {
			System.out.println("包的根路径不存在!");
			return;
		}

		if (file.isDirectory()) {

			// 获取包路径下所有action方法
			getAllActionMethod(packageRootPath);

			// 对action方法进行排序
			sortAllActionMethod();

			// 打印action方法
			for (String action : actionList) {
				System.out
						.println("insert into sys_permit(permit_id,permit_name) values('"
								+ action + "','');");
			}
		}
	}

	// 获取包路径下所有action方法
	private static void getAllActionMethod(String packagePath)
			throws SecurityException, ClassNotFoundException {
		File file = new File(packagePath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isFile()) {
						if (f.getName().endsWith("Action.java")) {
							String cName = f.getAbsolutePath().replace(
									packageRootPath, "").replace(".java", "")
									.replace("\\", ".");

							Method[] methods = Class.forName(cName)
									.getMethods();
							for (Method method : methods) {
								int modifier = method.getModifiers();
								String name = method.getName();
								if (modifier == java.lang.reflect.Modifier.PUBLIC) {

									if (!name.equals("createLastForward")
											&& !name.equals("execute")
											&& !name.equals("checkLogin")
											&& !name.equals("checkPermit")
											&& !name.equals("checkPermitCs")
											&& !name.equals("preAction")
											&& !name.equals("postAction")
											&& !name.equals("getLogin")
											&& !name.equals("getPageIndex")
											&& !name.equals("getPageSize")
											&& !name.equals("execute")
											&& !name.equals("setServlet")
											&& !name.equals("getServlet")
											&& !name.equals("equals")
											&& !name.equals("toString")) {

										actionList.add(cName + "@" + name);
									}
								}

							}
						}
					}
					if (f.isDirectory()) {
						getAllActionMethod(f.getAbsolutePath());
					}
				}
			}
		}
	}

	// 对action方法进行排序
	private static void sortAllActionMethod() {
		TreeMap<String, String> tm = new TreeMap<String, String>();
		for (String action : actionList) {
			tm.put(action, action);
		}

		actionList.clear();

		Iterator it = tm.keySet().iterator();
		while (it.hasNext()) {
			actionList.add(it.next().toString());
		}
	}
}
