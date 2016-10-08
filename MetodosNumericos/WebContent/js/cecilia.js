$(document).ready(function() {
	$('#menuTabs li a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	});

});
