package com.aotuspace.aotuerp.web.util.sort.complarator;

import java.util.Comparator;

import com.aotuspace.aotuerp.web.util.sort.ISortSpProductBproperties;

/**
 * 
 * Title:SpProductBpropertiesComparator
 * Description:基本属性比较类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-20 下午5:00:46
 *
 */
public class SpProductBpropertiesComparator implements Comparator {
	
	ISortSpProductBproperties iSortSpProductBproperties;

	public SpProductBpropertiesComparator(ISortSpProductBproperties iSortSpProductBproperties) {
		this.iSortSpProductBproperties = iSortSpProductBproperties;
	}
	public int compare(Object o1, Object o2) {
		return iSortSpProductBproperties.compare(o1,o2);
		
	}
}
