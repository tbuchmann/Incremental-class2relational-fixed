package class2relationalImperative.rules;

import atl.research.class_.Class_Factory;
import atl.research.class_.Class_Package;
import atl.research.relational_.Relational_Factory;
import atl.research.relational_.Relational_Package;
import class2relationalImperative.correspondence.class2relationalImperative.BasicElem;
import class2relationalImperative.correspondence.class2relationalImperative.Class2relationalImperativeFactory;
import class2relationalImperative.correspondence.class2relationalImperative.Corr;
import class2relationalImperative.correspondence.class2relationalImperative.Transformation;
import com.google.common.base.Objects;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public abstract class Elem2Elem {
  protected Resource sourceModel;

  protected Resource targetModel;

  protected Resource corrModel;

  protected final Class_Factory sourceFactory = Class_Factory.eINSTANCE;

  protected final Relational_Factory targetFactory = Relational_Factory.eINSTANCE;

  protected final Class2relationalImperativeFactory corrFactory = Class2relationalImperativeFactory.eINSTANCE;

  protected final Class_Package sourcePackage = Class_Package.eINSTANCE;

  protected final Relational_Package targetPackage = Relational_Package.eINSTANCE;

  protected String ruleID;

  protected static Map<EObject, Corr> elementsToCorr = CollectionLiterals.<EObject, Corr>newHashMap();

  public Elem2Elem(final Resource src, final Resource trgt, final Resource corr) {
    this.sourceModel = src;
    this.targetModel = trgt;
    this.corrModel = corr;
    this.ruleID = "base";
    EObject _get = this.corrModel.getContents().get(0);
    final Consumer<Corr> _function = (Corr c) -> {
      Elem2Elem.elementsToCorr.put(c.getSourceElement(), c);
      Elem2Elem.elementsToCorr.put(c.getTargetElement(), c);
    };
    ((Transformation) _get).getCorrespondences().forEach(_function);
  }

  public void sourceToTarget() {
  }

  public void targetToSource() {
  }

  public Corr getCorrModelElem(final EObject obj) {
    return Elem2Elem.elementsToCorr.get(obj);
  }

  public Corr getOrCreateCorrModelElement(final EObject obj, final String description) {
    Corr corr = this.getCorrModelElem(obj);
    boolean _equals = Objects.equal(corr, null);
    if (_equals) {
      BasicElem _createBasicElem = this.corrFactory.createBasicElem();
      final Procedure1<BasicElem> _function = (BasicElem it) -> {
        EPackage _ePackage = obj.eClass().getEPackage();
        if ((_ePackage instanceof Class_Package)) {
          it.setSourceElement(obj);
        }
        EPackage _ePackage_1 = obj.eClass().getEPackage();
        if ((_ePackage_1 instanceof Relational_Package)) {
          it.setTargetElement(obj);
        }
        it.setDesc(description);
      };
      BasicElem _doubleArrow = ObjectExtensions.<BasicElem>operator_doubleArrow(_createBasicElem, _function);
      corr = _doubleArrow;
      EObject _get = this.corrModel.getContents().get(0);
      EList<Corr> _correspondences = ((Transformation) _get).getCorrespondences();
      _correspondences.add(corr);
      Elem2Elem.elementsToCorr.put(corr.getSourceElement(), corr);
      Elem2Elem.elementsToCorr.put(corr.getTargetElement(), corr);
    }
    return corr;
  }

  public EObject createSourceElement(final EClass clazz) {
    return this.sourceFactory.create(clazz);
  }

  public EObject createTargetElement(final EClass clazz) {
    return this.targetFactory.create(clazz);
  }

  public EObject getOrCreateSourceElem(final Corr corr, final EClass clazz) {
    EObject source = corr.getSourceElement();
    EObject _sourceElement = corr.getSourceElement();
    boolean _equals = Objects.equal(_sourceElement, null);
    if (_equals) {
      source = this.createSourceElement(clazz);
      corr.setSourceElement(source);
      Elem2Elem.elementsToCorr.put(corr.getSourceElement(), corr);
    }
    return source;
  }

  public EObject getOrCreateTargetElem(final Corr corr, final EClass clazz) {
    EObject target = corr.getTargetElement();
    boolean _equals = Objects.equal(target, null);
    if (_equals) {
      target = this.createTargetElement(clazz);
      corr.setTargetElement(target);
      Elem2Elem.elementsToCorr.put(corr.getTargetElement(), corr);
    }
    return target;
  }
}
