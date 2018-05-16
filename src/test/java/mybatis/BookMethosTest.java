package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import mybatis.entity.Book;

public class BookMethosTest {
	SqlSession sqlSession = null;
	static final String NAMESPACE = "mybatis.entity.Book";

	@Before
	public void createSession() throws IOException {
		Reader resourceAsReader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
		sqlSession = sessionFactory.openSession();
	}

	@Test
	public void testQueryById() {
		Book book = sqlSession.selectOne(NAMESPACE + ".queryBookById", 1);
		System.out.println(book);
		sqlSession.close();
	}

	@Test
	public void testQueryById2() {
		Book book = sqlSession.selectOne(NAMESPACE + ".queryBookById2", 1);
		System.out.println(book);
		sqlSession.close();
	}

	@Test
	public void testQueryById3() {
		Book book = sqlSession.selectOne(NAMESPACE + ".queryBookById3", 2);
		System.out.println(book);
		sqlSession.close();
	}

	@Test
	public void testQueryById4Map() {
		Map<String, Object> book = sqlSession.selectOne(NAMESPACE + ".queryBook4Map", 2);
		System.out.println(book);
		sqlSession.close();
	}

	@Test
	public void testQueryByNameLike() {
		Book book = new Book();
		book.setName("g");
		System.out.println(book);
		List<Book> booklist = sqlSession.selectList(NAMESPACE + ".queryBook4Like", book);
		for (Book book2 : booklist) {
			System.out.println(book2);
		}
		sqlSession.close();
	}

	@Test
	public void insertBook() {
		Book book = new Book("English", 33);
		int num = sqlSession.insert(NAMESPACE + ".insertBook", book);
		System.out.println(num);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void insertBook2() {
		Map<String, Object> bookmap = new HashMap<String, Object>();
		bookmap.put("name", "政治");
		bookmap.put("price", 300);
		int num = sqlSession.insert(NAMESPACE + ".insertBookFromMap", bookmap);
		System.out.println(num);
		sqlSession.commit();
		sqlSession.close();
	}

}
