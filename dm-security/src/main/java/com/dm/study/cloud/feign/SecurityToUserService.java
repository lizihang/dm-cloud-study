package com.dm.study.cloud.feign;

import com.dm.study.cloud.constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年04月04日 11:21</p>
 * <p>类全名：com.dm.study.cloud.feign.SecurityToUserService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@FeignClient(name = ServiceNameConstant.DM_USER)
public interface SecurityToUserService extends ToUserService {
}
