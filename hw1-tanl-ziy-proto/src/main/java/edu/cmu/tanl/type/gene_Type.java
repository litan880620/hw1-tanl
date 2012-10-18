
/* First created by JCasGen Wed Oct 17 20:54:06 EDT 2012 */
package edu.cmu.tanl.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Oct 17 21:42:56 EDT 2012
 * @generated */
public class gene_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (gene_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = gene_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new gene(addr, gene_Type.this);
  			   gene_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new gene(addr, gene_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = gene.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.tanl.type.gene");
 
  /** @generated */
  final Feature casFeat_Gene;
  /** @generated */
  final int     casFeatCode_Gene;
  /** @generated */ 
  public String getGene(int addr) {
        if (featOkTst && casFeat_Gene == null)
      jcas.throwFeatMissing("Gene", "edu.cmu.tanl.type.gene");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Gene);
  }
  /** @generated */    
  public void setGene(int addr, String v) {
        if (featOkTst && casFeat_Gene == null)
      jcas.throwFeatMissing("Gene", "edu.cmu.tanl.type.gene");
    ll_cas.ll_setStringValue(addr, casFeatCode_Gene, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public gene_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Gene = jcas.getRequiredFeatureDE(casType, "Gene", "uima.cas.String", featOkTst);
    casFeatCode_Gene  = (null == casFeat_Gene) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Gene).getCode();

  }
}



    