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
import org.eclipse.emf.ecore.util.EcoreUtil;
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
      final EObject targetObject = this.getOrCreateTargetElem(corr, this.targetPackage.getTable());
      if ((targetObject instanceof Column)) {
        EcoreUtil.delete(targetObject);
      }
      EObject _orCreateTargetElem = this.getOrCreateTargetElem(corr, this.targetPackage.getTable());
      Table targetTable = ((Table) _orCreateTargetElem);
      EObject _eContainer = attribute.eContainer();
      atl.research.class_.Class owner = ((atl.research.class_.Class) _eContainer);
      String _xifexpression = null;
      if ((((owner != null) && (owner.getName() != null)) && (owner.getName() != ""))) {
        _xifexpression = owner.getName();
      } else {
        _xifexpression = "Table";
      }
      targetTable.setName(_xifexpression);
      String _name = targetTable.getName();
      String _plus = (_name + "_");
      String _name_1 = attribute.getName();
      String _plus_1 = (_plus + _name_1);
      targetTable.setName(_plus_1);
      Column _createColumn = this.targetFactory.createColumn();
      final Procedure1<Column> _function_2 = (Column it) -> {
        final Function1<DataType, Boolean> _function_3 = (DataType it_1) -> {
          String _name_2 = it_1.getName();
          return Boolean.valueOf(Objects.equal(_name_2, "Integer"));
        };
        it.setType(DataType2Type.getType(IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function_3)));
      };
      Column idCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn, _function_2);
      String _xifexpression_1 = null;
      if ((((owner == null) || (owner.getName() == null)) || (owner.getName() == ""))) {
        _xifexpression_1 = "tableId";
      } else {
        String _firstLower = StringExtensions.toFirstLower(owner.getName());
        _xifexpression_1 = (_firstLower + "Id");
      }
      idCol.setName(_xifexpression_1);
      Classifier _type = attribute.getType();
      if ((_type instanceof DataType)) {
        int _size = targetTable.getCol().size();
        boolean _equals = (_size == 0);
        if (_equals) {
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
          idCol = targetTable.getCol().get(0);
          String _xifexpression_2 = null;
          if ((((owner == null) || (owner.getName() == null)) || (owner.getName() == ""))) {
            _xifexpression_2 = "tableId";
          } else {
            String _firstLower_1 = StringExtensions.toFirstLower(owner.getName());
            _xifexpression_2 = (_firstLower_1 + "Id");
          }
          idCol.setName(_xifexpression_2);
        }
      } else {
        int _size_1 = targetTable.getCol().size();
        boolean _equals_1 = (_size_1 == 0);
        if (_equals_1) {
          Column _createColumn_2 = this.targetFactory.createColumn();
          final Procedure1<Column> _function_4 = (Column it) -> {
            String _firstLower_2 = StringExtensions.toFirstLower(attribute.getName());
            String _plus_2 = (_firstLower_2 + "Id");
            it.setName(_plus_2);
            final Function1<DataType, Boolean> _function_5 = (DataType it_1) -> {
              String _name_2 = it_1.getName();
              return Boolean.valueOf(Objects.equal(_name_2, "Integer"));
            };
            it.setType(DataType2Type.getType(IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function_5)));
          };
          final Column fkCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn_2, _function_4);
          EList<Column> _col_2 = targetTable.getCol();
          _col_2.add(idCol);
          EList<Column> _col_3 = targetTable.getCol();
          _col_3.add(fkCol);
        } else {
          idCol = targetTable.getCol().get(0);
          String _xifexpression_3 = null;
          if ((((owner == null) || (owner.getName() == null)) || (owner.getName() == ""))) {
            _xifexpression_3 = "tableId";
          } else {
            String _firstLower_2 = StringExtensions.toFirstLower(owner.getName());
            _xifexpression_3 = (_firstLower_2 + "Id");
          }
          idCol.setName(_xifexpression_3);
        }
      }
      EList<EObject> _contents = this.targetModel.getContents();
      _contents.add(targetTable);
    };
    IteratorExtensions.<Attribute>forEach(IteratorExtensions.<Attribute>filter(Iterators.<Attribute>filter(this.sourceModel.getAllContents(), Attribute.class), _function), _function_1);
  }
}
