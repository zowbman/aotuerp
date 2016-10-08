package com.aotuspace.aotuerp.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aotuspace.aotuerp.web.model.EasyTreeData;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;

/**
 * 
 * Title:TreeNodeUtil
 * Description:Ê÷µÝ¹é
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-10-21 ÉÏÎç11:28:48
 *
 */
public class TreeNodeUtil {
	
	/**
	 * Ê÷µÝ¹é
	 * @param erpPrivList ¶¥µã
	 * @param layer ²ãÊý
	 * @return
	 */
	public static  List<EasyTreeData> getTreeNode(Collection<SpEmployeePrivilege> erpPrivList,Integer layer) {
		List<EasyTreeData> treeNodeList = new ArrayList<EasyTreeData>();
		for (SpEmployeePrivilege erpPriv : erpPrivList) {//±éÀú
			if(layer>0){
				//System.out.println(prefix+erpPriv.getSpEpname());
				EasyTreeData treeNode = new EasyTreeData(erpPriv.getSpId(),erpPriv.getSpEpname(),erpPriv.getSpEpurl(), erpPriv.getSpState(),
						erpPriv.getSpIconcls());
				treeNode.setChildren(getTreeNode(erpPriv.getSpEpchildren(),layer-1));
				
				if(treeNode.getChildren().size()==0){
					treeNode.setChildren(null);
				}
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}
	
	
}
