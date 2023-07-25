package class2relationalImperative.rules;

import atl.research.class_.DataType;
import atl.research.relational_.Type;
import class2relationalImperative.correspondence.class2relationalImperative.Corr;
import com.google.common.collect.Iterators;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class DataType2Type extends Elem2Elem {
  private static Map<DataType, Type> typeMap = new HashMap<DataType, Type>();

  public DataType2Type(final Resource src, final Resource trgt, final Resource corr) {
    super(src, trgt, corr);
  }

  @Override
  public void sourceToTarget() {
    final Procedure1<DataType> _function = (DataType datatype) -> {
      final Corr corrDT = this.getOrCreateCorrModelElement(datatype, "DataType2Type");
      EObject _orCreateTargetElem = this.getOrCreateTargetElem(corrDT, this.targetPackage.getType());
      final Procedure1<Type> _function_1 = (Type it) -> {
        it.setName(datatype.getName());
      };
      final Type targetElem = ObjectExtensions.<Type>operator_doubleArrow(((Type) _orCreateTargetElem), _function_1);
      EList<EObject> _contents = this.targetModel.getContents();
      _contents.add(targetElem);
      DataType2Type.typeMap.put(datatype, targetElem);
    };
    IteratorExtensions.<DataType>forEach(Iterators.<DataType>filter(this.sourceModel.getAllContents(), DataType.class), _function);
  }

  public static Type getType(final DataType t) {
    return DataType2Type.typeMap.get(t);
  }
}
