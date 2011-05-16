#*******************************************************************************
# ALMA - Atacama Large Millimiter Array
# (c) UNSPECIFIED - FILL IN, 2009 
# 
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
# 
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
# 
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
#
#  "@(#) $Id$"
#
# who                 when                              what
# ----------------    -----------------------------    --------------------------
# ACSCCG              Tue Apr 19 00:13:28 CLT 2011     Creation of the file
#

#/usr/bin/env python
'''
DESCRIPTION
BasicAntennaJavaImplTestPy Class.
Test Methods:
	-	testSetAntennaPosition()
	-	testGetAntennaPosition()
'''

import unittest
from Acspy.Clients.SimpleClient import PySimpleClient

from singleComponentCommon *
from BasicAntennaInterface import *

class BasicAntennaJavaImplTestPy(unittest.TestCase):

	################################################
	# lifecycle
	################################################
	
	def setUp(self):
		self.simpleClient = PySimpleClient()
		self.componentName = "testInstanceBasicAntennaJavaImpl"
		self.simpleClient.getLogger().logInfo("pyUnitTestBasicAntennaJavaImpl.BasicAntennaJavaImplTestPy.setUp()...")
        self.simpleClient.getLogger().logInfo("Get component " + self.componentName)
        self.component = self.simpleClient.getComponent(self.componentName)
	#__pPp__	    	           
	
	def tearDown(self):
		self.simpleClient.getLogger().logInfo("pyUnitTestBasicAntennaJavaImpl.BasicAntennaJavaImplTestPy.tearDown()...")
		self.simpleClient.getLogger().logInfo("Releasing component " + self.componentName)
		self.simpleClient.releaseComponent(self.componentName)
		self.simpleClient.disconnect()
	#__pPp__       
        
	################################################
	# test methods
	################################################
	
	def testSetAntennaPosition(self):
		self.simpleClient.getLogger().logInfo("pyUnitTestBasicAntennaJavaImpl.BasicAntennaJavaImplTestPy.testSetAntennaPosition()...")
		response = None
		pos = position()
		response = self.component.setAntennaPosition(pos)
		# no return is expected, response should be None
		assert response is None
	#__pPp__
			    
	def testGetAntennaPosition(self):
		self.simpleClient.getLogger().logInfo("pyUnitTestBasicAntennaJavaImpl.BasicAntennaJavaImplTestPy.testGetAntennaPosition()...")
		response = None
		response = self.component.getAntennaPosition()
		# a return is expected, response should be not None
		assert response is not None
	#__pPp__
			    
if __name__ == "__main__":
	unittest.main()
	print "End of BasicAntennaJavaImplTestPy test __oOo__"
