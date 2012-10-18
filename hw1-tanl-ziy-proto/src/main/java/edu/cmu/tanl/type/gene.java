

/* First created by JCasGen Wed Oct 17 20:54:06 EDT 2012 */
package edu.cmu.tanl.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 17 21:42:56 EDT 2012
 * XML source: D:/Program Files/Java/WorkSpace-Juno/hw1-tanl/src/main/resources/descriptors/analysis_engine/TutorialTypeSystem.xml
 * @generated */
public class gene extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(gene.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected gene() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public gene(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public gene(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public gene(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Gene

  /** getter for Gene - gets Gene name
   * @generated */
  public String getGene() {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_Gene == null)
      jcasType.jcas.throwFeatMissing("Gene", "edu.cmu.tanl.type.gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((gene_Type)jcasType).casFeatCode_Gene);}
    
  /** setter for Gene - sets Gene name 
   * @generated */
  public void setGene(String v) {
    if (gene_Type.featOkTst && ((gene_Type)jcasType).casFeat_Gene == null)
      jcasType.jcas.throwFeatMissing("Gene", "edu.cmu.tanl.type.gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((gene_Type)jcasType).casFeatCode_Gene, v);}    
  }

    