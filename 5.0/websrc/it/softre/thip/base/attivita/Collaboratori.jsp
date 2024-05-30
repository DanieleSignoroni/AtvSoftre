<%@page import="it.softre.thip.base.attivita.AttivitaCollaboratore"%>
<%@page import="java.util.List"%>
<%@page import="it.softre.thip.base.attivita.AttivitaSoftre"%>
<%@page import="com.thera.thermfw.web.WebForm"%>
<%
WebForm parentForm = (WebForm) request.getAttribute("parentForm");
AttivitaSoftre attivita = (AttivitaSoftre) parentForm.getBODataCollector().getBo();
List<AttivitaCollaboratore> collaboratori = attivita.getAttivitaCollaboratori();
%>
<html>
<head>
<style>
.right-click-div {
            position: relative;
        }
        .custom-context-menu {
            display: none;
            background-color: white;
            border: 1px solid #ccc;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
            z-index: 0;
        }
        .custom-context-menu ul {
            list-style: none;
            padding: 5px;
            margin: 0;
        }
        .custom-context-menu li {
            padding: 8px 12px;
            cursor: pointer;
        }
        .custom-context-menu li:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<div class="container-fluid">
<div class="row">
	<%for(AttivitaCollaboratore collaboratore : collaboratori) { %>
		<div class="col-sm text-center right-click-div" id="<%=collaboratore.getKey()%>">
			 <div class="custom-context-menu">
                    <ul>
                        <li onclick="handleDelete('<%= collaboratore.getKey() %>')">Rimuovi collaboratore</li>
                    </ul>
                </div>
			<img class="collaboratore-img" style="width:75px" src="<%=collaboratore.getUrlImmagineCollaboratore()%>"></img>
			<p class="font-weight-bold"><%=collaboratore.getNomeCollaboratore() %></p>
		</div>
	<%} %>
</div>
</div>
<script>
document.addEventListener('DOMContentLoaded', (event) => {
    const rightClickDivs = document.querySelectorAll('.right-click-div');

    rightClickDivs.forEach(div => {
        div.addEventListener('contextmenu', function(event) {
            event.preventDefault();
            closeAllContextMenus();
            const contextMenu = this.querySelector('.custom-context-menu');

            const img = this.querySelector('.collaboratore-img');
            const rect = img.getBoundingClientRect();

            const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            const scrollLeft = window.pageXOffset || document.documentElement.scrollLeft;

            const topPosition = rect.top + scrollTop;
            const leftPosition = rect.left + scrollLeft;

            contextMenu.style.top = topPosition + 'px';
            contextMenu.style.left = leftPosition + 'px';
            contextMenu.style.display = 'block';
        });

        document.addEventListener('click', function() {
            closeAllContextMenus();
        });
    });
});

function closeAllContextMenus() {
    const contextMenus = document.querySelectorAll('.custom-context-menu');
    contextMenus.forEach(menu => {
        menu.style.display = 'none';
    });
}

function handleDelete(id) {
	var className = document.forms[0].thClassName.value;
	var key = document.forms[0].thKey.value;
	var url = "/" + webAppPath + "/" + servletPath + "/it.softre.thip.base.attiivta.web.RimuoviCollaboratoreAttivita?";
	url = url + "KeyCollab=" + id + "&thKey="+key+"&thClassName="+className;
	setLocationOnWindow(document.getElementById(errorsFrameName).contentWindow,url);
}
</script>
</body>
</html>