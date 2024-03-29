/*******************************************************************************
 * ALMA - Atacama Large Millimiter Array
 *
 * (c) European Southern Observatory, 2002
 * Copyright by ESO (in the framework of the ALMA collaboration)
 * and Cosylab 2002, All rights reserved
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
 *
 * "@(#) $Id$"
 *
 * who                 when                             what
 * ----------------    -----------------------------    ------------------------
 * ACSCCG              Tue Apr 19 00:13:27 CLT 2011     Creation of the file
 * 
 */
 
#include <cppunit/TestFixture.h>
#include <cppunit/TestCaller.h>
#include <maciSimpleClient.h>

#include "singleComponentCommonC.h"
#include "BasicAntennaInterfaceC.h"

maci::SimpleClient client;

class BasicAntennaJavaImplTest: public CppUnit::TestFixture 
{
	private:
	
		singleComponent::BasicAntennaInterface *BasicAntennaInterfaceComponent;
		
	public:
	
		void setUp() 
		{
			BasicAntennaInterfaceComponent = client.getComponent<singleComponent::BasicAntennaInterface>("testInstanceBasicAntennaInterface", 0, true);
		}//

		void tearDown()
		{
			client.releaseComponent("testInstanceBasicAntennaInterface");
		}//
		
		///////////////////////////////////////////////////////////
		// Test methods
		///////////////////////////////////////////////////////////
		
		void testSetAntennaPosition()
		{
			// TODO (generator autoimplementation, protected region)
		}//
		void testGetAntennaPosition()
		{
			// TODO (generator autoimplementation, protected region)
		}//
};

int main(int argc, char *argv[]) 
{
	client.init(argc, argv);
	
	client.login();
	
	// testSetAntennaPosition
	CppUnit::TestCaller<BasicAntennaJavaImplTest> SetAntennaPositionTest ("testInstanceBasicAntennaJavaImpl", &BasicAntennaJavaImplTest::testSetAntennaPosition);
	
	// testGetAntennaPosition
	CppUnit::TestCaller<BasicAntennaJavaImplTest> GetAntennaPositionTest ("testInstanceBasicAntennaJavaImpl", &BasicAntennaJavaImplTest::testGetAntennaPosition);
	
	client.logout();
}
