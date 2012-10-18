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

package edu.cmu.tanl.annotator;

import java.io.File;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.tanl.type.gene;

/**
 * Example annotator that detects gene .
 */
public class LingPipeAnnotator extends JCasAnnotator_ImplBase {
  private String Path;

  private Chunker c;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    Path = (String) aContext.getConfigParameterValue("Path");
    try {
      c = (Chunker) AbstractExternalizable.readObject(new File(Path));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    String text = aJCas.getDocumentText();
    String word = text.substring(text.indexOf(" ") + 1);
    Chunking ck = c.chunk(word);

    Iterator<Chunk> i = ck.chunkSet().iterator();
    while (i.hasNext()) {
      Chunk ci = i.next();
      gene annotation = new gene(aJCas);
      String gene = word.substring(ci.start(), ci.end());
      annotation.setBegin(ci.start() - countSpaces(word, ci.start()));
      annotation.setEnd(ci.end() - countSpaces(word, ci.start()) - gene.split(" ").length);
      annotation.setGene(gene);
      annotation.addToIndexes();
    }
  }

  private int countSpaces(String word, int end) {
    return word.substring(0, end + 1).split(" ").length - 1;
  }
}