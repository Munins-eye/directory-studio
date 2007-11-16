/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.studio.apacheds.configuration;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.directory.studio.apacheds.configuration.model.ServerConfigurationContentTypeChecker;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;


/**
 * This class implements a ContentDescriber for Apache DS Configuration file.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class ApacheDSConfigurationContentDescriber implements ITextContentDescriber
{
    /** The supported Options */
    private static final QualifiedName[] SUPPORTED_OPTIONS = new QualifiedName[]
        { IContentDescription.CHARSET, IContentDescription.BYTE_ORDER_MARK };


    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.content.ITextContentDescriber#describe(java.io.Reader, org.eclipse.core.runtime.content.IContentDescription)
     */
    public int describe( Reader contents, IContentDescription description ) throws IOException
    {
        if ( ServerConfigurationContentTypeChecker.isValid( contents ) )
        {
            return ITextContentDescriber.VALID;
        }
        else
        {
            return ITextContentDescriber.INVALID;
        }
    }


    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.content.IContentDescriber#describe(java.io.InputStream, org.eclipse.core.runtime.content.IContentDescription)
     */
    public int describe( InputStream contents, IContentDescription description ) throws IOException
    {
        if ( ServerConfigurationContentTypeChecker.isValid( contents ) )
        {
            return ITextContentDescriber.VALID;
        }
        else
        {
            return ITextContentDescriber.INVALID;
        }
    }


    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.content.IContentDescriber#getSupportedOptions()
     */
    public QualifiedName[] getSupportedOptions()
    {
        return SUPPORTED_OPTIONS;
    }
}
