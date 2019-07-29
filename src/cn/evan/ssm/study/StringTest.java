package cn.evan.ssm.study;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class StringTest
{
/*    public static void main(String[] args) {
        //自动装箱
        Integer total=99;
        //自动拆箱
        int totalPrim=total;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello word!");
            }
        }).start();

        //lambda表达式
        new Thread(()-> System.out.println("hello word lambda")).start();
    }*/


        public static void main(String[] args) throws IOException {
            String resource = "mybatis/conf/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //从 XML 中构建 SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();
            try {
                BlogMapper mapper = session.getMapper(BlogMapper.class);
                Blog blog = mapper.selectBlog(1L);
                System.out.println(blog);
                blog = mapper.selectBlog(1L);
                System.out.println(blog);
            } finally {
                session.close();
            }
        }


}
