package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import atl.research.relational_.Column
import atl.research.class_.Attribute
import atl.research.class_.Class
import atl.research.class_.Classifier
import atl.research.class_.DataType

class MultiClassAttribute2ColumnImpl extends MultiClassAttribute2Column {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	// Transformation 16
	override protected onIdCreation(Column id) {
		id.type = Utils.getType(findIntegerDatatype())
		id.corr.target().t.col += id
	}
	
	// Transformation 16
	override protected onFkCreation(Column fk) {
		fk.type = Utils.getType(findIntegerDatatype())
		fk.corr.target().t.col += fk
	}
	
	// Model Traversal 11
	override protected filterAtt(Attribute att) {		
		(att.isMultiValued) && (att.type instanceof Class)
	}
	
	// Transformation 25
	override protected tNameFrom(String attName, Class owner, Classifier attType) {
		var name = ""
		if (owner === null)
			name = "Table"
		else
			name = owner.name
		new Type4tName(name + "_" + attName)
	}
	
	// Transformation 21
	override protected idNameFrom(String attName, Class attOwner) {
		var name = "Default"
		if (attOwner !== null) name = attOwner.name
		new Type4idName(name.toFirstLower + "Id")
	}
	
	// Transformation 11
	override protected fkNameFrom(String attName, Class attOwner) {
		new Type4fkName(attName + "Id")
	}
	
	// Helper3
	def findIntegerDatatype() {
		// Model Traversal
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}	
	
}
