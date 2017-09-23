package tao.tao.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.rest.component.JedisClient;

public class TestClient {
	@Test
	public void testJedisClientString(){
		ApplicationContext acx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisClient jedisClient = acx.getBean(JedisClient.class);
		jedisClient.set("client", "Hello world");
		String result = jedisClient.get("client");
		System.out.println(result);
	}
}
