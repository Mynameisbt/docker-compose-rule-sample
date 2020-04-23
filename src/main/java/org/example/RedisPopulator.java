package org.example;

import redis.clients.jedis.Jedis;

public class RedisPopulator {

	private final String host;
	private final int port;
	private Jedis jedis;

	public RedisPopulator(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void populateRedis(String key, String value) {
		try (Jedis jedis = new Jedis(host, port);) {
			jedis.set(key, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

}
