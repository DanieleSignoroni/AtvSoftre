<!-- WIZGEN Therm 2.0.0 as Form riga interna - multiBrowserGen = true -->

<% 
  if(false) 
  { 
%> 
<head><% 
  } 
%> 

<%@ page contentType="text/html; charset=Cp1252"%>
<%@ page import= " 
  java.sql.*, 
  java.util.*, 
  java.lang.reflect.*, 
  javax.naming.*, 
  com.thera.thermfw.common.*, 
  com.thera.thermfw.type.*, 
  com.thera.thermfw.web.*, 
  com.thera.thermfw.security.*, 
  com.thera.thermfw.base.*, 
  com.thera.thermfw.ad.*, 
  com.thera.thermfw.persist.*, 
  com.thera.thermfw.gui.cnr.*, 
  com.thera.thermfw.setting.*, 
  com.thera.thermfw.collector.*, 
  com.thera.thermfw.batch.web.*, 
  com.thera.thermfw.batch.*, 
  com.thera.thermfw.pref.* 
"%> 
<%
  ServletEnvironment se = (ServletEnvironment)Factory.createObject("com.thera.thermfw.web.ServletEnvironment"); 
  BODataCollector AttivitaCollaboratoreBODC = null; 
  WebFormForInternalRowForm AttivitaCollaboratoreForm =  
     new com.thera.thermfw.web.WebFormForInternalRowForm(request, response, "AttivitaCollaboratoreForm", "AttivitaCollaboratore", "Arial,10", "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, false, false, true, true, null, 1); 
  int mode = AttivitaCollaboratoreForm.getMode(); 
  String key = AttivitaCollaboratoreForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        AttivitaCollaboratoreForm.outTraceInfo(getClass().getName()); 
        ClassADCollection globCadc = AttivitaCollaboratoreForm.getClassADCollection(); 
        requestIsValid = true; 
        AttivitaCollaboratoreForm.write(out); 
        String collectorName = AttivitaCollaboratoreForm.findBODataCollectorName(); 
				 AttivitaCollaboratoreBODC = (BODataCollector)Factory.createObject(collectorName); 
        AttivitaCollaboratoreBODC.initialize("AttivitaCollaboratore", true, 1); 
        AttivitaCollaboratoreForm.setBODataCollector(AttivitaCollaboratoreBODC); 
        WebForm parentForm = (WebForm)request.getAttribute("parentForm"); 
        AttivitaCollaboratoreForm.setJSTypeList(parentForm.getOwnerForm().getJSTypeList()); 
        AttivitaCollaboratoreForm.setParent(parentForm); 
        AttivitaCollaboratoreForm.writeHeadElements(out); 
     }
  }
  catch(NamingException e) { 
    errorMessage = e.getMessage(); 
  } 
  catch(SQLException e) { 
     errorMessage = e.getMessage(); 
  } 
  finally 
  { 
     try 
     { 
        se.end(); 
     } 
     catch(IllegalArgumentException e) { 
        e.printStackTrace(); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(); 
     } 
  } 
%> 

	<title>Collaboratori</title>
<% 
  if(false) 
  { 
%> 
</head><% 
  } 
%> 

<% 
  if(false) 
  { 
%> 
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0"><% 
  } 
%> 
<%
   AttivitaCollaboratoreForm.writeBodyStartElements(out); 
%> 

<% 
  if(false) 
  { 
%> 
<form action="submit" name="AttivitaCollaboratoreForm"><% 
  } 
%> 
<%
   AttivitaCollaboratoreForm.writeFormStartElements(out); 
%> 

<table cellpadding="2" cellspacing="0" height="100%" id="emptyborder" width="100%">
  <tr>
    <td style="height:0">
      <span class="menubar" id="myMenuBar"></span>
    </td>
  </tr>
  <tr>
    <td style="height:0">
      <span class="toolbar" id="myToolBar"></span>
    </td>
  </tr>
  <tr>
    <td>
      <span class="tabbed" id="mytabbed">
          <table border="0" style="margin: 2 5 5 5;">
		  	<tr>
		      <td><% 
  WebTextInput AttivitaCollaboratoreIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("AttivitaCollaboratore", "IdAzienda"); 
  AttivitaCollaboratoreIdAzienda.setParent(AttivitaCollaboratoreForm); 
%>
<input class="<%=AttivitaCollaboratoreIdAzienda.getClassType()%>" id="<%=AttivitaCollaboratoreIdAzienda.getId()%>" maxlength="<%=AttivitaCollaboratoreIdAzienda.getMaxLength()%>" name="<%=AttivitaCollaboratoreIdAzienda.getName()%>" size="<%=AttivitaCollaboratoreIdAzienda.getSize()%>" style="display:none" type="text"><% 
  AttivitaCollaboratoreIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaCollaboratore", "Id", null); 
   label.setParent(AttivitaCollaboratoreForm); 
%><label class="<%=label.getClassType()%>" for="Id"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebTextInput AttivitaCollaboratoreId =  
     new com.thera.thermfw.web.WebTextInput("AttivitaCollaboratore", "Id"); 
  AttivitaCollaboratoreId.setParent(AttivitaCollaboratoreForm); 
%>
<input class="<%=AttivitaCollaboratoreId.getClassType()%>" id="<%=AttivitaCollaboratoreId.getId()%>" maxlength="<%=AttivitaCollaboratoreId.getMaxLength()%>" name="<%=AttivitaCollaboratoreId.getName()%>" size="<%=AttivitaCollaboratoreId.getSize()%>" type="text"><% 
  AttivitaCollaboratoreId.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaCollaboratore", "IdUtente", null); 
   label.setParent(AttivitaCollaboratoreForm); 
%><label class="<%=label.getClassType()%>" for="Utente"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebMultiSearchForm AttivitaCollaboratoreUtente =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaCollaboratore", "Utente", false, false, true, 1, null, null); 
  AttivitaCollaboratoreUtente.setParent(AttivitaCollaboratoreForm); 
  AttivitaCollaboratoreUtente.write(out); 
%>
<!--<span class="multisearchform" id="Utente" name="Utente"></span>--></td>
			</tr>
	      </table>
		</span>
    </td>
  </tr>
  <tr>
    <td style="height:0">
      <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(AttivitaCollaboratoreForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
    </td>
  </tr>
</table>

<%
  AttivitaCollaboratoreForm.writeFormEndElements(out); 
%>
<% 
  if(false) 
  { 
%> 
</form><% 
  } 
%> 


<%
   AttivitaCollaboratoreForm.writeBodyEndElements(out); 
%> 
<% 
  if(false) 
  { 
%> 
</body><% 
  } 
%> 

