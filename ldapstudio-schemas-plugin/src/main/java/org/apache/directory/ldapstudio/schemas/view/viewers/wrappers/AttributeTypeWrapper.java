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

package org.apache.directory.ldapstudio.schemas.view.viewers.wrappers;


import org.apache.directory.ldapstudio.schemas.Activator;
import org.apache.directory.ldapstudio.schemas.PluginConstants;
import org.apache.directory.ldapstudio.schemas.model.AttributeType;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * This class is used to represent an attribute type in a tree viewer.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class AttributeTypeWrapper extends TreeNode
{
    /** The associated attribute type */
    private AttributeType myAttributeType;
    private State state;

    
    public enum State
    {
        resolved, unResolved
    }

    /**
     * Creates a new instance of AttributeTypeWrapper.
     *
     * @param myAttributeType
     *      the associated attribute type
     * @param parent
     *      the parent element
     */
    public AttributeTypeWrapper( AttributeType myAttributeType, ITreeNode parent )
    {
        super( parent );

        this.myAttributeType = myAttributeType;
        this.state = State.resolved;
    }


    /**
     * Gets the associated attribute type.
     * 
     * @return 
     *      the associated attribute type
     */
    public AttributeType getMyAttributeType()
    {
        return myAttributeType;
    }


    /* (non-Javadoc)
     * @see org.apache.directory.ldapstudio.schemas.view.viewers.wrappers.TreeNode#getImage()
     */
    public Image getImage()
    {
        return AbstractUIPlugin.imageDescriptorFromPlugin( Activator.PLUGIN_ID, PluginConstants.IMG_ATTRIBUTE_TYPE )
            .createImage();
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals( Object obj )
    {
        if ( obj instanceof AttributeTypeWrapper )
        {
            AttributeTypeWrapper compared = ( AttributeTypeWrapper ) obj;
            return compared.getMyAttributeType().getOid().equals( getMyAttributeType().getOid() );
        }

        return false;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return myAttributeType + " wrapper"; //$NON-NLS-1$
    }
    
    /**
     * Gets the state of the object class wrapper.
     * 
     * @return
     *      the state of the object class wrapper
     */
    public State getState()
    {
        return state;
    }


    /**
     * Sets the state of the object class wrapper.
     * 
     * @param state
     *      the state of the object class wrapper
     */
    public void setState( State state )
    {
        this.state = state;
    }
}
