var root = "/MetodosNumericos/";

$(document).ready(function() {
	$('#menuTabs li a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	});

});

function evaluarMetodo(nombreMetodo, idFormulario, idGrafico) {
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		console.log("Data reicbido: " + data);
		
//		var serie1= [[2, 4],[1.6,0.896],[1.44,0.115],[1.415,0.0031],[1.414,0]];
//		$.jqplot('chartMetodo1',[serie1], {
//			series: [{fill: true}, {}]
//		});
	}, "json");
}
