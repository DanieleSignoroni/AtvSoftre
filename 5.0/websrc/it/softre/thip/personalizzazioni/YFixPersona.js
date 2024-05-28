function YFixPersonaOL(){
  document.getElementById('Analisi').src = document.getElementById('LinkAnalisi').value;
  document.getElementById('Documentazione').src = document.getElementById('LinkDocumentazione').value;
  window.resizeTo(3000, 3000);
}

function settaDataOdierna(){
	const date = new Date();
	let day = date.getDate();
	let month = date.getMonth() + 1;
	let year = date.getFullYear();
	var data = day + "/" + month + "/" + year;
	document.getElementById('DataRilascio').value = data;
}
