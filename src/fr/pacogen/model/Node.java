package fr.pacogen.model;

import java.util.LinkedList;



public class Node {
	
	private String Name ;
	private String Id ;
	private LinkedList<Model_Operator> OpList ;
	
	
	
	
	public Node(String name, String id) {
		super();
		Name = name;
		Id = id;
		OpList = new LinkedList<Model_Operator>() ;
	}


	public Node()
	{
		super();
		Name = "";
		Id = "";
		OpList = new LinkedList<Model_Operator>() ;
	}

	public String getName() {
		return Name;
	}




	public void setName(String name) {
		Name = name;
	}




	public String getId() {
		return Id;
	}




	public void setId(String id) {
		Id = id;
	}




	public LinkedList<Model_Operator> getOpList() {
		return OpList;
	}




	public void setOpList(LinkedList<Model_Operator> opList) {
		OpList = opList;
	}

	public String toPrologCtr()
	{
		String res = "";
		if(OpList.size() != 0){
			for (Model_Operator mo : OpList) {
				res += mo.toProlog() + "," ;
			}
			res = res.substring(0,res.length() -1) ;
		}
		
		return res ;
	}


	public String getPrologName()
	{
		String res = "a" + fr.pacogen.utils.StringOperation.sansAccent(Id);
		return res.toUpperCase() ;
		
	}

}
