package com.dm.study.cloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月19日 16:34</p>
 * <p>类全名：com.dm.study.cloud.util.AESUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class AESUtil {
	private static final Logger logger                   = LoggerFactory.getLogger(AESUtil.class);
	private static final String KEY_ALGORITHM            = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

	public static void main(String[] args) {
		System.out.println(encrypt("testUser@163.com", "dm-encrypt"));
	}

	/**
	 * AES 加密操作
	 *
	 * @param content  待加密内容
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String key) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器
			byte[] result = cipher.doFinal(byteContent);// 加密
			//Base64是一种基于64个可打印字符来表示二进制数据的表示方法。
			return Base64Utils.encodeToString(result);//通过Base64转码返回
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @return
	 */
	public static String decrypt(String content, String key) {
		try {
			//实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			//使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
			//执行操作
			//Base64是一种基于64个可打印字符来表示二进制数据的表示方法。
			byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
			return new String(result, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey(String key) {
		//返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			//AES 要求密钥长度为 128
			kg.init(128, new SecureRandom(key.getBytes()));
			//生成一个密钥
			SecretKey secretKey = kg.generateKey();
			return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
		} catch (NoSuchAlgorithmException ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}
}
