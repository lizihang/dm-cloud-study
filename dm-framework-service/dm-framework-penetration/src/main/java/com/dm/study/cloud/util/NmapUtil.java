package com.dm.study.cloud.util;

import com.dm.study.cloud.param.ScanParams;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年01月09日 9:33</p>
 * <p>类全名：com.dm.study.cloud.util.NmapUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class NmapUtil {
	public static void main(String[] args) {
		remoteExec();
	}

	public static void localExec(ScanParams params) {
		try {
			String cmd = buildCommand(params);
			System.out.println("nmap命令：" + cmd);
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			System.out.println("start");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("end");
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void remoteExec() {
		String remoteHost = "192.168.23.131";
		String username = "kali";
		String password = "kali";
		try {
			JSch jsch = new JSch();
			// 创建SSH会话
			Session session = jsch.getSession(username, remoteHost, 22);
			session.setConfig("StrictHostKeyChecking", "no");  // 跳过HostKey检查
			session.setPassword(password);
			session.connect();
			// 创建SSH通道
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			// 设置要执行的nmap命令
			// String nmapCommand = "sudo nmap -O 192.168.23.131";  // 替换为你的实际nmap命令
			String nmapCommand = "echo 'root' | sudo -S nmap -O 192.168.23.131";  // 替换为你的实际nmap命令
			// String nmapCommand = "ip addr";  // 替换为你的实际nmap命令
			channelExec.setCommand(nmapCommand);
			// 获取命令输出
			InputStream in = channelExec.getInputStream();
			channelExec.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			// 关闭通道和会话
			channelExec.disconnect();
			session.disconnect();
		} catch (JSchException | IOException e) {
			e.printStackTrace();
		}
	}

	private static String buildCommand(ScanParams params) {
		String cmd = "nmap ";
		String ipAddress = params.getIpAddress();
		String portList = params.getPortList();
		Boolean pwdCracking = params.getPwdCracking();
		Boolean pingScan = params.getPingScan();
		Boolean poc = params.getPoc();
		Boolean useProxy = params.getUseProxy();
		String outName = params.getOutName();
		// ping 扫描
		if (pingScan) {
			cmd += "-sP " + ipAddress;
		}
		// linux 弱密码
		else if (pwdCracking) {
			// nmap -p 22 --script ssh-brute 192.168.23.102
			// nmap -p 22 --script ssh-brute --script-args userdb=D:\\safe\\user.txt,passdb=D:\\safe\\password.txt 192.168.23.102
			cmd += ipAddress + " -p 22 --script ssh-brute --script-args userdb=D:\\safe\\user.txt,passdb=D:\\safe\\password.txt " + ipAddress;
		}
		// POC
		else if (poc) {
			// TODO
			cmd += ipAddress + " -p " + portList;
		} else {
			cmd += ipAddress + " -p " + portList;
		}
		cmd += " -oX " + outName;
		return cmd;
	}
}
