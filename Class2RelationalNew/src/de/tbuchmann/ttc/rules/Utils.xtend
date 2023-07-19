package de.tbuchmann.ttc.rules

import atl.research.class_.Classifier
import de.tbuchmann.ttc.corrmodel.SingleElem
import atl.research.relational_.Type

// Helper/Utility class
class Utils {
	// Helper 16
	def static getType(Classifier type) {
		var corr = Elem2Elem.corrMap.get(type)
		if (corr === null) return null;
		// Model Traversal 7
		var targetCorr = corr.target.get(0) as SingleElem
		// Helper 4
		targetCorr.element as Type
	}

}