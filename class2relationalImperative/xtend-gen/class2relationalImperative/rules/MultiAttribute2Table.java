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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class MultiAttribute2Table extends Elem2Elem {
  public MultiAttribute2Table(final Resource src, final Resource trgt, final Resource corr) {
    super(src, trgt, corr);
  }

  @Override
  public void sourceToTarget() {
    final Function1<Attribute, Boolean> _function = (Attribute att) -> {
      return Boolean.valueOf(att.isMultiValued());
    };
    final Procedure1<Attribute> _function_1 = (Attribute attribute) -> {
      final Corr corr = this.getOrCreateCorrModelElement(attribute, "MultiAttribute2Table");
      EObject _orCreateTargetElem = this.getOrCreateTargetElem(corr, this.targetPackage.getTable());
      final Table targetTable = ((Table) _orCreateTargetElem);
      Column _createColumn = this.targetFactory.createColumn();
      final Procedure1<Column> _function_2 = (Column it) -> {
        final Function1<DataType, Boolean> _function_3 = (DataType it_1) -> {
          String _name = it_1.getName();
          return Boolean.valueOf(Objects.equal(_name, "Integer"));
        };
        it.setType(DataType2Type.getType(IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function_3)));
      };
      final Column idCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn, _function_2);
      EObject _eContainer = attribute.eContainer();
      atl.research.class_.Class owner = ((atl.research.class_.Class) _eContainer);
      Classifier _type = attribute.getType();
      if ((_type instanceof DataType)) {
        String _xifexpression = null;
        if (((owner != null) && (owner.getName() != ""))) {
          _xifexpression = "Table";
        } else {
          String _name = owner.getName();
          String _plus = (_name + "_");
          String _name_1 = attribute.getName();
          _xifexpression = (_plus + _name_1);
        }
        targetTable.setName(_xifexpression);
        String _xifexpression_1 = null;
        if ((owner == null)) {
          _xifexpression_1 = "Default";
        } else {
          String _firstLower = StringExtensions.toFirstLower(owner.getName());
          _xifexpression_1 = (_firstLower + "Id");
        }
        idCol.setName(_xifexpression_1);
        Column _createColumn_1 = this.targetFactory.createColumn();
        final Procedure1<Column> _function_3 = (Column it) -> {
          it.setName(attribute.getName());
          Classifier _type_1 = attribute.getType();
          it.setType(DataType2Type.getType(((DataType) _type_1)));
        };
        final Column valCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn_1, _function_3);
        EList<Column> _col = targetTable.getCol();
        _col.add(idCol);
        EList<Column> _col_1 = targetTable.getCol();
        _col_1.add(valCol);
      } else {
        String _xifexpression_2 = null;
        if ((owner == null)) {
          _xifexpression_2 = "Table";
        } else {
          String _name_2 = owner.getName();
          String _plus_1 = (_name_2 + "_");
          String _name_3 = attribute.getName();
          _xifexpression_2 = (_plus_1 + _name_3);
        }
        targetTable.setName(_xifexpression_2);
        String _xifexpression_3 = null;
        if ((owner == null)) {
          _xifexpression_3 = "Default";
        } else {
          String _firstLower_1 = StringExtensions.toFirstLower(owner.getName());
          _xifexpression_3 = (_firstLower_1 + "Id");
        }
        idCol.setName(_xifexpression_3);
        Column _createColumn_2 = this.targetFactory.createColumn();
        final Procedure1<Column> _function_4 = (Column it) -> {
          String _firstLower_2 = StringExtensions.toFirstLower(attribute.getName());
          String _plus_2 = (_firstLower_2 + "Id");
          it.setName(_plus_2);
          final Function1<DataType, Boolean> _function_5 = (DataType it_1) -> {
            String _name_4 = it_1.getName();
            return Boolean.valueOf(Objects.equal(_name_4, "Integer"));
          };
          it.setType(DataType2Type.getType(IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function_5)));
        };
        final Column fkCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn_2, _function_4);
        EList<Column> _col_2 = targetTable.getCol();
        _col_2.add(idCol);
        EList<Column> _col_3 = targetTable.getCol();
        _col_3.add(fkCol);
      }
      EList<EObject> _contents = this.targetModel.getContents();
      _contents.add(targetTable);
    };
    IteratorExtensions.<Attribute>forEach(IteratorExtensions.<Attribute>filter(Iterators.<Attribute>filter(this.sourceModel.getAllContents(), Attribute.class), _function), _function_1);
  }
}
