package de.tbuchmann.ttc.rules;

import atl.research.class_.Attribute;
import atl.research.class_.Classifier;
import atl.research.class_.DataType;
import atl.research.relational_.Column;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.tbuchmann.ttc.trafo.Class2Relational;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class MultiClassAttribute2ColumnImpl extends MultiClassAttribute2Column {
  public MultiClassAttribute2ColumnImpl(final Class2Relational trafo) {
    super(trafo);
  }

  @Override
  protected void onIdCreation(final Column id) {
    id.setType(Utils.getType(this.findIntegerDatatype()));
    EList<Column> _col = MultiClassAttribute2Column.target(this.getCorr(id)).getT().getCol();
    _col.add(id);
  }

  @Override
  protected void onFkCreation(final Column fk) {
    fk.setType(Utils.getType(this.findIntegerDatatype()));
    EList<Column> _col = MultiClassAttribute2Column.target(this.getCorr(fk)).getT().getCol();
    _col.add(fk);
  }

  @Override
  protected boolean filterAtt(final Attribute att) {
    return (att.isMultiValued() && (att.getType() instanceof atl.research.class_.Class));
  }

  @Override
  protected MultiClassAttribute2Column.Type4tName tNameFrom(final String attName, final atl.research.class_.Class owner, final Classifier attType) {
    MultiClassAttribute2Column.Type4tName _xblockexpression = null;
    {
      String _xifexpression = null;
      if ((((owner != null) && (owner.getName() != null)) && (owner.getName() != ""))) {
        _xifexpression = owner.getName();
      } else {
        _xifexpression = "Table";
      }
      String tblName = _xifexpression;
      _xblockexpression = new MultiClassAttribute2Column.Type4tName(((tblName + "_") + attName));
    }
    return _xblockexpression;
  }

  @Override
  protected MultiClassAttribute2Column.Type4idName idNameFrom(final String attName, final atl.research.class_.Class attOwner) {
    MultiClassAttribute2Column.Type4idName _xblockexpression = null;
    {
      String _xifexpression = null;
      if ((((attOwner == null) || (attOwner.getName() == null)) || (attOwner.getName() == ""))) {
        _xifexpression = "tableId";
      } else {
        String _firstLower = StringExtensions.toFirstLower(attOwner.getName());
        _xifexpression = (_firstLower + "Id");
      }
      String name = _xifexpression;
      _xblockexpression = new MultiClassAttribute2Column.Type4idName(name);
    }
    return _xblockexpression;
  }

  @Override
  protected MultiClassAttribute2Column.Type4fkName fkNameFrom(final String attName, final atl.research.class_.Class attOwner) {
    return new MultiClassAttribute2Column.Type4fkName((attName + "Id"));
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
