// package com.dm.study.cloud.runner;
//
// import com.dm.study.cloud.param.CollectionParams;
// import com.dm.study.cloud.param.FieldTypeParams;
// import com.dm.study.cloud.util.MilvusUtils;
// import io.milvus.grpc.DataType;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
//
// import javax.annotation.Resource;
// import java.util.ArrayList;
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
//  * <p>创建日期：2024年04月18日 15:14</p>
//  * <p>类全名：com.dm.study.cloud.runner.TestRunner</p>
//  * 查看帮助：<a href="" target="_blank"></a>
//  */
// @Component
// public class TestRunner implements CommandLineRunner {
// 	@Resource
// 	MilvusUtils milvusUtils;
//
// 	@Override
// 	public void run(String... args) {
// 		CollectionParams params = new CollectionParams();
// 		params.setDatabaseName("test");
// 		params.setCollectionName("k_123456");
// 		params.setDescription("测试向量库");
// 		List<FieldTypeParams> filedTypeList = new ArrayList<>();
// 		// 主键
// 		FieldTypeParams id = new FieldTypeParams();
// 		id.setName("id");
// 		id.setAutoID(true);
// 		id.setDataTypeValue(DataType.Int64_VALUE);
// 		id.setPrimaryKey(true);
// 		filedTypeList.add(id);
// 		// 向量字段
// 		FieldTypeParams vec =  new FieldTypeParams();
// 		vec.setName("vec");
// 		vec.setDataTypeValue(DataType.FloatVector_VALUE);
// 		vec.setDimension(1024);
// 		filedTypeList.add(vec);
// 		// 标量字段
// 		FieldTypeParams field =  new FieldTypeParams();
// 		field.setName("field");
// 		field.setDataTypeValue(DataType.VarChar_VALUE);
// 		field.setMaxLength(32);
// 		filedTypeList.add(field);
// 		//
// 		params.setFiledTypeList(filedTypeList);
// 		boolean b = milvusUtils.createCollection(params);
// 	}
// }
