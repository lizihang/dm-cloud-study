package com.dm.study.cloud.controller;

import com.dm.study.cloud.annotation.DmLog;
import com.dm.study.cloud.util.ValidateCodeUtil;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年08月15日 10:21</p>
 * <p>类全名：com.dm.study.cloud.controller.LoginController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
// @CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
	/**
	 * 生成验证码
	 */
	@DmLog
	@GetMapping("/getCodeImg")
	public Result getCodeImg() {
		//直接调用静态方法，返回验证码对象
		ValidateCodeUtil.Validate v = ValidateCodeUtil.getRandomCode();
		Map<String,Object> data = new HashMap<>();
		if (v != null) {
			data.put("validCode", v.getValue().toLowerCase());
			data.put("validStr", v.getBase64Str());
		}
		return Result.success("获取验证码成功", data);
	}
}
