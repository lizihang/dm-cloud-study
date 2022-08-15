package com.dm.study.cloud.util;

import org.apache.commons.lang3.RandomUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Random;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 15:54</p>
 * <p>类全名：com.dm.system.util.ValidateCodeUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ValidateCodeUtil
{
	//验证码类，用于最后返回此对象，包含验证码图片base64和真值
	private static Validate validate   = null;
	//随机类，用于生成随机参数
	private static Random   random     = new Random();
	//随机生成字符串的取值范围
	private static String   randString = "0123456789abcdefghijkmnpqrtyABCDEFGHIJLMNQRTY";
	//图片宽度
	private static int      width      = 80;
	//图片高度
	private static int      height     = 34;

	private static final char intToAltBase64[] = { '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':',
			';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
			'3', '4', '5', '6', '7', '8', '9', '+', '?' };
	private static final char intToBase64[]    = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
			'3', '4', '5', '6', '7', '8', '9', '+', '/' };

	/**
	 * 将构造函数私有化 禁止new创建
	 */
	private ValidateCodeUtil()
	{
		super();
	}

	/**
	 * 生成Base64图片验证码
	 * @return String 返回base64
	 */
	public static Validate getRandomCode()
	{
		validate = validate == null ? new Validate() : validate;
		// 1.设置验证码值
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// 获得BufferedImage对象的Graphics对象
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);//填充矩形
		// 设置字体
		g.setFont(new Font("Fixedsys", Font.BOLD, 25));
		// 设置颜色
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		drawLine(g);
		// 绘制验证码
		validate.setValue(drawString(g));
		//释放绘图资源
		g.dispose();
		// 2.设置验证码base64值
		try (ByteArrayOutputStream bs = new ByteArrayOutputStream())
		{
			ImageIO.write(image, "png", bs);//将绘制得图片输出到流
			String imgsrc = byteArrayToBase64(bs.toByteArray(),false);
			validate.setBase64Str(imgsrc);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return validate;
	}

	private static String byteArrayToBase64(byte[] a, boolean alternate) {
		int aLen = a.length;
		int numFullGroups = aLen / 3;
		int numBytesInPartialGroup = aLen - 3 * numFullGroups;
		int resultLen = 4 * ((aLen + 2) / 3);
		StringBuilder result = new StringBuilder(resultLen);
		char[] intToAlpha = (alternate ? intToAltBase64 : intToBase64);

		// Translate all full groups from byte array elements to Base64
		int inCursor = 0;
		for (int i = 0; i < numFullGroups; i++) {
			int byte0 = a[inCursor++] & 0xff;
			int byte1 = a[inCursor++] & 0xff;
			int byte2 = a[inCursor++] & 0xff;
			result.append(intToAlpha[byte0 >> 2]);
			result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
			result.append(intToAlpha[(byte1 << 2) & 0x3f | (byte2 >> 6)]);
			result.append(intToAlpha[byte2 & 0x3f]);
		}

		// Translate partial group if present
		if (numBytesInPartialGroup != 0) {
			int byte0 = a[inCursor++] & 0xff;
			result.append(intToAlpha[byte0 >> 2]);
			if (numBytesInPartialGroup == 1) {
				result.append(intToAlpha[(byte0 << 4) & 0x3f]);
				result.append("==");
			} else {
				// assert numBytesInPartialGroup == 2;
				int byte1 = a[inCursor++] & 0xff;
				result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
				result.append(intToAlpha[(byte1 << 2) & 0x3f]);
				result.append('=');
			}
		}
		// assert inCursor == a.length;
		// assert result.length() == resultLen;
		return result.toString();
	}

	/**
	 * 随机获取颜色
	 * @param frontColor
	 * @param backColor
	 * @return
	 */
	private static Color getRandColor(int frontColor, int backColor)
	{
		if (frontColor > 255)
		{
			frontColor = 255;
		}
		if (backColor > 255)
		{
			backColor = 255;
		}
		int red = frontColor + random.nextInt(backColor - frontColor - 16);
		int green = frontColor + random.nextInt(backColor - frontColor - 14);
		int blue = frontColor + random.nextInt(backColor - frontColor - 18);
		return new Color(red, green, blue);
	}

	/**
	 * 绘制干扰线
	 * @param g
	 */
	private static void drawLine(Graphics g)
	{
		//干扰线数量
		int lineSize = 40;
		for (int i = 0; i <= lineSize; i++)
		{
			//起点(x,y)  偏移量x1、y1
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(13);
			int yl = random.nextInt(15);
			g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
			g.drawLine(x, y, x + xl, y + yl);
		}
	}

	/**
	 * 绘制字符串,返回绘制的字符串
	 * @param g
	 * @return
	 */
	private static String drawString(Graphics g)
	{
		StringBuilder randomString = new StringBuilder();
		Graphics2D g2d = (Graphics2D) g;
		//字符的数量
		int stringNum = 4;
		for (int i = 1; i <= stringNum; i++)
		{
			// 随机生成一个字符
			String randChar = getRandomChar(random.nextInt(randString.length()));
			// 画出验证码
			g2d.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));//设置颜色
			int rot = RandomUtils.nextInt(5, 10);
			g2d.translate(random.nextInt(3), random.nextInt(3));
			g2d.rotate(rot * Math.PI / 180);
			g2d.drawString(randChar, 13 * i, 20);
			g2d.rotate(-rot * Math.PI / 180);
			// 拼接验证码
			randomString.append(randChar);
		}
		return randomString.toString();
	}

	/**
	 * 获取随机字符,并返回字符的String格式
	 * @param index (指定位置)
	 * @return
	 */
	private static String getRandomChar(int index)
	{
		//获取指定位置index的字符，并转换成字符串表示形式
		return String.valueOf(randString.charAt(index));
	}

	/**
	 * <p>标题：验证码类</p>
	 * <p>功能：</p>
	 * <pre>
	 * 其他说明：
	 * </pre>
	 * <p>作者：lizh</p>
	 * <p>审核：</p>
	 * <p>重构：</p>
	 * <p>创建日期：2021年02月24日 17:00</p>
	 * <p>类全名：com.dm.system.utils.ValidateCodeUtil.Validate</p>
	 * 查看帮助：<a href="" target="_blank"></a>
	 */
	public static class Validate implements Serializable
	{
		private static final long   serialVersionUID = 1L;
		//Base64值
		private              String Base64Str;
		//验证码值
		private              String value;

		public String getBase64Str()
		{
			return Base64Str;
		}

		public void setBase64Str(String base64Str)
		{
			Base64Str = base64Str;
		}

		public String getValue()
		{
			return value;
		}

		public void setValue(String value)
		{
			this.value = value;
		}
	}
}
