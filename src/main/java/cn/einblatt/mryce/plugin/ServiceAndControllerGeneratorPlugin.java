package cn.einblatt.mryce.plugin;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import java.util.ArrayList;
import java.util.List;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

public class ServiceAndControllerGeneratorPlugin extends PluginAdapter  {
 
    // 项目目录，一般为 src/main/java
    private String targetProject;
 
    // service包名，如：com.thinkj2ee.cms.service.service
    private String servicePackage;
 
    // service实现类包名，如：com.thinkj2ee.cms.service.service.impl
    private String serviceImplPackage;
    // Controlle类包名，如：com.thinkj2ee.cms.service.controller
    private String controllerPackage;
    // service接口名前缀
    private String servicePreffix;
 
    // service接口名后缀
    private String serviceSuffix;
 
    // service接口的父接口
    private String superServiceInterface;
 
    // service实现类的父类
    private String superServiceImpl;
    // controller类的父类
    private String superController;
 
    // dao接口基类
    private String superDaoInterface;
 
    // Example类的包名
    private String examplePacket;
 
    private String recordType;
 
    private String modelName;
 
    private FullyQualifiedJavaType model;
 
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
 
    @Override
    public boolean validate(List<String> warnings) {
        boolean valid = true;
        targetProject = properties.getProperty("targetProject");
        servicePackage = properties.getProperty("servicePackage");
        serviceImplPackage = properties.getProperty("serviceImplPackage");
        servicePreffix = properties.getProperty("servicePreffix");
        servicePreffix = stringHasValue(servicePreffix) ? servicePreffix : "";
        serviceSuffix = properties.getProperty("serviceSuffix");
        serviceSuffix = stringHasValue(serviceSuffix) ? serviceSuffix : "";
        superServiceInterface = properties.getProperty("superServiceInterface");
        superServiceImpl = properties.getProperty("superServiceImpl");
        superDaoInterface = properties.getProperty("superDaoInterface");
        controllerPackage = properties.getProperty("controllerPackage");
        superController = properties.getProperty("superController");
 
        return valid;
    }
 
    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        recordType = introspectedTable.getBaseRecordType();
        modelName = recordType.substring(recordType.lastIndexOf(".") + 1);
        model = new FullyQualifiedJavaType(recordType);
        serviceName = servicePackage + "." + servicePreffix + modelName + serviceSuffix;
        serviceImplName = serviceImplPackage + "." + modelName + serviceSuffix+"Impl";
        examplePacket=recordType.substring(0,recordType.lastIndexOf("."));
        controllerName=controllerPackage.concat(".").concat(modelName).concat("Controller");
        List<GeneratedJavaFile> answer = new ArrayList<>();
        GeneratedJavaFile gjf = generateServiceInterface(introspectedTable);
        GeneratedJavaFile gjf2 = generateServiceImpl(introspectedTable);
        GeneratedJavaFile gjf3 = generateController(introspectedTable);
        answer.add(gjf);
        answer.add(gjf2);
        answer.add(gjf3);
        return answer;
    }
 
    // 生成service接口
    private GeneratedJavaFile generateServiceInterface(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        Interface serviceInterface = new Interface(service);
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);
        // 添加父接口
        if(stringHasValue(superServiceInterface)) {
            String superServiceInterfaceName = superServiceInterface.substring(superServiceInterface.lastIndexOf(".") + 1);
            serviceInterface.addImportedType(new FullyQualifiedJavaType(superServiceInterface));
            serviceInterface.addImportedType(new FullyQualifiedJavaType(recordType));
            serviceInterface.addSuperInterface(new FullyQualifiedJavaType(superServiceInterfaceName + "<" + modelName + ">"));
        }
        GeneratedJavaFile gjf = new GeneratedJavaFile(serviceInterface, targetProject, context.getJavaFormatter());
        return gjf;
    }
 
    // 生成serviceImpl实现类
    private GeneratedJavaFile generateServiceImpl(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        FullyQualifiedJavaType serviceImpl = new FullyQualifiedJavaType(serviceImplName);
        TopLevelClass clazz = new TopLevelClass(serviceImpl);
        //描述类的作用域修饰符
        clazz.setVisibility(JavaVisibility.PUBLIC);
        //描述类 引入的类
        clazz.addImportedType(service);
        //描述类 的实现接口类
        clazz.addSuperInterface(service);


        String daoFieldType = introspectedTable.getMyBatis3JavaMapperType();
        String daoFieldName = daoFieldType.substring(daoFieldType.lastIndexOf(".") + 1);

        if(stringHasValue(superServiceImpl)) {
            String superServiceImplName = superServiceImpl.substring(superServiceImpl.lastIndexOf(".") + 1);
            clazz.addImportedType(superServiceImpl);
            clazz.addImportedType(recordType);
            clazz.addImportedType(daoFieldType);
            clazz.setSuperClass(superServiceImplName + "<" + modelName + ","+daoFieldName+">");
        }
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        clazz.addAnnotation("@Service");
//
//        //描述类的成员属性
//        Field daoField = new Field(daoFieldName, new FullyQualifiedJavaType(daoFieldType));
//        clazz.addImportedType(new FullyQualifiedJavaType(daoFieldType));
//        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
//        //描述成员属性 的注解
//        daoField.addAnnotation("@Autowired");
//        //描述成员属性修饰符
//        daoField.setVisibility(JavaVisibility.PRIVATE);
//        clazz.addField(daoField);
//
//        //描述 方法名
//        Method method = new Method("getMapper");
//        //方法注解
//        method.addAnnotation("@Override");
//        FullyQualifiedJavaType methodReturnType = new FullyQualifiedJavaType("Object");
//        //返回值
//        method.setReturnType(methodReturnType);
//        //方法体，逻辑代码
//        method.addBodyLine("return " + daoFieldName + ";");
//        //修饰符
//        method.setVisibility(JavaVisibility.PUBLIC);
//        clazz.addMethod(method);
//
//
//        Method method1 = new Method("getExample");
//        method1.addAnnotation("@Override");
//        FullyQualifiedJavaType methodReturnType1 = new FullyQualifiedJavaType("Object");
//        clazz.addImportedType(new FullyQualifiedJavaType(examplePacket.concat(".").concat(modelName).concat("Example")));
//        method1.setReturnType(methodReturnType1);
//        method1.addBodyLine("return new " + modelName + "Example();");
//        method1.setVisibility(JavaVisibility.PUBLIC);
//        clazz.addMethod(method1);
 
        GeneratedJavaFile gjf2 = new GeneratedJavaFile(clazz, targetProject, context.getJavaFormatter());
        return gjf2;
    }
 
 
    // 生成controller类
    private GeneratedJavaFile generateController(IntrospectedTable introspectedTable) {
 
        FullyQualifiedJavaType controller = new FullyQualifiedJavaType(controllerName);
        TopLevelClass clazz = new TopLevelClass(controller);
        //描述类的作用域修饰符
        clazz.setVisibility(JavaVisibility.PUBLIC);
 
        //添加@Controller注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
        clazz.addAnnotation("@RestController");
        //添加@RequestMapping注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        clazz.addAnnotation("@RequestMapping(\"/"+firstCharToLowCase(modelName)+"\")");
        //添加@Api注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.Api"));
        String controllerSimpleName = controllerName.substring(controllerName.lastIndexOf(".") + 1);
        clazz.addAnnotation("@Api(tags = \""+controllerSimpleName+"\")");
//        clazz.addAnnotation("@Api(tags = \""+controllerSimpleName+"\", description = \""+controllerSimpleName+"\")");

        //引入controller的父类和model，并添加泛型
        if(stringHasValue(superController)) {
            clazz.addImportedType(superController);
            clazz.addImportedType(recordType);
            FullyQualifiedJavaType superInterfac = new FullyQualifiedJavaType(superController+"<"+(serviceName.substring(serviceName.lastIndexOf(".") + 1)+","+modelName+">"));
            clazz.setSuperClass(superInterfac);
        }
 
        //引入Service
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        clazz.addImportedType(service);
 
//        //添加Service成员变量
//        String serviceFieldName = firstCharToLowCase(serviceName.substring(serviceName.lastIndexOf(".") + 1));
//        Field daoField = new Field(serviceFieldName, new FullyQualifiedJavaType(serviceName));
//        clazz.addImportedType(new FullyQualifiedJavaType(serviceName));
//        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
//        //描述成员属性 的注解
//        daoField.addAnnotation("@Autowired");
//        //描述成员属性修饰符
//        daoField.setVisibility(JavaVisibility.PRIVATE);
//        clazz.addField(daoField);
//
//
//        //描述 方法名
//        Method method = new Method("getService");
//        //方法注解
//        method.addAnnotation("@Override");
//        String simpleSuperServiceName = superServiceInterface.substring(superServiceInterface.lastIndexOf(".") + 1);
//        FullyQualifiedJavaType methodReturnType = new FullyQualifiedJavaType(simpleSuperServiceName+"<"+modelName+">");
//        //返回类型
//        method.setReturnType(methodReturnType);
//        //方法体，逻辑代码
//        method.addBodyLine("return " + serviceFieldName + ";");
//        //修饰符
//        method.setVisibility(JavaVisibility.PUBLIC);
//        clazz.addImportedType(superServiceInterface);
//        clazz.addMethod(method);
 
 
        GeneratedJavaFile gjf2 = new GeneratedJavaFile(clazz, targetProject, context.getJavaFormatter());
        return gjf2;
    }
 
 
    private String firstCharToLowCase(String str) {
        char[] chars = new char[1];
        //String str="ABCDE1234";
        chars[0] = str.charAt(0);
        String temp = new String(chars);
        if(chars[0] >= 'A'  &&  chars[0] <= 'Z') {
            return str.replaceFirst(temp,temp.toLowerCase());
        }
        return str;
    }
}