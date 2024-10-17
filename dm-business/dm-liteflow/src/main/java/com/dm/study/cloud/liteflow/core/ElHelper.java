package com.dm.study.cloud.liteflow.core;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import com.dm.study.cloud.liteflow.core.factory.NodeFactory;
import com.dm.study.cloud.liteflow.core.function.NodeFunction;
import com.dm.study.cloud.liteflow.core.model.JsonAstModel;
import com.dm.study.cloud.liteflow.core.nodeDefinition.Node;
import com.dm.study.cloud.liteflow.core.nodeDefinition.SummaryNode;
import com.dm.study.cloud.liteflow.core.nodeDefinition.SwitchNode;
import com.dm.study.cloud.liteflow.core.nodeDefinition.WhenNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.SwitchELWrapper;
import com.yomahub.liteflow.builder.el.ThenELWrapper;
import com.yomahub.liteflow.builder.el.WhenELWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
public class ElHelper {
	/**
	 * nodeAst转El表达式
	 * @param head
	 * @return
	 */
	public static String ast2El(Node head) {
		if (head == null) {
			return null;
		}
		// 用于存放if、when、switch节点的ThenELWrapper
		Deque<ThenELWrapper> stack = new ArrayDeque<>();
		// 用于标记是否处理过summary节点了
		Set<String> doneSummary = Sets.newHashSet();
		return tree2El(head, ELBus.then(), stack, doneSummary).toEL();
	}

	private static ThenELWrapper tree2El(Node currentNode, ThenELWrapper currentThenElWrapper, Deque<ThenELWrapper> stack, Set<String> doneSummary) {
		NodeFunction.dealNode(currentNode.getNodeEnum(),
				// common
				() -> {
					currentThenElWrapper.then(currentNode.getName());
					for (Node nextNode : currentNode.getNext()) {
						tree2El(nextNode, currentThenElWrapper, stack, doneSummary);
					}
				},
				// when
				() -> {
					stack.push(currentThenElWrapper);
					WhenELWrapper whenELWrapper = ELBus.when().ignoreError(Boolean.TRUE);
					currentThenElWrapper.then(whenELWrapper);
					for (Node nextNode : currentNode.getNext()) {
						ThenELWrapper thenELWrapper = ELBus.then();
						whenELWrapper.when(thenELWrapper);
						tree2El(nextNode, thenELWrapper, stack, doneSummary);
					}
				},
				// switch
				() -> {
					stack.push(currentThenElWrapper);
					SwitchNode switchNode = convert2SwitchNode(currentNode);
					SwitchELWrapper switchELWrapper = ELBus.switchOpt(currentNode.getName());
					currentThenElWrapper.then(switchELWrapper);
					for (Map.Entry<Node,String> nextNodeTag : switchNode.getNodeTagMap().entrySet()) {
						ThenELWrapper wrapper = ELBus.then().id(nextNodeTag.getValue());
						switchELWrapper.to(wrapper);
						tree2El(nextNodeTag.getKey(), wrapper, stack, doneSummary);
					}
				},
				// summary
				() -> {
					if (!doneSummary.contains(currentNode.getName())) {
						doneSummary.add(currentNode.getName());
						// 这种节点只有0个或者1个nextNode
						for (Node nextNode : currentNode.getNext()) {
							tree2El(nextNode, stack.pop(), stack, doneSummary);
						}
					}
				},
				// start
				() -> {
					for (Node nextNode : currentNode.getNext()) {
						tree2El(nextNode, currentThenElWrapper, stack, doneSummary);
					}
				},
				// end
				() -> {
				});
		return currentThenElWrapper;
	}

	/**
	 * 将JsonAst转为NodeAst
	 * @param model
	 * @return
	 */
	public static Node Json2Node(JsonAstModel model) {
		// 参数校验
		checkJsonAst(model);//暂时去掉结构校验
		List<JsonAstModel.NodeEntity> nodeEntities = model.getNodeEntities();
		List<JsonAstModel.NodeEdge> nodeEdges = Optional.ofNullable(model.getNodeEdges()).orElse(Lists.newArrayList());
		// 创建node
		Map<String,Node> nodeMap = nodeEntities.stream().map(nodeEntity -> NodeFactory.getNode(NodeEnum.valueByName(nodeEntity.getNodeType()), nodeEntity.getId(), nodeEntity.getName())).collect(Collectors.toMap(Node::getId, Function.identity()));
		// 构建节点关系
		for (JsonAstModel.NodeEdge nodeEdge : nodeEdges) {
			Node sourceNode = nodeMap.get(nodeEdge.getSource());
			Node targetNode = nodeMap.get(nodeEdge.getTarget());
			// 不同节点处理方式不同
			NodeFunction.dealNode(sourceNode.getNodeEnum(), () -> sourceNode.addNextNode(targetNode),//common
					() -> sourceNode.addNextNode(targetNode),//when
					() -> convert2SwitchNode(sourceNode).putNodeTag(targetNode, nodeEdge.getTag()),//switch
					() -> sourceNode.addNextNode(targetNode),//summary
					() -> sourceNode.addNextNode(targetNode),//start
					() -> sourceNode.addNextNode(targetNode)//end
			);
			// 设置前置节点
			targetNode.addPreNode(sourceNode);
		}
		String when = "v_node_when_";
		String summary = "v_node_summary_";
		Node whenNode = null;
		Node summaryNode = null;
		String whenId = null;
		String summaryId = null;
		int i = 0;
		int j = 0;
		Set<String> set = new HashSet<>(nodeMap.keySet());
		for (Object key : set) {
			Node node = nodeMap.get(key);
			List<Node> nextList = node.getNext();
			List<Node> preList = node.getPre();
			//以sourceNode为当前节点，查找下级节点，计算出度
			int outDegree = nextList.size();
			int inDegree = preList.size();
			if (node.getNodeEnum() == NodeEnum.COMMON && outDegree > 1) {//当前节点后方插入一个when节点，重新设置节点关系
				List<Node> tempPreList = new ArrayList<>();
				whenId = when + i++;
				whenNode = new WhenNode(whenId, whenId);
				whenNode.setNext(nextList);
				tempPreList.add(whenNode);
				node.setNext(tempPreList);
				whenNode.addPreNode(node);
				for (Node nd : nextList) {//遍历所有下级节点
					List<Node> p = nd.getPre();
					for (Node n : p) {//遍历前置节点，如果相同则移除，然后增加新的前置节点
						if (n.getId().equals(node.getId())) {
							p.remove(n);
							nd.addPreNode(whenNode);
						}
					}
				}
				nodeMap.put(whenId, whenNode);
			}
			if (inDegree > 1) {//summary，前方插入summary节点，重新设置节点关系
				List<Node> tempNextList = new ArrayList<>();
				summaryId = summary + j++;
				summaryNode = new SummaryNode(summaryId, summaryId);
				summaryNode.setPre(preList);
				summaryNode.addNextNode(node);
				tempNextList.add(summaryNode);
				node.setPre(tempNextList);
				for (Node nd : preList) {
					List<Node> p = nd.getNext();
					for (Node n : p) {//遍历前置节点，如果相同则移除，然后增加新的前置节点
						if (n.getId().equals(node.getId())) {
							p.remove(n);
							nd.addNextNode(summaryNode);
						}
					}
				}
				nodeMap.put(summaryId, summaryNode);
			}
		}
		// 只能有一个开始结点
		List<Node> start = nodeMap.values().stream().filter(node -> NodeEnum.START.equals(node.getNodeEnum())).collect(Collectors.toList());
		if (start.size() != 1) {
			throw new RuntimeException("语法树不合法，有且只能有一个开始结点！");
		}
		// 只能有一个结束结点
		List<Node> end = nodeMap.values().stream().filter(node -> NodeEnum.END.equals(node.getNodeEnum())).collect(Collectors.toList());
		if (end.size() != 1) {
			throw new RuntimeException("语法树不合法，有且只能有一个结束结点！");
		}
		for (Node node : nodeMap.values()) {
			// COMMON节点的出度和入度只能是1
			if (NodeEnum.COMMON.equals(node.getNodeEnum()) && (node.getPre().size() != 1 || node.getNext().size() != 1)) {
				throw new RuntimeException("语法树不合法，普通节点的出度和入度不能大于1");
			}
			// 开始节点出度为1，结束节点入度为1
			if (NodeEnum.START.equals(node.getNodeEnum()) && (node.getPre().size() != 0 || node.getNext().size() != 1)) {
				throw new RuntimeException("语法树不合法，开始节点的出度只能为1、入度只能为0");
			}
			if (NodeEnum.END.equals(node.getNodeEnum()) && (node.getPre().size() != 1 || node.getNext().size() != 0)) {
				throw new RuntimeException("语法树不合法，结束节点的出度只能为0、入度只能为1");
			}
		}
		return start.get(0);
	}

	private static void checkJsonAst(JsonAstModel model) {
		/**非空校验*/
		if (model == null || CollectionUtils.isEmpty(model.getNodeEntities())) {
			throw new RuntimeException("语法树不能为空");
		}
		List<JsonAstModel.NodeEntity> nodeEntities = model.getNodeEntities();
		List<JsonAstModel.NodeEdge> nodeEdges = Optional.ofNullable(model.getNodeEdges()).orElse(Lists.newArrayList());
		/**必填参数校验*/
		nodeEntities.forEach(nodeEntity -> {
			if (StringUtils.isAnyBlank(nodeEntity.getId(), nodeEntity.getLabel(), nodeEntity.getName(), nodeEntity.getNodeType())
				//                    || nodeEntity.getData()== null
			) {
				throw new RuntimeException("必填参数校验不通过，节点基本属性缺失" + nodeEntity.getLabel());
			}
		});
		nodeEdges.forEach(nodeEdge -> {
			if (StringUtils.isAnyBlank(nodeEdge.getSource(), nodeEdge.getTarget())) {
				throw new RuntimeException("必填参数校验不通过，边的基本属性缺失：" + nodeEdge.getSource() + "->" + nodeEdge.getTarget());
			}
		});
		/**校验节点和边的source target是否能对应上*/
		Set<String> nodeIdSet = nodeEntities.stream().map(JsonAstModel.NodeEntity::getId).collect(Collectors.toSet());
		nodeEdges.forEach(nodeEdge -> {
			if (!nodeIdSet.contains(nodeEdge.getSource()) || !nodeIdSet.contains(nodeEdge.getTarget())) {
				throw new RuntimeException("语法树不合法，边的源或目标与节点对应不上");
			}
		});
		// 节点分支相关校验
		int summaryNumber = 0;
		int branchNumber = 0;
		NodeEnum[] nodeEnums = { NodeEnum.SWITCH, NodeEnum.WHEN };
		for (JsonAstModel.NodeEntity nodeEntity : nodeEntities) {
			/**校验节点类型，只能是if、common、switch、when、summary、start、end*/
			NodeEnum nodeEnum = NodeEnum.valueByName(nodeEntity.getNodeType());
			if (nodeEnum == null) {
				throw new RuntimeException("语法树不合法，存在不正确的节点类型：" + nodeEntity.getNodeType());
			}
			/**校验switch的出度边是否有tag,且tag不能为空*/
			if (NodeEnum.SWITCH.equals(nodeEnum)) {
				boolean anyMatch = nodeEdges.stream().filter(nodeEdge -> nodeEntity.getId().equals(nodeEdge.getSource())).anyMatch(item -> StringUtils.isBlank(item.getTag()));
				if (anyMatch) {
					throw new RuntimeException("语法树不合法，switch节点的所有分支都必须有tag：" + nodeEntity.getLabel());
				}
			}
			/**when、switch节点的数量总和与summary类型节点数量总和校验*/
			if (ObjectUtils.containsElement(nodeEnums, nodeEnum)) {
				branchNumber++;
			}
			if (NodeEnum.SUMMARY.equals(nodeEnum)) {
				summaryNumber++;
			}
		}
		//        if (summaryNumber != branchNumber) {
		//            throw new RuntimeException("语法树不合法，SWITCH、WHEN节点必须有SUMMARY进行汇总");
		//        }
	}

	private static SwitchNode convert2SwitchNode(Node node) {
		return (SwitchNode) node;
	}
}
