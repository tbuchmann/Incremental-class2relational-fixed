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
	
	// Model Traversal 5
	override protected filterAtt(Attribute att) {	
		// Model Traversal 6
		(att.isMultiValued) && !(att.type instanceof Class)
	}
	
	// Transformation 7
	override protected tblNameFrom(String attName, Class owner) {
		// Transformation 20
		var tblName = (owner !== null && owner.name !== null && owner.name !== "")? owner.name : "Table" 
		//if (owner !== null && (owner.name !== null || owner.name !== "")) tblName = owner.name		
		new Type4tblName(tblName + "_" + attName)
	}
	
	// Transformation 9
	override protected colFrom(String attName, Classifier type, Class owner) {
		// Transformation 48
		val colList = newArrayList
		val columnName = (owner === null || owner.name === null || owner.name === "")? "tableId" : owner.name.toFirstLower + "Id"//"table"
		//if (owner !== null && (owner.name !== null || owner.name !== "")) columnName = owner.name.toFirstLower
		//val colName = columnName
		val idCol = Relational_Factory.eINSTANCE.createColumn() => [name = columnName
			type = Utils.getType(findIntegerDatatype())
		]
		val valCol = Relational_Factory.eINSTANCE.createColumn() => [name = attName
			type = Utils.getType(type)
		]
		
		colList += idCol
		colList += valCol
		
		return new Type4col(colList)
	}
	
	// Helper 13
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
	
}
