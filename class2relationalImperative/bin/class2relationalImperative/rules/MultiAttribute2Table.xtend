package class2relationalImperative.rules

import org.eclipse.emf.ecore.resource.Resource
import atl.research.class_.Attribute
import atl.research.relational_.Table
import atl.research.class_.DataType

class MultiAttribute2Table extends Elem2Elem {
	
	new(Resource src, Resource trgt, Resource corr) {
		super(src, trgt, corr)
	}
	
	override sourceToTarget() {
		sourceModel.allContents.filter(typeof(Attribute)).filter[att | att.isMultiValued].forEach[ attribute |
			val corr = attribute.getOrCreateCorrModelElement("MultiAttribute2Table")
			val targetTable = corr.getOrCreateTargetElem(targetPackage.table) as Table
			val idCol = targetFactory.createColumn() => [
				type = DataType2Type.getType(sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"])
			]
			var owner  = attribute.eContainer as atl.research.class_.Class
			if (attribute.type instanceof DataType) {				
				targetTable.name = (owner !== null && owner.name !== "")? "Table" : owner.name + "_" + attribute.name

				idCol.name = (owner === null)? "Default" : owner.name.toFirstLower + "Id"
				
				val valCol = targetFactory.createColumn() => [name = attribute.name
					type = DataType2Type.getType(attribute.type as DataType)
				]
				targetTable.col += idCol				
				targetTable.col += valCol
			}
			else {
				targetTable.name = owner === null? "Table" : owner.name + "_" + attribute.name 
				// create primary and foreign key
				idCol.name = owner === null? "Default" : owner.name.toFirstLower + "Id"
				val fkCol = targetFactory.createColumn() => [ name = attribute.name.toFirstLower + "Id"
					type = DataType2Type.getType(sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"])
				]
				targetTable.col += idCol
				targetTable.col += fkCol
			}
			targetModel.contents += targetTable
		]
	}
	
}