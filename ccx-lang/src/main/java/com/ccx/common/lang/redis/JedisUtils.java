package com.ccx.common.lang.redis;

import java.util.Date;
import java.util.Set;

import lombok.Cleanup;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

public class JedisUtils {

	// ------------------- key相关操作 -------------------

	public static Long del(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.del(key);
	}

	public static Long del(String... keys) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.del(keys);
	}

	// 序列化给定 key ，并返回被序列化的值，使用 RESTORE 命令可以将这个值反序列化为 Redis 键
	// 如果 key 不存在，那么返回 nil
	public static byte[] dump(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.dump(key);
	}

	public static Boolean exists(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.exists(key);
	}

	public static Long expire(String key, int seconds) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.expire(key, seconds);
	}

	// EXPIREAT 以秒为单位设置 key 的过期 unix 时间戳
	public static Long expireAt(String key, long unixTime) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.expireAt(key, unixTime);
	}

	public static Long expireAt(String key, Date date) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.expireAt(key, date.getTime() / 1000);
	}

	// 如果你需要从一个数据集中查找特定的 KEYS， 你最好还是用 Redis 的集合结构 SETS 来代替
	// 支持的正则表达模式（1个?表示一个占位符）：
	// h?llo 匹配 hello, hallo 和 hxllo
	// h*llo 匹配 hllo 和 heeeello
	// h[ae]llo 匹配 hello 和 hallo, 但是不匹配 hillo
	// h[^e]llo 匹配 hallo, hbllo, … 但是不匹配 hello
	// h[a-b]llo 匹配 hallo 和 hbllo
	// 如果你想取消字符的特殊匹配（正则表达式，可以在它的前面加 \
	public static Set<String> keys(String pattern) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.keys(pattern);
	}

	// 该命令返回指定key对应value所使用的内部表示(representation)(也可以理解为数据的压缩方式)
	// 参考 http://www.redis.cn/commands/object.html
	public static String objectEncoding(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.objectEncoding(key);
	}

	// 该命令返回指定key对应的value自被存储之后空闲的时间，以秒为单位(没有读写操作的请求) ，这个值返回以10秒为单位的秒级别时间
	public static Long objectIdletime(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.objectIdletime(key);
	}

	// 该命令主要用于调试(debugging)，它能够返回指定key所对应value被引用的次数
	public static Long objectRefcount(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.objectRefcount(key);
	}

	// 移除给定key的生存时间，使其成为永不过期的 key
	public static Long persist(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.persist(key);
	}

	public static Long pexpire(String key, Long milliseconds) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.pexpire(key, milliseconds);
	}

	// PEXPIREAT 以毫秒为单位设置 key 的过期 unix 时间戳
	public static Long persist(String key, Long millisecondsTimestamp) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.pexpireAt(key, millisecondsTimestamp);
	}

	public static Long persist(String key, Date date) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.pexpireAt(key, date.getTime());
	}

	// 以毫秒为单位返回 key 的剩余生存时间
	public static Long pttl(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.pttl(key);
	}

	// 从当前数据库返回一个随机的key
	public static String randomKey() {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.randomKey();
	}

	// 将key重命名为newkey，如果key与newkey相同，将返回一个错误。如果newkey已经存在，则值将被覆盖
	public static String rename(String oldkey, String newkey) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.rename(oldkey, newkey);
	}

	// 当且仅当 newkey 不存在时，将 key 改名为 newkey ；当 key 不存在时，返回一个错误。
	public static Long renamenx(String oldkey, String newkey) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.renamenx(oldkey, newkey);
	}

	// 反序列化给定的序列化值，并将它和给定的 key 关联
	// 参数 ttl 以毫秒为单位为 key 设置生存时间；如果 ttl 为 0 ，那么不设置生存时间。
	public static String restore(String key, int ttl, byte[] serializedValue) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.restore(key, ttl, serializedValue);
	}

	// 以秒为单位返回 key 的剩余生存时间
	// 如果key不存在或者已过期，返回 -2
	// 如果key存在并且没有设置过期时间（永久有效），返回 -1
	public static Long ttl(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.ttl(key);
	}

	// 返回key所存储的value的数据结构类型，它可以返回string, list, set, zset 和 hash等不同的类型
	public static String type(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.type(key);
	}

	// 迭代当前数据库中的key集合
	public static ScanResult<String> scan(String cursor) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.scan(cursor);
	}

	// ------------------- string相关操作 -------------------

	public static String get(String key) {
		@Cleanup
		Jedis jedis = JedisPoolUtil.getInstance();
		return jedis.get(key);
	}

	// ------------------- hash相关操作 -------------------
	// ------------------- list相关操作 -------------------
	// ------------------- set相关操作 -------------------
	// ------------------- zSet相关操作 -------------------
}
