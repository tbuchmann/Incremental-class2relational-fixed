package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import atl.research.class_.Attribute
import atl.research.class_.Classifier
import atl.research.class_.DataType

class SingleClassAttribute2ColumnImpl extends SingleClassAttribute2Column {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	// Model Traversal 11
	override protected filterAtt(Attribute att) {		
		!(att.isMultiValued) && !(att.type instanceof DataType)// && (att.type !== null) does not work, because this 
		// would result in attributes without a correspondence element and consequently the transformation
		// will yield an exception in Class2Table when attribute.getCorr is called on EACH attribute
	}
	
	// Transformation 11
	override protected colNameFrom(String attName, Classifier attType) {
		new Type4colName(attName + "Id")
	}
	
	// Transformation 12
	override protected colTypeFrom(String attName, Classifier attType) {
		new Type4colType(Utils.getType(findIntegerDatatype()))
	}
	
	// Helper 13
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
	
}
