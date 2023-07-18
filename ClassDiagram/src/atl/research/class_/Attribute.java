/**
 */
package atl.research.class_;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link atl.research.class_.Attribute#isMultiValued <em>Multi Valued</em>}</li>
 *   <li>{@link atl.research.class_.Attribute#getType <em>Type</em>}</li>
 *   <li>{@link atl.research.class_.Attribute#getOwner <em>Owner</em>}</li>
 * </ul>
 *
 * @see atl.research.class_.Class_Package#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends NamedElt {
	/**
	 * Returns the value of the '<em><b>Multi Valued</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Valued</em>' attribute.
	 * @see #setMultiValued(boolean)
	 * @see atl.research.class_.Class_Package#getAttribute_MultiValued()
	 * @model default="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isMultiValued();

	/**
	 * Sets the value of the '{@link atl.research.class_.Attribute#isMultiValued <em>Multi Valued</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multi Valued</em>' attribute.
	 * @see #isMultiValued()
	 * @generated
	 */
	void setMultiValued(boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Classifier)
	 * @see atl.research.class_.Class_Package#getAttribute_Type()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Classifier getType();

	/**
	 * Sets the value of the '{@link atl.research.class_.Attribute#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Classifier value);

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link atl.research.class_.Class#getAttr <em>Attr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(atl.research.class_.Class)
	 * @see atl.research.class_.Class_Package#getAttribute_Owner()
	 * @see atl.research.class_.Class#getAttr
	 * @model opposite="attr" required="true" transient="false" ordered="false"
	 * @generated
	 */
	atl.research.class_.Class getOwner();

	/**
	 * Sets the value of the '{@link atl.research.class_.Attribute#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(atl.research.class_.Class value);

} // Attribute
