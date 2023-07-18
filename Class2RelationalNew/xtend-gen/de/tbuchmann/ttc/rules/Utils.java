package de.tbuchmann.ttc.rules;

import atl.research.class_.Classifier;
import atl.research.relational_.Type;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import org.eclipse.emf.ecore.EObject;

@SuppressWarnings("all")
public class Utils {
  public static Type getType(final Classifier type) {
    Type _xblockexpression = null;
    {
      Corr corr = Elem2Elem.getCorrMap().get(type);
      if ((corr == null)) {
        return null;
      }
      CorrElem _get = corr.getTarget().get(0);
      SingleElem targetCorr = ((SingleElem) _get);
      EObject _element = targetCorr.getElement();
      _xblockexpression = ((Type) _element);
    }
    return _xblockexpression;
  }
}
