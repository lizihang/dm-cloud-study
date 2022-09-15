package com.dm.study.cloud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
/**
 * <p>标题：FinalShell激活高级版，专业版</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月15日 10:35</p>
 * <p>类全名：com.dm.study.cloud.util.FinalShell</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class FinalShell {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.print("请输入FinalShell的离线机器码：");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String machineCode = reader.nextLine();
		generateKey(machineCode);
	}

	public static void generateKey(String hardwareId) throws NoSuchAlgorithmException {
		String proKey = transform(61305 + hardwareId + 8552);
		String pfKey = transform(2356 + hardwareId + 13593);
		System.out.println("请将此行复制到离线激活中：" + proKey);
		System.out.println("请将此行复制到离线激活中：" + pfKey);
	}

	public static String transform(String str) throws NoSuchAlgorithmException {
		@SuppressWarnings("unused")
		String md5 = hashMD5(str);
		return hashMD5(str).substring(8, 24);
	}

	public static String hashMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] hashed = digest.digest(str.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : hashed) {
			int len = b & 0xFF;
			if (len < 16) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(len));
		}
		return sb.toString();
	}
}
