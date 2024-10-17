package com.dm.study.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.param.NmapScanQueryParams;
import com.dm.study.cloud.param.ScanParams;
import com.dm.study.cloud.po.NmapScanRecord;
import com.dm.study.cloud.service.NmapScanRecordService;
import com.dm.study.cloud.util.NmapUtil;
import com.dm.study.cloud.vo.Result;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年01月31日 16:02</p>
 * <p>类全名：com.dm.study.cloud.controller.NmapController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/nmap")
public class NmapController {
	@Resource
	NmapScanRecordService service;

	/**
	 * 查询nmap扫描记录分页信息
	 * @param params 参数
	 * @return 分页数据
	 */
	@PostMapping("/queryRecordPage")
	public Result queryRecordPage(@RequestBody NmapScanQueryParams params) {
		return Result.success("查询成功！", service.queryPage(params));
	}

	@PostMapping("/startScan")
	public Result startScan(@RequestBody ScanParams params) {
		System.out.println(JSON.toJSONString(params));
		NmapUtil.localExec(params);
		return Result.success("执行成功！");
	}

	@PostMapping(value = "/sse/startScan", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<String>> streamEvents(@RequestBody ScanParams params) {
		// 文件名
		String outName = "D:\\safe\\out\\out_" + System.currentTimeMillis() + ".xml";
		params.setOutName(outName);
		NmapScanRecord record = new NmapScanRecord();
		record.setOutFile(outName);
		service.save(record);
		//
		int total = 1;
		CountDownLatch latch = new CountDownLatch(total);
		/*
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(() -> {
				try {
					Random random = new Random();
					int sleepTime = random.nextInt(10) + 5;
					System.out.println("Thread finished after sleeping for " + sleepTime + " seconds");
					Thread.sleep(sleepTime * 1000);
					latch.countDown(); // 通知计数器减一
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		*/
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(() -> {
			try {
				NmapUtil.localExec(params);

				Thread.sleep(3000);
				latch.countDown(); // 通知计数器减一
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Map<String,Object> result = new HashMap<>();
		return Flux.interval(Duration.ofSeconds(1)).map(i -> {
			int count = (int) latch.getCount();
			System.out.println("还有" + count + "个线程未执行完毕！");
			int percent = (total - count) * 100 / total;
			if (percent == 100) {
				result.put("finished", true);
			}
			result.put("percent", percent);
			return ServerSentEvent.<String>builder() //
					.id(i + "") //
					.event("delta") //
					.comment("comment") //
					.data(JSON.toJSONString(result)) //
					.build();
		}).takeUntil(i -> {
			boolean b = (int) latch.getCount() == 0;
			System.out.println("takeUntil i=" + i);
			System.out.println("takeUntil: " + b);
			return b;
		});
		/*
		//
		NmapUtil.localExec(params);
		//
		Map<String,Object> result = new HashMap<>();
		result.put("finished", false);
		String[] arr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		int total = arr.length;
		return Flux.range(1, total).map(i -> {
			result.put("delta", arr[i - 1]);
			int percent = i * 100 / total;
			result.put("percent", percent);
			if (percent == 100) {
				result.put("finished", true);
			}
			return ServerSentEvent.<String>builder() //
					.id(i + "") //
					.event("delta") //
					.comment("comment") //
					.data(JSON.toJSONString(result)) //
					.build();
		}).doOnEach(v -> {
			System.out.println("v:" + v);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		*/
	}
}
