<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/4.7.0/websrcsvil/dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Form - multiBrowserGen = true -->
<%=WebGenerator.writeRuntimeInfo()%>
  <head>
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
  BODataCollector YFixPersonaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YFixPersonaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YFixPersonaForm", "YFixPersona", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/personalizzazioni/YFixPersona.js"); 
  YFixPersonaForm.setServletEnvironment(se); 
  YFixPersonaForm.setJSTypeList(jsList); 
  YFixPersonaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YFixPersonaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YFixPersonaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YFixPersonaForm.getMode(); 
  String key = YFixPersonaForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  boolean conflitPresent = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        YFixPersonaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YFixPersonaForm.findBODataCollectorName(); 
                YFixPersonaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YFixPersonaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YFixPersonaBODC).setServletEnvironment(se); 
        YFixPersonaBODC.initialize("YFixPersona", true, 0); 
        YFixPersonaForm.setBODataCollector(YFixPersonaBODC); 
        int rcBODC = YFixPersonaForm.initSecurityServices(); 
        mode = YFixPersonaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YFixPersonaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YFixPersonaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YFixPersonaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YFixPersonaForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YFixPersonaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YFixPersonaForm.getBodyOnBeforeUnload()%>" onload="<%=YFixPersonaForm.getBodyOnLoad()%>" onunload="<%=YFixPersonaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YFixPersonaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YFixPersonaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YFixPersonaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YFixPersonaForm.getServlet()%>" method="post" name="YFixPersonaForm" style="height:100%"><%
  YFixPersonaForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YFixPersonaForm); 
 mytabbed.addTab("tab1", "it.softre.thip.personalizzazioni.resources.YFixPersona", "tab1", "YFixPersona", null, null, null, null); 
 mytabbed.addTab("tab2", "it.softre.thip.personalizzazioni.resources.YFixPersona", "tab2", "YFixPersona", null, null, null, null); 
 mytabbed.addTab("tab3", "it.softre.thip.personalizzazioni.resources.YFixPersona", "tab3", "YFixPersona", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YFixPersona", "IdNumeroFix", null); 
   label.setParent(YFixPersonaForm); 
%><label class="<%=label.getClassType()%>" for="IdNumeroFix"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YFixPersonaIdNumeroFix =  
     new com.thera.thermfw.web.WebTextInput("YFixPersona", "IdNumeroFix"); 
  YFixPersonaIdNumeroFix.setParent(YFixPersonaForm); 
%>
<input class="<%=YFixPersonaIdNumeroFix.getClassType()%>" id="<%=YFixPersonaIdNumeroFix.getId()%>" maxlength="<%=YFixPersonaIdNumeroFix.getMaxLength()%>" name="<%=YFixPersonaIdNumeroFix.getName()%>" size="<%=YFixPersonaIdNumeroFix.getSize()%>"><% 
  YFixPersonaIdNumeroFix.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YFixPersona", "IdCliente", null); 
   label.setParent(YFixPersonaForm); 
%><label class="<%=label.getClassType()%>" for="RelCliente"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YFixPersonaRelCliente =  
     new com.thera.thermfw.web.WebMultiSearchForm("YFixPersona", "RelCliente", false, false, true, 1, null, null); 
  YFixPersonaRelCliente.setParent(YFixPersonaForm); 
  YFixPersonaRelCliente.write(out); 
%>
<!--<span class="multisearchform" id="RelCliente"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YFixPersona", "Descrizione", null); 
   label.setParent(YFixPersonaForm); 
%><label class="<%=label.getClassType()%>" for="Descrizione"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YFixPersonaDescrizione =  
     new com.thera.thermfw.web.WebTextArea("YFixPersona", "Descrizione"); 
  YFixPersonaDescrizione.setParent(YFixPersonaForm); 
%>
<textarea class="<%=YFixPersonaDescrizione.getClassType()%>" cols="60" id="<%=YFixPersonaDescrizione.getId()%>" maxlength="<%=YFixPersonaDescrizione.getMaxLength()%>" name="<%=YFixPersonaDescrizione.getName()%>" rows="5" size="<%=YFixPersonaDescrizione.getSize()%>"></textarea><% 
  YFixPersonaDescrizione.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YFixPersona", "DataRilascio", null); 
   label.setParent(YFixPersonaForm); 
%><label class="<%=label.getClassType()%>" for="DataRilascio"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YFixPersonaDataRilascio =  
     new com.thera.thermfw.web.WebTextInput("YFixPersona", "DataRilascio"); 
  YFixPersonaDataRilascio.setShowCalendarBtn(true); 
  YFixPersonaDataRilascio.setParent(YFixPersonaForm); 
%>
<input class="<%=YFixPersonaDataRilascio.getClassType()%>" id="<%=YFixPersonaDataRilascio.getId()%>" maxlength="<%=YFixPersonaDataRilascio.getMaxLength()%>" name="<%=YFixPersonaDataRilascio.getName()%>" size="<%=YFixPersonaDataRilascio.getSize()%>"><% 
  YFixPersonaDataRilascio.write(out); 
%>

                      <button id="DataOdierna" onclick="settaDataOdierna()" type="button">Setta data odierna</button>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
  WebCheckBox YFixPersonaFlagRilascio =  
     new com.thera.thermfw.web.WebCheckBox("YFixPersona", "FlagRilascio"); 
  YFixPersonaFlagRilascio.setParent(YFixPersonaForm); 
%>
<input id="<%=YFixPersonaFlagRilascio.getId()%>" name="<%=YFixPersonaFlagRilascio.getName()%>" type="checkbox" value="Y"><%
  YFixPersonaFlagRilascio.write(out); 
%>

                    </td>
                    <td valign="top">
                      <% 
  WebCheckBox YFixPersonaFlagModificheStd =  
     new com.thera.thermfw.web.WebCheckBox("YFixPersona", "FlagModificheStd"); 
  YFixPersonaFlagModificheStd.setParent(YFixPersonaForm); 
%>
<input id="<%=YFixPersonaFlagModificheStd.getId()%>" name="<%=YFixPersonaFlagModificheStd.getName()%>" type="checkbox" value="Y"><%
  YFixPersonaFlagModificheStd.write(out); 
%>

                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab2")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab2"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YFixPersona", "LinkAnalisi", null); 
   label.setParent(YFixPersonaForm); 
%><label class="<%=label.getClassType()%>" for="LinkAnalisi"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YFixPersonaLinkAnalisi =  
     new com.thera.thermfw.web.WebTextArea("YFixPersona", "LinkAnalisi"); 
  YFixPersonaLinkAnalisi.setParent(YFixPersonaForm); 
%>
<textarea class="<%=YFixPersonaLinkAnalisi.getClassType()%>" cols="60" id="<%=YFixPersonaLinkAnalisi.getId()%>" maxlength="<%=YFixPersonaLinkAnalisi.getMaxLength()%>" name="<%=YFixPersonaLinkAnalisi.getName()%>" rows="1" size="<%=YFixPersonaLinkAnalisi.getSize()%>"></textarea><% 
  YFixPersonaLinkAnalisi.write(out); 
%>

                    </td>
                  </tr>
                </table>
                 <table style="height:100%; width:100%">
                  <tr>
                    <td valign="top">
                    <iframe id="Analisi" src style="height: 78vh;     width: -webkit-fill-available;     border: 2px solid black;     border-radius: 10px;     margin: 10px;"></iframe>
                    </td>
                   </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab3")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab3"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YFixPersona", "LinkDocumentazione", null); 
   label.setParent(YFixPersonaForm); 
%><label class="<%=label.getClassType()%>" for="LinkDocumentazione"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YFixPersonaLinkDocumentazione =  
     new com.thera.thermfw.web.WebTextArea("YFixPersona", "LinkDocumentazione"); 
  YFixPersonaLinkDocumentazione.setParent(YFixPersonaForm); 
%>
<textarea class="<%=YFixPersonaLinkDocumentazione.getClassType()%>" cols="60" id="<%=YFixPersonaLinkDocumentazione.getId()%>" maxlength="<%=YFixPersonaLinkDocumentazione.getMaxLength()%>" name="<%=YFixPersonaLinkDocumentazione.getName()%>" rows="1" size="<%=YFixPersonaLinkDocumentazione.getSize()%>"></textarea><% 
  YFixPersonaLinkDocumentazione.write(out); 
%>

                    </td>
                  </tr>
                </table>
                 <table style="height:100%; width:100%">
                  <tr>
                    <td valign="top">
                    <iframe id="Documentazione" src style="height: 78vh;     width: -webkit-fill-available;     border: 2px solid black;     border-radius: 10px;     margin: 10px;"></iframe>
                    </td>
                   </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
            </div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YFixPersonaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YFixPersonaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YFixPersonaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YFixPersonaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YFixPersonaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YFixPersonaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YFixPersonaBODC.getErrorList().getErrors()); 
           if(YFixPersonaBODC.getConflict() != null) 
                conflitPresent = true; 
     } 
     else 
        errors.add(new ErrorMessage("BAS0000010")); 
  } 
  catch(NamingException e) { 
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("CBS000025", errorMessage));  } 
  catch(SQLException e) {
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("BAS0000071", errorMessage));  } 
  catch(Throwable e) {
     e.printStackTrace(Trace.excStream);
  }
  finally 
  {
     if(YFixPersonaBODC != null && !YFixPersonaBODC.close(false)) 
        errors.addAll(0, YFixPersonaBODC.getErrorList().getErrors()); 
     try 
     { 
        se.end(); 
     }
     catch(IllegalArgumentException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
  } 
  if(!errors.isEmpty())
  { 
      if(!conflitPresent)
  { 
     request.setAttribute("ErrorMessages", errors); 
     String errorPage = YFixPersonaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YFixPersonaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YFixPersonaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
