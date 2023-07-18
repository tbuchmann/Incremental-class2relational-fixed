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
	
	override protected onIdCreation(Column id) {
		id.type = Utils.getType(findIntegerDatatype())
		id.corr.target().t.col += id
	}
	
	override protected onFkCreation(Column fk) {
		fk.type = Utils.getType(findIntegerDatatype())
		fk.corr.target().t.col += fk
	}
	
	override protected filterAtt(Attribute att) {
		// Model Traversal
		(att.isMultiValued) && (att.type instanceof Class)
	}
	
	override protected tNameFrom(String attName, Class owner, Classifier attType) {
		var name = ""
		if (owner == null)
			name = "Table"
		else
			name = owner.name
		new Type4tName(name + "_" + attName)
	}
	
	override protected idNameFrom(String attName, Class attOwner) {
		var name = "Default"
		if (attOwner !== null) name = attOwner.name
		new Type4idName(name.toFirstLower + "Id")
	}
	
	override protected fkNameFrom(String attName, Class attOwner) {
		new Type4fkName(attName + "Id")
	}
	
	def findIntegerDatatype() {
		// Model Traversal
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}	
	
}
