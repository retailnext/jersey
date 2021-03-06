/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2014-2015 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.examples.entityfiltering.selectable;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

/**
 * Java application class starting Grizzly2 server with Entity Data Filtering with query parameters.
 *
 * @author Andy Pemberton (pembertona at oracle.com)
 */
public final class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    public static void main(final String[] args) {
        try {
            System.out.println("Jersey Entity Data Filtering Example.");

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,
                    new SelectableEntityFilteringApplication());

            System.out.println("Application started.\nTry out one of these URIs:");
            for (final String path : new String[]{"people/1234", "people/1234?select=familyName,givenName",
                    "people/1234?select=region,addresses.region",
                    "people/1234?select=familyName,givenName,addresses.phoneNumber.number"}) {
                System.out.println(BASE_URI + path);
            }
            System.out.println("Hit enter to stop it...");

            System.in.read();

            server.shutdownNow();
        } catch (final IOException ex) {
            Logger.getLogger(App.class.getName())
                    .log(Level.SEVERE, "I/O error occurred during reading from an system input stream.", ex);
        }
    }

    /**
     * Prevent instantiation.
     */
    private App() {
    }
}
