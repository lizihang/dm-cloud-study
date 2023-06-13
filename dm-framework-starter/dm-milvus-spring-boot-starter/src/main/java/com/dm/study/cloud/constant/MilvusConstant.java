package com.dm.study.cloud.constant;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 16:14</p>
 * <p>类全名：com.dm.study.cloud.constant.MilvusConstant</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class MilvusConstant {
	/**
	 * 集合名称(库名)
	 */
	public static final String  COLLECTION_NAME  = "face_archive";
	/**
	 * 分片数量
	 */
	public static final Integer SHARDS_NUM       = 8;
	/**
	 * 分区数量
	 */
	public static final Integer PARTITION_NUM    = 16;
	/**
	 * 分区前缀
	 */
	public static final String  PARTITION_PREFIX = "shards_";
	/**
	 * 特征值长度
	 */
	public static final Integer FEATURE_DIM      = 256;
	/**
	 * 字段
	 */
	public static class Field {
		/**
		 * 档案id
		 */
		public static final String ARCHIVE_ID      = "archive_id";
		/**
		 * 小区id
		 */
		public static final String ORG_ID          = "org_id";
		/**
		 * 档案特征值
		 */
		public static final String ARCHIVE_FEATURE = "archive_feature";
	}

	/**
	 * 通过组织id计算分区名称
	 * @param orgId
	 * @return
	 */
	public static String getPartitionName(Integer orgId) {
		return PARTITION_PREFIX + (orgId % PARTITION_NUM);
	}
}
