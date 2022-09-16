package com.dm.study.cloud.param;

import com.dm.study.cloud.annotation.SensitiveFiled;
import com.dm.study.cloud.vo.QueryParams;
import lombok.Getter;
import lombok.Setter;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月18日 21:16</p>
 * <p>类全名：com.dm.study.cloud.param.DmUserQueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
// @SensitiveData
@Getter
@Setter
public class DmUserQueryParams extends QueryParams {
	private static final long    serialVersionUID = 6725721872463615005L;
	/**
	 * id
	 */
	private              Integer id;
	/**
	 * 用户名
	 */
	private              String  username;
	/**
	 * 昵称
	 */
	private              String  nickname;
	/**
	 * 邮箱
	 */
	@SensitiveFiled
	private              String  email;
	/**
	 * 状态
	 */
	private              Integer status;

	@Override
	public String toString() {
		return "DmUserQueryParams{" + "id=" + id + ", username='" + username + '\'' + ", nickname='" + nickname + '\'' + ", email='" + email + '\'' + ", status=" + status + '}';
	}
}
