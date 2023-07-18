package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import atl.research.class_.Attribute
import atl.research.class_.Class
import atl.research.class_.Classifier
import atl.research.relational_.Relational_Factory
import atl.research.class_.DataType

class MultiAttribute2TableImpl extends MultiAttribute2Table {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	override protected filterAtt(Attribute att) {
		// Model Traversal
		(att.isMultiValued) && !(att.type instanceof Class)
	}
	
	override protected tblNameFrom(String attName, Class owner) {
		var tblName = owner.name
		if (tblName === null || tblName === "") tblName = "Table"
		new Type4tblName(owner.name + "_" + attName)
	}
	
	override protected colFrom(String attName, Classifier type, Class owner) {
		val colList = newArrayList
		val idCol = Relational_Factory.eINSTANCE.createColumn() => [name = owner.name.toFirstLower + "Id"
			type = Utils.getType(findIntegerDatatype())
		]
		val valCol = Relational_Factory.eINSTANCE.createColumn() => [name = attName
			type = Utils.getType(type)
		]
		
		colList += idCol
		colList += valCol
		
		return new Type4col(colList)
	}
	
		def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
	
}
