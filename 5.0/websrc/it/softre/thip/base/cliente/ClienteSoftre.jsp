<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/5.0.0/websrcsvil/dtd/xhtml1-transitional.dtd">
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
  BODataCollector ClienteSoftreBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm ClienteSoftreForm =  
     new com.thera.thermfw.web.WebForm(request, response, "ClienteSoftreForm", "ClienteSoftre", null, "it.softre.thip.base.cliente.web.ClienteSoftreFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/base/cliente/ClienteSoftre.js"); 
  ClienteSoftreForm.setServletEnvironment(se); 
  ClienteSoftreForm.setJSTypeList(jsList); 
  ClienteSoftreForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  ClienteSoftreForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  ClienteSoftreForm.setWebFormModifierClass("it.softre.thip.base.cliente.web.ClienteSoftreFormModifier"); 
  ClienteSoftreForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = ClienteSoftreForm.getMode(); 
  String key = ClienteSoftreForm.getKey(); 
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
        ClienteSoftreForm.outTraceInfo(getClass().getName()); 
        String collectorName = ClienteSoftreForm.findBODataCollectorName(); 
                ClienteSoftreBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (ClienteSoftreBODC instanceof WebDataCollector) 
            ((WebDataCollector)ClienteSoftreBODC).setServletEnvironment(se); 
        ClienteSoftreBODC.initialize("ClienteSoftre", true, 0); 
        ClienteSoftreForm.setBODataCollector(ClienteSoftreBODC); 
        int rcBODC = ClienteSoftreForm.initSecurityServices(); 
        mode = ClienteSoftreForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           ClienteSoftreForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = ClienteSoftreBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              ClienteSoftreForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(ClienteSoftreForm); 
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
  myToolBarTB.setParent(ClienteSoftreForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body onbeforeunload="<%=ClienteSoftreForm.getBodyOnBeforeUnload()%>" onload="<%=ClienteSoftreForm.getBodyOnLoad()%>" onunload="<%=ClienteSoftreForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   ClienteSoftreForm.writeBodyStartElements(out); 
%> 

  <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = ClienteSoftreForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", ClienteSoftreBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=ClienteSoftreForm.getServlet()%>" method="post" name="ClienteSoftreForm" style="height:100%"><%
  ClienteSoftreForm.writeFormStartElements(out); 
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
        <td>
          <% 
  WebTextInput ClienteSoftreIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "IdAzienda"); 
  ClienteSoftreIdAzienda.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreIdAzienda.getClassType()%>" id="<%=ClienteSoftreIdAzienda.getId()%>" maxlength="<%=ClienteSoftreIdAzienda.getMaxLength()%>" name="<%=ClienteSoftreIdAzienda.getName()%>" size="<%=ClienteSoftreIdAzienda.getSize()%>" type="hidden"><% 
  ClienteSoftreIdAzienda.write(out); 
%>

        </td>
      </tr>
      <tr>
        <td height="100%">
          <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(ClienteSoftreForm); 
 mytabbed.addTab("tab1", "it.softre.thip.base.cliente.resources.ClienteSoftre", "tab1", "ClienteSoftre", null, null, null, null); 
 mytabbed.addTab("tab2", "it.softre.thip.base.cliente.resources.ClienteSoftre", "tab2", "ClienteSoftre", null, null, null, null); 
 mytabbed.addTab("tab3", "it.softre.thip.base.cliente.resources.ClienteSoftre", "tab3", "ClienteSoftre", null, null, null, null); 
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
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "IdAnagrafico", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="AnagraficoDiBase"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebMultiSearchForm ClienteSoftreAnagraficoDiBase =  
     new com.thera.thermfw.web.WebMultiSearchForm("ClienteSoftre", "AnagraficoDiBase", false, false, true, 1, null, null); 
  ClienteSoftreAnagraficoDiBase.setParent(ClienteSoftreForm); 
  ClienteSoftreAnagraficoDiBase.write(out); 
%>
<!--<span class="multisearchform" id="AnagraficoDiBase"></span>-->
                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "DatabaseVendor", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DatabaseVendor"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebComboBox ClienteSoftreDatabaseVendor =  
     new com.thera.thermfw.web.WebComboBox("ClienteSoftre", "DatabaseVendor", null); 
  ClienteSoftreDatabaseVendor.setParent(ClienteSoftreForm); 
%>
<select id="<%=ClienteSoftreDatabaseVendor.getId()%>" name="<%=ClienteSoftreDatabaseVendor.getName()%>"><% 
  ClienteSoftreDatabaseVendor.write(out); 
%> 
</select>
                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "ApplicationSvrvType", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="ApplicationSvrvType"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput ClienteSoftreApplicationSvrvType =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "ApplicationSvrvType"); 
  ClienteSoftreApplicationSvrvType.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreApplicationSvrvType.getClassType()%>" id="<%=ClienteSoftreApplicationSvrvType.getId()%>" maxlength="<%=ClienteSoftreApplicationSvrvType.getMaxLength()%>" name="<%=ClienteSoftreApplicationSvrvType.getName()%>" size="<%=ClienteSoftreApplicationSvrvType.getSize()%>"><% 
  ClienteSoftreApplicationSvrvType.write(out); 
%>

                  </td>
                </tr>
              </table>
              <table>
                <tr>
                  <td>
                    <table>
                      <tr>
                        <td>
                          <fieldset>
                            <legend>Informazioni su Panthera (01)</legend>
                            <table>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "PthVrm01", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="PthVrm01"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftrePthVrm01 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "PthVrm01"); 
  ClienteSoftrePthVrm01.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftrePthVrm01.getClassType()%>" id="<%=ClienteSoftrePthVrm01.getId()%>" maxlength="<%=ClienteSoftrePthVrm01.getMaxLength()%>" name="<%=ClienteSoftrePthVrm01.getName()%>" size="<%=ClienteSoftrePthVrm01.getSize()%>"><% 
  ClienteSoftrePthVrm01.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "JvmApplication01", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="JvmApplication01"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreJvmApplication01 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "JvmApplication01"); 
  ClienteSoftreJvmApplication01.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreJvmApplication01.getClassType()%>" id="<%=ClienteSoftreJvmApplication01.getId()%>" maxlength="<%=ClienteSoftreJvmApplication01.getMaxLength()%>" name="<%=ClienteSoftreJvmApplication01.getName()%>" size="<%=ClienteSoftreJvmApplication01.getSize()%>"><% 
  ClienteSoftreJvmApplication01.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "JvmBatch01", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="JvmBatch01"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreJvmBatch01 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "JvmBatch01"); 
  ClienteSoftreJvmBatch01.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreJvmBatch01.getClassType()%>" id="<%=ClienteSoftreJvmBatch01.getId()%>" maxlength="<%=ClienteSoftreJvmBatch01.getMaxLength()%>" name="<%=ClienteSoftreJvmBatch01.getName()%>" size="<%=ClienteSoftreJvmBatch01.getSize()%>"><% 
  ClienteSoftreJvmBatch01.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "SirioVrm01", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="SirioVrm01"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreSirioVrm01 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "SirioVrm01"); 
  ClienteSoftreSirioVrm01.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreSirioVrm01.getClassType()%>" id="<%=ClienteSoftreSirioVrm01.getId()%>" maxlength="<%=ClienteSoftreSirioVrm01.getMaxLength()%>" name="<%=ClienteSoftreSirioVrm01.getName()%>" size="<%=ClienteSoftreSirioVrm01.getSize()%>"><% 
  ClienteSoftreSirioVrm01.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "CrystalVrm01", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="CrystalVrm01"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreCrystalVrm01 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "CrystalVrm01"); 
  ClienteSoftreCrystalVrm01.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreCrystalVrm01.getClassType()%>" id="<%=ClienteSoftreCrystalVrm01.getId()%>" maxlength="<%=ClienteSoftreCrystalVrm01.getMaxLength()%>" name="<%=ClienteSoftreCrystalVrm01.getName()%>" size="<%=ClienteSoftreCrystalVrm01.getSize()%>"><% 
  ClienteSoftreCrystalVrm01.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "PasswordAdmin01", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="PasswordAdmin01"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftrePasswordAdmin01 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "PasswordAdmin01"); 
  ClienteSoftrePasswordAdmin01.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftrePasswordAdmin01.getClassType()%>" id="<%=ClienteSoftrePasswordAdmin01.getId()%>" maxlength="<%=ClienteSoftrePasswordAdmin01.getMaxLength()%>" name="<%=ClienteSoftrePasswordAdmin01.getName()%>" size="<%=ClienteSoftrePasswordAdmin01.getSize()%>"><% 
  ClienteSoftrePasswordAdmin01.write(out); 
%>

                                </td>
                              </tr>
                            </table>
                          </fieldset>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>
                    <table>
                      <tr>
                        <td>
                          <fieldset>
                            <legend>Informazioni su Panthera (02)</legend>
                            <table>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "PthVrm02", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="PthVrm02"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftrePthVrm02 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "PthVrm02"); 
  ClienteSoftrePthVrm02.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftrePthVrm02.getClassType()%>" id="<%=ClienteSoftrePthVrm02.getId()%>" maxlength="<%=ClienteSoftrePthVrm02.getMaxLength()%>" name="<%=ClienteSoftrePthVrm02.getName()%>" size="<%=ClienteSoftrePthVrm02.getSize()%>"><% 
  ClienteSoftrePthVrm02.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "JvmApplication02", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="JvmApplication02"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreJvmApplication02 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "JvmApplication02"); 
  ClienteSoftreJvmApplication02.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreJvmApplication02.getClassType()%>" id="<%=ClienteSoftreJvmApplication02.getId()%>" maxlength="<%=ClienteSoftreJvmApplication02.getMaxLength()%>" name="<%=ClienteSoftreJvmApplication02.getName()%>" size="<%=ClienteSoftreJvmApplication02.getSize()%>"><% 
  ClienteSoftreJvmApplication02.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "JvmBatch02", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="JvmBatch02"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreJvmBatch02 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "JvmBatch02"); 
  ClienteSoftreJvmBatch02.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreJvmBatch02.getClassType()%>" id="<%=ClienteSoftreJvmBatch02.getId()%>" maxlength="<%=ClienteSoftreJvmBatch02.getMaxLength()%>" name="<%=ClienteSoftreJvmBatch02.getName()%>" size="<%=ClienteSoftreJvmBatch02.getSize()%>"><% 
  ClienteSoftreJvmBatch02.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "SirioVrm02", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="SirioVrm02"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreSirioVrm02 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "SirioVrm02"); 
  ClienteSoftreSirioVrm02.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreSirioVrm02.getClassType()%>" id="<%=ClienteSoftreSirioVrm02.getId()%>" maxlength="<%=ClienteSoftreSirioVrm02.getMaxLength()%>" name="<%=ClienteSoftreSirioVrm02.getName()%>" size="<%=ClienteSoftreSirioVrm02.getSize()%>"><% 
  ClienteSoftreSirioVrm02.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "CrystalVrm02", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="CrystalVrm02"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftreCrystalVrm02 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "CrystalVrm02"); 
  ClienteSoftreCrystalVrm02.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftreCrystalVrm02.getClassType()%>" id="<%=ClienteSoftreCrystalVrm02.getId()%>" maxlength="<%=ClienteSoftreCrystalVrm02.getMaxLength()%>" name="<%=ClienteSoftreCrystalVrm02.getName()%>" size="<%=ClienteSoftreCrystalVrm02.getSize()%>"><% 
  ClienteSoftreCrystalVrm02.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "PasswordAdmin02", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="PasswordAdmin02"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput ClienteSoftrePasswordAdmin02 =  
     new com.thera.thermfw.web.WebTextInput("ClienteSoftre", "PasswordAdmin02"); 
  ClienteSoftrePasswordAdmin02.setParent(ClienteSoftreForm); 
%>
<input class="<%=ClienteSoftrePasswordAdmin02.getClassType()%>" id="<%=ClienteSoftrePasswordAdmin02.getId()%>" maxlength="<%=ClienteSoftrePasswordAdmin02.getMaxLength()%>" name="<%=ClienteSoftrePasswordAdmin02.getName()%>" size="<%=ClienteSoftrePasswordAdmin02.getSize()%>"><% 
  ClienteSoftrePasswordAdmin02.write(out); 
%>

                                </td>
                              </tr>
                            </table>
                          </fieldset>
                        </td>
                      </tr>
                    </table>
                    <table>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "LinkPantheraExt", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="LinkPantheraExt"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput ClienteSoftreLinkPantheraExt =  
     new com.thera.thermfw.web.WebTextArea("ClienteSoftre", "LinkPantheraExt"); 
  ClienteSoftreLinkPantheraExt.setParent(ClienteSoftreForm); 
%>
<textarea class="<%=ClienteSoftreLinkPantheraExt.getClassType()%>" cols="60" id="<%=ClienteSoftreLinkPantheraExt.getId()%>" maxlength="<%=ClienteSoftreLinkPantheraExt.getMaxLength()%>" name="<%=ClienteSoftreLinkPantheraExt.getName()%>" rows="5" size="<%=ClienteSoftreLinkPantheraExt.getSize()%>"></textarea><% 
  ClienteSoftreLinkPantheraExt.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "NoteCliente", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="NoteCliente"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput ClienteSoftreNoteCliente =  
     new com.thera.thermfw.web.WebTextArea("ClienteSoftre", "NoteCliente"); 
  ClienteSoftreNoteCliente.setParent(ClienteSoftreForm); 
%>
<textarea class="<%=ClienteSoftreNoteCliente.getClassType()%>" cols="60" id="<%=ClienteSoftreNoteCliente.getId()%>" maxlength="<%=ClienteSoftreNoteCliente.getMaxLength()%>" name="<%=ClienteSoftreNoteCliente.getName()%>" rows="5" size="<%=ClienteSoftreNoteCliente.getSize()%>"></textarea><% 
  ClienteSoftreNoteCliente.write(out); 
%>

                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            <% mytabbed.endTab(); %> 
</div>
            <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab2")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab2"); %>
              <table style="width: 100%;">
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "AmbienteSviluppo", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="AmbienteSviluppo"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebComboBox ClienteSoftreAmbienteSviluppo =  
     new com.thera.thermfw.web.WebComboBox("ClienteSoftre", "AmbienteSviluppo", null); 
  ClienteSoftreAmbienteSviluppo.setParent(ClienteSoftreForm); 
%>
<select id="<%=ClienteSoftreAmbienteSviluppo.getId()%>" name="<%=ClienteSoftreAmbienteSviluppo.getName()%>"><% 
  ClienteSoftreAmbienteSviluppo.write(out); 
%> 
</select>
                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "ManagerProject", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="ManagerProject"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebComboBox ClienteSoftreManagerProject =  
     new com.thera.thermfw.web.WebComboBox("ClienteSoftre", "ManagerProject", null); 
  ClienteSoftreManagerProject.setParent(ClienteSoftreForm); 
%>
<select id="<%=ClienteSoftreManagerProject.getId()%>" name="<%=ClienteSoftreManagerProject.getName()%>"><% 
  ClienteSoftreManagerProject.write(out); 
%> 
</select>
                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "NoteServerSvil", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="NoteServerSvil"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput ClienteSoftreNoteServerSvil =  
     new com.thera.thermfw.web.WebTextArea("ClienteSoftre", "NoteServerSvil"); 
  ClienteSoftreNoteServerSvil.setParent(ClienteSoftreForm); 
%>
<textarea class="<%=ClienteSoftreNoteServerSvil.getClassType()%>" cols="60" id="<%=ClienteSoftreNoteServerSvil.getId()%>" maxlength="<%=ClienteSoftreNoteServerSvil.getMaxLength()%>" name="<%=ClienteSoftreNoteServerSvil.getName()%>" rows="5" size="<%=ClienteSoftreNoteServerSvil.getSize()%>"></textarea><% 
  ClienteSoftreNoteServerSvil.write(out); 
%>

                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "GitProject", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="GitProject"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput ClienteSoftreGitProject =  
     new com.thera.thermfw.web.WebTextArea("ClienteSoftre", "GitProject"); 
  ClienteSoftreGitProject.setParent(ClienteSoftreForm); 
%>
<textarea class="<%=ClienteSoftreGitProject.getClassType()%>" cols="60" id="<%=ClienteSoftreGitProject.getId()%>" maxlength="<%=ClienteSoftreGitProject.getMaxLength()%>" name="<%=ClienteSoftreGitProject.getName()%>" rows="5" size="<%=ClienteSoftreGitProject.getSize()%>"></textarea><% 
  ClienteSoftreGitProject.write(out); 
%>

                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "ProjectNote", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="ProjectNote"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput ClienteSoftreProjectNote =  
     new com.thera.thermfw.web.WebTextArea("ClienteSoftre", "ProjectNote"); 
  ClienteSoftreProjectNote.setParent(ClienteSoftreForm); 
%>
<textarea class="<%=ClienteSoftreProjectNote.getClassType()%>" cols="60" id="<%=ClienteSoftreProjectNote.getId()%>" maxlength="<%=ClienteSoftreProjectNote.getMaxLength()%>" name="<%=ClienteSoftreProjectNote.getName()%>" rows="5" size="<%=ClienteSoftreProjectNote.getSize()%>"></textarea><% 
  ClienteSoftreProjectNote.write(out); 
%>

                  </td>
                </tr>
              </table>
            <% mytabbed.endTab(); %> 
</div>
            <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab3")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab3"); %>
              <table style="width: 100%;">
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ClienteSoftre", "LinkVpnConfiguration", null); 
   label.setParent(ClienteSoftreForm); 
%><label class="<%=label.getClassType()%>" for="LinkVpnConfiguration"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput ClienteSoftreLinkVpnConfiguration =  
     new com.thera.thermfw.web.WebTextArea("ClienteSoftre", "LinkVpnConfiguration"); 
  ClienteSoftreLinkVpnConfiguration.setParent(ClienteSoftreForm); 
%>
<textarea class="<%=ClienteSoftreLinkVpnConfiguration.getClassType()%>" cols="60" id="<%=ClienteSoftreLinkVpnConfiguration.getId()%>" maxlength="<%=ClienteSoftreLinkVpnConfiguration.getMaxLength()%>" name="<%=ClienteSoftreLinkVpnConfiguration.getName()%>" rows="5" size="<%=ClienteSoftreLinkVpnConfiguration.getSize()%>"></textarea><% 
  ClienteSoftreLinkVpnConfiguration.write(out); 
%>

                  </td>
                </tr>
              </table>
              <table style="height:100%; width:100%">
                <tr>
                  <td valign="top">
                    <iframe id="ConfigurazioneVPN" src style="height: 78vh;   width: -webkit-fill-available;   border: 2px solid black;   border-radius: 10px;   margin: 10px;"></iframe>
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
  errorList.setParent(ClienteSoftreForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
        </td>
      </tr>
    </table>
  <%
  ClienteSoftreForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = ClienteSoftreForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", ClienteSoftreBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              ClienteSoftreForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, ClienteSoftreBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, ClienteSoftreBODC.getErrorList().getErrors()); 
           if(ClienteSoftreBODC.getConflict() != null) 
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
     if(ClienteSoftreBODC != null && !ClienteSoftreBODC.close(false)) 
        errors.addAll(0, ClienteSoftreBODC.getErrorList().getErrors()); 
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
     String errorPage = ClienteSoftreForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", ClienteSoftreBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = ClienteSoftreForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>

</html>
