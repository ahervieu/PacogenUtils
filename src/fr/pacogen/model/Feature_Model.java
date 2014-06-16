package fr.pacogen.model;

import java.util.Iterator;
import java.util.LinkedList;





public class Feature_Model {

	private String Name  = "";
	
	private Node root;
	private LinkedList<Node> nodelist ;
	private  LinkedList<Model_Operator> operatorList ;
	private LinkedList<CCT> ctrList ;
	
	public Feature_Model(String name) {
		super();
		Name = name;
		nodelist = new LinkedList<Node>();
		operatorList = new LinkedList<Model_Operator>();
		ctrList = new LinkedList<CCT>();
	}
	
	public LinkedList<CCT> getCtrList() {
		return ctrList;
	}

	public void setCtrList(LinkedList<CCT> ctrList) {
		this.ctrList = ctrList;
	}

	public Feature_Model() {
		super();
		Name = "";
		nodelist = new LinkedList<Node>();
		operatorList = new LinkedList<Model_Operator>();
		ctrList = new LinkedList<CCT>();
	}

	public Node getNodeFromID(String ID)
	{
		Node Res = null ;
		
		Iterator<Node> nodeIt = nodelist.iterator() ;
		boolean found = false ;
		while (nodeIt.hasNext() && !found)
		{
			Node currNode = nodeIt.next() ;
			if (currNode.getId().equals(ID))
					{
					found = true ;
					Res = currNode;
					}
		}
	return Res ;
		
	}
	
	
	public String getPrologCtr()
	{
	String res = "";
		
		for (Node mo : nodelist) {
			String str= mo.toPrologCtr() ;
			if(!str.equals("")){
				res += str + ",";
			
			}
		
		}
		res = res.substring(0,res.length()-1) ;
		return res;
	}
	
	public String getPrologFeature()
	{
		String res = "";
		
		for (Node mo : nodelist) {
			
			res += mo.getPrologName() + ",";
		}
		res = res.substring(0,res.length()-1) ;
		return res;
	}
	
	public String getFeatures()
	{
		String res = "";
		
		for (Node mo : nodelist) {
			
			res += mo.getName() + ",";
		}
		return res;
	}
	
	
	public String getIds()
	{
		String res = "";
		
		for (Node mo : nodelist) {
			
			res += mo.getId() + ",";
		}
		return res;
	}
	
	public String getPrologCCT()
	{
		String res = "";
		if(!ctrList.isEmpty()){
		for (CCT mo : ctrList) {
		
			res += mo.toProlog() + ",";
		}
		res = res.substring(0,res.length()-1) ;
		}
		return res;
	}
	
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	public LinkedList<Node> getNodelist() {
		return nodelist;
	}
	
	public void setNodelist(LinkedList<Node> nodelist) {
		this.nodelist = nodelist;
	}
	
	public LinkedList<Model_Operator> getOperatorList() {
		return operatorList;
	}
	
	public void setOperatorList(LinkedList<Model_Operator> operatorList) {
		this.operatorList = operatorList;
	}

	
	
	
	
}
