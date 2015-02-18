package com.tbb.basedata.XTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbb.basedata.domain.Organ;
import com.tbb.basedata.service.OrganService;

public class OrganTree {
	private String _mRootName = "辽宁省消防机构";

	public String ShowOrganTree(){
		StringBuffer organTree = new StringBuffer("");
		OrganService os = OrganService.getInstance();
		Map<String, Object> map = new HashMap<String, Object>();
//		List organList = os.getOrganTree("0");
		List organList = null;
		try {
			organList = os.queryOrganForList(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(organList.size() == 0)
			return "";
		
		XTree xTree = new XTree();
		XTreeObject xTreeObject = new XTreeObject();
		xTree.beginTree(organTree, _mRootName, 0);
		String parent;
		String id;
		String url;
		String name;
		Organ organ = new Organ();
		
		for(int i = 0;i < organList.size();i++){
			Object object = organList.get(i);
			organ = (Organ)organList.get(i);
			id = organ.getOrgan_id();
			name = organ.getOrgan_name();
			url = "javascript:f(" + id + ");";
			parent = organ.getParent_id();
			
			xTreeObject.setFarthercode(parent);
			xTreeObject.setName(name);
			xTreeObject.setCode(id);
			url = "javascript:f(" + id + ");";
			xTreeObject.setUrl(url);
			xTreeObject.setTarget("");
			xTreeObject.setChecked(false);
			xTreeObject.setCheckbox(false);
			xTree.addNode(organTree, xTreeObject);
		}

		xTree.endTree(organTree, 0);
		
		return organTree.toString();
	}
}