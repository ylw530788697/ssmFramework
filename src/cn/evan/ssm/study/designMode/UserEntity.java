package cn.evan.ssm.study.designMode;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserEntity() {
    }

    public UserEntity(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("zhangsan");
        System.out.println(JSONObject.toJSONString(userEntity));
        Class<?> aClass = Class.forName("cn.evan.ssm.study.designMode.UserEntity");
        UserEntity userEntity1 = (UserEntity) aClass.newInstance();
        userEntity1.setUserName("pppp");
        System.out.println(JSONObject.toJSONString(userEntity1));
        //应用场景  jdbc连接  springIOC容器  底层使用反射机制+DOM4j
        // hibernate   mybatis
        //使用反射机制获取类的属性信息
        Method[] methods = aClass.getMethods();
        //for (Method method : methods) {
        //    System.out.println(method);
        //}

        //Field[] fields = aClass.getDeclaredFields();
        //for (Field field : fields) {
        //    System.out.println(field);
        //}
        Constructor<?> constructor = aClass.getConstructor(String.class);
        UserEntity userEntity2 =(UserEntity)constructor.newInstance("ddddd");
        System.out.println(JSONObject.toJSONString(userEntity2));
    }
}
