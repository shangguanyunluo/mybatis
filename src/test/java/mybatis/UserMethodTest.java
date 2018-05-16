package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import mybatis.entity.User;

public class UserMethodTest {
	SqlSession sqlSession = null;
	public static final String NAMESPACE="mybatis.entity.User";

	@Before
	public void prepareResource() throws IOException {
		Reader configReader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configReader);
		sqlSession = sessionFactory.openSession();
	}

	@Test
	@Ignore
	public void testQuery() {
		User user = sqlSession.selectOne(NAMESPACE+".queryUserById", 1);
		System.out.println("user is :"+user);
		
		sqlSession.close();
	}
	
	@Ignore
	@Test
	public void testQueryList() {
		List<User> userList = sqlSession.selectList(NAMESPACE+".queryUsers");
		for (User user : userList) {
			System.out.println(user);
		}
		
		sqlSession.close();
	}
	
	@Test
	@Ignore
	public void insertUser(){
		User user = new User("wangwu", 33, "shanghai");
		int insert = sqlSession.insert(NAMESPACE+".insertUser", user);
		sqlSession.commit();
		System.out.println(insert);
		sqlSession.close();
	}
	
	@Test
	@Ignore
	public void modifyUser(){
		User user = new User("王五", 33, "上海");
		user.setId(4);
		int insert = sqlSession.update(NAMESPACE+".modifyUser", user);
		sqlSession.commit();
		System.out.println(insert);
		sqlSession.close();
	}
	
	@Test
	public void deleteUser(){
		int delete = sqlSession.delete(NAMESPACE+".deleteUser", 4);
		sqlSession.commit();
		System.out.println(delete);
		sqlSession.close();
	}
	
	

}
