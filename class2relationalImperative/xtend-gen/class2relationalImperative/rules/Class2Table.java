package class2relationalImperative.rules;

import atl.research.class_.Attribute;
import atl.research.class_.DataType;
import atl.research.relational_.Column;
import atl.research.relational_.Table;
import class2relationalImperative.correspondence.class2relationalImperative.Corr;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Class2Table extends Elem2Elem {
  public Class2Table(final Resource src, final Resource trgt, final Resource corr) {
    super(src, trgt, corr);
  }

  @Override
  public void sourceToTarget() {
    final Consumer<atl.research.class_.Class> _function = (atl.research.class_.Class clz) -> {
      final Corr corr = this.getOrCreateCorrModelElement(clz, "Class2Table");
      EObject _orCreateTargetElem = this.getOrCreateTargetElem(corr, this.targetPackage.getTable());
      final Table targetTable = ((Table) _orCreateTargetElem);
      targetTable.setName(clz.getName());
      int _size = targetTable.getCol().size();
      boolean _equals = (_size == 0);
      if (_equals) {
        Column _createColumn = this.targetFactory.createColumn();
        final Procedure1<Column> _function_1 = (Column it) -> {
          it.setName("objectId");
          final Function1<DataType, Boolean> _function_2 = (DataType it_1) -> {
            String _name = it_1.getName();
            return Boolean.valueOf(Objects.equal(_name, "Integer"));
          };
          it.setType(DataType2Type.getType(IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function_2)));
        };
        final Column colId = ObjectExtensions.<Column>operator_doubleArrow(_createColumn, _function_1);
        EList<Column> _col = targetTable.getCol();
        _col.add(colId);
        EList<Column> _key = targetTable.getKey();
        _key.add(colId);
      }
      EList<Attribute> _attr = clz.getAttr();
      for (final Attribute a : _attr) {
        boolean _isMultiValued = a.isMultiValued();
        boolean _not = (!_isMultiValued);
        if (_not) {
          EList<Column> _col_1 = targetTable.getCol();
          EObject _targetElement = Elem2Elem.elementsToCorr.get(a).getTargetElement();
          _col_1.add(((Column) _targetElement));
        }
      }
      EList<EObject> _contents = this.targetModel.getContents();
      _contents.add(targetTable);
    };
    Iterables.<atl.research.class_.Class>filter(this.sourceModel.getContents(), atl.research.class_.Class.class).forEach(_function);
  }
}
