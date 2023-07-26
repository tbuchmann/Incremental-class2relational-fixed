package class2relationalImperative.rules;

import atl.research.class_.Attribute;
import atl.research.class_.Classifier;
import atl.research.class_.DataType;
import atl.research.relational_.Column;
import atl.research.relational_.Table;
import class2relationalImperative.correspondence.class2relationalImperative.Corr;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class SingleAttribute2Column extends Elem2Elem {
  public SingleAttribute2Column(final Resource src, final Resource trgt, final Resource corr) {
    super(src, trgt, corr);
  }

  @Override
  public void sourceToTarget() {
    final Function1<Attribute, Boolean> _function = (Attribute att) -> {
      boolean _isMultiValued = att.isMultiValued();
      return Boolean.valueOf((!_isMultiValued));
    };
    final Procedure1<Attribute> _function_1 = (Attribute attribute) -> {
      final Corr corr = this.getOrCreateCorrModelElement(attribute, "SingleAttribute2Column");
      EObject targetObj = this.getOrCreateTargetElem(corr, this.targetPackage.getColumn());
      if ((targetObj instanceof Table)) {
        EcoreUtil.delete(targetObj);
      }
      EObject _orCreateTargetElem = this.getOrCreateTargetElem(corr, this.targetPackage.getColumn());
      final Column target = ((Column) _orCreateTargetElem);
      Classifier _type = attribute.getType();
      if ((_type instanceof DataType)) {
        target.setName(attribute.getName());
        Classifier _type_1 = attribute.getType();
        target.setType(DataType2Type.getType(((DataType) _type_1)));
      } else {
        String _name = attribute.getName();
        String _plus = (_name + "Id");
        target.setName(_plus);
        final Function1<DataType, Boolean> _function_2 = (DataType it) -> {
          String _name_1 = it.getName();
          return Boolean.valueOf(Objects.equal(_name_1, "Integer"));
        };
        target.setType(DataType2Type.getType(IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function_2)));
      }
    };
    IteratorExtensions.<Attribute>forEach(IteratorExtensions.<Attribute>filter(Iterators.<Attribute>filter(this.sourceModel.getAllContents(), Attribute.class), _function), _function_1);
  }
}
