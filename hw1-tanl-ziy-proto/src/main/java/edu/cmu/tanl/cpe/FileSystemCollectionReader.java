package edu.cmu.tanl.cpe;

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

public class FileSystemCollectionReader extends CollectionReader_ImplBase {
  public static final String PARAM_INPUTDIR = "InputDirectory";

  public static final String PARAM_ENCODING = "Encoding";

  public static final String PARAM_LANGUAGE = "Language";

  private File mFile;

  private String[] lines;

  private String mEncoding;

  private int numberOfLines;

  private int index;

  public void initialize() throws ResourceInitializationException {
    File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)));
    mEncoding = (String) getConfigParameterValue(PARAM_ENCODING);
    mFile = directory;
    index = 0;
    String text = null;
    try {
      text = FileUtils.file2String(mFile, mEncoding);
    } catch (IOException e) {
      e.printStackTrace();
    }
    lines = text.split("\n");
    numberOfLines = lines.length;
  }

  public boolean hasNext() {
    return index < numberOfLines;
  }

  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }
    jcas.setDocumentText(lines[index]);
    index++;
  }

  public void close() throws IOException {

  }

  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return null;
  }

}
