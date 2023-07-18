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
	
	// Model Traversal
	override protected filterAtt(Attribute att) {	
		(att.isMultiValued) && !(att.type instanceof Class)
	}
	
	// Transformation
	override protected tblNameFrom(String attName, Class owner) {
		var tblName = "Table"
		if (owner !== null) tblName = owner.name
		if (tblName === null || tblName === "") tblName = "Table"
		new Type4tblName(tblName + "_" + attName)
	}
	
	// Transformation
	override protected colFrom(String attName, Classifier type, Class owner) {
		val colList = newArrayList
		var columnName = "Default"
		if (owner !== null) columnName = owner.name.toFirstLower
		val colName = columnName
		val idCol = Relational_Factory.eINSTANCE.createColumn() => [name = colName + "Id"
			type = Utils.getType(findIntegerDatatype())
		]
		val valCol = Relational_Factory.eINSTANCE.createColumn() => [name = attName
			type = Utils.getType(type)
		]
		
		colList += idCol
		colList += valCol
		
		return new Type4col(colList)
	}
	
	// Helper
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
	
}
