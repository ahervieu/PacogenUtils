/*
Copyright or © or Copr. 
 contributor(s) : 
Aymeric Hervieu
Arnaud Gotlieb
Benoit Baudry
(29/05/2013)

aymeric.hervieu@gmail.com
benoit.baudry@inria.com
arnaud@simula.no

This software is a computer program whose purpose is to generate pairwise 
configurations on feature models.

This software is governed by the CeCILL-C license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the  CeCILL-C 
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL-C  license and that you accept its terms.
*/


package fr.pacogen.adapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import constraints.PropositionalFormula;

import fm.FeatureGroup;
import fm.FeatureModelException;
import fm.FeatureTreeNode;
import fm.SolitaireFeature;
import fm.XMLFeatureModel;

import fr.pacogen.model.And;
import fr.pacogen.model.Card;
import fr.pacogen.model.Feature_Model;
import fr.pacogen.model.Model_Operator;
import fr.pacogen.model.Node;
import fr.pacogen.model.Optionnal;
import fr.pacogen.model.Or;
import fr.pacogen.model.SpecialCCT;
import fr.pacogen.model.Xor;

public class SplotAdapter implements Adapter {
	
	
	private String Extension = "*.xml" ;
	private String Model ;
	private Feature_Model Fm ; 
	private HashMap<String, String> table;
	
	public SplotAdapter(String f)
	{
		
		Model = f;
		Fm = new Feature_Model();
		table = new HashMap<String, String>();
	}
	
	public Feature_Model getModel() {
		
		return Fm;
	}


	public void parse() {
		String featureModelFile = Model;
		XMLFeatureModel featureModel = new XMLFeatureModel(featureModelFile,XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModel.loadModel();
		} catch (FeatureModelException e1) {
	
			e1.printStackTrace();
		}

		String nom = featureModel.getName();
		Node Root = new Node(featureModel.getRoot().getName(),featureModel.getRoot().getID()) ;
		table.put(featureModel.getRoot().getID(), featureModel.getRoot().getName());

		buildModel(featureModel.getRoot(),Root);
		Fm.setRoot(Root);
		Fm.setName(featureModel.getName());
		Fm.getNodelist().add(Root);
		if (featureModel.getConstraints().size() != 0) {
			createCTC(featureModel,Fm);
		}
	}


	public void loadModel(String File) {
		Model = File ;
	}


	public String getExtension() {	
		return Extension;
	}
	
	private Model_Operator getOptOperator(Node n)
	{
		Model_Operator res = null;
		List<Model_Operator> lstOp = n.getOpList();
		Boolean Stop = false ;
		for (int i = 0 ; i<lstOp.size() && !Stop; i++)
		{	
			if(lstOp.get(i) instanceof Optionnal)
			{
				res = lstOp.get(i);
				Stop = true ;
			}
		}
		
	if(!Stop)
	{
		res = new Optionnal();
		Fm.getOperatorList().add(res);
		n.getOpList().add(res);	
		res.setFather(n);
	}
		return res;
	}

	private Model_Operator getAndOperator(Node n)
	{
		Model_Operator res = null;
		List<Model_Operator> lstOp = n.getOpList();
		Boolean Stop = false ;
		for (int i = 0 ; i<lstOp.size() && !Stop; i++)
		{
			
			if(lstOp.get(i) instanceof And)
			{
				res = lstOp.get(i);
				Stop = true ;
			}
		}
		if(!Stop)
		{
		res = new And();
		res.setFather(n);
		Fm.getOperatorList().add(res);
		n.getOpList().add(res);	
		}
		return res;
	}


private void buildModel(FeatureTreeNode _FTN, Node Ft)
{
	
	 table.put(_FTN.getID(), _FTN.getName());
	
		for( int i = 0 ; i < _FTN.getChildCount() ; i++ ) {	
			FeatureTreeNode Curr_FTN =(FeatureTreeNode) _FTN.getChildAt(i);
			 if ( Curr_FTN instanceof SolitaireFeature ) {
				Model_Operator Op = null;
				 if ( ((SolitaireFeature)Curr_FTN).isOptional())
					{
					  Op = getOptOperator(Ft);		
					}
					else
					{
					  Op = getAndOperator(Ft);	
					}
				 Node n2 = new Node(Curr_FTN.getName(),Curr_FTN.getID());
				 Fm.getNodelist().add(n2);
				 table.put(Curr_FTN.getID(), Curr_FTN.getName());
				 Op.getNodeVect().add(n2);
				 buildModel(Curr_FTN,n2); 
			 }else if ( Curr_FTN instanceof FeatureGroup )
			 {
				Model_Operator Op = null;			
				 int minCardinality = ((FeatureGroup)Curr_FTN).getMin();
				 int maxCardinality = ((FeatureGroup)Curr_FTN).getMax();
				 if(minCardinality==1 && maxCardinality==1 )
				 {
					 Op = new Xor();
					Fm.getOperatorList().add(Op);
					 
				 }else  if(minCardinality==1 && maxCardinality==-1 ){
				 
					 Op = new Or();
					 Fm.getOperatorList().add(Op);
				 }else
				 {
					 Op = new Card(minCardinality, maxCardinality);
					 Fm.getOperatorList().add(Op);
				 }
				 
				 for (int j = 0; j <Curr_FTN.getChildCount() ; j++)
				 {
					 Node n2 = new Node(((FeatureTreeNode)Curr_FTN.getChildAt(j)).getName(),((FeatureTreeNode)Curr_FTN.getChildAt(j)).getID());					
					 Fm.getNodelist().add(n2);
					 table.put(Curr_FTN.getID(), Curr_FTN.getName());
					 Op.getNodeVect().add(n2);	
					 Op.setFather(Ft);
					 buildModel(((FeatureTreeNode)Curr_FTN.getChildAt(j)),n2); 
				 }
				 Ft.getOpList().add(Op);
				
			 }
			else {
	
				}
			}
}



	



/* Fin du traitemement */

		public String addElement(String ID, String Name) {
			if (table.containsValue(Name)) {
				String str = random_name();
				Name = Name + str;
			}
			table.put(ID, Name);
			return Name;
		}
		
		public static String random_name() {
			Random r = new Random();
			int v = r.nextInt();
			String res = String.valueOf(v);
			return res;
		}


public void createCTC(XMLFeatureModel featureModel,Feature_Model FM) {

	for (PropositionalFormula formula : featureModel.getConstraints()) {

		SpecialCCT myCCT = new SpecialCCT();
		LinkedList<String> IDtable = new LinkedList<String>();
		LinkedList<Boolean> NegTable = new LinkedList<Boolean>();
		String str = formula.getFormula();
		String[] tab = str.split(" or ");

		for (int i = 0; i < tab.length; i++) {
			if (tab[i].contains("~")) {
				NegTable.add(true);

				String tmp = tab[i].replace("~", "");
				String tmp2 = tmp.replace("(", "");
				String ID1 = tmp2.replace(")", "");
				myCCT.addNegNode(FM.getNodeFromID(ID1));
				//IDtable.add(ID1);
			} else {
				NegTable.add(false);
				String tmp = tab[i].replace("~", "");
				String tmp2 = tmp.replace("(", "");
				String ID1 = tmp2.replace(")", "");
				myCCT.addPosNode(FM.getNodeFromID(ID1));

			}

		}

		Fm.getCtrList().add(myCCT);
	}
}


}
