package com.dm.study.cloud.util;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.function.Function;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年05月11日 18:10</p>
 * <p>类全名：com.dm.study.cloud.util.TikaUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class TikaUtil {
	/**
	 * 从上传PDF文件中，读取内容
	 * @param file
	 * @return
	 */
	public static String getPDFString(MultipartFile file, Function<String,String> contentHandler) {
		// 1.读取PDF
		Metadata metadata = new Metadata();
		ParseContext parseContext = new ParseContext();
		PDFParser pdfParser = new PDFParser();
		BodyContentHandler handler = new BodyContentHandler();
		String inputStr = "";
		try (InputStream in = file.getInputStream()) {
			pdfParser.parse(in, handler, metadata, parseContext);
			System.out.println("文件属性信息：");
			for (String name : metadata.names()) {
				System.out.println(name + ":" + metadata.get(name));
			}
			System.out.println("pdf文件内容：");
			inputStr = handler.toString();
			System.out.println("contentHandler处理后的内容：");
			inputStr = contentHandler.apply(inputStr);
			System.out.println(inputStr);
		} catch (Exception e) {
			System.out.println("e");
		}
		return inputStr;
	}

	public static String getPDFString(MultipartFile file) {
		return getPDFString(file, str -> str //
				.replaceAll("\n\n", "") //
				.replaceAll(" ", "") //
				.replaceAll(",", "") //
				.replaceAll("。", ""));
	}
}
