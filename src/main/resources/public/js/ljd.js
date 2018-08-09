/* ========================================== 
scrollTop() >= 300
Should be equal the the height of the header
========================================== */

window.onscroll = function (el) {
	var nav = u('nav');
	if (window.pageYOffset >= 240) {
		// we have scrolled down the page
		nav.addClass('fixed-header');
		nav.removeClass('translucent');
		nav.addClass('opaque');

		u('#site-title').removeClass('invisible');

	}
	else {
		// we are at the top of the page
		nav.removeClass('fixed-header');
		nav.addClass('translucent');
		nav.removeClass('opaque');

		u('#site-title').addClass('invisible');

	}
};

// in umbrella, the target of the event is 'this'
u('#login').on('click', function(event) {
	showModal(u(this.dataset.modal))
	// alert("this.dataset.modal: " + this.dataset.modal);
});

function showModal(element) {
	console.log("showing u(element) " + u(element));
	u( element).style.display = 'block';
}
