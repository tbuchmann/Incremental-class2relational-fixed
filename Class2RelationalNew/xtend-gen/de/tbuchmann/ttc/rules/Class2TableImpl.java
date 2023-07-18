package de.tbuchmann.ttc.rules;

import atl.research.class_.Attribute;
import atl.research.class_.Classifier;
import atl.research.class_.DataType;
import atl.research.relational_.Column;
import atl.research.relational_.Relational_Factory;
import atl.research.relational_.Table;
import atl.research.relational_.Type;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Class2Relational;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Class2TableImpl extends Class2Table {
  public Class2TableImpl(final Class2Relational trafo) {
    super(trafo);
  }

  @Override
  protected void onTblCreation(final Table tbl) {
    Column _createColumn = Relational_Factory.eINSTANCE.createColumn();
    final Procedure1<Column> _function = (Column it) -> {
      it.setName("objectId");
    };
    Column key = ObjectExtensions.<Column>operator_doubleArrow(_createColumn, _function);
    key.setType(Utils.getType(this.findIntegerDatatype()));
    tbl.getCol().add(0, key);
    EList<Column> _key = tbl.getKey();
    _key.add(key);
  }

  @Override
  protected Class2Table.Type4col colFrom(final List<Column> attSinCol, final List<Column> attSinCol_2, final List<Table> attMulTbl, final Table parent) {
    Class2Table.Type4col _xblockexpression = null;
    {
      final ArrayList<Column> columnsList = CollectionLiterals.<Column>newArrayList();
      boolean _isEmpty = parent.getCol().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        Column key = parent.getCol().get(0);
        columnsList.add(key);
      }
      for (final Column c : attSinCol) {
        {
          CorrElem _get = this.getCorr(c).getSource().get(0);
          Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
          Attribute obj = ((Attribute) _unwrap);
          Classifier _type = obj.getType();
          boolean _tripleNotEquals = (_type != null);
          if (_tripleNotEquals) {
            columnsList.add(c);
          } else {
            c.setOwner(null);
            EcoreUtil.delete(c, true);
          }
        }
      }
      for (final Column c_1 : attSinCol_2) {
        {
          CorrElem _get = this.getCorr(c_1).getSource().get(0);
          Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
          Attribute obj = ((Attribute) _unwrap);
          Classifier _type = obj.getType();
          boolean _tripleNotEquals = (_type != null);
          if (_tripleNotEquals) {
            columnsList.add(c_1);
          } else {
            EcoreUtil.delete(c_1, true);
          }
        }
      }
      for (final Table t : attMulTbl) {
        {
          CorrElem _get = this.getCorr(t).getSource().get(0);
          Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
          Attribute obj = ((Attribute) _unwrap);
          Classifier _type = obj.getType();
          boolean _tripleEquals = (_type == null);
          if (_tripleEquals) {
            EcoreUtil.delete(t, true);
          }
        }
      }
      _xblockexpression = new Class2Table.Type4col(columnsList);
    }
    return _xblockexpression;
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

  public void removeNullTypeColumns(final List<Column> cols) {
    for (final Column c : cols) {
      {
        Type _type = c.getType();
        boolean _tripleEquals = (_type == null);
        if (_tripleEquals) {
          EcoreUtil.delete(c, true);
        }
        CorrElem _get = this.getCorr(c).getSource().get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        Attribute obj = ((Attribute) _unwrap);
        Classifier _type_1 = obj.getType();
        boolean _tripleEquals_1 = (_type_1 == null);
        if (_tripleEquals_1) {
          c.setOwner(null);
          EcoreUtil.delete(c, true);
        }
      }
    }
  }
}
