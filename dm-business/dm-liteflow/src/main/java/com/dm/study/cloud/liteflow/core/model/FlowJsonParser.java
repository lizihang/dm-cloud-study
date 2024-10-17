package com.dm.study.cloud.liteflow.core.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import lombok.Data;

import java.util.*;

@Data
public class FlowJsonParser {
    public static JsonAstModel json2jsonAstModel(JSONObject json){
        JsonAstModel jsonAstModel = new JsonAstModel();
        List<JsonAstModel.NodeEntity> nodeEntities = new ArrayList<>();
        List<JsonAstModel.NodeEdge> nodeEdges = new ArrayList<>();

        JSONArray nodes = json.getJSONArray("nodes");
        Map<String,Object> map = null;
        JsonAstModel.NodeEntity nodeEntity = null;
        NodeEnum nodeType=null;

        Map<String,String> paramMap = null;
        String id = null;
        String name = null;
        String type = null;
        Map<String,String>  data = null;
        for(Object obj : nodes){
            map =(HashMap)obj;
            id = (String)map.get("id");
            nodeEntity = new JsonAstModel.NodeEntity();
            nodeEntity.setId(id);
            data = (HashMap)map.get("data");
            //            name = data.get("name");
            name=id;
            nodeEntity.setName(name);
            type = data.get("type");
            nodeEntity.setType(type);

            paramMap = new HashMap<>();
            nodeEntity.setData(paramMap);//输入参数
            nodeType = NodeEnum.valueByName(type.toUpperCase(Locale.CHINA));
            if(nodeType==null){
                nodeType=NodeEnum.COMMON;
            }

            nodeEntity.setNodeType(nodeType.name());
            nodeEntity.setLabel(name);

            nodeEntities.add(nodeEntity);
        }
        nodeEntity = null;
        map=null;
        JSONArray edges = json.getJSONArray("edges");

        JsonAstModel.NodeEdge nodeEdge = null;
        String tag=null;
        for(Object obj : edges){
            map =(HashMap)obj;
            nodeEdge = new JsonAstModel.NodeEdge();
            nodeEdge.setSource((String)map.get("source"));
            nodeEdge.setTarget((String)map.get("target"));
            tag = (String)map.get("target");
            nodeEdge.setTag(tag);
            nodeEdges.add(nodeEdge);
        }
        map = null;
        nodeEdge = null;

        jsonAstModel.setNodeEdges(nodeEdges);
        jsonAstModel.setNodeEntities(nodeEntities);
        return jsonAstModel;
    }
}

