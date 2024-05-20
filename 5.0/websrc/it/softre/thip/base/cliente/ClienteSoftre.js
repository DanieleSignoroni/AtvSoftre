function ClienteSoftreOL(){
   document.getElementById('ConfigurazioneVPN').src = document.getElementById('LinkVpnConfiguration').value;
  document.getElementById('LinkPantheraExt').addEventListener('dblclick', apriPantheraExt);
}

function apriPantheraExt(event){
	parent.window.open(document.getElementById('LinkPantheraExt').value);
}