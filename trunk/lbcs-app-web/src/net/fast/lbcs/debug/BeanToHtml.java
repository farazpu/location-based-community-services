package net.fast.lbcs.debug;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class BeanToHtml {
	private static List<Class<?>> primitives = new ArrayList<Class<?>>();
	private static List<Class<?>> skip = new ArrayList<Class<?>>();
	static {
		primitives.add(String.class);
		primitives.add(Date.class);
		skip.add(Class.class);
	}
	StringBuilder sb = new StringBuilder();
	private List<?> beans;
	private Class<?> clazz;
	List<Object> inspected = new ArrayList<Object>();
	private final String key;
	public BeanToHtml(List<?> beans, Class<?> clazz, String key) {
		this(beans, clazz, new ArrayList<Object>(), key);
	}

	public BeanToHtml(Object bean, Class<?> clazz, String key) {
		this(Arrays.asList(bean), clazz, new ArrayList<Object>(), key);
	}

	private BeanToHtml(List<?> beans, Class<?> clazz, List<Object> inspected, String key) {
		this.beans = beans;
		this.clazz = clazz;
		this.inspected = inspected;
		this.key = key;
	}

	private BeanToHtml(Object bean, Class<?> clazz, List<Object> inspected, String key) {
		this(Arrays.asList(bean), clazz, inspected, key);
	}

	public String createPropertiesIndentedList() {
		sb = new StringBuilder();
		if(clazz == null) {
			if(beans != null && beans.size() > 0 && beans.get(0) != null) {
				clazz = beans.get(0).getClass();
			} else {
				return "No ifnormation available.";
			}
		}

		start("ul");


		PropertyDescriptor[] descriptors = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			descriptors = beanInfo.getPropertyDescriptors();

		} catch (IntrospectionException e) {
		}
		if(descriptors != null) {

			for (Object bean : beans) {
				start("li");
				start("b class='label'");
				sb.append(bean.getClass().getSimpleName());
				end("b");
				start("ul");

				for (PropertyDescriptor descriptor : descriptors) {
					if(skip.contains(descriptor.getPropertyType())) {
						continue;
					}
					start("li");
					try {
						if(descriptor.getPropertyType().isPrimitive() || primitives.contains(descriptor.getPropertyType())) {
							Method readMethod = descriptor.getReadMethod();
							if(readMethod != null) {
								createLabel(descriptor);
								sb.append(readMethod.invoke(bean));
							}
						} else if (List.class.isAssignableFrom(descriptor.getPropertyType())) {
							Method readMethod = descriptor.getReadMethod();
							if(readMethod != null) {
								start("div class='typeContainer'");
								createLabel(descriptor);
								sb.append(new BeanToHtml((List<?>)readMethod.invoke(bean), null, inspected, key).createPropertiesIndentedList());
								end("div");

							}
						} else if (Map.class.isAssignableFrom(descriptor.getPropertyType())) {
							sb.append("Map");
						} else {
							Method readMethod = descriptor.getReadMethod();
							if(readMethod != null) {
								Object value = readMethod.invoke(bean);
								if(!isInspected(value)) {
									if(value != null) {
										inspected.add(value);
									}
									int id = inspected.size() - 1;
									start("div class='typeContainer' id='id" + prepareId(id) + "'");
									createLabel(descriptor);
									sb.append("<a name='" + prepareId(id) + "'></a>");
									if(value != null) {
										sb.append(new BeanToHtml(value, value.getClass(), inspected, key).createPropertiesIndentedList());
									} else {
										sb.append("null");
									}
									end("div");
								} else {
									int id = inspected.indexOf(value);
									createLabel(descriptor);
									prepareLink(id);
								}
							}
						}
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					} catch (InvocationTargetException e) {
					}

					end("li");
				}
				end("ul");
				end("li");
			}
		}
		end("ul");

		return sb.toString();
	}

	private void createLabel(PropertyDescriptor descriptor) {
		start("b");
		sb.append(descriptor.getDisplayName()).append(": ");
		end("b");
	}

	private void prepareLink(int id) {
		sb.append("Already Inspected: <a href='#" + prepareId(id) + "' onclick='select(\"" + prepareId(id) + "\")'>Go</a>");
		sb.append("&nbsp;<a href='' onclick='select(\"" + prepareId(id) + "\"); return false;'>Highlight</a>");
	}

	private String prepareId(int id) {
		return key + id;
	}

	private void start(String elem) {
		sb.append("<").append(elem).append(">");
	}

	private void end(String elem) {
		sb.append("</").append(elem).append(">");
	}

	private boolean isInspected(Object obj) {
		return inspected.contains(obj);
	}
}
