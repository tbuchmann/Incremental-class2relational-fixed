package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import atl.research.class_.Attribute
import atl.research.class_.Classifier
import atl.research.class_.DataType

class SingleClassAttribute2ColumnImpl extends SingleClassAttribute2Column {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	// Model Traversal
	override protected filterAtt(Attribute att) {		
		!(att.isMultiValued) && !(att.type instanceof DataType) 
	}
	
	// Transformation
	override protected colNameFrom(String attName, Classifier attType) {
		new Type4colName(attName + "Id")
	}
	
	// Transformation
	override protected colTypeFrom(String attName, Classifier attType) {
		new Type4colType(Utils.getType(findIntegerDatatype()))
	}
	
	// Helper
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
	
}
