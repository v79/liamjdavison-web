/* ========================================== 
scrollTop() >= 300
Should be equal the the height of the header
========================================== */

window.onscroll = function (el) {
	var nav = $('nav');
	if (window.pageYOffset >= 240) {
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
};

$('#login').click(function() {
	var showThis = this.dataset.modal;
	showModal(showThis);
});

$('.js-close-modal').click(function() {
	var closeThis = this.dataset.modal;
	hideModal(closeThis);
})
function showModal(modalName) {
	$('#' + modalName).addClass('active');
}

function hideModal(modalName) {
	$('#' + modalName).removeClass('active');
}