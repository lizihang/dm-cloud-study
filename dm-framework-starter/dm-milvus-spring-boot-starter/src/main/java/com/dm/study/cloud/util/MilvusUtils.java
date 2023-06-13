package com.dm.study.cloud.util;

import com.dm.study.cloud.exception.CustomMilvusException;
import com.dm.study.cloud.param.CollectionParams;
import com.dm.study.cloud.param.FieldTypeParams;
import com.dm.study.cloud.param.MilvusSearchParams;
import io.milvus.client.MilvusServiceClient;
import io.milvus.common.clientenum.ConsistencyLevelEnum;
import io.milvus.grpc.DataType;
import io.milvus.grpc.MutationResult;
import io.milvus.grpc.SearchResults;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.collection.LoadCollectionParam;
import io.milvus.param.collection.ReleaseCollectionParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.dml.SearchParam;
import io.milvus.param.index.CreateIndexParam;
import io.milvus.response.SearchResultsWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.*;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 16:20</p>
 * <p>类全名：com.dm.study.cloud.util.MilvusUtils</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class MilvusUtils {
	@Resource
	MilvusServiceClient milvusClient;

	public boolean createCollection(CollectionParams param) {
		String collectionName = param.getCollectionName();
		String description = param.getDescription();
		int shardsNum = param.getShardsNum();
		List<FieldTypeParams> ftParams = param.getFiledTypeList();
		/*
		FieldType fieldType1 = FieldType.newBuilder() //
				.withName("book_id") //
				.withDataType(DataType.Int64) //
				.withPrimaryKey(true) //
				.withAutoID(false) //
				.build();
		FieldType fieldType2 = FieldType.newBuilder() //
				.withName("word_count") //
				.withDataType(DataType.Int64) //
				.build(); //
		FieldType fieldType3 = FieldType.newBuilder() //
				.withName("book_intro") //
				.withDataType(DataType.FloatVector) //
				.withDimension(2) //
				.build();
		CreateCollectionParam createCollectionReq = CreateCollectionParam.newBuilder() //
				.withCollectionName(collectionName) //
				.withDescription("Test book search java") //
				.withShardsNum(2) //
				.addFieldType(fieldType1) //
				.addFieldType(fieldType2) //
				.addFieldType(fieldType3) //
				.build();
		*/
		CreateCollectionParam.Builder builder = CreateCollectionParam.newBuilder() //
				.withCollectionName(collectionName) //
				.withDescription(description) //
				.withShardsNum(shardsNum);
		for (FieldTypeParams fieldTypeParam : ftParams) {
			FieldType.Builder fieldBuilder = FieldType.newBuilder();
			if (fieldTypeParam.getAutoID() != null) {
				fieldBuilder.withAutoID(fieldTypeParam.getAutoID());
			}
			if (fieldTypeParam.getPrimaryKey() != null) {
				fieldBuilder.withPrimaryKey(fieldTypeParam.getPrimaryKey());
			}
			if (fieldTypeParam.getDataTypeValue() != null) {
				fieldBuilder.withDataType(DataType.forNumber(fieldTypeParam.getDataTypeValue()));
			}
			if (StringUtils.isNotEmpty(fieldTypeParam.getDescription())) {
				fieldBuilder.withDescription(fieldTypeParam.getDescription());
			}
			if (StringUtils.isNotEmpty(fieldTypeParam.getName())) {
				fieldBuilder.withName(fieldTypeParam.getName());
			}
			if (fieldTypeParam.getDimension() != null) {
				fieldBuilder.withDimension(fieldTypeParam.getDimension());
			}
			FieldType fieldType = fieldBuilder.build();
			builder.addFieldType(fieldType);
		}
		CreateCollectionParam createCollectionParam = builder.build();
		// 创建collection
		R<RpcStatus> result = milvusClient.createCollection(createCollectionParam);
		System.out.println("collection:" + result.toString());
		if (result.getStatus() != R.Status.Success.getCode()) {
			throw new CustomMilvusException(result.getMessage(), 500);
		}
		return true;
	}

	public boolean insertEntities(MilvusSearchParams param) {
		String collectionName = param.getCollectionName();
		// 1.模拟数据
		Random ran = new Random();
		List<Long> book_id_array = new ArrayList<>();
		List<Long> word_count_array = new ArrayList<>();
		List<List<Float>> book_intro_array = new ArrayList<>();
		for (long i = 0L; i < 2000; ++i) {
			book_id_array.add(i);
			word_count_array.add(i + 10000);
			List<Float> vector = new ArrayList<>();
			for (int k = 0; k < 2; ++k) {
				vector.add(ran.nextFloat());
			}
			book_intro_array.add(vector);
		}
		// 2
		List<InsertParam.Field> fields = new ArrayList<>();
		fields.add(new InsertParam.Field("book_id", book_id_array));
		fields.add(new InsertParam.Field("word_count", word_count_array));
		fields.add(new InsertParam.Field("book_intro", book_intro_array));
		InsertParam insertParam = InsertParam.newBuilder() //
				.withCollectionName(collectionName) //
				//.withPartitionName("novel") //
				.withFields(fields) //
				.build();
		// 3.插入
		R<MutationResult> result = milvusClient.insert(insertParam);
		if (result.getStatus() != R.Status.Success.getCode()) {
			throw new CustomMilvusException(result.getMessage(), 500);
		}
		return true;
	}

	@PostMapping("/buildIndex")
	public boolean buildIndex(MilvusSearchParams param) {
		String collectionName = param.getCollectionName();
		final IndexType INDEX_TYPE = IndexType.IVF_FLAT;   // IndexType
		final String INDEX_PARAM = "{\"nlist\":1024}";     // ExtraParam
		R<RpcStatus> result = milvusClient.createIndex( //
				CreateIndexParam.newBuilder() //
						.withCollectionName(collectionName) //
						.withFieldName("book_intro") //
						.withIndexType(INDEX_TYPE) //
						.withMetricType(MetricType.L2) //
						.withExtraParam(INDEX_PARAM) //
						.withSyncMode(Boolean.FALSE) //
						.build() //
		);
		if (result.getStatus() != R.Status.Success.getCode()) {
			throw new CustomMilvusException(result.getMessage(), 500);
		}
		return true;
	}

	public boolean vectorSimilaritySearch(MilvusSearchParams param) {
		String collectionName = param.getCollectionName();
		// 加载collection
		milvusClient.loadCollection( //
				LoadCollectionParam.newBuilder() //
						.withCollectionName(collectionName) //
						.build() //
		);
		final Integer SEARCH_K = 2;                       // TopK
		final String SEARCH_PARAM = "{\"nprobe\":10, \"offset\":5}";    // Params
		// 参数
		List<String> search_output_fields = Collections.singletonList("book_id");
		List<List<Float>> search_vectors = Collections.singletonList(Arrays.asList(0.80f, 0.95f));
		// param
		SearchParam searchParam = SearchParam.newBuilder() //
				.withCollectionName(collectionName) //
				.withConsistencyLevel(ConsistencyLevelEnum.STRONG) //
				.withMetricType(MetricType.L2) //
				.withOutFields(search_output_fields) //
				.withTopK(SEARCH_K) //
				.withVectors(search_vectors) //
				.withVectorFieldName("book_intro") //
				.withParams(SEARCH_PARAM) //
				.build();
		R<SearchResults> respSearch = milvusClient.search(searchParam);
		// 结果
		SearchResultsWrapper wrapperSearch = new SearchResultsWrapper(respSearch.getData().getResults());
		System.out.println(wrapperSearch.getIDScore(0));
		System.out.println(wrapperSearch.getFieldData("book_id", 0));
		// 释放collection
		milvusClient.releaseCollection( //
				ReleaseCollectionParam.newBuilder() //
						.withCollectionName(collectionName) //
						.build() //
		);
		return true;
	}
}
