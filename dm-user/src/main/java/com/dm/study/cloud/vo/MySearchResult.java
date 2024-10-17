package com.dm.study.cloud.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年10月06日 16:40</p>
 * <p>类全名：com.dm.study.cloud.vo.MySearchResult</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class MySearchResult implements Serializable {
	private static final long   serialVersionUID = 5148784625746884266L;
	@JsonProperty("index")
	private              int    index;
	@JsonProperty("title")
	private              String title;
	@JsonProperty("link")
	private              String link;
	@JsonProperty("content")
	private              String content;
	@JsonProperty("icon")
	private              String icon;
	@JsonProperty("media")
	private              String media;
	@JsonProperty("refer")
	private              String refer;
}
