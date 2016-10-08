package com.aotuspace.aotuerp.web.util.sort.complarator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBproperties;
import com.aotuspace.aotuerp.web.util.sort.ISortSpProductBproperties;
import com.aotuspace.aotuerp.web.util.sort.rule.SpProductBpropertiesOrder;

public class SortSpProductBpropertiesImp implements ISortSpProductBproperties {

	private List<SpProductBpropertiesOrder> spProductBpropertiesOrders;

	//构造方法
	public SortSpProductBpropertiesImp(List<SpProductBpropertiesOrder> spProductBpropertiesOrders) {
		this.spProductBpropertiesOrders = spProductBpropertiesOrders;
	}

	public int compare(Object o1, Object o2) {
		int result = 0;
		try {
			//对象比较（//这里可以动态）
			SpProductBproperties spb1 = (SpProductBproperties) o1;
			SpProductBproperties spb2 = (SpProductBproperties) o2;
			
			for (SpProductBpropertiesOrder spProductBpropertiesOrder : spProductBpropertiesOrders) {
				Object v1 = getVaule(spb1, spProductBpropertiesOrder.getPropertyName());
				Object v2 = getVaule(spb2, spProductBpropertiesOrder.getPropertyName());
				result = sort(v1, v2, spProductBpropertiesOrder.getDataType());
				if (!spProductBpropertiesOrder.isAsc()) {
					result *= -1;
				}
				if (result != 0) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("排序比较异常");
		}
		return result;
	}
	

	private int sort(Object v1, Object v2, int dataType) {
		int result = 0;
		switch (dataType) {
		case SpProductBpropertiesOrder._STRING:
			String s1 = (String) v1;
			String s2 = (String) v2;
			result = s1.compareTo(s2);
			break;
		case SpProductBpropertiesOrder._BIGDECIMAL:
			BigDecimal d1 = (BigDecimal) v1;
			BigDecimal d2 = (BigDecimal) v2;
			result = d1.compareTo(d2);
			break;
		case SpProductBpropertiesOrder._LONG:
			Long l1 = (Long) v1;
			Long l2 = (Long) v2;
			result = l1.compareTo(l2);
			break;
		case SpProductBpropertiesOrder._Integer:
			Integer i1 = (Integer) v1;
			Integer i2 = (Integer) v2;
			result = i1.compareTo(i2);
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}

	private Object getVaule(Object obj, String propertyName) {
		Object result = null;
		try {
			Class clazz =  obj.getClass();
			Field field = clazz.getDeclaredField(propertyName);
			field.setAccessible(true);
			clazz=field.getType();
			
			field=clazz.getDeclaredField("spSort");
			field.setAccessible(true);
			
			SpProductBproperties spProductBproperties=(SpProductBproperties) obj;
			result = field.get(spProductBproperties.getSpProductPropertyName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
