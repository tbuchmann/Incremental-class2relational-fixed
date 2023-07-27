package de.tbuchmann.ttc.rules;

import atl.research.class_.Attribute;
import de.tbuchmann.ttc.trafo.Class2Relational;

@SuppressWarnings("all")
public class SingleAttribute2ColumnImpl extends SingleAttribute2Column {
  public SingleAttribute2ColumnImpl(final Class2Relational trafo) {
    super(trafo);
  }

  @Override
  protected boolean filterAtt(final Attribute att) {
    return (((!att.isMultiValued()) && (!(att.getType() instanceof atl.research.class_.Class))) && (att.getType() != null));
  }
}
