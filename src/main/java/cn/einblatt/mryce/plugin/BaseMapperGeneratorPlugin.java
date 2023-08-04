package cn.einblatt.mryce.plugin;
import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
 
public class BaseMapperGeneratorPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
 
	/**
	 * 生成dao
	 */
	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		/**
		 * 主键默认采用java.lang.Integer
		 */
		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<"+introspectedTable.getBaseRecordType()+">");

		FullyQualifiedJavaType imp = new FullyQualifiedJavaType(
				"cn.einblatt.mryce.commons.BaseMapper");
		/**
		 * 添加 extends MybatisBaseMapper
		 */
		interfaze.addSuperInterface(fqjt);
 
		/**
		 * 添加import my.mabatis.example.base.MybatisBaseMapper;
		 */
		interfaze.addImportedType(imp);
		/**
		 * 方法不需要
		 */
		interfaze.getMethods().clear();
		interfaze.getAnnotations().clear();
		return true;
	}
 
}