package tao.tao.redis;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisTest {

	@Test
	public void redisTest() {
		Jedis jedis = new Jedis("192.168.112.128",6379);
		jedis.set("k1", "100");
		String result = jedis.get("k1");
		System.out.println(result);
		jedis.close();
	}
	 //连接集群
	@Test
	public void testJedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.112.128",7001));
		nodes.add(new HostAndPort("192.168.112.128",7002));
		nodes.add(new HostAndPort("192.168.112.128",7003));
		nodes.add(new HostAndPort("192.168.112.128",7004));
		nodes.add(new HostAndPort("192.168.112.128",7005));
		nodes.add(new HostAndPort("192.168.112.128",7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("String", "Oh My Godeness");
		String result = jedisCluster.get("String");
		System.err.println(result);
		jedisCluster.close();
	}
	

	
	
}