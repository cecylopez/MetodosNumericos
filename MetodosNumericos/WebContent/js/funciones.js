var root = "/MetodosNumericos/";

var plot1, plot2, plot3, plot4;
var puntos1, puntos2, puntos3, puntos4;

function convertFileToDataURLviaFileReader(url, callback) {
	var xhr = new XMLHttpRequest();
	xhr.responseType = 'blob';
	xhr.onload = function() {
		var reader = new FileReader();
		reader.onloadend = function() {
			callback(reader.result);
		}
		reader.readAsDataURL(xhr.response);
	};
	xhr.open('GET', url);
	xhr.send();
}

$(document).ready(function() {
	$('#menuTabs li a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	});

});

function evaluarMetodo1(nombreMetodo, idFormulario, idGrafico, idTabla) {
	if(!$("#" + idFormulario).valid()) {
		return;
	}
	
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.puntos) {
			$("#" + idGrafico).show();
			puntos1 = data.puntos;
			
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
			$("#btnGenerarPDF1").show();
		}
	}, "json");
}

function evaluarMetodo2(nombreMetodo, idFormulario, idGrafico, idTabla) {
	if(!$("#" + idFormulario).valid()) {
		return;
	}
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.puntos) {
			$("#" + idGrafico).show();
			puntos2 = data.puntos;
			
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
			$("#btnGenerarPDF2").show();
		}
	}, "json");
}

function evaluarMetodo3(nombreMetodo, idFormulario, idGrafico, idTabla) {
	if(!$("#" + idFormulario).valid()) {
		return;
	}
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.puntos) {
			$("#" + idGrafico).show();
			puntos3 = data.derivada;
			
			if(plot3) plot3.destroy();
			
			plot3 = $.jqplot(idGrafico, [data.puntos], {
				series: [{fill: false}, {}]
			});
			
			$("#" + idTabla + " tbody").empty();
			
			var tr = $("<tr>");
			tr.append($("<td>").append(data.derivada));
				
			$("#" + idTabla + " tbody").append(tr);
			
			$("#" + idTabla).show();
			$("#btnGenerarPDF3").show();
		}
	}, "json");
}

function evaluarMetodo4(nombreMetodo, idFormulario, idGrafico, idTabla) {
	if(!$("#" + idFormulario).valid()) {
		return;
	}
	$.post(root + nombreMetodo, $("#" + idFormulario).serialize(), function(data) {
		if (data.resultado) {
			
			$("#" + idTabla + " tbody").empty();
			puntos4 = data.resultado;
			
			var tr = $("<tr>");
			tr.append($("<td>").append(data.resultado));
				
			$("#" + idTabla + " tbody").append(tr);
			
			$("#" + idTabla).show();
			$("#btnGenerarPDF4").show();
			
			$("#" + idGrafico).empty();
			$("#" + idGrafico).append($("<img>").attr("alt", "grafico").attr("src", data.urlGrafico));
			$("#" + idGrafico).show();
		}
	}, "json");
}

function generarPDF1() {
	var img = $("#chartMetodo1").jqplotToImageStr({});
	var params = $("#formularioM1").serialize();
	
	$("#params").val(params);
	$("#puntos").val(puntos1);
	$("#img").val(img);
	$("#metodo").val("1");
	
	$("#frmPDFGen").submit();
	
}

function generarPDF2() {
	var img = $("#chartMetodo2").jqplotToImageStr({});
	var params = $("#formularioM2").serialize();
	
	$("#params").val(params);
	$("#puntos").val(puntos2);
	$("#img").val(img);
	$("#metodo").val("2");
	
	$("#frmPDFGen").submit();
}

function generarPDF3() {
	var img = $("#chartMetodo3").jqplotToImageStr({});
	var params = $("#formularioM3").serialize();
	
	$("#params").val(params);
	$("#puntos").val(puntos3);
	$("#img").val(img);
	$("#metodo").val("3");
	
	$("#frmPDFGen").submit();
}

function generarPDF4() {
	var params = $("#formularioM4").serialize();
	
	$("#params").val(params);
	$("#puntos").val(puntos4);
	$("#metodo").val("4");
	
	convertFileToDataURLviaFileReader($("#chartMetodo4 img").attr("src"), function(base64img) {
		$("#img").val(base64img);
		$("#frmPDFGen").submit();
	});
}