package de.tbuchmann.ttc.rules;

import atl.research.class_.Attribute
import atl.research.class_.DataType
import atl.research.relational_.Column
import atl.research.relational_.Table
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.trafo.Class2Relational
import java.util.List
import org.eclipse.emf.ecore.util.EcoreUtil
import atl.research.relational_.Relational_Factory

class Class2TableImpl extends Class2Table {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	// Transformation 23
	override protected onTblCreation(Table tbl) {
		var key = Relational_Factory.eINSTANCE.createColumn => [name = "objectId"]
		key.type = Utils.getType(findIntegerDatatype())
		tbl.col.add(0, key)
		tbl.key += key
	}
	
	// Transformation 14
	override protected colFrom(List<Column> attSinCol, List<Column> attSinCol_2, List<Table> attMulTbl,
		Table parent
	) {
		// Helper 3
		val columnsList = newArrayList
		// Helper 10
		if (!parent.col.empty) {
			var key = parent.col.get(0)
			columnsList += key	
		}

		// Transformation 4		
		for (Column c : attSinCol) {
			// Tracing 11
			var obj = unwrap(c.corr.source.get(0) as SingleElem) as Attribute
			// Transformation 14
			if (obj.type !== null) {
				columnsList += c	
			}
			else {
				c.owner = null
				EcoreUtil.delete(c, true)
			}
		}

		// Transformation 4
		for (Column c : attSinCol_2) {
			// Tracing 11
			var obj = unwrap(c.corr.source.get(0) as SingleElem) as Attribute
			// Transformation 14
			if (obj.type !== null) {
				columnsList += c	
			}
			else {
				EcoreUtil.delete(c, true)
			}
		}
		// delete Tables that are created from attributes with null-Type
		// Transformation 4
		for (Table t : attMulTbl) {
			// Tracing 11
			var obj = unwrap(t.corr.source.get(0) as SingleElem) as Attribute
			// Transformation 8
			if (obj.type === null)
				EcoreUtil.delete(t, true);
		}	
		// Transformation 3	
		new Type4col(columnsList)
	}
	
	// Helper 13
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}	
	
	// Helper 5
	def removeNullTypeColumns(List<Column> cols) {
		// Helper 12
		for (Column c : cols) {
			if (c.type === null) 
				EcoreUtil.delete(c, true);
			// check if corresponding attribute has null type
			// Tracing 11
			var obj = unwrap(c.corr.source.get(0) as SingleElem) as Attribute
			// Helper 11
			if (obj.type === null) {
				c.owner = null;
				EcoreUtil.delete(c, true);	
			}
		}
	}
	
}
