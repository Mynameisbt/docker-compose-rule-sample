package org.example;

import com.palantir.docker.compose.DockerComposeRule;
import org.junit.ClassRule;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

public class RedisPopulatorTest {

	@ClassRule
	public static DockerComposeRule docker = DockerComposeRule.builder()
			.file("src/test/resources/docker-compose.yml")
			.build();

	@Test
	public void  test() {
		RedisPopulator populator = new RedisPopulator("localhost",6379);
		populator.populateRedis("key1", "value1");
		populator.populateRedis("key2", "value2");

		try (Jedis jedis = new Jedis("localhost",6379)) {
			assertEquals("value1",jedis.get("key1"));
			assertEquals("value2",jedis.get("key2"));
		} catch (RuntimeException e) {
			fail("Exception occured when connecting to redis");
		}
	}

}