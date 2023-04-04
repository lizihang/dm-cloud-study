package com.dm.study.cloud.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * <p>标题：自定义测试用LoadBalancer</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 *     开发联调时，前端增加请求头‘toServer’，值为请求需要转发到的服务的IP。
 *     如果不传或toServer上没有对应服务，则走默认轮询负载均衡。
 * 作用：
 *     可以将公共服务放在服务器上，后台单独启动需要调试的服务即可，并且可以多人同时联调。
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月23日 11:27</p>
 * <p>类全名：com.dm.study.cloud.core.CustomTestLoadBalancer</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CustomTestLoadBalancer implements ReactorServiceInstanceLoadBalancer {
	private static final Log           log = LogFactory.getLog(CustomTestLoadBalancer.class);
	final                AtomicInteger position;
	final                String        serviceId;
	ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

	public CustomTestLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
		this(serviceInstanceListSupplierProvider, serviceId, new Random().nextInt(1000));
	}

	public CustomTestLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId, int seedPosition) {
		this.serviceId = serviceId;
		this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
		this.position = new AtomicInteger(seedPosition);
	}

	@Override
	public Mono<Response<ServiceInstance>> choose(Request request) {
		ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
		String toServer = null;
		List<String> toServerList = ((RequestDataContext) request.getContext()).getClientRequest().getHeaders().get("toServer");
		if (toServerList != null && toServerList.size() == 1) {
			toServer = toServerList.get(0);
		}
		String finalToServer = toServer;
		return supplier.get(request).next().map(serviceInstances -> processInstanceResponse(supplier, serviceInstances, finalToServer));
	}

	private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier, List<ServiceInstance> serviceInstances, String toServer) {
		Response<ServiceInstance> serviceInstanceResponse = getInstanceResponse(serviceInstances, toServer);
		if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
			((SelectedInstanceCallback) supplier).selectedServiceInstance(serviceInstanceResponse.getServer());
		}
		return serviceInstanceResponse;
	}

	private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, String toServer) {
		if (instances.isEmpty()) {
			if (log.isWarnEnabled()) {
				log.warn("No servers available for service: " + serviceId);
			}
			return new EmptyResponse();
		}
		if (toServer != null) {
			for (ServiceInstance ins : instances) {
				// 192.168.252.140
				// if (ins.getHost().equals(toServer)) {
				if (ins.getPort() == Integer.parseInt(toServer)) {
					return new DefaultResponse(ins);
				}
			}
		}
		int pos = Math.abs(this.position.incrementAndGet());
		ServiceInstance instance = instances.get(pos % instances.size());
		return new DefaultResponse(instance);
	}
}
