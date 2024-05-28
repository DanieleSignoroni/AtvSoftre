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
  BODataCollector AttivitaChatBODC = null; 
  WebFormForInternalRowForm AttivitaChatForm =  
     new com.thera.thermfw.web.WebFormForInternalRowForm(request, response, "AttivitaChatForm", "AttivitaChat", "Arial,10", "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, false, false, true, true, null, 1); 
  int mode = AttivitaChatForm.getMode(); 
  String key = AttivitaChatForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        AttivitaChatForm.outTraceInfo(getClass().getName()); 
        ClassADCollection globCadc = AttivitaChatForm.getClassADCollection(); 
        requestIsValid = true; 
        AttivitaChatForm.write(out); 
        String collectorName = AttivitaChatForm.findBODataCollectorName(); 
				 AttivitaChatBODC = (BODataCollector)Factory.createObject(collectorName); 
        AttivitaChatBODC.initialize("AttivitaChat", true, 1); 
        AttivitaChatForm.setBODataCollector(AttivitaChatBODC); 
        WebForm parentForm = (WebForm)request.getAttribute("parentForm"); 
        AttivitaChatForm.setJSTypeList(parentForm.getOwnerForm().getJSTypeList()); 
        AttivitaChatForm.setParent(parentForm); 
        AttivitaChatForm.writeHeadElements(out); 
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

	<title>Chat</title>
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
   AttivitaChatForm.writeBodyStartElements(out); 
%> 

<% 
  if(false) 
  { 
%> 
<form action="submit" name="AttivitaChatForm"><% 
  } 
%> 
<%
   AttivitaChatForm.writeFormStartElements(out); 
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
  WebTextInput AttivitaChatIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("AttivitaChat", "IdAzienda"); 
  AttivitaChatIdAzienda.setParent(AttivitaChatForm); 
%>
<input class="<%=AttivitaChatIdAzienda.getClassType()%>" id="<%=AttivitaChatIdAzienda.getId()%>" maxlength="<%=AttivitaChatIdAzienda.getMaxLength()%>" name="<%=AttivitaChatIdAzienda.getName()%>" size="<%=AttivitaChatIdAzienda.getSize()%>" style="display:none" type="text"><% 
  AttivitaChatIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaChat", "Id", null); 
   label.setParent(AttivitaChatForm); 
%><label class="<%=label.getClassType()%>" for="Id"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebTextInput AttivitaChatId =  
     new com.thera.thermfw.web.WebTextInput("AttivitaChat", "Id"); 
  AttivitaChatId.setParent(AttivitaChatForm); 
%>
<input class="<%=AttivitaChatId.getClassType()%>" id="<%=AttivitaChatId.getId()%>" maxlength="<%=AttivitaChatId.getMaxLength()%>" name="<%=AttivitaChatId.getName()%>" size="<%=AttivitaChatId.getSize()%>" type="text"><% 
  AttivitaChatId.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaChat", "MessageId", null); 
   label.setParent(AttivitaChatForm); 
%><label class="<%=label.getClassType()%>" for="MessageId"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebTextInput AttivitaChatMessageId =  
     new com.thera.thermfw.web.WebTextInput("AttivitaChat", "MessageId"); 
  AttivitaChatMessageId.setParent(AttivitaChatForm); 
%>
<input class="<%=AttivitaChatMessageId.getClassType()%>" id="<%=AttivitaChatMessageId.getId()%>" maxlength="<%=AttivitaChatMessageId.getMaxLength()%>" name="<%=AttivitaChatMessageId.getName()%>" size="<%=AttivitaChatMessageId.getSize()%>" type="text"><% 
  AttivitaChatMessageId.write(out); 
%>
</td>
			</tr>
			<tr>
			  <td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaChat", "Message", null); 
   label.setParent(AttivitaChatForm); 
%><label class="<%=label.getClassType()%>" for="Message"><%label.write(out);%></label><%}%></td>
		      <td><% 
  WebTextInput AttivitaChatMessage =  
     new com.thera.thermfw.web.WebTextArea("AttivitaChat", "Message"); 
  AttivitaChatMessage.setParent(AttivitaChatForm); 
%>
<textarea class="<%=AttivitaChatMessage.getClassType()%>" cols="60" id="<%=AttivitaChatMessage.getId()%>" maxlength="<%=AttivitaChatMessage.getMaxLength()%>" name="<%=AttivitaChatMessage.getName()%>" rows="5" size="<%=AttivitaChatMessage.getSize()%>"></textarea><% 
  AttivitaChatMessage.write(out); 
%>
</td>
			</tr>
	      </table>
		</span>
    </td>
  </tr>
</table>
<%
  AttivitaChatForm.writeFormEndElements(out); 
%>
<% 
  if(false) 
  { 
%> 
</form><% 
  } 
%> 

<%
   AttivitaChatForm.writeBodyEndElements(out); 
%> 
<% 
  if(false) 
  { 
%> 
</body><% 
  } 
%> 

