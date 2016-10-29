var root = "/MetodosNumericos/";

$(document).ready(function() {
	$('#menuTabs li a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	});

});

function evaluarMetodo(nombreMetodo, idFormulario, idGrafico) {
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.puntos) {
			$("#" + idGrafico).show();
			$.jqplot(idGrafico, [data.puntos], {
				series: [{fill: true}, {}]
			});
		}
	}, "json");
}
