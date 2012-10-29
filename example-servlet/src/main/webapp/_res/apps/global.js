function go(url) {
	window.location.href = url;
}

$(document).ready(function() {
	$('.dropdown-toggle').dropdown();

	$('#mainMenu3').tooltip({placement:'right'});
	$('#mainMenu4').tooltip({placement:'right'});
});