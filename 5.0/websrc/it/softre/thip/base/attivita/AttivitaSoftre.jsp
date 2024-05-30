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
  BODataCollector AttivitaSoftreBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm AttivitaSoftreForm =  
     new com.thera.thermfw.web.WebForm(request, response, "AttivitaSoftreForm", "AttivitaSoftre", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/base/attivita/AttivitaSoftre.js"); 
  AttivitaSoftreForm.setServletEnvironment(se); 
  AttivitaSoftreForm.setJSTypeList(jsList); 
  AttivitaSoftreForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  AttivitaSoftreForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  AttivitaSoftreForm.setWebFormModifierClass("it.softre.thip.base.attivita.web.AttivitaSoftreFormModifier"); 
  AttivitaSoftreForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = AttivitaSoftreForm.getMode(); 
  String key = AttivitaSoftreForm.getKey(); 
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
        AttivitaSoftreForm.outTraceInfo(getClass().getName()); 
        String collectorName = AttivitaSoftreForm.findBODataCollectorName(); 
                AttivitaSoftreBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (AttivitaSoftreBODC instanceof WebDataCollector) 
            ((WebDataCollector)AttivitaSoftreBODC).setServletEnvironment(se); 
        AttivitaSoftreBODC.initialize("AttivitaSoftre", true, 0); 
        AttivitaSoftreForm.setBODataCollector(AttivitaSoftreBODC); 
        int rcBODC = AttivitaSoftreForm.initSecurityServices(); 
        mode = AttivitaSoftreForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           AttivitaSoftreForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = AttivitaSoftreBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              AttivitaSoftreForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

  <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" rel="stylesheet">
  
  <% 
  WebScript script_0 =  
   new com.thera.thermfw.web.WebScript(); 
 script_0.setRequest(request); 
 script_0.setSrcAttribute("https://code.jquery.com/jquery-3.6.0.min.js"); 
 script_0.setLanguageAttribute(null); 
  script_0.write(out); 
%>
<!--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
  <% 
  WebScript script_1 =  
   new com.thera.thermfw.web.WebScript(); 
 script_1.setRequest(request); 
 script_1.setSrcAttribute("https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"); 
 script_1.setLanguageAttribute(null); 
  script_1.write(out); 
%>
<!--<script crossorigin="anonymous" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"></script>-->
  <% 
  WebLink link_0 =  
   new com.thera.thermfw.web.WebLink(); 
 link_0.setHttpServletRequest(request); 
 link_0.setHRefAttribute("it/softre/thip/base/attivita/AttivitaSoftre.css"); 
 link_0.setRelAttribute("stylesheet"); 
 link_0.setTypeAttribute("text/css"); 
  link_0.write(out); 
%>
<!--<link href="it/softre/thip/base/attivita/AttivitaSoftre.css" rel="stylesheet" type="text/css">
  -->
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(AttivitaSoftreForm); 
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
  myToolBarTB.setParent(AttivitaSoftreForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body onbeforeunload="<%=AttivitaSoftreForm.getBodyOnBeforeUnload()%>" onload="<%=AttivitaSoftreForm.getBodyOnLoad()%>" onunload="<%=AttivitaSoftreForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   AttivitaSoftreForm.writeBodyStartElements(out); 
%> 

  <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = AttivitaSoftreForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", AttivitaSoftreBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=AttivitaSoftreForm.getServlet()%>" method="post" name="AttivitaSoftreForm" style="height:100%"><%
  AttivitaSoftreForm.writeFormStartElements(out); 
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
  mytabbed.setParent(AttivitaSoftreForm); 
 mytabbed.addTab("tab1", "it.softre.thip.base.attivita.resources.AttivitaSoftre", "tab1", "AttivitaSoftre", null, null, null, null); 
 mytabbed.addTab("tab2", "it.softre.thip.base.attivita.resources.AttivitaSoftre", "tab2", "AttivitaSoftre", null, null, null, null); 
 mytabbed.addTab("tab3", "it.softre.thip.base.attivita.resources.AttivitaSoftre", "tab3", "AttivitaSoftre", null, null, null, null); 
 mytabbed.addTab("tab5", "it.softre.thip.base.attivita.resources.AttivitaSoftre", "tab5", "AttivitaSoftre", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
            <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
              <table style="width: 100%;">
                <tr>
                  <td style="width: 35%;" valign="top">
                    <table>
                      <tr>
                        <td>
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "Id", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="Id"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreId =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "Id"); 
  AttivitaSoftreId.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreId.getClassType()%>" id="<%=AttivitaSoftreId.getId()%>" maxlength="<%=AttivitaSoftreId.getMaxLength()%>" name="<%=AttivitaSoftreId.getName()%>" size="<%=AttivitaSoftreId.getSize()%>"><% 
  AttivitaSoftreId.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "NomeAttivita", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="NomeAttivita"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreNomeAttivita =  
     new com.thera.thermfw.web.WebTextArea("AttivitaSoftre", "NomeAttivita"); 
  AttivitaSoftreNomeAttivita.setParent(AttivitaSoftreForm); 
%>
<textarea class="<%=AttivitaSoftreNomeAttivita.getClassType()%>" cols="60" id="<%=AttivitaSoftreNomeAttivita.getId()%>" maxlength="<%=AttivitaSoftreNomeAttivita.getMaxLength()%>" name="<%=AttivitaSoftreNomeAttivita.getName()%>" rows="5" size="<%=AttivitaSoftreNomeAttivita.getSize()%>"></textarea><% 
  AttivitaSoftreNomeAttivita.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td colspan="2">
                          <fieldset class="date">
                            <legend>Date - Attivita</legend>
                            <table>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "DataPrevistaConsegna", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DataPrevistaConsegna"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreDataPrevistaConsegna =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "DataPrevistaConsegna"); 
  AttivitaSoftreDataPrevistaConsegna.setShowCalendarBtn(true); 
  AttivitaSoftreDataPrevistaConsegna.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreDataPrevistaConsegna.getClassType()%>" id="<%=AttivitaSoftreDataPrevistaConsegna.getId()%>" maxlength="<%=AttivitaSoftreDataPrevistaConsegna.getMaxLength()%>" name="<%=AttivitaSoftreDataPrevistaConsegna.getName()%>" size="<%=AttivitaSoftreDataPrevistaConsegna.getSize()%>"><% 
  AttivitaSoftreDataPrevistaConsegna.write(out); 
%>

                                </td>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "DataIncontroCliente", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DataIncontroCliente"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreDataIncontroCliente =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "DataIncontroCliente"); 
  AttivitaSoftreDataIncontroCliente.setShowCalendarBtn(true); 
  AttivitaSoftreDataIncontroCliente.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreDataIncontroCliente.getClassType()%>" id="<%=AttivitaSoftreDataIncontroCliente.getId()%>" maxlength="<%=AttivitaSoftreDataIncontroCliente.getMaxLength()%>" name="<%=AttivitaSoftreDataIncontroCliente.getName()%>" size="<%=AttivitaSoftreDataIncontroCliente.getSize()%>"><% 
  AttivitaSoftreDataIncontroCliente.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "DataCompletamento", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DataCompletamento"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreDataCompletamento =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "DataCompletamento"); 
  AttivitaSoftreDataCompletamento.setShowCalendarBtn(true); 
  AttivitaSoftreDataCompletamento.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreDataCompletamento.getClassType()%>" id="<%=AttivitaSoftreDataCompletamento.getId()%>" maxlength="<%=AttivitaSoftreDataCompletamento.getMaxLength()%>" name="<%=AttivitaSoftreDataCompletamento.getName()%>" size="<%=AttivitaSoftreDataCompletamento.getSize()%>"><% 
  AttivitaSoftreDataCompletamento.write(out); 
%>

                                </td>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "DataInizio", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DataInizio"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreDataFine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "DataFine"); 
  AttivitaSoftreDataFine.setShowCalendarBtn(true); 
  AttivitaSoftreDataFine.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreDataFine.getClassType()%>" id="<%=AttivitaSoftreDataFine.getId()%>" maxlength="<%=AttivitaSoftreDataFine.getMaxLength()%>" name="<%=AttivitaSoftreDataFine.getName()%>" size="<%=AttivitaSoftreDataFine.getSize()%>"><% 
  AttivitaSoftreDataFine.write(out); 
%>

                                </td>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "DataFine", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DataFine"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreDataInizio =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "DataInizio"); 
  AttivitaSoftreDataInizio.setShowCalendarBtn(true); 
  AttivitaSoftreDataInizio.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreDataInizio.getClassType()%>" id="<%=AttivitaSoftreDataInizio.getId()%>" maxlength="<%=AttivitaSoftreDataInizio.getMaxLength()%>" name="<%=AttivitaSoftreDataInizio.getName()%>" size="<%=AttivitaSoftreDataInizio.getSize()%>"><% 
  AttivitaSoftreDataInizio.write(out); 
%>

                                </td>
                              </tr>
                            </table>
                          </fieldset>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2">
                          <fieldset class="quotazioni">
                            <legend>Quotazione - Informazioni</legend>
                            <table>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "QuotazioneOre", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="QuotazioneOre"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreQuotazioneOre =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "QuotazioneOre"); 
  AttivitaSoftreQuotazioneOre.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreQuotazioneOre.getClassType()%>" id="<%=AttivitaSoftreQuotazioneOre.getId()%>" maxlength="<%=AttivitaSoftreQuotazioneOre.getMaxLength()%>" name="<%=AttivitaSoftreQuotazioneOre.getName()%>" size="<%=AttivitaSoftreQuotazioneOre.getSize()%>"><% 
  AttivitaSoftreQuotazioneOre.write(out); 
%>

                                </td>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "QuotazioneGg", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="QuotazioneGg"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebTextInput AttivitaSoftreQuotazioneGg =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "QuotazioneGg"); 
  AttivitaSoftreQuotazioneGg.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreQuotazioneGg.getClassType()%>" id="<%=AttivitaSoftreQuotazioneGg.getId()%>" maxlength="<%=AttivitaSoftreQuotazioneGg.getMaxLength()%>" name="<%=AttivitaSoftreQuotazioneGg.getName()%>" size="<%=AttivitaSoftreQuotazioneGg.getSize()%>"><% 
  AttivitaSoftreQuotazioneGg.write(out); 
%>

                                </td>
                              </tr>
                              <tr>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "TipoFatturazione", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="TipoFatturazione"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebComboBox AttivitaSoftreTipoFatturazione =  
     new com.thera.thermfw.web.WebComboBox("AttivitaSoftre", "TipoFatturazione", null); 
  AttivitaSoftreTipoFatturazione.setParent(AttivitaSoftreForm); 
%>
<select id="<%=AttivitaSoftreTipoFatturazione.getId()%>" name="<%=AttivitaSoftreTipoFatturazione.getName()%>"><% 
  AttivitaSoftreTipoFatturazione.write(out); 
%> 
</select>
                                </td>
                                <td valign="top">
                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "StatoCnfCliente", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="StatoCnfCliente"><%label.write(out);%></label><%}%>
                                </td>
                                <td valign="top">
                                  <% 
  WebComboBox AttivitaSoftreStatoCnfCliente =  
     new com.thera.thermfw.web.WebComboBox("AttivitaSoftre", "StatoCnfCliente", null); 
  AttivitaSoftreStatoCnfCliente.setParent(AttivitaSoftreForm); 
%>
<select id="<%=AttivitaSoftreStatoCnfCliente.getId()%>" name="<%=AttivitaSoftreStatoCnfCliente.getName()%>"><% 
  AttivitaSoftreStatoCnfCliente.write(out); 
%> 
</select>
                                </td>
                              </tr>
                            </table>
                          </fieldset>
                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "IdClienteSoftre", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="ClienteSoftre"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebMultiSearchForm AttivitaSoftreClienteSoftre =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaSoftre", "ClienteSoftre", false, false, true, 1, null, null); 
  AttivitaSoftreClienteSoftre.setParent(AttivitaSoftreForm); 
  AttivitaSoftreClienteSoftre.write(out); 
%>
<!--<span class="multisearchform" id="ClienteSoftre"></span>-->
                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "IdIncaricato", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="Incaricato"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebMultiSearchForm AttivitaSoftreIncaricato =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaSoftre", "Incaricato", false, false, true, 1, null, null); 
  AttivitaSoftreIncaricato.setParent(AttivitaSoftreForm); 
  AttivitaSoftreIncaricato.write(out); 
%>
<!--<span class="multisearchform" id="Incaricato"></span>-->
                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "DescrizioneAttivita", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="DescrizioneAttivita"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreDescrizioneAttivita =  
     new com.thera.thermfw.web.WebTextArea("AttivitaSoftre", "DescrizioneAttivita"); 
  AttivitaSoftreDescrizioneAttivita.setParent(AttivitaSoftreForm); 
%>
<textarea class="<%=AttivitaSoftreDescrizioneAttivita.getClassType()%>" cols="60" id="<%=AttivitaSoftreDescrizioneAttivita.getId()%>" maxlength="<%=AttivitaSoftreDescrizioneAttivita.getMaxLength()%>" name="<%=AttivitaSoftreDescrizioneAttivita.getName()%>" rows="5" size="<%=AttivitaSoftreDescrizioneAttivita.getSize()%>"></textarea><% 
  AttivitaSoftreDescrizioneAttivita.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "TicketSisthema", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="TicketSisthema"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreTicketSisthema =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "TicketSisthema"); 
  AttivitaSoftreTicketSisthema.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreTicketSisthema.getClassType()%>" id="<%=AttivitaSoftreTicketSisthema.getId()%>" maxlength="<%=AttivitaSoftreTicketSisthema.getMaxLength()%>" name="<%=AttivitaSoftreTicketSisthema.getName()%>" size="<%=AttivitaSoftreTicketSisthema.getSize()%>"><% 
  AttivitaSoftreTicketSisthema.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "RichiedenteSoftre", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="RichiedenteSoftre"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreRichiedenteSoftre =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "RichiedenteSoftre"); 
  AttivitaSoftreRichiedenteSoftre.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreRichiedenteSoftre.getClassType()%>" id="<%=AttivitaSoftreRichiedenteSoftre.getId()%>" maxlength="<%=AttivitaSoftreRichiedenteSoftre.getMaxLength()%>" name="<%=AttivitaSoftreRichiedenteSoftre.getName()%>" size="<%=AttivitaSoftreRichiedenteSoftre.getSize()%>"><% 
  AttivitaSoftreRichiedenteSoftre.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "RichiedenteCliente", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="RichiedenteCliente"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreRichiedenteCliente =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "RichiedenteCliente"); 
  AttivitaSoftreRichiedenteCliente.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreRichiedenteCliente.getClassType()%>" id="<%=AttivitaSoftreRichiedenteCliente.getId()%>" maxlength="<%=AttivitaSoftreRichiedenteCliente.getMaxLength()%>" name="<%=AttivitaSoftreRichiedenteCliente.getName()%>" size="<%=AttivitaSoftreRichiedenteCliente.getSize()%>"><% 
  AttivitaSoftreRichiedenteCliente.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "Priorita", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="Priorita"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftrePriorita =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "Priorita"); 
  AttivitaSoftrePriorita.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftrePriorita.getClassType()%>" id="<%=AttivitaSoftrePriorita.getId()%>" maxlength="<%=AttivitaSoftrePriorita.getMaxLength()%>" name="<%=AttivitaSoftrePriorita.getName()%>" size="<%=AttivitaSoftrePriorita.getSize()%>"><% 
  AttivitaSoftrePriorita.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "CommessaSmeup", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="CommessaSmeup"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebTextInput AttivitaSoftreCommessaSmeup =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "CommessaSmeup"); 
  AttivitaSoftreCommessaSmeup.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreCommessaSmeup.getClassType()%>" id="<%=AttivitaSoftreCommessaSmeup.getId()%>" maxlength="<%=AttivitaSoftreCommessaSmeup.getMaxLength()%>" name="<%=AttivitaSoftreCommessaSmeup.getName()%>" size="<%=AttivitaSoftreCommessaSmeup.getSize()%>"><% 
  AttivitaSoftreCommessaSmeup.write(out); 
%>

                        </td>
                      </tr>
                      <tr>
                        <td valign="top">
                          <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "StatoAttivita", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="StatoAttivita"><%label.write(out);%></label><%}%>
                        </td>
                        <td valign="top">
                          <% 
  WebComboBox AttivitaSoftreStatoAttivita =  
     new com.thera.thermfw.web.WebComboBox("AttivitaSoftre", "StatoAttivita", null); 
  AttivitaSoftreStatoAttivita.setParent(AttivitaSoftreForm); 
%>
<select id="<%=AttivitaSoftreStatoAttivita.getId()%>" name="<%=AttivitaSoftreStatoAttivita.getName()%>"><% 
  AttivitaSoftreStatoAttivita.write(out); 
request.setAttribute("parentForm", AttivitaSoftreForm); 
%> 
</select>
                        </td>
                      <tr>
                        <td colspan="2" class="mt-1 p-5">
                          <jsp:include flush="true" page="/it/softre/thip/base/attivita/Collaboratori.jsp"></jsp:include>
                        </td>
                      </tr>
                    </table>
                  </td>
                  <td style="width: 75%;">
                    <h3>Chat</h3>
                    <jsp:include flush="true" page="/it/softre/thip/base/attivita/Chat.jsp"></jsp:include>
                  </td>
                </tr>
              </table>
            <% mytabbed.endTab(); %> 
</div>
            <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab2")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab2"); %>
              <table style="width: 100%;">
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "LinkAnalisi", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="LinkAnalisi"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput AttivitaSoftreLinkAnalisi =  
     new com.thera.thermfw.web.WebTextArea("AttivitaSoftre", "LinkAnalisi"); 
  AttivitaSoftreLinkAnalisi.setParent(AttivitaSoftreForm); 
%>
<textarea class="<%=AttivitaSoftreLinkAnalisi.getClassType()%>" cols="60" id="<%=AttivitaSoftreLinkAnalisi.getId()%>" maxlength="<%=AttivitaSoftreLinkAnalisi.getMaxLength()%>" name="<%=AttivitaSoftreLinkAnalisi.getName()%>" rows="5" size="<%=AttivitaSoftreLinkAnalisi.getSize()%>"></textarea><% 
  AttivitaSoftreLinkAnalisi.write(out); 
%>

                  </td>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "ProprietarioAnalisi", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="ProprietarioAnalisi"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput AttivitaSoftreProprietarioAnalisi =  
     new com.thera.thermfw.web.WebTextInput("AttivitaSoftre", "ProprietarioAnalisi"); 
  AttivitaSoftreProprietarioAnalisi.setParent(AttivitaSoftreForm); 
%>
<input class="<%=AttivitaSoftreProprietarioAnalisi.getClassType()%>" id="<%=AttivitaSoftreProprietarioAnalisi.getId()%>" maxlength="<%=AttivitaSoftreProprietarioAnalisi.getMaxLength()%>" name="<%=AttivitaSoftreProprietarioAnalisi.getName()%>" size="<%=AttivitaSoftreProprietarioAnalisi.getSize()%>"><% 
  AttivitaSoftreProprietarioAnalisi.write(out); 
%>

                  </td>
                </tr>
                <tr>
                  <td colspan="4" valign="top">
                    <iframe id="AnalisiFrame" src style="height: 75vh;   width: -webkit-fill-available;   border: 2px solid black;   border-radius: 10px;   margin: 10px;"></iframe>
                  </td>
                </tr>
              </table>
            <% mytabbed.endTab(); %> 
</div>
            <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab3")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab3"); %>
              <table style="width: 100%;">
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "LinkDocumentazione", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="LinkDocumentazione"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput AttivitaSoftreLinkDocumentazione =  
     new com.thera.thermfw.web.WebTextArea("AttivitaSoftre", "LinkDocumentazione"); 
  AttivitaSoftreLinkDocumentazione.setParent(AttivitaSoftreForm); 
%>
<textarea class="<%=AttivitaSoftreLinkDocumentazione.getClassType()%>" cols="60" id="<%=AttivitaSoftreLinkDocumentazione.getId()%>" maxlength="<%=AttivitaSoftreLinkDocumentazione.getMaxLength()%>" name="<%=AttivitaSoftreLinkDocumentazione.getName()%>" rows="5" size="<%=AttivitaSoftreLinkDocumentazione.getSize()%>"></textarea><% 
  AttivitaSoftreLinkDocumentazione.write(out); 
%>

                  </td>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", "LinkFolderAllegati", null); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="LinkFolderAllegati"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                    <% 
  WebTextInput AttivitaSoftreLinkFolderAllegati =  
     new com.thera.thermfw.web.WebTextArea("AttivitaSoftre", "LinkFolderAllegati"); 
  AttivitaSoftreLinkFolderAllegati.setParent(AttivitaSoftreForm); 
%>
<textarea class="<%=AttivitaSoftreLinkFolderAllegati.getClassType()%>" cols="60" id="<%=AttivitaSoftreLinkFolderAllegati.getId()%>" maxlength="<%=AttivitaSoftreLinkFolderAllegati.getMaxLength()%>" name="<%=AttivitaSoftreLinkFolderAllegati.getName()%>" rows="5" size="<%=AttivitaSoftreLinkFolderAllegati.getSize()%>"></textarea><% 
  AttivitaSoftreLinkFolderAllegati.write(out); 
%>

                  </td>
                </tr>
                <tr>
                  <td colspan="4" valign="top">
                    <iframe id="DocumentazioneFrame" src style="height: 75vh;   width: -webkit-fill-available;   border: 2px solid black;   border-radius: 10px;   margin: 10px;"></iframe>
                  </td>
                </tr>
              </table>
            <% mytabbed.endTab(); %> 
</div>
            <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab5")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab5"); %>
              <table style="width: 100%;">
                <tr>
                  <td valign="top">
                    <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaSoftre", null, "AttivitaFixes"); 
   label.setParent(AttivitaSoftreForm); 
%><label class="<%=label.getClassType()%>" for="AttivitaFixes"><%label.write(out);%></label><%}%>
                  </td>
                  <td valign="top">
                  </td>
                </tr>
                <tr>
                  <td valign="top">
                    <!--<span class="editgrid" id="AttivitaFixes">--><% 
  WebEditGrid AttivitaSoftreAttivitaFixes =  
     new com.thera.thermfw.web.WebEditGrid("AttivitaSoftre", "AttivitaFixes", 8, new String[]{"IdAzienda", "IdFix", "Note", "Azienda.Descrizione", "Fix.Descrizione"}, 1, null, null,false,"com.thera.thermfw.web.servlet.GridActionAdapterForIndependentRow"); 
 AttivitaSoftreAttivitaFixes.setParent(AttivitaSoftreForm); 
 AttivitaSoftreAttivitaFixes.setNoControlRowKeys(false); 
 AttivitaSoftreAttivitaFixes.addHideAsDefault("Fix.Descrizione"); 
 AttivitaSoftreAttivitaFixes.addHideAsDefault("Azienda.Descrizione"); 
 AttivitaSoftreAttivitaFixes.write(out); 
%>
<BR><% 
   request.setAttribute("parentForm", AttivitaSoftreForm); 
   String CDForAttivitaFixes = "AttivitaFixes"; 
%>
<jsp:include page="/it/softre/thip/base/attivita/AttivitaFix.jsp" flush="true"> 
<jsp:param name="EditGridCDName" value="<%=CDForAttivitaFixes%>"/> 
<jsp:param name="Mode" value="NEW"/> 
</jsp:include> 
<!--</span>-->
                  </td>
                  <td valign="top">
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
  errorList.setParent(AttivitaSoftreForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
        </td>
      </tr>
    </table>
  <%
  AttivitaSoftreForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = AttivitaSoftreForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", AttivitaSoftreBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              AttivitaSoftreForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, AttivitaSoftreBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, AttivitaSoftreBODC.getErrorList().getErrors()); 
           if(AttivitaSoftreBODC.getConflict() != null) 
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
     if(AttivitaSoftreBODC != null && !AttivitaSoftreBODC.close(false)) 
        errors.addAll(0, AttivitaSoftreBODC.getErrorList().getErrors()); 
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
     String errorPage = AttivitaSoftreForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", AttivitaSoftreBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = AttivitaSoftreForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>

</html>
