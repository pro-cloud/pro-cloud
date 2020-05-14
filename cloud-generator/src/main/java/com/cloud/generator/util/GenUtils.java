package com.cloud.generator.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import com.cloud.generator.entity.GenScheme;
import com.cloud.generator.entity.TableColumn;
import freemarker.template.Template;
import lombok.experimental.UtilityClass;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 * @author Aijm
 * @since 2019/6/16
 */
@UtilityClass
public class GenUtils {

	private final String ENTITY_JAVA_VM = "Entity.java.ftl";
	private final String MAPPER_JAVA_VM = "Mapper.java.ftl";
	private final String SERVICE_JAVA_VM = "Service.java.ftl";
	private final String SERVICE_IMPL_JAVA_VM = "ServiceImpl.java.ftl";
	private final String CONTROLLER_JAVA_VM = "Controller.java.ftl";
	private final String MAPPER_XML_VM = "Mapper.xml.ftl";


	private final String BACK_END_PROJECT = "cloud";


	private List<String> getTemplates() {
		List<String> templates = new ArrayList<>();
		templates.add("Entity.java.ftl");
		templates.add("Mapper.java.ftl");
		templates.add("Mapper.xml.ftl");
		templates.add("Service.java.ftl");
		templates.add("ServiceImpl.java.ftl");
		templates.add("Controller.java.ftl");
		return templates;
	}

	/**
	 * 生成代码
	 */
	public static void generatorCode(GenScheme genScheme, List<TableColumn> tableColumns, ZipOutputStream zip) throws Exception {

		//封装模板数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("tableName", genScheme.getTableName());
//		map.put("className", genScheme.getCaseClassName());
//		map.put("classname", genScheme.getLowerClassName());
//		map.put("pathName", genScheme.getLowerClassName().toLowerCase());
//		map.put("columns", tableEntity.getColumns());
//		map.put("hasBigDecimal", hasBigDecimal);
		map.put("datetime", DateUtil.now());

		//获取模板列表
		List<String> templates = getTemplates();

		freemarker.template.Configuration configuration=new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
		ClassPathResource classPathResource = new ClassPathResource("templates");
		configuration.setDirectoryForTemplateLoading(classPathResource.getFile());

		for (String template : templates) {
			//渲染模板
            Template tpl = configuration.getTemplate(template,CharsetUtil.UTF_8);

            String content = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);

            //添加到zip
            zip.putNextEntry(new ZipEntry(Objects
                    .requireNonNull(getFileName(template, genScheme.getFunctionName()
                            , genScheme.getPackageName(), genScheme.getModuleName()))));
            IoUtil.write(zip, CharsetUtil.UTF_8, false, content);
            zip.closeEntry();

		}
	}


	/**
	 * 列名转换成Java属性名
	 */
	private String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}

	/**
	 * 表名转换成Java类名
	 */
	private String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replaceFirst(tablePrefix, "");
		}
		return columnToJava(tableName);
	}


	/**
	 * 获取文件名
	 */
	private String getFileName(String template, String className, String packageName, String moduleName) {
		String packagePath = BACK_END_PROJECT + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}

		if (template.contains(ENTITY_JAVA_VM)) {
			return packagePath + "beans" + File.separator + "po" + File.separator+ className + ".java";
		}

		if (template.contains(MAPPER_JAVA_VM)) {
			return packagePath + "mapper" + File.separator + className + "Mapper.java";
		}

		if (template.contains(SERVICE_JAVA_VM)) {
			return packagePath + "service" + File.separator + className + "Service.java";
		}

		if (template.contains(SERVICE_IMPL_JAVA_VM)) {
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}

		if (template.contains(CONTROLLER_JAVA_VM)) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}

		if (template.contains(MAPPER_XML_VM)) {
			return BACK_END_PROJECT + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
		}

		return null;
	}
}
