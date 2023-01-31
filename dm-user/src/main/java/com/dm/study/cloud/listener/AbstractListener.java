package com.dm.study.cloud.listener;

import com.dm.study.cloud.listener.event.AbstractEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 *   微服务的架构下，某个微服务作为其他业务的扩展功能时，需要提供接口供其他服务调用。
 *   而抽象事件及监听器也可以提供给其他服务，作为约束。
 * 业务场景：
 *   例如本服务为积分服务，当用户服务需要集成积分服务时，POM文件可以依赖积分服务提供的API，从而可以调用积分服务的接口等。
 *   获取积分的方式有多种，那么我们可以定义多种事件来触发获取积分，并定义对应的监听器来实现。
 *   那么，我们可以定义抽象事件作为这一组事件的父类，定义抽象监听器作为这一组监听器的父类。
 * 作用：
 *   1.抽象事件中，可以扩展业务需要的属性，并作为此业务所有事件的公共父类。
 *   2.抽象监听器，可以开发公共逻辑，并且方法增加final关键字，防止子类复写，从而破坏逻辑。
 *   3.抽象监听器，提供抽象方法，供子类实现。
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年01月31日 8:56</p>
 * <p>类全名：com.dm.study.cloud.listener.AbstractListener</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public abstract class AbstractListener<E extends AbstractEvent> implements ApplicationListener<E> {
	// final 控制子类不能复写此方法，只需要时间业务逻辑部分的抽象方法（doListenerBusiness）
	// @NonNull 不加注解的时候event参数提示Not annotated parameter overrides @NonNullApi parameter
	@Override
	public final void onApplicationEvent(@NonNull E event) {
		doListenerBusiness(event);
	}

	abstract void doListenerBusiness(E event);
}
