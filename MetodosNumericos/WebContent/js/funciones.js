var root = "/MetodosNumericos/";

var plot1, plot2, plot3, plot4;

$(document).ready(function() {
	$('#menuTabs li a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	});

});

function evaluarMetodo1(nombreMetodo, idFormulario, idGrafico, idTabla) {
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.puntos) {
			$("#" + idGrafico).show();
			
			if(plot1) plot1.destroy();
			
			plot1 = $.jqplot(idGrafico, [data.puntos], {
				series: [{fill: false}, {}]
			});
			
			$("#" + idTabla + " tbody").empty();
			
			for(var i=0; i<data.puntos.length; i++) {
				var tr = $("<tr>");
				tr.append($("<td>").append(i + 1));
				tr.append($("<td>").append(data.puntos[i][0]));
				tr.append($("<td>").append(data.puntos[i][1]));
				
				$("#" + idTabla + " tbody").append(tr);
			}
			
			$("#" + idTabla).show();
		}
	}, "json");
}

function evaluarMetodo2(nombreMetodo, idFormulario, idGrafico, idTabla) {
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.puntos) {
			$("#" + idGrafico).show();
			
			if(plot2) plot2.destroy();
			
			var arr = [data.puntos[0], data.puntos[1], data.puntos[2]];
			
			plot2 = $.jqplot(idGrafico, [arr], {
				series: [{fill: true}, {}]
			});
			
			$("#" + idTabla + " tbody").empty();
				
			var tr = $("<tr>");
			tr.append($("<th>").append("x0"));
			tr.append($("<td>").append(data.puntos[0][0]));
			tr.append($("<td>").append(data.puntos[0][1]));
			
			$("#" + idTabla + " tbody").append(tr);
			
			tr = $("<tr>");
			tr.append($("<th>").append("x1"));
			tr.append($("<td>").append(data.puntos[1][0]));
			tr.append($("<td>").append(data.puntos[1][1]));
			
			$("#" + idTabla + " tbody").append(tr);
			
			tr = $("<tr>");
			tr.append($("<th>").append("x2"));
			tr.append($("<td>").append(data.puntos[2][0]));
			tr.append($("<td>").append(data.puntos[2][1]));
			
			$("#" + idTabla + " tbody").append(tr);
			
			
			tr = $("<tr>");
			tr.append($("<th>").append("resultado"));
			tr.append($("<td>").append(""));
			tr.append($("<td>").append(data.puntos[3][1]));
			
			$("#" + idTabla + " tbody").append(tr);
			
			
			$("#" + idTabla).show();
		}
	}, "json");
}
