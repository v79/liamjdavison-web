/* ========================================== 
scrollTop() >= 300
Should be equal the the height of the header
========================================== */

$(window).scroll(function () {
	var nav = $('nav')
	if ($(window).scrollTop() >= 300) {
		// we have scrolled down the page

		nav.addClass('fixed-header');
		nav.removeClass('translucent');
		nav.addClass('opaque');

		$('#site-title').removeClass('invisible');

	}
	else {
		// we are at the top of the page
		nav.removeClass('fixed-header');
		nav.addClass('translucent');
		nav.removeClass('opaque');


		$('#site-title').addClass('invisible');

	}
});