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


package fr.pacogen.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;



public abstract class Model_Operator {

	
	protected Node Father ;
	protected LinkedList<Node> nodeVect ;
	
	public abstract String getType() ;
	
	public Model_Operator(Node _Father, LinkedList<Node> _nodeVect)
	{
		Father = _Father ;
		nodeVect  = _nodeVect ;
	}
	public Model_Operator() {
		nodeVect = new LinkedList<Node>() ;
		Node Father = new Node() ;
	}

	public Node getFather() {
		return Father;
	}
	
	public void setFather(Node father) {
		Father = father;
	}
	
	
	public LinkedList<Node> getNodeVect() {
		return nodeVect;
	}

	public void setNodeVect(LinkedList<Node> nodeVect) {
		this.nodeVect = nodeVect;
	}

	public String toProlog()
	{
		String res ="" ;
		res += getType() + "(" + Father.getPrologName() + ",[" ;
		Iterator<Node> itNode = nodeVect.iterator() ;
		while(itNode.hasNext())
		{
			res += itNode.next().getPrologName() ;
			if (itNode.hasNext())
			{
				res +="," ;
			}
		}
	res += "])" ;	
	
		return res ;
	}
	

	

 
}
