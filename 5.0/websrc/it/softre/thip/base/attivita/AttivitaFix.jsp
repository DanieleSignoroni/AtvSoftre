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
  BODataCollector AttivitaFixBODC = null; 
  WebFormForInternalRowForm AttivitaFixForm =  
     new com.thera.thermfw.web.WebFormForInternalRowForm(request, response, "AttivitaFixForm", "AttivitaFix", "Arial,10", "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, false, false, true, true, null, 1); 
  int mode = AttivitaFixForm.getMode(); 
  String key = AttivitaFixForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        AttivitaFixForm.outTraceInfo(getClass().getName()); 
        ClassADCollection globCadc = AttivitaFixForm.getClassADCollection(); 
        requestIsValid = true; 
        AttivitaFixForm.write(out); 
        String collectorName = AttivitaFixForm.findBODataCollectorName(); 
				 AttivitaFixBODC = (BODataCollector)Factory.createObject(collectorName); 
        AttivitaFixBODC.initialize("AttivitaFix", true, 1); 
        AttivitaFixForm.setBODataCollector(AttivitaFixBODC); 
        WebForm parentForm = (WebForm)request.getAttribute("parentForm"); 
        AttivitaFixForm.setJSTypeList(parentForm.getOwnerForm().getJSTypeList()); 
        AttivitaFixForm.setParent(parentForm); 
        AttivitaFixForm.writeHeadElements(out); 
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

	<title>Fix</title>
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
   AttivitaFixForm.writeBodyStartElements(out); 
%> 

<% 
  if(false) 
  { 
%> 
<form action="submit" name="AttivitaFixForm"><% 
  } 
%> 
<%
   AttivitaFixForm.writeFormStartElements(out); 
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
  WebTextInput AttivitaFixIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("AttivitaFix", "IdAzienda"); 
  AttivitaFixIdAzienda.setParent(AttivitaFixForm); 
%>
<input class="<%=AttivitaFixIdAzienda.getClassType()%>" id="<%=AttivitaFixIdAzienda.getId()%>" maxlength="<%=AttivitaFixIdAzienda.getMaxLength()%>" name="<%=AttivitaFixIdAzienda.getName()%>" size="<%=AttivitaFixIdAzienda.getSize()%>" style="display:none" type="text"><% 
  AttivitaFixIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaFix", "Id", null); 
   label.setParent(AttivitaFixForm); 
%><label class="<%=label.getClassType()%>" for="Id"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebTextInput AttivitaFixId =  
     new com.thera.thermfw.web.WebTextInput("AttivitaFix", "Id"); 
  AttivitaFixId.setParent(AttivitaFixForm); 
%>
<input class="<%=AttivitaFixId.getClassType()%>" id="<%=AttivitaFixId.getId()%>" maxlength="<%=AttivitaFixId.getMaxLength()%>" name="<%=AttivitaFixId.getName()%>" size="<%=AttivitaFixId.getSize()%>" type="text"><% 
  AttivitaFixId.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaFix", "IdFix", null); 
   label.setParent(AttivitaFixForm); 
%><label class="<%=label.getClassType()%>" for="Fix"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebMultiSearchForm AttivitaFixFix =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaFix", "Fix", false, false, true, 1, null, null); 
  AttivitaFixFix.setParent(AttivitaFixForm); 
  AttivitaFixFix.setAdditionalRestrictConditions("IdClienteSoftre,IdCliente"); 
  AttivitaFixFix.write(out); 
%>
<!--<span class="multisearchform" id="Fix" name="Fix"></span>--></td>
			</tr>
			<tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaFix", "Note", null); 
   label.setParent(AttivitaFixForm); 
%><label class="<%=label.getClassType()%>" for="Note"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput AttivitaFixNote =  
     new com.thera.thermfw.web.WebTextArea("AttivitaFix", "Note"); 
  AttivitaFixNote.setParent(AttivitaFixForm); 
%>
<textarea class="<%=AttivitaFixNote.getClassType()%>" cols="60" id="<%=AttivitaFixNote.getId()%>" maxlength="<%=AttivitaFixNote.getMaxLength()%>" name="<%=AttivitaFixNote.getName()%>" rows="5" size="<%=AttivitaFixNote.getSize()%>"></textarea><% 
  AttivitaFixNote.write(out); 
%>

                  </td>
                </tr>
	      </table>
		</span>
    </td>
  </tr>
</table>
<%
  AttivitaFixForm.writeFormEndElements(out); 
%>
<% 
  if(false) 
  { 
%> 
</form><% 
  } 
%> 

<%
   AttivitaFixForm.writeBodyEndElements(out); 
%> 
<% 
  if(false) 
  { 
%> 
</body><% 
  } 
%> 

