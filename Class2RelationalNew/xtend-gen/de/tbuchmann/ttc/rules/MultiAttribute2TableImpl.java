package de.tbuchmann.ttc.rules;

import atl.research.class_.Attribute;
import atl.research.class_.Classifier;
import atl.research.class_.DataType;
import atl.research.relational_.Column;
import atl.research.relational_.Relational_Factory;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.tbuchmann.ttc.trafo.Class2Relational;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class MultiAttribute2TableImpl extends MultiAttribute2Table {
  public MultiAttribute2TableImpl(final Class2Relational trafo) {
    super(trafo);
  }

  @Override
  protected boolean filterAtt(final Attribute att) {
    return (att.isMultiValued() && (!(att.getType() instanceof atl.research.class_.Class)));
  }

  @Override
  protected MultiAttribute2Table.Type4tblName tblNameFrom(final String attName, final atl.research.class_.Class owner) {
    MultiAttribute2Table.Type4tblName _xblockexpression = null;
    {
      String _xifexpression = null;
      if ((((owner != null) && (owner.getName() != null)) && (owner.getName() != ""))) {
        _xifexpression = owner.getName();
      } else {
        _xifexpression = "Table";
      }
      String tblName = _xifexpression;
      _xblockexpression = new MultiAttribute2Table.Type4tblName(((tblName + "_") + attName));
    }
    return _xblockexpression;
  }

  @Override
  protected MultiAttribute2Table.Type4col colFrom(final String attName, final Classifier type, final atl.research.class_.Class owner) {
    final ArrayList<Column> colList = CollectionLiterals.<Column>newArrayList();
    String _xifexpression = null;
    if ((((owner == null) || (owner.getName() == null)) || (owner.getName() == ""))) {
      _xifexpression = "tableId";
    } else {
      String _firstLower = StringExtensions.toFirstLower(owner.getName());
      _xifexpression = (_firstLower + "Id");
    }
    final String columnName = _xifexpression;
    Column _createColumn = Relational_Factory.eINSTANCE.createColumn();
    final Procedure1<Column> _function = (Column it) -> {
      it.setName(columnName);
      it.setType(Utils.getType(this.findIntegerDatatype()));
    };
    final Column idCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn, _function);
    Column _createColumn_1 = Relational_Factory.eINSTANCE.createColumn();
    final Procedure1<Column> _function_1 = (Column it) -> {
      it.setName(attName);
      it.setType(Utils.getType(type));
    };
    final Column valCol = ObjectExtensions.<Column>operator_doubleArrow(_createColumn_1, _function_1);
    colList.add(idCol);
    colList.add(valCol);
    return new MultiAttribute2Table.Type4col(colList);
  }

  public DataType findIntegerDatatype() {
    DataType _xblockexpression = null;
    {
      final Function1<DataType, Boolean> _function = (DataType it) -> {
        String _name = it.getName();
        return Boolean.valueOf(Objects.equal(_name, "Integer"));
      };
      final DataType datatype = IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function);
      _xblockexpression = datatype;
    }
    return _xblockexpression;
  }
}
