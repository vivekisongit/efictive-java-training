package in.vivek.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryBuilder {

	
	private StringBuilder queryBuilder = new StringBuilder();
	private String val="?";

	public String insertReflections(Class<?> className) {
		Field[] fields = className.getDeclaredFields();
		List<String> classfields = new ArrayList<String>();
		for (Field field : fields) {
			field.setAccessible(true);
			classfields.add(field.getName());
			
		}
		cleanQueryBuilder();
		queryBuilder.append("INSERT INTO ").append(className.getName().toLowerCase()).append(" ");
		for (Iterator<String> iterator = classfields.iterator(); iterator.hasNext();) {
			queryBuilder.append(iterator.next());
			if (iterator.hasNext()) {
				queryBuilder.append(", ");
			}
		}
		queryBuilder.append(" VALUES ").append("(");
		for (Iterator<String> iterator = classfields.iterator(); iterator.hasNext();) {
			queryBuilder.append(val);
			if (iterator.hasNext()) {
				queryBuilder.append(", ");
			}
		}
		return queryBuilder.append(")").toString();
	}

	public String updateReflections(Class<?> className) {
		Field[] fields = className.getDeclaredFields();
		List<String> fieldNames = new ArrayList<String>();
		for (Field field : fields) {
			field.setAccessible(true);
			fieldNames.add(field.getName());
		}
		cleanQueryBuilder();
		queryBuilder.append("UPDATE ").append(className.getName().toLowerCase()).append(" SET ");
		for (Iterator<String> iterator = fieldNames.iterator(); iterator.hasNext();) {
			queryBuilder.append(iterator.next()).append(" = ").append(val);
			if (iterator.hasNext()) {
				queryBuilder.append(", ");
			}
		}
		return queryBuilder.append(" WHERE ").append(fieldNames.get(0)).append(" = ").append(val).toString();
	}

	public String deleteReflections(Class<?> className) {
		String campoId = className.getClass().getDeclaredFields()[0].getName();
		if (campoId.equalsIgnoreCase("id")) {
			cleanQueryBuilder();
			return queryBuilder.append("DELETE FROM ").append(className.getClass().getName())
					.append(" WHERE ").append(campoId).append(" = ").append(val).toString();
		}
		return null;
	}

	private void cleanQueryBuilder() {
		if (!isQueryBuilderEmpty()) {
			queryBuilder = new StringBuilder();
		}
	}

	private Boolean isQueryBuilderEmpty() {
		if (queryBuilder.length() > 0) {
			return false;
		}
		return true;
	}

}