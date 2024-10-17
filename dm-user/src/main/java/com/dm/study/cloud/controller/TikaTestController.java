// package com.dm.study.cloud.controller;
//
// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.JSONArray;
// import com.alibaba.fastjson.JSONObject;
// import com.dm.study.cloud.exception.DmException;
// import com.dm.study.cloud.util.TikaUtil;
// import com.dm.study.cloud.vo.Result;
// import com.huaban.analysis.jieba.JiebaSegmenter;
// import io.milvus.client.MilvusServiceClient;
// import io.milvus.grpc.MutationResult;
// import io.milvus.param.R;
// import io.milvus.param.dml.InsertParam;
// import org.ansj.domain.Term;
// import org.ansj.splitWord.analysis.NlpAnalysis;
// import org.apache.http.HttpEntity;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.text.PDFTextStripper;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
//
// import javax.annotation.Resource;
// import java.io.InputStream;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// /**
//  * <p>标题：</p>
//  * <p>功能：</p>
//  * <pre>
//  * 其他说明：
//  * </pre>
//  * <p>作者：lizh</p>
//  * <p>审核：</p>
//  * <p>重构：</p>
//  * <p>创建日期：2023年05月08日 11:42</p>
//  * <p>类全名：com.dm.study.cloud.controller.TikaTestController</p>
//  * 查看帮助：<a href="" target="_blank"></a>
//  */
// @CrossOrigin
// @RestController
// @RequestMapping("/tika")
// public class TikaTestController {
// 	@Resource
// 	MilvusServiceClient milvusClient;
//
// 	/**
// 	 * @param file
// 	 * @return
// 	 */
// 	@PostMapping("/testParsePDF")
// 	public Result testParsePDF(MultipartFile file) throws Exception {
// 		// 1.读取PDF
// 		String inputStr = TikaUtil.getPDFString(file);
// 		// 2.jieba分词
// 		JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
// 		List<String> strings = jiebaSegmenter.sentenceProcess(inputStr);
// 		// 为了测试，少传点
// 		List<String> testData = new ArrayList<>();
// 		for (int i = 0; i < 5; i++) {
// 			testData.add(strings.get(i));
// 		}
// 		// ansj分词
// 		org.ansj.domain.Result parse = NlpAnalysis.parse(inputStr);
// 		for (Term term : parse.getTerms()) {
// 			System.out.println(term.getName());
// 		}
// 		// 3.转向量
// 		HttpPost httpPost = new HttpPost("http://vpn2.rendersea.com:18501/botong/embed/texts");
// 		HashMap<Object,Object> bodyParam = new HashMap<>();
// 		bodyParam.put("texts", strings);
// 		String bodyStr = JSON.toJSONString(bodyParam);
// 		StringEntity entity = new StringEntity(bodyStr, "UTF-8");
// 		entity.setContentEncoding("UTF-8");
// 		entity.setContentType("application/json");
// 		httpPost.setEntity(entity);
// 		CloseableHttpClient httpClient = HttpClients.createDefault();
// 		CloseableHttpResponse response = httpClient.execute(httpPost);
// 		HttpEntity resEntity = response.getEntity();
// 		String resStr = EntityUtils.toString(resEntity, "UTF-8");
// 		// 4.构造milvus数据
// 		JSONObject jsonObject = JSON.parseObject(resStr);
// 		String id = jsonObject.getString("id");
// 		JSONArray data = jsonObject.getJSONArray("data");
// 		// milvus字段
// 		List<String> id_array = new ArrayList<>();
// 		List<List<Float>> vec_array = new ArrayList<>();
// 		int i = 1;
// 		// 处理数据
// 		for (Object obj : data) {
// 			JSONArray ja = (JSONArray) obj;
// 			List<Float> flist = new ArrayList<>();
// 			for (Object o : ja) {
// 				System.out.println(o);
// 				Float f = new Float(o.toString());
// 				flist.add(f);
// 			}
// 			vec_array.add(flist);
// 			id_array.add(id + "-" + i);
// 			i++;
// 			System.out.println("==================");
// 		}
// 		// 5.存到milvus
// 		List<InsertParam.Field> fields = new ArrayList<>();
// 		fields.add(new InsertParam.Field("id", id_array));
// 		fields.add(new InsertParam.Field("word", strings));
// 		fields.add(new InsertParam.Field("vec", vec_array));
// 		InsertParam insertParam = InsertParam.newBuilder() //
// 				.withCollectionName("test") //
// 				//.withPartitionName("novel") //
// 				.withFields(fields) //
// 				.build();
// 		R<MutationResult> result = milvusClient.insert(insertParam);
// 		if (result.getStatus() != R.Status.Success.getCode()) {
// 			throw new DmException(result.getMessage());
// 		}
// 		return Result.success("成功！");
// 	}
//
// 	/**
// 	 * @param file
// 	 * @return
// 	 */
// 	@PostMapping("/testAnsj")
// 	public Result testAnsj(MultipartFile file) {
// 		// 1.读取PDF
// 		String inputStr = TikaUtil.getPDFString(file);
// 		// ansj分词
// 		org.ansj.domain.Result parse = NlpAnalysis.parse(inputStr);
// 		for (Term term : parse.getTerms()) {
// 			System.out.println(term.getName());
// 		}
// 		return Result.success("成功！");
// 	}
//
// 	/**
// 	 * @param file
// 	 * @return
// 	 */
// 	@PostMapping("/testJieba")
// 	public Result testJieba(MultipartFile file) {
// 		// 1.读取PDF
// 		String inputStr = TikaUtil.getPDFString(file);
// 		// 2.jieba分词
// 		JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
// 		List<String> strings = jiebaSegmenter.sentenceProcess(inputStr);
// 		for (String s : strings) {
// 			System.out.println(s);
// 		}
// 		return Result.success("成功！");
// 	}
//
// 	/**
// 	 * @param file
// 	 * @return
// 	 */
// 	@PostMapping("/testPDFBox")
// 	public Result testPDFBox(MultipartFile file) throws Exception {
// 		try (InputStream in = file.getInputStream()) {
// 			PDDocument load = PDDocument.load(in);
// 			PDFTextStripper stripper = new PDFTextStripper();
// 			stripper.setSortByPosition(true);
// 			String text = stripper.getText(load);
// 			System.out.println(text);
// 		}
// 		// 为了测试，少传点
// 		return Result.success("成功！");
// 	}
// }
//
