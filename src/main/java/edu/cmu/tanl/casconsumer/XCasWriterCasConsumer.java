package edu.cmu.tanl.casconsumer;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//package org.apache.uima.examples.cpe;
/* 
 *******************************************************************************************
 * N O T E :     The XML format (XCAS) that this Cas Consumer outputs, is eventually 
 *               being superceeded by the more standardized and compact XMI format.  However
 *               it is used currently as the expected form for remote services, and there is
 *               existing tooling for doing stand-alone component development and debugging 
 *               that uses this format to populate an initial CAS.  So it is not 
 *               deprecated yet;  it is also being kept for compatibility with older versions.
 *               
 *               New code should consider using the XmiWriterCasConsumer where possible,
 *               which uses the current XMI format for XML externalizations of the CAS
 *******************************************************************************************               
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.impl.XCASSerializer;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.XMLSerializer;
import org.xml.sax.SAXException;

import edu.cmu.tanl.type.gene;


public class XCasWriterCasConsumer extends CasConsumer_ImplBase {
  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  public static final String PARAM_OUTPUTFILE = "OutputFile";

  private File mOutputFile;

  private FileWriter out;

  public void initialize() throws ResourceInitializationException {

    mOutputFile = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR) + "/"
            + (String) getConfigParameterValue(PARAM_OUTPUTFILE));
    if (!mOutputFile.exists()) {
      try {
        mOutputFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      out = new FileWriter(mOutputFile); 
      out.close();
      out = new FileWriter(mOutputFile, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    
    try {
      String word = jcas.getDocumentText();
      FSIndex index = jcas.getAnnotationIndex(gene.type);
      Iterator iterator = index.iterator();
      while (iterator.hasNext()) {
        gene geneTag = (gene) iterator.next();
        out.write(word.substring(0, word.indexOf(" ")) + "|" + geneTag.getBegin() + " ");
        out.write(geneTag.getEnd() + "|" + geneTag.getGene() + "\n");
      }
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
