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
package org.apache.directory.studio.apacheds.schemaeditor.controller.actions;


import java.util.Iterator;

import org.apache.directory.studio.apacheds.schemaeditor.Activator;
import org.apache.directory.studio.apacheds.schemaeditor.PluginConstants;
import org.apache.directory.studio.apacheds.schemaeditor.controller.ProjectsHandler;
import org.apache.directory.studio.apacheds.schemaeditor.model.Project;
import org.apache.directory.studio.apacheds.schemaeditor.model.Project.ProjectState;
import org.apache.directory.studio.apacheds.schemaeditor.view.wrappers.ProjectWrapper;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * This action deletes one or more Projects from the ProjectsView.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class DeleteProjectAction extends Action implements IWorkbenchWindowActionDelegate
{
    /** The associated viewer */
    private TableViewer viewer;


    /**
     * Creates a new instance of DeleteProjectAction.
     *
     * @param view
     *      the associated view
     */
    public DeleteProjectAction( TableViewer viewer )
    {
        super( "&Delete Project" );
        setToolTipText( "Delete Project" );
        setId( PluginConstants.CMD_DELETE_PROJECT );
        setImageDescriptor( AbstractUIPlugin
            .imageDescriptorFromPlugin( Activator.PLUGIN_ID, PluginConstants.IMG_DELETE ) );
        setEnabled( false );
        this.viewer = viewer;
        this.viewer.addSelectionChangedListener( new ISelectionChangedListener()
        {
            public void selectionChanged( SelectionChangedEvent event )
            {
                StructuredSelection selection = ( StructuredSelection ) event.getSelection();
                if ( selection.size() == 1 )
                {
                    setText( "&Delete Project" );
                    setEnabled( true );
                }
                else if ( selection.size() > 1 )
                {
                    setText( "&Delete Projects" );
                    setEnabled( true );
                }
                else
                {
                    setText( "&Delete Project" );
                    setEnabled( false );
                }
            }
        } );
    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        ProjectsHandler projectsHandler = Activator.getDefault().getProjectsHandler();
        StructuredSelection selection = ( StructuredSelection ) viewer.getSelection();

        if ( !selection.isEmpty() )
        {
            MessageBox messageBox = new MessageBox( PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                SWT.YES | SWT.NO | SWT.ICON_QUESTION );
            int count = selection.size();
            if ( count == 1 )
            {
                ProjectWrapper wrapper = ( ProjectWrapper ) selection.getFirstElement();
                messageBox.setMessage( "Are you sure you want to delete project '" + wrapper.getProject().getName()
                    + "'?" );
            }
            else
            {
                messageBox.setMessage( "Are you sure you want to delete these " + count + " projects?" );
            }
            if ( messageBox.open() == SWT.YES )
            {
                for ( Iterator<?> iterator = selection.iterator(); iterator.hasNext(); )
                {
                    ProjectWrapper wrapper = ( ProjectWrapper ) iterator.next();
                    Project project = wrapper.getProject();

                    if ( project.getState() == ProjectState.OPEN )
                    {
                        // Closing the project before removing it. 
                        projectsHandler.closeProject( project );
                    }

                    projectsHandler.removeProject( project );
                }
            }
        }

    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run( IAction action )
    {
        run();
    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    public void dispose()
    {
        // Nothing to do
    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    public void init( IWorkbenchWindow window )
    {
        // Nothing to do
    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged( IAction action, ISelection selection )
    {
        // Nothing to do
    }
}
