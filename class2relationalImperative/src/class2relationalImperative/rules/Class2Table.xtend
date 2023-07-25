package class2relationalImperative.rules

import org.eclipse.emf.ecore.resource.Resource

import atl.research.class_.Class
import atl.research.relational_.Table
import atl.research.class_.Attribute
import atl.research.relational_.Column
import atl.research.class_.DataType

class Class2Table extends Elem2Elem {
	
	new(Resource src, Resource trgt, Resource corr) {
		super(src, trgt, corr)
	}
	
	override sourceToTarget() {
		sourceModel.contents.filter(typeof(Class)).forEach[ clz |
			val corr = clz.getOrCreateCorrModelElement("Class2Table")
			val targetTable = corr.getOrCreateTargetElem(targetPackage.table) as Table
			val colId = targetFactory.createColumn => [
				name = "objectID"
				type = DataType2Type.getType(sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"])
			]
			targetTable.name = clz.name
			targetTable.col += colId
			for (Attribute a : clz.attr) {
				if (!a.isMultiValued) {
					targetTable.col += elementsToCorr.get(a).targetElement as Column
				}
			}
			targetModel.contents += targetTable
		]
	}
}