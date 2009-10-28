/**
 * 
 */
package pl.gapps.hotel.gxt.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * @author cyprian
 */
public class ClassProperties<ClazzS, ClazzD extends ModelData> {

	public void copyList(List<ClazzS> sourceList, List<ClazzD> destList,Class<ClazzS> clazzS, Class<ClazzD> clazzD) {
		for (ClazzS object : sourceList) {
			try {
				ClazzD newOne = clazzD.newInstance();
				copy(object,newOne);
				destList.add(newOne);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void copy(ClazzS source, ClazzD destination) {
		Method[] sourceMethods = source.getClass().getDeclaredMethods();
		Method[] destinationMethods = destination.getClass().getDeclaredMethods();
		for (Method method : sourceMethods) {
			if (method.getName().startsWith("get")) {
				for (Method sMethod : destinationMethods) {
					if (method.getName().equals(sMethod.getName())) {
						Object value;
							try {
								value = method.invoke(source);
								String propertyName = method.getName().substring(3,4).toLowerCase()+method.getName().substring(4);
								destination.set(propertyName, value);
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			}
		}
	}
}
