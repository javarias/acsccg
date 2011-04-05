#! /bin/bash
cd ../src

#java -classpath ../lib/org.eclipse.emf.mwe.core_1.0.0.v201008251122.jar:../lib/org.apache.commons.cli_1.0.0.jar:../lib/org.eclipse.emf.common_2.6.0.v20100914-1218.jar:../lib/org.apache.commons.logging_1.1.1.v201005080502.jar:../lib/org.eclipse.emf.mwe2.runtime_1.0.1.v201008251113.jar:../lib/org.eclipse.emf.ecore_2.6.1.v20100914-1218.jar:../lib/org.eclipse.emf.mwe.utils_1.0.0.v201008251122.jar:../lib/org.eclipse.emf.ecore.xmi_2.5.0.v20100521-1846.jar:../lib/org.eclipse.xtend.typesystem.uml2_1.0.1.v201008251147.jar:../lib/org.eclipse.xtend.typesystem.emf_1.0.1.v201008251147.jar:../lib/org.eclipse.xpand_1.0.1.v201008251147.jar:../lib/org.eclipse.xtend_1.0.1.v201008251147.jar:../lib/org.eclipse.xtend.typesystem.xsd_1.0.1.v201008251147.jar:../lib/org.eclipse.uml2.uml_3.1.1.v201008191505.jar:../lib/org.eclipse.emf.mapping.ecore2xml_2.5.0.v20100521-1847.jar:../lib/org.eclipse.uml2.uml.resources_3.1.1.v201008191505.jar:../lib/org.antlr.runtime_3.0.0.v200803061811.jar:../lib/org.eclipse.uml2.common_1.5.0.v201005031530.jar:../lib/com.ibm.icu_4.2.1.v20100412.jar org.eclipse.emf.mwe.core.WorkflowRunner ../src/complwf.mwe

#acsStartJava -j ../lib org.eclipse.emf.mwe.core.WorkflowRunner ../src/workflow.mwe
acsStartJava org.eclipse.emf.mwe.core.WorkflowRunner ../src/workflow.mwe

#cat ../src-gen/model.scxml
